import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AlbumManager {
    private ArrayList<Album>list;
    private String filename;
    private static AlbumManager manager;
    private AlbumManager(String string) {
        list = new ArrayList<>();
        filename = string;
    }
    public static AlbumManager getInstance(String fname){
        if(manager == null) manager = new AlbumManager(fname);
        return manager;
    }

    public void add(Album album) {
        list.add(album);
    }
    public void showAll(){
        for(Album a: list) System.out.println(a);
    }
    //list에 있는 모든 Album객체들을 파일에 저장하는 메소드
    public void save() {
        //파일 읽기->파일에 객체 기록하기-> 파일닫기
        try {
            FileWriter writer = new FileWriter(filename);//FileWriter 객체 생성하면서 파일을 오픈, 파일이 없으면 생성해줌
            BufferedWriter buf = new BufferedWriter(writer);
            //파일에 기록하기
            for(Album album : list) {//list에 있는 모든 Album 객체에 대해 반복
                buf.write(album.getTitle() + ",");
                buf.write(album.getArtist() + ",");
                buf.write(album.getGenre() + ",");
                buf.write(album.getPubdate() + ",");
                buf.write(album.getCompany() + ",");
                buf.write(Float.toString(album.getRating()));
                buf.newLine(); // 엔터 다음 줄로 내리는것

            }
            buf.close();
            System.out.println(" 앨범데이터를 파일에 저장하였습니다. 파일명 : " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
