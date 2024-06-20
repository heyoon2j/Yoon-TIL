import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.ElementType.*;

/**
 * 어노테이션 (Annotations)
 * - 어노테이션의 사전적 의미는 "주석"
 * - JVM, 컴파일러, 프레임워크 등에서 사용할 수 있도록 전달하는 메타데이터
 *
 * 기본 어노테이션
 * - @Override, @Deprecated, @SuppressWarnings, @FunctionalInterface
 * - Deprecated : 앞으로 사용되지 않을 클래스/메소드/변수 ... 사용하지 말라는 의미
 * - SuppressWarnings : 특정 경고 메시지를 무시하도록 컴파일러에 전달
 *   - @SuppressWarnings("unused")
 *
 */

// @Target, @Retention : 메타 어노테이션
// @Target : 어노테이션을 사용할 수 있는 대상을 설정
// TYPE : 클래스, 인터페이스, 어노테이션, 열겨형
// FILED: 필드(멤버 변수), 열거형 상수
// PARAMETER : 메소드의 입력 파라미터
// CONSTRUCTOR : 클래스의 생성자
// LOCAL_VARIABLE : 로컬 변수
// MODULE : 모듈
// ANNOTATION_TYPE : 어노테이션
// PACKAGE : 패키지

// @Retention
// 어노테이션 정보를 어디까지 유지할 것인가를 결정하는 Policy
// SOURCE : 컴파일러가 사용. .java -> .class로 변경한느 과정에서 쓰임 .class에 포함 X
// CLASS : .class에 포함. JVM에 포함되지 않음
// RUNTIME : .class에 포함. JVM에 올라와서 Reflection API에서 사용 가능
@Target({TYPE, FIELD, PARAMETER, CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
@interface SuppressWarningsClone {
    String[] value();
}

/**
 * 멤버 변수를 대상으로 하는, Reflection API에서 쓸 수 있는 어노테이션
 */
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String[] value(); // 어노테이션 속성 설정 (기본 속성 이름은 value)
    String valueTwo() default "기본값";
}

class AnnotationUsage {
    @MyAnnotation("game")
    String gameName = "여러분의 틱택토";

    // value가 String[] 이지만 하나만 쓸 수 있도록 허용
    @MyAnnotation(value = "server", valueTwo = "localHost")
    String serverIP;

    @MyAnnotation(value = "server", valueTwo = "8080")
    String serverPort;

    @MyAnnotation("game")
    String gameMode = "AI vs AI";

    @MyAnnotation(value = "db", valueTwo = "localhost")
    String database;
}


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        @SuppressWarnings("unsued")
        int x;

        AnnotationUsage obj = new AnnotationUsage();
        Map<String, Object> gameProp = new HashMap();
        Map<String, Object> serverProp = new HashMap<>();
        Map<String, Object> dbProp = new HashMap<>();

        Field[] fields = AnnotationUsage.class.getDeclaredFields();
         for (Field field : fields) {
             MyAnnotation annotation = field.getDeclaredAnnotation(MyAnnotation.class);
             if(field.get(obj) == null) {
                 field.set(obj, annotation.valueTwo());
             }

             if (annotation.value()[0].equals("game")) {
                 gameProp.put(field.getName(), field.get(obj));
             } else if (annotation.value()[0].equals("server")) {
                 serverProp.put(field.getName(), field.get(obj));
             } else {
                 dbProp.put(field.getName(), field.get(obj));
             }
         }
    }
}
