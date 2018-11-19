package com.example.spi.localize2socialize.domain.enumeration;

public enum Brand {
    NIKE("Nike"),
    ADIDAS("Adidas"),
    UNDERARMOUR("Under Armour"),
    LEVIS("Levi's");

    private String name;

    public String getName(){return name;}

    private Brand(String name){this.name = name;}

    public static Brand fromString(String n){
        for(Brand type: Brand.values()){
            if(type.toString().equals(n)) return type;
        }
        return NIKE;
    }
}
