package liufeng.algo.str;

/**
 * 字符串匹配算法 brute force
 * 对于字符串S和T
 * 1. 判断S和T的长度是否相等
 * 2. 遍历，对比第一个字符是否相等，相等继续比较下一个；
 * 3. 如果某个元素不相等，index++，从下一个元素继续比较
 */
public class BruteForce extends StrMatch {
    @Override
    public int search(String pat, String txt) {
        int m = pat.length();
        int length = txt.length();
        for (int i = 0; i <= length - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    break;
                }
            }
            // pat 全都匹配了
            if (j == m) {
                return i;
            }
        }
        // txt 中不存在 pat 子串
        return -1;
    }


}
