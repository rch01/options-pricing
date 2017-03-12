package optionspricing.blackscholes;

public interface Derivative {
    char optionType();
    double underlying();
    double exercise();
    double time();
    double volatility();
    double rate();
}