package modelo;

public enum SymbolField {
    CIRCLE("O"),
    X("X");

    private final String symbol;
    SymbolField(String symbol) {
        this.symbol = symbol;
    }
    @Override
    public String toString() {
        return symbol;
    }
}
