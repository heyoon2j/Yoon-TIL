# YAML 기본 문법



## YAML 기본
* 기본 구조
    * ```#```: 주석
    * ```---```: Document의 시작점
    * ```...```: Document의 끝점
* 데이터 정의
    * ```key: value``` 형태로 표현되며 ```:``` 다음에는 무조건 공백 문자가 와야한다.
* Text 표현
    * ```|```: 제일 마지막 줄바꿈(\n) 포함
    * ```|-```: 제일 마지막 줄바꿈(\n) 제막
    * ```>```: 중간에 들어간 빈줄을 제외

## 자료형
* 기본적으로 정수형, 문자열, 부울형, Object, List 가 있다.

## Example
```
# test.yaml

--- # Strat

intVal: 3           # Integer Value
stringVal: "abc"    # String Value
booleanVal: true    # Boolean Value


# List : use "-"
list1:
    - item1
    - item2

list2: [
    item1, item2
]


# Object
object1:
    key1: val1
    key2: val2


object2: {
    key1: val1,
    key1: val2
}


# Object List
object_list:
    - color: red
      direction: left
    - color: blue
      direction: right



# Text
line_break: |
    Hello world.
    I'm studying YAML
single_line: |-
    Welcome to 
    my home
line_break: |
    Hello world.
    I'm studying YAML
... # End

```



### Reference
* https://subicura.com/k8s/prepare/yaml.html#%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%86%E1%85%AE%E1%86%AB%E1%84%87%E1%85%A5%E1%86%B8