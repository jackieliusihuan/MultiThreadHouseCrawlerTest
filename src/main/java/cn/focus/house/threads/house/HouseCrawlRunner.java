package cn.focus.house.threads.house;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
@Component
public class HouseCrawlRunner {

    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1);
    
    public void execute (List<String> UrlList, HouseWorkerFactory houseWorkerFactory, int limit){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(0, 15, 0, TimeUnit.HOURS, queue,new ThreadPoolExecutor.CallerRunsPolicy());
        
        List<String> templist = new ArrayList<String>();
        for(String houseUrl : UrlList){
            if(templist.size() >= limit){
                HouseBaseWorker houseBaseWorker = houseWorkerFactory.getWorker(templist);
                pool.execute(houseBaseWorker);
                templist = new ArrayList<String>();
            }
            templist.add(houseUrl);
        }
        
        if(templist.size() > 0){
            HouseBaseWorker houseBaseWorker = houseWorkerFactory.getWorker(templist);
            pool.execute(houseBaseWorker);
        }
        try {
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
