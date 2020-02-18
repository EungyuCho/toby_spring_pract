
<h1>메인으로 돌아가기 <a href="https://github.com/EungyuCho/toby_spring_pract">></a></h1>
<h2>5장 서비스 추상화</h2>
<ol>
    <li>
        <h4>사용자 레벨 관리 기능 추가해보기  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/6aa2853e437060e9d5f835409a73044653439a3a">>></a></h4>
        <BlockQuote>
           Enum을 이용하여 사용자 레벨을 정의하고 테스트해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>레벨 업그레이드 기능을 구현하고 테스트해보기  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/c319fce17e3dc3c0c41fcd9f4dbc6c8a1c45eb36">>></a></h4>
        <BlockQuote>
           UserDao를 UserService에 DI 해주고, User Level Upgrade 기능을 만들어 테스트 시 Level이 올라갔는지 테스트해보기!
        </BlockQuote>
    </li>
    <li>
        <h4>User 업그레이드 기능 리팩토링<a href="https://github.com/EungyuCho/toby_spring_pract/commit/a0ee4b02abd1c33ea8d1bc0a20c3bd2f1b1b8927">>></a></h4>
        <BlockQuote>
            업그레이드 기능을 리팩터링 해봐요!<br>
            명시적으로 선언된 수는 Const로 정의해주고, 복잡했던 if, else문을 리팩터링해봐요!
        </BlockQuote>
    </li>    
    <li>
        <h4>User 업그레이드 정책을 추상화해봐요!<a href="https://github.com/EungyuCho/toby_spring_pract/commit/7cfb99cf27efbaaafcbdd116479796568f4c60fb">>></a></h4>
        <BlockQuote>
            유저 업그레이드 정책을 interface로 추상화해봐요!<br>
        </BlockQuote>
    </li> 
    <li>
        <h4>유저 레벨 업그레이드의 경계설정을 해봐요! <a href="https://github.com/EungyuCho/toby_spring_pract/commit/4e7ca200544cc5d596da65c03c4516f514e7f1bb">>></a></h4>
        <BlockQuote>
            현재 업그레이드 소스는 도중 에러가 발생해도 이전결과들이 rollback안되는 상태인데 이걸 고쳐봐요!<br>
        </BlockQuote>
    </li> 
    <li>
        <h4>트랜잭션 API간 의존관계를 해결해봐요!<a href="https://github.com/EungyuCho/toby_spring_pract/commit/bcb1f30342aaf1fbf0eaacb31875a7d94555396f">>></a></h4>
        <BlockQuote>
            현재 JDBC에 종속적인 코드를 해결하기위해 JTA(Java Tranaction API)를 사용해봐요!<br>
        </BlockQuote>
    </li>    
    <li>
        <h4>이메일 서비스를 추상화해봐요!<a href="https://github.com/EungyuCho/toby_spring_pract/commit/1e39862cc66f39eb5d7192b462fd0e8360e9c946">>></a></h4>
        <BlockQuote>
            JavaMail 서비스를 작성하고 추상화해봐요!<br>
        </BlockQuote>
    </li>    
    <li>
        <h4>Mock Object를 이용한 테스트로 변경해봐요!<a href="https://github.com/EungyuCho/toby_spring_pract/commit/a5cb506e7fbc40bdd22d97e941ca24aca4986afe">>></a></h4>
        <BlockQuote>
            Mock Object를 이용해서 메일 발송 대상을 검증해봐요!<br>
        </BlockQuote>
    </li>    
</ol>

