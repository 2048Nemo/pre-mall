package top.rabbitbyte.serviceutil.filter;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.comon.utils.filter
 * @Author: nemo2048
 * @CreateTime: 2024-09-17  19:50
 * @Description: TODO
 * @Version: 1.0
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SQLFilter {

    private static final Set<String> UNSAFE_WORDS = new HashSet<>(Arrays.asList(
            "select", "update", "delete", "insert", "drop", "alter", "exec", "execute", "union",
            "create", "truncate", "declare", "into", "from", "where", "and", "or"
    ));

    private static final Set<Character> UNSAFE_CHARACTERS = new HashSet<>(Arrays.asList(
            ';', '-', '*', '(', ')', '=', '+', '-', '"', '\''
    ));

    /**
     * 检查输入字符串是否包含潜在的 SQL 注入关键词或特殊字符。
     *
     * @param input 输入字符串
     * @return 过滤后的字符串
     * @throws IllegalArgumentException 如果检测到潜在的 SQL 注入风险
     */
    public static String sqlInject(String input) {
        if (input == null) {
            return null;
        }

        // 检查是否包含不安全的关键词
        for (String word : UNSAFE_WORDS) {
            if (input.toLowerCase().contains(word)) {
                throw new IllegalArgumentException("输入包含非法关键词，可能存在 SQL 注入风险！");
            }
        }

        // 检查是否包含不安全的字符
        for (char c : input.toCharArray()) {
            if (UNSAFE_CHARACTERS.contains(c)) {
                throw new IllegalArgumentException("输入包含非法字符，可能存在 SQL 注入风险！");
            }
        }

        // 返回过滤后的字符串
        return input;
    }
}
