package com.mgw.part1.chapter3;


class Animal {
    public void eat() {
        System.out.println("动物进食");
    }
}

interface HuntAble {
    void hunt();
}

class Dog extends Animal implements HuntAble {

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    public void hunt() {
        System.out.println("狗捉耗子，多管闲事");
    }
}

class Cat extends Animal implements HuntAble {

    @Override
    public void eat() {
        super.eat(); // 表现为早期绑定
        System.out.println("猫吃鱼");
    }

    public void hunt() {
        System.out.println("猫捉老鼠，天经地义");
    }
}
/**
 * 测试早期绑定与晚期绑定
 * */
public class AnimalTest {

    public void showAnimal(Animal animal) {
        animal.eat(); // 表现为晚期绑定
    }

    public void showHunt(HuntAble huntAble) {
        huntAble.hunt(); // 表现为晚期绑定
    }
}
