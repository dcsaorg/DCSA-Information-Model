Setting up the database
=======================

The reference implementation uses PostgreSQL as the underlying database
```
cd initdb.d && cat ./* ../testdata.d/* | sudo -u postgres psql -f -
```

Alternatively, if you are using Windows run the following command in a Powershell window from the `initdb.d` directory (remember to replace the two placeholders in the command):

```
(Get-ChildItem ./*, ../testdata.d/* -File) | Get-Content | psql -U [postgres_username] -p [port_number] 
```
