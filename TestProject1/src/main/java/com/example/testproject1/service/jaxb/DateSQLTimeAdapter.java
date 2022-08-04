package com.example.testproject1.service.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;

/**
 * XmlAdapter для маршаллинга и анмаршаллинга Date.sql
 *
 * @author smigranov
 */
public class DateSQLTimeAdapter extends XmlAdapter<String, Date> {

    /**
     * Marshal sql.Date в String
     *
     * @param date объект sql.Date, представляющий дату в формате "yyyy-[m]m-[d]d".
     * @return объект String, представляющий дату в формате "yyyy-[m]m-[d]d"
     */
    @Override
    public String marshal(Date date) {
        return date.toString();
    }

    /**
     * Unmarshal String в sql.Date
     *
     * @param data объект String, представляющий дату в формате "yyyy-[m]m-[d]d".
     * @return {@link  Date} Возвращает объект java.sql.Date, представляющий заданную дату
     */
    @Override
    public Date unmarshal(String data) {
        return Date.valueOf(data);
    }
}
