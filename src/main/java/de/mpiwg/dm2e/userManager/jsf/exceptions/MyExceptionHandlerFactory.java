package de.mpiwg.dm2e.userManager.jsf.exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class MyExceptionHandlerFactory extends ExceptionHandlerFactory {

    ExceptionHandlerFactory delegateFactory;

    public MyExceptionHandlerFactory(ExceptionHandlerFactory delegateFactory) {
        this.delegateFactory = delegateFactory;
    }

    public ExceptionHandler getExceptionHandler() {
        return new MyExceptionHandler(delegateFactory.getExceptionHandler());
    }
}
