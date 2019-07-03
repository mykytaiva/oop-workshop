package checkout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Offer {

    private final LocalDate expiryDate;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Offer (String expiryDate) {
        this.expiryDate = LocalDate.parse(expiryDate, dateTimeFormatter);
    }

    public abstract void apply(Check check);

    boolean isValidDate() {
        return LocalDate.now().isBefore(this.expiryDate);
    }
}
