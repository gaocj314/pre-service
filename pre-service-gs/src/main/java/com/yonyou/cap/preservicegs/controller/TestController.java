package com.yonyou.cap.preservicegs.controller;

import com.yonyou.cap.preservicegs.config.OtherConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private OtherConfig otherConfig;

    @RequestMapping("test")
    public String hello() {
        return otherConfig.getName();
    }
}
