package com.xiaoyuan.designpattern.strategy.v2;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 17:54  
 */
public abstract class Zombie {
    abstract public void display();

    Moveable moveable;
    Attackable attackable;

    public Zombie(Moveable moveable, Attackable attackable) {
        this.moveable = moveable;
        this.attackable = attackable;
    }

    abstract void move();

    abstract void attack();

    public Moveable getMoveable() {
        return moveable;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }

    public Attackable getAttackable() {
        return attackable;
    }

    public void setAttackable(Attackable attackable) {
        this.attackable = attackable;
    }
}
