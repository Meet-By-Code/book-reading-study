package capter2.item7;
import java.util.*;

// 코드 7-1 메모리 누수가 일어나는 위치는 어디인가? (36쪽)
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object wrongPop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    // 코드 7-2 제대로 구현한 pop 메서드 (37쪽)
    /*
    * 자바는 가비지 컬렉터(이하 GC)를 갖춘 언어로써, 다 쓴 객체를 알아서 회수해간다. 그렇기 때문에 의도치 않게 객체를 살려두면 메모리 누수를 찾기가 매우 까다롭다.
    * 위의 Stack 클래스는 자기 메모리를 직접 관리하기 때문에 메모리 누수에 취약하다.
    *  → 객체 자체가 아니라 객체 참조를 담는 elements 배열을 사용한다.
    *  → elements 배열의 활성영역은 인덱스가 size 보다 작은 구간이다. (size 보다 큰 인덱스의 객체가 null이 아니라면 GC가 알 방법이 없고, 잠재적으로 성능에 악영향을 줄수 있다.)
    *
    * wrongPop() 메서드는 딱봐도 잘못된 곳이 눈에 띈다. 이제 더이상 쓰이지 않을 객체를 null 처리해주지 않았다. 이 문제는 아래의 pop() 메서드처럼 다 쓴 참조 객체를 해제해주면 된다.
    * 비활성 영역의 객체가 더 이상 쓸모없다는 것은 프로그래머만 아는 사실이다. 그러므로 이처럼 자기 메모리를 직접 관리하는 클래스를 설계했다면, 프로그래머는 항시 메모리 누수에 주의해야 한다.
    *
    * 그 외에 추가적으로 메모리 누수를 일으키는 존재들
    * 1. 캐시
    *  ㄱ. WeakHashMap에 key로 저장하는 방식으로 해결 가능(참조가 없으면 GC가 처리함)
    *  ㄴ. ScheduledThreadPoolExecutor 같은 백그라운드 스레드를 활용하거나 캐시에 세 엔트리를 추가할때 처리해주는 방법
    * 2. 리스너 or 콜백
    *  ㄱ. WeakHashMap에 key로 저장하는 방식으로 해결 가능(참조가 없으면 GC가 처리함)
    *
    * */
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }


    /**
     * 원소를 위한 공간을 적어도 하나 이상 확보한다.
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }



    public static void main(String[] args) {
        Stack stack = new Stack();
        for (String arg : args)
            stack.push(arg);

        while (true)
            System.err.println(stack.pop());
    }
}

