package checkout.conditions;

import checkout.Check;

public class TotalCost implements Condition {

    private int totalCost;

    public TotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean checkCondition(Check check) {
        return this.totalCost <= check.getTotalCost();
    }
}
