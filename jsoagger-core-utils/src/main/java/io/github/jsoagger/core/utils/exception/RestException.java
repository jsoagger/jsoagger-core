package io.github.jsoagger.core.utils.exception;

public class RestException  extends BaseException  {

    public RestException(){
        super();
    }

    public RestException(RuntimeException exception, Object...args){
        super(exception, args);
    }

    public RestException(Throwable exception, Object...args){
        super(exception, args);
    }

    public RestException(String errorCode, String messageTitle, Object...args){
        super(errorCode, messageTitle, args);
    }

    public RestException(String errorCode, Object...args){
        super(errorCode, args);
    }
}
