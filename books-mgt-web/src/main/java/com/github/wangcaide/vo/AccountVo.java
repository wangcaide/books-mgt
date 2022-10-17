package com.github.wangcaide.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 账户Vo
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 15:25:05
 */
@ApiModel(description= "账户")
@Data
@Builder
public class AccountVo implements Serializable {

    @Tolerate
    public AccountVo() {
    }

    @ApiModelProperty(value = "账户id")
    private String accountId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "名字")
    @NotBlank(message = "名字不能为空")
    private String firstName;
    @ApiModelProperty(value = "姓氏")
    @NotBlank(message = "姓氏不能为空")
    private String lastName;

    @ApiModelProperty(value = "证件类型")
    private String idType;
    @ApiModelProperty(value = "证件号")
    private String idValue;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "手机号格式有误")
    private String phoneNumber;


}
