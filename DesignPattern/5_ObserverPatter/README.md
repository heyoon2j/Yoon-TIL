# Observer Pattern
* Observable 객체의 변화를 Observer에서 알 수 있도록 하는 패턴
* 관찰 대상(Subject) 클래스는 관찰자(Observer)의 목록을 알고(가지고) 있다.
    * 관찰 대상(Subject)에 변화가 생길 시, 관찰자(Observer)들에게 변화되었다는 것을 알린다.
* 관찰자(Observer)는 관찰 대상을 알고 있다.
    * 변화에 대한 신호가 오게 되면 그에 대응하는 행동을 취하게 된다.
* 자주 사용되는 곳
    * GUI에 많이 사용되는 패턴
    * 게임에도 많이 사용
    * 즉, 보통 Hierarchy가 있는 경우(계층이 있는 경우) 많이 사용됨

## Pattern Structure
![DecoratorPatter](../img/ObserverPattern.jpg)

* **Subject** : 관찰 대상 클래스
* **Observer** : 관찰자 클래스

```java
class Subject {
  private List<Observer> observers = new ArrayList<Observer>();
  private int state;

  public int getState() {
      return state;
  }

  public void setState(int state) {
      this.state = state;
      notifyAllObservers();
  }

  public void attach(Observer observer){
      observers.add(observer);		
  }

  public void notifyAllObservers(){
      for (Observer observer : observers) {
        observer.update();
      }
  } 	
}

abstract class Observer {
  protected Subject subject;
  public abstract void update();
}

class BinaryObserver extends Observer{

  public BinaryObserver(Subject subject){
      this.subject = subject;
      this.subject.attach(this);
  }

  @Override
  public void update() {
      System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) ); 
  }
}

class OctalObserver extends Observer{

  public OctalObserver(Subject subject){
      this.subject = subject;
      this.subject.attach(this);
  }

  @Override
  public void update() {
    System.out.println( "Octal String: " + Integer.toOctalString( subject.getState() ) ); 
  }
}

class HexaObserver extends Observer{

  public HexaObserver(Subject subject){
      this.subject = subject;
      this.subject.attach(this);
  }

  @Override
  public void update() {
      System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ).toUpperCase() ); 
  }
}

class ObserverPatternDemo {
  public static void main(String[] args) {
      Subject subject = new Subject();

      new HexaObserver(subject);
      new OctalObserver(subject);
      new BinaryObserver(subject);

      System.out.println("First state change: 15");	
      subject.setState(15);
      System.out.println("Second state change: 10");	
      subject.setState(10);
  }
}
```

