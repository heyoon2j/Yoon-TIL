# Exception

## 오류 (Error)
* 메모리 부족 또는 프로그램 실행이 꼬이는 경우, 더이상 어떻게 프로그램을 복구해야 할지 알 수 없다.
* 프로그램 문제를 해결하여 해결 

## 예외 (Exception)
* 오류(Error)에 비해서 심각도가 낮고, 프로그램의 정상적인 흐름만 방해
    * 파일을 읽으려 했으나, 해당 파일이 없음
    * 네트워크 연결이 유실되는 경우
* 문제 상황을 처리하는 로직을 구현하여, 런타임에서 자연스럽게 해결 가능
* 문법으로는 ```try```, ````except```, ```else```, ```finally```, ```raise```가 있다.
### 예외의 종류 2가지
* **Checked Exception**
    * Exception 클래스를 상속하고 있으면, Checked Exception
    * Compiler에서 예외 발생 -> try ~ catch를 작성하지 않으면 아예 Build조차 할 수 없다.
    
* **Unchecked Exception**
    * RuntimeException 클래스를 상속하고 있으며 Unchecked Exception
    * try ~ catch를 작성하지 않아도 Build/Run이 가능
    * ex> ArrayIndexOutOfBoundsException, ArithmetrixException
</br>
</br>


## try except
* 기본 문법으로 try에는 예외가 발생할 수 있는 실행 명령을 except에는 에러 발생시 실행할 명령을 입력한다.
* Example
    ```java
    try{
          // 예외 발생 가능 코드
    }catch(예외 클래스 e){
          // 예외에 대한 처리 코드    
    }
    ```
    ```python
    try :
        list = [1, 2, 3, 4]
        print(list[4])

    except :
        print("Error")
    ```
    ```java
    try{
        ...
    }catch(ArithmetricException e){
        ...
    }catch(Exception e){
        ...
    }
    ```

* Spectific Error Message
    ```python
    except IndexError :

    except (FileNotFoundError, FileExistsError) 
    ```
    * 특정 에러를 처리하기 위해서는 ```except <error_name> :```을 사용한다.
    * 여러 개를 처리할 경우, 괄호를 이용하여 묶어줄 수 있다.

* Save Error Message
    ```python
    except IndexError as e :
        print(e)
    ```
    * ```excpet as <변수명> :``` 문법을 사용한다.
    * 해당 변수에 에러메시지가 저장된다.



## else, finally
* ```try```, ```except``` 처리 후에 ```else```, ```finally``` 구문을 사용할 수 있다.
* ```else```는 예외가 발생하면 실행되지 않는다.
* ```finally```는 예외가 발생하든 안하든 실행된다.
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
    ```python
    try:
        x = a
        y = 10 / x
    except ZeroDivisionError:
        print("Divide 0")
    else:
        print(x, y)
    finally:
        print("calcuation end")

    ```





## raise
* Error를 강제로 발생시킨다.
* Example
    ```python
    if i == 0
        raise ZeroDivisionError
    ```
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

---
## Error Class 생성
* 필요에 따라 사용자가 정의한 클래스를 선언할 수 있다.
* Exception or RuntimeException을 상속 받는다. (Python은 Exception)
    ```python
    class userException(Exception):
        def __init__(self):
            # init 함수에는 에러 메세지가 들어가진다.
            super().__init__("사용자 예외 처리")
    ```
    ```java
    public class userException extends Exception{
    // public class userException extends RuntimeException
        public userException(){}
        public userException(String message){ super(message); }
    }
    ```


### Reference
* https://nachwon.github.io/exception/