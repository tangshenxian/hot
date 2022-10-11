package com.shenxian.baidu;

import com.shenxian.baidu.entity.Hot;
import com.shenxian.baidu.utils.BaiDu;

import java.util.List;

/**
 * @author: shenxian
 * @date: 2022/10/11 9:42
 */
public class Execute {

    public static void main(String[] args) {
        List<Hot> result = BaiDu.execute();
        System.out.println("--------");
        System.out.println(result);
        System.out.println("--------");
    }

}
