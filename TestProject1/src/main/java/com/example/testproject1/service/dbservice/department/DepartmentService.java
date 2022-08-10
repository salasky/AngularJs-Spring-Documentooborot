package com.example.testproject1.service.dbservice.department;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DeleteByIdException;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.exception.UpdateException;
import com.example.testproject1.model.staff.Department;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций к базе данных.
 *
 * @author smigranov
 */
@Service("DepartmentService")
@Order(value=3)
public class DepartmentService implements CrudService<Department> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<Department> departmentRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "Department успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL = "Неудачная попытка сохранения Department";
    /**
     * Лог при выдаче всех Department
     */
    private final String GET_ALL_ATTEMPT = "Попытка выдачи всех Department";
    /**
     * Лог при выдаче Department по id
     */
    private final String GET_BY_ID_ATTEMPT = "Попытка получить Department по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS = "Department успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL = "Неудачная попытка обновления Department";
    /**
     * Лог при попытке удаления всех записей
     */
    private final String DELETE_SUCCESS = "Попытка удаления записей из таблицы department";
    /**
     * Лог при неудачном удалении удалении записи по id
     */
    private final String DELETE_BY_ID_FAIL = "Запись из таблицы department не удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public Department create(Department department) throws DocflowRuntimeApplicationException {
        Department departmentDB = departmentRepository.create(department);
        if (departmentDB != null) {
            LOGGER.info(CREATE_SUCCESS);
            return departmentDB;
        }
        throw new DocflowRuntimeApplicationException(CREATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Department> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return departmentRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Department> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return departmentRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Department update(Department department) throws DocflowRuntimeApplicationException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Department с id {0}", department.getId().toString()));
        int updateCount = departmentRepository.update(department);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return department;
        }
        throw new DocflowRuntimeApplicationException(UPDATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info(DELETE_SUCCESS);
        departmentRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) throws DocflowRuntimeApplicationException {
        try {
            departmentRepository.deleteById(id);
        } catch (DeleteByIdException e) {
            throw new DocflowRuntimeApplicationException(DELETE_BY_ID_FAIL);
        }
    }
}
