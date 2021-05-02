<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
   String From_date=request.getParameter("From_date");
   String To_date=request.getParameter("To_date");
   String driver = "com.mysql.jdbc.Driver";
   String connectionUrl = "jdbc:mysql://localhost/";
   String database = "edureka";
   String userid = "root";
   String password = "NO";
   try {
   Class.forName(driver);
   } catch (ClassNotFoundException e) {
   e.printStackTrace();
   }
   Connection connection = null;
   Statement statement = null;
   ResultSet resultSet = null;
   %>
<!DOCTYPE html>
<html>
   <body style="background-color:PaleGreen;">
      <head>
         <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
         <title>Display Statement</title>
         <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
         <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
         <script src="https://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
         <script>
            $( function() {
            $( "#firstdate" ).datepicker({ changeMonth: true, changeYear: true });
            $( "#firstdate" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
            } );
         </script>
         <script>
            $( function() {
            $( "#lastdate" ).datepicker({ changeMonth: true, changeYear: true });
            $( "#lastdate" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
            } );
         </script>
      </head>
      <body>
         <center>
            <h2>Display Statement</h2>
         </center>
         <form method="post" action="Display_Statement.jsp">
            Date Range:
            <br/>
            From <input type="text" name="From_date" id="firstdate">
            &emsp; To <input type="text" name="To_date" id="lastdate">
            <%
               try{
                connection = DriverManager.getConnection(connectionUrl + database, userid, password);
                statement=connection.createStatement();
                String sql ="select * from transactions";
                resultSet = statement.executeQuery(sql);
                %>
            <%            
               connection.close();
               } catch (Exception e) {
               e.printStackTrace();
               }
               %>
            &ensp;<input type="submit" value="Display"><br>
         </form>
         <br/>
         <br/>
         <table border="1">
            <tr>
               <td><b>No</b></td>
               <td><b>Date</b></td>
               <td><b>Description</b></td>
               <td><b>Withdraw</b></td>
               <td><b>Deposit</b></td>
               <td><b>Available Balance</b></td>
            </tr>
            <%
               try{
               connection = DriverManager.getConnection(connectionUrl+database, userid, password);
               statement=connection.createStatement();
               String sql ="select * from transactions where Date between '" + From_date + "' and '" + To_date + "'";
               
               resultSet = statement.executeQuery(sql);
               while(resultSet.next())
               {
               %>
            <tr>
               <td><%=resultSet.getString("No") %></td>
               <td><%=resultSet.getString("Date") %></td>
               <td><%=resultSet.getString("Description") %></td>
               <td><%=resultSet.getString("Withdraw") %></td>
               <td><%=resultSet.getString("Deposit") %></td>
               <td><%=resultSet.getString("Available_Balance") %></td>
            </tr>
            <%
               }
               connection.close();
               } catch (Exception e) {
               e.printStackTrace();
               }
               %>
         </table>
   </body>
</html>