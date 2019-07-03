package checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private List<Offer> offers = new ArrayList<>();
    private Check check;

    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        for (Offer offer : offers) {
            offer.apply(check);
        }

        Check closedCheck = check;
        check = null;
        return closedCheck;
    }

    public void useOffer(Offer offer) {
        offers.add(offer);
    }
}
