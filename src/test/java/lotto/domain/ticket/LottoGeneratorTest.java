package lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lotto.domain.money.PurchaseAmount;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoGenerator 테스트")
class LottoGeneratorTest {

    @Nested
    @DisplayName("로또를 생성할 때")
    class GenerateTest {

        @Test
        @DisplayName("구입 금액에 맞는 개수의 로또를 생성한다")
        void generateLottos() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(8000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(purchaseAmount);

            assertThat(lottos.size()).isEqualTo(8);
        }

        @Test
        @DisplayName("1,000원으로 1개의 로또를 생성한다")
        void generateOneLotto() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(purchaseAmount);

            assertThat(lottos.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("5,000원으로 5개의 로또를 생성한다")
        void generateFiveLottos() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(5000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(purchaseAmount);

            assertThat(lottos.size()).isEqualTo(5);
        }

        @Test
        @DisplayName("생성된 로또는 6개의 번호를 가진다")
        void generatedLottoHasSixNumbers() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(purchaseAmount);

            assertThat(lottos.size()).isEqualTo(1);
            String displayString = extractFirstLotto(lottos);
            int commaCount = displayString.length() - displayString.replace(",", "").length();
            assertThat(commaCount).isEqualTo(5);
        }

        @Test
        @DisplayName("생성된 로또의 번호는 1~45 범위다")
        void generatedLottoNumbersInRange() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(purchaseAmount);

            assertThat(lottos.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("생성된 로또의 번호는 중복되지 않는다")
        void generatedLottoNumbersAreUnique() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(purchaseAmount);

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
