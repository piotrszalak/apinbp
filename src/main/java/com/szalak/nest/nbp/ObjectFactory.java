
package com.szalak.nest.nbp;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public GetCurrency createGetCurrency() {
        return new GetCurrency();
    }

    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    public Rates createRates() {
        return new Rates();
    }

    public Currency createCurrency() {
        return new Currency();
    }

}
