package com.example.testproject1.service.dbservice.jobtittleservice;

import com.example.testproject1.dao.CrudRepositories;
import com.example.testproject1.exception.DeletePoorlyException;
import com.example.testproject1.model.staff.JobTittle;
import com.example.testproject1.service.dbservice.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service("JobTittleService")
public class JobTittleServiceImpl implements CrudService<JobTittle> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTittleServiceImpl.class);

    @Autowired
    @Qualifier("JobTittleRepository")
    private CrudRepositories jobTittleRepository;
    private final String CREATE_SUCCESS="JobTittle успешно сохранен";
    private final String CREATE_FAIL="Неудачная попытка сохранения JobTittle";
    private final String GET_ALL_ATTEMPT="Попытка выдачи всех JobTittle";
    private final String GET_BY_ID_ATTEMPT="Попытка получить JobTittle по id";
    private final String UPDATE_SUCCESS="JobTittle успешно обновлен";
    private final String UPDATE_FAIL="Неудачная попытка обновления JobTittle";
    private final String DELETE_SUCCESS="Записи из таблицы JobTittle успешно удалены";
    private final String DELETE_BY_ID_SUCCESS="Запись из таблицы JobTittle успешно удалена";

    @Override
    public Optional<JobTittle> create(JobTittle jobTittle) {
        Optional<JobTittle> jobTittleOptional = jobTittleRepository.create(jobTittle);
        if (jobTittleOptional.isPresent()) {
            LOGGER.info(CREATE_SUCCESS);
            return Optional.ofNullable(jobTittle);
        }
        LOGGER.error(CREATE_FAIL);
        return Optional.empty();
    }

    @Override
    public List<JobTittle> getall() {
        LOGGER.info(GET_ALL_ATTEMPT);
        return jobTittleRepository.getAll();
    }

    @Override
    public Optional<JobTittle> getById(String id) {
        LOGGER.info(GET_BY_ID_ATTEMPT);
        return jobTittleRepository.getById(id);
    }

    @Override
    public Optional<JobTittle> update(JobTittle jobTittle) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у JobTittle с id {0}", jobTittle.getUuid().toString()));
        int updateCount = jobTittleRepository.update(jobTittle);
        if (updateCount == 1) {
            LOGGER.info(UPDATE_SUCCESS);
            return Optional.ofNullable(jobTittle);
        }
        LOGGER.error(UPDATE_FAIL);
        return Optional.empty();
    }

    @Override
    public void deleteAll() {
        try {
            if (jobTittleRepository.deleteAll()) {
                LOGGER.info(DELETE_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            if (jobTittleRepository.deleteById(id)) {
                LOGGER.info(DELETE_BY_ID_SUCCESS);
            }
        } catch (DeletePoorlyException e) {
            LOGGER.error(e.toString());
        }
    }
}
