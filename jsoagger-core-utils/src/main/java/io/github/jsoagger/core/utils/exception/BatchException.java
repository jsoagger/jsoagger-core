package io.github.jsoagger.core.utils.exception;

public class BatchException extends BaseException {

    public BatchException(){
        super();
    }

    public BatchException(RuntimeException exception, Object...args){
        super(exception, args);
    }

    public BatchException(Throwable exception, Object...args){
        super(exception, args);
    }

    public BatchException(String errorCode, String messageTitle, Object...args){
        super(errorCode, messageTitle, args);
    }

    public BatchException(String errorCode, Object...args){
        super(errorCode, args);
    }
}
