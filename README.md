# Spring-Security for Spring-MVC webapp

Aim for Spring MVC - form based authentication, not particular for REST-ful design

* Spring Security - DaoAuthenticationProvider Implementation
* Spring Boot with Gradle
* Thymeleaf as the view resolver as it is default & easily (only need to add dependency to build.gradle)
* Right now, H2 is used for debug convenience (see application.properties for detail)
* csrf().disable() for the purpose of h2-console
* JPA for Dao layer - e.g. AccountDao extends JpaRepository<Account, Long>
* gradle -> checkstyleMain for coding styles
* originally created using java 11, let me know if you have any issue with java 8s

last updated on April-28-2020
