package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *意见反馈：(Feedback)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "Feedback")
public class Feedback implements Serializable {

    //Feedback编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedback_id;
    // 主题
    @Basic
    private String theme;
    // 相关凭证
    @Basic
    private String relevant_vouchers;
    // 用户名
    @Basic
    private Integer user_name;
    // 联系电话
    @Basic
    private String contact_number;
    // 内容
    @Basic
    private String content;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
