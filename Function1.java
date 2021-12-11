package sample;


public class Function1 extends Function{


    @Override
    public String answerString(double optVal, double x, double y, double z) {

        String str="Minimum Cost= " + optVal + "\nHeight= " + x + "\nRadius= " + y;
    return str;
    }
    @Override
    public double fnValue(double x) {
        if(0 <= x) {
            return(0.8 * Math.PI * Math.pow(x, 2)) + (800/x);
        }
        else {
            return Double.MAX_VALUE;
        }
    }


    @Override
    public double getXVal(double x) {
        return x;
    }

    @Override
    public double getYVal(double x) {
        return 2000/(Math.PI * Math.pow(x, 2));
    }

    @Override
    public double getZVal(double x) {
        return -1;
    }



    public String toString() {
        String str="Minimize the cost of a can that will hold 2 liters of liquid";
    return str;
    }





}