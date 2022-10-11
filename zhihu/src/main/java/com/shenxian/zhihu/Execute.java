package com.shenxian.zhihu;

import com.shenxian.zhihu.entity.Hot;
import com.shenxian.zhihu.utils.ZhiHu;

import java.util.List;

/**
 * @author: shenxian
 * @date: 2022/10/10 9:49
 */
public class Execute {

    public static void main(String[] args) {
        List<Hot> hots = ZhiHu.execute();
        System.out.println("--------");
        System.out.println(hots);
        System.out.println("--------");
    }

}
