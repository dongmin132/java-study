package org.example.customer;

public class Cooking {

    private MenuItem menuItem;
    public Cook makeCook(MenuItem menuItem) {
        Cook cook = new Cook(menuItem);
        return cook;
    }
}
