import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


public class Test {
    ArrayList<HashMap<String, Object>> boardTable = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        /*
         * ArrayList와 HashMap을 사용해 게시판 테이블을 만들고,
         * 조회, 등록, 수정, 삭제가 가능한 게시판을 만들어주세요.
         *
         * 번호, 제목, 내용, 작성자, 작성일
         *
         * 목록에서는 조회, 등록, 종료
         * 상세화면에서는 수정, 삭제, 목록
         *
         */
        new Test().start();
    }

    private void start() {
        while(true){
            System.out.println("=================================");
            System.out.println("번호\t제목\t작성자\t작성일");
            System.out.println("---------------------------------");
            for(int i = boardTable.size() - 1; i >= 0 ; i--){
                HashMap<String, Object> board = boardTable.get(i);
                System.out.println(board.get("BOARD_NO")
                        + "\t" + board.get("TITLE")
                        + "\t" + board.get("USER_NAME")
                        + "\t" + format.format(board.get("REG_DATE")));
            }
            System.out.println("=================================");

            System.out.print("1.조회  2.등록  0.종료>");
            int input = in.nextInt();

            switch (input) {
                case 1:
                    read();
                    break;
                case 2:
                    insert();
                    break;
                case 0:
                    System.out.println("프로그램이 종료되었습니다.");
                    System.exit(0);
                    break;
            }
        }
    }

    private void read() {
        System.out.print("조회할 게시물 번호>");
        int boardNo = in.nextInt();

        HashMap<String, Object> board = null;
        for(int i = 0; i < boardTable.size(); i++){
            if(boardNo == (int)boardTable.get(i).get("BOARD_NO")){
                board = boardTable.get(i);
            }
        }

        System.out.println("=======================");
        System.out.println("번호\t: " + board.get("BOARD_NO"));
        System.out.println("-----------------------");
        System.out.println("작성자\t: " + board.get("USER_NAME"));
        System.out.println("-----------------------");
        System.out.println("작성일\t: " + format.format(board.get("REG_DATE")));
        System.out.println("-----------------------");
        System.out.println("제목\t: " + board.get("TITLE"));
        System.out.println("-----------------------");
        System.out.println("내용\t: " + board.get("CONTENT"));
        System.out.println("=======================");

        System.out.print("1.수정  2.삭제  0.목록>");
        int input = in.nextInt();

        switch (input) {
            case 1:
                update(board);
                break;
            case 2:
                delete(board);
                break;
            case 0:

                break;
        }
    }

    private void delete(HashMap<String, Object> board) {
        System.out.print("정말 삭제 하시겠습니까?(Y/N)>");
        String input = in.nextLine();

        switch (input) {
            case "Y":
                for(int i = 0; i < boardTable.size(); i++){
                    if(board.get("BOARD_NO") == boardTable.get(i).get("BOARD_NO")){
                        boardTable.remove(i);
                        System.out.println("게시글이 삭제되었습니다.");
                        break;
                    }
                }
                break;
        }
    }

    private void update(HashMap<String, Object> board) {
        System.out.print("제목>");
        board.put("TITLE", in.nextLine());
        System.out.print("내용>");
        board.put("CONTENT", in.nextLine());

        System.out.println("게시글이 수정되었습니다.");
    }

    private void insert() {
        HashMap<String, Object> board = new HashMap<>();

        int max = 0;
        for(int i = 0; i < boardTable.size(); i++){
            if(max < (int)boardTable.get(i).get("BOARD_NO")){
                max = (int)boardTable.get(i).get("BOARD_NO");
            }
        }

        board.put("BOARD_NO", max + 1);
        System.out.print("제목>");
        board.put("TITLE", in.nextLine());
        System.out.print("내용>");
        board.put("CONTENT", in.nextLine());
        System.out.print("작성자>");
        board.put("USER_NAME", in.nextLine());
        board.put("REG_DATE", new Date());

        boardTable.add(board);

        System.out.println("작성하신 게시글이 등록되었습니다.");
    }
}
