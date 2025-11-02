package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("InputParser 테스트")
class InputParserTest {

    @Nested
    @DisplayName("문자열을 정수로 변환할 때")
    class ParseIntTest {

        @Test
        @DisplayName("숫자 문자열을 정수로 변환한다")
        void parseInteger() {
            String input = "8000";

            int result = InputParser.parseInt(input);

            assertThat(result).isEqualTo(8000);
        }

        @Test
        @DisplayName("앞뒤 공백을 제거하고 정수로 변환한다")
        void parseIntegerWithWhitespace() {
            String input = "  8000  ";

            int result = InputParser.parseInt(input);

            assertThat(result).isEqualTo(8000);
        }

        @Test
        @DisplayName("숫자가 아닌 문자열이면 예외가 발생한다")
        void parseIntegerWithInvalidFormat() {
            String input = "abc";

            assertThatThrownBy(() -> InputParser.parseInt(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @Test
        @DisplayName("빈 문자열이면 예외가 발생한다")
        void parseIntegerWithEmptyString() {
            String input = "";

            assertThatThrownBy(() -> InputParser.parseInt(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("쉼표로 구분된 문자열을 정수 리스트로 변환할 때")
    class ParseNumbersTest {

        @Test
        @DisplayName("쉼표로 구분된 숫자를 정수 리스트로 변환한다")
        void parseNumbers() {
            String input = "1,2,3,4,5,6";

            List<Integer> result = InputParser.parseNumbers(input);

            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("각 숫자의 공백을 제거하고 변환한다")
        void parseNumbersWithWhitespace() {
            String input = " 1 , 2 , 3 , 4 , 5 , 6 ";

            List<Integer> result = InputParser.parseNumbers(input);

            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("숫자가 아닌 값이 포함되면 예외가 발생한다")
        void parseNumbersWithInvalidNumber() {
            String input = "1,2,a,4,5,6";

            assertThatThrownBy(() -> InputParser.parseNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @Test
        @DisplayName("빈 문자열이면 예외가 발생한다")
        void parseNumbersWithEmptyString() {
            String input = "";

            assertThatThrownBy(() -> InputParser.parseNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }
}
