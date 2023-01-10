package com.urise.webapp;

public class MainString {
    public static void main(String[] args) {
        String[] strArray = {"1", "2", "3", "4", "5"};
        StringBuilder result = new StringBuilder();
        for (String str : strArray) {
            result.append(str).append(", ");
        }
        System.out.println(result.toString());

        String str1 = "abc";
        String str3 = "c";
        String str2 = ("ab" + str3).intern();
        System.out.println(str1 == str2);
    }
}
