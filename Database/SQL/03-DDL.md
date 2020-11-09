# DDL
* Data Definition Language

## CREATE
* DB, Table 생성
```
# CREATE DATABASE
CREATE DATABASE [db_name];

# CREATE TABLES
CREATE TABLE [table_name] (
    # Column
);
```

* Example
```
# 보통 이미 생성되어 있을 수 있기 때문에 DROP과 같이 사용한다.
DROP TABLE IF EXISTS money;
CREATE TABLE money(
	money_id INT PRIMARY KEY AUTO_INCREMENT,
    income INT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user (user_id)
    ON UPDATE CASCADE ON DELETE SET NULL
);
```

## ALTER
* 설정을 변경하는 키워드
### Database
```
# DB Character Set
# MySQL 기본 = latin1
# 그 외 ascii, euckr 등
ALTER DATABASE [db_name] CHARACTER SET = [character_set];
ALTER DATABASE test CHARACTER SET = utf8;

```

### Table
1. ADD
    * Table에 필드 추가
    * 추가적으로 열을 추가하게 되면 기본적으로 가장 뒤에 추가
    * 순서 지정을 위해서는 제일 뒤에 'FIRST' or 'AFTER 열 이름'
    ```
   ALTER TABLE [table_name] ADD [col_name] [col_type];
   ALTER TABLE user2 ADD tmp TEXT NOT NULL PRIMARY KEY; 
   ```

2. MODIFY
    * Table Column 수정
    ```
   ALTER TABLE [table_name] MODIFY COLUMN [col_name] [col_type];
   ALTER TABLE user2 MODIFY COLUMN tmp VARCHAR(20) NOT NULL;
   ```
    
3. DROP
    * Table 필드 및 제약 사항 삭제
    ```
   # 필드 삭제
   ALTER TABLE [table_name] DROP [col_name];
   ALTER TABLE user2 DROP tmp;
   
   # 제약 사항 삭제
   ALTER TABLE userTbl
    DROP PRIMARY KEY;
   ```
   
4. CHANGE
    * 열의 이름 및 데이터 형식 변경
    ```
   ALTER TABLE userTbl
    CHANGE COLUMN col_name col_newName VARCHAR(3) NULL;
   ```
   
5. RENAME
    * 테이블 이름 변경
    ```
   ALTER TABLE userTbl
    RENAME COLUMN newName;
   ```

### Constraint
* 제약 조건
1. NOT NULL
    * NULL 값을 저장할 수 없다.
    * NULL로 설정 시, NULL 값 허용
2. UNIQUE
    * 중복되지 않는 유일한 값
    * BUT NULL 값을 허용하고, NULL은 여러 개를 사용할 수 있다.
        ```
        # Table 생성 시, 선언
        email CHARACTER(30) NULL UNIQUE
      
        CONSTRAINT AK_email UNIQUE (email)
        ```
    
        ```
        # 외부에서 선언
        ALTER TABLE table_name
            ADD CONTRAINT constraint_name UNIQUE col_name
        ```
            
3. PRIMARY KEY
    * NOT NULL과 UNIQUE의 제약조건을 동시에 만족해야 한다.
    * 하나의 테이블에 하나의 Column만 조건을 설정할 수 있다. 
        ```
        # Table 생성 시, 선언
        userID INT NOT NULL PRIMARY KEY
      
        userID INT NOT NULL,
        PRIMARY KEY (userID)
      
        CONSTRAINT PRIMARY KEY PK_userTbl_userID (userID)
        ```
    
        ```
        # 외부에서 선언
        ALTER TABLE userTbl
            ADD [CONSTRAINT PK_userTbl_userID] PRIMARY KEY (userID);
     
        # KEY 같은 경우 2개의 열로 설정 가능, '제품 코드 + 제품 일련 번호'
        ALTER TABLE userTbl
            ADD CONSTRAINT PRIMARY KEY (prodCode, prodID)
        ```
4. FOREIGN KEY
    * 다른 Table 사이의 관계를 선언함으로써 데이터의 무결성을 보장해주는 역할
        ```
        # Table 생성 시, 선언
        FOREIGN KEY (col_name) REFERENCES ref_table_name (ref_col_name)
        FOREIGN KEY (user_id) REFERENCES user (user_id)
        
        CONSTRAINT key_name FOREIGN KEY (col_name) REFERENCE ref_table_name (ref_col_name)
        CONSTRAINT FK_userTbl_buyTbl FOREIGN KEY (user_id) REFERENCES userTbl (userID)
        ```
    
        ```
        # 외부에서 선언
        ALTER TABLE buyTbl
            ADD CONSTRAINT FK_userTbl_buyTbl FOREIGN KEY (userID) REFERENCE userTbl(userID);
        ```
    * FOREIGN KEY 설정 시, 기본적으로 참조받는 데이터가 수정하거나 삭제할 수 없다.
        * 이를 위해서는 추가 설정이 필요하다.
        * CASCADE : 참조를 받는 데이터가 수정하거나 삭제되면, 참조하는 데이터도 수정, 삭제
        * SET NULL : 참조 받는 데이터가 수정, 삭제되면, 참조하는 데이터는 NULL로 수정
        ```
        # ON UPDATE [condition]  ON DELETE [condition]
        FOREIGN KEY (user_id) REFERENCES user (user_id)
            ON UPDATE CASCADE ON DELETE SET NULL
        ```        
      
5. DEFAULT
    * 값을 입력하지 않았을 때, 자동으로 입력되는 기본값을 정의하는 방법
        ```
        # Table 생성 시, 선언
        userID INT NOT NULL PRIMARY KEY AUTO_INCREMENT DEFAULT -1
        ```
    
        ```
        # 외부에서 선언
        ALTER TABLE table_name
            MODIFY COLUMN col_name DEFAULT 1; 
        ```      
6. AUTO_INCREMENT
    * 주로 Table의 PRIMARY KEY 데이터를 저장할 때, 자동으로
    숫자를 1씩 증가시켜 주는 기능   
        ```
        # Table 생성 시, 선언
        userID INT NOT NULL PRIMARY KEY AUTO_INCREMENT
        ```
    
        ```
        # 외부에서 선언, 추가가 아닌 수정 또는 삭제 후 추가를 해야된다.
        ALTER TABLE table_name
            MODIFY COLUMN col_name AUTO_INCREMENT NOT NULL;
        ```  


## DROP
* Table이나 Database 삭제
* 트랜잭션이 발생하지 않는다.
```
# Databasea
DROP DATABASE [db_name];
DROP DATABASE test;

# Table
DROP TABLE [table_naem];
DROP TABLE user1;
```

## TRUNCATE
* 스키마(Table)는 남기고 모든 데이터 삭제
* 트랜잭션이 발생하지 않는다.
```
TRUNCATE [table_name];
TRUNCATE user2;
```

