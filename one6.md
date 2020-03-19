
<h1>메인으로 돌아가기 <a href="https://github.com/EungyuCho/toby_spring_pract">></a></h1>
<h2>6장 AOP🙉</h2>
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
                    모키토는 Mock Class를 미리 만들 필요가 없이 
                    간단히 메소드 호출으로 Mock Object(테스트를 위한 객체)를 간편하게 생성 할 수 있는 프레임워크야! UserDao를 모키토를 통해서 테스트적용해보자!
                </li>
                <li>
                    프록시, 데코레이터, 리플렉션을 이용해 다이나믹 프록시를 이용한 간단한 클래스를 만들어보자!
                    <BlockQuote>
                        <ol>
                            ※사용되는 디자인 패턴
                            <li>
                                프록시 패턴은 실제 기능을 수행하는 객체 대신 가상의 객체를 사용해 로직의 흐름을 제어하는 디자인 패턴
                            </li>
                            <li>
                                데코레이터 패턴은 부가적인 기능을 추가해주기위해 프록시 패턴을 사용하는 패턴
                            </li>
                            <li>
                                리플렉션은 객체를 통해 클래스의 정보를 분석해내는 프로그램 기법
                            </li>
                        </ol>
                    </BlockQuote>
                </li>
            </ol>
        </BlockQuote>
    <li>
        <h4>트랜잭션 FactoryBean을 이용한 다이나믹 프록시 설정 및 테스트  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/818ea108f76266ddb86b48dec12184c1892ad209">>></a></h4>
        <BlockQuote>
           UserServiceTx를 다이나믹 프록시 방식으로 변경해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>트랜잭션 FactoryBean을 이용한 다이나믹 프록시 설정 및 테스트  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/818ea108f76266ddb86b48dec12184c1892ad209">>></a></h4>
        <BlockQuote>
           UserServiceTx를 다이나믹 프록시 방식으로 변경해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>Advisor를 통한 Proxy 지정 Method 부가기능 적용  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/96a0f2888e894a6c87188c39f9b171b11ee35dbe">>></a></h4>
        <BlockQuote>
           Advisor를 이용해 원하는 대상에만 기능을 적용해보고 테스트 해보자!
           <BlockQuote>
            <h3>Advisor란?</h3>
            Pointcut(메소드 선정 알고리즘) + Advice(부가기능) = Advisor<br>
           </BlockQuote>
        </BlockQuote>
    </li>
    <li>
        <h4>트랜잭션어드바이스 설정 및 테스트<a href="https://github.com/EungyuCho/toby_spring_pract/commit/62cba1dc389600e5ee6cbf1fdfeba641b8960501">>></a></h4>
        <BlockQuote>
            이어서 트랜잭션기능에도 Advisor를 설정해보고 테스트까지 진행해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>포인트컷 ClassName 적용테스트(Hello클래스 이용)<a href="https://github.com/EungyuCho/toby_spring_pract/commit/49d3b1f5cdd3db58a02085ac5ee19394dba7a55c">>></a></h4>
        <BlockQuote>
           앞서 적용한 NameMatchMethodPointcut은 클래스 필터 기능이 없으니 클래스 필터기능을 내부 익명 클래스 방식으로 구현하고 테스트해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>자동 프록시 생성을 이용한 테스트<a href="https://github.com/EungyuCho/toby_spring_pract/commit/7138199e4f253298078c7f381959b703cd592437">>></a></h4>
        <BlockQuote>
           DI, 템플릿/콜백 패턴, 서비스 추상화까지 완벽히 적용되었지만 아직 문제인 부분이 보이는데 부가기능이 설정 적용이 필요한 타겟 오브젝트에 비슷한 내용의 ProxyFactoryBean의 설정정보가들어가는데 이 중복을 자동 프록시 생성을 이용해 제거해보고 테스트까지 마쳐보자!
        </BlockQuote>
    </li>
    <li>
        <h4>AspectJ를 이용한 포인트컷 테스트 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/72c002fb8a5b1bd1f5af0d4766ead19325afb8ec">>></a></h4>
        <BlockQuote>
           AspectJ는 포인트컷 표현식을 이용해서 한 번에 접근제한자, 클래스타입, 메소드, 리턴타입, 예외타입을 설정할 수 있어<br>
           테스트용 클래스를 만들고 다양한 표현식으로 선정 범위를 변경해가면서 AspectJ 표현식을 익혀보자!
        </BlockQuote>
    </li>
    <li>
        <h4> tx네임스페이스를 활용한 트랙잭션 인터셉터설정  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/0da3254f2f062e4834ee97d5038104d31bf9caa5">>></a></h4>
        <BlockQuote>
           트랜잭션의 동작방식에 영향을 줄 수 있는 4가지 속성이 있어<br>
            <BlockQuote>
                <ol>
                    <h3><li>
                        트랜잭션 전파(transaction propagation)</h3>
                        이미 진행중인 트랜잭션이 있을 때 어떻게 진행할것인지 설정해줄수있어
                    </li>
                    <h3><li>
                        격리수준(isolation level)</h3>
                        모든 트랜잭션이 순차적으로 진행하면 작업이 안전하겠지만 그러면 성능 문제가 있기때문에 격리수준을 조정해서 가능한 많은 트랜잭션의 가용범위를 늘리는데 목적이있어
                    </li>
                    <h3><li>
                        제한시간(timeout)</h3>
                        트랜잭션이 수행하는 제한시간을 설정할수있어
                    </li>
                    <h3><li>
                        읽기 전용(read only)</h3>
                        읽기전용의 경우에는 트랜잭션내에서 데이터를 조작하는 시도를 막아줄 수 있어
                    </li>
                </ol>
            </BlockQuote>
            이러한 4가지 속성을 적용하기위해 스프링이 편리하게 지원하는 TransactionInterceptor를 tx 네임스페이스를 통해 적용해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>트랜잭션 readOnly 속성 테스트  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/cc798b9a5c389f6061844ef367b7272ffeb9dee9">>></a></h4>
        <BlockQuote>
           tx 네임스페이스를 통해 ReadOnly를 설정한 후 testUserService에 예외상황을 세팅하고 실제로 테스트 시 예외를 던지는지 확인해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>선언형트랜잭션 적용과 테스트 트랜잭션 애노테이션 정리  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/cfc95291146a9996391fb36c08fcbe5bccd447bb">>></a></h4>
        <BlockQuote>
           어노테이션을 이용해서 트랜잭션 기능을 설정해보자<br>
           Transactional 어노테이션을 이용해서 인터페이스나 클래스 메소드에 트랜잭션기능을 부여할 수 있어!
        </BlockQuote>
    </li>
</ol>

