package com.xiaoyuan.designpattern.strategy.v2;

/**
 * @author : liyuan  
 * @description : 策略模式
 * 应用场景
 * 1.当你有很多类似的类，但它们执行某些行为的方式不同时，请使用此
 * 策略。
 * 2.使用该模式将类的业务逻辑与算法的实现细节隔离开来，这些算法在
 * 逻辑上下文中可能不那么重要。
 * 3.当你的类具有大量的条件运算符，并且在同一算法的不同变体之间切
 * 换时，请使用此模式。
 * @date : 2020/9/23 17:44  
 */
public class StrategyTest {
    public static void main(String[] args) {
        Zombie normalZombie = new NormalZombie();
        normalZombie.display();

        normalZombie.attack();
        normalZombie.move();

        normalZombie.setAttackable(new HitAttack());
        normalZombie.attack();

        System.out.println("-----------------------");

        Zombie flagZombie = new FlagZombie();
        flagZombie.display();

        flagZombie.attack();
        flagZombie.move();

        flagZombie.setAttackable(new BiteAttack());
        flagZombie.attack();
    }
}