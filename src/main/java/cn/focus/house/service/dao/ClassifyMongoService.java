package cn.focus.house.service.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.focus.house.dao.MongoDao;
import cn.focus.house.model.HouseSource;
import cn.focus.house.model.SimilarHouseSource;

@Service
public class ClassifyMongoService {
    @Resource
    private MongoDao mongoDao;
    //******************************************************
    // 注意在这里进行归类时，参考了四个特性
    //buildingName： 小区的名字
    //area：房屋的面积，这里取floor进行存储
    //houseType：房屋几室几厅，这里暂时只用室这一个条件
    //orientation：朝向
    //*******************************************************
    public void saveHouseSource(HouseSource houseSource, String colName) {
        
        if(houseSource == null){
            return;
        }
        Document query = new Document();
       
        Document existedDocument = null;
        try {
            query.append("houseSource.buildingName", houseSource.getBuildingName());
            
            String houseType = houseSource.getHouseType();
            if(houseType.indexOf("室") != -1){
                houseType = houseType.substring(houseType.indexOf("室")-1, houseType.indexOf("室")+1);
            }
            houseSource.setHouseType(houseType);
            query.append("houseSource.houseType", houseType);
            
            query.append("houseSource.orientation", houseSource.getOrientation());
            
            query.append("houseSource.area", Math.floor(houseSource.getArea()));
            
            existedDocument = mongoDao.findOne(colName, query);
            houseSource.setArea(Math.floor(houseSource.getArea()));
        } catch (NullPointerException e) {
            //对于不规范的数据，可能是个别网页页面的不规范，也可能是房源信息有错误
            //这里选择直接丢掉
            e.printStackTrace();
            return;
        }
        
        if (existedDocument == null) {
            //数据库里没有这个类型的房源
            System.out.println("插入的是新房源");
            mongoDao.insert(colName, Document.parse(JSON.toJSONString(new SimilarHouseSource(houseSource))));
        }else{
            //数据库里已经有这个类型的房源

            System.out.println("插入的是已有房源");
            
            ObjectId ID= existedDocument.getObjectId("_id");
            SimilarHouseSource similarHouseSource = JSON.parseObject(existedDocument.toJson(), SimilarHouseSource.class);
            
            similarHouseSource.getSimilarHouseList().add(houseSource);
            similarHouseSource.setSize(similarHouseSource.getSize()+1);
            mongoDao.updateById(colName, ID, Document.parse(JSON.toJSONString(similarHouseSource)));
        }
    }
    
public List<SimilarHouseSource> querySimilarByAmount(String colName, int skip, int limit){
        
        Document document = new Document();
        Document order = new Document();
        order.append("size", -1);
        
        List<Document> docList = mongoDao.find(colName, document, null, order, skip, limit);
        List<SimilarHouseSource> similarHouseSourceList = new ArrayList<SimilarHouseSource>();
        for(Document documenttemp :docList){
            similarHouseSourceList.add(JSON.parseObject(documenttemp.toJson(), SimilarHouseSource.class));
        }
        return similarHouseSourceList;
    }
    
    
}




















