### 강한 참조 약한 참조 HashMap 예제
~~~ java 

public class ReferenceMain {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        //일반적인 StrongReference로 감싸져 HashMap에 할당된다.
        HashMap<Object, String> strongMap = new HashMap<>();
        //WeakReference로 감싸져 HashMap에 할당된다.
        WeakHashMap<Object, String> weakMap = new WeakHashMap();

        strongMap.put(obj1, "strongTest");
        weakMap.put(obj2, "weakTest");

        obj1 = null;
        obj2 = null;

        System.gc();

        System.out.println("strongMap의 key를 출력한다.");
        //strongMap은 obj1을 null로 설정 후 gc가 실행되었지만 강한참조로 인해 map에 key가 남아있다.
        strongMap.keySet().stream().forEach(e1 -> System.out.println("strongMap = " + e1));

        System.out.println("weakMap의 key를 출력한다.");
        //weakMap은 obj2를 null로 설정 후 gc가 실행되어 참조가 해제되어 출려되지 않는다.
        weakMap.keySet().stream().forEach(e2 -> System.out.println("weakMap = " + e2));

    }
}
~~~
### 실행 결과

![image](https://github.com/polarHub25/StudyLog/assets/83585035/c4fa4241-51eb-4e42-bafa-26f085249bea)


