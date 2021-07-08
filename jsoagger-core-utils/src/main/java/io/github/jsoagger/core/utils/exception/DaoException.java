package io.github.jsoagger.core.utils.exception;

public class DaoException  extends BaseException {

    public DaoException(){
        super();
    }

    public DaoException(RuntimeException exception, String...args){
        super(exception, args);
    }

    public DaoException(Throwable exception, String...args){
        super(exception, args);
    }

    public DaoException(String errorCode, String messageTitle, String...args){
        super(errorCode, messageTitle, args);
    }

    public DaoException(String errorCode, Object...args){
        super(errorCode, args);
    }
}
