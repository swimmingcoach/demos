package com.zealot.mybatisplusdemo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2020/4/27 10:40
 */
public enum Gender implements IEnum<Integer> {
    male(1, "男性"),
    female(0, "女性"),
    unknow(-1, "待定"),;

    private Integer value;
    private String desc;

    Gender(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
