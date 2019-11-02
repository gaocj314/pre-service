package com.yonyou.cap.preservicegs.service.impl;

import com.yonyou.cap.preservicegs.service.DataNoticeService;
import com.yonyou.cap.preservicegs.service.PushService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 *  数据交换通知服务
 */
@Service
public class DataNoticeServiceImpl  implements DataNoticeService {


    @Override
    public boolean xmlNotice() {
        return false;
    }
}
