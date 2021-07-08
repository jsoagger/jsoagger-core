package io.github.jsoagger.core.utils.exception;

import io.github.jsoagger.core.utils.StringUtils;

public abstract class BaseException extends RuntimeException {

    protected String errorCode;
    protected String messageTitle;
    protected Object[] args;

    public BaseException(){
        super();
    }

    public BaseException(RuntimeException exception, Object...args){
        super();
        this.errorCode = "RUNTIME";
        this.messageTitle = exception.getLocalizedMessage();
        this.args = args;
    }

    public BaseException(Throwable exception, Object...args){
        super();
        this.errorCode = "RUNTIME";
        this.messageTitle = exception.getLocalizedMessage();
        this.args = args;
    }

    public BaseException(String errorCode, String messageTitle, Object...args){
        this.errorCode = errorCode;
        this.messageTitle = messageTitle;
        this.args = args;
    }

    public BaseException(String errorCode, Object...args){
        this.errorCode = errorCode;
        this.args = args;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessageTitle() {
        if(StringUtils.isNotEmpty(messageTitle)){
            return String.format(messageTitle, args);
        }

        return messageTitle;
    }

    public void setMessageTitle(final String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(final String[] args) {
        this.args = args;
    }
}
