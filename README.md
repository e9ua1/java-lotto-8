# 로또 미션

## 학습 목표

- 객체지향 설계: 관련 함수를 묶어 클래스를 만들고, 객체들이 협력하여 하나의 큰 기능을 수행하도록 한다
- TDD 실천: 클래스와 함수에 대한 단위 테스트를 통해 의도한 대로 정확하게 작동하는 영역을 확보한다
- Red-Green-Refactor: TDD 사이클을 체화한다

## 기능 요구 사항

### 1. 로또 구입
- 사용자로부터 구입 금액을 입력받는다
- 구입 금액은 1,000원 단위로만 입력 가능하다
- 구입 금액만큼 로또를 자동 생성한다 (1장당 1,000원)
- 생성된 로또의 수량을 출력한다

### 2. 로또 번호 생성
- 로또 한 장은 중복되지 않는 6개의 숫자를 가진다
- 로또 번호는 1부터 45까지의 숫자 중에서 선택한다
- 로또 번호는 오름차순으로 정렬하여 출력한다

### 3. 당첨 번호 입력
- 사용자로부터 당첨 번호 6개를 쉼표(,) 기준으로 입력받는다
- 당첨 번호는 중복되지 않는 6개의 숫자여야 한다
- 당첨 번호는 1부터 45까지의 숫자여야 한다

### 4. 보너스 번호 입력
- 사용자로부터 보너스 번호 1개를 입력받는다
- 보너스 번호는 1부터 45까지의 숫자여야 한다
- 보너스 번호는 당첨 번호와 중복되지 않아야 한다

### 5. 당첨 결과 계산
- 구매한 각 로또와 당첨 번호를 비교하여 일치 개수를 계산한다
- 일치 개수와 보너스 번호 일치 여부로 당첨 등수를 판정한다
- 당첨 기준은 다음과 같다:
  - 1등: 6개 번호 일치 / 2,000,000,000원
  - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
  - 3등: 5개 번호 일치 / 1,500,000원
  - 4등: 4개 번호 일치 / 50,000원
  - 5등: 3개 번호 일치 / 5,000원
  - 낙첨: 3개 미만 일치 / 0원

### 6. 당첨 통계 출력
- 등수별 당첨 개수를 출력한다
- 당첨되지 않은 로또는 통계에 포함하지 않는다
- 3개 일치(5,000원)부터 6개 일치(2,000,000,000원)까지 출력한다

### 7. 수익률 계산
- 총 당첨 금액을 계산한다
- 수익률은 (총 당첨 금액 / 구입 금액) * 100으로 계산한다
- 수익률은 소수점 둘째 자리에서 반올림한다
- 수익률을 백분율(%)로 출력한다

## 예외 상황

### 입력 검증
- 구입 금액이 1,000원 단위가 아니면 예외 발생
- 구입 금액이 음수 또는 0이면 예외 발생
- 로또 번호가 6개가 아니면 예외 발생
- 로또 번호가 1~45 범위를 벗어나면 예외 발생
- 로또 번호에 중복이 있으면 예외 발생
- 당첨 번호가 6개가 아니면 예외 발생
- 당첨 번호가 1~45 범위를 벗어나면 예외 발생
- 당첨 번호에 중복이 있으면 예외 발생
- 보너스 번호가 1~45 범위를 벗어나면 예외 발생
- 보너스 번호가 당첨 번호와 중복되면 예외 발생
- 숫자 입력란에 문자가 입력되면 예외 발생

### 에러 메시지
- 모든 에러 메시지는 [ERROR]로 시작한다
- 예외 발생 시 해당 부분부터 입력을 다시 받는다
- IllegalArgumentException을 사용한다

## 실행 결과 예시
```
구입금액을 입력해 주세요.
8000

8개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[1, 3, 5, 14, 22, 45]

당첨 번호를 입력해 주세요.
1,2,3,4,5,6

보너스 번호를 입력해 주세요.
7

당첨 통계
---
3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
총 수익률은 62.5%입니다.
```

