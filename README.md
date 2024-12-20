# Spring Boot 쇼핑 애플리케이션

이 프로젝트는 Spring Boot를 사용하여 개발된 쇼핑 애플리케이션으로, 도메인 주도 설계(Domain-Driven Design, DDD) 원칙을 따릅니다. 비즈니스 로직과 데이터 영속성을 분리하여 깔끔하고 모듈화된 아키텍처를 유지하며, 회원, 주문, 아이템,
배송을 관리할 수 있습니다.

## 목차

- [사용 기술](#사용-기술)
- [프로젝트 구조](#프로젝트-구조)
- [도메인 개요](#도메인-개요)
- [애플리케이션 서비스](#애플리케이션-서비스)
- [인프라스트럭처 레이어](#인프라스트럭처-레이어)
- [프레젠테이션 레이어](#프레젠테이션-레이어)
- [설정](#설정)
- [애플리케이션 실행](#애플리케이션-실행)

## 사용 기술

- **Java 17**
- **Spring Boot**
- **H2 Database** (개발 및 테스트용)
- **IntelliJ IDEA Ultimate**
- **Gradle**

## 프로젝트 구조

이 프로젝트는 DDD 원칙을 따라 여러 레이어로 구성되어 있습니다:

```plaintext
src
└── main
    ├── java
    │   └── com.seek.shopping
    │       ├── application      # 애플리케이션 레이어, 서비스 클래스 포함
    │       ├── domain           # 핵심 도메인 모델 및 비즈니스 로직
    │       ├── infrastructure   # 설정, 영속성 레이어, 리포지토리 등
    │       └── presentation     # 프레젠테이션 레이어, 컨트롤러 및 DTO
    └── resources
        └── application.yml      # 메인 설정 파일
```

### 주요 디렉터리 및 파일

- **`application`**: `OrderCommandService`와 `MemberCommandService` 같은 서비스가 포함된 애플리케이션 레이어입니다. 고수준의 비즈니스 로직을 관리합니다.
- **`domain`**: 핵심 비즈니스 엔티티(예: `Member`, `Order`, `Item`)와 열거형(`OrderStatus`, `DeliveryStatus`)을 정의하며, 핵심 비즈니스 로직을 캡슐화합니다.
- **`infrastructure`**:
    - **`config`**: 데이터베이스 연결, 보안 등의 설정 파일을 포함합니다.
    - **`persistence`**:
        - `entity`: 데이터베이스 테이블에 매핑되는 JPA 엔티티 클래스가 있습니다.
        - `repository`: 엔티티에 대한 CRUD 작업을 처리하는 리포지토리 인터페이스입니다.
        - `mapper`: 도메인 모델과 엔티티 모델 간의 변환을 처리하는 매퍼입니다.
- **`presentation`**:
    - **`controller`**: REST 컨트롤러로 API 엔드포인트를 정의합니다.
    - **`request` 및 `response`**: 클라이언트와 서버 간의 데이터를 관리하는 요청 및 응답 DTO를 정의합니다.

## 도메인 개요

### 엔티티

- **Member**: `username` 및 `email` 등의 세부 정보를 가진 회원을 나타냅니다.
- **Order**: 회원이 주문한 내용으로, 배송 정보 및 주문 아이템 세부 정보를 포함합니다.
- **Item**: 주문할 수 있는 아이템을 나타냅니다.
- **Delivery**: 배송 정보로, 상태 및 주소를 포함합니다.

### 열거형

- **OrderStatus**: 주문의 상태를 정의합니다 (예: `CREATED`, `CANCELED`).
- **DeliveryStatus**: 배송의 상태를 정의합니다 (예: `READY`, `SHIPPED`).

## 애플리케이션 서비스

### OrderCommandService

주문 관련 명령을 처리하는 서비스로, 주문 생성과 주문 취소 기능을 담당합니다.

- **`order(Long memberId, Long itemId, int count)`**: 지정된 회원과 아이템으로 주문을 생성합니다.
- **`cancelOrder(Long orderId)`**: ID로 주문을 조회하여 취소합니다.

### MemberCommandService

회원 생성 및 이메일 업데이트와 같은 회원 관련 명령을 처리하는 서비스입니다.

- **`createMember(String username, String email)`**: 지정된 사용자 이름과 이메일로 새 회원을 생성합니다.
- **`updateMemberEmail(Long memberId, String newEmail)`**: 새로운 이메일 형식을 검증 후 회원의 이메일을 업데이트합니다.

## 인프라스트럭처 레이어

### 영속성

이 레이어는 리포지토리와 매퍼로 구성됩니다:

- **리포지토리**: `MemberJpaRepository`와 `OrderJpaRepository`와 같은 인터페이스는 JPA를 통해 엔티티에 대한 CRUD 작업을 처리합니다.
- **매퍼**: `MemberMapper`와 `OrderMapper`는 도메인 모델과 엔티티 모델 간의 변환을 처리하여 비즈니스 로직이 영속성 세부 사항과 분리되도록 지원합니다.

## 프레젠테이션 레이어

### 컨트롤러

`presentation` 패키지의 컨트롤러는 주문 및 회원 관리에 필요한 REST 엔드포인트를 정의합니다. 컨트롤러 레이어는 DTO를 사용하여 애플리케이션 레이어와 상호 작용하며 도메인 모델을 캡슐화합니다.

## 설정

- **`application.yml`**: 애플리케이션의 주요 설정 파일로, 데이터베이스 연결 및 기타 설정을 정의합니다.

## 애플리케이션 실행

애플리케이션을 실행하려면 다음 단계를 따르세요:

1. 리포지토리를 클론합니다.
2. 프로젝트 디렉터리로 이동합니다.
3. Gradle을 사용하여 애플리케이션을 실행합니다:

   ```bash
   ./gradlew bootRun
   ```

4. 기본 포트(보통 8080번)에서 애플리케이션이 시작됩니다. 설정은 `application.yml`에서 수정할 수 있습니다.