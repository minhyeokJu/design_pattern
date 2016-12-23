package beverage.coffee;

import beverage.Beverage;

public class DarkRoast extends Beverage {
    public DarkRoast() {
        this.setDescription("다크 로스트");
    }

    @Override
    public int cost() {
        return 3500;
    }
}
