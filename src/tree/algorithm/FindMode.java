package tree.algorithm;

import java.util.ArrayList;
import java.util.List;

/*
    【501 二叉搜索树中的众数】一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
                          如果树中有不止一个众数，可以按 任意顺序 返回。
                          假定 BST 满足如下定义：
                                            结点左子树中所含节点的值 小于等于 当前节点的值
                                            结点右子树中所含节点的值 大于等于 当前节点的值
                                            左子树和右子树都是二叉搜索树
    【示例 1】              1
                              2
                           2
            输入：root = [1,null,2,2]
            输出：[2]
    【示例 2】
            输入：root = [0]
            输出：[0]
    ==============================================================================================================
    【解题思路】双指针法：中序遍历形成递增序列
                    1                                            1                          cur --> 1
                  /    \                                       /    \                   count = 1 /    \
                2        3                       cur -->     2        3                 pre -->  2      3
              /  \      /  \                     count = 2 /  \      /  \                       /  \   /  \
     cur --> 2  null   3    null                 pre -->  2  null   3    null                 2  null 3    null
  count = 1 / \       /  \                               / \       /  \                      / \     /  \
 pre -->  null null null null                         null null null null                  null null null null
                maxCount = 1                               maxCount = 2                         maxCount = 2
                result = [2]                               result = [2]                         result = [2]

                    1 <-- pre                                    1
                  /    \                                       /    \
                2        3  <-- cur                          2        3 <-- pre
              /  \      /  \ count = 1                     /  \      /  \   count = 2
            2  null   3    null                          2   null   3(cur)null
           / \       /  \                               / \       /  \
         null null null null                         null null null null
                maxCount = 2                               maxCount = 2
                result = [2]                               result = [2 3]

 1、cur指向当前遍历结点，pre指向cur中序遍历序列的前驱，count 记录每一个结点出现的次数，result记录众数集合（众数不止一个），maxCount表示最大频率
 2、初始状态 pre = null，代表 cur 所指元素出现个数是 1
 3、如果 pre != null，如果 pre.val = cur.val ，则 count ++;
 4、如果 pre != null，如果 pre.val != cur.val，则 count 归 1，重新计算cur所指元素出现的个数
 5、如何使得maxCount记录元素出现的最大频率，如何使得 result 保存出现次数最多的元素
    更新策略：在遍历过程中如果出现等于maxCount，就暂存 元素到 result
            在遍历过程中如果出现比当前 maxCount 大，就更新 maxCount，更新result保存出现频率更大的元素
 */
public class FindMode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode pre = null;
    int count = 0;
    int maxCount = 0;
    List<Integer> result = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        TreeNode cur = root;
        backTracking(cur);
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void backTracking(TreeNode cur) {
        if (cur == null)
            return;
        // 左
        backTracking(cur.left);
        // 中
        // count计算
        if (pre == null)
            count = 1;
        else if (pre.val == cur.val)
            count++;
        else
            count = 1;
        // 众数结果集更新，相等就保存结果，这个一定要有，否则只有一个结点[0],收集不到，同时也可以收集多个众数
        // 这个if不能放在最下面，否则符合要求的众数会被保存两次
        if (count == maxCount)
            result.add(cur.val);
        if (count > maxCount) {
            maxCount = count;
            result.clear();
            result.add(cur.val);
        }
        pre = cur;
        // 右
        backTracking(cur.right);
    }
}