## 구현할 기능 목록

### 도메인 기능

#### 1. 로또 번호 관리 (LottoNumber, LottoNumbers)
- [x] 로또 번호는 1~45 범위의 숫자다 (LottoNumber)
- [x] 로또 번호는 6개여야 한다 (LottoNumbers)
- [x] 로또 번호는 중복되지 않는다 (LottoNumbers)
- [x] 로또 번호 범위 검증 기능을 제공한다 (LottoNumber.isInRange())
- [x] 다른 로또 번호와의 일치 개수를 계산한다 (LottoNumbers.countMatches())
- [x] 정렬된 번호 리스트를 제공한다 (LottoNumbers.getSortedNumbers())

#### 2. 로또 티켓 (Lotto)
- [x] 로또는 LottoNumbers 타입으로 번호를 관리한다
- [x] 로또 번호는 생성 시점에 오름차순으로 정렬된다
- [x] 로또는 당첨 번호와 일치하는 개수를 계산한다
- [x] 로또는 보너스 번호 포함 여부를 판단한다
- [x] 로또는 자신의 등수를 스스로 계산한다 (calculateRank())
- [x] 로또 번호를 출력 형식으로 변환한다

#### 3. 당첨 번호 관리 (WinningNumbers)
- [x] 당첨 번호는 LottoNumbers로 검증한다
- [x] 당첨 번호는 특정 번호의 포함 여부를 확인한다
- [x] 당첨 번호는 사용자 번호와의 일치 개수 계산 책임을 가진다 (countMatches())

#### 4. 보너스 번호 관리 (BonusNumber)
- [x] 보너스 번호는 1~45 범위의 숫자다
- [x] 보너스 번호는 당첨 번호와 중복되지 않는다
- [x] 보너스 번호는 생성 시 WinningNumbers와 함께 검증한다

#### 5. 당첨 등수 판정 (Rank)
- [x] 6개 일치 시 1등으로 판정한다
- [x] 5개 일치 + 보너스 일치 시 2등으로 판정한다
- [x] 5개 일치 시 3등으로 판정한다
- [x] 4개 일치 시 4등으로 판정한다
- [x] 3개 일치 시 5등으로 판정한다
- [x] 3개 미만 일치 시 낙첨으로 판정한다
- [x] 각 등수는 해당하는 상금을 반환한다
- [x] 당첨 여부를 확인한다
- [x] 등수 설명을 반환한다

#### 6. 구입 금액 관리 (PurchaseAmount)
- [x] 구입 금액은 0보다 커야 한다
- [x] 구입 금액은 1,000원 단위여야 한다
- [x] 구입 금액으로 로또 개수를 계산한다
- [x] 총 당첨 금액으로 수익률을 계산한다

#### 7. 로또 생성 (LottoGenerator)
- [x] 구입 금액으로 구매 가능한 로또 개수를 계산한다
- [x] 계산된 개수만큼 로또를 자동 생성한다
- [x] 각 로또는 1~45 범위에서 중복 없이 6개의 번호를 가진다
- [x] Lottos 일급 컬렉션으로 반환한다

#### 8. 로또 목록 관리 (Lottos)
- [x] 구매한 모든 로또를 관리한다 (불변 컬렉션)
- [x] 로또 개수를 반환한다
- [x] 각 로또를 순회할 수 있다
- [x] 각 로또에게 등수 계산을 위임한다 (Lotto.calculateRank())
- [x] 등수별 당첨 개수를 집계하여 WinningStatistics를 생성한다

#### 9. 당첨 통계 (WinningStatistics)
- [x] 등수별 당첨 개수를 저장한다
- [x] 특정 등수의 당첨 개수를 반환한다
- [x] 등수별 당첨 개수와 상금으로 총 당첨 금액을 계산한다

### 입출력 기능

#### 10. 입력 파싱 (InputParser)
- [x] 문자열을 정수로 변환한다
- [x] 쉼표로 구분된 문자열을 숫자 리스트로 변환한다
- [x] 입력값의 공백을 제거한다
- [x] 숫자 변환 실패 시 예외를 발생시킨다
- [x] 빈 문자열 입력 시 예외를 발생시킨다

