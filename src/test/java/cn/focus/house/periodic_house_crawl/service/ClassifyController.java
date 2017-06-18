package cn.focus.house.periodic_house_crawl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.json.JsonMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.mongodb.Mongo;

import cn.focus.house.constant.Constant;
import cn.focus.house.dao.MongoDao;
import cn.focus.house.model.HouseSource;
import cn.focus.house.model.SimilarHouseSource;
import cn.focus.house.service.crawl.LianJiaCrawlService;
import cn.focus.house.service.crawl.MaiTianCrawlService;
import cn.focus.house.service.crawl.WoAiWoJiaCrawlService;
import cn.focus.house.service.dao.ClassifyMongoService;
import cn.focus.house.service.dao.MongoService;
import cn.focus.house.threads.area.AreaCrawlRunner;
import cn.focus.house.threads.area.LianJiaAreaWorkerFactory;
import cn.focus.house.threads.area.MaiTianAreaWorkerFactory;
import cn.focus.house.threads.area.WoAiWoJiaAreaWorkerFactory;
import cn.focus.house.threads.house.HouseCrawlRunner;
import cn.focus.house.threads.house.LianJiaHouseWorkerFactory;
import cn.focus.house.threads.house.MaiTianHouseWorkerFactory;
import cn.focus.house.threads.house.WoAiWoJiaHouseWorkerFactory;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/appcontext-periodic-house-crawler.xml")
public class ClassifyController {
    
    @Autowired
    LianJiaCrawlService lianjiaCrawlService;
    
    @Autowired
    AreaCrawlRunner areaCrawlRunner;
    
    @Autowired
    LianJiaAreaWorkerFactory lianJiaAreaWorkerFactory;
    
    @Autowired
    HouseCrawlRunner houseCrawlRunner;
    
    @Autowired
    LianJiaHouseWorkerFactory lianJiaHouseWorkerFactory;
    
    @Autowired
    WoAiWoJiaCrawlService woAiWoJiaCrawlService;
    
    @Autowired
    WoAiWoJiaAreaWorkerFactory woAiWoJiaAreaWorkerFactory;
    
    @Autowired
    MaiTianAreaWorkerFactory maiTianAreaWorkerFactory;
    
    @Autowired
    MaiTianHouseWorkerFactory maiTianHouseWorkerFactory;
    
    @Autowired
    WoAiWoJiaHouseWorkerFactory woAiWoJiaHouseWorkerFactory;
    @Autowired
    MongoService mongoService;
    
  @Autowired
  MaiTianCrawlService maitianCrawlService;
  
  @Autowired
  MongoDao mongoDao;
  
  @Autowired
  ClassifyMongoService classifyMongoService;
    @Test
    public void test() {
        
       /* //需要输入要合并的数据库，以及存入的数据库的集合名称
        // similar_house_data
        String[] srcDatabase = Constant.origin_database; 
        String dstDatabase = "similar_house_data";
        
        
        for(String database : srcDatabase){
            saveDatabase(database, dstDatabase);
        }*/
        
        saveDatabase("kufang_origin_data", "similar_house_data");
    }
    
    public void saveDatabase(String srcDatabase, String dstDatabase) {
        System.out.println(mongoDao.count(srcDatabase, new Document()));
        List<HouseSource> houseList = mongoService.getAllHouseSources(srcDatabase);
        System.out.println("未归类的总的总的房屋数量" + houseList.size());
        for(HouseSource houseSource : houseList){
            classifyMongoService.saveHouseSource(houseSource, dstDatabase);
        }
        
    }
    
    
}
