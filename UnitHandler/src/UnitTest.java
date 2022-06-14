import java.util.ArrayList;
import java.util.HashMap;

public class UnitTest {
    public static void main(String[] args) {
        UnitHandler handler = new UnitHandler();

        /* private 로 접근을 막았기 때문데 외부에서 내부클래스의 객체생성할수없음
        UnitHandler.LengthUnit lunit = handler.new LengthUnit();// 이너 클래스의 객체생성은 외부클래스 객체를 사용해야함
        lunit.setUnits("cm","m");
        double value =lunit.convert(320);
        System.out.println(value);
        value = lunit.convert(400);
        System.out.println(value);


        lunit.setUnits("cm", "ft");
        value = lunit.convert(4500);
        System.out.println(value);*/

/*
        double value = handler.convert("cm","m",320);
        System.out.println(value);
        value = handler.convert("cm","ft",4500);
        System.out.println(value);

*/

        UnitHandler.WeightUnit wunit = new UnitHandler.WeightUnit();
        wunit.setUnits("g", "kg");
        double value = wunit.convert(2340);
        System.out.println(value);

        //Anonymous class의 객체생성
        UnitInterface unit = new UnitInterface() {
            @Override
            public double convert(String from, String to, double value) {
                if (from.equals("g"))
                    if (to.equals("kg")) return value / 1000;
                    else if (to.equals("ton")) return value / 1000000;
                return -1;
            }
        };
        value = unit.convert("g","kg",7680);
        System.out.println(value);

        //1차원 가변길이 배열
        ArrayList<Integer> alist = new ArrayList<Integer>();
        alist.add(21);
        alist.add(33);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Lemon");
        map.put(2, "Cherry");

    }
}

