package com.example.testproject1.service.dbservice.department;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций к базе данных.
 *
 * @author smigranov
 */
@Service("DepartmentService")
@Order(value = 3)
public class DepartmentService implements CrudService<Department> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<Department> departmentRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Department create(Department department) {
        LOGGER.info("Попытка создания Department");
        return departmentRepository.create(department);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<Department> getAll() {
        LOGGER.info("Попытка выдачи всех Department");
        return departmentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Department> getById(UUID id) {
        LOGGER.info("Попытка получить Department по id");
        return departmentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Department update(Department department) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Department с id {0}", department.getId().toString()));
        return departmentRepository.update(department);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info("Попытка удаления записей из таблицы department");
        departmentRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        LOGGER.info(MessageFormat.format("Попытка удаления Department с id {0}", id.toString()));
        departmentRepository.deleteById(id);
    }

    @Override
    public void saveALL(List<Department> entityList) {
        LOGGER.info("Попытка сохранения List<Department> в таблицу department");
        departmentRepository.saveAll(entityList);
    }
}
