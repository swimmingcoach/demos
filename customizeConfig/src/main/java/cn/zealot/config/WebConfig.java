package cn.zealot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/10/22 16:08
 */
@Data
@Component
@ConfigurationProperties(prefix = "web.config")
public class WebConfig {
    private String webTitle;
    private String authorName;
    private String authorBlogUrl;
}
