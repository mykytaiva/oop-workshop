package checkout;

import java.util.function.Predicate;

public class FactorReward implements Reward {

    private Predicate<Product> predicate;
    private int factor;

    public FactorReward(Predicate<Product> predicate, int factor) {
        this.predicate = predicate;
        this.factor = factor;
    }

    @Override
    public int getReward(Check check) {
        return check.getSubCost(predicate) * (this.factor - 1);
    }
}
