
<h1>메인으로 돌아가기 <a href="https://github.com/EungyuCho/toby_spring_pract">></a></h1>
<h2>1장 오브젝트와 의존관계</h2>
<ol>
    <li>
        <a href="https://github.com/EungyuCho/toby_spring_pract/commit/be04e8e5a559669ed58fff9861bc5afe71cac4d3">초난감 DAO</a>
        <BlockQuote>
            - DAO란? <br>
            &nbsp&nbsp&nbspDB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트!<br/>
            - jdbc api를 이용한 간단한 유저 정보 조회 DAO를 만들어보자! <br/>  
            <BlockQuote>
                <h3>jdbc을 이용하는 작업의 순서</h3>
                <ol>
                    <li>
                        DB연결을 위한 Connection을 가져오기
                    </li>
                    <li>
                        SQL(쿼리문)을 담은 Statement 또는 PreparedStatement를 만들고
                    </li>
                    <li>
                        만들어진 Statement를 실행!
                    </li>
                    <li>
                        조회의 경우 SQL 쿼리의 실행 결과를 ResultSet으로 받아서 정보를 저장할 오브젝트에 옮겨줌
                    </li>
                    <li>
                        작업중에 생성된 Connection, Statement, ResultSet 같은 리소스는 작업을 마친 후 반드시 닫아주기!
                    </li>
                    <li>
                        JDBC API가 만들어내는 예외(Exception)을 잡아서 직접 처리하거나, 메소드에 throws 를 선언해서 예외 시 메소드 밖으로 던지게 한다!
                    </li>
                </ol>
            </BlockQuote>
            - mysql을 기반으로 테스트 하였으며 User테이블을 생성했음!<br>
            - UserDao 클래스가 데이터 등록과 출력이 문제없는지 확인!<br>
        </BlockQuote>
    </li>
    <li>
        DAO의 분리
        <BlockQuote>
            - 중복되는 Connection을 Method로 추출해줍니다. <a href="https://github.com/EungyuCho/toby_spring_pract/commit/3e86d604b326304d0567fe68853bf9e60c597479">>></a><br>
            - Factory Method를 이용한 Connection 분리 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/3cd54c99fa286c32a8862ac0392788231b9226cb">>></a><br>
            <BlockQuote>
                Factory Method란?<br>
                &nbsp상속을 통해 기능을 확장하게 하는 디자인 패턴으로 슈퍼클래스 코드에서는 서브클래스에서 구현할 메소드를 호출해서 필요한 타입의 Object를 가져와 사용한다.<br> 서브클래스에서 Object 생성방법과 클래스를 결정할 수 있도록 미리 정의해둔 메소드를 Factory Method라고 하며 이방식을 통해 Object의 생성방법을 나머지 로직, 슈퍼클래스에서 독립시키는 방법을 Factory Method 패턴이라고 한다.<br>
            - Interface 도입 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/a91ca1766d9b752e5a06d0acba029351eb3476ce">>></a><br>
               &nbsp&nbsp추상화를 도입하기 위해 가장 유용한 도구인 Interface를 사용해서 Connection을 분리해보자!<br>
            - 관계설정 책임의 분리 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/11466d26f01a50d113e9f0222bf9f754948040c7">>></a><br>
            UserDao의 생성자에서 ConnectionMaker를 의존성 주입(DI)을 받아서 책임을 주입해주는 Client에게 넘겨줄수있다!<br>
            - Object Factory를 통한 책임 분리 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/2845cfd6eb2e9c5c760a8e2b4901be74288a11d5">>></a><br>
            &nbspFactory를 사용해서 Client에게 Connection의 책임을 넘기는게아니라 Factory에게 UserDao의 생성과 ConnectionMaker책임을 넘겨준다!<br>
            - IOC 컨테이너 용어 정리와 어노테이션 기반 ApplicationContext호출 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/c6690362ac08a751f86180c5c1f410704ff87726">>></a><br>
            - 카운터 추가(DAO가 얼마나 DB를 사용했지?) <a href="https://github.com/EungyuCho/toby_spring_pract/commit/9493c1f2ca822dbe72ea5bbb882832ff9a2ea853">>></a><br>
            - 카운터 테스트 클래스 구현 (DAO가 얼마나 DB를 사용했지?) <a href="https://github.com/EungyuCho/toby_spring_pract/commit/273f2b4349c816837307bca1adf7e16373a1905f">>></a><br>
            - 수정자 메소드를 이용한 주입 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/46704f7b1541ecc9c76ffa4f01dabf1d7d440461">>></a><br>
            - XML을 이용한 ApplicationContext 설정<a href="https://github.com/EungyuCho/toby_spring_pract/commit/0cea2600b830d02b8ac74b51423d2afbc06dcce6">>></a><br>
            - dataSource를 이용한 connectionMaker refactoring<a href="https://github.com/EungyuCho/toby_spring_pract/commit/c5bd6c0f13d6239ff62a908bc0879d51bdc36453">>></a><br>
            &nbsp자바에서 DB Connection을 가져오는 오브젝트의 기능을 추상화해서 비슷한 용도로 사용할 수 있게 만들어진 DataSource를 사용해서 refactoring! XML 연결 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/72cbb0ed459a6e9bcfb1dc0298ed48e7d5b3781a">>></a><br>
        </BlockQuote>
    </li>
   
</ol>

