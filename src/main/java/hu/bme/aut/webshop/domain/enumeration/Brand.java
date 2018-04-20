package hu.bme.aut.webshop.domain.enumeration;

public enum Brand {
    NIKE{
        @Override
        public String toString(){
            return "Nike";
        }
    },
    ADIDAS{
        @Override
        public String toString(){
            return "Adidas";
        }
    },
    UNDERARMOUR{
        @Override
        public String toString(){
            return "Under Armour";
        }
    },
    LEVIS{
        @Override
        public String toString(){
            return "Levi's";
        }
    }
}
