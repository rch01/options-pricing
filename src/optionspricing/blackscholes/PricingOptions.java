package optionspricing.blackscholes;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class PricingOptions {
	public static void main(String [] args) {
		System.out.println("Black76 option pricing model example:");

		Option ex = new Option('c', 55.04, 50.5, 0.25, 0.0100883674, 0.12);//3-month dollar LIBOR annual interest rate, converted to continuously compounded using equation 4.3 p79 of Hull Options, Futures, And Other Derivatives 8th Ed.
		ex.price();
		ex.getOptionGreeks();
		ex.getOptionProperties();

		System.out.print("\nEnter an integer to price another option or any character to exit: ");
		Scanner sc = new Scanner(System.in);
		final boolean next = sc.hasNextInt();
		sc.nextLine();
		if (next) {
			Map<String, String> optionProps = setOptionProperties(sc);

			final char oType = optionProps.get("oType").charAt(0);
			final double u = Double.parseDouble(optionProps.get("u"));
			final double k = Double.parseDouble(optionProps.get("k"));
			final double t = Double.parseDouble(optionProps.get("t"));
			final double r = Double.parseDouble(optionProps.get("r"));
			final double v = Double.parseDouble(optionProps.get("v"));

			Option o = new Option(oType, u, k, t, r, v);
			o.price();
			o.getOptionGreeks();
			o.getOptionProperties();
		} else {
			sc.close();
		}
	}

	private static Map<String, String> setOptionProperties(Scanner sc) {
		Map<String, String> oProps = new HashMap<>();
		OptionState s = OptionState.OPTION_TYPE;

		while (s != OptionState.DONE) {
			switch (s) {
				case OPTION_TYPE:
					System.out.print("\nOption type (enter 'c' for Call or 'p' for Put): ");
					char oType = Character.toLowerCase(sc.next().charAt(0));
					if (oType == 'c' || oType == 'p') {
						s = OptionState.UNDERLYING_PRICE;
						oProps.put("oType", Character.toString(oType));
					} else {
						System.out.println("Invalid option type.");
						sc.nextLine();// clears the buffer
					}
					break;
				case UNDERLYING_PRICE:
					System.out.print("Underlying futures contract price: ");
					try {
						double u = sc.nextDouble();
						s = OptionState.EXERCISE_PRICE;
						oProps.put("u", Double.toString(u));
					} catch (InputMismatchException e) {
						System.out.println("Invalid underlying price.");
						sc.nextLine();
					}
					break;
				case EXERCISE_PRICE:
					System.out.print("Option exercise price: ");
					try {
						double k = sc.nextDouble();
						s = OptionState.TIME;
						oProps.put("k", Double.toString(k));
					} catch (InputMismatchException e) {
						System.out.println("Invalid exercise price.");
						sc.nextLine();
					}
					break;
				case TIME:
					System.out.print("Time to expiry in years (as a fraction e.g. 6/12): ");
					String input = sc.next();
					try {
						double a = Double.parseDouble(input.split("/")[0]);
						double b = Double.parseDouble(input.split("/")[1]);
						if (b == 0) {
							System.out.println("Invalid time to expiry.");
							sc.nextLine();
						}
						else {
							s = OptionState.INTEREST_RATE;
							oProps.put("t", Double.toString(a / b));
						}
					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						System.out.println("Invalid time to expiry.");
						sc.nextLine();
					}
					break;
				case INTEREST_RATE:
					System.out.print("Annual continuously compounded risk-free interest rate (e.g. for 5% please enter 5): ");
					try {
						double r = ((double) sc.nextInt()) / 100;
						s = OptionState.VOLATILITY;
						oProps.put("r", Double.toString(r));
					} catch (InputMismatchException e) {
						System.out.println("Invalid interest rate.");
						sc.nextLine();
					}
					break;
				case VOLATILITY:
					System.out.print("Annual volatility (e.g. for 20% please enter 20): ");
					try {
						double v = ((double) sc.nextInt()) / 100;
						s = OptionState.DONE;
						oProps.put("v", Double.toString(v));
					} catch (InputMismatchException e) {
						System.out.println("Invalid volatility.");
						sc.nextLine();
					}
					break;
			}
		}
		sc.close();
		return oProps;
	}

	private enum OptionState {
			OPTION_TYPE
		,	UNDERLYING_PRICE
		,	EXERCISE_PRICE
		,	TIME
		,	INTEREST_RATE
		,	VOLATILITY
		,	DONE;
	}

}