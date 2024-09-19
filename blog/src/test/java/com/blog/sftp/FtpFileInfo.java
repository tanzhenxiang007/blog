package com.blog.sftp;

import java.util.Calendar;

/**
 * @author 丁鹏飞
 * Date: 2017/12/11 14:01
 * Title:FTP 文件对象
 * Describe:
 */
public class FtpFileInfo {
    private String   name;         // 文件名
    private long     size;         // 文件大小
    private Calendar timestamp;    // 时间戳
    private boolean  type;         // 类型

    @Override
    public String toString() {
        return "FtpFileInfo [name=" + name + ", size=" + size + ", timestamp=" + timestamp + ", type=" + type + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
