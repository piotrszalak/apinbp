package com.szalak.nest.nbp;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "currency", namespace = "http://szalak.com/zadanie", propOrder = {
        "id",
        "table",
        "currency",
        "code",
        "rates"
})
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @XmlElement(required = true)
    protected String table;
    @XmlElement(required = true)
    protected String currency;
    @XmlElement(required = true)
    protected String code;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @XmlElement(required = true)
    protected List<Rates> rates;

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String value) {
        this.table = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String value) {
        this.currency = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public List<Rates> getRates() {
        if (rates == null) {
            rates = new ArrayList<Rates>();
        }
        return this.rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates +
                '}';
    }
}

