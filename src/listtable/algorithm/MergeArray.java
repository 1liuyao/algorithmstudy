package listtable.algorithm;

/*
    有两个数组a、b，数组a的元素是升序排列（从小到大），数组b的元素是降序排列（从大到小），请写出算法，将这两个数组合并成升序排列的数组。

    一口井深len米,蜗牛白天爬m米,晚上滑下去n米，几天到井口？实现爬井函数climb输出答案，简单起见len，m，n都是整数。
    例如：一口井深10米，蜗牛白天爬4米，晚上滑下去3米，最终应该是7天到达井口。
 */
public class MergeArray {
    public static void main(String[] args) {
        int[] a = {1, 3, 5, 5};
        int[] b = {6, 4, 2, 0, 0};
        int[] merge = sort(a, b);
//        for (int i = 0; i < merge.length; i++) {
//            System.out.println(merge[i]);
//        }

        int daycost = daycost(11, 4, 3);
        System.out.println(daycost);
        System.out.println("world".contains("wor"));
    }

    private static int[] sort(int[] a, int[] b) {
        int al = a.length;
        int bl = b.length;
        int[] merge = new int[al + bl];
        // 遍历比较
        int i = 0;
        int j = bl - 1;
        int k = 0;
        while (i < al && j >= 0) {
            if (a[i] <= b[j]) {
                merge[k] = a[i];
                k++;
                i++;
            } else {
                merge[k] = b[j];
                k++;
                j--;
            }
        }
        // 若一个数组存在剩余元素，则直接放入数组
        if(j >= 0){
            for (int l = j; l >= 0; l--, k++) {
                merge[k] = b[l];
            }
        }
        if(i < al){
            for (int l = 0; l <= i; l++, k++) {
                merge[k] = a[l];
            }
        }
        return merge;
    }

    public static int daycost (int len, int m, int n) {
        // 计算速度 并 判断合法性
        if (m >= len)
            return 1;
        int v = m - n;
        if (v <= 0)
            return -1;
        // 计算天数
        int result = 0;
        result = (int) Math.ceil((len - m)/1.0/v) + 1;
        return result;
    }

    public int match_str_in_sentence (String s, String x) {
        if ((s == null) || (s.length() == 0))
            return -1;
        // 切割语句
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            if (s1[i].contains(x))
                return i;
        }
        return -1;
    }
}
