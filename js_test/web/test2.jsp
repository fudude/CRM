<%--
  Created by IntelliJ IDEA.
  User: 杜春明
  Date: 2021/4/8
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>myTitle</title>
    <script src="js/jquery-3.6.0.js"></script>
    <script>

        $(function () {

            //$("msg").html(123);
            $("#djBtn").click(function () {

                //$("#msg").html(123);
                $.ajax({
                    url : "myServlet02.do",
                    //data : "key1=value1&key2=value2",
                    type : "get",
                    datatype:"json",
                    async : true,
                    success : function (data) {
                        //data,从后台响应来的数据

                    }
                })

            })

        })

    </script>
</head>
<body>
    <button id="djBtn">点击</button>
    <br>
    <br>

    <div id="msg" style="width: 200px;height: 200px;background-color: pink">



    </div>
    <br>
    <br>

    <div style="width: 200px;height: 200px;background-color: pink">

        ABCDEFG

    </div>

</body>
</html>
