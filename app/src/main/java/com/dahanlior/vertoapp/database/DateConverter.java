package com.dahanlior.vertoapp.database;



import java.util.Date;

import androidx.room.TypeConverter;



public class DateConverter implements Converter<Long, Date> {

    @TypeConverter
    @Override
    public Long toDbValue(Date objectValue) {
        return objectValue != null ? objectValue.getTime() : -1;
    }
    @TypeConverter
    @Override
    public Date toObjectValue(Long dbValue) {
        return dbValue != null && dbValue != -1 ? new Date(dbValue) : null;
    }

}
