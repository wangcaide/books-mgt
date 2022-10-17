package com.github.wangcaide.controller;

import com.github.wangcaide.common.log.OperationLog;
import com.github.wangcaide.common.log.OperationLogUtils;
import com.github.wangcaide.common.model.R;
import com.github.wangcaide.service.AccountService;
import com.github.wangcaide.vo.AccountVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 账户Controller
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 15:23:11
 */
@Api(tags = "账户相关")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    /**
     * <p>
     * 账户注册
     * </p>
     * @param vo 账户vo
     * @return R<AccountVo> 通用响应结果
     * @author Caide, Wang (wangcaide@outlook.com)
     * @date 2022-10-15 15:23:27
     */
    @OperationLog(content = "新用户注册，姓名${lastName}${firstName}")
    @ApiOperation(value = "账户注册")
    @PostMapping(value = "/register")
    public R<AccountVo> register(@Validated @RequestBody AccountVo vo) {
        OperationLogUtils.addParam("lastName", vo.getLastName());
        OperationLogUtils.addParam("firstName", vo.getFirstName());
        return R.ok(service.register(vo));
    }

}
