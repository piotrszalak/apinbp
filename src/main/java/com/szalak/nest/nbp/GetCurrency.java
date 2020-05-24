package com.szalak.nest.nbp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "currecny",
        "startDate",
        "endDate"
})
@XmlRootElement(name = "getCurrency", namespace = "http://szalak.com/zadanie")
public class GetCurrency {

    @XmlElement(required = true)
    protected String currecny;
    @XmlElement(required = true)
    protected String startDate;
    @XmlElement(required = true)
    protected String endDate;

    public String getCurrecny() {
        return currecny;
    }

    public void setCurrecny(String value) {
        this.currecny = value;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String value) {
        this.startDate = value;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String value) {
        this.endDate = value;
    }

}
