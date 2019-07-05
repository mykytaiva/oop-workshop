package checkout.conditions;

import checkout.Check;

public interface Condition {
    boolean checkCondition(Check check);
}
