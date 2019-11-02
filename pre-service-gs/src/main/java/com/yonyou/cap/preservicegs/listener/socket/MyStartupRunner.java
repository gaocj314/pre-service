package com.yonyou.cap.preservicegs.listener.socket;

import com.yonyou.cap.preservicegs.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=10)//代表启动时加载的顺序
public class MyStartupRunner implements CommandLineRunner{

    private static final Logger LOG  = LoggerFactory.getLogger(MyStartupRunner.class);


    @Override
    public void run(String... args) throws Exception{
        FileUpLoadServer fileUpLoadServer = null;
        if (null == fileUpLoadServer) {
            fileUpLoadServer = new FileUpLoadServer(Constants.SocketServer.SERVER_PORT);
        }
        fileUpLoadServer.load();
        LOG.info("Socket Server init, Port is {}",  Constants.SocketServer.SERVER_PORT);
    }
}
