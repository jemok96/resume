수정 중 <br>
## 자신의 이력서 작성기능과 공유하며 피드백 받을 수 있는 사이트<br>

# **[프로젝트 개요]**

| 항목  | 내용 |
| --- | --- |
| 프로젝트 소개 | 자신의 이력서를 작성하고 공유할 수 있는 웹 어플리케이션 |
| 개발 인원 | 총 2명(백엔드 2명) |
| 담당 역할 | 로그인,이메일 인증, 자기소개서, 공지사항, 자유게시판, 마이페이지 개발,  AWS RDS 및 S3 세팅, EC2 서버와 Docker를 통한 서버 배포  |


## 개발인원 <br>
총 2명(백엔드)<br>

## 아키텍쳐
<img  width=900 src=https://github.com/jemok96/resume/assets/91232870/49753c39-b96f-428b-98c5-2dc700ea4efd ><br>




## ERD
<img width="900" alt="DB table" src="https://github.com/jemok96/resume/assets/91232870/1b2f91f4-e543-446f-a20e-9de20b3dbe5c"><br>

## 기술 
---
**Frontend** `jquery` `Bootstrap` `Thyme-leaf`

---

**Backend**  `Java 11` `Spring boot 2.7.10` `Mybatis`

---

**Database** `MySQL`  `AWS S3`

---

**Deploy**  `AWS EC2` `AWS RDS`  `Docker`

---

**Test**  `PostMan` `Apache-jmeter` `Junit5`

---
## API
- **공지사항**
    
    
    | Method | URL Pattern | 설명 | Parameter |
    | --- | --- | --- | --- |
    | GET | /notices | 공지사항 전체 목록 |  |
    | GET | /notices/new | 공지사항 추가 화면 |  |
    | POST | /notices/new | 공지사항 추가 클릭 시 | {title,contents} |
    | GET | /notices/detail/{num} | 특정 공지사항 개시물 클릭 시 | {num} |
    | GET | /notices/{num} | 공지사항 수정 가능 화면  | {num} |
    | PATCH | /notices/{num} | 공지사항 수정 | {title,contents} |
    | DELETE | /notices/{num} | 공지사항 삭제 | {num} |
    
- **자기소개서**
    
    
    | 이름 | URL Pattern | 설명 | Parameter |
    | --- | --- | --- | --- |
    | GET | /selfintroduction | 자기소개서 메인 화면 |  |
    | GET | /selfintroduction/new | 자기소개서 추가 화면 |  |
    | POST | /selfintroduction | 자기소개서 저장 | {userid,title,contentes} |
    | GET | /selfintroduction/{rno} | 자기소개서  수정 화면 | {rno} |
    | PATCH | /selfintroduction/{rno} | 자기소개서 수정 | {rno} |
    | DELETE | /selfintroduction/{rno} | 자기소개서 삭제 | {rno} |
- **자유게시판**
    
    
    | Method | URL Pattern | 설명 | Parameter |
    | --- | --- | --- | --- |
    | GET | /board | 전체 게시판 조회 |  |
    | GET | /board/new | 게시판 작성 화면 |  |
    | POST | /board/new | 게시판 등록 |  |
    | GET | /board/detail/{num} | 특정 게시글 클릭 시 | {num} |
    | GET | /board/{num} | 특정 게시글 수정 삭제 가능 화면 | {num} |
    | PATCH | /board/{num} | 특정 게시글 수정 시 | {num,title,contents} |
    | DELETE | /board/{num} | 특정 게시글 삭제 시 | {num} |
- **로그인**
    
    
    | Method | URL Pattern | 설명 | Parameter |
    | --- | --- | --- | --- |
    | GET | /login | 로그인 화면 |  |
    | POST | /login | 로그인 인증 | {userid,password} |
    | GET | /logout | 로그아웃 |  |
    | GET | /login/search | 아이디찾기 |  |
    | POST | /login/search | 인증번호 발급 | {email} |
    | POST | /verify | 인증번호 확인 JSON | {userInput} |
    | GET | /login/search/detail | 회원 아이디  조회 |  |
    
