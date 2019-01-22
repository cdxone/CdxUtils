package example.cdx.com.utilslibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.UUID;


/**
 * Bitmap相关的工具 类
 */
public class BitmapUtils {

    private static final String SD_PATH = "/sdcard/dskqxt/pic/";
    private static final String IN_PATH = "/dskqxt/pic/";

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    /**
     * 图片(Bitmap)转成Base64格式的string
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {
        //1、内存输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //2、将bitmap转换到内存输出流中。
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        //3、将内存输出流转换为字节数组
        byte[] appicon = baos.toByteArray();
        //4、将字节数组转换为Base64形式的字符串
        return Base64.encodeToString(appicon, Base64.DEFAULT);
    }

    /**
     * Base64编码的图片字符串转成bitmap
     * @param st base64形式的图片字符串
     */
    public static Bitmap stringToBitmap(String st) {
        try {
            //1、将Base64形式的图片字符串转换为字节数组
            byte[] bitmapArray = Base64.decode(st, Base64.DEFAULT);
            //2、将字节数组转换为Bitmap
            Bitmap bitmap =  BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Base64格式的字符串转成bitmap
     * @param st Base64格式的字符串
     */
    public static Bitmap stringToBitmapNoWrap(String st) {
        try {
            //1、Base64格式的字符串转成字节数组
            byte[] bitmapArray = Base64.decode(st, Base64.NO_WRAP);
            //2、将字节数组转换为Bitmap
            Bitmap bitmap =  BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 计算缩放比
     *
     * @param bitWidth  当前图片宽度
     * @param bitHeight 当前图片高度
     */
    private static int getRatioSize(int bitWidth, int bitHeight) {
        //1、定义图片的一个规范
        int imageHeight = 2448;
        int imageWidth = 1080;
        //2、计算缩放比
        // 缩放比
        int ratio = 1;
        // 缩放比,由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        if (bitWidth > bitHeight && bitWidth > imageWidth) {
            // 如果图片宽度比高度大,以宽度为基准
            ratio = bitWidth / imageWidth;
        } else if (bitWidth < bitHeight && bitHeight > imageHeight) {
            // 如果图片高度比宽度大，以高度为基准
            ratio = bitHeight / imageHeight;
        }
        // 3、对缩放比矫正
        if (ratio <= 0){
            ratio = 1;
        }
        return ratio;
    }

}
