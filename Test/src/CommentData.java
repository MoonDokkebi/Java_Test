public class CommentData {
    private int commentNum;  //게시글번호
    private String comment;//게시글 내용?
    //private String postMakeer;//만든사람이름
    private boolean changeName;//익명 여부
    private int postnum; //소속된곳

    public CommentData(int commentNum, String comment, boolean changeName, int postnum) {
        super();
        this.commentNum = commentNum;
        this.comment = comment;
        //this.postMakeer = postMakeer;
        this.changeName = changeName;
        this.postnum = postnum;
    }

    public int getCommentNum() {
        return commentNum;
    }


    @Override
    public String toString() {
        return "PostData{" +
                "commentNum='" + commentNum + '\'' +
                ", comment='" + comment + '\'' +
                //", postMakeer='" + postMakeer + '\'' +
                ", changeName=" + changeName +
                ", postnum='" + postnum + '\'' +
                '}';
    }
}
