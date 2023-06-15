package subway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class LineRequest {
    @NotBlank(message = "노선 이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "노선 색깔을 입력해주세요.")
    private String color;
    @NotNull
    @Min(value = 0, message = "{value} 이상의 값을 입력해주세요")
    private Long baseFee;
}
