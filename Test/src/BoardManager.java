import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BoardManager {
    public static ArrayList<BoardData> boardData;
    public static int currentBoard;
    Scanner in = new Scanner(System.in);
    static int boardNum = 0;
    public BoardManager(){boardData =new ArrayList<>();}
    public void boardList() {

        if(boardData.isEmpty()) {System.out.println("게시판이 없습니다.");}
        else{
            System.out.println("게시판 리스트");
            System.out.println(boardData);

        }
        System.out.println("이전 : 0");
        System.out.println("들어갈 게시판번호를 입력하세요");

        boardNum = in.nextInt();
        if (boardNum==0) return;
        for (int i = 0; i < boardData.size() ; i++) {
            if (boardNum != boardData.get(i).getBoardNum()) {
                System.out.println("해당번호의 계시판이 없습니다.");
            }else if (boardNum == boardData.get(i).getBoardNum()){
                currentBoard =boardData.get(i).getBoardNum();
                PostManager.goPostMenu(currentBoard);

            }

        }
    }



    public void addBoard(User boardMaker) {

        boardNum++;

        System.out.println("게시판 이름을 입력하세요 : ");
        String boardTitle = in.next();
        //System.out.println("만든 사람 입력하세요 : ");
        //String boardMakeer = in.next();
        boolean hide;
        boolean check;
        do {
            System.out.println("익명 여부를 입력하세요(Y/N) : ");
            String bool = in.next();
            hide = true;
            if (Objects.equals(bool, "Y")) {
                hide = true;
                check = false;
            } else if (Objects.equals(bool, "N")) {
                hide = false;
                check = false;
            } else {
                System.out.println("Y/N을 입력하세요");
                check = true;
            }
        } while (check);



        BoardData BD = new BoardData(boardNum, boardTitle, boardMaker, hide);
        boardData.add(BD);
    }

    public void removeBoard() {
        int pos =0;
        System.out.println("게시판 삭제");
        System.out.println(boardData);
        System.out.println("지울 게시판 번호를 입력하세요 : ");
        System.out.println("이전 : 0");
        boardNum = in.nextInt();
        if (boardNum==0) return;
        int boardNum = in.nextInt();
        for (int i = 0; i <boardData.size() ; i++) {
            if (boardData.get(i).getBoardNum()==boardNum) pos = i;
        }
        boardData.remove(pos);
    }


}
