package MultiThreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceClass {

    Lock lock = new ReentrantLock();

    int count = 0;

    int increaseCount(){

        try {
            lock.tryLock(5, TimeUnit.SECONDS);
            count++;
            System.out.println(Thread.currentThread().getName() + " " + count);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            lock.unlock();
        }
        return count;
    }
}
