//좌표상의 무늬점을 정의하는 데이터 클래스

public class PatternDot extends Dot{
    private String pattern;
    public PatternDot(){
        super();
    }
    public PatternDot(Point loc, int size){
        super(loc, size);//부모생성자를 호출하면서 매개변수를 전달 Dot(Point, int)
    }
    public PatternDot(String pattern){
        super();
        this.pattern = pattern;
    }
    public PatternDot(Point loc, int size, String pattern){//부모객체 생성을 위한 매개변수와 자신을 초기할 매개변수
        super(loc, size);
        this.pattern = pattern;
    }
    public String getPattern(){
        return pattern;
    }
    public void setPattern(String pattern){
        this.pattern = pattern;
    }
    @Override
    public String toString(){
        return "PatternDot[loc =" + loc + ", size=" + size + "pattern="+ pattern +"]";
    }

    @Override
    public void draw() {

    }

}
