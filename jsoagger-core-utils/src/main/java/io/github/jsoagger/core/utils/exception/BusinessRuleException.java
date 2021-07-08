package io.github.jsoagger.core.utils.exception;

public class BusinessRuleException extends  BaseException{

    public BusinessRuleException(){
        super();
    }

    public BusinessRuleException(RuntimeException exception, Object...args){
        super(exception, args);
    }

    public BusinessRuleException(Throwable exception, Object...args){
        super(exception, args);
    }

    public BusinessRuleException(String errorCode, String messageTitle, Object...args){
        super(errorCode, messageTitle, args);
    }

    public BusinessRuleException(String errorCode, Object...args){
        super(errorCode, args);
    }
}
