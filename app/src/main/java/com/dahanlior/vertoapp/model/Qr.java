package com.dahanlior.vertoapp.model;

import android.media.Image;

import com.dahanlior.vertoapp.constants.DbSettings;
import com.dahanlior.vertoapp.database.DateConverter;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = DbSettings.TABLE_QR)
public class Qr {

    public static Qr create(String decodedData, Date createdOn, byte[] qrImage, String imageLocation) {
        final Qr qr = new Qr();
        qr.setDecodedData(decodedData);
        qr.setCreatedOn(createdOn);
        qr.setQrImage(qrImage);
        qr.setImageLocation(imageLocation);
        return qr;
    }


    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "decodedData")
    private String decodedData = "";
    @ColumnInfo(name = "created_on")
    @TypeConverters({DateConverter.class})
    private Date createdOn;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] qrImage;
    @ColumnInfo(name = "image_location")
    private String imageLocation;

    @Ignore
    public Qr(){

    }


    public Qr(String decodedData, Date createdOn, byte[] qrImage, String imageLocation) {
        this.decodedData = decodedData;
        this.createdOn = createdOn;
        this.qrImage = qrImage;
        this.imageLocation = imageLocation;
    }


    public String getDecodedData() {
        return decodedData;
    }

    public void setDecodedData(String decodedData) {
        this.decodedData = decodedData;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public byte[] getQrImage() {
        return qrImage;
    }

    public void setQrImage(byte[] qrImage) {
        this.qrImage = qrImage;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Qr qr = (Qr) o;
        return Objects.equals(decodedData, qr.decodedData) &&
                Objects.equals(createdOn, qr.createdOn) &&
                Arrays.equals(qrImage, qr.qrImage) &&
                Objects.equals(imageLocation, qr.imageLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decodedData, createdOn, qrImage, imageLocation);
    }

    @Override
    public String toString() {
        return "Qr{" +
                "decodedData='" + decodedData + '\'' +
                ", createdOn=" + createdOn +
                ", qrImage=" + qrImage +
                ", imageLocation='" + imageLocation + '\'' +
                '}';
    }
}
