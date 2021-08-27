# CustomFlumeInterceptor

In this project, we create a custom flume interceptor. It intercepts all flume events between source and channel. For 
the event that is validated, it will be sent to channel, otherwise it will be ignored. 

Important Notes:
- the custom interceptor must implement the org.apache.flume.interceptor.Interceptor
- when using the generated jar file, you must take the one with dependencies, because flume agent will not have them,
you will receive "Class not found errors"