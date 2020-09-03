public class Product {
    private String name;

    public Product(String name){
        this.name = name;
    }

    public void run(){
        System.out.println(name+" Product");
    }

    public String getName() {
        return name;
    }
}

class ConcreteProduct extends Product {

    public ConcreteProduct(String name){
        super(name);
    }

    @Override
    public void run(){
        System.out.println(getName()+" ConcreteProduct");
    }
}