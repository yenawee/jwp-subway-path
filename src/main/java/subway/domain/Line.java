package subway.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Line {
    private final String name;
    private final String color;
    private final Fare baseFee;

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
        this.baseFee = new Fare(0);
    }

    @JsonCreator
    public Line(@JsonProperty("name") String name,
                @JsonProperty("color") String color,
                @JsonProperty("baseFee") Fare baseFee) {
        this.name = name;
        this.color = color;
        this.baseFee = baseFee;
    }
}
