package com.yonyou.cap.preservicegs.service.impl;

import com.yonyou.cap.preservicegs.service.PushService;
import org.springframework.stereotype.Service;

/**
 * 调用HTTP服务
 */
@Service
public class HttpPushService implements PushService {


    @Override
    public boolean push() {
        return false;
    }
}
