package cn.focus.house.periodic_house_crawl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.Mongo;

import cn.focus.house.constant.Constant;
import cn.focus.house.dao.MongoDao;
import cn.focus.house.model.HouseSource;
import cn.focus.house.model.Review;
import cn.focus.house.service.crawl.FangTianXiaCrawlService;
import cn.focus.house.service.crawl.LianJiaCrawlService;
import cn.focus.house.service.crawl.MaiTianCrawlService;
import cn.focus.house.service.dao.MongoService;
import cn.focus.house.threads.area.AreaCrawlRunner;
import cn.focus.house.threads.area.FangTianXiaAreaWorkerFactory;
import cn.focus.house.threads.area.LianJiaAreaWorkerFactory;
import cn.focus.house.threads.house.FangTianXiaHouseWorkerFactory;
import cn.focus.house.threads.house.HouseCrawlRunner;
import cn.focus.house.threads.house.LianJiaHouseWorkerFactory;
import cn.focus.house.utils.CrawlerWithProxy;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/appcontext-periodic-house-crawler.xml")
public class ServiceTest2 {
    @Autowired
    MongoService mongoService;
    
    @Autowired
    LianJiaCrawlService lianjiaCrawlService;
    
    
    @Autowired
    MaiTianCrawlService maiTianCrawlService;
    @Autowired
    AreaCrawlRunner areaCrawlRunner;
    
    @Autowired
    LianJiaAreaWorkerFactory lianJiaAreaWorkerFactory;
    
    @Autowired
    HouseCrawlRunner houseCrawlRunner;
    
    @Autowired
    LianJiaHouseWorkerFactory lianJiaHouseWorkerFactory;
    
    @Autowired
    FangTianXiaCrawlService fangTianXiaCrawlService;
    @Autowired
    FangTianXiaAreaWorkerFactory fangTianXiaAreaWorkerFactory;
    @Autowired
    FangTianXiaHouseWorkerFactory fangTianXiaHouseWorkerFactory;
    
    @Autowired 
    MongoDao mongoDao;
    
    @Test
    public void test(){
        
        System.out.println("1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
       System.out.println("2");
       try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        System.out.println("Done!");
    }
    
    
}
