package com.bamboovir.muresearchboost.tool;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class InputUtilTest {

    @Test
    @DisplayName("Dummy test")
    public void dummyTest() {
        int expected = 4;
        int actual = 2 + 2;
        assertEquals(expected, actual, "INCONCEIVABLE!");
        //
        Object nullValue = null;
        assertFalse(nullValue != null);
        assertNull(nullValue);
        assertNotNull("A String", "INCONCEIVABLE!");
        assertTrue(nullValue == null);
    }

    @Test
    @DisplayName("Check String Test")
    public void checkStringList() {
        List<String> stringList = Arrays.asList(
                "as", // 2 <
                "asd",
                "hd945",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaa", // 25 +
                "$s\\#%^s&*()s!_+",
                "asdasd aasdfas",
                "");

        List<String> tmp = stringList.stream()
                .flatMap(str -> Stream.of(str.split(" "))) // 对字符串进行检查 先根据空格再分一遍
                .map(str -> str.replaceAll("[^\\p{IsLetter}0-9]+", "")) // Replace any sequence of non-letters and non-number with a "":
                .map(str -> str.replaceAll("[\\s|\\u00A0]+", "")) // remove unbreakable spaces
                .filter(str -> str.length() < 25 && str.length() > 2) // 去掉过长/过短的词
                .collect(Collectors.toList());

        assertTrue(Arrays.asList("asd","sss","hd945","asdasd","aasdfas").containsAll(tmp));

    }

    @Test
    public void isValidEmail() {
        List<Boolean> result = new ArrayList<>();
        List<String> inputList = Arrays
                .asList("hd945@mail.missouri.edu"
                ,""
                ,"hd945@"
                ,"hd945@666"
                ,"hd945@666.com"
                ,"@666.com"
                ,"@@gmail.com"
                ,"!#4%^&@&*.com");
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

        assertEquals(inputList.stream().map(email -> pattern.matcher(email).matches()).collect(Collectors.toList())
                , Arrays.asList(Boolean.TRUE
                        ,Boolean.FALSE
                        ,Boolean.FALSE
                        ,Boolean.FALSE
                        ,Boolean.TRUE
                        ,Boolean.FALSE
                        ,Boolean.FALSE
                        ,Boolean.FALSE));
    }
}