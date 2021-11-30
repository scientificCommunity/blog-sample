package org.baichuan.exmaple.algorithms.interview.huawei;

import java.util.*;

/**
 * @author: tk (rivers.boat.snow@gmail.com)
 * @date: 2021/11/30
 */
public class Main {
    public static void main(String[] args) {
        new Main().test3();
    }


    public void test1() {
        Scanner sr = new Scanner(System.in);

        while (sr.hasNext()) {
            String next = sr.next();
            char[] chars = next.toCharArray();
            boolean leftFlag = false;
            boolean rightFlag = false;
            StringBuilder leftValue = new StringBuilder();
            StringBuilder rightValue = new StringBuilder();
            int leftInt = 0;
            int rightInt = 0;
            int maxResult = 0;
            String result = "(0,0)";

            for (int i = 0; i < chars.length; i++) {
                switch (chars[i]) {
                    case '(':
                        leftFlag = chars[i + 1] != '0';
                        break;
                    case ',':
                        if (!leftFlag) {
                            break;
                        }
                        leftFlag = false;
                        rightFlag = chars[i + 1] != '0';
                        if (rightFlag) {
                            leftInt = Integer.parseInt(leftValue.toString());
                        }
                        break;
                    case ')':
                        if (rightFlag) {
                            rightFlag = false;
                            rightInt = Integer.parseInt(rightValue.toString());
                            if (leftInt > 0 && rightInt > 0) {
                                int tmpMax = leftInt * leftInt + rightInt * rightInt;
                                if (tmpMax > maxResult) {
                                    maxResult = tmpMax;
                                    result = "(" + leftValue + ',' + rightValue + ')';
                                }
                            }
                        }
                        leftValue.delete(0, leftValue.length());
                        rightValue.delete(0, rightValue.length());
                        break;
                    default:
                        if (leftFlag) {
                            leftValue.append(chars[i]);
                        } else if (rightFlag) {
                            rightValue.append(chars[i]);
                        }
                }
            }
            System.out.println(result);
        }

    }

    public void test2() {
        Scanner sr = new Scanner(System.in);
        String str1 = sr.next();
        String str2 = sr.next();
        char[] chars = str1.toCharArray();
        List<String> possibleValues = new ArrayList<>();
        for (char aChar : chars) {

        }
    }

    public void test3() {
        System.out.println(strategy6(new int[]{2, 2, 3, 4, 1}));
    }

    public boolean strategy1(int[] values,List<String> colors) {
        return strategy4(colors) && strategy5(values);
    }

    public boolean strategy2(int[] values) {
        return checkSame(values, 1);
    }


    public boolean strategy3(int[] values) {
        if (checkSame(values, 2)) {
            int preValue = 0;
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < values.length; i++) {
                int curr = 1;
                if (map.containsKey(values[i])) {
                    curr = map.get(values[i]) + 1;
                }
                map.put(values[i], curr);

            }

            if (map.containsValue(2)) {
                return true;
            }
        }
        return false;
    }

    public boolean strategy4(List<String> colors) {
        String preColor = colors.get(0);
        for (String color : colors) {
            if (!color.equals(preColor)) {
                return false;
            }
            preColor = color;
        }
        return true;
    }

    public boolean strategy5(int[] values) {
        values = sort(values);
        return checkContinuity(values);
    }

    public boolean strategy6(int[] values) {
        return checkSame(values, 2);
    }

    public boolean checkSame(int[] values, int countLimiter) {
        int preValue = 0;
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < values.length; i++) {
            if (preValue == 0) {
                preValue = values[i];
                set.add(values[i]);
                continue;
            }
            if (!set.contains(values[i])) {
                count++;
            }

            if (count > countLimiter) {
                return false;
            }
            set.add(values[i]);
        }
        return true;
    }


    public boolean compare(String val1, String val2) {
        int v1 = strToInt(val1);
        int v2 = strToInt(val2);
        return v1 >= v2;
    }

    public int strToInt(String str) {
        switch (str) {
            case "K":
                return 13;
            case "Q":
                return 12;
            case "J":
                return 11;
            case "A":
                return 1;
            default:
                return Integer.parseInt(str);
        }
    }

    public int[] sort(int[] values) {
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values.length; k++) {
                if (values[i] > values[k]) {
                    int tmp = values[i];
                    values[i] = values[k];
                    values[k] = tmp;
                }
            }
        }

        return values;
    }

    public boolean checkContinuity(int[] values) {
        int preValue = 0;
        for (int value : values) {
            if (preValue == 0) {
                preValue = value;
                continue;
            }
            if ((value - preValue) > 1) {
                if (preValue == 1 && value == 10) {
                    values[0] = 14;
                    return checkContinuity(values);
                }
                return false;
            }
            preValue = value;
        }

        return true;
    }


    public void test0() {
        Scanner sr = new Scanner(System.in);
        int count = 0;
        int result = 0;
        boolean hasPrint = false;
        while (sr.hasNext()) {
            hasPrint = false;
            String next = sr.next();
            if (count == 2) {
                System.out.println(result);
                hasPrint = true;
                result = 0;
                count = 0;
            }
            result += Integer.parseInt(next);
            count++;
        }
        if (!hasPrint) {
            System.out.println(result);
        }
    }
}
