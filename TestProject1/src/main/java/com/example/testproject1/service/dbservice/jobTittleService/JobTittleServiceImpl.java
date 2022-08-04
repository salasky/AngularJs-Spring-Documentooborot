package com.example.testproject1.service.dbservice.jobTittleService;

import com.example.testproject1.dao.jobtittle.JobTittleRepository;
import com.example.testproject1.model.staff.JobTittle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class JobTittleServiceImpl implements JobTittleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTittleServiceImpl.class);

    @Autowired
    private JobTittleRepository jobTittleRepository;

    @Override
    public Optional<JobTittle> create(JobTittle jobTittle) {
        LOGGER.info("Попытка создания JobTittle");
        int updateCount = jobTittleRepository.create(jobTittle);
        if (updateCount == 1) {
            LOGGER.info("JobTittle успешно сохранен");
            return Optional.ofNullable(jobTittle);
        }
        LOGGER.error("Неудачная попытка сохранения JobTittle");
        return null;
    }

    @Override
    public List<JobTittle> getall() {
        LOGGER.info("Попытка выдачи всех JobTittle");
        return jobTittleRepository.getAll();
    }

    @Override
    public Optional<JobTittle> getById(String id) {
        LOGGER.info("Попытка получить JobTittle по id");
        return jobTittleRepository.getById(id);
    }

    @Override
    public Optional<JobTittle> update(JobTittle jobTittle) {
        LOGGER.info(MessageFormat.format("Попытка изменить данные у JobTittle с id {0}", jobTittle.getUuid().toString()));
        int updateCount = jobTittleRepository.update(jobTittle);
        if (updateCount == 1) {
            LOGGER.info("JobTittle успешно обновлен");
            return Optional.ofNullable(jobTittle);
        }
        LOGGER.error("Неудачная попытка обновления JobTittle");
        return null;
    }

    @Override
    public String deleteAll() {
        LOGGER.info("Попытка удалить все записи в таблице job_tittle");
        int count = jobTittleRepository.deleteAll();
        if (count > 0) {
            LOGGER.info("Записи из таблицы job_tittle успешно удалены");
            return "Записи из таблицы job_tittle успешно удалены";
        }
        LOGGER.error("Не удачная попытка удаления записей из таблицы job_tittle");
        return "Не удачная попытка удаления записей из таблицы job_tittle";
    }

    @Override
    public String deleteById(String id) {
        LOGGER.info("Попытка удалить запись из таблицы job_tittle");
        int count = jobTittleRepository.deleteById(id);
        if (count > 0) {
            LOGGER.info("Запись из таблицы job_tittle успешно удалена");
            return "Запись из таблицы job_tittle успешно удалена";
        }
        LOGGER.error("Не удачная попытка удаления записи из таблицы job_tittle");
        return "Не удачная попытка удаления записи из таблицы job_tittle";
    }
}
