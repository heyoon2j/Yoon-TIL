import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

/**
 * 열거형 타입과 함수형 프로그래밍을 이용하여 플레이어의 공격력을 계산하는 알고리즘을 구현하시오.
 * Stream API를 이용하여 기존 구현을 개선하시오.
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
    BLACK_POTION(x -> x * 1.1,50), WHITE_POTION(x -> x + 200,100), MUSHROOM(x -> x + 20,1);

    private DoubleUnaryOperator effectOp;
    private int priority;

    Item(DoubleUnaryOperator effectOp, int priority){
        this.effectOp = effectOp;
        this.priority = priority;
    }

    public DoubleUnaryOperator getEffectOp() {
        return effectOp;
    }

    public int getPriority() {
        return priority;
    }
}

class Player {
    Weapon weapon;
    List<Item> items;

    // TODO: Player에 필요한 메소드 구현
    // 무기 교체, 아이템 사용, 아이템 효과 종료 메소드 구현
    Player(){
        this.weapon = Weapon.BARE_HANDS;
        items = new LinkedList<>();
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public double getDamage() {
        return  items.stream()
                .sorted((item1, item2) -> item1.getPriority() - item2.getPriority())
                .map(item -> item.getEffectOp())
                .reduce(op0 -> op0, (op1, op2) -> op1.andThen(op2)).applyAsDouble(this.weapon.getAttack());

    }
}

public class DamageCalculation {
    public static void main(String[] args) {
        // 무기 및 아이템 장착/사용 시나리오 및 플레이어 공격력 출력
        Player player = new Player();
        player.setWeapon(Weapon.DAGGER);
        System.out.println(player.getDamage());     // 40

        player.setWeapon(Weapon.DRAGON_SLAYER);     // 250
        System.out.println(player.getDamage());

        player.addItem(Item.BLACK_POTION);
        player.addItem(Item.WHITE_POTION);
        player.addItem(Item.WHITE_POTION);
        System.out.println(player.getDamage());     // 250*1.1 + 200 + 200 = 675

        player.removeItem(Item.WHITE_POTION);
        System.out.println(player.getDamage());     // 250*1.1 + 200 = 475

        player.addItem(Item.BLACK_POTION);
        player.addItem(Item.BLACK_POTION);
        player.addItem(Item.BLACK_POTION);
        player.addItem(Item.BLACK_POTION);
        System.out.println(player.getDamage());     // 250*1.1^5 + 200 = 602.6275

        player.addItem(Item.MUSHROOM);
        System.out.println(player.getDamage());     // (250+20)*1.1^5 + 200 = 634.8377

        player.setWeapon(Weapon.LONG_SWORD);
        System.out.println(player.getDamage());     // (100+20)*1.1^5 + 200 = 393.2612
    }
}
