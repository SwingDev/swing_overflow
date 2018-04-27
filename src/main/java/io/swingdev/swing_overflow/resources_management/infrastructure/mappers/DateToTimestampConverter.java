package io.swingdev.swing_overflow.resources_management.infrastructure.mappers;

import org.dozer.DozerConverter;

import java.util.Date;

public class DateToTimestampConverter extends DozerConverter<Date, String> {
    public DateToTimestampConverter() {
        super(Date.class, String.class);
    }

    @Override
    public String convertTo(Date source, String destination) {
        return Float.toString(source.getTime() / 1000);
    }

    @Override
    public Date convertFrom(String source, Date destination) {
        return new Date((long) Float.parseFloat(source) * 1000);
    }
}
