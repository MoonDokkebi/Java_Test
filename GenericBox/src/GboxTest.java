
public class GboxTest {
    private  static void showBox(GBox box){
        box.showAll();
    }
    private  static <T> void showGBox(GBox<?> box){
        for (int i = 0; i < box.size() ; i++) {
            T data = (T) box.get(i);
            System.out.println(data);
        }
    }
    public static void main(String[] args) {
        GBox<Integer> box =new GBox(); //정수형 박스 객체 생성
        box.add(7);
        int data = box.get(0);
        System.out.println(data);

        GBox<String> sbox = new GBox(10);//문자열이 10개들어가는 s박스
        sbox.add("Spring");
        String data2 = sbox.get(0);
        System.out.println(data2);

        GBox<ComBook> bookbox =new GBox();//원래 5개 크기의 박스 생성
        bookbox.add(new ComBook("JAva1",12000));
        bookbox.add(new ComBook("JAva2",12000));
        bookbox.add(new ComBook("JAva3",12000));
        bookbox.add(new ComBook("JAva4",12000));
        bookbox.add(new ComBook("JAva5",12000));
        bookbox.add(new ComBook("JAva6",12000));
        bookbox.add(new ComBook("JAva7",12000));
        bookbox.showAll();

        showBox(box);
        showBox(sbox);

        showGBox(bookbox);
    }
}
