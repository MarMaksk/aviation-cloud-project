package org.aviation.entity.enums;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    CREATED("Создана"),
    PROCESSED("Обрабатывается"),
    IN_PROGRESS("В процессе"),
    FINISHED("Завершена"),
    CANCELLATION("Отменена"),
    EXPIRED("Просрочена");

    private String name;

    DeliveryStatus(String name) {
        this.name = name;
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