#### 11. 입력 처리 (InputView)
- [x] 구입 금액을 입력받는다
- [x] 당첨 번호를 입력받는다
- [x] 보너스 번호를 입력받는다

#### 12. 출력 처리 (OutputView)
- [x] 구매한 로또 개수를 출력한다
- [x] 구매한 로또 번호를 오름차순으로 출력한다
- [x] 당첨 통계 헤더를 출력한다
- [x] 등수별 당첨 개수를 출력한다 (5등부터 1등까지)
- [x] 수익률을 소수점 첫째 자리까지 백분율로 출력한다
- [x] 에러 메시지를 출력한다

### 흐름 제어

#### 13. 게임 진행 (LottoGame)
- [x] 전체 게임 흐름을 제어한다
- [x] 구입 금액을 입력받는다
- [x] 로또를 생성하고 출력한다
- [x] 당첨 번호를 입력받는다
- [x] 보너스 번호를 입력받는다
- [x] 당첨 결과를 계산하고 출력한다 (도메인 객체 조합)
- [x] 예외 발생 시 재입력을 받는다

## 패키지 구조
```
lotto
├── Application.java
├── LottoGame.java
├── domain
│   ├── ticket
│   │   ├── LottoNumber.java
│   │   ├── LottoNumbers.java
│   │   ├── Lotto.java
│   │   ├── Lottos.java
│   │   └── LottoGenerator.java
│   ├── winning
│   │   ├── WinningNumbers.java
│   │   ├── BonusNumber.java
│   │   ├── Rank.java
│   │   └── WinningStatistics.java
│   └── money
│       └── PurchaseAmount.java
└── view
    ├── InputView.java
    ├── OutputView.java
    └── InputParser.java
```

## 클래스 다이어그램

### 전체 구조
```
┌─────────────────────────────────────────────────────────────┐
│                         Application                         │
│  ┌──────────────────────────────────────────────────────┐   │
│  │                     LottoGame                        │   │
│  │ ─────────────────────────────────────────────────────│   │
│  │ - inputView: InputView                               │   │
│  │ - outputView: OutputView                             │   │
│  │ - lottoGenerator: LottoGenerator                     │   │
│  │ ─────────────────────────────────────────────────────│   │
│  │ + start(): void                                      │   │
│  │ - readPurchaseAmount(): PurchaseAmount               │   │
│  │ - generateLottos(PurchaseAmount): Lottos             │   │
│  │ - readWinningNumbers(): WinningNumbers               │   │
│  │ - readBonusNumber(WinningNumbers): BonusNumber       │   │
│  │ - printResult(...): void                             │   │
│  │ - retry(Supplier<T>): T                              │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
         │                   │                    │
         ↓                   ↓                    ↓
    ┌─────────┐        ┌────────────┐      ┌────────────┐
    │InputView│        │OutputView  │      │   Domain   │
    └─────────┘        └────────────┘      └────────────┘
```

