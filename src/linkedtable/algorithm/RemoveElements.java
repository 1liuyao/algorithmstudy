package linkedtable.algorithm;
/*
    【203 删除链表结点】给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
    【用例1】
        输入：head = [1,2,6,3,4,5,6], val = 6
        输出：[1,2,3,4,5]
    【用例2】
        输入：head = [], val = 1
        输出：[]
    【用例3】
        输入：head = [7,7,7,7], val = 7
        输出：[]
    ========================================================================
    【解决思路】：head初始时指向链表中的第一个元素
        【无虚拟头结点】如果要删除的结点是head所指向结点，直接移动head指针即可；否则，需要记录结点前驱来删除结点
        【构建虚拟头节点】只需要记录结点前驱来删除结点
 */

public class RemoveElements {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    // 方式一：不是用虚拟头结点
    public ListNode removeElements(ListNode head, int val) {
      // 步骤1：若链表为空，无需查找删除结点，直接返回空链表
      if (head == null) {
        return null;
      }
      // 情况1：head指向的头结点为要删除的结点
      while (head!=null && head.val == val){

        head = head.next;
      }
      // 情况2：head指向的头结点不是要删除的结点
      // 记录要删除结点的前驱
      // 注意：head必须指向第一个结点，是不能随便移动的，因为这是链表访问的入口，如果任意移动会导致链表元素丢失
      ListNode pre = head;
      while (pre != null && pre.next != null ){
        if (pre.next.val == val)
          pre.next = pre.next.next;
        else
          pre = pre.next;
      }
      return head;
    }

    //方式二：采用虚拟头节点
    public ListNode removeElements1(ListNode head, int val) {
      // 构造虚拟头节点
      ListNode dummyHead = new ListNode(-1,head);
      // 步骤1：若链表为空，无需查找删除结点，直接返回空链表
      if (head == null) {
        return null;
      }
      // 步骤2：遍历链表，删除值和val相等的结点
      // 记录待删结点前驱,同样的要保存头结点，不然没办法返回链表的第一个元素
      ListNode pre = dummyHead;
      while (pre != null && pre.next != null){
        if (pre.next.val == val) {
          pre.next = pre.next.next;
        }else {
          pre = pre.next;
        }
      }
      return dummyHead.next;
    }

  public ListNode getInstance(int val, ListNode next) {
    ListNode listNode = new ListNode(val, next);
    return listNode;
  }
}
