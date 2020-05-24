
package com.szalak.nest.nbp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rates"
})
@XmlRootElement(name = "getResponse", namespace = "http://szalak.com/zadanie")
public class GetResponse {

    @XmlElement(required = true)
    protected List<Rates> rates;

    public List<Rates> getRates() {
        if (rates == null) {
            rates = new ArrayList<Rates>();
        }
        return this.rates;
    }

}
