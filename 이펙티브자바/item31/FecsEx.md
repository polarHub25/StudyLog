### 1. FECS
~~~ java 

public class PecsEx {
    public static void printNumbers(List<? extends Number> numbers) { // Number를 상위 타입으로둔 무언가
        for (Number number : numbers) {
            System.out.println(number);//읽기만 가능
        }
//        numbers.add(10); //어떤 타입의 데이터가 들어갈지 알 수 없어 컴파일단계에서 막는다.
    }

    public static void addIntegers(List<? super Integer> numbers) { // Integer를 하위 타입으로둔 무언가
        numbers.add(10); //어떤 타입의 데이터가 들어갈지 어느정도 특정되기 때문에 가능하다.
        numbers.add(20);


    }

    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        addIntegers(intList);
        printNumbers(intList);

        List<Number> numList = new ArrayList<>();
        addIntegers(numList);
        printNumbers(numList);


    }
}
~~~
