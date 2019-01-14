package concurrentassignment;



public class Mechanic implements Runnable {

    Depot depot;

    int a;
    int b;
    public Mechanic(Depot depot,int b){
        this.depot = depot;
        //this.a = a;
        new Thread().start();
        this.b = b;
    }

    public void run(){
        try{
            System.out.println("Mechanic is ready.....");
            Thread.sleep(1000);
        }catch(InterruptedException iex){
            iex.printStackTrace();
        }
      depot.FixBus();
    }


    public synchronized void isnotavailable()
    {
        //NotAvailable_mech = true;
        System.out.println("Ramp not available");
    }

}
