package optionspricing.blackscholes;

/**
 * An implementation of the Black-76 model for pricing European options on futures.
 * All formulas are from Chapter 17, 17.8, page 370 of Hull, C. J. 2011. Options, Futures, And Other Derivatives, 8th Edition.
 */

import org.apache.commons.math3.distribution.NormalDistribution;

public final class Black76 {
	
	/**
	 * This implementation of Black-76 takes in six variables, and returns the theoretical price of an option.
	 * @param optionType - call or put option.
	 * @param f - The current price of the underlying instrument, in the case of Black-76 this will be a futures contract.
	 * @param k - The exercise/strike price.
	 * @param t - The time to expiry/maturity.
	 * @param r - The annual continuously compounded risk-free interest rate.
	 * @param v - The annual volatility of the underlying price.
	 * @return option price.
	 */
	
	private static final NormalDistribution nd = new NormalDistribution();

	public static double optionPriceCall(double f, double k, double t, double r, double d1, double d2) {
		return Math.exp(-r * t) * ((f * nd.cumulativeProbability(d1)) - (k * nd.cumulativeProbability(d2)));
	}

	public static double optionPricePut(double f, double k, double t, double r, double d1, double d2) {
		return Math.exp(-r * t) * ((k * nd.cumulativeProbability(-d2)) - (f * nd.cumulativeProbability(-d1)));
	}
	
	private Black76() {}/** the constructor is private so no instances can be created. */
}
