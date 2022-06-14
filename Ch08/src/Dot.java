


//좌표상의 점을 정의 하는 데이터 클래스
public abstract class Dot { // 추상클래스 : 객체 생성을 할수가없음 자깃 클래스에게 상속하는 용도
    protected Point loc;
    protected int size;


    public Dot() {}

    public Dot(Point loc, int size) {
        super();
        this.loc = loc;
        this.size = size;
    }

    public Point getLoc() {
        return loc;
    }

    public void setLoc(Point loc) {
        this.loc = loc;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString(){
        return "Dot[loc =" + loc + ", size=" + size + "]";
    }
    //public void draw(){} // 자식클래스가 재정의 할거라서 내용을 작성하지 않음
    public abstract void draw();//absract는 추상메소드 무조건 자식이 재정의 해야함

}
