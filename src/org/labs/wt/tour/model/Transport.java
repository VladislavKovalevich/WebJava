package org.labs.wt.tour.model;

import org.labs.wt.tour.model.KindOfTransport;

public class Transport {

    private KindOfTransport kindOfTransport;
    private String placeFrom;

    public KindOfTransport getKindOfTransport() {
        return kindOfTransport;
    }

    public String getPlaceFrom() {
        return placeFrom;
    }

    public Transport(KindOfTransport kindOfTransport, String placeFrom){
        this.kindOfTransport = kindOfTransport;
        this.placeFrom = placeFrom;
    }
}
