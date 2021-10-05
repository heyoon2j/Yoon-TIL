## concat(list1, list2, ...)
* https://www.terraform.io/docs/language/functions/concat.html
* 둘 이상의 목록을 단일 목록으로 결합.
    ```
    > concat(["a", ""], ["b", "c"])
    [
      "a",
      "",
      "b",
      "c",
    ]
    ```
</br>


## join(separator, list)
* https://www.terraform.io/docs/language/functions/join.html
* 문자열 목록(list)의 모든 요소를 구분 기호(separator)로 연결한다.
    ```
    > join(", ", ["foo", "bar", "baz"])
    foo, bar, baz
    > join(", ", ["foo"])
    foo
    ```


 ## element(list, index)
* https://www.terraform.io/docs/language/functions/element.html
* 목록(list)에서 해당 index에 해당하는 요소 반환
    ```
    > element(["a", "b", "c"], 1)
    b

    # Terraform
    resource "aws_iam_user" "devs" {
      count = "${length(var.dev_user_names)}"
      name  = "${element(var.dev_user_names, count.index)}"
    }
    ```


## base64encode & base64decode
* https://www.terraform.io/docs/language/functions/base64encode.html
* 해당 문자열을 Base64 인코딩/디코딩
  ```
  # encoding
  > base64encode("Hello World")
  SGVsbG8gV29ybGQ=


  # decoding
  > base64decode("SGVsbG8gV29ybGQ=")
  Hello World
  ```


## file(path)
* https://www.terraform.io/docs/language/functions/file.html
* 파일 내용을 읽고, 문자열로 반환
* 
  ```
  > file("${path.module}/hello.txt")
  Hello World
  ``` 








