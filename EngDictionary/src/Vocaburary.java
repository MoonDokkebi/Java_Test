import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//매니저 클래스
public class Vocaburary {
    private HashMap<String, Word> voca; // 영어 단어 , 하나의 워드
    private String filename;
    private Scanner in = new Scanner(System.in);
    public Vocaburary(String file){
        filename = file;
        voca = new HashMap<>();
    }

    public void addWord(String eng, ArrayList<String> korWords){
        voca.put(eng, new Word(eng, korWords));
    }
    public void addWord(){
        String eng;
        ArrayList<String> korWord = new ArrayList<>();
        System.out.println(" English word : ");
        eng = in.next();
        if (voca.containsKey(eng)){
            System.out.println("영어사전에 이미 등록된 단어입니다. 삭제후 재등록하기 바랍니다.");
            return;
        }
        System.out.println("Korean word");
        String kor;
        while ((kor = in.next())!= null){
            if(kor.equals("#"))break;
            korWord.add(kor);
        }
        voca.put(eng, new Word(eng, korWord));
    }

    public void load() {
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader buf = new BufferedReader(reader);

            String line;
            while ((line = buf.readLine()) != null){
                String eng;
                ArrayList<String> korWords = new ArrayList<>();
                StringTokenizer st = new StringTokenizer(line, ",");
                eng =st.nextToken();
                while(st.hasMoreTokens()){
                    korWords.add(st.nextToken().trim());// 공백을 제거한 단어를 리스트에 추가
                }
                addWord(eng, korWords);
            }
            buf.close();
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println(filename + "파일이 존재하지 않습니다.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void find() {
        String eng;

        System.out.print("English word");
        eng = in.next();

        if (voca.containsValue(eng)){ // 영어단어가 해쉬맵의 키로 존재하는 경우
            String output =voca.get(eng).getKor().toString();
            System.out.println(output);
        }
        else System.out.println("영어사전에 검색하는 단어가 없습니다.");
    }

    //영어사전을 정렬해서 출력하기
    public void showAll() {
        TreeMap<String, Word> tmap = new TreeMap<>();//voca 해시맵 데이터를 복사해서 트리맵 생성
        Iterator<String> iter = tmap.keySet().iterator(); //Iterator는 일종의 포인터

        while (iter.hasNext()){
            String key = iter.next(); // 키(영어단어)가 전달
            System.out.println(key+" : " + tmap.get(key).getKor().toString());
        }

    }
}
