package example.cdx.com.utilslibrary.utils;

import android.graphics.Color;
import android.text.TextUtils;

/**
 * 颜色的工具类
 */
public class ColorUtils {

    /**
     * 颜色转换(将#FF000000转换成颜色)
     */
    public static int string2color(String color) {
        return string2color(color, "#FF000000");
    }

    /**
     * 颜色转换(将#FF000000转换成颜色)
     */
    public static int string2color(String color, String defult) {
        if (TextUtils.isEmpty(color) || "null".equals(color)) {
            color = defult;
        }
        if (color.startsWith("#")) {
            return Color.parseColor(color);
        }

        if (color.length() == 6) {
            return Color.parseColor("#FF" + color);
        }

        if (color.length() == 8) {
            return Color.parseColor("#" + color);
        }
        return Color.parseColor("#FFFF0000");

    }
}
