# SAML & SCIM
SAML (Security Assertion Markup Language): Authentication
- IdP와 SP간의 인증 정보를 안전하게 교환하는 역할
    - IdP: Naver, Kakao 등
    - SP: Service Provider / AWS, GCP, KC 등
- 기술: XML 기반 인증 정보 전달
- 사용 환경: 웹 브라우저 기반
- 레거시 방식

SCIM (System for Cross-domain Identity Management) : Account Provider
- 사용자 프로비저닝 동기화에 사용되는 인증 프로토콜
- ex> AWS <-> MS AD 사용자 동기화


> SAML vs OIDC : XML 웹 기반 vs OAuth2.0 웹/앱 기반