package com.yonyou.cap.preservicegs.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;

/**
 * 文件接收端控制器
 */
@Controller
@RequestMapping("api")
public class ReceiveFile {


	/**
	 * 接收文件方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(consumes = "multipart/form-data",value ="receiveFile",method = RequestMethod.POST)
	@ResponseBody
	public  void receive(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		boolean success = false;

		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)/* && mach.isPresent()*/) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					String originalFilename = file.getOriginalFilename();
					// 定义绝对路径
					String localPath = "G:/test/";
					File path = new File(localPath);
					// 文件夹不存在 则创建文件夹
					if (!path.exists()) {
						path.mkdirs();
					}
					String filepath = localPath +File.separator+ file.getOriginalFilename();
					File file1 = new File(filepath);
					if(file1.exists()){
						file1.delete();
					}
					// 文件数据存储起来
					file.transferTo(file1);
					success = true;
				}
			}

		}
		jsonObject.put("success",success);
		response.getWriter().println(jsonObject.toString());
	}

}