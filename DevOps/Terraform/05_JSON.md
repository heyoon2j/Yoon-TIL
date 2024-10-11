# JSON
* JavaScript Object Nation
* 데이터 표현 방법

## 구조
* 기본적으로 key : value 형태의 구조를 가지고 있다.
* 자료형은 string, number, true, false, null, object, array
* 중괄호 ```{}```는 객체를 의미
* Example
    ```json
    {
      "name" : "yoon",
      "age" : 29,
      "like" : ["board", "dance"]
    }
    ```
    * ```{}```는 object를 의미하고, key:value 형태의 Set 구조를 가진다(중복이 가능)
    * ```[]``` array를 의미하며, 순서화된 collection이다.
    
    
* https://gxnzi.tistory.com/61

## Terraform JSON
* Terraform 파일 이름은 suffix로 ```.tf```를 가지며, 
JSON 파일 이름은 suffix로 ```.tf.json```을 가진다.

* https://www.terraform.io/docs/language/syntax/json.html