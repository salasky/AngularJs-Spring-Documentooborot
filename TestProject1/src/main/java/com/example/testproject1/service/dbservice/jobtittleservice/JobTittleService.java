package com.example.testproject1.service.dbservice.jobtittleservice;

import com.example.testproject1.dao.CrudRepository;
import com.example.testproject1.exception.DocflowRuntimeApplicationException;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс реализующий интерфейс {@link CrudService}. Для выполнения CRUD операций объектов класса {@link  JobTittle} к базе данных .
 *
 * @author smigranov
 */
@Order(value = 2)
@Service("JobTittleService")
public class JobTittleService implements CrudService<JobTittle> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTittleService.class);
    /**
     * Бин {@link CrudRepository}
     */
    @Autowired
    private CrudRepository<JobTittle> jobTittleRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle create(JobTittle jobTittle) {
        JobTittle jobTittleDB = jobTittleRepository.create(jobTittle);
        if (jobTittleDB != null) {
            LOGGER.info("JobTittle успешно сохранен");
            return jobTittleDB;
        }
        throw new DocflowRuntimeApplicationException("Неудачная попытка сохранения JobTittle");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobTittle> getAll() {
        LOGGER.info("Попытка выдачи всех JobTittle");
        return jobTittleRepository.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<JobTittle> getById(UUID id) {
        LOGGER.info("Попытка получить JobTittle по id");
        return jobTittleRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle update(JobTittle jobTittle) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у JobTittle с id {0}", jobTittle.getUuid().toString()));
        return jobTittleRepository.update(jobTittle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        LOGGER.info("Попытка удаления записей из таблицы JobTittle");
        jobTittleRepository.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(UUID id) {
        LOGGER.info(MessageFormat.format("Попытка удаления JobTittle с id {0}", id.toString()));
        jobTittleRepository.deleteById(id);
    }

    @Override
    public void saveALL(List<JobTittle> entityList) {
        LOGGER.info("Попытка сохранения List<JobTittle> в таблицу JobTittle");
        jobTittleRepository.saveAll(entityList);
    }
}
