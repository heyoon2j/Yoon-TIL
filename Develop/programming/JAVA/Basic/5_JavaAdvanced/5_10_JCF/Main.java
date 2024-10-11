import java.util.*;

/**
 * Java Collections Framework (JCF)
 * - java.util 에 속한 일련의 클래스, 자료구조를 담당
 *     - 자료구조: 자료의 집합 또는 그 집합의 동작을 정의하는 수학적 모델
 * - 제네릭 클래스로 되어 있어, 다양한 객체를 요소(Element)로 담을 수 있다.
 *     - 요소: 자료구조를 구성하는 하나하나의 자료
 */


public class Main {
    public static void main(String[] args) {
        // List 인터페이스
        // - Collection 인터페이스 상속
        // - 순서가 있는 데이터의 집합. 데이터 중복 허용
        // - 데이터의 순서(index)가 유일한 데이터의 구분자(identifier)로 사용
        // [1, 3, 2, 5, 6, 2, 1] -> 같은 값이 있으나, index가 다름
        List<String> stringList = new ArrayList<>();    // 가장 많이 쓰이는, 배열 기반의 리스트
        List<String> stringList2 = new LinkedList<>();  // 노드의 연결로 구성된 리스트
        List<String> stringList3 = new Vector<>();      // 멀티스레드 환경에서 안전하게 동작하나... 너무 느리다!!!
        List<String> stringList4 = new Stack<>();       // Vector를 더 제한한 방법, Stack 자료구조 구현

        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intList.add(i);
        }
        System.out.println(intList);
        System.out.println(intList.size());

        intList.add(2, 678494); // 중간 index에 add할 경우 자료를 뒤로 한 칸씩 민다.
        System.out.println(intList);
        System.out.println(intList.size());

        List<Integer> intList2 = new LinkedList<>();
        for (int i = 5; i < 10; i++) {
            intList2.add(i);
        }

        // addAll() : 입력된 Collection 내용 전체를 한 번에 add하는 메소드
        // index를 입력받아 위치도 지정 가능
        intList.addAll(1, intList2);
        System.out.println(intList);

        // get(int index) : 해당 Index의 값 반환
        System.out.println(intList.get(3));

        // indexOf() : 객체를 찾아 가장 첫 번째 인덱스를 반환
        System.out.println(intList.indexOf(9));

        // lastIndexOf() : 객체를 찾아 가장 마지막 인덱스를 반환
        System.out.println(intList.lastIndexOf(9));

        // remove() : 해당 index에서 obj를 제거하고 그것을 반환
        // 요소를 제거되면, 그 뒤 요소들은 모두 index가 하나씩 앞으로 당겨짐
        System.out.println(intList.remove(9));

        // set() : 해당 index의 obj 값을 변경시킨다.
        intList.set(1, 100);
        System.out.println(intList);

        //
        List<Integer> list3 = intList.subList(2, 5);
        System.out.println(list3);

        // fori를 이용한 접근
        for (int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i));
        }

        // foreach를 이용한 접근
        for (int value : list3) {
            System.out.println(value);
        }

        // listIterator를 이용한 접근
        ListIterator<Integer> iter = list3.listIterator();
        while (iter.hasNext()) {
            Integer integer = iter.next();
            System.out.println(integer);
        }


        // Set 인터페이스
        // - Collection 인터페이스 상속
        // - 순서가 없는 데이터 {집합}을 다루는 인터페이스
        // - 중복되는 데이터를 효율적으로 제거하는데에 사용 가능
        // - 중복을 검사하는 수단 1.hashCode(), 2.equals()
        //   - hash를 빠르게 계산해서 hash만 비교
        //   - 그 다음에 판정이 안 나면 equals()로 추가 비교

        Set<String> stringSet1 = new HashSet<>();   // 기본적인 집합
        stringSet1.add("A");
        stringSet1.add("B");
        stringSet1.add("B");    // 중복되기 때문에 저장되지 않는다.
        stringSet1.add("F");


        class Foo {
            int x, y;

            public Foo(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString() {
                return x + ", " + y;
            }
        }

        NavigableSet<Foo> set = new TreeSet<>(Comparator.comparingInt(o -> o.x));
        set.add(new Foo(1, 100));
        set.add(new Foo(4, 50));
        set.add(new Foo(0, 150));
        set.add(new Foo(-2, 3300));

        System.out.println(set.first());    // -2, 3300
        System.out.println(set.last());     // 4, 50
        System.out.println(set.lower(new Foo(1, 500))); // 0, 150,
        System.out.println(set.floor(new Foo(1, 500))); // 1, 100
        System.out.println(set.higher(new Foo(2, 500)));    // 4, 50
        System.out.println(set.ceiling(new Foo( 1, 500)));  // 1, 100

        System.out.println(set.pollFirst());    // first를 가지고 오면서 삭제한다.
        System.out.println(set.pollFirst());
        System.out.println(set.pollFirst());



        // Map 인터페이스
        // - Collection 인터페이스 상속 X
        // - Key, Value 쌍으로 구성된 자료의 집합을 다루는 인터페이스
        //   - Map.Entry<K, V>
        // - Key는 중복될 수 없으며, Value는 중복이 가능
        //   - Key가 identifier 역할을 한다.

        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map3 = new Hashtable<>();  // Vector처럼 옛날 구현, synchronized
        Properties prop = new Properties(); // HashTable<String, String>을 상속
        prop = System.getProperties();

        // put() : Map에 추가하는 메소드, 기존에 동일 key가 있으면 기존 value를 반환, 없었으면 null
        System.out.println(map.put("ABCDE", 5));
        System.out.println(map.put("CDEF", 1023));
        System.out.println(map.put("ABCDE", 1023)); // 중복되어

        // get() : query (질의)
        System.out.println(map.get("CDEF"));
        System.out.println(map.getOrDefault("CDEF", 0));    // key가 없으면, defaultValue를 반환
        System.out.println(map.getOrDefault("ZZZZ", 0));    // key가 있으면, value 반환

        // 이렇게 기존 값이 없을 때, 0 등으로 기본 값을 설정하고 싶으면 편리함
        map.put("ABCDE", map.getOrDefault("ABCDE", 0) + 1);

        // KeySet 사용
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }

        // KeySet보다 더 좋은 방법이다.
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }


        NavigableMap<String, Integer> map2 = new TreeMap<>();
        map2.put("a", 10);
        map2.put("g", 12);
        map2.put("z", 14);
        map2.put("z", 114); // 중복이므로 14 -> 114로 업데이트 됨

        System.out.println(map2.ceilingEntry("b").getValue());
        System.out.println(map2.pollFirstEntry().getValue());       // 첫 번째 Entry 삭제하고, Entry 반환


    }
}
