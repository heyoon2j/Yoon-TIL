# 9. Exception
* 문법으로는 ```try```, ````except```, ```else```, ```finally```, ```raise```가 있다.


## try except
* 기본 문법으로 try에는 예외가 발생할 수 있는 실행 명령을 except에는 에러 발생시 실행할 명령을 입력한다.
* Example
    ```python
    try :
        list = [1, 2, 3, 4]
        print(list[4])

    except :
        print("Error")
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


## raise
* Error를 강제로 발생시킨다.
* Example
    ```python
    if i == 0
        raise ZeroDivisionError
    ```

## Error Class 생성



### Reference
* https://nachwon.github.io/exception/