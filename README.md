## DDD 개발환경 구축

- 프로젝트 생성 (SpringBoot, Gradle, Java 1.8, War)
- MySQL 연동 및 DDL 생성 (sql 폴더 안에 ddl.sql, init.sql)
- application.yml 설정 (MySQL, JPA)
    - `profiles` 활용하여 `development`, `production` 구분 적용
        1. appication-dev.yml
        2. appication-prod.yml
        - `.gitignore`에 등록

-----------------------------------------------------

## MySQL 연동 체크

- Build 및 WAS Run 을 통해 구동 확인
    - 확실하게 알고 싶으면... `Entity` 작업을 통해 확인 가능

-----------------------------------------------------

## Hibernate, QueryDSL, HikariCP 사용

- Hibernate : `JPA`와 조화
- QueryDSL : JPA 보다 더 복잡한 Query 사용 시 활용
- HikariCP : `DB Connection Pool` 관리

------------------------------------------------------

## Common Config

1. CommonBean
2. JWT
3. Security
4. Auth

## Web View

1. JSP
2. Thymeleaf

------------------------------------------------------

## Entity 설계

1. Table > Column DataType 및 ID 기초 설계
2. Realationships > OneToMany, ManyToOne 양방향 관계 연결 > FetchType LAZY 전략 사용
3. Tostring > exclude 를 통해 기본적으로 제외하도록 등록
4. Index Column 연결
