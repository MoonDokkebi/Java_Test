import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RainDataMain {

    private static Scanner in = new Scanner(System.in);



    private static void menu() {
        System.out.println("[[[[ 도시별 월별 강수량 데이터 ]]]]");
        System.out.println(" 1. 도시별 월간 강수량 검색");
        System.out.println(" 2. 도시별 최대 강수량 검색");
        System.out.println(" 3. 강수량 데이터 등록");
        System.out.println(" 4. 강수량 데이터 삭제");
        System.out.println(" 0. 종료");
    }


    public static void main(String[] args) {
        RainDataManager manager = new RainDataManager();

        if(manager.loadData() == -1) return;
        manager.showAllData();

        while(true) {
            menu();
            int menu = in.nextInt();
            if(menu ==0){
                System.out.println("프로그램 종료");
                break;
            }

            System.out.println();
            switch (menu) {
                case 1 -> manager.findMonthRain();
                case 2 ->manager.findHighestRain();
                case 3 -> manager.addRainData();
                case 4 ->manager.removeRainData();
            }
        }
    }




}
