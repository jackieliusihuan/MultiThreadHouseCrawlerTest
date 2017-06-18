package cn.focus.house.service.crawl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import cn.focus.house.constant.Constant;
import cn.focus.house.model.HouseSource;
import cn.focus.house.model.Review;

@Service
public class FangTianXiaCrawlService implements CrawlService {
    
//    private static final Logger LOGGER = LoggerFactory.getLogger(FangtianxiaCrawlService.class);
    File file = new File("d:/tmp.txt");
    // FileUtils.writeStringToFile(file, address + "\n", true);

    // 注意房天下抓取的是自营房屋
    // 由于一页只能显示100套房，所以按照分区来抓取
    public ArrayList<String> getDistrict(String url, int timeout) {
        // http://esf.fang.com/house/a21/
        ArrayList<String> dtList = new ArrayList<String>();
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            for (int i = 1; i < doc.select(".qxName a").size(); i++) {
                // 获取所有的区的URL
                String dtUrl = doc.select(".qxName a").get(i).absUrl("href");
                if (dtUrl != "" && dtUrl != null) {
                    dtList.add(dtUrl);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("读取房天下的主页面失败： " + url);
            try {
                FileUtils.writeStringToFile(file, "读取房天下的主页面失败： " + url + "\n", true);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return dtList;
    }

    public ArrayList<String> crawlMainPage(String url, int timeout) {
        ArrayList<String> houseList = new ArrayList<String>();
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            if (doc.select(".fanye .txt").size() == 0) {
                return houseList;
            }

            int totalPage = Integer.parseInt(doc.select(".fanye .txt").text().replaceAll("共", "").replaceAll("页", ""));
            int page = 1;

            for (; page <= totalPage; page++) {
                // 下一页地址
                String address = url.substring(0, url.length() - 1) + "-i3" + page + "/";
                try {
                    doc = Jsoup.connect(address).userAgent(Constant.USER_AGENT).timeout(timeout).get();
                } catch (IOException e) {
                    System.out.println("房天下列表页读取错误： " + address);
                    //FileUtils.writeStringToFile(file, "房天下列表页读取错误： " + address + "\n", true);
                    e.printStackTrace();
                    continue;
                }

                // 获取一个区的所有房源的URL
                for (int j = 0; j < doc.select(".list").size(); j++) {
                    // 获取一页当中的30条房源URL
                    String houseUrl = doc.select(".list").get(j).select("a").get(0).absUrl("href");
//                    System.out.println(houseUrl);// 测试用输出
                    houseList.add(houseUrl);
                }

            }

        } catch (IOException e) {
            System.out.println("房天下抓取主页页出现错误： " + url);
            try {
                FileUtils.writeStringToFile(file, "房天下抓取主页页出现错误： " + url + "\n", true);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return houseList;
    }



    @Override
    public Map<String, String> getDistrictMap(String mainUrl, int timeout) {
        Map<String, String> dtMap = new HashMap<String, String>();
        try {
            Document doc = Jsoup.connect(mainUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            for (Element href : doc.select(".qxName a")) {
                if ("不限".equals(href.html())) {
                    continue;
                }
                dtMap.put(href.html(), href.absUrl("href"));
            }
        } catch (IOException e) {
            System.out.println("读取房天下的district主页面出现问题： " + mainUrl);
            e.printStackTrace();
        }
        return dtMap;
    }

    @Override
    public Map<String, String> getAreaMap(String districtUrl, int timeout) {
        Map<String, String> areaMap = new HashMap<String, String>();
        try {
            Elements hrefs = Jsoup.connect(districtUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get().select("#shangQuancontain a");
            for (Element href : hrefs) {
                if ("不限".equals(href.html())) {
                    continue;
                }
                areaMap.put(href.html(), href.absUrl("href"));
            }
        } catch (IOException e) {
            System.out.println("读取房天下的area主页面出现问题： " + districtUrl);
            e.printStackTrace();
        }
        return areaMap;
    }
    
    @Override
    public List<String> getHouseList(String areaUrl, int timeout) {
        ArrayList<String> houseList = new ArrayList<String>();
        Document doc;
        try {
            doc = Jsoup.connect(areaUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            if (doc.select(".fanye .txt").size() == 0) {
                return houseList;
            }

            int totalPage = Integer.parseInt(doc.select(".fanye .txt").text().replaceAll("共", "").replaceAll("页", ""));
            int page = 1;

            for(;page <= totalPage;page++ ){
                // 下一页地址
                String address = areaUrl.substring(0, areaUrl.length() - 1) + "-i3" + page + "/";
                try {
                    doc = Jsoup.connect(address).userAgent(Constant.USER_AGENT).timeout(timeout).get();
                } catch (IOException e) {
                    System.out.println("读取房天下具体某一页出现错误：  " + address);
                    e.printStackTrace();
                    continue;
                }

                // 获取一个区的所有房源的URL
                for (int j = 0; j < doc.select(".list").size(); j++) {
                    // 获取一页当中的30条房源URL
                    String houseUrl = doc.select(".list").get(j).select("a").get(0).absUrl("href");
                    System.out.println(houseUrl);//测试用输出
                    houseList.add(houseUrl);
                }

            }

        } catch (IOException e) {
//            System.out.println("房天下抓取主页页出现错误： " + url);
//            try {
//                FileUtils.writeStringToFile(file, "房天下抓取主页页出现错误： " + url + "\n", true);
//            } catch (IOException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
        }
        return houseList;
    }
    
    @Override
    public HouseSource crawlDescPage(String houseUrl, int timeout) {
        System.out.println(houseUrl);//用于观察哪个网页出现问题
        
        HouseSource house = new HouseSource();
        house.setHouseUrl(houseUrl);
        try {
            Document doc = Jsoup.connect(houseUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();

            house.setBuildingTitle(doc.select(".title h1").get(0).html());
            
            Elements elements;
            if (doc.select(".title p span").size() > 0) {
                elements = doc.select(".title p span");
                int eleSize = elements.size();
                String buildingTags = "";
                for (int i = 0; i < eleSize - 2; i++) {
                    buildingTags = buildingTags + elements.get(i).html() + ";";
                }
                house.setBuildingTags(buildingTags);
                

                String buildingNum = elements.get(eleSize - 2).html();
                buildingNum = buildingNum.substring(buildingNum.indexOf("号") + 2);
                house.setBuildingNum(buildingNum.trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
            }

            elements = doc.select(".inforTxt dl dt");
            String totalPriceValue = elements.get(0).select("span").html();
            String unitPriceValue = elements.get(0).html();
            int toolLoc = unitPriceValue.indexOf("n>") + 2;
            house.setTotalPrice(Double.parseDouble(totalPriceValue));
            
            unitPriceValue = unitPriceValue.substring(toolLoc + 2, unitPriceValue.indexOf("元"));//单纯的数字
            house.setUnitPrice(Integer.parseInt(unitPriceValue));
            
            house.setBuildingName(elements.get(1).select("a").get(0).select("a").html());

            String str = doc.select(".inforTxt dl dd").html();
            if (str.indexOf("户型") > 0) {
                if(str.indexOf("卫") == -1){
                    house.setHouseType(str.substring(str.indexOf("户型") + 3, str.indexOf("厅") + 1));
                }else{
                    house.setHouseType(str.substring(str.indexOf("户型") + 3, str.indexOf("卫") + 1));
                }
            }
            if (str.indexOf("建筑") > 0) {
                String tmp = str.substring(str.indexOf("建筑") + 5, str.indexOf("&")).trim().replaceAll(" ", "");
                house.setArea(Double.parseDouble(tmp));//单纯的数字
            }
            if (str.indexOf("楼层") > 0) {
                house.setFloor(str.substring(str.indexOf("楼层") + 3, str.indexOf("共") + 5));
            }
            if (str.indexOf("朝向") > 0) {
                if (str.indexOf("朝向") + 5 > str.length()) {
                    house.setOrientation(str.substring(str.indexOf("朝向") + 3, str.length()).trim().replaceAll(" ", ""));
                }
                else {
                    house.setOrientation(str.substring(str.indexOf("朝向") + 3, str.indexOf("朝向") + 5).trim().replaceAll(" ", ""));
                }
            }
            if (str.indexOf("年代") > 0) {
                house.setBuildingAge(str.substring(str.indexOf("年代") + 3, str.indexOf("年代") + 7).trim().replaceAll(" ", ""));
            }
            if (str.indexOf("装修") > 0) {
                house.setDecoration(str.substring(str.indexOf("装修") + 3, str.indexOf("装修") + 5));
            }

            house.setCompanyName("房天下");

            List<Review> reviews = new ArrayList<Review>();
            String address = null;
            try {
                address = "http://esf.fang.com/EsfHouse/Detail/Eb_AgentPingJia.aspx?houseid=" + house.getBuildingNum()
                        + "&pageindex=";
                doc = Jsoup.connect(address + 1).userAgent(Constant.USER_AGENT).timeout(timeout).get();

                elements = doc.select(".agent-info .list");

                // 不再翻页，只抓取首页上的评论
                for (int i = 0; i < elements.size() && i < 3; i++) {
                    Review aReview = new Review();
                    Element element = elements.get(i);
                    if (element.select(".ppInfor #Span3").size() == 0) {
                        continue;
                    }
                    aReview.setAgentName(element.select(".ppInfor #Span3").html());
                    aReview.setAgentPhone(element.select(".tel strong").get(0).html());
                    String reviewContent = element.select(".cmtC").html();
                    reviewContent = reviewContent.replaceAll("<br>", "");
                    reviewContent = reviewContent.replaceAll("<br />", "");
                    aReview.setReview(reviewContent);
                    String reviewDate = element.select(".gray9").html();
                    reviewDate = reviewDate.substring(reviewDate.indexOf("n>") + 2);
                    reviewDate = reviewDate.substring(0, reviewDate.indexOf(" "));
                    aReview.setDate(reviewDate);
                    reviews.add(aReview);
                }

                house.setReviews(reviews);
            } catch (IOException e) {
                System.out.println("房天下评论抓取错误： " + address + "\n");
               // FileUtils.writeStringToFile(file, "房天下评论抓取错误： " + address + "\n", true);
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("房天下页面详情抓取错误： " + houseUrl + "\n");
            e.printStackTrace();
            return null;
        }
        return house;
    }
    
}
