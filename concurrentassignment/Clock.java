package concurrentassignment;

public class Clock extends Thread {

    private BusGenerator bg;

    public Clock(BusGenerator bg){
        this.bg = bg;
    }

    public void run(){
        try{
            Thread.sleep(1500000000);
            setclosingTime();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public synchronized void setclosingTime(){
        System.out.println("Depot : We're closing now!");
        bg.isnotavailable();
        //b.setclosingTime();
    }
}
