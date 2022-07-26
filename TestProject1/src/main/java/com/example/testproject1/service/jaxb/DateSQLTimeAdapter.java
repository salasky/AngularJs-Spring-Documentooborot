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
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public String marshal(Date date) throws Exception {
        return date.toString();
    }

    /**
     * Unmarshal String в sql.Date
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public Date unmarshal(String s) throws Exception {
        return Date.valueOf(s);
    }
}
