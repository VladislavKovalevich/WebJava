package org.labs.wt.tour.model;

public enum KindOfTransport {
    BUS("Автобус"),
    AIR("Самолет"),
    TRAIN("Поезд");

    private String enumName;

    KindOfTransport(String name) {
        this.enumName = name;
    }
}
