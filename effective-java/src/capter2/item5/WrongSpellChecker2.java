package com.daramKim.capter2.item5;

import java.util.List;

// 싱글턴을 잘못 사용한 예
public class WrongSpellChecker2 {
    private static final Lexicon dictionary = new Lexicon();

    // 싱글턴 공식
    private static final WrongSpellChecker2 INSTANCE = new WrongSpellChecker2();
    private WrongSpellChecker2(){}

    public static boolean isValid(String word){
        /*
        * ......
        * */
        return false;
    }

    public static List<String> suggestions(String typo){
        /*
        * ......
        * */
        return null;
    }
}
