package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *视频分享：(VideoSharing)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VideoSharing")
public class VideoSharing implements Serializable {

    //VideoSharing编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_sharing_id")
    private Integer video_sharing_id;
    // 标题
    @Basic
    private String title;
    // 封面
    @Basic
    private String cover;
    // 视频
    @Basic
    private String video;
    // 发布日期
    @Basic
    private Timestamp release_date;
    // 用户名
    @Basic
    private Integer user_name;
    // 简介
    @Basic
    private String brief_introduction;
    // 详情
    @Basic
    private String details;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;
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
