package com.daramKim.capter2.item5;

import java.util.List;

// 정적 유틸리티를 잘못 사용한 예
public class WrongSpellChecker {
    private static final Lexicon dictionary = new Lexicon();

    private WrongSpellChecker(){} // 객체 생성 방지

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
