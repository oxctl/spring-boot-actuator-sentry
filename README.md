Spring Boot Actuator for Sentry
===============================

This library adds a new actuator endpoint called `sentry` which allows you to check that your Sentry configuration is correct and that events can be sent to Sentry.

To use this library you should already have Spring Boot Actuator setup in your application.

Setup
-----

Add the dependency to your `pom.xml` in the `<dependencies>` section:

```xml
<dependency>
    <groupId>uk.ac.ox.it</groupId>
    <artifactId>spring-boot-actuator-sentry</artifactId>
    <version>0.1</version>
</dependency>
```

The library is automatically configured through Spring Boot autoconfiguration so no further code changes are needed.

To allow access to this endpoint you need to add the following to your `application.yml` or `application.properties` file:

```properties
management.endpoints.web.exposure.include=sentry
```

You may already have some endpoints exposed, in which case just add `,sentry` to the list.

If you have some web security configured you will need to allow access to the sentry endpoint (`/actuator/sentry` by default).

Usage
-----

This library doesn't expose any configuration options or properties.

Checking if sentry is enabled (ie the application has a DSN configured):

```bash
curl http://localhost:8080/actuator/sentry
```
and this should return:

```terminaloutput
{"enabled":true}
```
or if something is wrong with the configuration:
```terminaloutput
{"enabled":false}
```

To test that events can be sent to Sentry you can use the write operation:

```bash
curl -X POST http://localhost:8080/actuator/sentry
```

This should not return any content but a 204 status and there should be an issue accessible in the Sentry web UI.


Releasing
---------

To make a new release of this library do:

```bash
mvn release:prepare
```

then if everything looks good:

```bash
mvn release:perform
```
