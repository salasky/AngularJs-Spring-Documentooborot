package com.example.testproject1.model.dto;

/**
 * Класс для сообщений отправляемых через контроллер
 *
 * @author smigranov
 */
public class MessageResponseDTO {
    /**
     * Отправляемое сообщение
     */
    private String message;

    public MessageResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
