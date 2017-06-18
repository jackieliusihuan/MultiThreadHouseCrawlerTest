package cn.focus.house.service.crawl;

import java.util.List;
import java.util.Map;

import cn.focus.house.model.HouseSource;

public interface CrawlService {

    public Map<String, String> getDistrictMap(String mainUrl, int timeout);
    public Map<String, String> getAreaMap(String districtUrl, int timeout);
    public List<String> getHouseList(String areaUrl, int timeout);
    public HouseSource crawlDescPage(String houseUrl, int timeout);

}
