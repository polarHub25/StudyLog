### 배열 복사
~~~ java 

public class ArrayCopy {

    public static void main(String[] args) {
        int[] orgArr = {1, 2, 3, 4, 5, 6};
        int[] copyArr = new int[10];
        System.arraycopy(orgArr, 0, copyArr, 3, orgArr.length);
        int[] cloneArr = orgArr.clone();

        int[] copyOfArr = Arrays.copyOf(orgArr, orgArr.length);
        System.out.println("orgArr = " + Arrays.toString(orgArr));

        orgArr[1] = 6;
        System.out.println("orgArr[1]의 값을 6으로 변경");
        System.out.println();
        
        System.out.println("orgArr = " + Arrays.toString(orgArr));
        System.out.println("copyArr = " + Arrays.toString(copyArr));
        System.out.println("copyOf = " + Arrays.toString(copyOfArr));
        System.out.println("cloneArr = " + Arrays.toString(cloneArr));


    }
}
~~~

### 실행예제
![image](https://github.com/polarHub25/StudyLog/assets/83585035/615d69b1-d682-46c1-b7e7-18c72e5afbf1)

