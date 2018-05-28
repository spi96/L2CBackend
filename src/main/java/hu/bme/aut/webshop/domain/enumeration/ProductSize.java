package hu.bme.aut.webshop.domain.enumeration;

public enum ProductSize {
    ONESIZE("One size"),
    S("S"), M("M"), L("L"), XL("XL");

    private String name;

    public String getName(){return name;}

    private ProductSize(String name){this.name = name;}

    public static ProductSize fromString(String n){
        for(ProductSize type: ProductSize.values()){
            if(type.toString().equals(n)) return type;
        }
        return ONESIZE;
    }
}
