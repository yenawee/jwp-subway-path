package subway.fixture;

import subway.domain.Fare;
import subway.domain.Line;

public class LineFixture {
    public static final Line LINE_1 = new Line("경의중앙선", "청록");
    public static final Line LINE_2 = new Line("3호선", "주황");
    public static final Line LINE_3 = new Line("2호선", "초록");
    public static final Line LINE_4 = new Line("9호선", "금색", new Fare(1000));
    public static final Line LINE_5 = new Line("1호선", "남색", new Fare(50));
}
