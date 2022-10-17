package com.github.wangcaide.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wangcaide.common.constant.SysConstant;
import com.github.wangcaide.common.exception.WebException;
import com.github.wangcaide.common.model.PageInfo;
import com.github.wangcaide.entity.BookEntity;
import com.github.wangcaide.mapper.BookMapper;
import com.github.wangcaide.util.LoginUserUtil;
import com.github.wangcaide.vo.BookVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * Book service 层
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-17 15:04:14
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper mapper;

    public void add(BookVo vo) {
        BookEntity book = mapper.selectOne(new QueryWrapper<BookEntity>()
                .eq("BOOK_ISBN", vo.getBookIsbn()));
        if (Objects.nonNull(book)) {
            throw new WebException("该书已经入库，请勿重复入库");
        }
        String username = LoginUserUtil.getCurrentLoginUsername();
        BookEntity entity = new BookEntity();
        BeanUtils.copyProperties(vo, entity);
        entity.setBorrowedNum(0);
        Date current = new Date();
        entity.setCreatedTime(current);
        entity.setCreatedBy(username);
        entity.setUpdatedTime(current);
        entity.setUpdatedBy(username);
        mapper.insert(entity);
    }

    @Transactional
    public void delete(String isbn) {
        BookEntity book = mapper.lockByIsbn(isbn);
        if (Objects.isNull(book)) {
            throw new WebException(String.format("根据图书的国际标准书号找不到图书记录[s%]", isbn));
        }
        if (book.getTotalNum() == book.getBorrowedNum()) {
            throw new WebException("请先将图书做归还操作后再做报废操作");
        }
        book.setTotalNum(book.getTotalNum()-1);

        mapper.updateById(book);
    }

    public PageInfo<BookVo> list(String isbn, String bookName, int pageNum, int pageSize) {
        QueryWrapper query = new QueryWrapper<BookEntity>();
        if (StringUtils.isNoneBlank(isbn)) {
            query.eq("BOOK_ISBN", isbn);
        }
        if (StringUtils.isNoneBlank(bookName)) {
            query.like("BOOK_NAME", bookName);
        }
        IPage<BookEntity> page = mapper.selectPage(new Page<>(pageNum, pageSize), query);
        return PageInfo.from(page, entity -> entity.convertVO(new BookVo()));
    }

}
