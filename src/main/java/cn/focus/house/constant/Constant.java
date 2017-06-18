package cn.focus.house.constant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

public class Constant {
    public final static String LIANJIA_HOMEPAGE = "http://bj.lianjia.com/ershoufang/";
    public final static String KUFANG_HOMEPAGE = "http://beijing.koofang.com/sale/";
    public final static String FANGTIANXIA_HOMEPAGE = "http://esf.fang.com/house/a21/";
    public final static String WOAIWOJIA_HOMEPAGE = "http://bj.5i5j.com/exchange/n1";
    public final static String MAITIAN_HOMEPAGE = "http://bj.maitian.cn/esfall";
    
    //用于存储读取失败的List
    public static List<String> lianjiadistrictList = new ArrayList<String>();
    public static List<String> lianjiaareaList = new ArrayList<String>();
    public static List<String> lianjiahouseList = new ArrayList<String>();
    
    
    public static String[] origin_database={
            "lianjia_origin_data",
            "fangtianxia_origin_data",
            "kufang_origin_data",
            "maitian_origin_data",
            "woaiwojia_origin_data"
    };
    
    public static final String similarHouseDB = "similar_house_data";
    //没必要
  /*  public static String chinesesConvertFunction(String string){
        if(string.equals("一")) return Integer.toString(1);
        if(string.equals("二")) return Integer.toString(2);
        if(string.equals("三")) return Integer.toString(3);
        if(string.equals("四")) return Integer.toString(4);
        if(string.equals("五")) return Integer.toString(5);
        if(string.equals("六")) return Integer.toString(6);
        return string;
        
    }*/
    
    
    
    
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36";
    
    
    public final static String[] comList = {"lianjia", "woaiwojia","maitian", "kufang", "fangtianxia"};


    public static Map<String, String> companyMap = new HashMap<String, String>();
    static{
        companyMap.put("链家", "lianjia");
        companyMap.put("酷房网", "kufang");
        companyMap.put("房天下", "fangtianxia");
        companyMap.put("我爱我家", "woaiwojia");
        companyMap.put("麦田在线", "maitian");
    }
    
    public final static Map<Integer, String> districtMap = new HashMap<Integer, String>();
    static {
        districtMap.put(110101, "东城");
        districtMap.put(110102, "西城");
        districtMap.put(110108, "海淀");
        districtMap.put(110105, "朝阳");
        districtMap.put(110106, "丰台");
        districtMap.put(110101, "石景山");
        districtMap.put(110112, "通州");
        districtMap.put(110114, "昌平");
        districtMap.put(110113, "顺义");
        districtMap.put(110109, "门头沟");
        districtMap.put(110111, "房山");
        districtMap.put(110115, "大兴");
        districtMap.put(110228, "密云");
        districtMap.put(110116, "怀柔");
        districtMap.put(110229, "延庆");
        districtMap.put(110117, "平谷");
        districtMap.put(110000, "北京周边");
    }
    public static String[] orientations = {"", "东", "西", "南", "北", "东西", "东南", "东北", "西南", "西北", "南北"};
    
    public static String[] tags = {"", "满二", "满五", "唯一", "不限购", "地铁"};
     
    public enum WebSite {
        lianjia("链家", "lianjia", "http://bj.lianjia.com/ershoufang/"),
        kufang("酷房网", "kufang", "http://beijing.koofang.com/sale/"),
        fangtianxia("房天下", "fangtianxia", "http://esf.fang.com/house/a21/"),
        woaiwojia("我爱我家", "woaiwojia", "http://bj.5i5j.com/exchange"),
        maitian("麦田在线", "maitian", "http://bj.maitian.cn/esfall");
        
        private String name;
        private String host;
        private String homepage;

        private WebSite(String name, String host, String homepage) {
            this.name = name;
            this.host = host;
            this.homepage = homepage;
        }
    
        public String getName() {
            return name;
        }
        public String getHost() {
            return host;
        }
        public String getHomepage() {
            return homepage;
        }
        public static WebSite getByName(String name) {
            for (WebSite site : values()) {
                if (site.name.equals(name)) {
                    return site;
                }
            }
            return lianjia;
        }
        public static WebSite getByHost(String host) {
            for (WebSite site : values()) {
                if (site.host.equals(host)) {
                    return site;
                }
            }
            return lianjia;
        }
    }
    
    public static Map<String, String> disName = new HashMap<String, String>();
    static{
        disName.put("dongcheng", "东城");
        disName.put("xicheng", "西城");
        disName.put("haidian", "海淀");
        disName.put("chaoyang", "朝阳");
        disName.put("fengtai", "丰台");
        disName.put("shijingshan", "石景山");
        disName.put("tongzhou", "通州");
        disName.put("changping", "昌平");
        disName.put("shunyi", "顺义");
        disName.put("mentougou", "门头沟");
        disName.put("fangshan", "房山");
        disName.put("daxing", "大兴");
        disName.put("miyun", "密云");
        disName.put("huairou", "怀柔");
        disName.put("yanqing", "延庆");
        disName.put("pinggu", "平谷");
        disName.put("zhoubian", "北京周边");
    }
}
