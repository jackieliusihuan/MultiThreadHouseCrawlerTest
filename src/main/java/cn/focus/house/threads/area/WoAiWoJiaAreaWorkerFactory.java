package cn.focus.house.threads.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.focus.house.service.crawl.WoAiWoJiaCrawlService;
@Component
public class WoAiWoJiaAreaWorkerFactory implements AreaWorkerFactory{

    @Autowired
    WoAiWoJiaCrawlService woAiWoJiaCrawlService;
    
    @Override
    public  AreaBaseWorker getWorker(){
        return new AreaBaseWorker() {
            
            @Override
            public List<String> crawlJob(String areaUrl, int timeout) {
                return woAiWoJiaCrawlService.getHouseList(areaUrl, timeout);
            }
        };
    };

}
