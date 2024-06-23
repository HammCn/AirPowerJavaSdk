package cn.hamm.sdk.common.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h1>随机助手类</h1>
 *
 * @author Hamm.cn
 */
public class Random {
    /**
     * <h2>小写字母</h2>
     */
    private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";

    /**
     * <h2>数字</h2>
     */
    private static final String BASE_NUMBER = "0123456789";

    /**
     * <h2>小写字母和数字</h2>
     */
    private static final String BASE_CHAR_NUMBER_LOWER = BASE_CHAR + BASE_NUMBER;

    /**
     * <h2>大写和小写字母</h2>
     */
    private static final String BASE_CHAR_NUMBER = BASE_CHAR.toUpperCase() + BASE_CHAR_NUMBER_LOWER;

    /**
     * <h2>获取32位随机字符串</h2>
     *
     * @return 随机字符串
     */
    public static String randomString() {
        return randomString(32);
    }

    /**
     * <h2>获取指定位数的随机字符串</h2>
     *
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public static String randomString(final int length) {
        return randomString(BASE_CHAR_NUMBER, length);
    }

    /**
     * <h2>获取随机数字的字符串</h2>
     *
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public static String randomNumbers(final int length) {
        return randomString(BASE_NUMBER, length);
    }

    /**
     * <h2>获取指定样本的随机字符串</h2>
     *
     * @param baseString 随机字符选取的样本
     * @param length     字符串的长度
     * @return 随机字符串
     */
    public static String randomString(final String baseString, int length) {
        length = Math.max(length, 1);
        final int baseLength = baseString.length();
        return IntStream.range(0, length)
                .map(i -> randomInt(baseLength))
                .mapToObj(number -> String.valueOf(baseString.charAt(number)))
                .collect(Collectors.joining());
    }

    /**
     * <h2>获取一个随机整数</h2>
     *
     * @return 随机数
     * @see java.util.Random#nextInt()
     */
    public static int randomInt() {
        return getRandom().nextInt();
    }

    /**
     * <h2>获得指定范围内的随机数</h2>
     *
     * @param exclude 排除的数字
     * @return 随机数
     */
    public static int randomInt(final int exclude) {
        return getRandom().nextInt(exclude);
    }

    /**
     * <h2>获得指定范围内的随机数</h2>
     *
     * @param minInclude 最小数（包含）
     * @param maxExclude 最大数（不包含）
     * @return 随机数
     */
    public static int randomInt(final int minInclude, final int maxExclude) {
        return randomInt(minInclude, maxExclude, true, false);
    }

    /**
     * <h2>获得指定范围内的随机数</h2>
     *
     * @param min        最小数
     * @param max        最大数
     * @param includeMin 是否包含最小值
     * @param includeMax 是否包含最大值
     * @return 随机数
     */
    public static int randomInt(int min, int max, final boolean includeMin, final boolean includeMax) {
        if (!includeMin) {
            min++;
        }
        if (includeMax) {
            max++;
        }
        return getRandom().nextInt(min, max);
    }

    /**
     * <h2>获得随机数种子</h2>
     *
     * @return 随机种子
     */
    private static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }
}
