목적
* user-service로 오는 요청을 처리한다.
* user 관리, 로그인을 처리한다.

lib
* kotlin-logging-jvm
* spring-boot-starter-web
* spring-security-crypto
* jackson-module-kotlin
* springfox-swagger2
* springfox-swagger-ui
* spring-boot-starter-data-jpa
* mysql-connector-java
* jjwt-api
* jjwt-impl
* jjwt-jackson
* spring-cloud-starter-openfeign

infra
* mysql

function
* 회원가입
* 로그인
   - access, refresh token 발급
* user 조회
* user 정보 수정
* user token 발급
    - refresh token이 확인 되면 access, refresh token 발급
    - refresh token은 저장