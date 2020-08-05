package com.zealot.mybatisplusdemo.config;

import com.alibaba.fastjson.JSON;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/12/31 9:48
 */
@Component("baseCacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder key = new StringBuilder();
        String className = target.getClass().getSimpleName();
        String methodName = method.getName();

        key.append(className).append(".");
        key.append(methodName);

        if (params.length <= 0) {
            return key.toString();
        }

        key.append("->[");
        for (Object param : params) {
            if (param == null) {
                key.append("NULL");
            } else if (ClassUtils.isPrimitiveArray(param.getClass()) || ClassUtils.isPrimitiveWrapperArray(param.getClass())) {
                for (int i = 0, length = Array.getLength(param); i < length; i++) {
                    key.append(Array.get(param, i));
                    key.append(',');
                }
            } else if(param instanceof List) {
                for (int i = 0, length = ((List<?>) param).size(); i < length; i++) {
                    key.append(((List<?>) param).get(i));
                    key.append(',');
                }
            } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
                key.append(param);
            } else {
                key.append(JSON.toJSONString(param));
            }
            key.append(",");
        }
        key = key.deleteCharAt(key.lastIndexOf(","));
        key.append("]");
        return key.toString();
    }
}
