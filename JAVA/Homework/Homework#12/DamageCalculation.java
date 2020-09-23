import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.ToDoubleBiFunction;

/**
 * 열거형 타입과 함수형 프로그래밍을 이용하여 플레이어의 공격력을 계산하는 알고리즘을 구현하시오.
 *
 * 플레이어 공격력을 계산하는 과정은 아래와 같다.
 * 1. 플레이어의 무기에 따라 공격력이 변화한다. 무기는 최근에 장착한 무기의 공격력 만으로 계산된다.
 *   1-1. BARE_HANDS - 공격력 5
 *   1-2. DAGGER - 공격력 40
 *   1-3. LONG_SWORD - 공격력 100
 *   1-4. DRAGON_SLAYER -  공격력 250
 * 2. 플레이어의 공격력에 영향을 주는 아이템에 따라 공격력 증가 방식이 다르며, 아이템은 중복 적용된다.
 *   2-1. BLACK_POTION - 공격력 10% 증가
 *   2-2. WHITE_POTION - 모든 공격력 계산이 끝난 후에 공격력 + 200
 *   2-3. MUSHROOM - 무기 공격력 + 20
 *
 */

enum Weapon {
    // 무기 구현
    BARE_HANDS(5), DAGGER(40), LONG_SWORD(100), DRAGON_SLAYER(250);

    private double attack;
    Weapon(double attack){
        this.attack = attack;
    }

    public double getAttack() {
        return attack;
    }
}

enum Item {
    // 소비 아이템 구현
    BLACK_POTION(0, 10, 0), WHITE_POTION(0, 0, 200), MUSHROOM(20,0,0);

    private double addedATK;
    private double addedPercentATK;
    private double afterAddedATK;

    Item(double addedATK, double addedPercentATK, double afterAddedATK){
        this.addedATK = addedATK;
        this.addedPercentATK = addedPercentATK;
        this.afterAddedATK = afterAddedATK;
    }

    Item(double addedATK){
        this(addedATK,0,0);
    }

    public double getAddedATK() {
        return addedATK;
    }

    public double getAddedPercentATK() {
        return addedPercentATK;
    }

    public double getAfterAddedATK() {
        return afterAddedATK;
    }
}

class Player {
    Weapon currentWeapon;
    Map<Item, Integer> items;
    List<Item> usingItem;
    double currentATK;

    // TODO: Player에 필요한 메소드 구현
    // 무기 교체, 아이템 사용, 아이템 효과 종료 메소드 구현
    Player(){
        items = new HashMap<>();
        usingItem = new LinkedList<>();
    }

    void addItem(Item item){
        if(!items.containsKey(item))
            items.put(item, 0);
        else
            items.put(item, items.get(item) + 1);
    }

    void changeWeapon(Weapon newWeapon){
        this.currentWeapon = newWeapon;
        this.currentATK = this.currentWeapon.getAttack();
    }

    //
    void useItem(Item useItem){
        if(items.containsKey(useItem)){
            usingItem.add(useItem);
        }else{
            System.out.println("해당 Item이 존재하지 않습니다.");
        }

        BiFunction<Double, Item, Double> addedATKFunc = (atk, item) -> {
            return atk + item.getAddedATK();
        };

        BiFunction<Double, Item, Double> addedPercentATKFunc = (atk, item) -> {
            return atk * (1 + item.getAddedPercentATK()/100);
        };

        ToDoubleBiFunction<Double, Item> afterAddedATKFunc = (atk, item) -> {
            return atk + item.getAfterAddedATK();
        };

        this.currentATK = afterAddedATKFunc.applyAsDouble(addedPercentATKFunc.apply(addedATKFunc.apply(currentATK, useItem), useItem),useItem);
    }

    void finishItemEffect(Item usedItem){

        BiFunction<Double, Item, Double> addedATKFunc = (atk, item) -> {
            return atk - item.getAddedATK();
        };

        BiFunction<Double, Item, Double> addedPercentATKFunc = (atk, item) -> {
            return atk / (1 + item.getAddedPercentATK()/100);
        };

        ToDoubleBiFunction<Double, Item> afterAddedATKFunc = (atk, item) -> {
            return atk - item.getAfterAddedATK();
        };

        this.currentATK = addedATKFunc.apply(addedPercentATKFunc.apply(afterAddedATKFunc.applyAsDouble(currentATK, usedItem),usedItem),usedItem);
    }

    void showATK(){
        System.out.println(currentATK);
    }
}

public class DamageCalculation {
    public static void main(String[] args) {
        // 무기 및 아이템 장착/사용 시나리오 및 플레이어 공격력 출력
        Player player1 = new Player();
        // 기본 공격력 출력
        player1.showATK();

        // 무기 교체
        player1.changeWeapon(Weapon.DRAGON_SLAYER);
        // 교체 후 공격력 출력
        player1.showATK();

        // 아이템 사용하기
        // 현재 무기 교체하려 기본 공격력 : 250
        player1.addItem(Item.BLACK_POTION); // 공격력 10% 증가
        player1.addItem(Item.MUSHROOM); // 공격력 + 20
        player1.addItem(Item.WHITE_POTION); // 모든 계산이 끝난 후 + 200

        // 추가 공격력 20
        player1.useItem(Item.MUSHROOM);
        player1.showATK();
        // 효과 종료
        player1.finishItemEffect(Item.MUSHROOM);
        player1.showATK();

        // 모든 아이템 사용
        // (250 + 20) * 1.1 + 200
        player1.useItem(Item.MUSHROOM);
        player1.useItem(Item.BLACK_POTION);
        player1.useItem(Item.WHITE_POTION);
        player1.showATK();

        player1.finishItemEffect(Item.MUSHROOM);
        player1.finishItemEffect(Item.BLACK_POTION);
        player1.finishItemEffect(Item.WHITE_POTION);
        player1.showATK();
    }
}