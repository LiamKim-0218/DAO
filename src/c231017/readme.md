## 생각 순서?
1. CPU || GPU
2. GPU || CPU
3. 메인보드 << CPU와 GPU의 호환성
4. Power || 케이스
5. RAM << 종류에 따라서 달라지긴 한다.
6. HDD/SSD 하드디스크
7. 키보드 모니터 마우스
8. 사운드 카드

### 게이밍
1. GPU

### 사무용
1. CPU || RAM

# 디자인 패턴(약 20가지)

## 생성 패턴
- 싱글톤
- 팩토리 메소드 

## 구조 패턴


## 행동 패턴
- 전략 패턴
- 템플릿 메소드
- 상태(State)

# MVC
- MVC 패턴, MVC 모델, 프로그래밍에 대한 디자인 패턴,
소프트웨어 공학에서의 디자인 패턴


## M
- Model => DB, FileServer
- 저장한다.
- DB
  - 코드를 알고있나?
  - 어떻게 보여질지 알고있나?
  - 사용자가 입력한 데이터 저장 안하는 거 있나?
- 조건
  - 사용자가 사용(편집)하길 원하는 모든 데이터를 저장해야한다.
  - 뷰나 컨트롤러를 모른다. 정보를 알지 못한다.
  - 바뀌면 알려야한다. => 컨트롤러, 뷰
  

## V
- View => Front, HTML
- 보여준다.
- 조건
  - 모델이나 컨트롤러를 모른다. 정보를 알지 못한다.
  - 모델에 저장된 데이터를 저장하면 안된다.
  - 바뀌면 알려야한다. => 컨트롤러
  

## C
- Controller => Back, Front
- 조작한다.
- 조건
  - 다 알아야한다.
  - 모니터링해야한다.
  
# MVP

## p  
- Presenter << 진행자

## MVVM

- Model View View Model
- ViewModel
- React << 프레임워크가 아닌 라이브러리 << 뷰
  - Redux 
  - Controller?? << BackEnd
  
# Test
- 단위 테스트 : Unit Test
- JUnit : 테스트 프레임워크

## TDD : Test Driven Development
- 테스트 주도 개발






