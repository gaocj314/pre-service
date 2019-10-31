package com.yonyou.cap.preservicegs.listener.socket;

import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Server端<br><br>
 * 功能说明：服务端监听开启Servlet
 */
@WebServlet(name="socketServerListener")
public class SocketServerListener extends HttpServlet  {

    private static final long serialVersionUID = -999999999999999999L;

    //  初始化启动Socket服务
    @Override
    public void init() throws ServletException {
        super.init();
        for(int i = 0; i < 3; i++){
            if ("instart".equals(FinalVariables.IS_START_SERVER )) {
                open();
                break;
            }
        }
    }

    public void open(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @SuppressWarnings("resource")
            @Override
            public void run() {
                try {
                    FileUpLoadServer fileUpLoadServer = new FileUpLoadServer(FinalVariables.SERVER_PORT);
                    fileUpLoadServer.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 3000);
    }
}
