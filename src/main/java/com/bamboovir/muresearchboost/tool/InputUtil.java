package com.bamboovir.muresearchboost.tool;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputUtil {
    public static List<String> checkStringList(List<String> stringList) {
        return stringList.stream()
                .flatMap(str -> Stream.of(str.split(" ")))
                .map(str -> str.replaceAll("[^\\p{IsLetter}0-9]+", "")) // Replace any sequence of non-letters and non-number with a "":
                .map(str -> str.replaceAll("[\\s|\\u00A0]+", "")) // remove unbreakable spaces
                .filter(str -> str.length() < 25 && str.length() > 2)
                .collect(Collectors.toList());
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }


}
