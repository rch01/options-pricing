package optionspricing.blackscholes;

final public class D1D2 {
    public static double d1(double f, double k, double t, double v) {
        return calculateD1(f, k, t, v);
    }

    public static double d2(double f, double k, double t, double v) {
        return calculateD2(f, k, t, v);
    }

    private static double calculateD1(double f, double k, double t, double v) {
        final double a = (Math.log(f / k));
        final double b = (Math.pow(v, 2) * (t / 2));
        final double c = (v * Math.sqrt(t));
        return (a + b) / c;
    }

    private static double calculateD2(double f, double k, double t, double v) {
        return calculateD1(f, k, t, v) - (v * Math.sqrt(t));
    }
}
