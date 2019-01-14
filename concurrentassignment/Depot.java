package concurrentassignment;

import java.util.*;

public class Depot extends Thread{

    //int count = 0;

    int no_ramp = 1;

    int count_bus = 0;

    int no_mech;

    int no_bus;
    List<Bus> listbus;


    ArrayList al = new ArrayList(); //mech list

    ArrayList leave = new ArrayList();

    int  No_cleaner;


    public Depot(int no_mech,int No_cleaner,int no_bus){
        listbus = new LinkedList<Bus>();
        this.no_mech = no_mech;
        this.No_cleaner = No_cleaner;
        this.no_bus = no_bus;
    }

    Bus bus;

    public void CleanBus(ArrayList bus_ls){

        System.out.println("Cleaner is waiting for bus");

        System.out.println("Bus in cleaner " + bus_ls + " " + bus.getInTime());

        Queue cleaningQueue = new LinkedList(bus_ls);

        while(cleaningQueue.size() > 0){
            leave.add(((LinkedList) cleaningQueue).getFirst());
            System.out.println("Cleaner is cleaning the bus " + ((LinkedList) cleaningQueue).pollFirst());

            System.out.println("Done cleaning");

            if(leave.size() == no_bus){
                remove_bus_from_clean(leave);
                //ResetLeave(leave);
            }
        }
    }


   /* public void ResetLeave(ArrayList leave_arr){
        try {
            synchronized (leave_arr) {

                //CleanBus(al);               //Add bus from mech slot to cleaning slot
                leave_arr.wait(100);
                for(int b = leave_arr.size()-1; b >=0; b--){
                    leave_arr.remove(b);
                }
                //System.out.println("Mechanic is available" + al);
            }
            leave_arr.wait(10);
            //System.out.println(al + " Empty");
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
    }*/


    public void remove_bus_from_clean(ArrayList al1){
        try {

            synchronized (al1) {

                //On queue ramp
                Queue outputQueue = new LinkedList(al1);

                while(outputQueue.size() > 0){

                    System.out.println(outputQueue + " on queue to leave");

                    System.out.println (((LinkedList) outputQueue).getFirst() +" on the ramp " );
                    al1.wait(1000);
                    System.out.println(((LinkedList) outputQueue).pollFirst() + " has leave the ramp " );
                }


                System.out.println(outputQueue + " No bus on queue");
                System.out.println("Cleaner is available " + outputQueue);
            }

        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
    }


    public void FixBus(){

        synchronized (listbus){
            System.out.println("Mechanic is waiting for bus ");
            while(listbus.size() == 0) {
                try {
                    listbus.wait(100);
                } catch (InterruptedException iex) {
                    iex.printStackTrace();
                }
            }
           // System.out.println("Mechanic found a bus.");
            bus = (Bus) ((LinkedList<?>) listbus).poll();

            if (al.size() < no_mech){
                //If no bus add bus to mech list
                al.add(bus.getBus_name());
                //al.add(bus.getInTime());
                count_bus++ ;
            }

            //Fixing queue
            Queue fixingQueue = new LinkedList(al);

            while(fixingQueue.size() > 0){

                System.out.println("Bus in mechanic " + ((LinkedList) fixingQueue).getFirst() +  " " +bus.getInTime());
                System.out.println("Mechanic is fixing the bus " + ((LinkedList) fixingQueue).pollFirst() + " " +bus.getInTime());
                System.out.println("Mechanic done fixing " + fixingQueue);
            }

            if(count_bus %no_mech == 0){
                CleanBus(al);
                remove_bus();

            }

        }

    }

    //Remove bus from mech list
    public void remove_bus(){
        try {
            synchronized (al) {

                //CleanBus(al);               //Add bus from mech slot to cleaning slot
                al.wait(100);
                for(int b = al.size()-1; b >=0; b--){
                    al.remove(b);
                }
                System.out.println("Mechanic is available" + al);
            }
            //al.wait(10);
            //System.out.println(al + " Empty");
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
    }




    //Bus on ramp
    public void add(Bus bus)
    {
        synchronized (listbus){
            System.out.println(bus.getBus_name()+ " request for entering the ramp at "+bus.getInTime());

            while(listbus.size() < no_ramp){
                System.out.println(bus.getBus_name()+ " is on ramp "+bus.getInTime());
                ((LinkedList<Bus>)listbus).offer(bus);
                System.out.println(listbus);

                System.out.println("Mechanic found a bus.");
                FixBus();
                try
                {
                    listbus.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
               // CleanBus();

            }

        }

    }



}
