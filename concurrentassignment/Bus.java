package concurrentassignment;

import java.util.Date;

import java.util.concurrent.Semaphore;

public class Bus extends Thread{

    int no_bus;

    String bus_name;

    Date inTime;

    Depot depot;


    Semaphore bus;

    public int getNo_bus() {
        return no_bus;
    }

    public void setNo_bus(int no_bus) {
        this.no_bus = no_bus;
    }

    public Bus(Depot depot,int no_bus){
        this.depot=depot;
        this.no_bus = no_bus;

    }

    //Bus id auto load
    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }


    public void setInTime(Date inTime){this.inTime = inTime;}

    public Date getInTime(){ return inTime;}

    public void run(){

        addBus();

    }

    private synchronized void addBus(){

        depot.add(this);

    }
}
