package fun.findhappytime.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangshu on 2017/9/14.
 */
public class CheckUtil {
    private CheckUtil() {
        //私有
    }

    /**
     * 空检查 包括空字符串
     */
    @SafeVarargs
    public static <T> boolean checkEmpty(T... objects) {
        if (null == objects) {
            return true;
        }
        for (T object : objects) {
            if (object instanceof String && StringUtils.isEmpty((String) object)) {
                return true;
            }
            if (null == object) {
                return true;
            }
        }
        return false;
    }

    /**
     * 空检查
     */
    @SafeVarargs
    public static <T> boolean checkNull(T... objects) {
        if (null == objects) {
            return true;
        }
        for (T object : objects) {
            if (null == object) {
                return true;
            }
        }
        return false;
    }
}
