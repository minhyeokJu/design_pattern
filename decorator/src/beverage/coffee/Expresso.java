package beverage.coffee;

import beverage.Beverage;

public class Expresso extends Beverage {
    public Expresso() {
        this.setDescription("에스프레소");
    }

    @Override
    public int cost() {
        return 4000;
    }
}
