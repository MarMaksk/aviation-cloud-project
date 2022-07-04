package org.aviation.projects.flightcatering.service;

import io.micrometer.core.annotation.Timed;

public interface ISender {

    void send(String emailTo, String subject, String message);

    @Timed
    void send(String emailTo, String subject, byte[] data, String name);

    @Timed
    void sendForCaterers(String subject, String message, String token);
}
