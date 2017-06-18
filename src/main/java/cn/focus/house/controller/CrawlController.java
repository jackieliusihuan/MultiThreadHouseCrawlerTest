package cn.focus.house.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.focus.house.constant.Constant;
import cn.focus.house.service.crawl.FangTianXiaCrawlService;
import cn.focus.house.service.crawl.KuFangCrawlService;
import cn.focus.house.service.crawl.LianJiaCrawlService;
import cn.focus.house.service.crawl.MaiTianCrawlService;
import cn.focus.house.service.crawl.WoAiWoJiaCrawlService;
import cn.focus.house.threads.area.AreaCrawlRunner;
import cn.focus.house.threads.area.FangTianXiaAreaWorkerFactory;
import cn.focus.house.threads.area.KuFangAreaWorkerFactory;
import cn.focus.house.threads.area.LianJiaAreaWorkerFactory;
import cn.focus.house.threads.area.MaiTianAreaWorkerFactory;
import cn.focus.house.threads.area.WoAiWoJiaAreaWorkerFactory;
import cn.focus.house.threads.house.FangTianXiaHouseWorkerFactory;
import cn.focus.house.threads.house.HouseCrawlRunner;
import cn.focus.house.threads.house.KuFangHouseWorkerFactory;
import cn.focus.house.threads.house.LianJiaHouseWorkerFactory;
import cn.focus.house.threads.house.MaiTianHouseWorkerFactory;
import cn.focus.house.threads.house.WoAiWoJiaHouseWorkerFactory;

@Controller
@RequestMapping("/crawl")
public class CrawlController {

    @Autowired
    LianJiaCrawlService lianjiaCrawlService;
    @Autowired
    KuFangCrawlService kufangCrawlService;
    @Autowired
    FangTianXiaCrawlService fangTianXiaCrawlService;
    @Autowired
    WoAiWoJiaCrawlService woAiWoJiaCrawlService;
    @Autowired
    MaiTianCrawlService maiTianCrawlService;
    
    @Autowired
    AreaCrawlRunner areaCrawlRunner;
    
    @Autowired
    HouseCrawlRunner houseCrawlRunner;

    @Autowired
    LianJiaAreaWorkerFactory lianJiaAreaWorkerFactory;
    @Autowired
    KuFangAreaWorkerFactory kuFangAreaWorkerFactory;
    @Autowired
    FangTianXiaAreaWorkerFactory fangTianXiaAreaWorkerFactory;
    @Autowired
    WoAiWoJiaAreaWorkerFactory woAiWoJiaAreaWorkerFactory;
    @Autowired
    MaiTianAreaWorkerFactory maiTianAreaWorkerFactory;
    
    @Autowired
    LianJiaHouseWorkerFactory lianJiaHouseWorkerFactory;
    @Autowired
    KuFangHouseWorkerFactory kuFangHouseWorkerFactory;
    @Autowired
    FangTianXiaHouseWorkerFactory fangTianXiaHouseWorkerFactory;
    @Autowired
    WoAiWoJiaHouseWorkerFactory woAiWoJiaHouseWorkerFactory;
    @Autowired
    MaiTianHouseWorkerFactory maiTianHouseWorkerFactory;
    
    
    @RequestMapping("/crawlAll")
    @ResponseBody
    public String crawlAll(){
        
        lianjia();
        fangtianxia();
        woaiwojia();
        maitian();
        kufang();

        return "crawl done!";
    }
    
    
    @RequestMapping("/lianjia")
    @ResponseBody
    public String lianjia(){

        Map<String, String> districtMap = lianjiaCrawlService.getDistrictMap(Constant.LIANJIA_HOMEPAGE, 5000);
        List<String> areaList = new ArrayList<String>();
        for(Entry<String, String> entry : districtMap.entrySet()){
            Map<String, String> areaMap = lianjiaCrawlService.getAreaMap(entry.getValue(), 5000);
            if(areaMap.size()!=0){
                areaList.addAll(areaMap.values());
                System.out.println(areaList.size());
            }
        }

        System.out.println("开始抓取houselist");
        List<String> houseList = areaCrawlRunner.execute(areaList, lianJiaAreaWorkerFactory, 5);

        houseCrawlRunner.execute(houseList, lianJiaHouseWorkerFactory, 50);

        return "lianjia crawl done!";
    }

    @RequestMapping("/kufang")
    @ResponseBody
    public String kufang(){

        Map<String, String> districtMap = kufangCrawlService.getDistrictMap(Constant.KUFANG_HOMEPAGE, 5000);
        List<String> areaList = new ArrayList<String>();
       
        for(Entry<String, String> entry : districtMap.entrySet()){
            Map<String, String> areaMap = kufangCrawlService.getAreaMap(entry.getValue(), 5000);
            if(areaMap.size()!=0){
                areaList.addAll(areaMap.values());
                System.out.println(areaList.size());
            }
//            break;//测试用
        }

        System.out.println("开始抓取houselist");
        List<String> houseList = areaCrawlRunner.execute(areaList, kuFangAreaWorkerFactory, 5);
        
        houseCrawlRunner.execute(houseList, kuFangHouseWorkerFactory, 40);
        
        return "kufang crawl done!";
    }
    
    
    @RequestMapping("/fangtianxia")
    @ResponseBody
    public String fangtianxia(){
    
        Map<String, String> districtMap = fangTianXiaCrawlService.getDistrictMap(Constant.FANGTIANXIA_HOMEPAGE, 5000);
        List<String> areaList = new ArrayList<String>();
       
        for(Entry<String, String> entry : districtMap.entrySet()){
            Map<String, String> areaMap = fangTianXiaCrawlService.getAreaMap(entry.getValue(), 5000);
            if(areaMap.size()!=0){
                areaList.addAll(areaMap.values());
                System.out.println(areaList.size());
            }
//            break;//测试用
        }
        
        System.out.println("开始抓取houselist");
        List<String> houseList = areaCrawlRunner.execute(areaList, fangTianXiaAreaWorkerFactory, 5);
        
        houseCrawlRunner.execute(houseList, fangTianXiaHouseWorkerFactory, 40);
        
        return "fangtianxia crawl done!";
    }

    @RequestMapping("/woaiwojia")
    @ResponseBody
    public String woaiwojia(){
        
        Map<String, String> districtMap = woAiWoJiaCrawlService.getDistrictMap(Constant.WOAIWOJIA_HOMEPAGE, 5000);

        List<String> districtList = new ArrayList<String>();
        for(String district : districtMap.values()){
            districtList.add(district);
        }
        
        System.out.println("开始抓取houselist");
        List<String> houseList = areaCrawlRunner.execute(districtList, woAiWoJiaAreaWorkerFactory, 1);
        
        houseCrawlRunner.execute(houseList, woAiWoJiaHouseWorkerFactory, 50);
        
        return "woaiwojia crawl done!";
    }
    @RequestMapping("/maitian")
    @ResponseBody
    public String maitian(){
        
        Map<String, String> districtMap = maiTianCrawlService.getDistrictMap(Constant.MAITIAN_HOMEPAGE, 5000);
        List<String> districtList = new ArrayList<String>();
        for(String temp : districtMap.values()){
            districtList.add(temp);
        }
        
        System.out.println("开始抓取房源List");
        List<String> houseList = areaCrawlRunner.execute(districtList, maiTianAreaWorkerFactory, 1);
        houseCrawlRunner.execute(houseList, maiTianHouseWorkerFactory, 50);
        
        return "maitian crawl done!";
    }


}
