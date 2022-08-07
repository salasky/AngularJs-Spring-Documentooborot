package com.example.testproject1.service.dbservice.department;

import com.example.testproject1.dao.department.DepartmentRepository;
import com.example.testproject1.exception.DeletePoorlyException;
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
    private final String CREATE_SUCCESS="Department успешно сохранен";
    private final String CREATE_FAIL="Неудачная попытка сохранения Department";
    private final String GET_ALL_ATTEMPT="Попытка выдачи всех Department";
    private final String GET_BY_ID_ATTEMPT="Попытка получить Department по id";
    private final String UPDATE_SUCCESS="Department успешно обновлен";
    private final String UPDATE_FAIL="Неудачная попытка обновления Department";
    private final String DELETE_SUCCESS="Записи из таблицы department успешно удалены";
    private final String DELETE_BY_ID_SUCCESS="Запись из таблицы department успешно удалена";
    @Override
    public Optional<Department> create(Department department) {
        Optional<Department> optionalDepartment= departmentRepository.create(department);
        if (optionalDepartment.isPresent()) {
            LOGGER.info(CREATE_SUCCESS);
            return Optional.ofNullable(department);
        }
        LOGGER.error(CREATE_FAIL);
        return Optional.empty();
    }

    @Override
    public List<Department> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return departmentRepository.getAll();
    }

    @Override
    public Optional<Department> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return departmentRepository.getById(id);
    }
    @Override
    public Optional<Department> update(Department department) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у Department с id {0}", department.getId().toString()));
        int updateCount = departmentRepository.update(department);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(department);
        }
        LOGGER.error(UPDATE_FAIL);
        return Optional.empty();
    }

    @Override
    public void deleteAll() {
        try {
            if (departmentRepository.deleteAll()) {
                LOGGER.info(DELETE_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            if (departmentRepository.deleteById(id)) {
                LOGGER.info(DELETE_BY_ID_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
}
