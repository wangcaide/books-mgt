package com.github.wangcaide.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wangcaide.common.exception.WebException;
import com.github.wangcaide.entity.AccountEntity;
import com.github.wangcaide.entity.BookEntity;
import com.github.wangcaide.entity.BorrowLogEntity;
import com.github.wangcaide.mapper.AccountMapper;
import com.github.wangcaide.mapper.BookMapper;
import com.github.wangcaide.mapper.BorrowLogMapper;
import com.github.wangcaide.util.LoginUserUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * borrow 层
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-17 15:56:24
 */
@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowLogMapper borrowLogMapper;
    private final BookMapper bookMapper;
    private final AccountMapper accountMapper;

    @Transactional
    public void borrowBook(String isbn, String borrowBy, String expirationTime) {
        AccountEntity borrowByAccount = accountMapper
                .selectOne(new QueryWrapper<AccountEntity>().eq("username", borrowBy));
        if (Objects.isNull(borrowByAccount)) {
            throw new WebException(String.format("借书人不存在[s%]", borrowBy));
        }
        BookEntity book = bookMapper.lockByIsbn(isbn);
        if (Objects.isNull(book)) {
            throw new WebException(String.format("根据图书的国际标准书号找不到图书记录[s%]", isbn));
        }
        if (book.getTotalNum() == book.getBorrowedNum()) {
            throw new WebException("图书已被借空");
        }
        Date current = new Date();
        String currentUsername = LoginUserUtil.getCurrentLoginUsername();

        book.setBorrowedNum(book.getBorrowedNum()+1);
        book.setUpdatedBy(currentUsername);
        book.setUpdatedTime(current);
        bookMapper.updateById(book);

        BorrowLogEntity log = new BorrowLogEntity();
        log.setBorrowBy(borrowBy);
        log.setBorrowTime(current);
        log.setBookName(book.getBookName());
        log.setBookIsbn(isbn);
        log.setCreatedBy(currentUsername);
        log.setCreatedTime(current);
        log.setUpdatedBy(currentUsername);
        log.setUpdatedTime(current);
        try {
            log.setExpirationTime(DateUtils.parseDate(expirationTime, "yyyy-MM-dd"));
        } catch (ParseException e) {
            throw new WebException(String.format("日期格式异常[s%]", expirationTime));
        }
        borrowLogMapper.insert(log);
    }

    @Transactional
    public void returnBook(String isbn, String borrowBy) {
        AccountEntity borrowByAccount = accountMapper
                .selectOne(new QueryWrapper<AccountEntity>().eq("username", borrowBy));
        if (Objects.isNull(borrowByAccount)) {
            throw new WebException(String.format("借书人不存在[s%]", borrowBy));
        }
        BookEntity book = bookMapper.lockByIsbn(isbn);
        if (Objects.isNull(book)) {
            throw new WebException(String.format("根据图书的国际标准书号找不到图书记录[s%]", isbn));
        }
        if (book.getTotalNum() == book.getBorrowedNum()) {
            throw new WebException("图书没被借过");
        }
        Date current = new Date();
        String currentUsername = LoginUserUtil.getCurrentLoginUsername();

        book.setBorrowedNum(book.getBorrowedNum()-1);
        book.setUpdatedTime(current);
        book.setUpdatedBy(currentUsername);
        bookMapper.updateById(book);

        BorrowLogEntity log = borrowLogMapper.selectOne(new QueryWrapper<BorrowLogEntity>()
                .eq("BORROW_BY", borrowBy)
                .eq("BOOK_ISBN", isbn)
                .isNull("RETURN_TIME")
        );
        if (Objects.isNull(log)) {
            throw new WebException("没找到借书记录");
        }
        borrowLogMapper.lockById(log.getId());
        log.setReturnTime(current);
        log.setUpdatedTime(current);
        log.setUpdatedBy(currentUsername);
        borrowLogMapper.updateById(log);
    }


}
