package command;

public class ConcreteCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Command Execution");
    }
}
