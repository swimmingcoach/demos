package cn.zealot.controller;

import cn.zealot.config.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/10/22 16:16
 */
@Controller
@Slf4j
public class IndexCtrl {
    @Autowired
    private WebConfig webConfig;

    @RequestMapping("/")
    public String index(ModelMap map) {
        log.debug("IN");
        map.addAttribute("title", webConfig.getWebTitle());
        map.addAttribute("name", webConfig.getAuthorName());
        map.addAttribute("blog", webConfig.getAuthorBlogUrl());
        return "index";
    }
}
