# 오류 / 예외
## 오류 (Error)
* 메모리 부족 또는 프로그램 실행이 꼬이는 경우, 더이상 어떻게 프로그램을 복구해야 할지 알 수 없다.
* 프로그램 문제를 해결하여 해결 

## 예외 (Exception)
* 오류(Error)에 비해서 심각도가 낮고, 프로그램의 정상적인 흐름만 방해
    * 파일을 읽으려 했으나, 해당 파일이 없음
    * 네트워크 연결이 유실되는 경우
* 문제 상황을 처리하는 로직을 구현하여, 런타임에서 자연스럽게 해결 가능

## 예외 처리
* 예외가 발생했을 경우, 이 상황을 **'감지'**하고 **'처리'**하는 코드
* ```try ~ catch```, ```throws```, ```throw```, ```finally``` 키워드를 이용
* Throwable 클래스를 상속하는 자식 클래스로 이러한 문제들을 해결


### try ~ catch 문 종류
* **try ~ catch**
    ```java
    try{
          // 예외 발생 가능 코드
    }catch(예외 클래스 e){
          // 예외에 대한 처리 코드    
    }
    ```
* **multiple catch**
    * 다형성에 의해서 결정된다.
    * 즉, catch하고 있는 클래스의 자식 클래스의 객체면 catch 가능
    ```java
    try{
        ...
    }catch(ArithmetricException e){
        ...
    }catch(Exception e){
        ...
    }
    ```

* **try ~ catch ~ finally**
    * ```finally```는 catch 발생 여부와 무관하게 실행
    * try에서 생성한 리소스를 회수하기 위해 사용
    ```java
    try{
        FileInputStream file = new FileInputStream("a.txt");
        file.read();
    }catch(IOException e){
        System.out.println("파일 처리 실패");
    }finally{
        try{
            file.close();
        } catch (IOException e){
            System.out.println("FileInputStream Close Fail");
        }  
    }
    ```

* **try ~ with ~ resource**
    * Resource를 종료할 필요가 있는 경우 사용
    * AutoCloseObj 인터에페이스를 구현하는 리소스를 자동으로 close 처리
    * AutoCloseObj의 close 함수 재정의, 즉 해당 클래스의 close 함수를 자동적으로 호출
    ```java
      try(FileInpuStream file = new FileInputStream("a.txt")){
          file.read();
      } catch(IOException e) {
          System.out.println("File Read Fail")
      }
    ```

## 예외의 종류 2가지
* **Checked Exception**
    * Exception 클래스를 상속하고 있으면, Checked Exception
    * Compiler에서 예외 발생 -> try ~ catch를 작성하지 않으면 아예 Build조차 할 수 없다.
    
    
* **Unchecked Exception**
    * RuntimeException 클래스를 상속하고 있으며 Unchecked Exception
    * try ~ catch를 작성하지 않아도 Build/Run이 가능
    * ex> ArrayIndexOutOfBoundsException, ArithmetrixException
    

## 예외처리 위임
* 예외를 바로 처리하는 것이 아니라 상위 Function에 던진다. 이렇게 되면 상위 위치에서 예외처리를 강제할 수 있다.
* ```throws``` 키워드 이용
    * **CheckedException인 경우, 예외처리가 필요하므로 throws를 통해 위임**
    ```java
    class CheckedExceptionThrow {
        void methodA() throws IOException {
            FileInputStream file1 = new FileInputStream("a.txt");
            file1.read();
            file1.close();
        }
    
        void methodB() {
            try {
                methodA();
            } catch (Exception e) {
                System.out.println("methodA 실패");
            }
        }
    }
    ```
    
    * **UncheckedException인 경우, ```throws``` 키워드를 사용하지 않아도 된다(자동으로 위임)**
    ```java
    class UnCheckedExceptionThrows {
      void methodA() {
          int x = 10 / 0;
      }
      
      void methodB() {
          methodA();
      }
      
      public static void main(String[] args) {
          try {
              new UnCheckedExceptionThrows().methodB();
              } catch (ArithmeticException e) {
                  System.out.println(e.getMessage());
              }
          }
    }      
    ```
    
    * **Method가 Overriding이 되는 경우**
        * 부모 클래스의 메소드를 오버라이딩 할때는 부모 클래스의 메소드의 예외보다 더 조상인 예외는 던질 수 없다.
        * 오버라이딩할 때 구현하는 내용을 어느정도 제한하고 있는 부분

    ```java
    class Foo {
        void methodA() throws IOException {
            FileInputStream file = new FileInputStream("a.txt");
        }    // Checked Exception
    
        void methodB() throws ArithmeticException {
            try {
                FileInputStream file = new FileInputStream("a.txt");
            } catch (IOException e) {
                throw new ArithmeticException("에러 발생");
            }
        }
    }
    
    class BarOne extends Foo {
        void methodA() throws IOException {
        }    // IOException을 상속 받는다.
    }
    
    class BarTwo extends Foo {
        void methodA() throws FileNotFoundException {
        }     // 더 자식 Exception은 possible
    }
    
    class BarThree extends Foo {
    //    void methodA() throws Exception {}    // not possible
    }
    ```

    
## 예외 발생시키기
* ```throw``` 키워드 사용하여 예외를 발생
    ```java
    void exceptMethod1() throws IOException{
        if(false)
            throw new FileNotFoundException("Error");     // possible
    }
  
    void exceptMethod2() throws FileNotFoundException{
        if(false)
            throw new IOException("Error");               // not possible, FileNotFoundException이 IOException의 자식 클래스이므로
    }    
    ```

## 사용자 정의 예외 클래스 선언
* 필요에 따라 사용자가 정의한 클래스를 선언할 수 있다.
* Exception or RuntimeException을 상속 받는다.
    ```java
    public class userException extends Exception{
    // public class userException extends RuntimeException
        public userException(){}
        public userException(String message){ super(message); }
    }
    ```

