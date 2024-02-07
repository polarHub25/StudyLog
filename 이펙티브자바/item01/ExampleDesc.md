### 정적 팩토리 메소드 

1. 이름을 가질수 있다.

~~~ java 

class Car {
    private String color;
    private String model;

    // private 생성자
    private Car(String color, String model) {
        this.color = color;
        this.model = model;
    }

    // 정적 팩터리 메소드
    public static Car createRedKia() {
        return new Car("Red", "Kia Model S");
    }
    
    // 메소드
    public void drive() {
        System.out.println(model + " is driving.");
    }

    public void stop() {
        System.out.println(model + " has stopped.");
    }
}

~~~
   
2. 호출될때 마다 인스턴스를 새로 생성하지는 않아도 된다.

~~~ java

class Car {
    private String color;
    private static Car instance;

    private Car(String color) {
        this.color = color;
    }

    // 정적 팩터리 메소드
    public static Car getInstance(String color) {
        if (instance == null) {
            instance = new Car(color);
        }
        return instance;
    }
}

~~~

3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
~~~ java
class Car {
    private String color;

    private Car(String color) {
        this.color = color;
    }

    // 정적 팩터리 메소드
    public static Car getCar(String color) {
        return new ElectricCar(color, 100); // ElectricCar는 Car의 하위 클래스
    }
}

class ElectricCar extends Car { // Car의 하위 클래스 
    private int batteryLevel;

    ElectricCar(String color, int batteryLevel) {
        super(color);
        this.batteryLevel = batteryLevel;
    }
}

~~~
  
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.

~~~ java
class CarFactory {
    public static Car getCar(String type) {
        if ("Electric".equals(type)) {
            return new ElectricCar("Blue", 100);
        } else if ("Gasoline".equals(type)) {
            return new GasolineCar("Red", "V6");
        }
        return null;
    }
}

class GasolineCar extends Car {
    private String engineType;

    GasolineCar(String color, String engineType) {
        super(color);
        this.engineType = engineType;
    }
}

~~~
5. 정적 팩터리 메소드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지않아도된다. 
~~~ Java
public interface Animal {
    void makeSound();
}

public class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

public class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}
public class AnimalFactory {
    public static Animal getAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) {
            return new Dog();
        } else if ("cat".equalsIgnoreCase(type)) {
            return new Cat();
        }
        return null;
    }
}
~~~





### 용어 
정적 팩터리 메소드
구현체
브리지 패턴
