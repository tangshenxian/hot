package com.shenxian.baidu.utils;

import com.shenxian.baidu.entity.Hot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenxian
 * @date: 2022/10/11 9:43
 */
public class BaiDu {

    private static final String BAI_DU_URL = "https://top.baidu.com/board?tab=realtime&sa=fyb_realtime_31065";

    public static List<Hot> execute() {
        List<Hot> result = new ArrayList<>();
        try {
            Document document = Jsoup.connect(BAI_DU_URL).get();
            Elements elements = document.select(".category-wrap_iQLoo.horizontal_1eKyQ");
            for (Element element : elements) {
                Element indexEle = element.selectFirst(".img-wrapper_29V76");
                // 排名
                String index = indexEle.selectFirst(".index_1Ew5p").text();
                // 图片
                String image = indexEle.selectFirst("img").attr("src");

                Element metricsEle = element.selectFirst(".trend_2RttY.hide-icon");
                String number = metricsEle.selectFirst(".hot-index_1Bl1a").text();
                String text = metricsEle.selectFirst(".text_1lUwZ").text();
                // 热度
                String metrics = number + text;

                Element contentEle = element.selectFirst(".content_1YWBm");
                // 标题
                String title = contentEle.selectFirst("a .c-single-text-ellipsis").text();
                // 摘录
                Element excerptEle = contentEle.selectFirst(".hot-desc_1m_jR.large_nSuFU");
                String excerpt = excerptEle != null ? excerptEle.childNode(0).outerHtml().replace("\n", "") : null;

                Hot hot = new Hot(index, title, excerpt, metrics, image);
                result.add(hot);
                System.out.println(hot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private BaiDu() {
    }

}