### Domain Layer - 티켓 관리
```
┌──────────────────────────────────────────────────────┐
│                  domain.ticket                       │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌─────────────────┐                                 │
│  │  LottoNumber    │                                 │
│  ├─────────────────┤                                 │
│  │ - value: int    │                                 │
│  ├─────────────────┤                                 │
│  │ + LottoNumber(int)                                │
│  │ + isInRange(int): boolean  (static)               │
│  │ + getValue(): int                                 │
│  └─────────────────┘                                 │
│           △                                          │
│           │ 사용                                      │
│           │                                          │
│    ┌──────────────────────┐                          │
│    │   LottoNumbers       │                          │
│    ├──────────────────────┤                          │
│    │ - numbers: List<Integer>                        │
│    ├──────────────────────┤                          │
│    │ + LottoNumbers(List<Integer>)                   │
│    │ + contains(int): boolean                        │
│    │ + countMatches(LottoNumbers): long              │
│    │ + getSortedNumbers(): List<Integer>             │
│    └──────────────────────┘                          │
│         △                 △                          │
│         │ 포함             │ 포함                      │
│    ┌────┴────┐       ┌────┴──────┐                   │
│    │         │       │           │                   │
│  ┌─────────────┐   ┌──────────────────┐              │
│  │   Lotto     │   │ WinningNumbers   │              │
│  ├─────────────┤   ├──────────────────┤              │
│  │ - numbers: LottoNumbers  - lottoNumbers: LottoNumbers
│  ├─────────────┤   ├──────────────────┤              │
│  │ + Lotto(List<Integer>)    + WinningNumbers(List)  │
│  │ + toDisplayString(): String  + contains(int): boolean
│  │ + countMatches(WinningNumbers): int               │
│  │ + containsBonus(BonusNumber): boolean             │
│  │ + calculateRank(WinningNumbers, BonusNumber): Rank│
│  └─────────────┘   ├──────────────────┤              │
│         △          │ + WinningNumbers(List)          │
│         │ 포함      │ + contains(int): boolean        │
│         │          │ + countMatches(LottoNumbers):int│
│         │          └──────────────────┘              │
│  ┌──────────────────────┐                            │
│  │      Lottos          │   (일급 컬렉션, 불변)          │
│  ├──────────────────────┤                            │
│  │ - lottos: List<Lotto>│                            │
│  ├──────────────────────┤                            │
│  │ + Lottos(List<Lotto>)│                            │
│  │ + size(): int        │                            │
│  │ + forEach(Consumer<Lotto>): void                  │
│  │ + calculateStatistics(...): WinningStatistics     │
│  └──────────────────────┘                            │
│         △                                            │
│         │ 생성                                        │
│         │                                            │
│  ┌──────────────────────┐                            │
│  │  LottoGenerator      │                            │
│  ├──────────────────────┤                            │
│  │                      │                            │
│  ├──────────────────────┤                            │
│  │ + generate(PurchaseAmount): Lottos                │
│  └──────────────────────┘                            │
│                                                      │
└──────────────────────────────────────────────────────┘
```

### Domain Layer - 당첨 관리
```
┌──────────────────────────────────────────────────────┐
│                  domain.winning                      │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌──────────────────────────────┐                    │
│  │     WinningNumbers           │                    │
│  ├──────────────────────────────┤                    │
│  │ - lottoNumbers: LottoNumbers │                    │
│  ├──────────────────────────────┤                    │
│  │ + WinningNumbers(List<Integer>)                   │
│  │ + contains(int): boolean     │                    │
│  │ + countMatches(LottoNumbers): int                 │
│  └──────────────────────────────┘                    │
│           △                                          │
│           │ 협력                                      │
│           │                                          │
│  ┌──────────────────────────────┐                    │
│  │      BonusNumber             │                    │
│  ├──────────────────────────────┤                    │
│  │ - number: int                │                    │
│  ├──────────────────────────────┤                    │
│  │ + BonusNumber(int, WinningNumbers)                │
│  │ + getValue(): int            │                    │
│  └──────────────────────────────┘                    │
│                                                      │
│  ┌──────────────────────────────┐                    │
│  │         Rank (Enum)          │                    │
│  ├──────────────────────────────┤                    │
│  │ FIRST, SECOND, THIRD,        │                    │
│  │ FOURTH, FIFTH, NONE          │                    │
│  ├──────────────────────────────┤                    │
│  │ - matchCount: int            │                    │
│  │ - bonusMatch: boolean        │                    │
│  │ - prize: int                 │                    │
│  │ - description: String        │                    │
│  ├──────────────────────────────┤                    │
│  │ + of(int, boolean): Rank  (static)                │
│  │ + getPrize(): int            │                    │
│  │ + isWinning(): boolean       │                    │
│  │ + getDescription(): String   │                    │
│  └──────────────────────────────┘                    │
│           △                                          │
│           │ 사용                                      │
│           │                                          │
│  ┌──────────────────────────────┐                    │
│  │   WinningStatistics          │                    │
│  ├──────────────────────────────┤                    │
│  │ - rankCounts: Map<Rank, Long>│                    │
│  ├──────────────────────────────┤                    │
│  │ + WinningStatistics(Map)     │                    │
│  │ + getCountByRank(Rank): long │                    │
│  │ + calculateTotalPrize(): long│                    │
│  └──────────────────────────────┘                    │
│                                                      │
└──────────────────────────────────────────────────────┘
```

