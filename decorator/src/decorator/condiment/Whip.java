package decorator.condiment;

import beverage.Beverage;
import decorator.CondimentDecorator;

public class Whip extends CondimentDecorator {
    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , 휘핑";
    }

    @Override
    public int cost() {
        return beverage.cost() + 800;
    }
}
