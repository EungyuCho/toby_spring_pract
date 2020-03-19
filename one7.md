
<h1>메인으로 돌아가기 <a href="https://github.com/EungyuCho/toby_spring_pract">></a></h1>
<h2>7장 스프링 핵심 기술의 응용🔧</h2>
<ol>
    지금까지 스프링의 3대 핵심기술인 Ioc/DI, 서비스추상화, AOP를 간단히 살펴봤어.<br>
    7장에서는 앞서 살펴봤던 3가지 기술을 어플리케이션 개발에 활용해서 새로운 기능을 만들어보고 이를 통해 스프링의 개발철학과 추구하는 가치, 스프링 사용자에게 요구되는것을 살펴보자!
    <li>
        <h4>스프링 핵심 기술의 응용 : XML 설정을 이용한 분리(개별SQL 프로퍼티방식)  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/69d5f80329983670ec9717b6de6dee99582fedf9">>></a></h4>
        <BlockQuote>
           UserDao에서 아직 SQL과 SQL이 분리가 안되어있어 이제 SQL을 XML을 통해서 분리해보자! 우선 sqlAdd메소드만 개별적으로 적용해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>XML설정을 이용한 분리(SQL MAP방식)  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/92f13e11f97480f0816bbf9249d025d30a987546">>></a></h4>
        <BlockQuote>
           SQL이 많아지면 프로퍼티 설정이 많아지니 컬렉션으로 담아둬보자<br>
           Map방식을 이용해 xml프로퍼티를 map, entry를 세팅한 후 테스트에 이상이 없는지 확인하자!
        </BlockQuote>
    </li>
    <li>
        <h4>JAXB 와 Bean 후처리기  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/3387ae2b677f05a5e68d3d46212db9e2c24633bb">>></a></h4>
       <BlockQuote>
           SQL을 분리하긴 했지만 Bean태그 안에서도 SQL을 분리해보자<br>
           SQL전용 XML을 만들고 읽어오는 방법 중 JAXB(Java Architecture XML Binding)를 이용해서 구현하고 빈 후처리기를 이용해서 외부 DI를 적용해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>인터페이스 분리와 자기참조 빈  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/f806a290897779d0412f69ff469482cf95907596">>></a></h4>
        <BlockQuote>
           SQL을 읽어오는데 성공했으니 이제 인터페이스를 다시 분리해보자<br>
           SQL을 읽는 Reader와 SQL을 생성하고 저장하는 Registry인터페이스를 분리해서 적용해보고 자기참조 빈을 설정해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>JAXB 구현 테스트  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/c05396c9120a5504e01eb3003ce3ee20982f3629">>></a></h4>
        <BlockQuote>
           앞서 만들었던 JAXB테스트를 OXM(Object-XML Mapping) 서비스 추상화 인터페이스를 통해서 Unmashaller를 빈으로 등록하여 테스트 한 후 결과에 이상없는지 확인해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>OXM 추상화  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/3ddd2908e04dcadf60643b73155b6df4297f3714">>></a></h4>
        <BlockQuote>
           OXM기능을 추상화하는 SQLService를 만들어보자<br>
           OXM Unmashaller를 사용하도록 Service에 고정해서 SQL의 Reader를 OXM로 제한할 수 있어!
        </BlockQuote>
    </li>
    <li>
        <h4>리소스 추상화<a href="https://github.com/EungyuCho/toby_spring_pract/commit/82fd5b6cdf8b04577cb9a554110400882621bf1b">>></a></h4>
        <BlockQuote>
            지금까지 만든 OxmSqlReader와 XmlSqlReader는 파일이 클래스패스에 한정되는 단점이 있어 스프링의 Resource 추상화 인터페이스로 클래스패스뿐만 아니라 파일 시스템, HTTP 프로토콜을 이용해서도 로딩할 수 있게 확장해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>ConcurrentHashMap을 이용한 수정가능한 SQL 레지스트리 구현 및 테스트<a href="https://github.com/EungyuCho/toby_spring_pract/commit/a22bdc9fa54185e5cabbd084e774d28759f2f189">>></a></h4>
        <BlockQuote>
           지금까지 써왔던 HashMapRegistry는 JDK의 HashMap을 사용하는데 멀티스레드 환경에서 취약한 단점이 있어<br>
            synchronizedMap을 이용해 동기화 할 수는있지만 고성능 서비스에 문제가 있을 수 있으니 동기화된 해시데이터 조작에 최적화된 ConcurrentHashMap을 이용해서 Registry를 구현하고 테스트해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>내장형 DB 빌더를 이용한 내장형 데이터베이스 구현 및 테스트<a href="https://github.com/EungyuCho/toby_spring_pract/commit/8287415e4d291981b60f8b3d6b849ac01765ad23">>></a></h4>
        <BlockQuote>
           이번엔 ConcurrentHashMap 대신 내장형 DB(HSQL)를 이용해서 SQL을 저장하고 수정해보자!
        </BlockQuote>
    </li>
    <li>
        <h4>내장형 DB를 이용한 SqlRegistry 생성 및 테스트 코드 재사용 리팩터링 <a href="https://github.com/EungyuCho/toby_spring_pract/commit/18c0652d58b727799949e52e406bbefc6c2332b0">>></a></h4>
        <BlockQuote>
           내장형 DB를 이용해서 SQLRegistry를 만들고 테스트를 진행해보자<br>
           테스트는 ConcurrentHashMap과 같이 단위테스트를 만들면 되는데 둘다 어떤 Registry를 제외하고는 달라질 부분이 없으니 테스트코드를 재사용 할 수 있도록 Abstract클래스로 만들어서 재활용해보자!
        </BlockQuote>
    </li>
    <li>
        <h4> SQL 수정에 대한 트랜잭션 적용 테스트  <a href="https://github.com/EungyuCho/toby_spring_pract/commit/02849b6ba9e89f9ac79d1719a23c6ac2c294417d">>></a></h4>
        <BlockQuote>
           SQL수정에 대한 테스트를 해보자<br>
           Map 형태로 업데이트를 진행해서 없는 Key에 대한 Update가 발생했을 때 Rollback이 정상적으로 되었는지 검증해보자!
        </BlockQuote>
    </li>
</ol>

