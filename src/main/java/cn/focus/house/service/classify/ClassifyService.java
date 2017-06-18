package cn.focus.house.service.classify;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.focus.house.dao.MongoDao;
import cn.focus.house.model.HouseSource;
import cn.focus.house.service.dao.ClassifyMongoService;
import cn.focus.house.service.dao.MongoService;


@Service
public class ClassifyService {
    
    // classified_house_data 数据库名称
    @Autowired
    MongoDao mongoDao;
    
    @Autowired
    ClassifyMongoService classifyMongoService;
    
    @Autowired
    MongoService mongoService;
    
    
    public void classifyDB(String srcDatabase, String dstDatabase) {
        
        System.out.println(mongoDao.count(srcDatabase, new Document()));//测试用

        List<HouseSource> houseList = mongoService.getAllHouseSources(srcDatabase);
        
        System.out.println("未归类的总的总的房屋数量" + houseList.size());//测试用
        
        for(HouseSource houseSource : houseList){
            classifyMongoService.saveHouseSource(houseSource, dstDatabase);
        }
        
        }


}
