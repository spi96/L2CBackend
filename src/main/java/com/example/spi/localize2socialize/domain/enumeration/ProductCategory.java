package com.example.spi.localize2socialize.domain.enumeration;

public enum ProductCategory {
    WOMEN("Women"),
    MEN("Men");

    private String name;

    public String getName(){return name;}

    private ProductCategory(String name){this.name = name;}

    public static ProductCategory fromString(String n){
        for(ProductCategory type: ProductCategory.values()){
            if(type.toString().equals(n)) return type;
        }
        return WOMEN;
    }
}
