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

@Service
public class WoAiWoJiaCrawlService implements CrawlService {

    @Override
    public Map<String, String> getDistrictMap(String mainUrl, int timeout) {
        Map<String, String> dtMap = new HashMap<String, String>();
        try {
            Document doc = Jsoup.connect(mainUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            Elements elements = doc.select(".search-term-list li").get(0).select("a");
            for(Element element : elements){
                if ("不限".equals(element.html()) || "".equals(element.html()) || element.html()==null) {
                    continue;
                }
                dtMap.put(element.html(), element.absUrl("href"));
                System.out.println(element.html() + element.absUrl("href"));
            }
        } catch (IOException e) {
            System.out.println("读取我爱我家district页面出现错误" + mainUrl);
            e.printStackTrace();
        }
        return dtMap;
    
    
    }
    

    //因为没有页数限制，所以没有必要再把抓取的单位设置得非常小
    @Override
    public Map<String, String> getAreaMap(String districtUrl, int timeout) {
        return null;
    }
    
    
    @Override
    public List<String> getHouseList(String districtUrl, int timeout) {
        ArrayList<String> houseList = new ArrayList<String>();

        // 我爱我家：http://bj.5i5j.com/exchange/n1
        try {
            Document doc = Jsoup.connect(districtUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();

            int page = 1;// 测试用，应该从第一页开始抓
            while (doc == null || doc.select(".no-result").size() == 0) {
                String address = "";
                try {
                    address = districtUrl+"n" + page + "/";
                    doc = Jsoup.connect(address).userAgent(Constant.USER_AGENT)
                            .timeout(2000).get();
                    if (doc.select(".no-result").size() != 0) {
                        break;
                    }
                    if(doc.html().indexOf("下一页") == -1){
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("我爱我家列表页面某一页抓取失败: " + address);
                    e.printStackTrace();
                    doc = null;
                    page++;
                    continue;
                }

                Elements elements = doc.select("div.list-body ul.list-body").select("h2 a");
                
                for(Element element :elements){
                    String singleHouseUrl = element.attr("href");
                  System.out.println("http://bj.5i5j.com" + singleHouseUrl);// 测试用输出
                  houseList.add("http://bj.5i5j.com" + singleHouseUrl);
                }
          
                page++;
                // elements=doc.select("div.list-page a");
                // element=elements.last();
                // url=element.attr("href");
            }
        } catch (Exception e) {
            System.out.println("我爱我家主页面抓取失败: " + districtUrl);
            e.printStackTrace();
        }
        return houseList;
    
    }
    
    @Override
    public HouseSource crawlDescPage(String url, int timeout) {
        System.out.println(url);//用于观察哪个网页出现问题

        HouseSource houseSource = new HouseSource();
        houseSource.setHouseUrl(url);
        houseSource.setCompanyName("我爱我家");

        Document doc;
        
        Elements elements = null;
        Element element = null;
        try {
            doc = Jsoup.connect(url).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            houseSource.setBuildingTitle(doc.select(".house-tit").get(0).html());
            
            elements = doc.select(".house-info > li");
            
            houseSource.setTotalPrice(Double.parseDouble(elements.get(0).select(".font-price").get(0).html()));

            
            String houseType = elements.get(1).select(".house-info-2").get(0).select("li").get(0).html();
            houseSource.setHouseType(houseType.substring(houseType.indexOf("室")-1,houseType.indexOf("室")+5).trim().replaceAll("\\s", "").replaceAll("\u00A0", ""));
                    
                    
            String unitPrice = elements.get(1).select(".house-info-2").get(0).select("li").get(1).html();
            houseSource.setUnitPrice(Integer.parseInt(unitPrice.substring(unitPrice.indexOf("</b>")+4,unitPrice.indexOf("元"))));

            String area = elements.get(1).select(".house-info-2").get(0).select("li").get(2).html();
            houseSource.setArea(Double.parseDouble(area.substring(area.indexOf("</b>")+4,area.indexOf("平"))));

            String buildingAge = elements.get(1).select(".house-info-2").get(0).select("li").get(3).html();
            houseSource.setBuildingAge(buildingAge.substring(buildingAge.indexOf("</b>")+4));

            String orientation = elements.get(1).select(".house-info-2").get(0).select("li").get(4).html();
            houseSource.setOrientation(orientation.substring(orientation.indexOf("</b>")+4));
           
            String floor = elements.get(1).select(".house-info-2").get(0).select("li").get(5).html();
            houseSource.setFloor(floor.substring(floor.indexOf("</b>")+4));
            
            String buildingNameValue = elements.get(2).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            houseSource.setBuildingName(buildingNameValue.substring(buildingNameValue.indexOf("</b>")+4,buildingNameValue.indexOf("<span>")));
           
            String buildingNumValue = doc.select(".house-code span").get(0).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            buildingNumValue = buildingNumValue.substring(buildingNumValue.indexOf("查询编号") + 5);
            houseSource.setBuildingNum(buildingNumValue);

            String address = "http://bj.5i5j.com/exchange/brokerDetail/id/" + buildingNumValue;
            List<Review> reviews = new ArrayList<Review>();
            
            try {
                doc = Jsoup.connect(address).userAgent(Constant.USER_AGENT).timeout(timeout).get();
                elements = doc.select(".detail-intro dl");

                for (int i = 0; i < elements.size() && i < 3; i += 2) {
                    Review review = new Review();
                    element = elements.get(i);

                    String agentNameValue = element.select("p").get(0).html();
                    agentNameValue = agentNameValue.replaceAll("<b>置业顾问：</b>", "");
                    review.setAgentName(agentNameValue);

                    review.setAgentPhone(element.select(".broker-tel2 strong").html());

                    String reviewContent = element.select("dd").get(1).select("p").html();
                    String reviewDate = element.select("dd").get(1).select("p").last().html();
                    reviewContent = reviewContent.replaceAll(reviewDate, "");
                    reviewDate = reviewDate.substring(0, reviewDate.indexOf(" "));
                    review.setReview(reviewContent);
                    review.setDate(reviewDate);

                    reviews.add(review);
                }

            } catch (Exception e) {
                System.out.println("我爱我家评论抓取失败： " + address);
               // FileUtils.writeStringToFile(file, "我爱我家评论抓取失败： " + address + "\n", true);
                e.printStackTrace();
            }

            houseSource.setReviews(reviews);
        } catch (IOException e1) {
            System.out.println("我爱我家页面详情抓取失败：" + url);
            e1.printStackTrace();
            return null;
        }
        return houseSource;
    
    }
    
}
