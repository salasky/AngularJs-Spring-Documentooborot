package com.example.testproject1.exeption;

public class DocumentExistsException  extends Exception{
    private Long detail;
    public DocumentExistsException(Long detail,String message) {
        super(message);
        this.detail=detail;
    }

    @Override
    public String toString() {
        return "DocumentExistsException{" +
                "RegNumber= " + detail +" exist"+
                '}';
    }
}
