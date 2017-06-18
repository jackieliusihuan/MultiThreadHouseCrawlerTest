package cn.focus.house.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Detail;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.focus.house.constant.Constant;
import cn.focus.house.dao.MongoDao;
import cn.focus.house.model.HouseSource;
import cn.focus.house.model.SimilarHouseSource;
import cn.focus.house.service.dao.ClassifyMongoService;
import cn.focus.house.service.dao.MongoService;

@Controller
@RequestMapping("/read")
public class ReadController {
    @Resource
    private MongoDao mongoDao;

    @Autowired
    MongoService mongoService;
    
    @Autowired 
    ClassifyMongoService classifyMongoService;
    
    @RequestMapping("/index")
    public String index() {
       return "redirect:/read/elf-list/{1}";
    }
    
  
    
    
    @RequestMapping("/esf-list/{page}")
    public String index(Model model, HttpServletRequest request, @PathVariable String page) {
        //list标题题目长度25最合适
        int totalHouseNum= mongoDao.find(Constant.similarHouseDB, new Document(), null, null, 0, 0).size();
        int totalPage ;
        if(totalHouseNum%30 == 0){
            totalPage = totalHouseNum/30;
        }else{
            totalPage = totalHouseNum/30 + 1;
        }
        model.addAttribute("totalPage",totalPage);
        
        int pageNum = Integer.parseInt(page);
        if(pageNum>totalPage){
            pageNum = totalPage;
        }
        model.addAttribute("currentPage", pageNum);
        
        List<SimilarHouseSource> similarHouseSourceList = classifyMongoService.querySimilarByAmount("similar_house_data", 30*(pageNum-1),30);
        for(SimilarHouseSource similarHouseSourceTemp : similarHouseSourceList){
            int titleLength = similarHouseSourceTemp.getHouseSource().getBuildingTitle().length();
            if(titleLength >18){
                titleLength = 18;
            }
            similarHouseSourceTemp.getHouseSource().setBuildingTitle(similarHouseSourceTemp.getHouseSource().getBuildingTitle().substring(0, titleLength)+"....");
        }
        
        model.addAttribute("similarHouseSourceList", similarHouseSourceList);

        
        
        return "esf-list";
    }
    
    
    @RequestMapping("/esf-similar")
    public String SimilarHouseSource(HttpServletRequest request, Model model){
        String houseID = request.getParameter("id");
        if(houseID == null || houseID.equals("")){
            return null;
        }
        
        Document query = new Document();
        query.append("houseSource.buildingNum", houseID);
        Document similarHouseSourceDocument = mongoDao.findOne("similar_house_data", query);
        SimilarHouseSource similarHouseSource = JSON.parseObject(similarHouseSourceDocument.toJson(), SimilarHouseSource.class);
       
        List<String> similarHouseList = new ArrayList<String>();
        similarHouseList.add(similarHouseSource.getHouseSource().getHouseUrl());
        for(HouseSource temp : similarHouseSource.getSimilarHouseList()){
            similarHouseList.add(temp.getHouseUrl());
        }
        model.addAttribute("similarHouseList", similarHouseList);
        
        return "similar_house";
    }
    
    
    @RequestMapping("/esf-detail")
    public String detail(Model model) {
        return "esf-detail";
    }
}
