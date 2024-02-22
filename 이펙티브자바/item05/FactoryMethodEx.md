### 팩토리 메소드 패턴
~~~ java 

interface Product {
    void printLog();
}

// 구체적인 제품 클래스
class ProductA implements Product {
    @Override
    public void printLog() {
        System.out.println("ProductA");
    }
}

class ProductB implements Product {
    @Override
    public void printLog() {
        System.out.println("ProductB");
    }
}

// 팩토리 클래스
class ProductFactory {
    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ProductA();
        } else if (type.equals("B")) {
            return new ProductB();
        } else {
            throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
}

public class main {
    public static void main(String[] args) {

        Product productA = ProductFactory.createProduct("A");
        productA.printLog(); // 출력: ProductA

        Product productB = ProductFactory.createProduct("B");
        productB.printLog(); // 출력: ProductB
    }
}

~~~
팩토리 클래스를 통해서 상황에 맞는 구현체를 가져올 수 있다.
