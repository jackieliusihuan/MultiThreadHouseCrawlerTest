<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "w3.org/TR/html4/strict.dtd">

<html>
<head>
    <meta charset="UTF-8">
    <title>焦点二手房</title>
    <link rel="stylesheet" href="/resources/css/esf-common.css">
    <link rel="stylesheet" href="/resources/css/esf-list.css">
</head>
<body>
    <div id="doc">
        <div id="header">
            <div class="module-header">
                <!--header-->
                <div class="header-wrap clearfix">
                    <div class="header-left">
                        <ul class="channel-nav-list clearfix">
                            <li class="channel-nav-item"><span class="icon-index"></span><a class="channel-link" href="//beijing.focus.cn/" target="_blank">搜狐焦点</a></li>
                            <li class="channel-nav-item"><a class="channel-link" href="//beijing.focus.cn/loupan/" target="_blank">楼盘</a></li>
                            <li class="channel-nav-item"><a class="channel-link" href="//beijing.focus.cn/zixun/" target="_blank">资讯</a></li>
                            <li class="channel-nav-item"><a class="channel-link" href="//bbs.focus.cn/beijing/" target="_blank">论坛</a></li>
                            <li class="channel-nav-item"><a class="channel-link" href="//home.focus.cn" target="_blank">家居</a></li>
                        </ul>
                    </div>
                    <div class="header-right">
                        <a href="//mp.focus.cn/front/index.html#/index" class="btn btn-open-platform" target="_blank">
                            <span class="icon"></span>
                            <span class="txt">焦点开放平台入驻</span>
                        </a>
                        <span class="unlog-wrap" style="display:inline-block">
                            <a href="javascript:;" class="btn btn-open-login">
                                <span class="icon"></span>
                                <span class="txt">登录</span>
                            </a>
                            <span class="divide-line">|</span>
                            <a href="//login.focus.cn/w/reg?ru=https://beijing.focus.cn/" target="_blank" class="btn btn-open-signup">
                                <span class="icon"></span>
                                <span class="txt">注册</span>
                            </a>
                        </span>
                        <span class="loged-wrap" style="display:none">
                            <a href="http://i.focus.cn/myfocus/" target="_blank" class="btn btn-info">
                                <span class="icon"></span>
                                <span class="txt"></span>
                            </a>
                            <span class="divide-line">|</span>
                            <a href="javascript:void(0)" class="btn btn-logout">
                                <span class="icon"></span>
                                <span class="txt">退出</span>
                            </a>
                        </span>
                    </div>
                </div>
            </div>
            <div class="header-search-wrapper">
                <div class="header-search global-clearfix">
                    <div class="header-search-left">
                        <a href="#"><div class="logo"></div></a>
                    </div>
                    <div class="header-search-right">
                        <input type="text" class="search-input" placeholder="请输入小区/商圈">
                        <div class="btn-search">开始找房</div>
                    </div>
                </div>
            </div>
        </div>
        <div id="bd">
            <div class="choose-options-wrapper">
                <div class="filter-line global-clearfix">
                    <span class="filter-title">位置</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-link" href="#">东城</a>
                        <a class="filter-link" href="#">西城</a>
                        <a class="filter-link" href="#">海淀</a>
                        <a class="filter-link" href="#">朝阳</a>
                        <a class="filter-link" href="#">丰台</a>
                        <a class="filter-link" href="#">石景山</a>
                        <a class="filter-link" href="#">通州</a>
                        <a class="filter-link" href="#">昌平</a>
                        <a class="filter-link" href="#">顺义</a>
                        <a class="filter-link" href="#">门头沟</a>
                    </div>
                </div>
                <div style="display: none;" class="filter-more-position global-clearfix">
                    <div class="more-position-item">
                        <span class="more-position-title">A</span>
                        <a href="#" class="more-position-link">安定门</a>
                        <a href="#" class="more-position-link">安贞</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">B</span>
                        <a href="#" class="more-position-link">北京南站</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">C</span>
                        <a href="#" class="more-position-link">朝阳门外</a>
                        <a href="#" class="more-position-link">朝阳门内</a>
                        <a href="#" class="more-position-link">崇文门</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">D</span>
                        <a href="#" class="more-position-link">东单</a>
                        <a href="#" class="more-position-link">东直门</a>
                        <a href="#" class="more-position-link">地安门</a>
                        <a href="#" class="more-position-link">东花市</a>
                        <a href="#" class="more-position-link">东四</a>
                        <a href="#" class="more-position-link">灯市口</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">G</span>
                        <a href="#" class="more-position-link">国展</a>
                        <a href="#" class="more-position-link">广渠门</a>
                        <a href="#" class="more-position-link">工体</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">H</span>
                        <a href="#" class="more-position-link">惠新西街</a>
                        <a href="#" class="more-position-link">和平里</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">J</span>
                        <a href="#" class="more-position-link">劲松</a>
                        <a href="#" class="more-position-link">建国门外</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">L</span>
                        <a href="#" class="more-position-link">六铺炕</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">M</span>
                        <a href="#" class="more-position-link">木樨园</a>
                        <a href="#" class="more-position-link">木樨地</a>
                    </div>
                    <div class="more-position-item">
                        <span class="more-position-title">Q</span>
                        <a href="#" class="more-position-link">前门</a>
                    </div>
                </div>
                <div class="filter-line global-clearfix">
                    <span class="filter-title">总价</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            200万以下
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            200-300万
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            300-400万
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            400-500万
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            500-600万
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            600-700万
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            700-800万
                        </a>
                        <div class="filter-input-wrapper global-clearfix">
                            <input type="text" class="filter-input"> - <input type="text" class="filter-input"> 万
                        </div>
                    </div>
                </div>
                <div class="filter-line global-clearfix">
                    <span class="filter-title">户型</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            一居室
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            二居室
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            三居室
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            四居室
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            五居室
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            五居室以上
                        </a>
                    </div>
                </div>
                <div class="filter-line global-clearfix">
                    <span class="filter-title">面积</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            50平米以下
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            50-70平米
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            70-90平米
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            90-110平米
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            110-140平米
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            140-170平米
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            170-200平米
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            200平米以上
                        </a>
                    </div>
                </div>
                <div class="filter-line global-clearfix">
                    <span class="filter-title">标签</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            满二
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            满五
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            唯一住房
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            不限购
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            地铁房
                        </a>
                    </div>
                </div>
                <div class="filter-line more-option global-clearfix">
                    <span class="filter-title">朝向</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            东
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            西
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            南
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            北
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            南北
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            东西
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            东南
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            东北
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            西南
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            西北
                        </a>
                    </div>
                </div>
                <div class="filter-line more-option global-clearfix">
                    <span class="filter-title">楼层</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            低楼层
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            中楼层
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            高楼层
                        </a>
                    </div>
                </div>
                <div class="filter-line more-option global-clearfix">
                    <span class="filter-title">装修</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            豪装
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            精装
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            中装
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            简装
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            毛坯
                        </a>
                    </div>
                </div>
                <div class="filter-line more-option global-clearfix">
                    <span class="filter-title">建筑年代</span>
                    <div class="filter-line-wrapper">
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            5年以内
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            10年以内
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            15年以内
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            20年以内
                        </a>
                        <a class="filter-checkbox-w global-clearfix" href="#">
                            <div class="filter-checkbox cur"></div>
                            20年以上
                        </a>
                    </div>
                </div>
            </div>
            <div class="choose-options-more">
                <div class="filter-more"><span>更多选项</span></div>
            </div>
            <div id="bd-float" class="global-clearfix">
                <div id="bd-left">
                    <div class="list-sort global-clearfix">
                        <a href="#" class="list-sort-item cur">默认排序</a>
                        <a href="#" class="list-sort-item">房屋总价</a>
                        <a href="#" class="list-sort-item">房屋单价</a>
                        <a href="#" class="list-sort-item">房屋面积</a>
                        <a href="#" class="list-sort-item">最新更新</a>
                        <a href="#" class="list-sort-item">最新发布</a>
                    </div>
                    <div class="search-num">共找到<span class="search-num-red">89547658</span>套北京二手房</div>
                    <div class="list-wrapper">



                        <c:forEach items="${similarHouseSourceList}" var="house" varStatus="status">
                            <div class="list-item global-clearfix">
                                <div class="item-left">
                                    <a href=""><img
                                        src="http://t4.focus-img.cn/sh200x150sh/xf/zxc/cf529bb23b7f90c2e7a87d34fa0f9fa7.jpg"
                                        alt="" class="item-img"></a>
                                    <div class="item-left-info">
                                        <h3>测试数据输出:
                                            ${house.houseSource.houseUrl} ::::: ${house.houseSource.buildingName}  ::::::  ${house.houseSource.buildingNum}
                                        </h3>
                                    
                                    
                                        <h3 class="left-info-title">
                                            <a href="${house.houseSource.houseUrl}">${house.houseSource.buildingTitle}&nbsp;&nbsp;景观房&nbsp;&nbsp;${house.houseSource.decoration}</a>
                                        </h3>
                                        <div class="left-types">
                                            <span>${house.houseSource.buildingName}</span>&nbsp;|&nbsp;<span>房山/长阳(没抓)</span>&nbsp;|&nbsp;<span>${house.houseSource.houseType }</span>&nbsp;|&nbsp;<span>${house.houseSource.area}平方米</span>&nbsp;|&nbsp;<span>${house.houseSource.orientation }</span>&nbsp;|&nbsp;<span>${house.houseSource.buildingAge}</span>
                                        </div>
                                        <div class="left-more">
                                            <span>相似房源<span class="red-num">
                                            <a style="color:red" href="/read/esf-similar?id=${house.houseSource.buildingNum}">${house.size} </a>
                                            </span>套
                                            </span>&nbsp;|&nbsp;中介公司：<span>链家 我爱我家 房天下(没抓)</span>
                                        </div>
                                        <div class="left-update">
                                            <span>更新时间：2016-12-21</span>&nbsp;|&nbsp;最新发布：<span>3天以前</span>
                                        </div>
                                        <div class="left-tags">
                                            <span class="left-tag">满五唯一(没抓)</span> <span class="left-tag">唯一住房</span>
                                            <span class="left-tag">地铁房</span> <span class="left-tag">不限购</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="item-right">
                                    <h3 class="right-price">
                                        350-360<span class="right-unit">万</span>
                                    </h3>
                                    <p class="avg-price">均价29857元/平米</p>
                                </div>
                            </div>
                        </c:forEach>





                    </div>
                </div>
                <div id="bd-right"></div>
            </div>
            <div class="pagenation">
                <a href="/read/esf-list/1" class="page-num cur">1</a>
                <a href="/read/esf-list/2" class="page-num">2</a>
                <a href="/read/esf-list/3" class="page-num">3</a>
                <a href="/read/esf-list/${currentPage+1}" class="page-num">下一页</a>
                <a href="/read/esf-list/${totalPage}" class="page-num">末页</a>
                <span class="totalCount">共${totalPage}页</span>
                                                
                                                第<input type="text" id="page-forward"  value = "">页
               <script>
                  function getNextPage()
                  {
                	  var a = document.getElementById("page-forward").value;
                	  window.location.href="/read/esf-list/" + a;
                  }
               </script>
                <a href="javascript:getNextPage();"  class="page-num">确定</a>
            
            </div>
        </div>
        <div class="footer">
            <div node-type="module" class="module-footer">
                <div class="h-area">
                    <div class="foot-copy">
                        <p><a rel="nofollow" href="mailto:realbjhouse@sohu.com" target="_blank">欢迎来信</a><em>|</em>
                            <a rel="nofollow" href="//house.focus.cn/aboutus/mianzeshengming.html" target="_blank">免责声明</a><em>|</em>
                            <a rel="nofollow" href="//house.focus.cn/aboutus/xinfangguanggao.html" target="_blank">广告服务</a><em>|</em>
                            <a rel="nofollow" href="//house.focus.cn/aboutus/xinfanglianxi.html"
                               target="_blank">联系我们</a><em>|</em>
                            <a rel="nofollow" href="//house.focus.cn/jiameng/juejin.php" target="_blank">加盟合作</a></p>
                        <p>Copyright <span class="fontArial">©</span> 2016 Sohu.com Inc. All Rights Reserved. 搜狐公司 <span
                                class="unline"><a href="//house.focus.cn/aboutus/banquanshengming.html"
                                                  target="_blank">版权所有</a></span></p>
                    </div>
                    <div class="sohu_linka">
                        <p>增值电信业务经营许可证B2-20040144 京ICP证030367号 </p>
                        <p><a rel="nofollow" href="//images.house.focus.cn/img/focus_news_certi.JPG" target="_blank">互联网新闻信息服务许可证</a>
                            <a href="//www.sohu.com/upload/uiue20140718/vz_sohu.html" target="_blank" rel="nofollow">信息网络传播视听节目许可证 </a>
                        </p>
                        <p><a target="_blank" href="//www.sohu.com/upload/uiue20141205/web2014.html" rel="nofollow">网络文化经营许可证
                            京网文【2014】0935-235号 </a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="http://js.sohu.com/library/jquery-1.7.1.min.js"></script>
    <script>
        $('.choose-options-more').on('click',function () {
            var $this = $(this);
            if ($this.hasClass('active')) {
                $this.find('span').text('展开选项');
                $('.more-option').hide();
            } else {
                $this.find('span').text('收起选项');
                $('.more-option').show();
            }
            $this.toggleClass('active');
        });
    </script>
</body>
</html>