package com.example.testproject1.service.staffservice;

import java.util.List;

/**
 * Интерфейс хранения Department
 *
 * @author smigranov
 */
public interface StorageService<T> {
    /**
     * Метод получения сохраненных department
     *
     * @return объект {@link List} содержащий объекты из xml
     */
    List<T> getList();
}
