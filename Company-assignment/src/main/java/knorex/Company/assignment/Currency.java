package knorex.Company.assignment;


public enum Currency {
    INR("₹"),
    USD("$"),
    EUR("€");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}