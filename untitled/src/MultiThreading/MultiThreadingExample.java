package MultiThreading;

public class MultiThreadingExample{

    public static void main(String[] args) {

        ResourceClass resource = new ResourceClass();

        Runnable rn = resource::increaseCount;

        Thread t1 = new Thread(rn,"t1");
        Thread t2 = new Thread(rn,"t2");
        Thread t3 = new Thread(rn,"t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(resource.count);
    }
}
