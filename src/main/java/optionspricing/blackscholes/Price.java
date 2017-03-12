package optionspricing.blackscholes;

final public class Price {
    final private Derivative d;
    final private double d1;
    final private double d2;

    Price(Derivative d) {
        this.d = d;
        this.d1 = D1D2.d1(d.underlying(), d.exercise(), d.time(), d.volatility());
        this.d2 = D1D2.d2(d.underlying(), d.exercise(), d.time(), d.volatility());
    }

    public double price() {
        return (d.optionType() == 'c') ? call() : put();
    }

    public double delta() {
        return Greeks.delta(d.optionType(), d.rate(), d.time(), d1);
    }

    public double gamma() {
        return Greeks.gamma(d.underlying(), d.rate(), d.time(), d.volatility(), d1);
    }

    public double theta() {
        return Greeks.theta(d.optionType(), d.underlying(), d.exercise(), d.rate(), d.time(), d.volatility(), d1, d2);
    }

    private double call() {
        return Black76.optionPriceCall(d.underlying(), d.exercise(), d.time(), d.rate(), d1, d2);
    }

    private double put() {
        return Black76.optionPricePut(d.underlying(), d.exercise(), d.time(), d.rate(), d1, d2);
    }
}
