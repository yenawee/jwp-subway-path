package subway.service.path;

import subway.domain.Fare;
import subway.domain.Line;
import subway.domain.Passenger;
import subway.domain.Section;

import java.util.Comparator;
import java.util.List;

public class LineFareCalculator extends FareCalculator {
    @Override
    Fare internalCalculate(ShortestPath shortestPath, Passenger passenger, Fare fare) {
        List<Section> path = shortestPath.getPath();
        Line highestFareLine = path.stream()
                .map(Section::getLine)
                .max(Comparator.comparingDouble(line -> line.getBaseFee().getFare()))
                .orElseThrow(() -> new IllegalStateException("가장 큰 요금을 가진 노선을 조회할 수 없습니다"));
        return fare.plus(highestFareLine.getBaseFee());
    }
}
