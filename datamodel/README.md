Setting up the database
=======================

The reference implementation uses PostgreSQL as the underlying database
```
cd initdb.d && cat ./* ../testdata.d/* | sudo -u postgres psql -f -
```

Alternatively, if you are using Windows, you can run the following the command in a bash windows (e.g. WSL or GIT BASH):

```
cd initdb.d && cat ./* ../testdata.d/* > hat.sql
```

The `hat.sql` file should be in the `initdb.d` directory. If it is not, move it there.

Afterwards, open a Powershell window in the `initdb.d` directory and run the following commands: 

```
psql -U [db_user] -p [port_number] -f hat.sql
``` 

