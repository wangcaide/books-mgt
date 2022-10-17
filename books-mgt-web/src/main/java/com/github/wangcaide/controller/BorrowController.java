package com.github.wangcaide.controller;

import com.github.wangcaide.common.log.OperationLog;
import com.github.wangcaide.common.model.PageInfo;
import com.github.wangcaide.common.model.R;
import com.github.wangcaide.service.BorrowService;
import com.github.wangcaide.vo.BorrowLogVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 图书借换 Controller
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 14:54:19
 */
@Api(tags = "图书借阅相关")
@RestController
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService service;

    /**
     * <p>
     * 借书
     * </p>
     * @param isbn 图书的国际标准书号
     * @param borrowBy 借书人
     * @param expirationTime 到期时间(yyyy-MM-dd)
     * @return R 通用响应结果
     * @author Caide, Wang (wangcaide@outlook.com)
     * @date 2022-10-15 14:56:18
     */
    @ApiOperation(value = "借书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn", value = "图书的国际标准书号"),
            @ApiImplicitParam(name = "borrowBy", value = "借书人的username"),
            @ApiImplicitParam(name = "expirationTime", value = "到期时间(yyyy-MM-dd)")
    })
    @OperationLog(content = "借书，借书人：${borrowBy}， 图书的国际标准书号：${isbn}")
    @PostMapping("/book-borrow")
    public R borrowBook(String isbn, String borrowBy, String expirationTime) {
        service.borrowBook(isbn, borrowBy, expirationTime);
        return R.ok();
    }

    /**
     * <p>
     * 归还图书
     * </p>
     * @param isbn 图书的国际标准书号
     * @param borrowBy 借书人
     * @return R 通用响应结果
     * @author Caide, Wang (wangcaide@outlook.com)
     * @date 2022-10-15 15:05:41
     */
    @ApiOperation(value = "归还图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn", value = "图书的国际标准书号"),
            @ApiImplicitParam(name = "borrowBy", value = "借书人的username")
    })
    @PostMapping("/book-return")
    @OperationLog(content = "归还图书，借书人：${borrowBy}， 图书的国际标准书号：${isbn}")
    public R returnBook(String isbn, String borrowBy) {
        service.returnBook(isbn, borrowBy);
        return R.ok();
    }

    /**
     * <p>
     * 借阅记录查询
     * </p>
     * @param isbn 图书的国际标准书号
     * @param bookName 图书名称
     * @param startBorrowTime 起始借书时间 (yyyy-MM-dd)
     * @param endBorrowTime 结束借书时间 (yyyy-MM-dd)
     * @param startReturnTime 起始还书时间 (yyyy-MM-dd)
     * @param endReturnTime 结束还书时间 (yyyy-MM-dd)
     * @param pageNum 当前页数
     * @param pageSize 每页记录数量
     * @return returnType returnExplanation
     * @author Caide, Wang (wangcaide@outlook.com)
     * @date 2022-10-15 15:06:55
     */
//    @ApiOperation(value = "借阅记录查询")
//    @GetMapping("/book-borrow")
//    public R<PageInfo<BorrowLogVo>> borrowLog(
//            @ApiParam(name = "isbn", value = "图书的国际标准书号")String isbn,
//            @ApiParam(name = "bookName", value = "图书名称")String bookName,
//            @ApiParam(name = "startBorrowTime", value = "起始借书时间(yyyy-MM-dd)")String startBorrowTime,
//            @ApiParam(name = "endBorrowTime", value = "结束借书时间(yyyy-MM-dd)")String endBorrowTime,
//            @ApiParam(name = "startReturnTime", value = "起始还书时间(yyyy-MM-dd)")String startReturnTime,
//            @ApiParam(name = "endReturnTime", value = "结束还书时间(yyyy-MM-dd)")String endReturnTime,
//            @ApiParam(name = "pageNum", value = "当前页数")int pageNum,
//            @ApiParam(name = "pageSize", value = "每页记录数量")int pageSize) {
//        return null;
//    }

}
