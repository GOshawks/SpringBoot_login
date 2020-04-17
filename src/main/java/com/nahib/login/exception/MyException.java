package com.nahib.login.exception;

public class MyException extends RuntimeException {
        public MyException() {
            super();
        }

        public MyException(Enum e,String message){
            super(message);
        }

        public MyException(Enum e){
            super();
        }

}
