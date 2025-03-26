package com.project.demo.controller;

import com.project.demo.entity.VideoSharing;
import com.project.demo.service.VideoSharingService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *视频分享：(VideoSharing)表控制层
 *
 */
@RestController
@RequestMapping("/video_sharing")
public class VideoSharingController extends BaseController<VideoSharing,VideoSharingService> {

    /**
     *视频分享对象
     */
    @Autowired
    public VideoSharingController(VideoSharingService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
