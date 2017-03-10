package optionspricing.blackscholes;

import java.text.DecimalFormat;

/** Try to create an immutable class as per the definition */

public final class Option {
	final private char optionType;
	final private double priceUnderlying;
	final private double priceExercise;
	final private double timeToExpiry;
	final private double interestRate;
	final private double volatility;
	
	final private DecimalFormat df = new DecimalFormat("#,###.####");

	private double d1;
	private double d2;

	public Option(char oType, double u, double k, double t, double r, double v) {
		this.optionType = oType;
		this.priceUnderlying = u;
		this.priceExercise = k;
		this.timeToExpiry = t;
		this.interestRate = r;
		this.volatility = v;
	}

	public void price() {
		this.calculateD1D2();
		final double price = (this.optionType == 'c') ? getOptionPriceCall() : getOptionPricePut();
		System.out.println("\nOption price: " + ((price == -1.0) ? "cannot calculate option price." : df.format(price)));
	}
	
	public void getOptionGreeks() {
		final double delta = Greeks.delta(this.optionType, this.interestRate, this.timeToExpiry, d1);
		final double gamma = Greeks.gamma(this.priceUnderlying, this.interestRate, this.timeToExpiry, this.volatility, d1);
		final double theta = Greeks.theta(this.optionType, this.priceUnderlying, this.priceExercise, this.interestRate, this.timeToExpiry, this.volatility, d1, d2);
		System.out.println("Delta: " + df.format(delta));
		System.out.println("Theta: " + df.format(theta) + " (per calendar day)");
		System.out.println("Gamma: " + df.format(gamma));
	}

	public void getOptionProperties() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "[optionType=" + optionType + ", futuresPrice=" + priceUnderlying + ", exercisePrice="
				+ priceExercise + ", timeToExpiry=" + timeToExpiry + ", interestRate=" + interestRate
				+ ", volatility=" + volatility + "]";
	}

	private void calculateD1D2() {
		d1 = D1D2.d1(this.priceUnderlying, this.priceExercise, this.timeToExpiry, this.volatility);
		d2 = D1D2.d2(this.priceUnderlying, this.priceExercise, this.timeToExpiry, this.volatility);
	}

	private double getOptionPriceCall() {
		return Black76.optionPriceCall(this.priceUnderlying, this.priceExercise, this.timeToExpiry, this.interestRate, this.d1, this.d2);
	}

	private double getOptionPricePut() {
		return Black76.optionPricePut(this.priceUnderlying, this.priceExercise, this.timeToExpiry, this.interestRate, this.d1, this.d2);
	}
}