### Domain Layer - 금액 관리
```
┌──────────────────────────────────────────────────────┐
│                  domain.money                        │
├──────────────────────────────────────────────────────┤
│                                                      │
│  ┌──────────────────────────────┐                    │
│  │    PurchaseAmount            │                    │
│  ├──────────────────────────────┤                    │
│  │ - amount: int                │                    │
│  ├──────────────────────────────┤                    │
│  │ + PurchaseAmount(int)                             │
│  │ + calculateLottoCount(): int                      │
│  │ + calculateReturnRate(long): double               │
│  └──────────────────────────────┘                    │
│                                                      │
└──────────────────────────────────────────────────────┘
```

## 객체 협력 구조

### 1. 로또 구매 흐름
```
[사용자]
   │
   │ 1. 구입 금액 입력 (예: "8000")
   ↓
[InputView.readPurchaseAmount()]
   │
   │ 2. 파싱
   ↓
[InputParser.parseInt()] ────→ int 8000
   │
   │ 3. 검증 및 생성
   │    - 양수인지
   │    - 1,000원 단위인지
   ↓
[PurchaseAmount(8000)]
   │
   │ 4. 로또 개수 계산
   │    calculateLottoCount() ───→ 8개
   │
   │ 5. 로또 생성
   ↓
[LottoGenerator.generate(PurchaseAmount)]
   │
   │ 6. 8번 반복:
   │
   ├─→ Randoms.pickUniqueNumbersInRange(1, 45, 6)
   │    └─→ List<Integer> (예: [8, 21, 23, 41, 42, 43])
   │
   │ 7. 각 번호 리스트로 Lotto 생성
   ↓
[Lotto(List<Integer>)]
   │
   │ 8. LottoNumbers로 검증 및 관리 (타입 변경)
   │    - 6개인지
   │    - 1~45 범위인지  
   │    - 중복 없는지
   │
   │ 9. 8개의 Lotto를 일급 컬렉션으로 묶기
   │    (불변 컬렉션으로 생성)
[Lottos(List<Lotto>)]
   │
   │ 10. 개수와 번호 출력
   ↓
[OutputView.printPurchaseCount(8)]
[OutputView.printLottos(Lottos)]
```

### 2. 당첨 결과 계산 흐름
```
[사용자]
   │
   │ 1. 당첨 번호 입력 (예: "1,2,3,4,5,6")
   ↓
[InputView.readWinningNumbers()]
   │
   │ 2. 파싱
   ↓
[InputParser.parseNumbers()] ────→ List<Integer> [1,2,3,4,5,6]
   │
   │ 3. 검증 및 생성
   ↓
[WinningNumbers(List<Integer>)]
   │
   │ - LottoNumbers로 위임 검증
   │   (6개, 1~45, 중복 없음)
   │
   │ 4. 보너스 번호 입력 (예: "7")
   ↓
[InputParser.parseInt()] ────→ int 7
   │
   │ 5. 검증 및 생성
   ↓
[BonusNumber(7, WinningNumbers)]
   │
   │ - 1~45 범위 검증
   │ - WinningNumbers와 중복 검증
   │
   │ 6. 당첨 결과 계산
   ↓
[Lottos.calculateStatistics(WinningNumbers, BonusNumber)]
   │
   │ 7. 각 Lotto마다 반복:
   │
   ├─→ [Lotto.calculateRank(WinningNumbers, BonusNumber)]
   │    │
   │    ├─→ countMatches(WinningNumbers) → int
   │    │    └─→ WinningNumbers.countMatches(LottoNumbers)
   │    │         └─→ LottoNumbers.countMatches(LottoNumbers)
   │    │
   │    ├─→ containsBonus(BonusNumber) → boolean
   │    │
   │    └─→ Rank.of(matchCount, hasBonus) → Rank
   │
   │ 8. 등수별 집계 (EnumMap 사용)
   │
   ↓
[WinningStatistics(Map<Rank, Long>)]
   │
   │ 9. 총 당첨 금액 계산
   │ calculateTotalPrize()
   │  = FIFTH(2개) * 5,000원 + FOURTH(1개) * 50,000원 + ...
   │    → long totalPrize
   │
   │ 10. 수익률 계산
   │ [LottoGame에서 직접 조합]
   │   totalPrize = statistics.calculateTotalPrize()
   │   returnRate = purchaseAmount.calculateReturnRate(totalPrize)
   │
   │ 11. 결과 출력
   ↓
[OutputView.printStatistics(WinningStatistics)]
[OutputView.printReturnRate(double)]
```

