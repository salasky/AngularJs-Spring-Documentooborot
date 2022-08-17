package com.example.testproject1.model.utility;

import java.sql.Timestamp;

public class RestErrorMessage {
    /**
     * Отправляемая на UI ошибка
     */
    private String errorMessage;
    /**
     * Время возникновения ошибки
     */
    private Timestamp timestamp;

    public RestErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
