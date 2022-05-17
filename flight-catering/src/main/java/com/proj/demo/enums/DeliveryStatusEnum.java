package com.proj.demo.enums;

import lombok.Getter;

@Getter
public enum DeliveryStatusEnum {
    CREATED("Создана"),
    PROCESSED("Обрабатывается"),
    IN_PROGRESS("В процессе"),
    FINISHED("Завершена"),
    CANCELLATION("Отменена"),
    EXPIRED("Просрочена");

    private String name;

    DeliveryStatusEnum(String name) {
        this.name = name;
    }
}
