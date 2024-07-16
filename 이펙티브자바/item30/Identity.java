import java.util.function.Function;
import java.util.function.UnaryOperator;

class Identity{

        public static void main(String[] args) {
        // Function.identity() 예제
        Function<String, String> functionIdentity = Function.identity();
        String result1 = functionIdentity.apply("Hello, Function.identity()!");
        System.out.println("Function.identity() 결과: " + result1);

        // 직접 구현한 항등함수 예제
        Function<String, String> customIdentity = s -> s;
        String result2 = customIdentity.apply("Hello, custom identity function!");
        System.out.println("Custom identity function 결과: " + result2);

        // UnaryOperator.identity() 예제
        UnaryOperator<String> unaryOperatorIdentity = UnaryOperator.identity();
        String result3 = unaryOperatorIdentity.apply("Hello, UnaryOperator.identity()!");
        System.out.println("UnaryOperator.identity() 결과: " + result3);

        // Function을 이용한 변환 함수와 함께 사용
        Function<String, String> toUpperCase = s -> s.toUpperCase();
        String result4 = process("hello, world!", toUpperCase);
        System.out.println("Upper case 변환 결과: " + result4);

        String result5 = process("hello, world!", Function.identity());
        System.out.println("Function.identity()를 이용한 변환 결과: " + result5);

        // 조건부 변환 예제
        String result6 = conditionalTransform("hello, conditional world!", false, toUpperCase);
        System.out.println("조건부 변환 (변환 안함) 결과: " + result6);

        String result7 = conditionalTransform("hello, conditional world!", true, toUpperCase);
        System.out.println("조건부 변환 (변환 적용) 결과: " + result7);

        // 함수 조합 예제
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> addThree = x -> x + 3;
        Function<Integer, Integer> combined = multiplyByTwo.andThen(UnaryOperator.identity()).andThen(addThree);

        Integer result8 = combined.apply(5);
        System.out.println("함수 조합 결과: " + result8);

        // 항등함수를 사용하지 않았을 경우 
        String inputNoIdentity = "Hello, World!";
        
        // 변환 함수 없이 null 전달
        String resultNoIdentity = process(inputNoIdentity, null);
        
        System.out.println("항등함수를 사용하지 않았을 경우: "+ resultNoIdentity); // 출력: Hello, World!

    }

    // 다양한 변환 함수를 처리하는 메소드
    public static <T> T process(T input, Function<T, T> transformer) {
        return transformer.apply(input);
    }

    // 조건부 변환을 처리하는 메소드
    public static <T> T conditionalTransform(T input, boolean shouldTransform, Function<T, T> transformer) {
        Function<T, T> effectiveTransformer = shouldTransform ? transformer : Function.identity();
        return effectiveTransformer.apply(input);
    }

    // 항등함수를 사용하지 않았을 경우 
    public static <T> T processNoIdentityMethod(T input, Function<T, T> transformer) {
        if (transformer == null) {
            return input;
        }
        return transformer.apply(input);
    }
}