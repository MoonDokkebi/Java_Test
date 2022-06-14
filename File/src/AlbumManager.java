import java.io.*;
import java.util.ArrayList;
import java.util.PrimitiveIterator;
import java.util.StringTokenizer;

public class AlbumManager {
    private ArrayList<Album> list;
    private String filename;
    private static  AlbumManager manager = null;

    private  AlbumManager(String string){
        list = new ArrayList<>();
        filename = string;
    }
    public  static  AlbumManager getInstance(String fname){
        if(manager == null)manager = new AlbumManager(fname);
        return manager;
    }
    public void add(Album album){
        list.add(album);
    }
    public void showAll(){
        for (Album a : list) System.out.println(a);
    }
    public void save(){
        try{
            FileWriter writer = new FileWriter(filename);
            BufferedWriter buf = new BufferedWriter(writer);
            for(Album album : list){
                buf.write(album.getTitle() +",");
                buf.write(album.getArtist() +",");
                buf.write(album.getGenre() +",");
                buf.write(album.getCompany() +",");
                buf.write(album.getPubdate() +",");
                buf.write(album.getCompany() +",");
                buf.write(album.getRating() +",");
                buf.newLine();
            }
            buf.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(){
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader buf = new BufferedReader(reader);
            String line;
            while((line = buf.readLine()) != null){
                StringTokenizer token = new StringTokenizer(line,",");
                String title = token.nextToken();
                String artist = token.nextToken();
                String genre = token.nextToken();
                String pubdate = token.nextToken();
                String company = token.nextToken();
                Double rating = Double.parseDouble(token.nextToken());
                add(new Album(title,artist,genre,pubdate,company,rating));
            }
            buf.close();
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println(filename + "파일이 존재하지 않습니다.");
        }catch (IOException e){
            System.out.println(filename + " 파일을 읽는과정중 오류발생");
            e.printStackTrace();
        }
    }
}
