package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = convertToLottoNumbers(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoNumber.SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또는 6개의 번호를 가져야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> nonDuplicateNumbers = new HashSet<>(numbers);
        if (nonDuplicateNumbers.size() != LottoNumber.SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또는 중복되지 않는 수로 이루어져야 합니다.");
        }
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers){
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();

    }

    public long matchCount(Lotto lotto){
        return numbers.stream()
                .filter(number -> lotto.numbers.contains(number))
                .count();
    }

}
