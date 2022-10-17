package com.github.wangcaide.controller;

import com.github.wangcaide.common.log.OperationLog;
import com.github.wangcaide.common.log.OperationLogUtils;
import com.github.wangcaide.common.model.PageInfo;
import com.github.wangcaide.service.BookService;
import com.github.wangcaide.vo.BookVo;
import com.github.wangcaide.common.model.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 图书 Controller
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 14:56:15
 */
@Api(tags = "图书相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    /**
     * <p>
     * 图书入库
     * </p>
     * @param book 图书vo类
     * @return R 通用响应结果
     * @author Caide, Wang (wangcaide@outlook.com)
     * @date 2022-10-15 14:38:45
     */
    @ApiOperation(value = "图书入库")
    @PostMapping
    @OperationLog(content = "图书入库，国际标准书号:${isbn}，图书名:${bookName}，总数量:${totalNum}")
    public R add(@Validated @RequestBody BookVo book) {
        OperationLogUtils.addParam("isbn", book.getBookIsbn());
        OperationLogUtils.addParam("bookName", book.getBookName());
        OperationLogUtils.addParam("totalNum", book.getTotalNum());
        service.add(book);
        return R.ok();
    }

    /**
     * <p>
     * 图书报废/遗失 库存总数量减1
     * </p>
     * @param isbn 图书的国际标准书号
     * @return R 通用响应结果
     * @author Caide, Wang (wangcaide@outlook.com)
     * @date 2022-10-15 14:42:38
     */
    @ApiOperation(value = "图书报废/遗失 库存总数量减1")
    @DeleteMapping("/{isbn}")
    @OperationLog(content = "图书报废，国际标准书号:${isbn}")
    public R delete(
    @NotBlank @ApiParam(name = "isbn", value = "图书的国际标准书号", required = true) @PathVariable("isbn") String isbn) {
        service.delete(isbn);
        return R.ok();
    }

    /**
     * <p>
     * 查询图书列表
     * </p>
     * @param isbn 图书的国际标准书号
     * @param bookName 图书名称
     * @param pageNum 当前页数
     * @param pageSize 每页记录数量
     * @return R<PageInfo<BookVo>> 图书分页列表
     * @author Caide, Wang (wangcaide@outlook.com)
     * @date 2022-10-15 14:51:03
     */
    @ApiOperation(value = "查询图书列表")
    @GetMapping
    @OperationLog(content = "查询图书列表，国际标准书号:${isbn}，图书名:${bookName}")
    public R<PageInfo<BookVo>> list(
            @ApiParam(name = "isbn", value = "图书的国际标准书号")String isbn,
            @ApiParam(name = "bookName", value = "图书名称")String bookName,
            @NotNull @ApiParam(name = "pageNum", value = "当前页数，从1开始")Integer pageNum,
            @NotNull @ApiParam(name = "pageSize", value = "每页记录数量")Integer pageSize) {
        return R.ok(service.list(isbn, bookName, pageNum, pageSize));
    }

}
