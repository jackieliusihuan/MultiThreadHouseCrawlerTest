package cn.focus.house.service.crawl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bson.json.JsonMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.focus.house.constant.Constant;
import cn.focus.house.model.HouseSource;
import cn.focus.house.model.Review;
import cn.focus.house.utils.CrawlerWithProxy;
import cn.focus.house.utils.strDealUtil;

//可以正常向数据库存储，并且记录无法抓取的数据
//出现问题：链家访问次数过多会限制IP

@Service
public class LianJiaCrawlService implements CrawlService {

    private strDealUtil strDeal = new strDealUtil();
    public File file = new File("d:/lianjia.txt");
    // FileUtils.writeStringToFile(file, address + "\n", true);
    
    
    //获得每个区的主页
    @Override
    public Map<String, String> getDistrictMap(String url, int timeout){
        Map<String, String> districtMap = new HashMap<String, String>();
        try {
            Document doc = Jsoup.parse(CrawlerWithProxy.getHtml(url));
            //Elements elements=doc.select("div[data-role=ershoufang] a");
            
            for(Element element : doc.select("div[data-role=ershoufang] a")){
                String districtUrl = Constant.LIANJIA_HOMEPAGE+element.attr("href").split("/")[2]+"/";
                districtMap.put(element.html(), districtUrl);
                System.out.println(element.html() + districtUrl);
                
            }
           
        } catch (Exception e) {
            System.out.println("读取链家district页面出现错误： " + url);
            e.printStackTrace();
        }

        return districtMap;
    }
    
    
    @Override
    public Map<String, String> getAreaMap(String districtUrl, int timeout) {
        Map<String, String> areaMap = new HashMap<String, String>();
        try {
            Document doc = Jsoup.parse(CrawlerWithProxy.getHtml(districtUrl));
            
            Elements areaHrefs = doc.select(".m-filter .position div[data-role=ershoufang] div").get(1).select("a");
            for (Element area : areaHrefs) {
                String areaUrl = Constant.LIANJIA_HOMEPAGE + area.attr("href").split("/")[2]+"/";
                areaMap.put(area.html(), areaUrl);
                System.out.println(area.html() + areaUrl);
                //areaMap.put(area.html(), area.absUrl("href"));
            }
            
        } catch (Exception e) {
            System.out.println("读取链家area页面出现错误： " + districtUrl);
            e.printStackTrace();
        }
        return areaMap;
    }

    public ArrayList<String> getHouseList(String areaUrl, int timeout) {
        
        ArrayList<String> houseUrlList=new ArrayList<String>();
        
        try {
            int totalPage = 0;
            Document doc = Jsoup.parse(CrawlerWithProxy.getHtml(areaUrl));
            
            Elements elements = doc.select(".contentBottom .page-box");
            Element element = elements.last();
            // 判断房屋列表是不是为空
            if (element.attr("page-data") == null || element.attr("page-data") == "") {
                // System.out.println("没房子");
                return houseUrlList;
            }
            
            String str = element.attr("page-data");
            // 解析json,获得当前城区总页数
            JSONObject jsStr = JSON.parseObject(str);
            totalPage = jsStr.getInteger("totalPage");

            for (int temp = 1; temp <= totalPage; temp++) {

                String address = areaUrl + "pg" + temp + "/";
                try {
                    doc = Jsoup.parse(CrawlerWithProxy.getHtml(address));
                } catch (Exception e) {
                    System.out.println("链家网某个区第" + temp + "页读取错误! 地址：" + address);
                    //FileUtils.writeStringToFile(file, "链家网某个区第" + temp + "页读取错误! 地址：" + address + "\n", true);
                    e.printStackTrace();
                    continue;
                }

                for(Element elementTemp : doc.select(".sellListContent .clear .img")){
                    houseUrlList.add(elementTemp.attr("href"));
                    System.out.println("具体一个房源的URL地址 ：  " + elementTemp.attr("href"));
                }
              
            }
        } catch (Exception e) {
            System.out.println("链家网读取某个区主页面出现错误: " + areaUrl);
            e.printStackTrace();
        }

        return houseUrlList;
    }

