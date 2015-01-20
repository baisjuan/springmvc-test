<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Testing REST methods via AJAX</title>
    <script type="text/javascript" src="../libs/jquery-1.10.0.min.js"></script>
</head>
<body>
    <div style="width: 250px; margin: 0 auto;">
         <input type="text" id="POST" value=""/><input rel="POST" class="method_btn" type="button" id="post_btn" value="POST"/> <br/>
         <input type="text" id="PUT" value=""/><input rel="PUT" class="method_btn" type="button" id="put_btn" value="PUT"/> <br/>
         <input type="text" id="DELETE" placeholder="Profile name" value=""/><input rel="DELETE" class="method_btn" type="button" id="delete_btn" value="DELETE"/>
    </div>
    <div id="response" style="margin-top:25px;"></div>

    <script type="text/javascript">

        $('.method_btn').click(function(){
            $.ajax({
                type: $(this).attr('rel'),
                url: 'ajax',
                contentType: "application/json; charset=UTF-8",
                dataType: 'json',
                //contentType: "application/json; charset=UTF-8",
                data: JSON.stringify({
                        "name":"Albertina",
                        "company":"Devspark",
                        "age":15,
                        "id":5
                      }),
                success: function(data){
                            $('#response').html(decodeURIComponent(data.msg));
                         }
            });
        });
    </script>

</body>
</html>