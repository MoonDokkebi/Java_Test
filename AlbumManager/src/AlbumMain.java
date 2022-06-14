


public class AlbumMain {
    public static void main(String[] args) {
        //AlbumManager manager = new AlbumManager("album.txt");
        //매니저가 여러개 필요없으므로 static 써서 하나만 만듬
        AlbumManager manager = AlbumManager.getInstance("album.csv");

        manager.add(new Album("여행1","볼빨간 사춘기","발라드", "2021/04/15","사춘기뮤직", (float) 9.4));
        manager.add(new Album("여행1","볼빨간 사춘기","발라드", "2021/04/15","사춘기뮤직", (float) 9.4));
        manager.add(new Album("여행1","볼빨간 사춘기","발라드", "2021/04/15","사춘기뮤직", (float) 9.4));
        manager.add(new Album("여행1","볼빨간 사춘기","발라드", "2021/04/15","사춘기뮤직", (float) 9.4));
        manager.add(new Album("여행1","볼빨간 사춘기","발라드", "2021/04/15","사춘기뮤직", (float) 9.4));
        manager.showAll();

        manager.save();
    }
}
