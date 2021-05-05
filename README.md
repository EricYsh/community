## CPT202 Group Project
A web app help SAT students especially for CS major to find internships or even work.

## Records of framework we use
### frontend
Thymeleaf:
https://www.thymeleaf.org/doc/articles/layouts.html

Bootstrap:
https://v3.bootcss.com/getting-started/

jQuery:
https://jquery.com/

### backend
OAuth App
https://docs.github.com/en/developers/apps/creating-an-oauth-app

Spring Boot
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

MyBatis
https://mybatis.org/mybatis-3/index.html

H2: lightweight database
http://www.h2database.com/html/main.html

Flyway: database migrate
https://flywaydb.org/getstarted/firststeps/maven

Lombok
https://www.projectlombok.org/setup/maven

MyBatis Generator (a code generator for MyBatis正在测试，暂时没用到)
http://mybatis.org/generator/

## Run this project in your device
1. rm ~/community.*
2. mvn flyway:migrate
3. mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
4. run this project in IDEA

### Please ignore the following code
> mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate