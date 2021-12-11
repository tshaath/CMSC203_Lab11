package sample;

public class Function3 extends Function{

    @Override
    public String answerString(double optVal, double x, double y, double z) {

        String str= "Absolute Minumum: " + optVal + " between (" + x + "," +
                y + "). and (" + -x + "," + y + ").";
    return str;
    }

    public String toString() {
        String str= "Absolute minimum between the function of y = x^2 and a point (0,1).";
    return str;
    }

    @Override
    public double fnValue(double x) {
        return Math.sqrt(Math.pow(x, 4) - Math.pow(x, 2) + 1);
    }

    @Override
    public double getXVal(double x) {
        return x;
    }

    @Override
    public double getYVal(double x) {
        return Math.pow(x, 2);
    }

    @Override
    public double getZVal(double x) {
        return -1;
    }

}