package com.shenxian.weibo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: shenxian
 * @date: 2022/10/10 10:18
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hot {

    private String index;
    private String title;
    private String metrics;
    private String tag;

}
