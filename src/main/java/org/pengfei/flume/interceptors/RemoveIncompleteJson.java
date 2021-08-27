package org.pengfei.flume.interceptors;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class RemoveIncompleteJson implements Interceptor {
    @Override
    public void initialize() {

    }

    /**
     * This method handles one single flume event,
     */
    @Override
    public Event intercept(Event event) {
        // get the flume event body
        byte[] body = event.getBody();
        // convert byte to string
        String log = new String(body, StandardCharsets.UTF_8);
        // check the log
        if (JsonUtils.isValidate(log)) {
            return event;
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        // we can't use for loop here, because we want to remove the invalid event while we loop the list
        // we need to use iterator
        Iterator<Event> ite = list.iterator();
        while (ite.hasNext()){
            Event current_event = ite.next();
            //if current event is null(invalid event), remove the event from the list
            if(intercept(current_event)==null){
                ite.remove();
            }
        }
        return list;
    }

    @Override
    public void close() {

    }
    // this inner static class creat an instance of the RemoveIncompleteJson, so that the flume agent can use.
    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new RemoveIncompleteJson();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
