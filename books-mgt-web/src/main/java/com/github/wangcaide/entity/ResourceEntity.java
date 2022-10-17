package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.github.wangcaide.entity.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Entity Resource 资源(系统/菜单/按键/链接)
 * @author : wangcaide@outlook.com
 * @date : 2022-10-15
 */
@TableName("RESOURCE")
@Data
public class ResourceEntity extends BaseEntity {
    /** ID */
    @TableId(type = IdType.AUTO)
    private Integer resourceId ;
    /** 资源类型，系统/菜单/按键/链接 */
    private String resourceType ;
    /** 资源链接对应的http方法，GET/POST... */
    private String resourceUrlMethod ;
    /** 资源链接 */
    private String resourceUrl ;
    /** 资源名称 */
    private String resourceName ;
    /** 资源描述 */
    private String resourceDescription ;
    /** 状态 */
    private String status ;

}