### 3. 예외 처리 흐름 (재입력)
```
[LottoGame]
      │
      │ retry(Supplier<T>)
      ↓
   ┌─────────────┐
   │ while(true) │
   │      │      │
   │      ↓      │
   │   try {     │
   │      │      │
   │   입력/검증   │  ───→  성공 ───→ return T
   │      │      │
   │      ↓      │
   │   } catch(IllegalArgumentException e) {
   │      │      │
   │      ↓      │
   │ outputView.printErrorMessage(e.getMessage())
   │      │      │
   │      │      │  (다시 반복)
   │   }         │
   │      ↑      │
   └──────┼──────┘
          │
          └─ 재시도
```

### 4. 검증 책임 분산
```
┌──────────────────────────────────────────┐
│         검증 책임의 위임 구조                 │
└──────────────────────────────────────────┘

LottoNumber
  ├─ 범위 검증 (1~45)
  └─ 정적 메서드로 범위 확인 제공

LottoNumbers
  ├─ 크기 검증 (6개)
  ├─ 중복 검증
  ├─ 범위 검증 (LottoNumber.isInRange() 사용)
  └─ 일치 개수 계산 (countMatches())  ← 추가

Lotto
  ├─ LottoNumbers에 검증 위임
  └─ 등수 계산 책임 (calculateRank())  ← 추가

WinningNumbers
  ├─ LottoNumbers에 검증 위임
  └─ 일치 개수 계산 책임 (countMatches())  ← 추가

BonusNumber
  ├─ 범위 검증 (LottoNumber.isInRange() 사용)
  └─ WinningNumbers와 중복 검증

PurchaseAmount
  ├─ 양수 검증
  ├─ 1,000원 단위 검증
  └─ 수익률 계산 책임

InputParser
  ├─ 숫자 변환 검증
  └─ 빈 문자열 검증
```

## 핵심 객체 책임 정리

### Domain 객체

