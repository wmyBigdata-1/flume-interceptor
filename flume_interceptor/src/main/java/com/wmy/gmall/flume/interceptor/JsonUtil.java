package com.wmy.gmall.flume.interceptor;

import org.mortbay.util.ajax.JSON;

/**
 * ClassName:JsonUtil
 * Package:com.wmy.gmall.flume.interceptor
 *
 * @date:2021/5/22 5:57
 * @author:数仓开发工程师
 * @email:2647716549@qq.com Description:
 */
public class JsonUtil {
    public static boolean validateJSON(String log){
        try {
            JSON.parse(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
