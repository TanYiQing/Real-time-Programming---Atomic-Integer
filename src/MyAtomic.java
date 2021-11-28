import java.util.concurrent.atomic.AtomicInteger;
import java.util.Scanner;

public class MyAtomic {

    public static void main(String[] args) throws InterruptedException {
        Action action = new Action();
        int x;
        Scanner scan=new Scanner(System.in);
        System.out.print("Please input x:");
        x=scan.nextInt();
        System.out.println();
        action.setStartPoint(x);

        Thread t1=new Thread(action);
        Thread t2=new Thread(action);
        Thread t3=new Thread(action);
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        System.out.println("Total = " + action.getIndex());
    }

}

class Action implements Runnable{

    private AtomicInteger index = new AtomicInteger();
    private int i;

    @Override
    public void run(){
        for(int j=i;j<i+10;j++){
            System.out.println(Thread.currentThread().getName()+": "+j);
            index.getAndAdd(j);
        }
    }

    public void setStartPoint(int i){
        this.i=i;
    }

    public int getIndex(){
        return this.index.get();
    }


}

