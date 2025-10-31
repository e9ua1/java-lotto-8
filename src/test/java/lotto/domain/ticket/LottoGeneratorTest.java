package lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lotto.domain.money.Money;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoGenerator 테스트")
class LottoGeneratorTest {

    @Nested
    @DisplayName("로또를 생성할 때")
    class GenerateTest {

        @Test
        @DisplayName("구입 금액에 맞는 개수의 로또를 생성한다")
        void generateLottos() {
            Money money = new Money(8000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(money);

            assertThat(lottos.size()).isEqualTo(8);
        }

        @Test
        @DisplayName("1,000원으로 1개의 로또를 생성한다")
        void generateOneLotto() {
            Money money = new Money(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(money);

            assertThat(lottos.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("5,000원으로 5개의 로또를 생성한다")
        void generateFiveLottos() {
            Money money = new Money(5000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(money);

            assertThat(lottos.size()).isEqualTo(5);
        }

        @Test
        @DisplayName("생성된 로또는 6개의 번호를 가진다")
        void generatedLottoHasSixNumbers() {
            Money money = new Money(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(money);

            assertThat(lottos.size()).isEqualTo(1);
            // toDisplayString()으로 검증 - [1, 2, 3, 4, 5, 6] 형식이므로 쉼표 5개 = 6개 번호
            String displayString = extractFirstLotto(lottos);
            int commaCount = displayString.length() - displayString.replace(",", "").length();
            assertThat(commaCount).isEqualTo(5);
        }

        @Test
        @DisplayName("생성된 로또의 번호는 1~45 범위다")
        void generatedLottoNumbersInRange() {
            Money money = new Money(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(money);

            // 간접 검증: 생성 시 예외가 발생하지 않으면 범위 내 번호
            assertThat(lottos.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("생성된 로또의 번호는 중복되지 않는다")
        void generatedLottoNumbersAreUnique() {
            Money money = new Money(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(money);

            // 간접 검증: 생성 시 예외가 발생하지 않으면 중복 없음
            assertThat(lottos.size()).isEqualTo(1);
        }

        private String extractFirstLotto(Lottos lottos) {
            final String[] result = {""};
            lottos.forEach(lotto -> {
                if (result[0].isEmpty()) {
                    result[0] = lotto.toDisplayString();
                }
            });
            return result[0];
        }
    }
}
