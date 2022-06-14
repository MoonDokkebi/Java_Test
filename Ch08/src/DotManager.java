

public class DotManager {
    public static void main(String[] args) {
        PatternDot pdot = new PatternDot(new Point(1,3), 5 , "물결 무늬");
        System.out.println(pdot);
        ColorDot cdot = new ColorDot(new Point(3,3), 8, "Red");
        System.out.println(cdot);

        PatternDot[] pdots = new PatternDot[10];
        ColorDot[] cdots = new ColorDot[10];
        int cnt1 =0, cnt2 =0;

        Dot dot = pdot;// 부모클래스 객체변수가 자식클래스 객체를 가리킬수 있음
        Dot[] dots = new Dot[10];
        dots[0] = pdot;
        dots[1] = cdot;
        dots[2] = new ColorDot(new Point(4,5),3,"Blue");
        dots[3] = new PatternDot(new Point(2,3),7,"점무늬");
        dots[4] = new ColorDot(new Point(-2,5),8,"Orange");




        for(int i=0; i<5; i++){
            System.out.println(dots[i]);

        }
        /*Dot dor2 =pdot; // 업캐스팅 PatternDot -> 닷 변환
        dot2.setLoc(new Point(5,9));//Dot 클래스에 정의된 베소드를 조정할수있음 setLoc 메소드는 pdot 객체 내부에 들어있는 dot 객체
        pdot.draw();
        //dot2.setPattern("빗살무늬");//dot2객체 변수가 가리키는 객체는 PatternDot 객체이지만 dot2의 타입이 Dot 이라서 Dot에 정의된 메소드만 실행
        ((PatternDot)dot2).setPattern("빗살무늬");//((PatternDot)dot2) 이부분이 다운캐스팅
        dot2.draw();

        String data;
        for(Dot mydot : dots){// 배열이 10개인 크기인데 5개만 들어간다면 nullpointexeption 발생
            if(dot instanceof ColorDot){
                data = "black";
                ((ColorDot)mydot).setColor(data);
            }
            else if(mydot instanceof PatternDot){
                data = "꽃무늬";
                ((PatternDot)mydot).setPatern(data);
            }
            mydot.draw(); // draw() 메소드가 오버라이딩 되어 있어서 실제 가리키는 객체가
            //Dot dotObject
        }*/
    }
}
