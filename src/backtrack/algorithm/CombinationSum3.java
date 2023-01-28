package backtrack.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    【216 组合总和 III】找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
                     （1）只使用数字1到9
                     （2）每个数字 最多使用一次
                     （3）返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
    【用例1】
            输入: k = 3, n = 7
            输出: [[1,2,4]]
            解释:
            1 + 2 + 4 = 7
            没有其他符合的组合了。
    【用例2】
            输入: k = 3, n = 9
            输出: [[1,2,6], [1,3,5], [2,3,4]]
            解释:
            1 + 2 + 6 = 9
            1 + 3 + 5 = 9
            2 + 3 + 4 = 9
            没有其他符合的组合了。
    【用例3】
            输入: k = 4, n = 1
            输出: []
            解释: 不存在有效的组合。
            在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
     =========================================================================================
     【解题思路】
     【回溯法】只是在组合题目的基础上增加了求和操作
             1、在确定本层回溯函数操作时，for中不单单是只取数据，而是在取完数据后增加一个求和的操作
             2、终止条件在满足 k 条件的基础上，还需满足和条件，只有同时满足以上两者的叶子结点集合才应该被收集到容器中
             3、剪枝优化：
               （1）横向剪枝：和组合问题类似，针对for [1 2 3 4]，取 4 操作是无意义的，因为根本无法满足结点结合元素个数为 k 的条件
               （2）纵向剪枝：当我们进行深度遍历的时候，从根结点经过 k 步到达叶子结点的过程中，被取出的元素不断累加，
                           当到达某一个结点后出现 大于 n 的情况，那么就没有必要继续向下递归，因为继续递归只会导致和更大，违背题目和条件
        ====================================================================
                                         [1 2 3 4]
                     取1              取2             取3             取4
                  计算sum=0+1      计算sum=0+2     计算sum=0+3     计算sum=0+4
        ====================================================================
                  [2 3 4]            [3 4]           [4]             [空]
              取2    取3   取4      取3    取4         取4
         sum=1+2 sum=1+3 sum=1+4 sum=2+3 sum=2+4   sum=3+4
        ====================================================================
            [1 2]   [1 3]  [1 4]  [2 3]   [2 4]     [3 4]

 */
public class CombinationSum3 {
    // 步骤1：定义深度遍历叶子结点集合收集容器
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        int sumPath = 0;
        int startIndex = 1;

        backTracking(k, n, sumPath, startIndex);

        return result;
    }

    // 步骤2：确定回溯函数的形参和返回值
    //       形参：组合问题，需要给出startIndex，执行取出元素操作后，下层结点从取出结点后遍历
    //            回溯函数执行本层操作时，需要累计path中元素的和 和 目标和进行比较
    //       返回值：已经承载在成员变量定义的容器中，不需要返回
    public void backTracking(int k, int n, int sumPath, int startIndex) {
        // 步骤2：确定回溯函数的终止条件
        // 需要满足 结点个数 k 和 结点和 n 条件
        if (path.size() == k) {
            if (sumPath == n) {
                result.add(new ArrayList<>(path));
                return;
            }
        }
        // 步骤3：确定回溯函数本层操作逻辑
        // 取数 + 累加和
        for (int i = startIndex; i <= 9; i++) {
            path.offerLast(i);
            sumPath = sumPath + i;
            backTracking(k, n, sumPath, i + 1);
            //步骤4 ：回溯撤销操作
            sumPath =sumPath - i;
            path.pollLast();
        }
    }

    // 【剪枝优化】
    public void backTracking1(int k, int n, int sumPath, int startIndex) {
        // 纵向剪枝优化：从根结点经过 k 步到达叶子结点的过程中，违背和条件，就应该停止遍历
        if (sumPath > n) {
            return;
        }
        if (path.size() == k) {
            if (sumPath == n) {
                result.add(new ArrayList<>(path));
                return;
            }
        }
        // 横向剪枝：在满足结点集合个数达到 k 条件的前提下，缩短 i 最大可以遍历的位置
        for (int i = startIndex; i <= (9 + 1 - (k - path.size())); i++) {
            path.offerLast(i);
            sumPath = sumPath + i;
            backTracking(k, n, sumPath, i + 1);
            //步骤4 ：回溯撤销操作
            sumPath =sumPath - i;
            path.pollLast();
        }
    }
    // 剪枝优化k 换一种写法
    public void backTracking2(int k, int n, int sumPath, int startIndex) {
        // 如果遍历到下一层，发现 不满足 元素个数 k 的要求
        // 纵向剪枝优化：从根结点经过 k 步到达叶子结点的过程中，违背和条件，就应该停止遍历，这样就不用在for中限制k
        if(path.size() > k)
            return;
        if (sumPath > n) {
            return;
        }
        if (path.size() == k) {
            if (sumPath == n) {
                result.add(new ArrayList<>(path));
                return;
            }
        }
        // 横向剪枝：在满足结点集合个数达到 k 条件的前提下，缩短 i 最大可以遍历的位置
        for (int i = startIndex; i <= 9; i++) {
            path.offerLast(i);
            sumPath = sumPath + i;
            backTracking(k, n, sumPath, i + 1);
            //步骤4 ：回溯撤销操作
            sumPath =sumPath - i;
            path.pollLast();
        }
    }
}
