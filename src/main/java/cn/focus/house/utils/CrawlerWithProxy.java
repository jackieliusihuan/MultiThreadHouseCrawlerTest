package cn.focus.house.utils;

import java.net.ProxySelector;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;


public class CrawlerWithProxy {
    
    // 代理服务器
    final static String ProxyHost = "proxy.abuyun.com";
    final static Integer ProxyPort = 9010;
    final static String ProxyUser = "H09I6P795285K80D";
    final static String ProxyPass = "DB0C81A2B44CE08C";
    final static String ProxyHeadKey = "Proxy-Switch-Ip";
    final static String ProxyHeadVal = "yes";
    
    public static HttpClientBuilder httpClientBuilder = null;
    private static RequestConfig defaultRequestConfig = null;
    
    public static final int MAX_TOTAL_CONNECTIONS = 800;
    public static final int MAX_ROUTE_CONNECTIONS = 200;
    public static int Socket_TIME_OUT = 1000 * 60 ;
    public static int Connect_TIME_OUT = 1000 * 60;
    public static int Request_TIME_OUT = 1000 * 60 ;
    
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36";
    public static final String REG_META = "<meta[^>]*?charset=\"?([a-z|A-Z|0-9]*[\\-]*[0-9]*)\"?[\\s|\\S]*>";
    
    static {
        Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSocketFactory()).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(reg);
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);//连接池最大并发连接数
        cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);// 单路由最大并发数
        httpClientBuilder = HttpClients.custom().setConnectionManager(cm);
        //请求重试,关闭默认。
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(0,false));
        //503重试
        httpClientBuilder.setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy(1, 1000));
        //设置路由策略
        httpClientBuilder.setRoutePlanner(new SystemDefaultRoutePlanner(ProxySelector.getDefault()));
        
        defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(Socket_TIME_OUT)
                .setConnectTimeout(Connect_TIME_OUT)
                .setConnectionRequestTimeout(Request_TIME_OUT)
                .setStaleConnectionCheckEnabled(true).build();
        httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
    }
    
    public static void main(String[] args) throws Exception {
        String url = "http://www.baidu.com/";
        System.out.println(getHtml(url));
    }
    
    public static HttpResponse getResponse(String url) throws Exception {
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(url);
        Map<String, String> headers = new HashMap<String,String>();
        headers.put(ProxyHeadKey, ProxyHeadVal);
        setHeader(httpGet, headers);
        
        CloseableHttpClient httpClient = getAbuyunClient();
        response = httpClient.execute(new HttpHost(ProxyHost, ProxyPort, "http"), httpGet, getAbuyunClientContext());
        return response;
    }
    
    public static String getHtml(String url)  throws Exception  {
        String html = "";
        HttpResponse response = getResponse(url);
        HttpEntity entity = response.getEntity();
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && entity != null) {
            entity = new BufferedHttpEntity(entity);
            String tempHtml = EntityUtils.toString(entity);
            String encode = getCharSet(tempHtml);
            html = EntityUtils.toString(entity, encode);
        }
        return html;
    }
    
    private static void setHeader(HttpMessage httpMessage, Map<String,String> heads){
        httpMessage.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
        httpMessage.setHeader(new BasicHeader("Accept-Language", "zh-cn,zh,en-US,en;q=0.5"));
        httpMessage.setHeader(new BasicHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,GB2312;q=0.7,*;q=0.7"));
        httpMessage.setHeader(new BasicHeader("User-Agent", USER_AGENT));
        if (null == heads) {
            return ;
        }
        for (Entry<String, String> entry : heads.entrySet()) {
            httpMessage.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
        }
    }
    
    private static CloseableHttpClient getAbuyunClient(){
        CloseableHttpClient httpClient = null;
        HttpHost target = new HttpHost(ProxyHost, ProxyPort, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(ProxyUser, ProxyPass));

        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).setDefaultRequestConfig(defaultRequestConfig).build();
        return httpClient;
    }
    
    private static HttpClientContext getAbuyunClientContext(){
        HttpHost target = new HttpHost(ProxyHost, ProxyPort, "http");
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(target, basicAuth);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);
        return localContext;
    }
    
    private static String getCharSet(String content) {
        Matcher m = Pattern.compile(REG_META).matcher(content);
        if (m.find()) {
            String code = m.group(1);
            if ("gb2312".equals(code)) {
                return "GBK";
            }
        }
        return "utf-8";
    }
}
