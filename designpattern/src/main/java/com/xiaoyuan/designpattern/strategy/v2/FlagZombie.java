package com.xiaoyuan.designpattern.strategy.v2;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 17:55  
 */
public class FlagZombie extends Zombie {

    public FlagZombie() {
        super(new StepByStepMove(), new HitAttack());
    }

    public FlagZombie(Moveable moveable, Attackable attackable) {
        super(moveable, attackable);
    }

    @Override
    public void display() {
        System.out.println("我是旗手僵尸.");
    }

    @Override
    void move() {
        moveable.move();
    }

    @Override
    void attack() {
        attackable.attack();
    }
}
