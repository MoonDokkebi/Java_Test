//Nested Class 정의
public class UnitHandler {
    private  static  String from, to;

    //Inner class 정의
    private class LengthUnit{//길이변환 클래스
        public  void setUnits(String from,String to){
            UnitHandler.from=from; //내부 클래스에서 외부클래스의 속성에 접근할수있음
            UnitHandler.to = to;
        }
        public  double convert(double value){
            if(UnitHandler.from.equals("cm")) {
                if (UnitHandler.to.equals("m")) return value / 100;
                else if (UnitHandler.to.equals("inch")) return value / 2.54;
                else if (UnitHandler.to.equals("ft")) return value / (2.54 * 12);
            }
            return -1;

        }
    }

    //Static nested
    public static class WeightUnit{
        public  void setUnits(String from,String to){
            UnitHandler.from=from; //내부 클래스에서 외부클래스의 속성에 접근할수있음
            UnitHandler.to = to;
        }
        public  double convert(double value){
            if(UnitHandler.from.equals("g")) {
                if (UnitHandler.to.equals("kg")) return value / 1000;
                else if (UnitHandler.to.equals("ton")) return value / 1000000;
            }
            return -1;
        }
    }

    public double convert(String form, String to, double value){
        LengthUnit unit = new LengthUnit();
        unit.setUnits(from, to);
        return unit.convert(value);


        /*UnitHandler.from=form;
        UnitHandler.to = to;
        if(UnitHandler.from.equals("cm"))
            if(UnitHandler.to.equals("m"))return value /100;
            else if (UnitHandler.to.equals("inch")) return value/2.54;
            else if (UnitHandler.to.equals("ft")) return value/(2.54*12);

        return -1;*/
    }

}
