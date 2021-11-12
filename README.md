# starbooks_project
✔⭐ 로그인, 리뷰, 좋아요, 도서 조회 기능이 있는 도서 홈페이지
+ Star 팀원들과의 첫 프로젝트
+ 프론트엔드와 백엔드를 분리하여 개발(협업)을 경험해보는 것을 중점으로 둠

## ⭐Star Team
+ 현재기준 팀 구성(5명)
+ 프론트엔드(2명) : 신건우, 이세민
+ 안드로이드(1명) : 진혜윤
+ 백엔드(1명) : __임희상__
+ 데이터(1명) : 박유나
<br/><br/>

## 개요
+ 명칭 : starbooks_project
+ 개발 인원 : 프론트(2명), 안드로이드(1명), 백엔드(1명, 임희상)
+ 서비스 기능 : 서적 조회(카테고리별), 회원가입 및 로그인, 리뷰(작성, 조회, 수정, 삭제), 좋아요(조회, 삽입, 삭제)
+ 개발 환경 : Intellij, Springboot 2.5.4, Java 11, Mysql 8.0, Jpa(Spring Data JPA), QueryDsl, SpringSecurity
+ 형상 관리 툴 : git
+ 협업 툴 : notion
+ 시연 영상 : 추후 추가 예정
<br/><br/>

## 프로젝트 특징
+ Vue.js, Spring Framework 기반
+ 사용자가 해당 서적을 좋아요(찜)하거나, 서적에 대한 리뷰를 남기고, 리뷰와 좋아요를 확인할 수 있는 서비스
+ 프론트엔드와 백엔드를 분리하여 개발 진행
  + 프론트 : AWS S3 정적 호스팅
  + 백엔드 : AWS (EC2 서버 + RDS 서버) 호스팅
  + 빌드 후, S3와 EC2연동
+ 초기 더미데이터는 서적 홈페이지 크롤링
+ 로그인 처리는 Jwt Token방식을 이용한 SpringSecurity를 통해 구현
+ RestApi 방식으로 CRUD 구현
  + 회원가입 및 로그인 기능
  + DB데이터 기반으로 서적에 대한 전체, 좋아요, 리뷰(별점) 순 조회기능
  + 서적에 별점부여 및 리뷰 작성, 조회, 수정, 삭제기능
  + 서적에 좋아요 여부 조회(로그인/비로그인), 좋아요 하기, 좋아요 취소 기능
<br/><br/>

## API 설계
![api1](https://user-images.githubusercontent.com/87533189/141422009-92e102d4-2d6b-402e-b671-f7982e0ad95d.PNG)
![api2](https://user-images.githubusercontent.com/87533189/141422013-dcbc4be5-8194-4500-8297-72b2f86bc3a6.PNG)
![api3](https://user-images.githubusercontent.com/87533189/141422014-069df447-ae70-4895-86cf-e66a102825c7.PNG)
![api4](https://user-images.githubusercontent.com/87533189/141422016-6bfb3c16-e776-4b30-9152-d9f7353fc9a5.PNG)
<br/><br/>

## 프로젝트를 통해 공부한 것들
+ Spring을 이용한 RestApi방식 개발
+ AWS S3, EC2, RDS 호스팅, 리눅스 명령어
+ Jwt Token을 이용한 SpringSecurity 로그인 처리
+ 페이징 처리(QueryDsl)
+ JPA 아키텍쳐
+ Spring Data JPA 경험
+ Spring exception 아키텍쳐(각 Api 별로 정의)
<br/><br/>

## 개발 타임라인
![타임라인1](https://user-images.githubusercontent.com/87533189/141361604-ad36a362-5bc2-4701-b028-d023250ceb81.PNG)
![타임라인2](https://user-images.githubusercontent.com/87533189/141361618-8a9ac8fa-8727-4224-8845-097c6da612bf.PNG)
<br/><br/>


### 만났던 에러를 해결하는데 도움을 얻은 곳
+ QueryDsl 버전문제
[블로그 주소](https://dev-jo.tistory.com/38?category=947368)


