package beverage.coffee;

import beverage.Beverage;

public class HouseBlend extends Beverage {
    public HouseBlend() {
        this.setDescription("집 블렌드");
    }

    @Override
    public int cost() {
        return 5500;
    }
}
