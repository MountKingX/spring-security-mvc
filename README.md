# Spring-Security for Spring-MVC webapp learner [Gradle]

Aim for Spring MVC - form based authentication, not particular for REST-ful design

* Spring Security - DaoAuthenticationProvider Implementation
* Thymeleaf as the view resolver as it is default & easily (only need to add dependency to build.gradle)
* Right now, H2 is used for debug convenience (see application.properties for detail)
* csrf().disable() for the purpose of h2-console
* JPA for Dao layer - e.g. AccountDao extends JpaRepository<Account, String>
* `gradle checkstyleMain` for coding styles
* originally created using java 11

last updated on April-28-2020