```
LottoNumber
├─ 책임: 로또 번호 한 개의 범위 검증
├─ 협력: LottoNumbers에서 사용
└─ 원칙: 원시값 포장 (1~45)

LottoNumbers
├─ 책임: 로또 번호 6개에 대한 검증 (크기, 중복, 범위)
│        다른 로또 번호와의 일치 개수 계산
│        정렬된 번호 리스트 제공
├─ 협력: Lotto, WinningNumbers에서 위임받아 사용
└─ 원칙: 공통 검증 로직 재사용, 일급 컬렉션

Lotto
├─ 책임: 로또 한 장의 번호 관리 (LottoNumbers 타입으로)
│        자신의 등수를 스스로 계산
│        일치 개수 계산, 보너스 포함 여부 판단
├─ 협력: LottoNumbers로 검증 및 비교 위임
│        WinningNumbers, BonusNumber와 협력
│        Rank.of()로 등수 판정
└─ 원칙: Tell, Don't Ask (스스로 계산)
         SRP (자신의 등수는 자신이 계산)

Lottos (일급 컬렉션)
├─ 책임: 여러 로또 관리 (불변 컬렉션)
│        각 로또에게 등수 계산 위임
│        통계 집계
├─ 협력: Lotto.calculateRank() 호출
│        WinningNumbers, BonusNumber 전달
└─ 원칙: 컬렉션 기반 행위 캡슐화
         방어적 복사 (List.copyOf())

LottoGenerator
├─ 책임: 로또 자동 생성
│        Randoms API를 사용한 번호 생성
├─ 협력: PurchaseAmount로 개수 계산
│        Lotto 생성 후 Lottos로 묶기
└─ 원칙: 단일 책임 (생성만)

WinningNumbers
├─ 책임: 당첨 번호 6개 관리
│        사용자 번호와 일치 개수 계산
├─ 협력: LottoNumbers로 검증 위임
│        LottoNumbers.countMatches() 활용
└─ 원칙: 불변 객체, 비교 책임 소유

BonusNumber
├─ 책임: 보너스 번호 1개 관리 + 중복 검증
├─ 협력: WinningNumbers와 중복 확인
│        LottoNumber로 범위 검증
└─ 원칙: 생성 시점 검증

Rank (Enum)
├─ 책임: 등수 판정 + 상금 제공
│        등수별 설명 제공
├─ 협력: 일치 개수와 보너스 여부로 판정
└─ 원칙: 전략 패턴 (Enum으로 구현)

WinningStatistics
├─ 책임: 등수별 통계 집계
│        총 당첨 금액 계산
├─ 협력: Rank로 통계 집계
└─ 원칙: 값 객체 (불변)
         단일 책임 (통계만)

PurchaseAmount
├─ 책임: 구입 금액 검증 (양수, 1,000원 단위)
│        로또 개수 계산
│        수익률 계산
├─ 협력: LottoGenerator에게 개수 정보 제공
│        LottoGame에서 수익률 계산 시 사용
└─ 원칙: 원시값 포장 + 계산 캡슐화
         Information Expert (금액 정보 소유자가 계산)
```

### View 객체

```
InputView
├─ 책임: 사용자 입력 받기
└─ 원칙: Console API 사용

OutputView
├─ 책임: 결과 출력
│        포맷팅 담당
└─ 원칙: 출력 로직 집중

InputParser
├─ 책임: 문자열 파싱
│        형식 변환
└─ 원칙: 정적 유틸리티
```

### 게임 흐름 제어 객체

```
LottoGame
├─ 책임: 전체 게임 흐름 제어
│        예외 처리 및 재입력
│        도메인 객체들의 조합
├─ 협력: InputView, OutputView, Domain 계층 연결
│        statistics.calculateTotalPrize() +
│        purchaseAmount.calculateReturnRate() 조합
└─ 원칙: 추상화 수준 통일
         재시도 로직 캡슐화 (retry 메서드)
         Law of Demeter (직접 협력)
```

## 프로그래밍 요구사항

### 필수 구현 사항
- Lotto 클래스를 사용하여 구현해야 한다
- Lotto에 numbers 필드의 타입을 LottoNumbers로 변경
- numbers의 접근 제어자인 private은 변경할 수 없다
- Lotto의 패키지는 `lotto.domain.ticket`으로 변경했다

### 코딩 컨벤션
- Java 코드 컨벤션을 지키며 프로그래밍한다
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다 (2까지만 허용)
- 3항 연산자를 쓰지 않는다
- else 예약어를 쓰지 않는다
- 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만든다
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다
- JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다
- Java Enum을 적용하여 프로그램을 구현한다

### 라이브러리
- `camp.nextstep.edu.missionutils`에서 제공하는 Randoms 및 Console API를 사용하여 구현해야 한다
- Random 값 추출은 `camp.nextstep.edu.missionutils.Randoms`의 `pickUniqueNumbersInRange()`를 활용한다
- 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다
