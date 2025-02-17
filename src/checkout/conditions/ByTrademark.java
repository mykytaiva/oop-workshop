package checkout.conditions;

import checkout.Check;
import checkout.Trademark;

public class ByTrademark implements Condition {
    private Trademark trademark;
    private int requiredAmount;

    public ByTrademark(int requiredAmount, Trademark trademark) {
        this.requiredAmount = requiredAmount;
        this.trademark = trademark;
    }

    @Override
    public boolean checkCondition(Check check) {
        return check.getSubCost(p -> p.getProductTrademark() == this.trademark) >= requiredAmount;
    }
}
