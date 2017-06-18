package cn.focus.house.threads.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.focus.house.service.crawl.LianJiaCrawlService;
@Component
public class LianJiaAreaWorkerFactory implements AreaWorkerFactory{

    @Autowired
    LianJiaCrawlService lianJiaCrawlService;
    
    @Override
    public  AreaBaseWorker getWorker(){
        return new AreaBaseWorker() {
            
            @Override
            public List<String> crawlJob(String areaUrl, int timeout) {
                return lianJiaCrawlService.getHouseList(areaUrl, timeout);
            }
        };
    };

}
