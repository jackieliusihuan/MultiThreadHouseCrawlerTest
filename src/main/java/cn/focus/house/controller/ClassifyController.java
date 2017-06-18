package cn.focus.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.focus.house.constant.Constant;
import cn.focus.house.dao.MongoDao;
import cn.focus.house.service.classify.ClassifyService;
import cn.focus.house.service.dao.MongoService;

@Controller
@RequestMapping("/classify")
public class ClassifyController {
    @Autowired
    MongoDao mongoDao;
    @Autowired
    MongoService mongoService;
    @Autowired
    ClassifyService classifyService;
    
    @RequestMapping("/run")
    @ResponseBody
    public String classify() {

        //需要输入要合并的数据库，以及存入的数据库的集合名称
        // similar_house_data
        String[] srcDatabase = Constant.origin_database; 
        String dstDatabase = Constant.similarHouseDB;
        
        
        for(String database : srcDatabase){
            System.out.println("*********************start:  " + database + "*******************");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            classifyService.classifyDB(database, dstDatabase);
        
            System.out.println("*********************end:  " + database + "*******************");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
        }
        
        return "classify job done";
    }
    
    @RequestMapping("/{companyName}")
    @ResponseBody
    public String classifyFangtianxia(@PathVariable String companyName) {
        classifyService.classifyDB(companyName + "_origin_data", "similar_house_data");
        System.out.println("classify " +companyName+ " done");
        return "classify " +companyName+ " done";
    }
    


}
