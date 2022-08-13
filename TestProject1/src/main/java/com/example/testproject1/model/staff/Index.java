package com.example.testproject1.model.staff;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Класс UUID нельзя проверить до десериализации, поэтому нужен класс оболочка
 *
 * @author smigranov
 */
public class Index implements Serializable {
    /**
     * UUID сохраняемой сущности
     */
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
