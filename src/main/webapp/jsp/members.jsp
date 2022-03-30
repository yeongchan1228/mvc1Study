<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mvc.mvc1Study.servlet.domain.member.repository.MemberRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="mvc.mvc1Study.servlet.domain.member.entity.Member" %>
<%@ page import="java.util.ArrayList" %>
<%
    // servlet의 request, response는 자동 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();

    List<Member> members = memberRepository.findAll();

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write("    <tr>");
            out.write("     <td>" + member.getId() + "</td>");
            out.write("     <td>" + member.getUsername() + "</td>");
            out.write("     <td>" + member.getAge()  + "</td>");
            out.write("    </tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>