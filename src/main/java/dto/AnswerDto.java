package dto;

import lombok.*;

import java.util.Comparator;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {
    private Long id;
    private Long questionId;
    private String text;
    private boolean result;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDto answerDto = (AnswerDto) o;
        return result == answerDto.result && Objects.equals(id, answerDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result);
    }
}
