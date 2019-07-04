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

        testDate = LocalDate.of(2019, 8, 30);
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

        checkoutService.useOffer(new AnyGoodsOffer(6, 2, testDate));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        checkoutService.useOffer(new AnyGoodsOffer(6, 2, testDate));

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, testDate));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__beforeCheckIsClosed() {
        checkoutService.addProduct(milk_7);

        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, testDate));

        checkoutService.addProduct(milk_7);

        checkoutService.useOffer(new AnyGoodsOffer(40, 2, testDate));

        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
        assertThat(check.getUsedOffers(), is(1));
    }

    @Test
    void useOffer__checkExpiryDate() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new AnyGoodsOffer(6, 2, testDate));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__getHalfPriceDiscount() {
        checkoutService.useOffer(new DiscountOffer(Category.MILK, testDate));

        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.addProduct(bred_3);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void useOffer__factorByTrademark() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.useOffer(new TrademarkOffer(Trademark.YAGOTUNSKE, 20,10, testDate));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(34));
    }
}
