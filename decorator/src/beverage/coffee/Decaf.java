package beverage.coffee;

import beverage.Beverage;

public class Decaf extends Beverage {
    public Decaf() {
        this.setDescription("카페인 없는 커피");
    }

    @Override
    public int cost() {
        return 3000;
    }
}
