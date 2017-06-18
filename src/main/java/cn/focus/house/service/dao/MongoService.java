package cn.focus.house.service.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.focus.house.dao.MongoDao;
import cn.focus.house.model.HouseSource;

/*
 * 处理mongo数据库相关操作
 */
@Service
public class MongoService {
    @Resource
    private MongoDao mongoDao;

    public void save(HouseSource houseSource, String colName) {
        org.bson.Document dbDocument = org.bson.Document.parse(JSON.toJSONString(houseSource));
        mongoDao.insert(colName, dbDocument);
    }
    
    // 这个存储方法保证数据库里不会重复存储
    public void saveHouseSource(HouseSource houseSource, String colName) {
        Document query = new Document();
        query.append("buildingNum",houseSource.getBuildingNum());
        query.append("buildingName", houseSource.getBuildingName());
        if(mongoDao.findOne(colName, query) != null){
            System.out.println("这个房源已经存在于数据库");
            return;
        }
        org.bson.Document dbDocument = org.bson.Document.parse(JSON.toJSONString(houseSource));
        mongoDao.insert(colName, dbDocument);
    }
    
    //Document.parse(JSON.toJSONString(houseSource)
    //public List<Document> find(String collectionName, Document query, Document key, Document order, int skip,int limit) {
    public List<HouseSource> getHouseSources(String colName, HouseSource houseSource){
        List<Document> docList = mongoDao.find(colName, Document.parse(JSON.toJSONString(houseSource)), null, null, 0, 0);
        List<HouseSource> houseSourceList = new ArrayList<HouseSource>();
        for(Document document :docList){
            houseSourceList.add(JSON.parseObject(document.toJson(), HouseSource.class));
        }
        return houseSourceList;
    }
    
    
    public List<HouseSource> getAllHouseSources(String colName){
        List<Document> docList = mongoDao.find(colName, new Document(), null, null, 0, 0);
        List<HouseSource> houseSourceList = new ArrayList<HouseSource>();
        for(Document document :docList){
            houseSourceList.add(JSON.parseObject(document.toJson(), HouseSource.class));
        }
        return houseSourceList;
    }
    
    
    
    
    public List<Document> getHouseSourceDocuments(String colName, HouseSource houseSource){
        return mongoDao.find(colName, Document.parse(JSON.toJSONString(houseSource)), null, null, 0, 0);
    }
    
    
    public List<HouseSource> queryHouseSources(String colName, HouseSource houseSource,int skip, int limit){
        
        Document document;
        if(houseSource == null){
            document = new Document();
        }else{
            document = Document.parse(JSON.toJSONString(houseSource));
        }
        
        List<Document> docList = mongoDao.find(colName, document, null, null, skip, limit);
        List<HouseSource> houseSourceList = new ArrayList<HouseSource>();
        for(Document documenttemp :docList){
            houseSourceList.add(JSON.parseObject(documenttemp.toJson(), HouseSource.class));
        }
        return houseSourceList;
    }
    

    public void insertDoc(Document doc, String colName) {
        mongoDao.insert(colName, doc);
    }

    public List<Document> findDoc(String colName, int skip, int docNum) {
        Document query = new Document();
        List<Document> result = mongoDao.find(colName, query, null, null, skip, docNum);
        return result;
    }
}
