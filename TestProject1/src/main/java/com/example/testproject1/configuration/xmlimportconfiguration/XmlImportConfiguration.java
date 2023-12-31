package com.example.testproject1.configuration.xmlimportconfiguration;

import com.example.testproject1.service.dbservice.CrudService;
import com.example.testproject1.service.staffservice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для конфигурации импорта xml в базу данных
 *
 * @author smigranov
 */
@Configuration
public class XmlImportConfiguration {

    @Autowired
    private List<StorageService> storageServiceList;

    @Autowired
    private List<CrudService> crudServiceList;

    /**
     * Бин возвращающий сконфигурированный Map<StorageService, CrudService>
     *
     * @return возвращает Map<StorageService, CrudService> c совпадающими значениями
     * в keys и values
     */
    @Bean
    public Map<StorageService, CrudService> getStorageServiceMap() {
        return combineLists(storageServiceList, crudServiceList);
    }

    /**
     * Вспомогательный метод для конфигурации map
     *
     * @param keys   StorageService
     * @param values CrudService
     * @return Map<StorageService, CrudService>
     */
    private Map<StorageService, CrudService> combineLists(List<StorageService> keys, List<CrudService> values) {
        if (keys.size() > values.size()) {
            throw new IllegalArgumentException("Unable to merge lists. StorageServiceList size must not be larger.");
        }
        Map<StorageService, CrudService> map = new LinkedHashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }
}
