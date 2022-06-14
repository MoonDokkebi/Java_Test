import java.util.ArrayList;

//데이터 클래스
//단어 하나를 정의하는 클래스
public class Word {
    private String eng;
    private ArrayList<String> kor = new ArrayList<>();

    public Word(String eng, ArrayList<String> kor) {
        this.eng = eng;
        this.kor = kor;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public ArrayList<String> getKor() {
        return kor;
    }

    public void setKor(ArrayList<String> kor) {
        this.kor = kor;
    }
}
