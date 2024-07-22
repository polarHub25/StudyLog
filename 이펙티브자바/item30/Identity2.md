### 1. 항등함수 예제2
~~~ java 
 public class Identity2 {

    public static void main(String[] args) {
        Cat cat1 = new Cat(5);
        Cat cat2 = new Cat(2);
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        //groupingBy에 그룹핑에 사용될 함수를 전달
        Map<Integer, List<Cat>> catCollet = cats.stream().collect(Collectors.groupingBy(Cat::getAge));

        Dog dog1 = new Dog(5);
        Dog dog2 = new Dog(2);
        List<Dog> dogs = new ArrayList<>();
        dogs.add(dog1);
        dogs.add(dog2);
        //groupingBy에 그룹핑에 사용될 함수를 전달
        Map<Integer, List<Dog>> dogCollet = dogs.stream().collect(Collectors.groupingBy(Dog::getAge));

    }

}

class Cat{

    public Cat(int age) {

        this.age = age;
    }

    private int age;

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }
}

class Dog{

    public Dog(int age) {

        this.age = age;
    }
    private int    age;

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

}
~~~

### 2.groupingBy
~~~ java
public static <T, K> Collector<T, ?, Map<K, List<T>>>
    groupingBy(Function<? super T, ? extends K> classifier) {
        return groupingBy(classifier, toList());
    }
~~~
