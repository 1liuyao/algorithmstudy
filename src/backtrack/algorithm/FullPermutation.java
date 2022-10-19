package backtrack.algorithm;

public class FullPermutation {

    public static void main(String[] args) {
        char[] str= {'1','2','3'};
        int k=2;
        int n=3;
        perm2(str, k, n);
    }
    public static void swap(char[] str, int i, int k){
        char temp;
        temp = str[k];
        str[k] = str[i];
        str[i] = temp;
    }
    public static void perm2(char[] str, int k, int n) {
        int i, j;
        if (k == 0) {
            for (j = 0; j <= n - 1; ++j)
                System.out.print(str[j]+" ");
            System.out.println();
        } else {
            for (i = 0; i <= k; ++i) {
                swap(str,i,k);
                perm2(str, k - 1, n);
                swap(str,i,k);
            }
        }
    }
}
