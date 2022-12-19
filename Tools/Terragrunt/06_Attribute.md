# Attribute

## inputs

## prevent_destroy
* 리소스 파괴를 방지한다!!
```
terraform {
  source = "git::git@github.com:foo/modules.git//app?ref=v0.0.3"
}

prevent_destroy = true
```
