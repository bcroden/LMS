<html>
   <head>
      <script>
         function validate() {
            var form = document.forms[0];
            var pass1 = form["password"].value;
            var pass2 = form["password2"].value;
            return true;
            if(pass1 == pass2)
               return true;
            else
               return false;
         }
      </script>
   </head>
   <body>
      <form action="view_account.php" method="post" onsubmit="return validate()">
         First Name: <input type="text" name="first_name"/><br/>
         Last Name: <input type="text" name="last_name"/><br/>
         E-mail: <input type="text" name="email"/><br/>
         Username: <input type="text" name="user_name"/><br/>
         Password: <input type="password" name="password"/><br/>
         Confirm Password: <input type="password" name="password2"/><br/>
         Email Notification <input type="radio" name="notify" value="1">Yes</intput>
         <input type="radio" name="notify" value="-1">No</intput><br/>
         <input type="submit" value="Create Account"/>
         <input type="hidden" name="form_type" value="creation"/>
      </form>
   </body>
</html>
