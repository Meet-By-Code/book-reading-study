package capter2.item6;

import java.util.Comparator;

// 코드 6-3 끔찍이 느리다! 객체가 만들어지는 위치를 찾았는가? (34쪽)
/*
* [ sum += i; ] 구분을 실행할때마다 boxed primitive object가 생성된다.
* 'Integer.MAX_VALUE'번 반복하므로 2의 31승.. 개의 객체가 생성된다. 아래 상황에서는
* 간단한 계산만 하는 로직이므로 primitive type으로 쓰는 것이 성능 개선에 도움이 된다.
* */
public class Sum {
    private static long sum() {
//        Long sum = 0L; // 3589.017167 ms. 3098.316 ms. 740.136167 ms. 722.837 ms. 이것도 3, 4번 반복하다보면 비슷해짐 jvm클라스
        long sum = 0L; // 734.983542 ms.
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }

    public static void main(String[] args) {
        int numSets = Integer.parseInt(args[0]);
        long x = 0;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            x += sum();
            long end = System.nanoTime();
            System.out.println((end - start) / 1_000_000. + " ms.");
        }

        // VM이 최적화하지 못하게 막는 코드
        if (x == 42)
            System.out.println();
    }
}
