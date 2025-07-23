package MultiThreading;

import javax.naming.directory.SearchResult;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(i, "Value " + i);
        }

        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>(map);
        HashMap<Integer, String> hashMap = new HashMap<>(map);
        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(map);

//        Thread modifier = new Thread(() -> {
//            try {
//                Thread.sleep(100);
//                synchronizedMap.put(100, "New Value");
//                System.out.println("Modified map from other thread.");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });


  //      modifier.start();


        try {
            synchronized (synchronizedMap) {
                for (Map.Entry<Integer, String> entry : synchronizedMap.entrySet()) {
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(synchronizedMap);
    }
}
