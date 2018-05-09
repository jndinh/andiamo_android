package com.example.pc.andiamo;


public class Constants {
    public static final int SPLASH_TIME_OUT = 2500;
    public static final String WEB_SERVICE_URL = "http://ec2-34-239-146-206.compute-1.amazonaws.com:8003/webservice/";
    public static final String AUTHORIZATION_HEADER = "ZNLhfFrapAOTqjcWrseVne4PBfrHkcYG";

    public static final String LOGIN_EP = WEB_SERVICE_URL + "login";
    public static final String REGISTER_EP = WEB_SERVICE_URL + "register";
    public static final String STORES_EP = WEB_SERVICE_URL + "get_stores";
    public static final String PLACE_ORDER_EP = WEB_SERVICE_URL + "place_orders";
    public static final String GET_ORDER_EP = WEB_SERVICE_URL + "get_order";

    public static final int SUB_OFFSET = 8;
    public static final int DES_OFFSET = 16;

    // Menu Item Prices
    public enum MenuItem{
        // pizza
        YBP("Your Basic Pie", "Zesty Marinara Sauce, Mozzarella & Parmesian",10.99f, 0),
        BP("Bruschetta Pizza", "Thick-crust, Rich Tomato Sauce, Bruschetta, Oil & Vinegar, Parmesian", 10.99f, 1),
        PEP("Pepperoni", "Zesty Marinara, Pepperoni, Mozzarella & Parmesian", 12.99f, 2),
        GW("Green and White","Classic White Sauce, Roasted Spinach & Artichokes, Basil, Mozzarella & Parmesian", 11.99f, 3),
        MM("Mostly Meat", "Zesty Marinara, Pulled Chicken, Sausage, Pepperoni, Crumbled Bacon, Mozzarella & Chedder", 15.99f, 4),
        VS("Veggie Supreme", "Zesty Marinara, Roasted Peppers, Sun-dried Tomatoes, Olives, Onions, Basil, Mozzarella", 11.99f, 5),
        MARP("Margherita", "Zesty Marinara, Fresh Tomatoes, Basil, Olive Oil, Salt & Pepper, Fresh Mozzarella", 12.99f, 6),
        SPHW("Spicy Hawaiian", "Sweet & Spicy BBQ Marinara, Bacon, Ham, Fresh Pineapple, Mozzarella & Cheddar", 14.99f, 7),
        // subs and sandwiches
        IS("Italian Sub", "Salami, Ham, Mortadella, Provolone, Tomato, Arugula, Onions, Oil & Vinegar on a Baguette", 10.99f, 8),
        IC("The Italian Club", "Roast Beef, Ham, Salami, Mozzarella, Provolone, Tomato, Salt & Pepper on our Touscany Toast", 10.99f, 9),
        BOW("Beed on Wick", "Marinated Brisket, Sauteed Onions, Au Jus, on Wick (Roll dusted in sea salt and caraway seeds", 12.99f, 10),
        BLT("Bacon, Lettuce, and Tomato", "Bacon, Lettuce, Tomato, Pesto Mayo on our Touscany Toast", 11.99f, 11),
        MARS("Margherita Sandwich", "Mozzarella, Fresh Tomatoes, Marinara, Pesto, Spinach, Basil on a Baguette", 15.99f, 12),
        PCS("Philly Cheesesteak", "Steak, Sauteed Onions, Provolone, Red and Yellow Peppers on a Baguette", 11.99f, 13),
        CPS("Chicken Pesto Sub", "Parmesean Crusted Chicken Breat, Provolone, Arugula, Tomato, Pesto on our Tuscany Toast", 12.99f, 14),
        TFR("The Fat Reuben", "Half a Pound Corned Beef, Sauerkraut, Thousand Island on our Tuscany Toast", 14.99f, 15),
        // drinks and deserts
        RV("Red Velvet", "", 3.99f, 16),
        VAN("Vanilla", "", 3.99f, 17),
        CHOC("Chocolate", "", 3.99f, 18),
        KL("Key Lime", "", 3.99f, 19),
        CAC("Cookies and Cream", "", 3.99f, 20),
        CC("Chocolate Chip", "", 2.99f, 21),
        OR("Oatmeal Raisin", "", 2.99f, 22),
        SCC("Super Chocolate Chunk", "", 2.99f, 23),
        COKE("Coke", "", 2.99f, 24),
        DCOKE("Diet Coke", "", 2.99f, 25),
        SPT("Sprite", "", 2.99f, 26),
        GIN("Gingerale", "", 2.99f, 27),
        LEM("Lemonade", "", 2.99f, 28);

        private String name;
        private String desc;
        private float price;
        private int id;

        MenuItem(String n, String d, float p, int i){
            name = n;
            desc = d;
            price = p;
            id = i;
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
}
