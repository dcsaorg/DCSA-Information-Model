Setting up the database
=======================

The reference implementation uses PostgreSQL as the underlying database
```
cd initdb.d && cat ./* ../testdata.d/* | sudo -u postgres psql -f -
```

Alternatively, if you are using Windows run the following command in a Powershell window:

```
(cd initdb.d) -and (cat ./* ../testdata.d/*) | psql -U [db_user] -p [port_number] -f
```