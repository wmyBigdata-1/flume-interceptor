package com.wmy.gmall.flume.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName:LogInterceptor
 * Package:com.wmy.gmall.flume.interceptor
 *
 * @date:2021/5/22 5:53
 * @author:数仓开发工程师
 * @email:2647716549@qq.com Description:
 */
public class LogInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // todo 1、校验数据
        byte[] body = event.getBody();

        // todo 2、转换数据集
        String log = new String(body, StandardCharsets.UTF_8);

        // todo 3、解析对象
        // 如果不是就会报错，写一个工具类
        if (JsonUtil.validateJSON(log)){
            return event;
        }else {
            // 过滤掉
            return null;
        }

    }

    @Override
    public List<Event> intercept(List<Event> list) {
        // for (Event event : list) {
        //     // todo 1、循环的过程不能去除对象
        // }
        Iterator<Event> eventIterator = list.iterator();
        while (eventIterator.hasNext()){
            Event next = eventIterator.next();
            if (intercept(next) == null){
                for (Event event : list) {
                    eventIterator.remove();
                }
            }
        }
        return list;
    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            // todo 1、new 一个拦截器对象
            return new LogInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

    @Override
    public void close() {

    }
}
