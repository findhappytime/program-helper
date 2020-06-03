package fun.findhappytime.helperlombokprocesser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：zhangshu09
 * @date ：Created in 2020-06-02 16:51
 * @description：
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Getter {
}