package checkout.offers.bonus;

import checkout.Check;
import checkout.conditions.Condition;
import checkout.offers.Offer;

import java.time.LocalDate;

public class BonusOffer extends Offer {
    private Reward reward;

    public BonusOffer(LocalDate expiryDate, Condition condition, Reward reward) {
        super(expiryDate, condition);
        this.reward = reward;
    }

    @Override
    public void apply(Check check) {
        check.addPoints(reward.getReward(check));
    }

    @Override
    public boolean isValid(Check check) {
        return this.condition.checkCondition(check);
    }
}
