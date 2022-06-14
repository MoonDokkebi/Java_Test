import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CommentManager {
    public static ArrayList<CommentData>commentData;
    static Scanner in = new Scanner(System.in);
    static int commentNum = 0;

    public CommentManager(){commentData =new ArrayList<>();}
    private static void menu() {
        System.out.println("[[[[ 게시글 ]]]]");
        System.out.println(" 1. 게시글 목록");
        System.out.println(" 2. 게시글 생성");
        System.out.println(" 3. 게시글 삭제");
        System.out.println(" 4. 게시글 수정");
        System.out.println(" 0. 이전");
    }

    public static void goCommentMenu(int currentPost) {
        while(true) {
            menu();
            int menu = in.nextInt();
            if (menu == 0) return;
            System.out.println();
            switch (menu) {
                case 1 -> commentList();
                case 2 -> addcomment(currentPost);
                case 3 -> removecomment();
                case 4 -> upateComment(currentPost);
            }
        }
    }
    private static void upateComment(int currentPost) {

        int pos = findLocation(commentNum);
        if(pos!=-1) {
            System.out.println("내용 : ");
            String post = in.next();
            System.out.println("익명여부 : ");
            String yn = in.next();
            boolean chabgeName = false;
            if(Objects.equals(yn, "Y"))chabgeName=true;
            else if (Objects.equals(yn, "N"))chabgeName=false;


            commentData.set(pos, new CommentData(pos, post, chabgeName, currentPost));//pos 위치에 새로운 book 객체를 저장하는 메소드
        }
    }
    private static void removecomment() {
        System.out.println("댓글 삭제");
        System.out.println(commentData);
        System.out.println("지울 댓글 번호를 입력하세요 : ");
        int commentNum = in.nextInt();
        int pos = findLocation(commentNum);
        if(pos!=-1) commentData.remove(pos);
        else System.out.println("해당하는 댓글이 없습니다.");
    }

    private static void addcomment(int currentPost) {
        commentNum++;


        //System.out.println("만든 사람 입력하세요 : ");
        //String boardMakeer = in.next();
        System.out.println("게시글 내용을 입력하세요");
        String comment = in.next();
        boolean hide;
        boolean check ;
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

        CommentData CD = new CommentData(commentNum, comment, hide, currentPost);
        commentData.add(CD);
    }


    private static void commentList() {
        if(commentData.isEmpty()) {System.out.println("댓글이 없습니다.");}
        else{
            System.out.println("게시글 리스트");
            System.out.println(commentData);
        }
    }


    private static int findLocation(int commentNum){
        int pos=0;
        for (int i = 0; i < commentData.size() ; i++)
            if(commentData.get(i).getCommentNum()==commentNum) pos=i;
        return pos;
    }



}
