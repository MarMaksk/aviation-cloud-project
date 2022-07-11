FROM docker.elastic.co/beats/filebeat:8.2.2
# Copy our custom configuration file
COPY conf/filebeat.yml /usr/share/filebeat
USER root

RUN chown -R root /usr/share/filebeat/
RUN chmod -R go-w /usr/share/filebeat/

