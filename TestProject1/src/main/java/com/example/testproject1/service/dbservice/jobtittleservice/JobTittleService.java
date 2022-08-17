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

import java.sql.BatchUpdateException;
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

    @Autowired
    private CrudRepository<JobTittle> jobTittleRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public JobTittle create(JobTittle jobTittle) {
        LOGGER.info("Попытка создания JobTittle");
        if (jobTittle.getId() == null) {
            jobTittle.setId(UUID.randomUUID());
        }
        return jobTittleRepository.create(jobTittle);
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
        LOGGER.info(MessageFormat.format("Попытка изменить данные у JobTittle с id {0}", jobTittle.getId().toString()));
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
    public void saveAll(List<JobTittle> entityList) {
        LOGGER.info("Попытка сохранения List<JobTittle> в таблицу JobTittle");
        entityList.stream().filter(entity -> entity.getId() == null).forEach(entity -> entity.setId(UUID.randomUUID()));
        try {
            jobTittleRepository.saveAll(entityList);
        } catch (BatchUpdateException e) {
            throw new DocflowRuntimeApplicationException("Ошибка сохранения", e);
        }
    }
}
