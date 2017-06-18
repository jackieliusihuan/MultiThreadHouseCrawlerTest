package cn.focus.house.service.crawl;
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

//这一页的错误处理还有问题需要进一步处理

@Service
public class MaiTianCrawlService implements CrawlService {
    
    @Override
    public Map<String, String> getDistrictMap(String mainUrl, int timeout) {
        Map<String, String> dtMap = new HashMap<String, String>();
        try {
            Document doc = Jsoup.connect(mainUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            
            Elements elements = doc.select(".condition .filter_options ").get(0).select("ul > li");
            for(Element element : elements){
                if ("".equals(element.html()) || element.html()==null) {
                    continue;
                }
                dtMap.put(element.select("a").get(0).html(), element.select("a").get(0).absUrl("href"));
                System.out.println(element.select("a").get(0).html()+ element.select("a").get(0).absUrl("href"));
            }
        } catch (Exception e) {
            System.out.println("读取麦田district页面出现错误" + mainUrl);
            e.printStackTrace();
        }
        return dtMap;
    }
    
    
    @Override
    public Map<String, String> getAreaMap(String districtUrl, int timeout) {
        return null;
    }
    
    @Override
    public List<String> getHouseList(String districtUrl, int timeout) {
        // 麦田东城 ；http://bj.maitian.cn/esfall/R2/PG1

        ArrayList<String> urlList = new ArrayList<String>();
        Elements elements = null;
        Element element = null;
        try {
            Document doc = Jsoup.connect(districtUrl).userAgent(Constant.USER_AGENT).timeout(timeout).get();
            int totalPage = 0;
            element = doc.select(".paging a.down_page").last();
            totalPage = Integer.parseInt(element.attr("href").substring(element.attr("href").indexOf("PG") + 2));
            
            // i=1应该，350用于测试
            for (int i = 1; i <= totalPage; i++) {
                String address = districtUrl + "/" + "PG" + i;
                try {
                    doc = Jsoup.connect(address).userAgent(Constant.USER_AGENT).timeout(2000).get();
                } catch (Exception e) {

                    System.out.println("麦田房产的列表页抓取出现错误： " + address);
                    /*try {
                        FileUtils.writeStringToFile(file, "麦田房产的列表页抓取出现错误： " + address + "\n", true);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }*/
                    e.printStackTrace();
                    continue;
                }

                elements = doc.select("div.list_wrap li.clearfix");
                for (int j = 0; j < elements.size(); j++) {
                    element = elements.get(j);
                    System.out.println("http://bj.maitian.cn" + element.select(".list_title h1 a").attr("href"));// 测试用打印日志
                    urlList.add("http://bj.maitian.cn" + element.select(".list_title h1 a").attr("href"));
                }
            }
        } catch (Exception e) {
            System.out.println("麦田房产的主页抓取出现错误： " + districtUrl);
            /*try {
                FileUtils.writeStringToFile(file, "麦田房产的主页抓取出现错误： " + url + "\n", true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
            e.printStackTrace();

        }
        return urlList;
    }
    

    @Override
    public HouseSource crawlDescPage(String url, int timeout) {
  System.out.println(url);//用于观察哪个网页出现问题
        
        HouseSource houseSource = new HouseSource();
        houseSource.setHouseUrl(url);
        Elements elements;
        Element element;
        try {
            Document doc = Jsoup.connect(url).userAgent(Constant.USER_AGENT).timeout(timeout).get();

             elements = doc.select(".clearfix");
            houseSource.setBuildingTitle(elements.select("samp").get(1).text().replaceAll(" 关注", ""));
            String buildingTagsValue = "";
            elements = elements.select("mark");
            for (int i = 0; i < elements.size(); i++) {
                buildingTagsValue += elements.get(i).text() + ";";
            }
            houseSource.setBuildingTags(buildingTagsValue);

            elements = doc.select("tbody tr");
            houseSource.setTotalPrice(Double.parseDouble(doc.select("strong > span").html()));
            
           
            String unitPrice = doc.select(".home_content").select(".hc_left > table > tbody > tr").get(1).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            houseSource.setUnitPrice(Integer.parseInt(unitPrice.substring(unitPrice.indexOf("</span>")+7, unitPrice.indexOf("元"))));
            
            String area = doc.select(".home_content").select(".hc_left > table > tbody > tr").get(4).select("td").get(0).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            houseSource.setArea(Double.parseDouble(area.substring(area.indexOf("</span>")+7, area.indexOf("㎡"))));

            String houseType =  doc.select(".home_content").select(".hc_left > table > tbody > tr").get(4).select("td").get(1).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            houseSource.setHouseType(houseType.substring(houseType.indexOf("</span>")+7));
          
            
            
            String orientation = doc.select(".home_content").select(".hc_left > table > tbody > tr").get(5).select("td").get(0).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            houseSource.setOrientation(orientation.substring(orientation.indexOf("</span>")+7));
            
            String floor = doc.select(".home_content").select(".hc_left > table > tbody > tr").get(5).select("td").get(1).html().trim().replaceAll("\\s", "").replaceAll("\u00A0", "");
            houseSource.setFloor(floor.substring(floor.indexOf("</span>")+7));
            
            houseSource.setBuildingName(doc.select("tbody em span").text());
            String buildingNumValue = doc.select(".hc_right p").html();
            buildingNumValue = buildingNumValue.substring(buildingNumValue.indexOf("房") + 5,
                    buildingNumValue.indexOf('&'));         //这种抓法太蠢了
            houseSource.setBuildingNum(buildingNumValue);
            houseSource.setCompanyName("麦田在线");

            List<Review> reviews = new ArrayList<Review>();
            elements = doc.select(".conWrap .con");
            for (int i = 0; i < elements.size() && i < 3; i++) {
                Elements tmp = elements.get(i).select(".agent_speak");
                for (int j = 0; j < tmp.size(); j++) {
                    String agentNameValue = tmp.select("dl").get(j).text();
                    String agentPhoneValue = tmp.select("li kbd").get(j).text();
                    String reviewContent = tmp.select("li span").get(j).text() + "。";
                    reviewContent += tmp.select(".font14").get(1 + j * 2).text();
                    reviewContent = reviewContent.replaceAll(tmp.select(".genju").get(j).text(), "");
                    String reviewDate = tmp.select(".genju").get(j).html();
                    reviewDate = reviewDate.substring(0, reviewDate.indexOf("\n"));
                    reviews.add(new Review(agentNameValue, agentPhoneValue, reviewContent, reviewDate));
                }
            }
            houseSource.setReviews(reviews);
        } catch (Exception e) {

            System.out.println("麦田房产的抓取详情页出现错误： " + url);
            /*try {
                FileUtils.writeStringToFile(file, "麦田房产的抓取详情页出现错误： " + url + "\n", true);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }*/
            e.printStackTrace();
            return null;
        } 
        
        
        return houseSource;
    }


}
