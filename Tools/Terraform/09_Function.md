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


# Network
## cidrsubnet(prefix, newbits, netnum)
* https://www.terraform.io/docs/language/functions/cidrsubnet.html
* 주어진 IP 네트워크 주소 접두사 내에서 서브넷 주소를 계산
  ```
  > cidrsubnet("172.16.0.0/12", 4, 2)
  172.18.0.0/16
  > cidrsubnet("10.1.2.0/24", 4, 15)
  10.1.2.240/28
  > cidrsubnet("fd00:fd12:3456:7890::/56", 16, 162)
  fd00:fd12:3456:7800:a200::/72
  ```

## cidrsubnets(prefix, newbits...)
* https://www.terraform.io/docs/language/functions/cidrsubnets.html
* 특정 CIDR 접두사 내에서 연속적인 IP 주소 범위 시퀀스를 계산
  ```
  > cidrsubnets("10.1.0.0/16", 4, 4, 8, 4)
  [
    "10.1.0.0/20",
    "10.1.16.0/20",
    "10.1.32.0/24",
    "10.1.48.0/20",
  ]

  > cidrsubnets("fd00:fd12:3456:7890::/56", 16, 16, 16, 32)
  [
    "fd00:fd12:3456:7800::/72",
    "fd00:fd12:3456:7800:100::/72",
    "fd00:fd12:3456:7800:200::/72",
    "fd00:fd12:3456:7800:300::/88",
  ]
  ```

