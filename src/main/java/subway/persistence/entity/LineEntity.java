package subway.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import subway.domain.Fare;
import subway.domain.Line;

@Getter
@AllArgsConstructor
public class LineEntity {
    private final Long id;
    private final String name;
    private final String color;
    private final Long baseFee;

    public LineEntity(String name, String color, Long baseFee) {
        this(null, name, color, baseFee);
    }

    public Line toLine() {
        return new Line(name, color, new Fare(Math.toIntExact(baseFee)));
    }
}
