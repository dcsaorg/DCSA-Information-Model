Setting up the database
=======================

The reference implementation uses PostgreSQL as the underlying database
```
cd initdb.d && cat ./* ../testdata.d/* | sudo -u postgres psql -f -
```
