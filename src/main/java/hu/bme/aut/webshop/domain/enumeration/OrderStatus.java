package hu.bme.aut.webshop.domain.enumeration;

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
    }
}
