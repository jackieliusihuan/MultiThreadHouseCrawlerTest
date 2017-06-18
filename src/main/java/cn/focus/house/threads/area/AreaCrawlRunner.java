package cn.focus.house.threads.area;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class AreaCrawlRunner {
    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1);
    
    public List<String> execute (List<String> UrlList, AreaWorkerFactory areaWorkerFactory, int limit){
        
        List<String> resultList = Collections.synchronizedList(new ArrayList<String>());//同步的线程
        
        ThreadPoolExecutor pool = new ThreadPoolExecutor(0, 15, 0, TimeUnit.HOURS, queue,new ThreadPoolExecutor.CallerRunsPolicy());
        
        List<String> templist = new ArrayList<String>();
        
        for(Iterator<String> iterator = UrlList.iterator(); iterator.hasNext();){
            String Url = iterator.next();
            iterator.remove();
            
            if(templist.size() >= limit){
                AreaBaseWorker areaBaseWorker = areaWorkerFactory.getWorker();
                areaBaseWorker.setDestList(resultList);
                areaBaseWorker.setSrcList(templist);
                pool.execute(areaBaseWorker);
                templist = new ArrayList<String>();
            }
            templist.add(Url);
        }
        
        if(templist.size() > 0){
            AreaBaseWorker areaBaseWorker = areaWorkerFactory.getWorker();
            areaBaseWorker.setDestList(resultList);
            areaBaseWorker.setSrcList(templist);
            pool.execute(areaBaseWorker);
        }
        try {
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return resultList;
    }
}
