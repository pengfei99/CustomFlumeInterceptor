package org.pengfei.flume.interceptors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class JsonUtils {
    public static boolean isValidate(String log) {
        try {
            JSON.parse(log);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        boolean res1 = JsonUtils.isValidate("{\"id\": \"pengfei\"}");
        boolean res2 = JsonUtils.isValidate("{idfddf}");
        System.out.println("res1: " + res1 + " res2: " + res2);
    }
}


