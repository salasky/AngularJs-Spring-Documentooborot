package com.example.testproject1.service.dbservice.department;

import com.example.testproject1.dao.department.DepartmentRepository;
import com.example.testproject1.model.staff.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Optional<Department> create(Department department) {
        LOGGER.info("Попытка создания Department");
        Optional<Department> optionalDepartment= departmentRepository.create(department);
        if (optionalDepartment.isPresent()) {
            LOGGER.info("Department успешно сохранен");
            return Optional.ofNullable(department);
        }
        LOGGER.error("Неудачная попытка сохранения Department");
        return Optional.empty();
    }

    @Override
    public List<Department> getall() {
        LOGGER.info("Попытка выдачи всех Department");
        return departmentRepository.getAll();
    }

    @Override
    public Optional<Department> getById(String id) {
        LOGGER.info("Попытка получить Department по id");
        return departmentRepository.getById(id);
    }

    @Override
    public Optional<Department> update(Department department) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Department с id {0}", department.getId().toString()));
        int updateCount = departmentRepository.update(department);
        if (updateCount == 1) {
            LOGGER.info("Department успешно обновлен");
            return Optional.ofNullable(department);
        }
        LOGGER.error("Неудачная попытка обновления Department");
        return null;
    }

    @Override
    public String deleteAll() {
        LOGGER.info("Попытка удалить все записи в таблице department");
        int count = departmentRepository.deleteAll();
        if (count > 0) {
            LOGGER.info("Записи из таблицы department успешно удалены");
            return "Записи из таблицы department успешно удалены";
        }
        LOGGER.error("Не удачная попытка удаления записей из таблицы department");
        return "Не удачная попытка удаления записей из таблицы department";
    }

    @Override
    public String deleteById(String id) {
        LOGGER.info("Попытка удалить запись из таблицы department");
        int count = departmentRepository.deleteById(id);
        if (count > 0) {
            LOGGER.info("Запись из таблицы department успешно удалена");
            return "Запись из таблицы department успешно удалена";
        }
        LOGGER.error("Не удачная попытка удаления записи из таблицы department");
        return "Не удачная попытка удаления записи из таблицы department";
    }
}
