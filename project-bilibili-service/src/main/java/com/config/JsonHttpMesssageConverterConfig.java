package com.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class JsonHttpMesssageConverterConfig {

    @Bean
    @Primary
    public HttpMessageConverters fastJsonHttpMessageConvertes(){
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //日期格式
        fastJsonConfig.setDateFormat("yyyy-MM--dd HH:mm:ss");

        fastJsonConfig.setSerializerFeatures(
                //json格式化 更加美观
                SerializerFeature.PrettyFormat,
                //将 null 的字符串字段输出为空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                //将 null 的列表字段输出为空列表
                SerializerFeature.WriteNullListAsEmpty,
                //输出值为 null 的字段
                SerializerFeature.WriteMapNullValue,
                //将对象的字段按字母顺序排序
                SerializerFeature.MapSortField,
                //禁用循环引用检测
                SerializerFeature.DisableCircularReferenceDetect

        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastJsonHttpMessageConverter);

    }
}
