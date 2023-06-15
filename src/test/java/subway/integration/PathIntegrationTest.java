package subway.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;
import subway.dto.PathResponse;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static subway.fixture.SectionFixture.*;

@DisplayName("지하철 최단경로 구하기 기능")
public class PathIntegrationTest extends IntegrationTest {
    @DisplayName("최단 거리 경로를 구한다 (연령별 요금정책 적용)")
    @ParameterizedTest
    @CsvSource({"20, 1750", "10, 700", "18, 1120"})
    void getShortestPath(Long age, int expected) {
        //given
        createStation("용산");
        createStation("이촌");
        createStation("서빙고");
        createStation("한남");
        createStation("옥수");
        createStation("압구정");

        createLine("경의중앙선", "청록", 0L);
        createLine("3호선", "주황", 0L);

        createLineStation(1L, "1", "2", "10");
        createLineStation(1L, "2", "3", "8");
        createLineStation(1L, "3", "4", "10");
        createLineStation(1L, "4", "5", "7");
        createLineStation(2L, "5", "6", "10");

        //when
        PathResponse shortestPath = getShortestPath(2L, 6L, age);

        //then
        assertThat(shortestPath.getPath()).isEqualTo(List.of(
                SECTION_2, SECTION_3, SECTION_4, SECTION_7));
        assertThat(shortestPath.getDistance().getDistance()).isEqualTo(35);
        assertThat(shortestPath.getFare().getFare()).isEqualTo(expected);
    }

    @DisplayName("출발역이 노선에 포함되어 있지 않으면 예외가 발생한다")
    @Test
    void ExceptionOccursWhenDepartureStationNotExists() {
        //given
        createStation("용산");
        createStation("이촌");
        createStation("서빙고");
        createStation("한남");
        createStation("옥수");
        createStation("압구정");

        createLine("경의중앙선", "청록", 0L);
        createLine("3호선", "주황", 0L);

        createLineStation(1L, "1", "2", "10");
        createLineStation(1L, "2", "3", "10");
        createLineStation(2L, "5", "6", "10");


        //when, then
        given().
                when().
                get("/path?departure={departure}&arrival={arrival}&age={age}", "4", "6", "10").
                then().
                log().all().
                statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("노선별 추가 요금 정책을 확인한다")
    @Test
    void fareCalculate_LineFare() {
        //given
        createStation("용산");
        createStation("이촌");
        createStation("노량진");
        createStation("노들");

        createLine("경의중앙선", "청록", 0L);
        createLine("9호선", "금색", 1000L);
        createLine("1호선", "남색", 50L);

        createLineStation(1L, "1", "2", "10");
        createLineStation(2L, "3", "4", "3");
        createLineStation(3L, "1", "3", "10");


        //when
        PathResponse shortestPath = getShortestPath(2L, 4L, 20L);

        //then
        assertThat(shortestPath.getDistance().getDistance()).isEqualTo(23);
        assertThat(shortestPath.getFare().getFare()).isEqualTo(2550);
    }
}
