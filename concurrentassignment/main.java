package concurrentassignment;

import java.util.Scanner;

public class main{

    /*int in;
    Ramp ramp;
    public main(){
        this.ramp = ramp;
    }*/

   // Bus bus_1 = new Bus(ramp);


    public static void main(String[] args){
       // Ramp ramp;

        int in;         //No of bus
        int no_mech;    //No of mechanic
        int no_clean;   //No of cleaner

        //No of bus
        System.out.println("Enter amount of bus: ");

        Scanner input = new Scanner(System.in);

        in = input.nextInt();

        //No of mechanic
        System.out.println("Enter amount of mech: ");

        Scanner input_mech = new Scanner(System.in);

        no_mech = input_mech.nextInt();

        //No of cleaner
        System.out.println("Enter amount of cleaner: ");

        Scanner input_clean = new Scanner(System.in);

        no_clean = input_clean.nextInt();

        Depot depot = new Depot(no_mech,no_clean);

        BusGenerator bg = new BusGenerator(depot,in);


        Mechanic mechanic = new Mechanic(depot,no_mech);

        Cleaner cleaner = new Cleaner(depot,no_clean);

        //cleaner.start();

        //depot.start();

        Thread buscg = new Thread(bg);

        Thread thmech = new Thread(mechanic);
        Thread thclean = new Thread(cleaner);

        Clock closetime = new Clock(bg);

        buscg.start();

       // buses.start();
        thmech.start();
        thclean.start();
        closetime.start();

    }




}
