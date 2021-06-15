* 문자 변환 및 제거
    * ```tr [OPTION] SET1 [SET2]``` 명령어 사용
    * 기본적으로 SET1을 SET2로 변환시킨다.
    * ```-d``` 옵션을 사용하는 경우, 특정 문자들을 삭제할 수 있다.
    ```
    # 특정 문자 변환
    echo abcd | tr '[a-z]' '[A-Z]'  # ABCD
  
    echo abcd | tr -d 1,3,4,a,c     # bd
    ```
  






