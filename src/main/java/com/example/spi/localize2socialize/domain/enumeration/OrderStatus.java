package com.example.spi.localize2socialize.domain.enumeration;

/**
 * Created by Spi on 2018. 04. 07..
 */
public enum OrderStatus {
    PROCESSING{
        @Override
        public String toString(){
            return "Processing";
        }
    },
    DISPATCHED{
        @Override
        public String toString(){
            return "Dispatched";
        }
    },
    DELIVERED{
        @Override
        public String toString(){
            return "Delivered";
        }
    },
    CANCELLED{
        @Override
        public String toString(){
            return "Cancelled";
        }
    };

    public static OrderStatus fromString(String n){
        for(OrderStatus type: OrderStatus.values()){
            if(type.toString().equals(n)) return type;
        }
        return PROCESSING;
    }
}
