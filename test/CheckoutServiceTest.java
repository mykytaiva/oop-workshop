import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;
    private LocalDate testDate;
    private Product milk_7;
    private Product bred_3;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        testDate = LocalDate.now().plusDays(1);
        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.YAGOTUNSKE);
        bred_3 = new Product(3, "Bred");
    }

    @Test
    void closeCheck__withOneProduct() {
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        checkoutService.addProduct(milk_7);
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(bred_3);
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }

    @Test
    void closeCheck__calcTotalPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }

    @Test
    void useOffer__addOfferPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Condition condition = new TotalCost(6);
        Reward reward = new FlatReward(2);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        Condition condition = new TotalCost(6);
        Reward reward = new FlatReward(2);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));

        assertThat(check.getTotalPoints(), is(3));
    }


    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Condition condition = new ByCategory(Category.MILK);
        Reward reward = new FactorReward(p -> p.getProductCategory() == Category.MILK, 2);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__beforeCheckIsClosed() {
        checkoutService.addProduct(milk_7);

        Condition first_condition = new ByCategory(Category.MILK);
        Reward first_reward = new FactorReward(p -> p.getProductCategory() == Category.MILK, 2);
        checkoutService.useOffer(new BonusOffer(testDate, first_condition, first_reward));

        checkoutService.addProduct(milk_7);

        Condition second_condition = new TotalCost(40);
        Reward second_reward = new FlatReward(2);
        checkoutService.useOffer(new BonusOffer(testDate, second_condition, second_reward));

        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
        assertThat(check.getUsedOffers(), is(1));
    }

    @Test
    void useOffer__checkExpiryDate() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Condition condition = new TotalCost(6);
        Reward reward = new FlatReward(2);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    /*
    @Test
    void useOffer__getHalfPriceDiscount() {
        checkoutService.useOffer(new DiscountOffer(Category.MILK, testDate));

        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }*/

    @Test
    void useOffer__factorByTrademark() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Condition condition = new ByTrademark(20, Trademark.YAGOTUNSKE);
        Reward reward = new FlatReward(10);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(34));
    }


    @Test
    void useOffer__extraFlatPointsByTotalCost() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);

        Condition condition = new TotalCost(15);
        Reward reward = new FlatReward(10);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(30));
    }

    @Test
    void useOffer__extraFlatPointsByTrademark() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Condition condition = new ByTrademark(10, Trademark.YAGOTUNSKE);
        Reward reward = new FlatReward(10);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(27));
    }

    @Test
    void useOffer__extraFlatPointsByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);

        Condition condition = new ByCategory(Category.MILK);
        Reward reward = new FlatReward(10);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(24));
    }

    @Test
    void useOffer__extraFactorPointsByTotalCost() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Condition condition = new TotalCost(15);
        Reward reward = new FactorReward(p -> p.getProductCategory() == Category.MILK, 2);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(45));
    }

    @Test
    void useOffer__extraFactorPointsByTrademark() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Condition condition = new ByTrademark(10, Trademark.YAGOTUNSKE);
        Reward reward = new FactorReward(p -> p.getProductTrademark() == Trademark.YAGOTUNSKE, 2);

        checkoutService.useOffer(new BonusOffer(testDate, condition, reward));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }
}
