package main;

import beverage.Beverage;
import beverage.coffee.DarkRoast;
import beverage.coffee.Expresso;
import decorator.condiment.Milk;
import decorator.condiment.Mocha;
import decorator.condiment.Soy;
import decorator.condiment.Whip;

public class MiraeassetCoffeeShop {
    public static void main(String[] args) {
        Beverage beverage1 = new Expresso();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Soy(beverage1);
        beverage1 = new Whip(beverage1);

        System.out.println(beverage1.getDescription() + " ===> " + beverage1.cost() + "원");

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Milk(beverage2);
        beverage2 = new Milk(beverage2);
        beverage2 = new Milk(beverage2);
        beverage2 = new Soy(beverage2);
        beverage2 = new Whip(beverage2);

        System.out.println(beverage2.getDescription() + " ===> " + beverage2.cost() + "원");
    }
}
