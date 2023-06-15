package subway.service.path;

import org.springframework.stereotype.Component;
import subway.domain.Distance;
import subway.domain.Fare;
import subway.domain.Passenger;

@Component
public class DistanceFareCalculator extends FareCalculator {
    private static final Distance BASE_DISTANCE = new Distance(10);
    private static final Distance ADDITIONAL_FARE_DISTANCE = new Distance(50);
    private static final Distance FIRST_ADDITIONAL_DISTANCE_INTERVAL = new Distance(5);
    private static final Distance SECOND_ADDITIONAL_DISTANCE_INTERVAL = new Distance(8);
    private static final int ADDITIONAL_FARE = 100;
    private static final int BASIC_FARE = 1_250;

    @Override
    public Fare internalCalculate(ShortestPath shortestPath, Passenger passenger, Fare fare) {
        Distance distance = shortestPath.getDistance();
        int additionalFare = 0;
        if (distance.compareTo(BASE_DISTANCE) > 0 && distance.compareTo(ADDITIONAL_FARE_DISTANCE) != 1) {
            additionalFare = (int) ((Math.ceil((distance.subtract(BASE_DISTANCE).getDistance()) / FIRST_ADDITIONAL_DISTANCE_INTERVAL.getDistance()) * ADDITIONAL_FARE));
        }
        if (distance.compareTo(ADDITIONAL_FARE_DISTANCE) > 0) {
            additionalFare = (800 + (int) ((Math.ceil((distance.subtract(ADDITIONAL_FARE_DISTANCE).getDistance()
                    / SECOND_ADDITIONAL_DISTANCE_INTERVAL.getDistance())) * ADDITIONAL_FARE)));
        }
        return new Fare(BASIC_FARE + additionalFare);
    }
}
