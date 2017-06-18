package cn.focus.house.periodic_house_crawl.service;

import java.util.List;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.focus.house.constant.Constant;
import cn.focus.house.dao.MongoDao;
import cn.focus.house.model.SimilarHouseSource;
import cn.focus.house.service.crawl.LianJiaCrawlService;
import cn.focus.house.service.crawl.MaiTianCrawlService;
import cn.focus.house.service.crawl.WoAiWoJiaCrawlService;
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
public class ServiceTest {
    
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
  MaiTianCrawlService maitianCrawlService;
  
  @Autowired
  MongoService mongoService;
  @Autowired
  MongoDao mongoDao;
   
  
  @Test
    public void test() {}
    
    
}
