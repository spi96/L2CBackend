package hu.bme.aut.webshop.domain.enumeration;

public enum ProductSize {
    ONESIZE{
        @Override
        public String toString(){
            return "One size";
        }
    },
    S, M, L, XL
}
