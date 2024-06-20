# Stream API
* Java 8에 추가된 java.util.stream 패키지
* 컬렉션 요소를 람다식으로 처리할 수 있도록 돕는 함수형 프로그래밍 도구


## Stream API 특징
* 간결한 코드로 작성할 수 있다.
```java
// JAVA 7
List<String> list = Arrays.asList("fast", "campus", "rocks");
List<String> newList = new ArrayList<>();

for (String s: list) {
    newList.add(s.toUpperCase());
}

for (String s: newList) {
    System.out.println(s);
}
```
```java
// JAVA 8
List<String> list = Arrays.asList("fast", "campus", "rocks");
Stream<String> stream = list.stream();
stream.map(String::toUpperCase).forEach(System.out::println);
```
* 데이터 소스에 대한 공통된 접근 방식 제공
    * Stream으로 변경해주고 나면, List, Set, Map 모두 동일한 방식으로 데이터를 처리할 수 있다.
* 중간 처리와 최종 처리를 지원


## Stream 생성 방식
1. Collection의 인스턴스 메소드
    * Stream<T> stream() 메소드 이용
    ```java
    List<String> list1 = Arrays.asList("fast", "campus", "rocks");
    Stream<String> stream1 = list1.stream();
    ```
2. Arrays를 이용하는 방법
    * Arrays.stream() 메소드 이용
    * IntStream, LongStream, DoubleStream 가능
    ```java
    int[] ints = {4, 6, 2, 19, 2, 58, 4, 6, 5};
    IntStream intStream = Arrays.stream(ints);
    ```
3. Stream 클래스의 클래스 메소드
    * Stream.of() 메소드 이용
    ```java
    DoubleStream doubleStream = DoubleStream.of(0.4, 0.6, 0.2, 1.2);
    ```

4. Stream의 range를 이용하는 방법
    ```java
    IntStream intStream1 = IntStream.range(0, 10);  // 10은 포함되지 않는다.
    intStream1.forEach(System.out::println);
    IntStream intStream2 = IntStream.rangeClosed(0, 10); // 10이 포함된다.
    // LongStream도 range, rangeClosed가 있다.
    ``` 

5. Random 객체를 이용한 방법
    * ints, longs, doubles 메소드
    * ints(streamSize, randomNumberOrigin, randomNumberBound)
    ```java
    Random random = new Random();
    LongStream longStream = random.longs(100);  // 100개 제한
    longStream.forEach(System.out::println);
    
    LongStream longStream2 = random.longs(100, 0, 1000); // 0 부터 1000개 
    ```

## Stream API 종류
1. Stream 종류

    | 종류 | 처리 대상 |
    |-----|-----------|
    | Stream<T> | 일반적인 객체를 처리 |
    | IntStream | 기본 자료형 int를 처리 |
    | LongStream | 기본 자료형 long을 처리 |
    | DoubleStream | 기본 자료형 double을 처리 |

2. 중간 처리 메소드
    1) Filtering 메소드
        * Stream<T> distinct() - 스트림에 같은 요소가 있을 경우, 하나만 남기고 삭제하는 메소드
        ```
       stringStream.distinct().forEach(System.out::println);
        ```
        * Stream<T> filter(Predicate<? super T> predicate) - Predicate 계열을 입력받아, true인 요소를 남긴다.
        ```java
        stringStream = Stream.of("Java", "Is", "Fun", "Isn't", "It", "?", "Java", "Java");
        stringStream.filter(s -> s.length() >= 3)
           .forEach(System.out::println);
        ```
    
    2) Cutting 메소드
        * Stream<T> limit(long maxSize) - 스트림의 최대 요소 개수가 maxSize인 스트림 반환
        * Stream<T> skip(long n) - 스트림의 최초 n개를 생략하는 메소드
    
    3) Sorting 메소드
        * compareTo() 또는 입력받은 Comparator를 이용해 정렬
        * Stream<T> sorted() : Comparable 객체를 정렬한 스트림 반환
        * Stream<T> sorted(Comparator<? super T> comparator) : Comparator를 이용하여 정렬
        ```java
        stringStream = Stream.of("abc", "fwf", "dnmov", "work");
        stringStream.sorted().forEach(System.out::println);         // String 객체의 comateTo를 사용
        
        stringStream = Stream.of("abc", "fwf", "dnmov", "work");
        stringStream.sorted((o1, o2) -> o1.length() - o2.length())   // Comparator 인터페이스를 람다식으로 구현
                .forEach(System.out::println);
        System.out.println();
        ```
    4) Mapping 메소드
        * Function 계열의 인터페이스를 사용하여 스트림의 각 요소를 매핑
        * <R> Stream<R> map(Function<? super T, ? extends R> mapper) - 기존 스트림의 T 타입 요소를 R 타입으로 변환하여 새로운 스트림 반환
        * PStream mapToP(ToPFunction<? super T> mapper) -  R이 기본형 타입으로 제한된 map()
        ```java
        stringStream = Stream.of("abc", "fwf", "dnmov", "work");
        // Function 계열로 String -> Integer로 변환하는 매핑, Function<String, Integer>
        Stream<Integer> stream2 = stringStream.map(s -> s.length());
        stream2.forEach(System.out::println);
        ```
        * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) - 스트림의 T 타입 요소가 n개의 R 타입 요소로 매핑된 새로운 스트림을 반환
        * PStream flatMapToP(Function<? super T, ? extends PStream> mapper) - R이 기본형 타입으로 제한된 flatMap()
        ```java
        List<String> list2 = Arrays.asList("java", "backend", "best", "course");
        System.out.println(list2.stream().flatMap(data -> {
           return Arrays.stream(data.split(""));
        }).distinct().count());
        ```
       
    5) 조회(Peek)
        * 중간 결과를 출력해 볼 수 있음 (디버깅 기능)
        * Stream<T> peek(Consumer<? super T> action) : Consumer 계열의 람다식으로 입력받아 입력 요소를 소비
        ```java
        list2 = Arrays.asList("java", "backend", "best", "course");
        list2.stream().flatMap(data -> {
           return Arrays.stream(data.split(""));
        }).peek(s -> {
           System.out.println(s);
        }).distinct().forEach(System.out::println);
        ```
       
    
