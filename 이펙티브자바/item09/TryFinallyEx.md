### tryFinally 예제
~~~ java 

public class TryFinallyEx {

    public static void main(String[] args){
        try {
            throw new RuntimeException("try");
        } finally{
            throw new RuntimeException("finally"); //이부분을 주석을 통해서 확인
        }
    }
}
~~~

### 실행예제
finally code를 주석으로 처리한 경우 try 발생한 RuntimeException이 출력됨
![image](https://github.com/polarHub25/StudyLog/assets/83585035/7251249e-4d05-4ed5-bfb9-f345de25024e)

finally code를 주석을 해제한 경우 finally에서 발생한 RuntimeException이 출력됨
![image](https://github.com/polarHub25/StudyLog/assets/83585035/f627d9aa-6fff-45e5-a54b-98e4b6ba531c)
