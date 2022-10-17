package com.github.wangcaide.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 图书vo
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 15:27:14
 */
@ApiModel(description= "图书")
@Data
@Builder
public class BookVo {

    @Tolerate
    public BookVo() {
    }
    @ApiModelProperty(value = "图书的国际标准书号")
    @NotBlank(message = "图书的国际标准书号不能为空")
    private String bookIsbn;
    @ApiModelProperty(value = "图书名")
    @NotBlank(message = "图书名不能为空")
    private String bookName;
    @ApiModelProperty(value = "出版社")
    @NotBlank(message = "出版社不能为空")
    private String publisher;
    @ApiModelProperty(value = "图书描述")
    private String bookDescription;
    @ApiModelProperty(value = "总库存数量")
    @NotNull(message = "总库存不能为空")
    private Integer totalNum;
    @ApiModelProperty(value = "已借出的数量")
    private Integer borrowedNum;

}
