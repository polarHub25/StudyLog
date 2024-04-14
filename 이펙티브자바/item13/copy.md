### 얕은 복사, 깊은 복사 예제
~~~ java 

public class Copy {

    public static void main(String[] args) {
        int[] orgArr = {1, 2, 3};
        int[] shallowArr = orgArr; // 얕은 복사
        int[] deepArr = orgArr.clone();  // 깊은 복사

        System.out.println("orgArr" + Arrays.toString(shallowArr));
        System.out.println("shallowArr" + Arrays.toString(shallowArr));
        System.out.println("deepArr = " + Arrays.toString(deepArr));
        System.out.println("");

        orgArr[1] = 6;
        System.out.println("orgArr의[1] 값을 6으로 수정");

        System.out.println("");
        System.out.println("orgArr" + Arrays.toString(shallowArr));
        System.out.println("shallowArr" + Arrays.toString(shallowArr));
        System.out.println("deepArr = " + Arrays.toString(deepArr)); // 깊은 복사는 값이 변경되지 않았다.

    }
}
~~~

### 실행예제
![image](https://github.com/polarHub25/StudyLog/assets/83585035/7d0e750f-f09f-4c8f-814f-070d1e93779d)