3. 최종 처리 메소드
    1) 매칭(Maching)
        * boolean 타입의 값을 리턴
        * boolean allMatch(Predicate<? super T> predicate) : 모든 요소가 true일 경우, true를 리턴
        * boolean anyMatch(Predicate<? super T> predicate) : 하나라도 요소가 true일 경우 true를 리턴
        * boolean noneMatch(Predicate<? super T> predicate) : 모든 요소가 false인 경우, true 리턴
        ```java
        Stream<String> st0 = Stream.of("avc", "def", "gop");
        System.out.println(st0.allMatch(s -> s.equals("avc"))); // false
        
        st0 = Stream.of("avc", "def", "gop");
        System.out.println(st0.anyMatch(s -> s.equals("avc"))); // true
        
                st0 = Stream.of("avc", "def", "gop");
                System.out.println(st0.noneMatch(s -> s.equals("avc"))); // false
        ```
    
    2) 집계(통계)
        * 기본형 스트림 (Int, Long, Double) - count(), sum(), average(), min(), max()
        * Stream<T> 타입 스트림 - count(), min(), max() -> (min과 max는 Comparator 구현 필요)
        * T reduce(T identity, BinaryOperator<T> accumulator) :  identity를 초기값으로 하여, accumulator를 이용해 누적 집계 연산
            * sum()이 실제로도 reduce로 구현되어 있다.
        ```java
        IntStream.range(0, 10).reduce(0, (value1, value2) -> value1 + value2);
        ```    
    
    3) 반복
        * void forEach(Comsumer<? super T> action) : Consumer 계열의 람다식을 입력받아, 각 요소를 소비
    
    4) 수집
        * Collectors 클래스의 정적 메소드를 이용하는 방법
        * <R, A> R collect(Collector<? super T, A, R> collector) : Collection으로 변환시키는 메소드
        * Collectors.toList() : ArrayList 반환
        * Collectors.toSet() : HashSet 반환
        * Collectors.toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) : Map<K, U> 반환 
        * Collectors.toCollection(Supplier<C> collectionFactory) : 입력 값으로 Collector로 변환
        ```java
        // Collectors 클래스의 정적 메소드를 이용하는 방법
        // toList() 메소드를 쓸 경우, ArrayList로 Collect하는 Collector 반환
        String[] array = {"Collection", "Framework", "is", "so", "cool"};
        Stream<String> stream3 = Arrays.stream(array);
        List<String> collected = stream3.filter(s -> s.length() >= 3)
        //                         .collect(Collectors.toList());
                                   .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(collected);
        
        // toSet() 메소드를 쓸 경우, HashSet으로 collect하는 Collector 반환
        Stream<String> stream4 = Arrays.stream(array);
        Set<String> collected2 = stream4.filter(s -> s.length() >= 3)
                                    .collect(Collectors.toSet());
        System.out.println(collected2);
        
        // toMap() : Map<K, V> -> Map.Entry<K, V> - Key, Value를 정해줘야 된다.
        Stream<String> stream5 = Arrays.stream(array);
        Map<String, Integer> collected3 = stream5.filter(s -> s.length() >= 3)
                                    .collect(Collectors.toMap(s->s, String::length));
        System.out.println(collected3);
        ```
    
    5) 그룹화/분리
        * Map<R, List<T>> groupingBy(Function<? super T, ? extends K> classifier) : Key 값으로 Function을 입력받고, Key를 기준으로 List 타입으로 그룹화한다.
        * Map<Boolean, List<T>> partitioningBy(Predicate<? super T> predicate) : Key 값으로 Predicate를 입력받고, true, false에 따라 List 타입으로 분리된다.
        ```java
        String[] arr = {"Python", "is", "aweful", "lame", "not", "good"};
        Map<Integer, List<String>> map = Arrays.stream(arr)
                .collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(map);
        
        Map<Boolean, List<String>> map2 = Arrays.stream(arr)
                .collect(Collectors.partitioningBy(s -> s.length() < 4));
        ```
        
        * 그룹화 + Downstream collector
        * Collector 중에도 counting(), summingP(), averagingP(), maxBy(), minBy(), reducing() 이 있다.
        ```java
        Map<Integer, Long> map3 = Arrays.stream(arr)
                        .collect(Collectors.groupingBy(String::length,Collectors.counting()));
        ```

    6) 병렬 스트림
        * 생성 방법
            * stream() 대신 parallelStream() 으로 변경
            * stream 생성 후 parallel()으로 병렬화
        ```java
        Stream<String> parStream = Arrays.stream(arr).parallel();
        System.out.println(parStream.map(String::length).count());
        
        List<String> list4 = List.of("atew", "bff", "cqqwq", "dassa");
        Stream<String> stream6 = list4.parallelStream();    // 병렬이기 때문에 연산 순서가 달라질 수도 있다! 내부적으로 여러 쓰레드가 계산하기 때문에
        stream6.map(String::length)
                .peek(s -> System.out.println("A:" + s))
                .filter(value -> value > 3)
                .peek(s -> System.out.println("B:" + s))
                .forEach(System.out::println);
        ```
