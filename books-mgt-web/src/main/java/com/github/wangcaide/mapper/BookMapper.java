package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 图书;(BOOK)表数据库访问层
 * @date : 2022-10-15
 */
@Mapper
public interface BookMapper  extends BaseMapper<BookEntity>{

    @Select("select * from BOOK where BOOK_ISBN = #{isbn} for update")
    public BookEntity lockByIsbn(@Param("isbn") String isbn);

}
