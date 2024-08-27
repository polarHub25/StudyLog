# Stream

> 중간 연산 (Intermediate Operations)
- 중간 연산들은 스트림을 변환하거나 필터링
- filter(Predicate): 주어진 조건에 맞는 요소만을 필터링합니다.
- map(Function): 각 요소를 주어진 함수로 변환합니다.
- flatMap(Function): 각 요소를 스트림으로 변환하고, 이를 하나의 스트림으로 병합합니다.
- distinct(): 중복된 요소를 제거합니다.
- sorted(): 요소를 정렬합니다.
- limit(long): 스트림의 요소 수를 제한합니다.
- skip(long): 처음 몇 개의 요소를 건너뜁니다.

> 최종 연산 (Terminal Operations)
- 최종 연산들은 스트림을 닫고, 최종 결과를 반환
- forEach(Consumer): 각 요소를 순회하며 작업을 수행합니다. 주로 출력 등의 부수 효과를 발생시킵니다.
- collect(Collector): 스트림의 요소들을 수집하여 리스트, 집합, 맵 등의 컬렉션으로 변환합니다.
- reduce(BinaryOperator): 스트림의 요소들을 하나의 값으로 누적하여 결과를 반환합니다.
- count(): 스트림의 요소 수를 반환합니다.
- anyMatch(Predicate): 조건을 만족하는 요소가 하나라도 있으면 true를 반환합니다.
- allMatch(Predicate): 모든 요소가 조건을 만족하면 true를 반환합니다.
- noneMatch(Predicate): 모든 요소가 조건을 만족하지 않으면 true를 반환합니다.
- findFirst(): 스트림의 첫 번째 요소를 반환합니다.
- findAny(): 병렬 스트림에서 요소 중 하나를 반환합니다.
- 
> 지연 평가
 - 스트림은 최종 연산이 호출되기 전까지는 중간 연산들을 쌓아두기만 하고, 실질적인 데이터 처리 작업은 지연

> 스트림 동작 방식
``` java

public class StreamLazyEvaluation {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Tom", "Jerry"); 

        names.stream()
            .filter(name -> {
                System.out.println("filter: " + name); // 필터링 단계에서 출력
                return name.startsWith("J");
            })
            .map(name -> {
                System.out.println("map: " + name); // 맵핑 단계에서 출력
                return name.toUpperCase();
            })
            .forEach(name -> System.out.println("forEach: " + name)); // 최종 처리 단계에서 출력
    }
}

/*
출력결과

filter: John
map: John
forEach: JOHN
filter: Jane
map: Jane
forEach: JANE
filter: Tom
filter: Jerry
map: Jerry
forEach: JERRY

*/
```
- names 의 요소 하나씩 처리됨
- 스트림이 한 번에 모든 데이터를 중간 연산으로 처리하지 않고, 첫 번째 요소에 대해 모든 중간 연산이 끝나면 그 결과를 최종 연산으로 전달하고, 그 다음 요소로 넘어가서 동일한 과정을 반복
- 종단 연산이 없는 스트림 파이프라인은 아무일도 하지않음 

> 무한 스트림
- 무한 스트림은 끝이 없는, 무한한 양의 데이터를 생성하는 스트림
- ex) 무한한 자연수, 무작위 수열 ..
- 스트림에서 지연 평가(lazy evaluation)가 필요한 데이터만 계산하고 즉시 결과를 반환하므로 무한 스트림을 다룰수있음



















