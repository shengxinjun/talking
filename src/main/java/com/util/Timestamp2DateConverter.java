package com.util;

import org.jooq.Converter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Timestamp2DateConverter implements Converter<Timestamp, Date> {

	private static final long serialVersionUID = 1529338732611199027L;

	@Override
    public Date from(Timestamp databaseObject) {
        return Objects.isNull(databaseObject) ? null : new Date(databaseObject.getTime());
    }

    @Override
    public Timestamp to(Date userObject) {
        return Objects.isNull(userObject) ? null : new Timestamp(userObject.getTime());
    }

    @Override
    public Class<Timestamp> fromType() {
        return Timestamp.class;
    }

    @Override
    public Class<Date> toType() {
        return Date.class;
    }

}
