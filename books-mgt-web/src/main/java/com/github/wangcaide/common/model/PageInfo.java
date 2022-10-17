package com.github.wangcaide.common.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 分页列表封装对象
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 14:47:07
 */
@Data
public class PageInfo<T> implements Serializable {

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 分页总数
     */
    private long totalPage;

    /**
     * 每页的记录尺寸
     */
    private long pageSize = 10;

    /**
     * 当前的页号,从0开始
     */
    private long pageNum;

    /**
     * 当前分页数据
     */
    private List<T> data;

    public static <T, R> PageInfo<R> from(IPage<T> page, Function<T, R> convert) {
        PageInfo<R> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setTotalPage(page.getPages());
        pageInfo.setTotalCount(page.getTotal());
        pageInfo.setData(page.getRecords().stream().map(t -> convert.apply(t)).collect(Collectors.toList()));
        return pageInfo;
    }

}
