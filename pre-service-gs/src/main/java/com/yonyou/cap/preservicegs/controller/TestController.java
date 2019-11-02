package com.yonyou.cap.preservicegs.controller;

import com.yonyou.cap.preservicegs.config.OtherConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    private static  final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private OtherConfig otherConfig;

    @RequestMapping("test")
    public String hello() {
        LOG.info("hello...");
        return otherConfig.getName();
    }
}
