import java.util.ArrayList;
import java.util.function.Consumer;

public class LamdaTest {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.sendMsg("Hello");

        MyInterface obj2 = new MyInterface() {
            @Override
            public void sendMsg(String msg) {
                System.out.println("Annonymous class : " + msg);
            }
        };
        obj2.sendMsg("Spring");

        MyInterface obj3 = (msg)->{
            if (msg.equals("Summer"))
                System.out.println("Annonymoous Class lamda : HOT"+msg);
            else
                System.out.println("Annonymoous Class lamda :"+msg);
        };
        obj3.sendMsg("Summer");
        perform("Winter", obj3);

        ArrayList<Integer>data = new ArrayList<>();
        data.add(5);
        data.add(2);
        data.add(6);
        data.add(7);
        data.add(9);
        Consumer<Integer> obj4 = (a) -> {a++;
            System.out.println(a);};
        data.forEach(obj4);

    }

    private static void perform(String msg, MyInterface obj) {
    }
}
