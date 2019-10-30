package com.yonyou.cap.preservicegs.controller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *发送文件工具类
 */
public class SendFileUtils {

    public static void main(String[] args) {
        File file = new File("G:/tensquare-master.zip");
        sendFile("http://localhost:8080/cap_aco_gs/api/receiveFile",file);
    }

    /**
     * 发送日志文件方法
     * @param url 接收文件接口连接
     * @param file  发送文件
     */
    public static void sendFile(String url, File file) {
        if (file.exists()) {
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod(url);

            try {
                // FilePart：用来上传文件的类,file即要上传的文件
                FilePart fp = new FilePart("file", file);
                Part[] parts = { fp };

                // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
                MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
                postMethod.setRequestEntity(mre);
                // 由于要上传的文件可能比较大 , 因此在此设置最大的连接超时时间
                client.getHttpConnectionManager().getParams() .setConnectionTimeout(50000);

                int status = client.executeMethod(postMethod);
                if (status == HttpStatus.SC_OK) {

                    // 获取返回数据
                    InputStream inputStream = postMethod.getResponseBodyAsStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer stringBuffer = new StringBuffer();
                    String str = "";
                    // 遍历返回数据
                    while ((str = br.readLine()) != null) {
                        stringBuffer.append(str);
                    }

                    String body = stringBuffer.toString();

                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                // 释放连接
                postMethod.releaseConnection();
            }
        }
    }

}
