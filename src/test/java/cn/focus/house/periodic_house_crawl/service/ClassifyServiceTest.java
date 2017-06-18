package cn.focus.house.periodic_house_crawl.service;

import javax.xml.stream.events.StartDocument;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.focus.house.dao.MongoDao;
import cn.focus.house.service.classify.ClassifyService;
import cn.focus.house.service.crawl.LianJiaCrawlService;
import cn.focus.house.service.dao.MongoService;
import cn.focus.house.threads.area.AreaCrawlRunner;
import cn.focus.house.threads.area.LianJiaAreaWorkerFactory;
import cn.focus.house.threads.house.HouseCrawlRunner;
import cn.focus.house.threads.house.LianJiaHouseWorkerFactory;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/appcontext-periodic-house-crawler.xml")
public class ClassifyServiceTest {
    @Autowired
    MongoService mongoService;
    
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
    MongoDao mongoDao;
    
    @Autowired
    ClassifyService classifyService;
    
    @Test
    public void test(){
        System.out.println("start");
        classifyService.classifyDB("fangtianxia_origin_data", "similar_house_data");
        System.out.println("end");
        
    }
    
}
