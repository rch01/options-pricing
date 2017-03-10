package optionspricing.blackscholes;

public final class Option {
	final private char optionType;
	final private double priceUnderlying;
	final private double priceExercise;
	final private double timeToExpiry;
	final private double interestRate;
	final private double volatility;
	final private double d1;
	final private double d2;

	public Option(char oType, double u, double k, double t, double r, double v) {
		this.optionType = oType;
		this.priceUnderlying = u;
		this.priceExercise = k;
		this.timeToExpiry = t;
		this.interestRate = r;
		this.volatility = v;
		this.d1 = D1D2.d1(this.priceUnderlying, this.priceExercise, this.timeToExpiry, this.volatility);
		this.d2 = D1D2.d2(this.priceUnderlying, this.priceExercise, this.timeToExpiry, this.volatility);
	}

	public double price() {
		return (this.optionType == 'c') ? optionPriceCall() : optionPricePut();
	}
	
	public double optionDelta() {
		return Greeks.delta(this.optionType, this.interestRate, this.timeToExpiry, d1);
	}

	public double optionGamma() {
		return Greeks.gamma(this.priceUnderlying, this.interestRate, this.timeToExpiry, this.volatility, d1);
	}

	public double optionTheta() {
		return Greeks.theta(this.optionType, this.priceUnderlying, this.priceExercise, this.interestRate, this.timeToExpiry, this.volatility, d1, d2);
	}

	@Override
	public String toString() {
		return "[optionType=" + optionType + ", futuresPrice=" + priceUnderlying + ", exercisePrice="
				+ priceExercise + ", timeToExpiry=" + timeToExpiry + ", interestRate=" + interestRate
				+ ", volatility=" + volatility + "]";
	}

	private double optionPriceCall() {
		return Black76.optionPriceCall(this.priceUnderlying, this.priceExercise, this.timeToExpiry, this.interestRate, this.d1, this.d2);
	}

	private double optionPricePut() {
		return Black76.optionPricePut(this.priceUnderlying, this.priceExercise, this.timeToExpiry, this.interestRate, this.d1, this.d2);
	}
}
