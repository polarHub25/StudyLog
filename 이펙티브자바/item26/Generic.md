### 1. Generic
~~~ java 

public class Generic {

    void test1(List<?> list){
        list.add(list.get(0)); // 컴파일 오류 
    }
    <T> void test2(List<T> list){ // 타입이 특정되었기 때문에 컴파일 성공
        list.add(list.get(0));
    }
    void test3(List<? super Number > list){ // 한정적 와일드 카드를 선언하여 Number를 상속받는 타입을 허용하기 때문에 컴파일 성공
        list.add(0);
    }
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        List<String> strings = new ArrayList<>();
        strings.add("1");
        Generic generic = new Generic();
        generic.test1(integers);
    }
}
~~~
