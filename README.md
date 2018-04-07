# About

Quickstart repo for a Maven managed dynamic web-application using Servlets and
JDBC.

## `DataSource`

Before deploying to a webserver create a `Resource` like in your webserver's config (e.g. for Apache Tomcat in `conf/context.xml`).

```
<Resource name="jdbc/database"
              type="javax.sql.DataSource"
              username="postgres"
              password="admin"
              driverClassName="org.postgresql.Driver"
              url="jdbc:postgresql://localhost:5432/coupon_store"/>
```
