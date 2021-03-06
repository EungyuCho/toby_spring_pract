<%@ page contentType="text/html; charset=UTF-8"%>
<%
		request.setCharacterEncoding("UTF-8");
		
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>1장 오브젝트와 의존관계</title>
	</head>
	<body>
		<h3>용어 정리</h3>
		<hr>
		<h5>빈(bean)</h5>
		<p>빈이란 스프링이 IoC방식으로 관리하는 오브젝트라는 뜻이다.</p>
		<p>스프링이 직접 생성과 제어를 담당하는 오브젝트만을 빚빈으로 부른다.</p>
		<hr>
		<h5>빈 팩토리(bean factory)</h5>
		<p>스프링의 IoC를 담당하는 핵심 컨테이너를 가리킨다.</p>
		<p>빈을 등록하고 생성하고 조회하고 돌려주고 부가적인 빈들을 관리한다.</p>
		<p>보통은 확장된 개념인 Application context를 사용한다.</p>
		<p>BeanFactory 인터페이스에는 getBean()과 같은 메소드가 정의되어있다.</p>
		<hr>
		<h5>어플리케이션 컨텍스트(Application context)</h5>
		<p>빈 팩토리를 확장한 IoC컨테이너이다. 기본적인 빈의 관리기능은 Bean factory와 동일하다</p>
		<p>빈 팩토리라고 부를 때에는 주로 빈의 생성과 제어의 관점에서 이야기하는것이며,</p>
		<p>어플리케이션 컨텍스트라고 할 때는 스프링이 제공하는 어플리케이션 지원기능을 모두 포함해서 이야기하는것이다.</p>
		<p>어플리케이션 컨텍스트는 당연히 BeanFactory를 상속받은 인터페이스이다</p>
		<hr>
		<h5>설정정보/설정 메타정보(configuration metadata)</h5>
		<p>스프링의 설정정보란 어플리케이션 컨텍스트 또는 빈 팩토리가 IoC를 적용하기위해 사용하는 메타정보를 말한다.</p>
		<p>스프링의 설정정보는 컨테이너에 어떤 기능을 세팅하거나 조정할때에도 사용하지만</p>
		<p>그보다는 IoC컨테이너에 관리되는 어플리케이션 오브젝트를 생성하고 구성하는데 사용된다.</p>
		<hr>
		<h5>컨테이너 또는 IoC컨테이너(IoC container)</h5>
		<p>IoC방식으로 빈을 관리한다는 의미에서 어플리케이션 컨텍스트나 빈 팩토리를 컨테이너 또는 IoC컨테이너라고도 한다</p>
		<p>컨테이너라는 말 자체가 IoC의 개념을 담고있어서 어플리케이션 컨텍스트 대신 스프링 컨테이너 또는 컨테이너라고 부르는걸 선호하는 사람들도 있다.</p>
		<p>어플리케이션 컨텍스트는 그 자체로 ApplicationContext인터페이스를 구현한 오브젝트를 가리키기도 하는데</p>
		<p>어플리케이션 컨텍스트 오브젝트는 하나의 어플리케이션에서 보통 여러 개가 만들어져 사용된다. 이를 통틀어서 스프링컨테이너라고 부를수있다.</p>
		<hr>
		<h5>스프링 프레임워크</h5>
		<p>스프링 프레임워크는 IoC컨테이너, 어플리케이션 컨텍스트를 포함해서 스프링이 제공하는 모든 기능을 통틀어 말할때 사용한다.</p>
		 
	</body>
</html>