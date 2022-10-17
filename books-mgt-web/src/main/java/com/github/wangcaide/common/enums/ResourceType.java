package com.github.wangcaide.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 资源类型
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-17 10:31:18
 */
@AllArgsConstructor
@Getter
public enum ResourceType {
    URL("url", "链接"),
    MENU("menu", "菜单"),
    BUTTON("button", "按键")
    ;

    private String dbValue;
    private String description;

}
