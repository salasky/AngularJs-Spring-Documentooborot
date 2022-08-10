package com.example.testproject1.service.dbservice.jobtittleservice;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.UpdateException;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link  JobTittle} к базе данных .
 *
 * @author smigranov
 */
@Service("JobTittleService")
public class JobTittleService implements CrudService<JobTittle> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTittleService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<JobTittle> jobTittleRepository;
    /**
     * Лог при успешном сохранении
     */
    private final String CREATE_SUCCESS = "JobTittle успешно сохранен";
    /**
     * Лог при неудачном сохранении
     */
    private final String CREATE_FAIL = "Неудачная попытка сохранения JobTittle";
    /**
     * Лог при выдаче всех JobTittle
     */
    private final String GET_ALL_ATTEMPT = "Попытка выдачи всех JobTittle";
    /**
     * Лог при выдаче JobTittle по id
     */
    private final String GET_BY_ID_ATTEMPT = "Попытка получить JobTittle по id";
    /**
     * Лог при успешном обновлении
     */
    private final String UPDATE_SUCCESS = "JobTittle успешно обновлен";
    /**
     * Лог при неудачном обновлении
     */
    private final String UPDATE_FAIL = "Неудачная попытка обновления JobTittle";
    /**
     * Лог при попытке удаления всех записей
     */
    private final String DELETE_SUCCESS = "Попытка удаления записей из таблицы JobTittle";
    /**
     * Лог при успешном удалении записи по id
     */
    private final String DELETE_BY_ID_SUCCESS = "Запись из таблицы JobTittle успешно удалена";
    /**
     * Лог при неудачном удалении удалении записи по id
     */
    private final String DELETE_BY_ID_FAIL = "Запись из таблицы JobTittle не удалена";

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle create(JobTittle jobTittle) {
        JobTittle jobTittleDB = jobTittleRepository.create(jobTittle);
        if (jobTittleDB!=null) {
            LOGGER.info(CREATE_SUCCESS);
            return jobTittleDB;
        }
        LOGGER.error(CREATE_FAIL);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobTittle> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return jobTittleRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<JobTittle> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return jobTittleRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle update(JobTittle jobTittle) throws UpdateException {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у JobTittle с id {0}", jobTittle.getUuid().toString()));
        int updateCount = jobTittleRepository.update(jobTittle);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return jobTittle;
        }
        throw new UpdateException(UPDATE_FAIL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info(DELETE_SUCCESS);
        jobTittleRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(String id) {
        if (jobTittleRepository.deleteById(id)) {
            LOGGER.info(DELETE_BY_ID_SUCCESS);
        }
        else {
            LOGGER.error(DELETE_BY_ID_FAIL);
        }
    }
}
