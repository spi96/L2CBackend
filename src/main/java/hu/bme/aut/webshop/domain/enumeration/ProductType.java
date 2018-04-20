package hu.bme.aut.webshop.domain.enumeration;

public enum ProductType {
    EQUIPMENTS{
        @Override
        public String toString(){
            return "Equipments";
        }
    },
    HATS{
        @Override
        public String toString(){
            return "Hats";
        }
    },
    GLASSES{
        @Override
        public String toString(){
            return "Glasses";
        }
    },
    SHIRTS{
        @Override
        public String toString(){
            return "Shirts";
        }
    },
    JEANS{
        @Override
        public String toString(){
            return "Jeans";
        }
    }
}
