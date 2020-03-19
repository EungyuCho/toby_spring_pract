
<h1>메인으로 돌아가기 <a href="https://github.com/EungyuCho/toby_spring_pract">></a></h1>
<h2>4장 예외🧺</h2>
<ol>
    <li>
        <h4>예외처리의 종류와 특징</h4>
        <BlockQuote>
           자바에서 throw로 발생시킬 수 있는 예외는 3가지가 있어요!<br>
           같이 예외의 종류를 학습해봅시다!<br>
           <ol>
                <li>
                    Error(java.lang.Error)<br>
                    &nbsp 시스템에 비 정상적인 상황이 발생했을 경우에 사용되며, 주로 자바 VM에서 발생시키는 Error에요!<br> 그래서 어플리케이션에서는 이런 예외는 주로 사용하지 않아요.
                </li>
                <li>
                    Exception(체크예외)
                    &nbsp Error와 달리 어플리케이션 레벨에서 사용되며, RuntimeException을 상속하지않은 Exception 서브클래스들이 체크에외에요!<br>
                    체크예외는 예외적인 상황에 던져질 가능성이 있는 API에 명시적으로 예외처리를 강제하게 만들어뒀어요!
                </li>
                <li>
                    Exception(언체크 예외)
                    &nbsp RuntimeExeption 클래스를 상속한 서브클래스들로 체크예외와 달리 예외처리를 강제하지 않는 Exception이에요!<br>
                    피할 수 있지만 개발자의 부주의에 의해서 발생하도록 만든 것이 RuntimeException으로, 대표적으로 NullPointerException이나 IllegalArgumentException 등이 있어요!
                </li>
           </ol>
           </BlockQuote>
    </li>
    <li>
        <h4>예외처리 방법</h4>
        <BlockQuote>
           일반적인 예외처리 방법을 학습해봅시다!
           <ol>
                <li>
                    예외 복구<br>
                    &nbsp 예외 상황을 파악하고 문제를 해결한 후 정상 상태로 돌려놓는 방법이에요<br>
                    예시로 DB 서버에 접속하다가 접속이 잘 안되는경우에 재시도를 하도록 코드를 짜서 Retry 횟수를 넘기는 RetryFail에 관련된 Exception을 던지는 식으로 예외처리를 진행해요!
                </li>
                <li>
                    예외처리 회피<br>
                    &nbsp 예외를 자신이 처리하지않고 다른 오브젝트나 메소드가 예외를 대신 처리할 수 있도록 던져주는 방식이에요.<br>
                    예외를 회피하는 것은 예외를 복구하는 것처럼 의도가 분명해야해요.<br>
                    무책임하게 throw하는것은 예외블랙홀같은 상황을 야기하기때문이에요
                </li>
                <li>
                    예외전환<br>
                    &nbsp 발생된 예외를 catch해서 다른 예외로 전환하고나서 던지는 특징이 있는 예외처리 방법이에요<br>
                    예외전환의 목적은 보통 두 가지 목적이 있는데, 첫째는 의미를 더 구체화시키기 위해서 더 적합한 의미를 가진 예외로 변경하는것이에요.<br>
                    두번째는 예외를 처리하기 쉽고 단순하게 만들기 위해서 포장하는 것이에요.<br>
                    복구가 불가능한 Exception은 가능한 한 빨리 런타임 예외로 포장해서 던지게해서 다른 메소드를 작성할때 불필요한 throws 선언이 들어가지 않도록 할수있어요!
                    </li>
           </ol>
        </BlockQuote>
    </li>
    <li>
        <h4>기술에 독립적인 UserDao 만들기&nbsp&nbsp<a href="https://github.com/EungyuCho/toby_spring_pract/commit/e955a86d6b3d08e456c31026f97f80f1ace63fce">>></a></h4>
        <BlockQuote>
           UserDao 인터페이스 적용 및 Exception 테스트<br>
            &nbsp UserDao를 인터페이스를 통해 분리하고(jdbc, jpa, hibernate 확장대비) 
            테스트에서 add()메소드를 2번호출해서 DataAccessException 하위에있는 DuplicateKeyException예외가 정상적으로 호출되는지 확인해보자! 
        </BlockQuote>
    </li>    
</ol>

