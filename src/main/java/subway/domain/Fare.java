package subway.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Fare {
    private static final String UNIT = "원";

    private int fare;
    private String unit;

    public Fare(int fare) {
        validate(fare);
        this.fare = fare;
        this.unit = UNIT;
    }

    private void validate(int fare) {
        if (fare < 0) {
            throw new IllegalArgumentException("요금은 양수여야 합니다");
        }
    }

    public Fare plus(Fare other) {
        return new Fare(fare + other.fare);
    }

    public Fare minus(Fare other) {
        return new Fare(fare - other.fare);
    }

    public Fare applyDiscountRate(double rate) {
        return new Fare((int) (fare * rate));
    }
}
