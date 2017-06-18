package cn.focus.house.service.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import cn.focus.house.constant.Constant;
import cn.focus.house.model.HouseSource;
import cn.focus.house.model.Review;

//可以正常向数据库存储，并且记录无法抓取的数据

@Service
public class KuFangCrawlService implements CrawlService {


    public ArrayList<String> getDistrict(String url, int timeout) {
        ArrayList<String> dtList = new ArrayList<String>();
        Document doc;
        try {
            
            doc = Jsoup.connect(url).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            for (int i = 2; i < doc.select("#areaDiv a").size(); i++) {
                // 获取所有的区的URL
                String temp = doc.select("#areaDiv a").get(i).absUrl("href");
                dtList.add(temp);
            }
        } catch (IOException e) {
            System.out.println("酷房网区县列表页读取失败: " + url);
            // try {
            // FileUtils.writeStringToFile(file,"酷房网区县列表页读取失败: " + url + "\n",
            // true);
            // } catch (IOException e1) {
            // e1.printStackTrace();
            // }
            e.printStackTrace();
        }
        return dtList;
    }

    public ArrayList<String> crawlMainPage(String url, int timeout) {
        ArrayList<String> houseUrlList = new ArrayList<String>();
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            if (doc.select(".multipage span").size() == 0) {
                return null;
            }
            int totalPage = Integer
                    .parseInt(doc.select(".multipage span").get(doc.select(".multipage span").size() - 2).text());
            int page = 1;

            for (; page <= totalPage; page++) {
                String address = url + "pg" + page + "/";
                try {
                    doc = Jsoup.connect(address).userAgent(Constant.USER_AGENT).timeout(timeout).get();
                } catch (IOException e) {
                    System.out.println("库房网某个区第" + page + "页读取错误! 地址：" + address);
                    // FileUtils.writeStringToFile(file,"库房网某个区第" + page +
                    // "页读取错误! 地址：" + address + "\n", true);
                    e.printStackTrace();
                    continue;
                }
                // 获取一个区的所有房源的URL
                Elements elements = doc.select(".listing #biaoti_a");
                for (int i = 0; i < elements.size(); i++) {// 获取一页当中房源的URL
                    houseUrlList.add(elements.get(i).absUrl("href"));
//                    System.out.println(elements.get(i).absUrl("href"));
                }
            }
        } catch (IOException e) {
            System.out.println("库房网读取某个区主页面出现错误: " + url);
            // try {
            // FileUtils.writeStringToFile(file,"库房网读取某个区主页面出现错误: " + url +
            // "\n", true);
            // } catch (IOException e1) {
            // e1.printStackTrace();
            // }
            e.printStackTrace();
        }
        return houseUrlList;
    }


    @Override
    public Map<String, String> getDistrictMap(String mainUrl, int timeout) {
        Map<String, String> dtMap = new HashMap<String, String>();
        try {
            Document doc = Jsoup.connect(mainUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            for(Element href : doc.select("#areaDiv a")){
                if ("不限".equals(href.html()) || "".equals(href.html()) || href.html()==null) {
                    continue;
                }
                dtMap.put(href.html(), href.absUrl("href"));
            }
        } catch (IOException e) {
            System.out.println("读取酷房网district页面出现错误" + mainUrl);
            e.printStackTrace();
        }
        return dtMap;
    }
   
    @Override
    public Map<String, String> getAreaMap(String districtUrl, int timeout) {
        Map<String, String> areaMap = new HashMap<String, String>();
        try {
            Elements hrefs = Jsoup.connect(districtUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get().select("#filterTargetSub a");
            for (Element href : hrefs) {
                areaMap.put(href.html(), href.absUrl("href"));
            }
        } catch (IOException e) {
            System.out.println("读取酷房网area页面出现错误" + districtUrl);
            e.printStackTrace();
        }
        if(areaMap.size() <= 0) return null;
        
        return areaMap;
    }

    @Override
    public List<String> getHouseList(String areaUrl, int timeout) {
        ArrayList<String> houseUrlList = new ArrayList<String>();
        Document doc;
        try {
            doc = Jsoup.connect(areaUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            if (doc.select(".multipage span").size() == 0) {
                return null;
            }
            int totalPage = Integer.parseInt(doc.select(".multipage span").get(doc.select(".multipage span").size() - 2).text());
            int page = 1;

            for (; page <= totalPage; page++) {
                String address = areaUrl + "pg" + page + "/";
                try {
                    doc = Jsoup.connect(address).userAgent(Constant.USER_AGENT).timeout(timeout).get();
                } catch (IOException e) {
                    System.out.println("库房网某个区第" + page + "页读取错误! 地址：" + address);
                    // FileUtils.writeStringToFile(file,"库房网某个区第" + page +
                    // "页读取错误! 地址：" + address + "\n", true);
                    e.printStackTrace();
                    continue;
                }
                // 获取一个区的所有房源的URL
                Elements elements = doc.select(".listing #biaoti_a");
                for (int i = 0; i < elements.size(); i++) {// 获取一页当中房源的URL
                    houseUrlList.add(elements.get(i).absUrl("href"));
                    System.out.println(elements.get(i).absUrl("href"));
                }
            }
        } catch (IOException e) {
            System.out.println("库房网读取某个区主页面出现错误: " + areaUrl);
            e.printStackTrace();
        }
        if(houseUrlList.size() <= 0) return null;
        return houseUrlList;
    }



    @Override
    public HouseSource crawlDescPage(String url, int timeout) {
        System.out.println(url);//用于观察哪个网页出现问题
        
        HouseSource house = new HouseSource();
        house.setHouseUrl(url);
        try {
            Document doc = Jsoup.connect(url).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            
            if (doc.select(".Details_Page_two_l_h").size() == 0) {
                return null;
            }
            String buildingTitleValue = doc.select(".Details_Page_two_l_h").text();
            int low = buildingTitleValue.indexOf("房源编号");
            
            house.setBuildingNum((buildingTitleValue.substring(low + 5, buildingTitleValue.indexOf("更新时间"))).trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            System.out.println("buildingNum: " + buildingTitleValue.substring(low + 5, buildingTitleValue.indexOf("更新时间")));
            
            house.setBuildingTitle(buildingTitleValue.substring(0, low));
            System.out.println("buildingTitle: " + buildingTitleValue.substring(0, low));
            
            String Details_Page_two = doc.select(".Details_Page_two_cent_r_up li").text().trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            
            // 售　　价：935万元（单价85000元/㎡）参考首付：万参考月供（30年）：元户　　型：2室2厅1厨2卫产证面积：110㎡
            
            String area = Details_Page_two.substring(Details_Page_two.indexOf("产证面积")+5, Details_Page_two.length()-1);
            house.setArea(Double.parseDouble(area));
            System.out.println("area: " + Double.parseDouble(area));
            
            String houseType = Details_Page_two.substring(Details_Page_two.indexOf("型：")+2, Details_Page_two.indexOf("产证面积："));
            house.setHouseType(houseType);
            System.out.println("houseType : " + houseType);
            
            String unitPrice = Details_Page_two.substring(Details_Page_two.indexOf("单价")+2, Details_Page_two.indexOf("元/㎡"));
            house.setUnitPrice(Integer.parseInt(unitPrice));
            System.out.println("Unitprice: " + Integer.parseInt(unitPrice));
            
            String totalPrice = Details_Page_two.substring(Details_Page_two.indexOf("价：")+2, Details_Page_two.indexOf("万元"));
            house.setTotalPrice(Double.parseDouble(totalPrice));
            System.out.println(Double.parseDouble(totalPrice));
            
            Elements elements = doc.select(".borde_das span");
            house.setBuildingAge(elements.get(3).text().trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            String orientation = elements.get(7).text().replaceAll("通透", "");
            orientation = orientation.replaceAll("采光", "");
            house.setOrientation(orientation.trim().replaceAll(" ", ""));
            house.setFloor(elements.get(11).text());
            house.setDecoration(elements.get(19).text());
            house.setBuildingName(elements.select("a").text());
            
            house.setCompanyName("酷房网");
            
            List<Review> reviews = new ArrayList<Review>();
            reviews.add(new Review(doc.select(".name_wu").text(), doc.select(".phone_150").text(), "", ""));
            house.setReviews(reviews);
            
        } catch (IOException e) {
            System.out.println("库房网抓取一个具体房源页面出现错误！" + url);
            e.printStackTrace();
            return null;
        }
        if((house.getBuildingName() == null || house.getBuildingName().equals(""))
                && (house.getBuildingTitle() == null || house.getBuildingTitle().equals("")) ) 
            return null;
                
        return house;
    }
    
}
