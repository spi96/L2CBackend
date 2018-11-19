package com.example.spi.localize2socialize.domain.enumeration;

public enum ProductType {
    EQUIPMENTS("Equipments"),
    HATS("Hats"),
    GLASSES("Glasses"),
    SHIRTS("Shirts"),
    JEANS("Jeans");

    private String name;

    public String getName(){return name;}

    private ProductType(String name){this.name = name;}

    public static ProductType fromString(String n){
        for(ProductType type: ProductType.values()){
            if(type.toString().equals(n)) return type;
        }
        return EQUIPMENTS;
    }
}