    @Override
    public HouseSource crawlDescPage(String houseUrl, int timeout) {
                
        System.out.println(houseUrl);//用于观察哪个网页出现问题
        
        HouseSource houseSource = new HouseSource();
        houseSource.setHouseUrl(houseUrl);
        try {
            Document doc = Jsoup.parse(CrawlerWithProxy.getHtml(houseUrl));            

            Elements elements = null;
            Element element = null;
            // 楼盘名称：
            houseSource.setBuildingName(doc.select(".communityName a").get(0).html());
            
            // 房源标题：
            houseSource.setBuildingTitle(doc.select(".title").get(0).select("h1").html());
            
            // 总价：
            String temp = strDeal.toPriceDeal(doc.select(".price ").get(0).select(".total").html()
                    + doc.select(".price ").get(0).select(".unit span").html());
            houseSource.setTotalPrice(Double.parseDouble(temp));
            
            // 单价：
            String unitPriceValue = doc.select(".unitPriceValue").get(0).html();
            int index = unitPriceValue.indexOf('<');
            unitPriceValue = unitPriceValue.substring(0, index) + unitPriceValue.substring(index + 3);
            index = unitPriceValue.indexOf('<');
            unitPriceValue = unitPriceValue.substring(0, index) + unitPriceValue.substring(index + 4);
            houseSource.setUnitPrice(Integer.parseInt(strDeal.uniPriceDeal(unitPriceValue)));
            
            // 面积：
            houseSource.setArea(Double.parseDouble(strDeal.areaDeal(doc.select(".mainInfo").get(2).html())));
            
            // 户型：
            houseSource.setHouseType(doc.select(".mainInfo").get(0).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            
            // 朝向：
            houseSource.setOrientation(doc.select(".mainInfo").get(1).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            
            // 楼层：
            houseSource.setFloor(doc.select(".subInfo").get(0).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            
            // 装修：
            houseSource.setDecoration(doc.select(".subInfo").get(1).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            
            // 建筑年代：
            houseSource.setBuildingAge(doc.select(".subInfo").get(2).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            
            // 房源标签：房屋优势特点
            String buildingTagsValue = "";
            elements = doc.select(".content .tag");
            for (int i = 1; i < elements.size(); i++) {
                element = elements.get(i);
                buildingTagsValue += element.html() + " ";
            }
            houseSource.setBuildingTags(buildingTagsValue);
            System.out.println("房源标签" + buildingTagsValue);
            
            // 公司名称
            houseSource.setCompanyName("链家");
            System.out.println("公司名称" + "链家");
            
            // 房源编号：
            String buildingNumValue = doc.select(".houseRecord").get(0).select(".info  ").html();
            index = buildingNumValue.indexOf('<');
            buildingNumValue = buildingNumValue.substring(0, index);
            houseSource.setBuildingNum(buildingNumValue.trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            System.out.println("房源编号" + buildingNumValue);
            
            // review 经纪人姓名 电话 点评内容 点评时间
            ArrayList<Review> reviews = new ArrayList<Review>();

            // int page = 1;
            String address = "http://bj.lianjia.com/ershoufang/showcomment?isContent=1&page=1&order=0&id="+ buildingNumValue;
            address = address.trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            
            
            
            try {
                System.out.println("经纪人评论链接： " + address);
                String commentsStr = CrawlerWithProxy.getHtml(address);
                JSONObject commentsJson = JSON.parseObject(commentsStr); 
                JSONArray agentList = commentsJson.getJSONObject("data").getJSONArray("agentList");
                if(agentList != null && agentList.size() > 0){
                    for(Object object : agentList){
                        String agentName = ((JSONObject)object).getString("name");
                        String agentPhone = ((JSONObject)object).getString("phone");
                        String agentComment = ((JSONObject)object).getString("comment");
                        String agentDate = ((JSONObject)object).getString("lastestSeeRecordDate");
                        reviews.add(new Review(agentName,agentPhone,agentComment,agentDate));
                    }
                }else{
                    return  houseSource; 
                }
                houseSource.setReviews(reviews);
            } catch (Exception e) {
                System.out.println("链家网抓取一个具体房源页面的经纪人以及评论出现错误： " + address);
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            System.out.println("链家网抓取一个具体房源页面出现错误！" + houseUrl);
            e.printStackTrace();
            return null;
        }
        return houseSource;
    }

}
