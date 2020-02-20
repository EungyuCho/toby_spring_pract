
<h1>메인으로 돌아가기 <a href="https://github.com/EungyuCho/toby_spring_pract">></a></h1>
<h2>6장 AOP</h2>
<ol>
    우선 들어가기전에 AOP의 목적을 파악하고 학습하는게 중요할거같아!<br>
    AOP는 어플리케이션 전체에 걸쳐 사용되는 기술을 재사용하기위해서 나온 핵심개념이야.<br>
    AOP(Aspect-Oriented Programming)는 관점 지향 프로그래밍으로 번역할수있는데, 관점을 달리해서 핵심 기능이 중점이 아니라 부가기능 또는 인프라를 모듈화하는데 목적을 두고있어!
    AOP의 장점은 어플리케이션에 흩어진 공통기능이 하나의 장소에서 관리되고, 다른 서비스 모듈들이 본인의 목적에만 집중을 할 수 있는 효과가 있어! 같이 구현해보면서 AOP에 대해서 알아보자!
    <li>
        <h4>트랜잭션 기능의 분리  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/e4a62818ba39aa3598cd3078b1ab1a4a714a81db#diff-df0e9cc892a31305ec28819bc6e08e6a">>></a></h4>
        <BlockQuote>
           이전에 작성했던 트랜잭션 코드는 제법 깔끔해진거같지만 아직 Service로직에 try catch문이 선언되어 있어, UserService의 DI적용으로 트랜잭션에 대한 기능을 Service에서 분리해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>UserDao Mock Object화를 통한 테스트  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/92f13e11f97480f0816bbf9249d025d30a987546">>></a></h4>
        <BlockQuote>
           현재 Service Test는 DB에 의존하고있어 Mock이라는 Object Class로 만들어서 의존관계를 없애보자!
        </BlockQuote>
    </li>
    <li>
        <h4>모키토 프레임워크와 프록시 패턴  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/a28838f49798c945776a576a258a9bdd664c1ee1">>></a></h4>
        <BlockQuote>
            <ol>
                <li>
                    모키토는 Mock Class를 미리 만들 필요가 없이 간단히 메소드 호출으로 Mock Object를 생성 할 수 있는 프레임워크야! UserDao를 모키토를 통해서 테스트적용해보자!
                </li>
                <li>
                    프록시 패턴은 실제 기능을 수행하는 객체 대신 가상의 객체를 사용해 로직의 흐름을 제어하는 디자인 패턴이에요. 
                </li>
                <li>
                    데코레이터 패턴은 부가적인 기능을 추가해주기위해 프록시 패턴을 사용하는 패턴이야
                </li> 
                <li>
                    리플렉션
                </li>
            </ol>
        </BlockQuote>
    </li>    
</ol>

