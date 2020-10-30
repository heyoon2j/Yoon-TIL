import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규표현식 (Regular Expression)
 * - 특정 조건에 맞는 문자열을 검색(Search) / 치환(Replace)하는 패턴 기반의 식
 * - 이메일, 전화번호 등 특정한 형식에 맞게 적혀 있는지 validation하는데에 사용 가능
 * - Crawling 등 날 것의 자료를 긁어 모았을 때 정리하는 데에 유용
 * - 정규표현식은 느리기 때문에 남용하면 안된다.
 *   - ex) 알고리즘 문제를 푸는데 정규 표현식을 쓰는건 안된다.
 *
 * Reference Site : https://regexper.com/
 */

public class Main {
    public static void main(String[] args) {
//        [a-zA-Z0-9가-힣]
        String regex = "[0-9]*";
        String str = "1234124";

        // Patter.matches() Method
        // String.matches()
        System.out.println(Pattern.matches(regex, str));

        // 자바에서는 \도 문자열이기 때문에 두 개를 써야 \가 된다..
        // \b 경계(boundary)는 시작, 끝, 공백, 문장기호로 분리해서 찾는다.
        Pattern pattern = Pattern.compile("\\bJava\\b");
        // Javac는 못 찾음
        Matcher matcher = pattern.matcher("Java is Java and Java will be Java.");

        // 일반적으로 정규 표현 식 / /g 이런식으로 사용하지만
        // Java에서는 사용 불가해서 파라미터로 입력 받는다.
        while (matcher.find()) {
            System.out.println(matcher.start() +", "+ matcher.end());
        }

//        Pattern pattern1 = Pattern.compile("^(\\w+):(\\w+)\\.(\\w+)$");
        // ?<>를 이용해서 그룹에 이름을 넣을 수 있다.
        Pattern pattern1 = Pattern.compile("^(?<field>\\w+):(?<name>\\w+)\\.(?<ext>\\w+)$");
        Matcher matcher1 = pattern1.matcher("filename:temp.png");

        matcher1.find();
        System.out.println(matcher1.group());   // 매칭된 전체가 출력
        System.out.println(matcher1.group(0));  // 매칭된 전체가 출력
        System.out.println(matcher1.group(1));  // 첫번째 그룹
        System.out.println(matcher1.group(2));
        System.out.println(matcher1.group(3));

        System.out.println(matcher1.group("field"));
        System.out.println(matcher1.group("name"));
        System.out.println(matcher1.group("ext"));

//        Pattern pattern1 = Pattern.compile("ID:\\s");
//        Matcher matcher1 = pattern.matcher("ID: Ssamzang");
//
//        System.out.println(matcher1.lookingAt());   // true, 패턴으로 시작하는지 확인
//        String str2 = matcher1.replaceFirst(""); // 패턴을 찾아 바꿔준다.
//        System.out.println();

    }
}
