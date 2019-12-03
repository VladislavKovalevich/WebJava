
package org.labs.wt.tour.model;


public enum TransportType {

    BUS("Автобус"),
    AIR("Самолет"),
    TRAIN("Поезд");

    private String enumName;

    TransportType(String name) {
        this.enumName = name;
    }

}
