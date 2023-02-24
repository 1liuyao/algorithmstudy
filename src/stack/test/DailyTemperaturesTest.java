package stack.test;

import stack.algorithm.DailyTemperatures;

public class DailyTemperaturesTest {
    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] result = dailyTemperatures.dailyTemperatures(temperatures);
        for (int i :
                result) {
            System.out.println(i);
        }
    }
}
