package com.epam.movieTheater.entity;

import com.opencsv.bean.CsvBindByPosition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Auditorium {

    @CsvBindByPosition(position = 0)
    private Integer auditoriumId;
    @CsvBindByPosition(position = 1)
    private String auditoriumName;
    @CsvBindByPosition(position = 2)
    private String numberOfSeats;
    @CsvBindByPosition(position = 3)
    private String vipSeats;
    @CsvBindByPosition(position = 4)
    private String priceForBasicSeat;
    @CsvBindByPosition(position = 5)
    private String factorForVipSeat;
    @CsvBindByPosition(position = 6)
    private String factorForHighRatedEvent;

    public Auditorium() {
    }

    public Auditorium(Integer auditoriumId, String auditoriumName, String numberOfSeats, String vipSeats, String priceForBasicSeat, String factorForVipSeat, String factorForHighRatedEvent) {
        this.auditoriumId = auditoriumId;
        this.auditoriumName = auditoriumName;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
        this.priceForBasicSeat = priceForBasicSeat;
        this.factorForVipSeat = factorForVipSeat;
        this.factorForHighRatedEvent = factorForHighRatedEvent;
    }

    public Integer getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(Integer auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(String vipSeats) {
        this.vipSeats = vipSeats;
    }

    public String getPriceForBasicSeat() {
        return priceForBasicSeat;
    }

    public void setPriceForBasicSeat(String priceForBasicSeat) {
        this.priceForBasicSeat = priceForBasicSeat;
    }

    public String getFactorForVipSeat() {
        return factorForVipSeat;
    }

    public void setFactorForVipSeat(String factorForVipSeat) {
        this.factorForVipSeat = factorForVipSeat;
    }

    public String getFactorForHighRatedEvent() {
        return factorForHighRatedEvent;
    }

    public void setFactorForHighRatedEvent(String factorForHighRatedEvent) {
        this.factorForHighRatedEvent = factorForHighRatedEvent;
    }

    public Auditorium setAuditorium(Integer auditoriumId, String auditoriumName, String numberOfSeats, String vipSeats, String priceForBasicSeat, String factorForVipSeat, String factorForHighRatedEvent) {
        this.auditoriumId = auditoriumId;
        this.auditoriumName = auditoriumName;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
        this.priceForBasicSeat = priceForBasicSeat;
        this.factorForVipSeat = factorForVipSeat;
        this.factorForHighRatedEvent = factorForHighRatedEvent;
        return new Auditorium(auditoriumId, auditoriumName, numberOfSeats, vipSeats, priceForBasicSeat, factorForVipSeat, factorForHighRatedEvent);
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "auditoriumId=" + auditoriumId +
                ", auditoriumName='" + auditoriumName + '\'' +
                ", numberOfSeats='" + numberOfSeats + '\'' +
                ", vipSeats='" + vipSeats + '\'' +
                ", priceForBasicSeat='" + priceForBasicSeat + '\'' +
                ", factorForVipSeat='" + factorForVipSeat + '\'' +
                ", factorForHighRatedEvent='" + factorForHighRatedEvent + '\'' +
                '}';
    }
}

