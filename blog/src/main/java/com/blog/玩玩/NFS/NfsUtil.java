package com.blog.玩玩.NFS;

import com.emc.ecs.nfsclient.nfs.io.Nfs3File;
import com.emc.ecs.nfsclient.nfs.io.NfsFileInputStream;
import com.emc.ecs.nfsclient.nfs.io.NfsFileOutputStream;
import com.emc.ecs.nfsclient.nfs.nfs3.Nfs3;
import com.emc.ecs.nfsclient.rpc.CredentialUnix;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @描述 NFS工具类
 * @参考文档 https://www.cnblogs.com/yshyee/p/9520181.html
 * @参考文档 https://blog.csdn.net/ZYQ_1004/article/details/104947117
 * @参考文档 https://blog.csdn.net/fromfire2/article/details/123695955
 */
public class NfsUtil {
    private static final String NFS_IP = "xxx.xxx.xxx.xxx";
    private static final String NFS_DIR = "/xxx/xxx";//这个结尾有没有/不影响,可以填入子路径
    private static Logger logger = LoggerFactory.getLogger(NfsUtil.class);

    static Nfs3 client;

    static {
        try {
            client = new Nfs3(NFS_IP, NFS_DIR, new CredentialUnix(), 3);
        } catch (Exception e) {
            logger.info(e.getMessage(),e);
            e.printStackTrace();
        }
    }

    public static Nfs3File getFile(String path) {
        try {
            if (!path.startsWith("/"))
                path = "/" + path;//无论NFS_DIR末尾带不带/,这里开头必须/,否者堆栈溢出
            return client.newFile(path);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return null;
        }
    }

    public static List<Nfs3File> getRootFiles() throws IOException {
        return getFile("/").listFiles();
    }

    public static InputStream read(Nfs3File file) throws IOException {
        return new NfsFileInputStream(file);
    }

    public static InputStream read(String path) throws IOException {
        return read(getFile(path));
    }

    public static String readText(String path) throws IOException {
        return BinaryUtil.readText(read(path));
    }

    public static void upload(String path, byte[] data) throws IOException {
        var file = getFile(path);
        BinaryUtil.write(new NfsFileOutputStream(file), data);
    }

    public static void upload(String path, InputStream data) throws IOException {
        upload(path, BinaryUtil.getBytes(data));
    }

    public static void upload(String path, String text) throws IOException {
        upload(path, BinaryUtil.getBytes(text));
    }

    public static void test() throws IOException {
        //var是一个关键字，用于声明一个没有指定类型的变量
        //需要注意的是，var关键字只能用于局部变量，不能用于声明类变量或方法参数。此外，使用var声明的变量必须被初始化才能使用。
        var path = StringUtils.guid(".txt");
        upload(path, "中国智造,惠及全球");
        //var是一个关键字，用于声明一个没有指定类型的变量
        var text = readText(path);
        logger.info(text);
        logger.info(getRootFiles().toString());
    }
}
