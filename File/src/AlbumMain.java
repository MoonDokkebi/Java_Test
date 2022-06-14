public class AlbumMain {
    public static void main(String[] args) {
        AlbumManager manager = AlbumManager.getInstance("album.txt");

        manager.add(new Album("여행1", "볼빨간 사춘기", "발라드","2018/04/15", "소속사1",9.4));
    }

}
