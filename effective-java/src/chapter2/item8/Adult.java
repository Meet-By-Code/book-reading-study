package capter2.item8;

// cleaner 안전망을 갖춘 자원을 제대로 활용하는 클라이언트 (45쪽)
public class Adult {
    // let's do it
    /*
    * ----결과----
    * 안녕~
    * Room Count(in using resources) = 7
    * Cleaning room
    * Room Count(after use of resources) = 0
    * ------------
     * */
    public static void main(String[] args) {
        Room.State state;
        /*
        * try-with-resources 문법
        * 자원을 다 쓰면 자동으로 반환한다.
        * */
        try (Room myRoom = new Room(7)) {
            System.out.println("안녕~");
            state = myRoom.getState();
            System.out.println("Room Count(in using resources) = " + state.numJunkPiles);
        }
        System.out.println("Room Count(after use of resources) = " + state.numJunkPiles);
    }
}
