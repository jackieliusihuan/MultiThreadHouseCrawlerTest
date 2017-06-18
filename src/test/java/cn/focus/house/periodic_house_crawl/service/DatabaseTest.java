package cn.focus.house.periodic_house_crawl.service;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.focus.house.dao.MongoDao;
import cn.focus.house.service.crawl.LianJiaCrawlService;
import cn.focus.house.service.dao.MongoService;
import cn.focus.house.threads.area.AreaCrawlRunner;
import cn.focus.house.threads.area.LianJiaAreaWorkerFactory;
import cn.focus.house.threads.house.HouseCrawlRunner;
import cn.focus.house.threads.house.LianJiaHouseWorkerFactory;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/appcontext-periodic-house-crawler.xml")
public class DatabaseTest {
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
    
    @Test
    public void test(){
        
        /*HouseSource houseSource = new HouseSource();)
        houseSource.setBuildingName("liusihuantest");
        mongoService.saveHouseSource(houseSource, "test_liusihuan");
        */
        
       /* for(int i=0;i<=10;i++){
            System.out.println(mongoDao.find("similar_house_data", new Document(), null, null, i, 1));
        }*/
        
        
      //  System.out.println(mongoDao.find("kufang_origin_data", new org.bson.Document(), null, null, 0, 0));
        /*mongoDao.delete("similar_house_data", new Document());
        System.out.println(mongoDao.count("similar_house_data", new Document()));*/
       /* System.out.println(mongoDao.count("lianjia_origin_data", new Document()));
        System.out.println(mongoDao.count("fangtianxia_origin_data", new Document()));
        System.out.println(mongoDao.count("kufang_origin_data", new Document()));
        System.out.println(mongoDao.count("woaiwojia_origin_data", new Document()));
        System.out.println(mongoDao.count("maitian_origin_data", new Document()));*/
        
        //System.out.println(mongoDao.find("similar_house_data", new org.bson.Document(),null,null,));
      //  mongoDao.insert("similar_house_data", new Document());
      //  System.out.println(mongoDao.count("kufang_origin_data", new org.bson.Document()));
       
       /*  for(int i =400;i<10000;i=i+50){
            System.out.println("***********************************************************************");
            System.out.println(mongoDao.find("similar_house_data", new Document(),null,null,i,1));
        }*/
        
    /*    System.out.println(mongoDao.find("similar_house_data", new Document(),null,null,10,1));("kufang_origin_data", new Document(),null,null,10,1));
    
        
        Document query = new Document();
       query.append("houseSource.buildingName", "西三环北路105号院");
      //  query.append("buildingNum", "dda21321");
        
        
        System.out.println(mongoDao.findOne("similar_house_data",query));
        System.out.println(mongoDao.count("similar_house_data",query));*/
      //  mongoDao.delete("similar_house_data", new Document());
        //mongoDao.delete("kufang_origin_data", new Document());
        
         System.out.println(mongoDao.count("lianjia_origin_data",new Document()));
         System.out.println(mongoDao.count("woaiwojia_origin_data",new Document()));
         System.out.println(mongoDao.count("maitian_origin_data",new Document()));
         System.out.println(mongoDao.count("fangtianxia_origin_data",new Document()));
         System.out.println(mongoDao.count("kufang_origin_data",new Document()));
         System.out.println(mongoDao.count("similar_house_data", new Document()));
         
         /*System.out.println("lianjia"+mongoDao.findOne("lianjia_origin_data", new Document()));
         System.out.println("*******************************************************");
         System.out.println("maitian"+mongoDao.findOne("maitian_origin_data", new Document()));
         System.out.println("*******************************************************");
         System.out.println("woaiwojia"+mongoDao.findOne("woaiwojia_origin_data", new Document()));
         System.out.println("*******************************************************");
         System.out.println("kufang"+mongoDao.findOne("kufang_origin_data", new Document()));
         System.out.println("*******************************************************");
         System.out.println("fangtianxia"+mongoDao.findOne("fangtianxia_origin_data", new Document()));*/
       
       //  System.out.println(mongoDao.findOne("similar_house_data", new Document()));
         
         
         /*  System.out.println("*********************");
         query.append("houseSource.buildingNum", "4510293625 ");
Document document= mongoDao.findOne(Constant.similarHouseDB, query);
         System.out.println(document);*/
         
         /* HouseSource houseSource = new HouseSource();
        houseSource.setBuildingName("翠城");
        mongoDao.insert("liusihuantest", Document.parse(JSON.toJSONString(houseSource)));
        Document query = new Document();
      query.append("buildingName", "核二院");
      Document query2 = new Document();
      query2.append("buildingName", "翠城");
      System.out.println(mongoDao.findOne("liusihuantest",query));
      System.out.println(mongoDao.findOne("liusihuantest",query2));*/
        
       // JOptionPane.showMessageDialog(null, "标题",Long.toString(mongoDao.count("lianjia_origin_data", new org.bson.Document())) , JOptionPane.ERROR_MESSAGE);
  /*      System.out.println(mongoDao.find("lianjia_origin_data", new Document(),null,null,10,1));
        System.out.println("***********************************************************************");
        System.out.println(mongoDao.find("fangtianxia_origin_data", new Document(),null,null,10,1));
        System.out.println("***********************************************************************");
        System.out.println(mongoDao.find("woaiwojia_origin_data", new Document(),null,null,10,1));
        System.out.println("***********************************************************************");
        System.out.println(mongoDao.find("kufang_origin_data", new Document(),null,null,10,1));
        System.out.println("***********************************************************************");
        System.out.println(mongoDao.find("maitian_origin_data", new Document(),null,null,10,1));
        System.out.println("***********************************************************************");
        */
        
        
    }
    
}
