package com.shenxian.zhihu.utils;

import com.shenxian.zhihu.entity.Hot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenxian
 * @date: 2022/10/10 9:49
 */
public class ZhiHu {

    public static final String ZHI_HU_URL = "https://www.zhihu.com/billboard";

    public static List<Hot> execute() {
        List<Hot> hots = new ArrayList<>();
        try {
            Document document = Jsoup.connect(ZHI_HU_URL).get();
            Elements list = document.select(".Card .HotList-item");
            for (Element element : list) {
                // 排名
                Element indexEle = element.selectFirst(".HotList-itemPre .HotList-itemIndex");
                String index = indexEle.text();

                Element contentEle = element.selectFirst(".HotList-itemBody");
                // 标题
                String title = contentEle.selectFirst(".HotList-itemTitle").text();
                // 摘录
                Element excerptEle = contentEle.selectFirst(".HotList-itemExcerpt");
                String excerpt = excerptEle != null ? excerptEle.text() : null;
                // 热度
                String metrics = contentEle.selectFirst(".HotList-itemMetrics").text();

                Element imageEle = element.selectFirst(".HotList-itemImgContainer img");
                String image = imageEle != null ? imageEle.attr("src") : null;

                Hot hot = new Hot(index, title, excerpt, metrics, image);
                System.out.println(hot);
                hots.add(hot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hots;
    }

    private ZhiHu() {}

}
