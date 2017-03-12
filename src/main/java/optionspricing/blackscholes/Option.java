package optionspricing.blackscholes;

final public class Option implements Derivative {
	final private char optionType;
	final private double priceUnderlying;
	final private double priceExercise;
	final private double timeToExpiry;
	final private double interestRate;
	final private double volatility;

	public Option(char oType, double u, double k, double t, double r, double v) {
		this.optionType = oType;
		this.priceUnderlying = u;
		this.priceExercise = k;
		this.timeToExpiry = t;
		this.interestRate = r;
		this.volatility = v;
	}

	@Override
	public String toString() {
		return "[optionType=" + optionType + ", futuresPrice=" + priceUnderlying + ", exercisePrice="
				+ priceExercise + ", timeToExpiry=" + timeToExpiry + ", interestRate=" + interestRate
				+ ", volatility=" + volatility + "]";
	}

	@Override
	public char optionType() {
		return this.optionType;
	}

	@Override
	public double underlying() {
		return this.priceUnderlying;
	}

	@Override
	public double exercise() {
		return this.priceExercise;
	}

	@Override
	public double time() {
		return this.timeToExpiry;
	}

	@Override
	public double volatility() {
		return this.volatility;
	}

	@Override
	public double rate() {
		return this.interestRate;
	}
}
