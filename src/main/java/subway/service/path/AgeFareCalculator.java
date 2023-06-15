package subway.service.path;

import subway.domain.Fare;
import subway.domain.Passenger;

public class AgeFareCalculator extends FareCalculator {
    private static final Fare BASIC_DEDUCTION = new Fare(350);
    private static final double CHILD_DISCOUNT_RATE = 0.5;
    private static final double TEENAGER_DISCOUNT_RATE = 0.8;

    @Override
    Fare internalCalculate(ShortestPath shortestPath, Passenger passenger, Fare fare) {
        if (passenger.isChild()) {
            return fare.minus(BASIC_DEDUCTION).applyDiscountRate(CHILD_DISCOUNT_RATE);
        }
        if (passenger.isTeenager()) {
            return fare.minus(BASIC_DEDUCTION).applyDiscountRate(TEENAGER_DISCOUNT_RATE);
        }
        return fare;
    }
}
