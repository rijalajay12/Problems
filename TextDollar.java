
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
//import org.junit.Assert;
//import org.junit.Test;

/**
 * I have added the test cases as well but commented them out as it would fail if you don't have junit jar.
 */
public class TextDollar {
    
    public static void main(String[] args) {
        while(true) {
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                System.out.println(getTextValue(in.next()));
            } else
                break;
        }
    }

    private static String getTextValue(String input) {
        if (null == input || input.isEmpty() || Integer.parseInt(input.trim()) == 0) {
            return "";
        }
        final int BILLION = 1_000_000_000;
        if (Integer.parseInt(input) >= BILLION ) {
            throw new UnsupportedOperationException("Only numbers less than billion is supported");
        }
        // there can be multiple validations like whether string is integer or not; skipping them as they are trivial
        // and probably not something interviewer is trying to test
        Map<Integer, List<String>> numberMap = new HashMap<>();
        numberMap.put(0, getArrayList( "", ""));
        numberMap.put(1, getArrayList("One", "Ten"));
        numberMap.put(2, getArrayList("Two", "Twenty"));
        numberMap.put(3, getArrayList("Three", "Thirty"));
        numberMap.put(4, getArrayList("Four", "Forty"));
        numberMap.put(5, getArrayList("Five", "Fifty"));
        numberMap.put(6, getArrayList("Six", "Sixty"));
        numberMap.put(7, getArrayList("Seven", "Seventy"));
        numberMap.put(8, getArrayList("Eight", "Eighty"));
        numberMap.put(9, getArrayList("Nine", "Ninety"));

        List<String> tens = getArrayList("Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen");

        List<String> multiples = getArrayList(  "", "Thousand", "Million");

        int length = input.length();
        String number = length % 3 == 1 ? "00" + input : length % 3 == 2 ? "0" + input : input;
        // groups would be 1 or 2 or 3
        int groups = number.length() / 3;
        String[] numArray = new String[groups];
        int index = 0;
        while (groups > 0) {
            String inner = number.substring(3*index, 3*index +3);
            numArray[index++] = inner;
            groups --;
        }

        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < numArray.length; i++) {
            String weight = multiples.get(numArray.length - i - 1);
            StringBuilder sb = new StringBuilder();
            char[] numbers = numArray[i].toCharArray();
            for (int j = 0; j < 3; j++) {
                boolean isHundred = false;
                boolean isTenth = false;
                boolean isTens = false;
                int val = numbers[j] - '0';
                if (val == 0) {
                    continue;
                }
                if (j == 0) {
                    isHundred = true;
                }
                if (j == 1) {
                    isTenth = true;
                    if (val == 1) {
                        isTens = true;
                    }
                }
                String text = numberMap.get(val).get(0);

                if (isTens) {
                    text = tens.get(numbers[2] - '0');
                } else if (isTenth) {
                    text = numberMap.get(val).get(1);
                }

                sb.append(text);

                if (isHundred) {
                    sb.append("Hundred");
                }

                if (isTens) {
                    break;
                }
            }
            if (sb.length() != 0) {
                sb.append(weight);
            }
            resultBuilder.append(sb);
        }
        if (resultBuilder.length() != 0) {
            resultBuilder.append("Dollars");
        }

        return resultBuilder.toString();
    }

    private static <T>List<T> getArrayList(T... items) {
        List<T> list = new ArrayList<>();
        for (T item: items) {
            list.add(item);
        }
        return list;
    }

//    @Test
//    public void testGetTextValue() {
//        Assert.assertEquals("TwelveThousandThreeHundredTwentyDollars", getTextValue("12320"));
//        Assert.assertEquals("FiveMillionThreeHundredThreeThousandFourHundredFiftyFiveDollars",
//            getTextValue("5303455"));
//        Assert.assertEquals("OneHundredTwentyThreeDollars", getTextValue("0123"));
//        Assert.assertEquals("", getTextValue("0000"));
//        Assert.assertEquals("ThreeDollars", getTextValue("3"));
//        Assert.assertEquals("FourHundredSixtySixDollars", getTextValue("466"));
//        Assert.assertEquals("OneThousandTwoHundredThirtyFourDollars", getTextValue("1234"));
//        Assert.assertEquals("TenDollars", getTextValue("10"));
//        Assert.assertEquals("TwentyOneDollars", getTextValue("21"));
//		  Assert.assertEquals("", getTextValue("-21"));
//    }
}
