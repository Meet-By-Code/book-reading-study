package capter2.item6;
import java.util.regex.Pattern;

// 값비싼 객체를 재사용해 성능을 개선한다. (32쪽)
public class RomanNumerals {
    // 코드 6-1 성능을 훨씬 더 끌어올릴 수 있다!
    /*
    * String.matches는 내부적으로 정규표현식용 Pattern 인스턴스를 만드는데, 이 패턴 인스턴스는 한번 쓰고 버려지기 때문에
    * 반복적으로 사용될 소지가 있는 케이스에선 String.matches를 사용하지 않는 것이 좋다.
    *
    * ⁉️️ Pattern은 입력받은 정규표현식에 해당하는 유한 상태 머신(final state machine)을 만들기 때문에 인스턴스 생성비용이 높다.
    * */
    static boolean isRomanNumeralSlow(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // 코드 6-2 값비싼 객체를 재사용해 성능을 개선한다.
    /*
    * 성능을 개선하기 위해 정규표현식을 표현하는 Pattern 인스턴스를 static 인스턴스로 생성하고, 호출될 때마다 재사용
    * String.matches(regex); → Pattern.matcher(str).matches();  ✅
    *  */
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches();
    }

    public static void main(String[] args) {
        int numSets = Integer.parseInt(args[0]);
        int numReps = Integer.parseInt(args[1]);
        boolean b = false;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < numReps; j++) {
                // 성능 차이를 확인하려면 xxxSlow 메서드를 xxxFast 메서드로 바꿔 실행해보자.
                b ^= isRomanNumeralSlow("MCMLXXVI");
            }
            long end = System.nanoTime();
            System.out.println(((end - start) / (1_000. * numReps)) + " μs.");
        }

        // VM이 최적화하지 못하게 막는 코드
        if (!b)
            System.out.println();
    }
}

