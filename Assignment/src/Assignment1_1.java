import java.util.ArrayList;
import java.util.Random;

public class Assignment1_1 {
    public static void main(String[] args) {
        int num;
        int count =0; //위치값
        Random r =new Random();
        ArrayList<Integer> list = new ArrayList<Integer>();


        num = r.nextInt(40) + 10; //10부터 50 까지 난수생성
        list.add(num);


        while(list.size()<10){
            num = r.nextInt(40) + 10; //난수생성
            if (!list.contains(num)) { //중복체크
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) < num) { //num이 [i]보다클경우 위치값++
                        count++;
                    }
                }
                list.add(count,num);
                count =0;
            }
        }
        System.out.println("## 배열 출력 ##");
        for(Integer i : list) { System.out.println(i); }

    }
}
