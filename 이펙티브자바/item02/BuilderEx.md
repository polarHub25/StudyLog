### 빌더 패턴
~~~ java 

public abstract class Pizza {
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    public enum Sauce { TOMATO, HOT, CREAM}

    public enum Cheeze { MOZZARELLA, RICOTTA, CHEDDAR}
    final Set<Topping> toppings;

    final Set<Sauce> sauceSet;

    final Set<Cheeze> cheezeSet;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        EnumSet<Sauce> sauceSets = EnumSet.noneOf(Sauce.class);
        EnumSet<Cheeze> cheezeSets = EnumSet.noneOf(Cheeze.class);
        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        // 가변인수로 선언하여 여러개의 소스를 한번에 받을 수 있다
        public T addIngredients(Sauce ... sauces) {
            for(Sauce sauce : sauces){
                sauceSets.add(sauce);
            }
            return self();
        }
        // addIngredients를 오버로딩하여 치즈를 여러개 추가할 수 있다.
        public T addIngredients(Cheeze ... cheezes) {
            for(Cheeze cheeze : cheezes){
                cheezeSets.add(cheeze);
            }
            return self();
        }

        abstract Pizza build();

        // 하위 클래스는 이 메서드를 재정의(overriding)하여
        // "this"를 반환하도록 해야 한다.
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone(); // 아이템 50 참조
        sauceSet = builder.sauceSets.clone();
        cheezeSet = builder.cheezeSets.clone();
    }
}

public class NyPizza extends Pizza {

    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {

            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {

            return new NyPizza(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }

    private NyPizza(Builder builder) {

        super(builder);
        size = builder.size;
    }

    @Override
    public String toString() {

        return toppings + "로 토핑하고 "+ sauceSet +" 추가한 뉴욕 피자";
    }
}

public class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false; // 기본값

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override public Calzone build() {
            return new Calzone(this);
        }

        @Override protected Builder self() { return this; }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }

    @Override public String toString() {
        return  toppings + "로 토핑하고 "+ sauceSet +" 추가한 칼초네 피자";
    }
}

public class Main {

    public static void main(String[] args) {
        // 빌더 패턴의 메서드 연쇄 특징을 이용하여 여러개의 가변인수 메서드를 호출
        NyPizza pizza = new NyPizza.Builder(SMALL)
                .addTopping(Pizza.Topping.SAUSAGE).addTopping(ONION)
                .addIngredients(Pizza.Sauce.CREAM, Pizza.Sauce.TOMATO)
                .addIngredients(Pizza.Cheeze.MOZZARELLA, Pizza.Cheeze.CHEDDAR).build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside()
                .addIngredients(Pizza.Sauce.HOT, Pizza.Sauce.CREAM, Pizza.Sauce.TOMATO)
                .addIngredients(Pizza.Cheeze.MOZZARELLA, Pizza.Cheeze.CHEDDAR, Pizza.Cheeze.RICOTTA).build();

        System.out.println(pizza);
        System.out.println(calzone);
    }
}
