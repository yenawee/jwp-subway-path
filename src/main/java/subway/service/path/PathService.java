package subway.service.path;

import org.springframework.stereotype.Service;
import subway.domain.*;
import subway.dto.PathResponse;
import subway.persistence.repository.PathRepository;

@Service
public class PathService {
    private final PathRepository pathRepository;
    private final FareCalculator fareCalculator;

    public PathService(PathRepository pathRepository, FareCalculator fareCalculator) {
        this.pathRepository = pathRepository;

        FareCalculator ageCalculator = new AgeFareCalculator();
        FareCalculator lineCalculator = new LineFareCalculator();

        fareCalculator.setNextFareCalculator(lineCalculator).setNextFareCalculator(ageCalculator);

        this.fareCalculator = fareCalculator;
    }

    public PathResponse getShortestPath(Long departureStationId, Long arrivalStationId, Long age) {
        Station departure = pathRepository.findStationById(departureStationId);
        Station arrival = pathRepository.findStationById(arrivalStationId);

        Sections sections = pathRepository.findAll();
        Subway subway = Subway.from(sections);

        SubwayGraph subwayGraph = SubwayGraph.from(subway);
        ShortestPath shortestPath = subwayGraph.getDijkstraShortestPath(departure, arrival);
        Passenger passenger = new Passenger(age);
        Fare fare = fareCalculator.calculate(shortestPath, passenger, new Fare(0));

        return PathResponse.of(shortestPath, fare);
    }


}
