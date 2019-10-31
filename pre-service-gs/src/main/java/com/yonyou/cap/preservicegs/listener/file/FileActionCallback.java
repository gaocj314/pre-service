package com.yonyou.cap.preservicegs.listener.file;


import java.io.File;

/**
 * 文件操作的回调方法
 */
public abstract class FileActionCallback {

    public abstract void delete(File file);

    public abstract void modify(File file);

    public abstract void create(File file);
}
