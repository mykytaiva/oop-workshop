package checkout;

public class FlatReward implements Reward {

    private int points;

    public FlatReward(int points) {
        this.points = points;
    }

    @Override
    public int getReward(Check check) {
        return points;
    }
}
