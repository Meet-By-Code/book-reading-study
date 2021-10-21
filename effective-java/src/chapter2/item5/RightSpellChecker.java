package capter2.item5;

import java.util.List;
import java.util.Objects;

// 올바른 예: DI 방식(DICTIONARY를 외부에서 주입 받음.)
public class RightSpellChecker {
    private final Lexicon dictionary;

    public RightSpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

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
/*
* 위의 두 예시 클래스인 WrongSpellChekcer, WrongSpellChekcer2는 모두 사전을 단 하나만 사용한다고 가정한다는
* 점에서 그리 훌륭해 보이지 않는다. 실세계에서는 사전이 언어별로 따로 있고 특수 어휘용 사전을 별도로 두기도 한다.
* 심지어 테스트용 사전도 필요할 수 있다. 사전 하나로 이 모든 쓰임에 대응할 수는 없다.
*
* 이제, RightSpellChecker를 살펴보자..
* 필드에서 final 한정자를 제거하고 다른 사전으로 교체하는 메서드를 추가하는 방법도 있지만, 아쉽게도 이 방법은
* 어색하고 오류를 내기 쉬우며 멀티스레드 환경에서는 쓸 수 없다.
*
* 사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식이 적합하지 않다.
* → 클래스(RightSpellChecker)가 여러 자원 인스턴스를 지원해야 하며, 클라언트가 원하는 자원(dictionary)을 사용해야 한다.
*
* 이 조건을 만족하는 간단한 패턴이 있다. 바로 의존성 주입(DI) 방식의 한 형태인 '인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식'이다.
* 즉, 스펠체커(해당 클래스의 인스턴스)를 생성할 때 어떤 사전(의존성 객체)을 쓸 것인지 정해(주입)주면 된다.
* */