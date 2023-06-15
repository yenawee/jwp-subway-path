package subway.service.path;

import subway.domain.Fare;
import subway.domain.Passenger;

public abstract class FareCalculator {
    private FareCalculator nextFareCalculator = null;

    public FareCalculator setNextFareCalculator(FareCalculator fareCalculator) {
        this.nextFareCalculator = fareCalculator;
        return nextFareCalculator;
    }

    public Fare calculate(ShortestPath shortestPath, Passenger passenger, Fare baseFare) {
        Fare fare = this.internalCalculate(shortestPath, passenger, baseFare);
        if (nextFareCalculator != null) {
            fare = nextFareCalculator.calculate(shortestPath, passenger, fare);
        }
        return fare;
    }

    abstract Fare internalCalculate(ShortestPath shortestPath, Passenger passenger, Fare fare);
}
