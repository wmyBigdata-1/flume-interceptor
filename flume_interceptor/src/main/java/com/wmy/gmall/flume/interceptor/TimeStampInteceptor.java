package com.wmy.gmall.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ClassName:TimeStampInteceptor
 * Package:com.wmy.gmall.flume.interceptor
 *
 * @date:2021/5/22 8:45
 * @author:数仓开发工程师
 * @email:2647716549@qq.com Description:
 */
public class TimeStampInteceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // todo 1、获取数据
        byte[] body = event.getBody();

        // todo 2、设置编码格式
        String log = new String(body, StandardCharsets.UTF_8);

        // todo 3、解析log
        JSONObject jsonObject = JSONObject.parseObject(log);

        if (jsonObject.containsKey("ts")){
            String ts = jsonObject.getString("ts");
            event.getHeaders().put("timestamp",ts);
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        // todo 1、使用增强for循环
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new TimeStampInteceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
