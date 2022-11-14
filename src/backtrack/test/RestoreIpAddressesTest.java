package backtrack.test;

import backtrack.algorithm.RestoreIpAddresses;

import java.util.List;

public class RestoreIpAddressesTest {
    public static void main(String[] args) {
        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();
        String s = "25525511135";
        s = "0000";
        s = "101023";
        List<String> strings = restoreIpAddresses.restoreIpAddresses(s);
        for (String ss:
             strings) {
            System.out.println(ss);
        }
    }
}
