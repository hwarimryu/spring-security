# spring-security

### Spring security 기본 구성 요소
- 인증 필터
- 인증 관리자
- 인증 공급자 AuthenticationProvider
  - 인증 논리를 여기서 구현한다.
- 암호 인코더 PasswordEncoder
  - 암호를 인코딩한다.
  - 안호가 기존 인코딩과 일치하는지 확인한다.
  - Basic 인증 flow에 꼭 필요하다. 
- 사용자 세부 정보 서비스 UserDetailsService
  - 애플리케이션 내부 메모리에 기본 자격 증명을 등록하는 일을 한다.
  - 사용자 관리 책임을 여기서 구현한다.
- 보안 컨텍스트
  - 인증 프로세스 후 인증 데이터를 유지한다.


### 기본 인증 프로세스
1. 인증 필터가 요청을 가로챈다. 
   1. 인증 필터는 인증 요청을 인증 관리자에 위임하고 응답을 바탕으로 보안 컨텐스트를 구성한다.

2. 인증 관리자는 인증 논리를 구현한 인증 공급자를 이용해 인증을 처리한다.
3. 인증 공급자는 사용자 관리 책임을 구현한 사용자 세부 정보 서비스와 암호 관리를 구현한 암호 인코더를 인증 논리에 이용한다.
   1. 사용자 세부정보 서비스(UserDetailsService Contract 구현 객체)로 사용자를 찾고
   2. 암호 인코더로 암호를 검증한다.
4. 인증 결과가 필터에 반환된다.
5. 인증된 엔티티에 관한 세부 정보가 보안 컨텍스트에 저장된다.

## 기본 구성요소 재정의
:각 구성요소를 재정의할 때, configure 메서드를 override 하는 방법, bean으로 등록하는 방법들이 있지만, 두 가지 접근법을 혼합하기보다는 한가지로 통일하는게 이해하기 쉽고 깔끔하다. 
### UserDetailsService 재정의
#### 자체적으로 관리하는 자격 증명을 인증에 이용할 수 있다.

1. InMemoryUserDetailsManager 사용
- 메모리에 자격 증명을 저장해서 Spring Security가 요청을 인증할 때 이용할 수 있게 한다.
- 운영 단계 application을 위한 것은 아님.

### endpoint 권한 부여(SecurityFilterChain) 재정의

- WebSecurityConfigurerAdapter deprecated 되어 대신 SecurityFilterChain 빈으로 등록하는 방법 사용함.
  https://covenant.tistory.com/277
