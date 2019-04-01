package com.codimiracle.util.stringifization;

import java.util.*;
import java.util.function.Function;


/**
 * 用于把字符串与集合等数据结构的互转
 * 只能处理每个元素没有包含英文逗号的情况
 *
 * @author Codimiracle
 */
public class StringifizationUtil {
    public static final String SEPARATOR_STRINGIFIED_SET = ",";

    public static String toString(Set<String> set) {
        return toString(set, s -> s);
    }

    public static <T> String toString(Set<T> set, Function<T, String> tToString) {
        StringBuilder builder = new StringBuilder();
        String sep = "";
        for (T s : set) {
            builder.append(tToString.apply(s));
            builder.append(sep);
            sep = SEPARATOR_STRINGIFIED_SET;
        }
        return builder.toString();
    }

    public static Set<String> toSet(String stringifiedSet) {
        return toSet(stringifiedSet, s -> s);
    }

    public static <T> Set<T> toSet(String stringifiedSet, Function<String, T> stringToT) {
        if (Objects.isNull(stringifiedSet)) {
            return Collections.emptySet();
        }
        Set<T> set = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(stringifiedSet, SEPARATOR_STRINGIFIED_SET);
        while (tokenizer.hasMoreTokens()) {
            set.add(stringToT.apply(tokenizer.nextToken()));
        }
        return Collections.unmodifiableSet(set);
    }
}
