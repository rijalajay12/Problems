
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//import org.junit.Assert;
//import org.junit.Test;

/**
 * I have added the test cases as well but commented them out as it would fail if you don't have junit jar.
 */
public class MaxRangeSums {

    public static void main(String[] args) {
        String input = "10 7 -3 -10 4 2 8 -2 4 -5 -6";
        String[] inputs = input.split("\\s+");
        List<Integer> stocks = Arrays.stream(inputs).map(Integer::parseInt).collect(Collectors.toList());
        // first item is number of days, so ignoring that, removing 1st item from arraylist is not optimal,
        // but ignoring that for now
        stocks.remove(0);
        System.out.println(getMaxGain(stocks));
    }

    private static int getMaxGain(List<Integer> stocks) {
        int maxGain = 0;
        int totalSoFar = 0;
        for (int stock: stocks) {
            totalSoFar += stock;
            if (totalSoFar >= maxGain) {
                maxGain = totalSoFar;
            }
            if (totalSoFar < 0) {
                totalSoFar = 0;
            }
        }
        return maxGain;
    }

//    @Test
//    public void testGetMaxGain() {
//        List<Integer> input = getArrayList(7, -3, -10, 4, 2, 8, -2, 4, -5, -2);
//        Assert.assertEquals(16, getMaxGain(input));
//
//        List<Integer> input2 = getArrayList(-7, -3);
//        Assert.assertEquals(0, getMaxGain(input2));
//
//        List<Integer> input3 = getArrayList(7, -3, -10, 4, 2);
//        Assert.assertEquals(7, getMaxGain(input3));
//
//        List<Integer> input4 = getArrayList(7, -3, -10, 4, 8);
//        Assert.assertEquals(12, getMaxGain(input4));
//    }

    private <T>List<T> getArrayList(T... numbers) {
        List<T> list = new ArrayList<>();
        for (T num: numbers) {
            list.add(num);
        }
        return list;
    }

}
