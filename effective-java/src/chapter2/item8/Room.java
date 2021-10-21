package capter2.item8;

import java.lang.ref.Cleaner;

// 코드 8-1 cleaner를 안전망으로 활용하는 AutoCloseable 클래스 (44쪽)
/*
* cleaner(자바 8이하에선 finalizer 인듯)는 메이저 기능으로 사용하지 않는다고 함.
* 안전망 역할이나 중요하지 않은 네이티브 자원 회수용으로만 사용. 이 경우에도 불확실성과 성능 저하에 주의해야 한다.
* */
public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    // 청소가 필요한 자원. 절대 Room을 참조해서는 안 된다!
    // Adult 에서 테스트 용이하게 하기 위해 protected로 수정
    protected static class State implements Runnable {
        int numJunkPiles; // Number of junk piles in this room

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // close 메서드나 cleaner가 호출한다.
        @Override public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    // 방의 상태. cleanable과 공유한다.
    private final State state;

    // Adult에서 테스트 호출용도로 만듬.
    public State getState() {
        return state;
    }

    // cleanable 객체. 수거 대상이 되면 방을 청소한다.
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override public void close() {
        cleanable.clean();
    }

    // let's do it
    /*
    * ----결과----
    * Cleaning room
    * result: 0
    * -----------
    *
    * */
    public static void main(String[] args) {
        int fileCnt = 1;
        Room room = new Room(fileCnt);
        room.close();
        // room이 0이 나와야됌
        System.out.println("result: "+ room.state.numJunkPiles);
    }

}
