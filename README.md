# CustomFlumeInterceptor

In this project, we create a custom flume interceptor. It intercepts all flume events between source and channel. For 
the event that is validated, it will be sent to channel, otherwise it will be ignored. 

Important Notes:
- the custom interceptor must implement the org.apache.flume.interceptor.Interceptor
- the custom interceptor must contain a inner static class Builder that implements org.apache.flume.interceptor.Interceptor.Builder. This class will be used by flume agent to create the interceptor instance
- when using the generated jar file, you must take the one with dependencies, because flume agent will not have them,
you will receive "Class not found errors"
