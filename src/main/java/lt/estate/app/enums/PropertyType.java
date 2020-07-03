package lt.estate.app.enums;

public enum  PropertyType {
    APARTMENT(2),
    HOUSE(7),
    INDUSTRIAL(10);

    public final double taxRate;

    PropertyType( double taxRate) {
        this.taxRate = taxRate;
    }

}
