

//RainData 클래스는 강수량 데이터를 정의하는 데이터 클래스로서
// 도시코드, 도시명, 년월, 강수량을 속성으로 가진다.
public class RainData {

    private  String yearmonth;
    private  String city;
    private  String rain;
    private String citycode;


    public RainData(String yearmonth,String city, String citycode, String rain){
        super();
        this.yearmonth=yearmonth;
        this.city=city;
        this.citycode = citycode;
        this.rain=rain;

    }

    public String getYearmonth() {
        return yearmonth;
    }

    public RainData setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
        return this;
    }

    public String getCity() {
        return city;
    }

    public RainData setCity(String city) {
        this.city = city;
        return this;
    }

    public String getRain() {
        return rain;
    }

    public RainData setRain(String rain) {
        this.rain = rain;
        return this;
    }

    public String getCitycode() {
        return citycode;
    }

    public RainData setCitycode(String citycode) {
        this.citycode = citycode;
        return this;
    }

    public void printInfo() {
        System.out.println("Date : "+ yearmonth + ", City : " + city +", Citycode : "+ citycode +", rain : "+ rain);
    }


}