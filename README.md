Setting up the database
=======================

The reference implementation uses PostgreSQL as the underlying database
```
cd datamodel && cat initdb.d/* testdata.d/* | sudo -u postgres psql -f -
```

Loading only the reference data can be done by:

```
cd datamodel && cat initdb.d/04_load_*_reference_data.sql | sudo -u postgres psql -f -
```
