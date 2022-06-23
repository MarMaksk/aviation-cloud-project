package org.aviation.projects.flightcatering.service;

public interface ISender {

    void send(String emailTo, String subject, String message);

    void send(String emailTo, String subject, byte[] data, String name);

    void sendForCaterers(String subject, String message);
}
