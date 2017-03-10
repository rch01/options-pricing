package optionspricing.blackscholes;

/**
 * Implementation of the Greek letters, which are used to measure different dimensions of the risk in an option position.
 * All formulas are from Chapter 18, Table 18.6, page 398 of Hull, C. J. 2011. Options, Futures, And Other Derivatives, 8th Edition.
 */

import org.apache.commons.math3.distribution.NormalDistribution;

public final class Greeks {
	
	/**
	 * @param optionType - call or put option.
	 * @param f - The current price of the underlying instrument.
	 * @param k - The exercise/strike price.
	 * @param t - The time to expiry/maturity.
	 * @param r - The risk-free interest rate per annum, continuously compounded.
	 * @param v - The volatility of the underlying price.
	 * @param d1
	 * @param d2
	 */
	
	private static final NormalDistribution nd = new NormalDistribution();
	
	/** Delta - rate of change of the option price with respect to the price of the underlying asset. */
	public static double delta(char optionType, double r, double t, double d1) {
		return (optionType == 'c') ? Math.exp(-r * t) * nd.cumulativeProbability(d1) : Math.exp(-r * t) * (nd.cumulativeProbability(d1) - 1);
	}
	
	/** Theta - rate of change of the value of the portfolio with respect to the passage of time, with all else remaining the same (time decay of the portfolio). 
	 * Theta is quoted per day - This implementation returns Theta per calendar day - To obtain per trading day divide by 252. */
	public static double theta(char optionType, double f, double k, double r, double t, double v, double d1, double d2) {
		final double a = -(f * nd.density(d1) * v * Math.exp(-r * t));
		final double b = 2 * Math.sqrt(t);
		final double c = r * f * nd.cumulativeProbability((optionType == 'c') ? d1 : -d1) * Math.exp(-r * t);
		final double d = r * k * Math.exp(-r * t) * nd.cumulativeProbability((optionType == 'c') ? d2 : -d2);
		return (optionType == 'c') ? ((a / b) + c - d) / 365 : ((a / b) - c + d) / 365;
	}
	
	/** Gamma - rate of change of the portfolio's delta with respect to the price of the underlying asset. It is the same for call and put options. */
	public static double gamma(double f, double r, double t, double v, double d1) {
		final double a = nd.density(d1) * Math.exp(-r * t);
		final double b = f * v * Math.sqrt(t);
		return a / b;
	}
	
	private Greeks() {}/** private constructor so no instances can be created. */
}
