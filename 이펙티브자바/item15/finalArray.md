### 얕은 복사, 깊은 복사 예제
~~~ java 

public class FinalArray {
    public static final int[] finalArr = {1, 2, 3, 4, 5};
    public static int[] arr = {6, 7, 8, 9, 10};
    public static void main(String[] args) {

        System.out.println("finalArr = " + Arrays.toString(finalArr));
        System.out.println("finalArr 주소값 = " + finalArr);
        System.out.println();

//      finalArr[0]의 다른 값을 할당
        finalArr[0] = 0;
        System.out.println("finalArr = " + Arrays.toString(finalArr));
        System.out.println("finalArr 주소값 = " + finalArr);

//      finalArr = new int[5]; 새로운 길이의 배열을 할당할 수 없다 컴파일 오류
        System.out.println();
        System.out.println("arr의 주소 값 = " + arr);
        arr = new int[5];
        System.out.println("arr의 주소 값이 변경되었다. = " + arr);
    }
}
~~~

### 실행예제
![image](https://github.com/polarHub25/StudyLog/assets/83585035/80dc0ec1-8c70-469d-936b-6bc5b277b06d)
