public class BoardData {

    private int boardNum;  //게시판번호
    private String boardTitle;//제목
    //private String    ;//보드 설명?
    private User boardMaker;//만든사람이름
    private boolean changeName;//익명여부

    public BoardData(int boardNum, String boardTitle, User boardMaker, boolean changeName) {
        this.boardNum = boardNum;
        this.boardTitle = boardTitle;
        this.boardMaker = boardMaker;
        this.changeName = changeName;
    }

    public int getBoardNum() {
        return boardNum;
    }


    @Override
    public String toString() {
        return "BoardData{" +
                "boardNum=" + boardNum +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardMaker=" + boardMaker +
                ", changeName=" + changeName +
                '}' + "\n";
    }

}
