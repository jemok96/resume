## 자신의 이력서 작성기능과 공유하며 피드백 받을 수 있는 사이트

## 개발인원 
총 2명(백엔드)

## ERD
<img width="900" alt="DB table" src="https://github.com/jemok96/resume/assets/91232870/1b2f91f4-e543-446f-a20e-9de20b3dbe5c">

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