- **마이페이지**
    
    
    | Method | URL Pattern | 설명 | Parameter |
    | --- | --- | --- | --- |
    | GET | /profiles/{userid} | 마이페이지 메인 화면 |  |
    | POST | /profiles/{userid} | 마이페이지 비밀번호 확인 | {userid,} |
    | POST | /profiles/checkpw | 마이페이지 비밀번호 입력시 DB에 있는 내용과 조회 비교  |  |
    | GET |  |  |  |
    | POST |  |  |  |
    | POST |  |  |  |
    | GET |  |  |  |
    
- **댓글**
    
    
    | Method | URL Pattern | 설명 | Parameter |
    | --- | --- | --- | --- |
    | GET | /comments/{num} | 게시판에 해당하는 댓글 조회 JSON형 |  |
    | POST | /comments/{num} | 게시판 번호에 해당하는 게시글에 댓글 추가 | {num,userId,contents} |
    | DELETE | /commnets/{commentNo} | 댓글 번호에 해당하는 댓글 삭제 | {commentNo} |
    -
## Docker 하면서 배운 점
docker images : 이미지 확인
docker ps  : 실행중인 container 확인
docker ps -a : 죽었던애들까지도 확인가능

docker  create -it --name mycon1 centos : docker 컨테이너안에 centos라는 운영체제를 독립적으로 생성
docker container[생략가능] start -ai mycon1 : mycon1컨테이너 실행해라 , -ai container안으로 들어가는거
ctrl + p +q 컨테이너에서 빠져나오기
docker container[생략가능] stop mycon1 : mycon1 중지
docker run -d --name mytomcat[name] tomcat[image] : 생성 image가 없다면 pull까지해옴
=>여기서 -d만붙였을때는 centos가 켜지자마자 죽어버리는데  -itd붙이면 안죽음
docker exec -it boring_goldwasser[containerName] /bin/bash : 실행중인 container로 들어가기

docker run -it --name myCentos --env DB_NAME=mydb centos
env : 환경변수 확인
echo $DB_NAME

docker run -it --name MyCentos3 --env-file env.file centos : env.file에 저장된 값으로 환경변수 설정

docker run -itd --name web01 -p 8001:80 nginx : port지정
docker run -dit --name mysql01 -e MYSQL_ROOT_PASSWORD=tiger -p 3306:3306 mysql : mysql은 -e rootpw필요함
mysql -u root -p : root로 로그인하기 비번은 위에서 설정한 tiger

docker cp web03.war web05:/usr/local/tomcat/webapps : dev폴더가서 실행하면 복사됨 신기하노
docker network create wp_net01 : 새로운 network 생성
docker network ls :목록

docker run --name mysql03 -d --net=wp_net01 --env-file db.txt mysql : --network 사용 하겠다
docker run --name wp01 -d --net=wp_net01 -p 8001:80 --env-file wp.txt wordpress

docker cp index.html apa01:/usr/local/apache2/htdocs/  : 현재 폴더에서 저 폴더로 복사
docker cp apa01:/usr/local/apache2/htdocs/index.html test.html : 저폴더에서 local(현재폴더)로 복사

docker volume ls
docker volume create dbfile

docker run --name mysql03 -d --net=wp_net01 -v dbfile:/var/lib/mysql --env-file db.txt mysql

docker run --name web09 -d -p 8010:80 httpd
docker commit web09 web : 위 설정으로 만든거를 image로 만들어줌

docker build -t newweb[내가만든 image이름] -f Dockerfile.txt . :  다른거 다 안 되고  이거로해야됨

docker login -i [id] + [pw]

먼저 docker hub가서 repository 만든 후에  docker build -t [image이름] dockerfile.txt .  : image이름은 내 hub repository image이름임
docker image tag [image이름] jemok96/mynewos:1.0 : tag지정하기

docker push jemok96/mynewos:1.0 : docker hub에 push하기

docker export myos01 > myos.tar : container 압축하기

docker compse : 시스템 구축과 관련된 명령어를 하나의 텍스트파일(정의파일)에 기재해 명령어 한번에 시스템 전체를 실행, 종료, 폐기까지 한번에
하도록 도와주는 도구 (JSON , YAML)으로 설정

docker-compose와 dockerfile의 차이점?
dockerfile : 이미지만 생성가능
docker-compose : docker run +network, volume 까지 가능

명령어
docker-compose up
docker-compose down
docker-compose up -d --scale www1=5  : 5개 띄움

docker-compose -f [파일이름] up -d  : -f는 yaml이 여러개 있을 때 파일이름 지정하려고 사용
docker compose -f [파일이름] down : 종료할 떄
