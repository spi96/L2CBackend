package hu.bme.aut.webshop.domain.enumeration;

public enum ProductCategory {
    WOMEN{
        @Override
        public String toString(){
            return "Women";
        }
    },
    MEN{
        @Override
        public String toString(){
            return "Men";
        }
    }
}
