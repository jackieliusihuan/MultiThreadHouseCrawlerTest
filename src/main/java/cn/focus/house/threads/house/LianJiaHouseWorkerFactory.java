package cn.focus.house.threads.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.focus.house.model.HouseSource;
import cn.focus.house.service.crawl.LianJiaCrawlService;
import cn.focus.house.service.dao.MongoService;
@Component
public class LianJiaHouseWorkerFactory implements HouseWorkerFactory {

    @Autowired
    LianJiaCrawlService lianJiaCrawlService;
    @Autowired
    MongoService mongoService;
    
    @Override
    public HouseBaseWorker getWorker(List<String> houseList) {
        return new HouseBaseWorker(houseList) {
            
            @Override
            public void saveHouse(HouseSource houseSource) {
                if(houseSource.getBuildingName() != null && "".equals(houseSource.getBuildingName()) != true ){
                    mongoService.saveHouseSource(houseSource, "lianjia_origin_data");
                    System.out.println(houseSource.getBuildingNum() + "  号房源存储成功");
                }
            }
            
            @Override
            public HouseSource crawlJob(String houseUrl, int timeout) {
                return lianJiaCrawlService.crawlDescPage(houseUrl, timeout);
            }
        };
    }




}
