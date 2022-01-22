import java.util.*;
import java.util.function.Consumer;
import java.util.stream.*;

/**
 * Stream API
 * - Java 8에 추가된 java.util.stream 패키지
 * - 컬렉션 요소를 람다식으로 처리할 수 있도록 돕는 함수형 프로그래밍 도구
 * - 간결한 코드로 작성할 수 있다.
 * - 데이터 소스에 대한 공통된 접근 방식 제공
 *  - Stream으로 변경해주고 나면, List, Set, Map 모두 동일한 방식으로 데이터를 처리할 수 있다는 장점
 *
 */

public class StreamAPI {
    String p = "";
    public void ccc(){
        int q = 1;
        Consumer<String> a =(s) -> {

            System.out.println(q);
        };
    }
    public static void main(String[] args) {
        List<String> list = Arrays.asList("fast", "campus", "rocks");
        List<String> newList = new ArrayList<>();

        for (String s: list) {
            newList.add(s.toUpperCase());
        }

        for (String s: newList) {
            System.out.println(s);
        }

        // JAVA 8 - Stream API -> 훨씬 더 간결한 코드로 작성
        List<String> list1 = Arrays.asList("fast", "campus", "rocks");
        Stream<String> stream = list1.stream();
        stream.map(String::toUpperCase).forEach(System.out::println);


        // Stream 생성 방식 1. Collection의 인스턴스 메소드 stream()
        Stream<String> stream1 = list1.stream();

        // Stream 생성 방식 2. Array를 이용하는 방법, Arrays.stream()
        int[] ints = {4, 6, 2, 19, 2, 58, 4, 6, 5};
        IntStream intStream = Arrays.stream(ints);
        // LongStream, DoubleStream

        // Stream 생성 방식 3. Stream 클래스의 클래스 메소드 of, Stream.of()
        DoubleStream doubleStream = DoubleStream.of(0.4, 0.6, 0.2, 1.2);

        // range를 이용한 스트림 -> for i문을 대체하는 스트림
        IntStream intStream1 = IntStream.range(0, 10);  // 10은 포함되지 않는다.
        intStream1.forEach(System.out::println);
        IntStream intStream2 = IntStream.rangeClosed(0, 10); // 10이 포함된다.
        // LongStream도 range, rangeClosed가 있다.

        // Random 객체를 이용한 스트림
        Random random = new Random();
        LongStream longStream = random.longs(100);  // 100개
        longStream.forEach(System.out::println);

        LongStream longStream2 = random.longs(100, 0, 1000);

        ////////////////////////////////////

        Stream<String> stringStream = Stream.of("Java", "Is", "Fun", "Isn't", "It", "?", "Java", "Java");

        // 중간 처리 메소드 - Stream을 반환

        // Filtering 메소드
        // distict() - 스트림에 같은 요소가 있을 경우 하나만 남기고 삭제하는 메소드
        stringStream.distinct().forEach(System.out::println);

        // filter() : Predicate 계열을 입력으로 받아, true인 요소만 남긴다.
        stringStream = Stream.of("Java", "Is", "Fun", "Isn't", "It", "?", "Java", "Java");
        stringStream.filter(s -> s.length() >= 3)
                .forEach(System.out::println);

        // Cutting 메소드
        // skip(long n) : 스트림의 최초 n개를 생략하는 메소드
        // limit(long maxSize) : 스트림의 최대 요소 개수를 maxSize로 전환

        // Sorting 메소드
        stringStream = Stream.of("abc", "fwf", "dnmov", "work");
        stringStream.sorted().forEach(System.out::println);         // String 객체의 comateTo를 사용

        stringStream = Stream.of("abc", "fwf", "dnmov", "work");
        stringStream.sorted((o1, o2) -> o1.length() - o2.length())   // Comparator 인터페이스를 람다식으로 구현
                .forEach(System.out::println);
        System.out.println();


        // Mapping 메소드
        // Funcation 계열의 인터페이스를 사용하여 스트림의 각 요소를 매핑
        stringStream = Stream.of("abc", "fwf", "dnmov", "work");
        // Function 계열로 String -> Integer로 변환하는 매핑, Function<String, Integer>
        Stream<Integer> stream2 = stringStream.map(s -> s.length());
        stream2.forEach(System.out::println);


        // PStream(기본형 타입의 스트림), 형 변환이 안됨
        IntStream intStream3 = IntStream.of(5, 2, 30, 8, 0, 2, -34);
        IntStream intStream4 = intStream3.map(value -> value * 10);
        intStream4.forEach(System.out::println);

        // flatMap 계열
        // 기본 매핑은 입력 1:1 출력
        // flatMap은 입력 1:n 출력
        List<String> list2 = Arrays.asList("java", "backend", "best", "course");
        System.out.println(list2.stream().flatMap(data -> {
            return Arrays.stream(data.split(""));
        }).distinct().count());


        // 조회 (Peek) - 중간 결과를 출력해 볼 수 있음 (디버깅 기능)
        // peek() : Consumer 계열의 람다식으로 입력받아 입력 요소를 소비
        list2 = Arrays.asList("java", "backend", "best", "course");
        list2.stream().flatMap(data -> {
            return Arrays.stream(data.split(""));
        }).peek(s -> {
            System.out.println(s);
        }).distinct().forEach(System.out::println);


        // 최종 처리 메소드 - Stream을 반환하지 않음

        // 매칭 (Machiing) - boolean 타입의 값을 리턴
        // allMatch(), anyMatch(), noneMach()
        // Predicate 계열 람다식을 입력받아,
        //  allMatch(Predicate<T> predicate) : 모든 요소가 true일 경우, true를 리턴
        //  anyMatch(Predicate<T> predicate) : 하나라도 요소가 true일 경우 true를 리턴
        //  noneMatch(Predicate<T> predicate) : 모든 요소가 false인 경우, true 리턴
        Stream<String> st0 = Stream.of("avc", "def", "gop");
        System.out.println(st0.allMatch(s -> s.equals("avc"))); // false

        st0 = Stream.of("avc", "def", "gop");
        System.out.println(st0.anyMatch(s -> s.equals("avc"))); // true

        st0 = Stream.of("avc", "def", "gop");
        System.out.println(st0.noneMatch(s -> s.equals("avc"))); // false


        // 집계 (통계)
        // 기본형 스트림 (Int, Long, Double) - count(), sum(), average(), min(), max()
        // Stream<T> 타입 스트림 - count(), min(), max() -> (min과 max는 Comparator 구현 필요)

        // reduce() 메소드 - 사용자 정의 집계 메소드
        IntStream.range(0, 10).reduce(0, (value1, value2) -> value1 + value2);
        // 누적 집계가 되는데, identity는 처음 최초 값을 의미한다.
        // sum()이 실제로도 reduce로 구현되어 있다.

        // min()
        IntStream.range(0, 10).reduce(Integer.MAX_VALUE, (value1, value2) -> value1 > value2 ? value2 : value1);


        // 반복
        // forEach() : Consumer 계열의 람다식을 입력받아, 각 요소를 소비



        // 수집
        // collect() : Collection으로 변환시키는 메소드
        // Stream API는 JCF -> Stream -> 처리 -> 결과(출력, 값, Collection)


        // Collectors 클래스의 정적 메소드를 이용하는 방법
        // toList() 메소드를 쓸 경우, ArrayList로 Collect하는 Collector 반환
        String[] array = {"Collection", "Framework", "is", "so", "cool"};
        Stream<String> stream3 = Arrays.stream(array);
        List<String> collected = stream3.filter(s -> s.length() >= 3)
//                                        .collect(Collectors.toList());
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


        // 그룹화/분리 - groupingBY, partitioningBy
        // Map<Boolean, List<T>> partitioningBy() : Predicate를 입력받아, Key 값이 True, False가 된다.
        // Map<R, List<T>> groupingBy(R Function<T>): Function을 입력받아, Key 값이 R Type이 된다.

        String[] arr = {"Python", "is", "aweful", "lame", "not", "good"};
        Map<Integer, List<String>> map = Arrays.stream(arr)
                .collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(map);

        Map<Boolean, List<String>> map2 = Arrays.stream(arr)
                .collect(Collectors.partitioningBy(s -> s.length() < 4));

        // 그룹화 + Downstream collector
        // 최종 처리 메소드에서 있던 count(), min()... 등과 유사한
        // Collector 중에도 counting(), minBy(), maxBy() ... 등이 있다.
        Map<Integer, Long> map3 = Arrays.stream(arr)
                .collect(Collectors.groupingBy(String::length,Collectors.counting()));
        // Value에 리스트 안의 갯수를 파악해 입력된다.



        // 병렬 스트림
        Stream<String> parStream = Arrays.stream(arr).parallel();
        System.out.println(parStream.map(String::length).count());

        List<String> list4 = List.of("atew", "bff", "cqqwq", "dassa");
        Stream<String> stream6 = list4.parallelStream();    // 병렬이기 때문에 연산 순서가 달라질 수도 있다! 내부적으로 여러 쓰레드가 계산하기 때문에
        stream6.map(String::length)
                .peek(s -> System.out.println("A:" + s))
                .filter(value -> value > 3)
                .peek(s -> System.out.println("B:" + s))
                .forEach(System.out::println);

    }
}
