package com.github.wangcaide.common.log;

import com.github.wangcaide.common.constant.SysConstant;
import com.github.wangcaide.common.enums.ErrorCode;
import com.github.wangcaide.common.enums.OperationLogStatus;
import com.github.wangcaide.common.model.R;
import com.github.wangcaide.entity.OperationLogEntity;
import com.github.wangcaide.mapper.OperationLogMapper;
import com.github.wangcaide.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 操作日志aop
 * 将注解 @OperationLog 加到方法上（一般是controller层的方法）
 * @OperationLog 中的content可以放变量名
 * 变量的来源有三个地方
 * 1.从request中
 * 2.注解所在方法的入参中获取参数值
 * 3.OperationLogUtils.addParam
 * eg:
 *     @ApiOperation(value = "借书")
 *     @OperationLog(content = "借书，借书人：${borrowBy}， 图书的国际标准书号：${isbn}")
 *     @PostMapping("/book-borrow/{isbn}")
 *     public R borrowBook(
 *             @ApiParam(name = "isbn", value = "图书的国际标准书号", required = true)@PathVariable("isbn") String isbn,
 *             @ApiParam(name = "borrowBy", value = "借书人", required = true)String borrowBy,
 *             @ApiParam(name = "expirationTime", value = "到期时间(yyyy-MM-dd)", required = true)String expirationTime)
 *
 *     @OperationLog(content = "新用户注册，姓名${lastName}${firstName}")
 *     @ApiOperation(value = "账户注册")
 *     @PostMapping(value = "/register")
 *     public R<AccountVo> register(@Validated @RequestBody AccountVo vo) {
 *         OperationLogUtils.addParam("lastName", vo.getLastName());
 *         OperationLogUtils.addParam("firstName", vo.getFirstName());
 *         return R.ok(service.register(vo));
 *     }
 *
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-17 14:03:35
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class OperationLogAspect {

    private final OperationLogMapper operationLogMapper;

    @Pointcut(value = "@annotation(log)", argNames = "log")
    public void pointcut(OperationLog log) {
    }

    @Around(value = "pointcut(operationLog)", argNames = "joinPoint,operationLog")
    public Object around(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        Object result = joinPoint.proceed();
        try {
            // 从request及注解所在方法的入参中获取参数值
            Map<String, String> paramMap = getParameterMap(joinPoint);
            // 从工具类中获取参数值
            OperationLogUtils.getParams().entrySet().forEach(
                    entry -> paramMap.put(entry.getKey(), JsonUtil.toJSONString(entry.getValue())));
            //operationLog content中的参数变量替换成paramMap中的值
            String content = StringSubstitutor.replace(operationLog.content(), paramMap);
            OperationLogStatus status = checkStatus(result);
            Date current = new Date();
            OperationLogEntity log = new OperationLogEntity();
            log.setDescription(content);
            log.setStatus(status.getDbValue());
            log.setCreatedTime(current);
            log.setCreatedBy(SysConstant.SYSTEM_USER);
            log.setUpdatedTime(current);
            log.setUpdatedBy(SysConstant.SYSTEM_USER);
            operationLogMapper.insert(log);
        } catch (Throwable t) {
            log.error("记录操作日志失败", t);
        }
        return result;
    }

    private OperationLogStatus checkStatus(Object result) {
        OperationLogStatus status = OperationLogUtils.getStatus();
        if (Objects.nonNull(status)) {
            return status;
        }
        if (result instanceof R) {
            R r = (R) result;
            if (ErrorCode.SUCCESS.getCode().equals(r.getCode())) {
                return OperationLogStatus.SUCCESS;
            } else {
                return OperationLogStatus.FAILURE;
            }
        }
        return OperationLogStatus.SUCCESS;
    }

    private Map<String, String> getParameterMap(final ProceedingJoinPoint joinPoint) {
        Map<String, String> paramMap = new HashMap<>();
        try {
            // request parameter
            ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes());
            if (servletRequestAttributes != null) {
                HttpServletRequest request = servletRequestAttributes.getRequest();
                request.getParameterMap().entrySet().forEach(entry -> paramMap.put(entry.getKey(), StringUtils.join(entry.getValue())));
                // method parameter
                Object[] args = joinPoint.getArgs();
                Signature signature = joinPoint.getSignature();
                MethodSignature methodSignature = (MethodSignature) signature;
                String[] parameterNames = methodSignature.getParameterNames();
                for (int i = 0; i < parameterNames.length; i++) {
                    if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse) {
                        continue;
                    }
                    paramMap.put(parameterNames[i], JsonUtil.toJSONString(args[i]));
                }
            }
        } catch (Exception e) {
            log.error("getParameterMap exception", e);
        }
        return paramMap;
    }

}
