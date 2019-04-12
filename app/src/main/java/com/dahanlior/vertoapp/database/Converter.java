package com.dahanlior.vertoapp.database;


import androidx.room.TypeConverter;

/**
 * Created by liord on 8/5/2018.
 */

public interface Converter<DB, OBJECT> {

    /**
     * Converts custom valueFromServer to DB valueFromServer.
     * @param objectValue   Object custom valueFromServer
     * @return
     */
    @TypeConverter
    DB toDbValue(OBJECT objectValue);

    /**
     * Converts DB valueFromServer to custom object valueFromServer.
     * @param dbValue   DB valueFromServer.
     * @return
     */
    @TypeConverter
    OBJECT toObjectValue(DB dbValue);

}
