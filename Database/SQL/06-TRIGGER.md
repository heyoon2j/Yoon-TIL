# TRIGGER
* 특정 테이블을 감시하고 있다가 설정된 조건의 쿼리가 실행되면,
 미리 지정한 쿼리가 자동으로 실행되도록 하는 방법
* Syntax
    ```
    # TRIGGER 있을 시 지우는 방법
    DROP TRIGGER IF EXISTS {trigger_name}
    # DELIMITER는 구문 문자이다.
    DELIMITER //
    CREATE TRIGGER {tigger_name}
        {BEFORE | AFTER}
        {INSERT | UPDATE | DELETE}
        ON {table_name}
        FOR EACH ROW
        {{FOLLOWS | PRECEDES} other_trigger_name}
    BEGIN
        {execute_query}
    END //
    DELEIMITER ;
    ```
    * BEFORE : {INSERT | UPDATE | DELETE}를 호출하게 되면, 입력한 쿼리가 먼저 실행된 후에 동작한다.
    * AFTER : {INSERT | UPDATE | DELETE}가 먼저 동작한 후에, 입력한 쿼리가 실행된다.
    * FOLLOWS : 같은 테이블에 여러 개의 Trigger가 있는 경우 순서를 지정할 수 있으며, 입력한 다른 Trigger가 동작 후에 해당 Trigger가 실행 된다.
    * PRECEDES : 입력한 다른 Trigger가 동작하기 전에 해당 Trigger가 실행된다.
  
* Example
    ```
    DROP TRIGGER IF EXISTS trigger_name;
    DELIMITER //
    CREATE TRIGGER orderTrg
        AFTER INSERT
        ON orderTBL
        FOR EACH ROW
    BEGIN
        DECLARE orderAmout INT;
        SET orderAmount = OLD.account - NEW.account;
        INSERT INTO deliverTbl(prodName, account)
            VALUES(NEW.prodName, orderAmount);
    END //
    DELEMETER ;
    ```
  