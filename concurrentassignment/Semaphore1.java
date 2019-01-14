package concurrentassignment;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Semaphore1 extends Thread{

    static Semaphore sem = new Semaphore(0);

    Depot depot;

    Bus bus;

    public Semaphore1(Semaphore sem,Depot depot){
        this.sem = sem;
        this.depot = depot;
    }
    int count;

    public void get(){
        try{

            sem.acquire();
        }catch(InterruptedException e){

        }
        System.out.println("Mechanic fixing " + bus.getBus_name());
        sem.release();

    }


}
