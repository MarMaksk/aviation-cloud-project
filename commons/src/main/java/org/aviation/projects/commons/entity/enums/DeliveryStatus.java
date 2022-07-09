package org.aviation.projects.commons.entity.enums;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    CREATED("Создана", "CREATED"),
    PROCESSED("Обрабатывается", "PROCESSED"),
    IN_PROGRESS("В процессе", "IN_PROGRESS"),
    FINISHED("Завершена", "FINISHED"),
    CANCELLATION("Отменена", "CANCELLATION"),
    EXPIRED("Просрочена", "EXPIRED");

    private String name;
    private String status;

    DeliveryStatus(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public static DeliveryStatus fromString(String name) {
        for (DeliveryStatus val : DeliveryStatus.values()) {
            if (val.getName().equalsIgnoreCase(name)) {
                return val;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }

}
