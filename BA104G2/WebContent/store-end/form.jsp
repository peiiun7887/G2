<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<HTML>
<HEAD>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

</HEAD>
<BODY>


  <FORM METHOD="get" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do">
      
會員編號
    <INPUT TYPE="TEXT" NAME="mem_num" VALUE="MB0000000001"><p>
店家編號
    <select size="1" name="sto_num">
    	<option value="ST0000000001" >ST0000000001
    	<option value="ST0000000002" >ST0000000002
    	<option value="ST0000000003" >ST0000000003
    </select>
    
    <input type="hidden" name="action" value="loginin">
    <INPUT TYPE="SUBMIT" value="送出">
  </FORM>


<!-- 	<FORM METHOD="get" ACTION="/BA104G2/store_detail/store_detail.do"> -->

<!--     <INPUT TYPE="TEXT" NAME="mem_num" VALUE="MB0000000001"><p> -->

<!--     <INPUT TYPE="TEXT" NAME="sto_num" VALUE="ST0000000001"><p> -->
<!--     <INPUT TYPE="SUBMIT"> -->
<!--   </FORM> -->


</BODY>
</HTML>