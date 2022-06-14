public class ColorDot extends Dot {
    private String color;

    public ColorDot(){}
    public ColorDot(Point loc, int size, String color){
        super(loc,size);
        this.color = color;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }

    @Override
    public String toString(){
        return "ColorDot [ location ="+ loc +"size="+size+ "color="+color+"]";
    }
    @Override
    public void draw(){     //Dot 클래스의 draw()를 재정의
        //super.draw();
        System.out.println("ColorDot : " + color);
        System.out.println("위치 :" +loc);
        System.out.println();
    }
}
