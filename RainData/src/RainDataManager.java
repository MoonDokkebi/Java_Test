import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RainDataManager {
    private String[][] cities = {{"108", "서울"}, {"159", "부산"}, {"133", "대전"},
            {"184", "제주"}, {"112", "인천"}, {"119", "수원"}, {"156", "광주"}, {"143", "대구"}};
    public static ArrayList<RainData> data;
    Scanner in = new Scanner(System.in);


    public RainDataManager(){data =new ArrayList<>();}//객체 생성

    public int loadData() {
        String file = "F:\\상민\\대학교\\3학년1학기\\JAVA\\과제1\\raindata.csv";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            String line;
            while((line = buf.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                String yearmonth = tokenizer.nextToken();
                String citycode = tokenizer.nextToken();
                String rain = tokenizer.nextToken();
                System.out.println(yearmonth + " : " + citycode + " : " + rain);


                String city = null;
                for (String[] code : cities) {
                    if (code[0].equals(citycode))
                        city = code[1];
                }

                RainData rainData = new RainData(yearmonth,city,citycode, rain);
                data.add(rainData);
            }
            buf.close();
        } catch (FileNotFoundException e) {
            System.out.println("File open error : "+file+"을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void showAllData() {
        data.sort(Comparator.comparing(RainData::getCity).thenComparing(RainData ::getYearmonth));
        // 이름순으로 오름차순 정렬  이름이 같으면 날짜로 오름차순 정렬
        for(RainData rainData : data) rainData.printInfo();
    }

    public void findMonthRain() {
        int pos1=-1;
        int pos2=-1;
        System.out.println("도시별 월간 강수량 검색");
        System.out.println("도시명을 입력하세요 : ");
        String cityname = in.next();
        System.out.println("시작년월을 입력하세요(입력방식: yyyy-mm) : ");
        String startym = in.next();
        System.out.println("종료년월을 입력하세요(입력방식: yyyy-mm) : ");
        String endym = in.next();


        for (int i = 0; i < data.size() ; i++){
            if(data.get(i).getCity().equals(cityname)){
                if (data.get(i).getYearmonth().equals(startym)) pos1 = i;
                if (data.get(i).getYearmonth().equals(endym)) pos2 = i;
            }
        }//for끝
        for (int i = pos1; i <= pos2; i++) {
            data.get(i).printInfo();
        }
        System.out.println();
    }//메소드 끝


    public void findHighestRain() {
        //도시명, 년도 입력받고 최대 강수량의 년월과 강수량 검색 출력
        double max_temp = 0;
        int pos=-1;
        System.out.println("최대 강수량 검색");
        System.out.println("도시명을 입력하세요 : ");
        String cityname = in.next();
        System.out.println("년도를 입력하세요(입력방식: yyyy) : ");
        String year = in.next();
        for (int i = 0; i < data.size() ; i++) {

            if (data.get(i).getCity().equals(cityname)
                    && data.get(i).getYearmonth().contains(year)) {
                if (Double.parseDouble(data.get(i).getRain()) > max_temp) {
                    max_temp = Double.parseDouble(data.get(i).getRain());
                    pos =i;
                }
            }
        }//for끝
        System.out.println("최대 강수량인 월은 : " + (data.get(pos).getYearmonth()));
        System.out.println("그 달의 강수량은 : " + (data.get(pos).getRain()));
        System.out.println();
    }


    public void addRainData() {
        //강수량 데이터 등록 : 도시명, 년월, 강수량 입력받고 추가한다.
        //이때 이미 등록된 데이터가 있으면 변경할지 물어보고 예를 선택한 경우
        //데이터를 교체한다.
        System.out.println("강수량 데이터 등록");
        System.out.println("도시명을 입력하세요 : ");
        String cityname = in.next();
        System.out.println("년월을 입력하세요(입력방식: yyyy-mm) : ");
        String yearmonth = in.next();
        System.out.println("강수량을 입력하세요 : ");
        String rain = in.next();
        String citycode = "";
        for (String[] code : cities) {
            if (code[1].equals(cityname))
                citycode = code[0];
        }

        String check = "";
        int pos=-1;
        for (int i = 0; i < data.size() ; i++){
            if(data.get(i).getCity().equals(cityname)//등록된 데이터가 있을때
                    &&data.get(i).getYearmonth().equals(yearmonth)){
                System.out.println("등록된 데이터가있습니다. 변경하시겠습니까?(Y/N)");
                check = in.next();
                pos = i;
            }//if
        }//for
        switch (check) {
            case "N","n" -> {break;}
            case "Y","y" ->{
                data.set(pos,new RainData(yearmonth,cityname,citycode,rain));
            }default -> {data.add(new RainData(yearmonth, cityname,citycode, rain));}
        }//sitch
        showAllData();
        System.out.println();
    }

    public void removeRainData() {
        //강수량 데이터 삭제 : 도시명, 년월 입력받고 해당 데이터를 삭제한다.
        System.out.println("강수량 데이터 삭제");
        System.out.println("도시명을 입력하세요 : ");
        String cityname = in.next();
        System.out.println("년월을 입력하세요(입력방식: yyyy-mm) : ");
        String yearmonth = in.next();
        int pos=-1;
        for (int i = 0; i < data.size() ; i++){
            if(data.get(i).getCity().equals(cityname)//등록된 데이터가 있을때
                    &&data.get(i).getYearmonth().equals(yearmonth)){
                pos = i;
            }//if
        }//for
        data.remove(pos);
        showAllData();
        System.out.println();
    }
}

