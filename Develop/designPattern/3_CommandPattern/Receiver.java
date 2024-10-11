package command;

import java.util.LinkedList;
import java.util.List;

public class Receiver {
    private List<Command> command;

    public Receiver() {
        this.command = new LinkedList<Command>();
    }
    public void push(Command command){
        this.command.add(command);
    }

    public void action(){
        if(!this.command.isEmpty()) {
            this.command.get(0).execute();
            this.command.remove(0);
        }
    }
    public void pop(){
        if(!this.command.isEmpty())
            this.command.remove(this.command.size()-1);
        else
            System.out.println("No Command");
    }

    public static void main(String[] args) {
        Command command1 = new ConcreteCommand();
        Receiver receiver = new Receiver();
        receiver.push(command1);
        receiver.push(command1);
        receiver.action();
        receiver.pop();
    }
}
