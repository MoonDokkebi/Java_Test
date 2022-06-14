import java.util.Scanner;

public class DicMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Vocaburary voca = new Vocaburary("voca1800.txt");
        voca.load(); // 파일에서 데이터 읽어오기

        while (true){
            System.out.println("1.Find, 2.Add, 3.Listup, 0.Exit >>>");
            System.out.println("Enter : ");
            int menu= in.nextInt();
            if (menu == 0)
            switch (menu){
                case 1 -> voca.find();
                case 2 -> voca.addWord();
                case 3 -> voca.showAll();


            }
        }
    }
}
