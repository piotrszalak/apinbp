package com.szalak.nest.nbp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rates", namespace = "http://szalak.com/zadanie", propOrder = {
        "no",
        "effectiveDate",
        "bid",
        "ask"
})
@Entity
@Table(name = "rates")
public class Rates {

    @Id
    @XmlElement(required = true)
    protected String no;
    @Column(name = "effectvieDate")
    @XmlElement(required = true)
    protected String effectiveDate;
    @Column(name ="bid")
    protected double bid;
    @Column(name ="ask")
    protected double ask;

    public String getNo() {
        return no;
    }

    public void setNo(String value) {
        this.no = value;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String value) {
        this.effectiveDate = value;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double value) {
        this.bid = value;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double value) {
        this.ask = value;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}
