package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *注册用户：(RegisteredUser)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RegisteredUser")
public class RegisteredUser implements Serializable {

    //RegisteredUser编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registered_user_id")
    private Integer registered_user_id;
    // 用户名
    @Basic
    private String user_name;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
