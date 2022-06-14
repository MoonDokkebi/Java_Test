public class PostData {
    private int postNum;  //게시글번호
    private String postTitle;//게시글제목
    private String   post;//게시글 내용?
    //private String postMakeer;//만든사람이름
    private boolean changeName;//익명 여부
    private int boardnum; //소속된곳

    public PostData(int postNum, String postTitle, String post, boolean changeName, int boardnum) {
        super();
        this.postNum = postNum;
        this.postTitle = postTitle;
        this.post = post;
        //this.postMakeer = postMakeer;
        this.changeName = changeName;
        this.boardnum = boardnum;
    }

    public int getPostdNum() {return postNum;}


    @Override
    public String toString() {
        return "PostData{" +
                "postNum='" + postNum + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", post='" + post + '\'' +
                //", postMakeer='" + postMakeer + '\'' +
                ", changeName=" + changeName +
                ", boardnum='" + boardnum + '\'' +
                '}';
    }


}
