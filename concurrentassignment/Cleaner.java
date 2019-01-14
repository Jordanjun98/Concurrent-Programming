package concurrentassignment;

public class Cleaner extends Thread{
    Depot depot;

    int  No_cleaner;
    public Cleaner(Depot depot,int No_cleaner){
       // this.sem = sem;
        this.depot = depot;
        this.No_cleaner = No_cleaner;
    }

    public void run(){
        try{
            System.out.println("Cleaner bay is ready.....");

            Thread.sleep(1000);
        }catch(InterruptedException iex){
            iex.printStackTrace();
        }
        //depot.CleanBus();

    }

}
