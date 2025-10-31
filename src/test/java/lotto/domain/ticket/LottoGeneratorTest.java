package lotto.domain.ticket;

import lotto.domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

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
            List<Lotto> lottoList = lottos.getLottos();

            assertThat(lottoList.get(0).getNumbers()).hasSize(6);
        }

        @Test
        @DisplayName("생성된 로또의 번호는 1~45 범위다")
        void generatedLottoNumbersInRange() {
            Money money = new Money(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(money);
            List<Lotto> lottoList = lottos.getLottos();
            List<Integer> numbers = lottoList.get(0).getNumbers();

            assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
        }

        @Test
        @DisplayName("생성된 로또의 번호는 중복되지 않는다")
        void generatedLottoNumbersAreUnique() {
            Money money = new Money(1000);
            LottoGenerator generator = new LottoGenerator();

            Lottos lottos = generator.generate(money);
            List<Lotto> lottoList = lottos.getLottos();
            List<Integer> numbers = lottoList.get(0).getNumbers();

            assertThat(numbers).doesNotHaveDuplicates();
        }
    }
}
