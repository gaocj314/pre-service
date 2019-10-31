package com.yonyou.cap.preservicegs.listener.socket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=10)//代表启动时加载的顺序
public class MyStartupRunner implements CommandLineRunner
{
    SocketServerListener socketServlet=new SocketServerListener();

    @Override
    public void run(String... args) throws Exception{
        socketServlet.init();
    }
}