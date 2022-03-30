<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mvc.mvc1Study.servlet.domain.member.entity.Member" %>
<%@ page import="mvc.mvc1Study.servlet.domain.member.repository.MemberRepository" %>
<%--<% ~~~ %>자바 코드 실행--%>
<%
    // servlet의 request, response는 자동 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = Member.createMember().username(username).age(age).build();

    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%>></li>
    <li>username=<%=member.getUsername()%>></li>
    <li>age=<%=member.getAge()%>></li>
</ul>
</body>
</html>
