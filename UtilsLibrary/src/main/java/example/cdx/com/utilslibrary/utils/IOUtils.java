package example.cdx.com.utilslibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 作者：yzh
 * <p>
 * 创建时间：2017/12/13 14:03
 * <p>
 * 描述：io流关闭
 * <p>
 * 修订历史：
 */
public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable... ios) {
        for (Closeable io : ios) {
            if (io != null) {
                try {
                    io.close();
                } catch (IOException e) {
                }
            }
        }
        return true;
    }
}
