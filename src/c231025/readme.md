# VPN

- Virtual Private Network
- 우리 컴퓨터 -> VPN 서버 -> 다른 웹페이지 요청
- VPN이 받고 -> 우리 컴퓨터

# Proxy
- 반대로 다른 곳에서 우리한테 보내준 걸 다른 웹페이지,
  내부적으로 다른 port로 요청을 보내 처리하는 것.
- "http://localhost" -> naver 출력\

# AWS
- 컴퓨터를 몇대 둘까? 3대? 1대? 프로젝트 개수만큼?
- 프로젝트가 크지 않다, 무겁지 않다 -> 1대
- 1대의 컴퓨터 안에서 프로젝트를 몇개 배포할 수 있을까?
  => 몇개든 성능만 된다면
  	 - Java : 80port(http), Node.js : 8081, React : 3000, GoLang : 8082
  	 => 내가 배포하는건 https : 443
	  	- 443/java => Java 프로젝트로 연결, 
	  	  443/node => Node.js 프로젝트로 연결,
	  	  443/react => react 프로젝트로 연결,
	  	  443/go => GoLang 프로젝트로 연결.
  	 	- apache : 443/java <-> java : 80 << 이때 사용하는게 proxy가 된다. | reverse proxy
  	 	  => .conf
  	 	- Cross Origin Resources Sharing
  	 	