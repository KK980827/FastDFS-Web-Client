package com.bestbigkk.resource.web;

import com.bestbigkk.resource.entity.RespBO;
import com.bestbigkk.resource.service.DfsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @author: xugongkai
 * @data: 2019-12-19 15:05:12
 * @describe: 控制器
 **/
@Validated
@Controller
public class Index {
    private final DfsService dfsService;

    public Index(DfsService dfsService) {
        this.dfsService = dfsService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping({"/40x", "/4xx", "/404"})
    public String page4xx() {
        return "/error/4xx";
    }

    @GetMapping({"/50x", "/5xx", "/500"})
    public String page5xx() {
        return "/error/5xx";
    }

    @ResponseBody
    @PostMapping("/upload")
    public RespBO upload(@NotNull(message = "必须指定上传文件") MultipartFile file,
                         @NotNull(message = "必须提供令牌") String token, HttpServletResponse response) {
        RespBO respBO = dfsService.upload(file, token);
        if(!respBO.getStatus()){
            response.setStatus(401);
        }
        return respBO;
    }
}
