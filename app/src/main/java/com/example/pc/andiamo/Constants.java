package com.example.pc.andiamo;


class Constants {
    public static final int SPLASH_TIME_OUT = 2500;
    public static final String WEB_SERVICE_URL = "ec2-34-239-146-206.compute-1.amazonaws.com:8003/webservice/";
    public static final String AUTHORIZATION_HEADER = "ZNLhfFrapAOTqjcWrseVne4PBfrHkcYG";

    public static final String LOGIN_EP = WEB_SERVICE_URL + "login";
    public static final String REGISTER_EP = WEB_SERVICE_URL + "register";
    public static final String STORES_EP = WEB_SERVICE_URL + "get_stores";
    public static final String PLACE_ORDER_EP = WEB_SERVICE_URL + "place_orders";
    public static final String GET_ORDER_EP = WEB_SERVICE_URL + "get_order";


    // Menu Item Prices
    public enum MenuItem{
        // pizza
        YBP("Your Basic Pie", "Zesty Marinara Sauce, Mozzarella & Parmesian",10.99f),
        BP("Bruschetta Pizza", "Thick-crust, Rich Tomato Sauce, Bruschetta, Oil & Vinegar, Parmesian", 10.99f),
        PEP("Pepperoni", "Zesty Marinara, Pepperoni, Mozzarella & Parmesian", 12.99f),
        GW("Green and White","Classic White Sauce, Roasted Spinach & Artichokes, Basil, Mozzarella & Parmesian", 11.99f),
        MM("Mostly Meat", "Zesty Marinara, Pulled Chicken, Sausage, Pepperoni, Crumbled Bacon, Mozzarella & Chedder", 15.99f),
        VS("Veggie Supreme", "Zesty Marinara, Roasted Peppers, Sun-dried Tomatoes, Olives, Onions, Basil, Mozzarella", 11.99f),
        MARP("Margherita", "Zesty Marinara, Fresh Tomatoes, Basil, Olive Oil, Salt & Pepper, Fresh Mozzarella", 12.99f),
        SPHW("Spicy Hawaiian", "Sweet & Spicy BBQ Marinara, Bacon, Ham, Fresh Pineapple, Mozzarella & Cheddar", 14.99f),
        // subs and sandwiches
        IS("Italian Sub", "Salami, Ham, Mortadella, Provolone, Tomato, Arugula, Onions, Oil & Vinegar on a Baguette", 10.99f),
        IC("The Italian Club", "Roast Beef, Ham, Salami, Mozzarella, Provolone, Tomato, Salt & Pepper on our Touscany Toast", 10.99f),
        BOW("Beed on Wick", "Marinated Brisket, Sauteed Onions, Au Jus, on Wick (Roll dusted in sea salt and caraway seeds", 12.99f),
        BLT("Bacon, Lettuce, and Tomato", "Bacon, Lettuce, Tomato, Pesto Mayo on our Touscany Toast", 11.99f),
        MARS("Margherita Sandwich", "Mozzarella, Fresh Tomatoes, Marinara, Pesto, Spinach, Basil on a Baguette", 15.99f),
        PCS("Philly Cheesesteak", "Steak, Sauteed Onions, Provolone, Red and Yellow Peppers on a Baguette", 11.99f),
        CPS("Chicken Pesto Sub", "Parmesean Crusted Chicken Breat, Provolone, Arugula, Tomato, Pesto on our Tuscany Toast", 12.99f),
        TFR("The Fat Reuben", "Half a Pound Corned Beef, Sauerkraut, Thousand Island on our Tuscany Toast", 14.99f)
        ;

        private String name;
        private String desc;
        private float price;

        MenuItem(String n, String d, float p){
            name = n;
            desc = d;
            price = p;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public float getPrice() {
            return price;
        }
    }

    public static final float CUPCAKE_PRICE = 3.99f;
    public static final float DRINK_PRICE = 2.99f;
    public static final float BROWNIE_PRICE = 2.99f;
    public static final float COOKIE_PRICE = 2.99f;
    // Menu Item Prices
}
