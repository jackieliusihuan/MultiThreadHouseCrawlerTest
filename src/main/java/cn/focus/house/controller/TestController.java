package cn.focus.house.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.focus.house.service.crawl.LianJiaCrawlService;

@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private LianJiaCrawlService lianjiaCrawlService;

    @RequestMapping("/lianjia")
    @ResponseBody
    public String test() {
        
        //lianjiaCrawlService.getDistrictMap(Constant.LIANJIA_HOMEPAGE,100);
        //lianjiaCrawlService.getAreaMap("http://bj.lianjia.com/ershoufang/dongcheng/", 100);
        lianjiaCrawlService.getHouseList("http://bj.lianjia.com/ershoufang/andingmen/", 0);
        return "test Done";
    }

}
