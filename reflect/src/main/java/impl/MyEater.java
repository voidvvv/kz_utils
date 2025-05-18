package impl;

import anno.KzTransaction;
import interfaces.Eatable;

//@Component
public class MyEater implements Eatable {



    @Override
    @KzTransaction
    public void eat() {
        this.eat(100);
    }

    @Override
    @KzTransaction
    public void eat(int amount) {
        System.out.println("Eating " + amount + " units...");
    }

    @Override
    public void drink() {
        System.out.println("Drinking...");
    }
}
