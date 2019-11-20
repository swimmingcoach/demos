package cn.zealot.springtransactionaldemo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/11/18 16:21
 */
@Data
public class TestModel {

    private Integer id;
    private String name;
    private String desc;
    private Date createtime;

}
