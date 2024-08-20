### 1. 메서드 참조 유형
~~~ java 

public class MethodReference {

    public static void main(String[] args) {
        // 정적 메서드 참조: Integer 클래스의 parseInt 메서드 참조
        Function<String, Integer> strToInt = Integer::parseInt;

        // "123" 문자열을 정수로 변환
        Integer result = strToInt.apply("123");

        System.out.println(result);  // 출력: 123

        //

        // 한정적 메서드 참조: 이미 존재하는 특정 객체의 length 메서드 참조
        String str = "Hello";
        Supplier<Integer> strLength = str::length;

        // 해당 객체(str)의 길이를 계산
        Integer length = strLength.get();

        System.out.println(length);  // 출력: 5

        // 비한정적 메서드 참조: String 클래스의 length 메서드 참조
        Function<String, Integer> strLength2 = String::length;

        // "Hello" 문자열의 길이를 계산
        Integer length2 = strLength2.apply("Hello");

        System.out.println(length);  // 출력: 5
    }
}
~~~
