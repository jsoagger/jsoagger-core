package io.github.jsoagger.core.utils.exception;

public class ServicesException extends BaseException {

    public ServicesException(){
        super();
    }

    public ServicesException(RuntimeException exception, Object...args){
        super(exception, args);
    }

    public ServicesException(Throwable exception, Object...args){
        super(exception, args);
    }

    public ServicesException(String errorCode, String messageTitle, Object...args){
        super(errorCode, messageTitle, args);
    }

    public ServicesException(String errorCode, Object...args){
        super(errorCode, args);
    }
}
