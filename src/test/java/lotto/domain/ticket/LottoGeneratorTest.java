package lotto.domain.ticket;

import lotto.domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    }
}
