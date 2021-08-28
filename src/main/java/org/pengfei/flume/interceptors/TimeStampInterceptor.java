package org.pengfei.flume.interceptors;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class TimeStampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // get the event header key
        Map<String, String> headers = event.getHeaders();
        // get the timestamp inside the event body (json body has a field called ts)
        byte[] body = event.getBody();
        // convert byte to string by using utf-8 encoding
        String log=new String(body, StandardCharsets.UTF_8);
        // convert string to json object
        JSONObject jObj = JSONObject.parseObject(log);
        // get the ts value
        String timestamp = jObj.getString("ts");
        // assign the body timestamp to header timestamp
        headers.put("timestamp",timestamp);

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        //for all event inside the list apply the intercept method, which adds the timestamp header
        for(Event e:list){
            intercept(e);
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new TimeStampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
