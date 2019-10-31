package com.yonyou.cap.preservicegs.listener.file;

import com.yonyou.cap.preservicegs.util.Constants;
import com.yonyou.cap.preservicegs.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.nio.file.Files;


@WebListener
public class ContextListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //监听业务平台的数据包
        listenTransfer();
        //监听光闸传入的数据包
        listenFgap();
    }

    public void listenTransfer(){
        File transfer = new File(Constants.FTP_TRANSFER);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new WatchDir(transfer, true, new FileActionCallback() {
                        @Override
                        public void create(File file) {
                            try {
                                logger.info("新建文件{}",file.getAbsolutePath());
                                checkFileWritingOn(file,1);
                            }catch (Exception e){
                                logger.error("文件MD5校验异常:", e);
                            }
                        }
                        @Override
                        public void delete(File file) {
                            System.out.println("文件已删除" + file.getAbsolutePath());
                        }
                        @Override
                        public void modify(File file) {
                            System.out.println("文件已修改" + file.getAbsolutePath());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        logger.info("正在监视文件夹:{}", transfer.getAbsolutePath());
    }

    public void listenFgap(){
        File fgap = new File(Constants.FTP_FGAP.concat("/").concat(Constants.IN));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new WatchDir(fgap, true, new FileActionCallback() {
                        @Override
                        public void create(File file) {
                            try {
                                logger.info("新建文件{}",file.getAbsolutePath());
                                checkFileWritingOn(file,2);
                            }catch (Exception e){
                                logger.error("文件MD5校验异常:", e);
                            }
                        }
                        @Override
                        public void delete(File file) {
                            System.out.println("文件已删除" + file.getAbsolutePath());
                        }
                        @Override
                        public void modify(File file) {
                            System.out.println("文件已修改" + file.getAbsolutePath());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        logger.info("正在监视文件夹:{}", fgap.getAbsolutePath());
    }

    public void checkFileWritingOn(File file,int type) throws Exception{
        long oldLen = 0;
        long newLen = 0;
        while(true){
            newLen = file.length();
            if ((newLen - oldLen) > 0) {
                oldLen = newLen;
                Thread.sleep(5000);//5s内文件大小未发生变化,认为文件已传输完毕
            } else {
                logger.info("文件传输完成,总大小为{}",newLen);
                String fileMD5Str = MD5Util.getFileMD5String(file);
                String fileName = file.getName();
                String subFileName = fileName.substring(0,fileName.lastIndexOf("."));
                if(fileMD5Str.equals(subFileName)){
                    if(type==1){
                        logger.info("{}文件MD5校验通过,开始复制到光闸传输out目录",fileName);
                        copyFile(file.getAbsolutePath(),Constants.FTP_FGAP.concat("/").concat(Constants.OUT).concat("/").concat(fileName));
                    }else if(type==2){
                        logger.info("光闸摆渡传入in目录...");
                    }
                }else{
                    logger.info("{}文件MD5校验失败",fileName);
                }
                break;
            }
        }
    }

    public void copyFile(String oldPath, String newPath) {
        try {
            File oldpaths = new File(oldPath);
            File newpaths = new File(newPath);
             if (!newpaths.exists()) {
                Files.copy(oldpaths.toPath(), newpaths.toPath());
             } else {
                newpaths.delete();
                Files.copy(oldpaths.toPath(), newpaths.toPath());
            }
            logger.info("文件已复制到光闸out同步目录中");
        }catch (Exception e) {
            logger.error("复制文件操作出错", e);
        }
    }
}
