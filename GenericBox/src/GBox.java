//제네릭 클래스 정의
import java.util.*;
//public class GBox<T extends Book> {// T : Parameterized Type, book과 book 하위 자식 ㅁ클래스 타입만 허용 , T super Combook
public class GBox<T> {
    // private T[] space = new T[5]; 제네릭타입으로는 배열을 만들수 없음
    //ArrayList<T> space;
    private Object[] space;     //Object 최상위 클래스 타입으로 배열선언
    // space에는 여러 오브젝트가 들어갈수있다.
    private int capacity;      // 박스의 용량
    private int cnt;           //박스에 담긴 물건의 개수
    private static final int INIT = 5;

    private void sizeUp(){ //사이즈 늘려주는 메소드
        capacity *= 2;
        Object[] temp = new Object[capacity]; // 용량두배의 배열 공간을 생성
        for (int i =0; i <cnt ; i++) temp [i] = space[i];
        space =temp;
        //원래 space 배열은 가리키는 참조가 사라짐
        // -> 힙공간에 쓰레기가 됨-> 자바 가상머신에는 가비지콜렉터가있음
    }

    public  GBox(){
        space = new Object[INIT];
        capacity = INIT;
        cnt = 0;
    }
    public  GBox(int s){
        space = new Object[s];
        capacity = s;
        cnt = 0;
    }

    public void add(T i) {
        if(cnt == capacity) sizeUp();//용량이 다찻을경우 사이즈업
        space[cnt++] = i;
    }

    public T get(int i) {
        return (T)space[i];  //Object--> T타입으로 변환
    }

    public void showAll(){
        for(int i = 0; i < cnt; i++) System.out.println(space[i]);
    }
    public void remove(int i){
        if(i<cnt){
            cnt--;
            for(int k =0; k < cnt; k++)space[k] = space[k+i];
        }
    }


    public int size() {
        return cnt;
    }
}
