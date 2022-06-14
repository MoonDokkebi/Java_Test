import java.util.Scanner;

public class MainPage {
    private static Scanner in = new Scanner(System.in);

    private static void bfloginmenu() {
        System.out.println("[[[[ 로그인전 ]]]]");
        System.out.println(" 1. 회원가입");
        System.out.println(" 2. 로그인");
        System.out.println(" 0. 종료");
    }
    private static void afloginmenu() {
        System.out.println("[[[[ 로그인후 ]]]]");
        System.out.println(" 1. 게시판 목록");
        System.out.println(" 2. 게시판 생성");
        System.out.println(" 3. 게시판 삭제");
        System.out.println(" 4. 시간표");
        System.out.println(" 0. 종료");
    }
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        BoardManager boardManager = new BoardManager();
        TimeTableManager timeTableManager = new TimeTableManager();
        boolean check = true;
        while(check) {
            bfloginmenu();
            int bfloginmenu = in.nextInt();
            if (bfloginmenu == 0) {
                System.out.println("프로그램 종료");
                break;
            }
            System.out.println();
            switch (bfloginmenu) {
                case 1 -> userManager.register();
                case 2 ->check = userManager.login();

            }
        }

        while(!check) {
            afloginmenu();
            int afloginmenu = in.nextInt();
            if (afloginmenu == 0) {
                System.out.println("로그아웃? 종료");
                break;
            }
            switch (afloginmenu) {
                case 1 -> boardManager.boardList();
                case 2 -> boardManager.addBoard(UserManager.currentUser);
                case 3 -> boardManager.removeBoard();
                case 4 -> timeTableManager.showTimeTableMenu();
            }
        }

    }
}
