package com.shenxian.weibo.utils;

import com.shenxian.weibo.entity.Hot;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenxian
 * @date: 2022/10/11 10:19
 */
public class WeiBo {

    public static final String WEI_BO_URL = "https://s.weibo.com/top/summary?cate=realtimehot";

    public static List<Hot> execute() {
        List<Hot> result = new ArrayList<>();
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(WEI_BO_URL);
            httpGet.setHeader("cookie", "SUB=_2AkMVL_DCf8NxqwJRmfoWy23qboh1ywnEieKjcwEZJRMxHRl-yT9jqhBbtRB6Pq_eLaglTHCEg6PYoS42ix__DZ9OyyQF; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9WF2UVa1XdocCz-y4_nY_iFd; SINAGLOBAL=6035045507999.928.1651736574285; UOR=,,www.baidu.com; _s_tentry=www.baidu.com; Apache=7817465278164.227.1665454616860; ULV=1665454616866:3:2:2:7817465278164.227.1665454616860:1665365424881");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String html = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                Document document = Jsoup.parse(html);
                Element realtimehot = document.getElementById("pl_top_realtimehot");
                Elements elements = realtimehot.select("table tbody tr");
                for (Element element : elements) {
                    // 排名
                    String index = element.selectFirst(".td-01").text();

                    Element contentEle = element.selectFirst(".td-02");
                    // 标题
                    String title = contentEle.selectFirst("a").text();
                    // 热度
                    Element metricsEle = contentEle.selectFirst("span");
                    String metrics = metricsEle != null ? metricsEle.text() : null;

                    // tag
                    Element tagEle = element.selectFirst(".td-03").selectFirst(".icon-txt");
                    String tag = tagEle != null ? tagEle.text() : null;
                    Hot hot = new Hot(index, title, metrics, tag);
                    result.add(hot);
                    System.out.println(hot);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private WeiBo() {}

}
