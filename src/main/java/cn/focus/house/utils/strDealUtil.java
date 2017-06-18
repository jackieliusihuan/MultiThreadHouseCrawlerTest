package cn.focus.house.utils;

public class strDealUtil {
    public String toPriceDeal(String totalPrice) {
        totalPrice = totalPrice.trim().replaceAll(" ", "");
        if (totalPrice != null) {
            if (totalPrice.indexOf("亿") > 0) {
                int i = Integer.parseInt(totalPrice.substring(0, totalPrice.indexOf("亿")).trim());
                return (i * 10000) + "";
            }
            else if (totalPrice.indexOf("万") > 0) {
                return totalPrice.substring(0, totalPrice.indexOf("万")).trim();
            }
        }
        return totalPrice;
    }
    
    public String uniPriceDeal(String unitPrice) {
        unitPrice = unitPrice.trim().replaceAll(" ", "");
        if (unitPrice != null && unitPrice.indexOf("元") > 0) {
            return unitPrice.substring(0, unitPrice.indexOf("元")).trim();
        }
        return unitPrice;
    }
    
    public String areaDeal(String area) {
        area = area.trim().replaceAll(" ", "");
        if (area != null) {
            if (area.indexOf("平") > 0) {
                return area.substring(0, area.indexOf("平")).trim();
            }
            else if(area.length() > 1) {
                return area.substring(0, area.length() - 1).trim();
            }
        }
        return area;
    }
}
