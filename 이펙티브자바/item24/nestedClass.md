### 1. 정적 멤버 클래스
~~~ java 

public class OuterStatic {
    private static int staticField = 10;
    private int nonStaticField = 20;
    static class StaticNestedClass {
        void display() {
            // 외부 클래스의 static 필드에 접근할 수 있음
            System.out.println("Static field: " + staticField);
            // 외부 클래스의 non-static 필드에는 접근 불가
            // System.out.println("Non-static field: " + nonStaticField); // 오류 발생
        }
    }
    public static void main(String[] args) {
        // 외부 클래스의 생성 없이 바로 정적 멤버 클래스 인스턴스를 생성할 수 있음
        OuterStatic.StaticNestedClass nestedClass = new OuterStatic.StaticNestedClass();
        nestedClass.display();
    }
}
~~~

### 2. 비정적 멤버 클래스
~~~ java 

public class OuterInner {
    private int nonStaticField = 20;
    private int staticField = 10;

    class InnerClass {
        void display() {
            // 외부 클래스의 nonstatic 필드와 static 필드 모두 접근할 수 있음
            System.out.println("Non-static field: " + nonStaticField);
            System.out.println("staticField = " + staticField);
        }
    }

    public static void main(String[] args) {
        OuterInner outer = new OuterInner();
        // 비정적 멤버클래스는 인스턴스가 독립적으로 생성될 수 없음
        OuterInner.InnerClass inner = outer.new InnerClass();
        inner.display();
    }
}
~~~

### 3. 지역 클래스
~~~ java 

public class OuterLocal {
    private int nonStaticField = 20;

    void myMethod() {
        final int localVariable = 30;
        int functionVariable = 40;

        class LocalClass {
            void display() {
                // 외부 클래스의 필드에 접근할 수 있음
                System.out.println("Non-static field: " + nonStaticField);
                // 메서드의 로컬 변수에 접근할 수 있음
                System.out.println("Local variable: " + localVariable);
                System.out.println("functionVariable = " + functionVariable);
            }
        }

        LocalClass local = new LocalClass();
        //메서드 내에서만 사용가능
        local.display();
    }

    public static void main(String[] args) {
        OuterLocal outer = new OuterLocal();
        outer.myMethod();
    }
}
~~~

### 4. 익명 클래스
~~~ java 

public class OuterAnonymous {
    private int nonStaticField = 20;

    void myMethod() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 외부 클래스의 필드에 접근할 수 있음
                // Runnable에 run을 상속 없이 재정의하여 사용가능
                System.out.println("Non-static field: " + nonStaticField);
            }
        };
        runnable.run();
    }

    public static void main(String[] args) {
        OuterAnonymous outer = new OuterAnonymous();
        outer.myMethod();
    }
}
~~~
