package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("[ERROR]");
    }

    @DisplayName("다른 로또와 일치하는 번호의 개수를 구할 수 있다.")
    @ParameterizedTest
    @MethodSource("matchData")
    void matchCount(Lotto lotto, Lotto targetLotto, long count) {
        assertThat(lotto.matchCount(targetLotto)).isEqualTo(count);
    }

    static Stream<Arguments> matchData() {
        return Stream.of(
                arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6L),
                arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 7)), 5L),
                arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 8, 7)), 4L),
                arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 8, 23, 24)), 3L),
                arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 22, 12, 33, 23)), 2L),
                arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 23, 32, 12, 34, 35)), 1L),
                arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(17, 23, 32, 12, 34, 35)), 0L)
        );
    }

}