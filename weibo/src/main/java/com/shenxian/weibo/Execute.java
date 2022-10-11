package com.shenxian.weibo;

import com.shenxian.weibo.entity.Hot;
import com.shenxian.weibo.utils.WeiBo;

import java.util.List;

/**
 * @author: shenxian
 * @date: 2022/10/11 10:18
 */
public class Execute {

    public static void main(String[] args) {
        List<Hot> result = WeiBo.execute();
        System.out.println("--------");
        System.out.println(result);
        System.out.println("--------");
    }

}
