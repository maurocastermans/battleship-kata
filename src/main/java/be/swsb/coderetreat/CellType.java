package be.swsb.coderetreat;

public enum CellType {
    WATER("🌊"),
    SHIP("🛳️"),
    MISSED("💧"),
    DAMAGED("💥"),
    SUNKEN("🏊");

    private final String symbol;

    CellType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
