FROM postgres:10-alpine
EXPOSE 5432
COPY initdb.d/* /docker-entrypoint-initdb.d/
COPY referencedata.d/* /docker-entrypoint-referencedata.d/
ENV DCSA_REFERENCE_DATA /docker-entrypoint-referencedata.d/
COPY load-reference-data.sh /docker-entrypoint-initdb.d/04-load-reference-data.sh
RUN chmod +x /docker-entrypoint-initdb.d/04-load-reference-data.sh
