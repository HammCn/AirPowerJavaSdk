package cn.hamm.sdk.common.util;

/**
 * <h2>AirDebug</h2>
 *
 * @author Hamm.cn
 */
public class AirDebug {
    /**
     * <h2>调试模式</h2>
     */
    public static boolean debug = true;

    /**
     * <h2>显示日志</h2>
     *
     * @param log 日志
     */
    public static void show(String key, Object log) {
        System.out.println("\n🥵" + key);
        System.out.println(log);
    }
}
