package concurrentassignment;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BusGenerator implements Runnable{

    Depot depot;
    Semaphore sem;

    public boolean Notavailable = false;

    int a;
    int b;

    public BusGenerator(Depot depot,int a){
        this.depot=depot;
        this.a = a;

        //new Thread(this,"BusGenerator").start();
    }

    public void run(){

        while(b < a){

            Bus bus = new Bus(depot,a);

            bus.setInTime(new Date());
            Thread thbus = new Thread(bus);

            bus.setBus_name("Bus " +thbus.getId());

            thbus.start();

            try{
                TimeUnit.SECONDS.sleep((long)(Math.random()*10));
               // sem1.acquire();


            }catch(InterruptedException iex){
                iex.printStackTrace();
            }
            b++;

        }

        if (Notavailable) { try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return; }

    }

    public synchronized void isnotavailable()
    {
        Notavailable = true;
        System.out.println("Depot is close");
    }

}
