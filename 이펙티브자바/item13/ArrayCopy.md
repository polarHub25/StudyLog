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

### 속도 비교
~~~ java 

public class ArrayCopy {

    public static void main(String[] args) {
        int[] orgArr = new int[1000000];
        int[] copyArr = new int[1000000];
        long startTime = System.nanoTime();
        System.arraycopy(orgArr, 0, copyArr, 0, orgArr.length);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("arraycopy time: " + duration + " milliseconds");
        long startTime2 = System.nanoTime();
        int[] cloneArr = orgArr.clone();
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;
        System.out.println("clone time: " + duration2 + " milliseconds");
        long startTime3 = System.nanoTime();
        int[] copyOfArr = Arrays.copyOf(orgArr, orgArr.length);
        long endTime3 = System.nanoTime();
        long duration3 = endTime3 - startTime3;
        System.out.println("copyof time: " + duration3 + " milliseconds");


    }
}
~~~

### 실행예제
![image](https://github.com/polarHub25/StudyLog/assets/83585035/5a4a6213-2533-4144-a6db-fff76262d406)
