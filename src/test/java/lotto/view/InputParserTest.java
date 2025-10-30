package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
