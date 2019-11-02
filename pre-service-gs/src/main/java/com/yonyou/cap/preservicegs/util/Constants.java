//*********************************************************************
// 系统名称：cap-base
// 版本信息：Copyright(C)2016-2020 UFIDA Software Co. Ltd. All rights reserved.
// 作        者：徐真
// 手       机：18611123594
// SVN版本号                                             日   期                作     者              变更记录
// Constants-001       2016/07/24    徐真　                 新建
//*********************************************************************

package com.yonyou.cap.preservicegs.util;

/**
 *系统常量工具类
 */
public interface Constants {

	//业务平台ftp数据资源传入目录
	String FTP_TRANSFER="G:/ftpservre/transfer";

	//光闸数据资源传入目录
	String FTP_FGAP="G:/ftpservre/fgap";

	//光闸数据资源子传出目录
	 String OUT="out";

	//光闸数据资源子传入目录
     String IN="in";

	//FTP ip
    String FTP_HOST="192.168.0.178";

	//FTP name
    String FTP_USER="gaocj";

	//FTP password
	 String FTP_PW="123456";

	 class SocketServer{
		 //  服务端IP
		 public  static final String SERVER_IP = "127.0.0.1";
		 //  服务端端口
		 public  static final int SERVER_PORT = 10001;
		 //  开启配置
		 public  static final  String IS_START_SERVER = "instart";
	 }
}
