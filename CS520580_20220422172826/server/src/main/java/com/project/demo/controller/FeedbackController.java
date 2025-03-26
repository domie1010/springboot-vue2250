package com.project.demo.controller;

import com.project.demo.entity.Feedback;
import com.project.demo.service.FeedbackService;
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
 *意见反馈：(Feedback)表控制层
 *
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController<Feedback,FeedbackService> {

    /**
     *意见反馈对象
     */
    @Autowired
    public FeedbackController(FeedbackService service) {
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
