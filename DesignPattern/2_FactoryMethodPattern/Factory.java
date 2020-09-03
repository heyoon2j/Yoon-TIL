abstract public class Factory {
    // An Operation()
    public final Product create(String name){
        Product pd = createFactory(name);
        // Other Operation
        System.out.println("Other Operation");
        return pd;
    }

    // FactoryMethod()
    protected abstract Product createFactory(String name);
}

class ConcreteFactory extends Factory{

    // FactoryMethod();
    @Override
    protected ConcreteProduct createFactory(String name) {
        return new ConcreteProduct(name);
    }

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Product product1 = factory.create("ABC");
        Product product2 = factory.create("EFG");
        product1.run();
        product2.run();
    }
}
