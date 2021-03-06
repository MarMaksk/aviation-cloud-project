package org.aviation.projects.flightorder.entity.enums;

import lombok.Getter;

@Getter
public enum FlightStatus {
    CREATED("Создан"),
    READY("Готов к вылету"),
    FLYING("Вылетел"),
    COMPLETED("Выполнен"),
    CANCELLATION("Отменён");

    private final String name;

    FlightStatus(String name) {
        this.name = name;
    }

    public static FlightStatus fromString(String name) {
        for (FlightStatus val : FlightStatus.values()) {
            if (val.getName().equalsIgnoreCase(name)) {
                return val;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }
}
