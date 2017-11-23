<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<jsp:useBean id="pdcTSvc" scope="request" class="com.product_type.model.ProductTypeService" />	
<jsp:useBean id="pdSvc" scope="request" class="com.product.model.ProductService" />
<jsp:useBean id="mcSvc" scope="request" class="com.merged_commodity.model.MergedCommodityService" />

<%
	String sto_num = (String) session.getAttribute("sto_num");
	ProductService pdcSvc = new ProductService();
    List<ProductVO> list = pdcSvc.stoFindAllProduct(sto_num);
    pageContext.setAttribute("list",list);
    ProductVO productVO = (ProductVO) request.getAttribute("productVO");  	
	session.setAttribute("addform","permit");	//�qadd�����ӱo���ӳq����

%>


<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>�X�ְӫ~��Ʒs�W - addProduct.jsp</title>

</head>
<body>
	<jsp:include page="/store-end/store_top.jsp" /> <!-- navbar -->
	<!-- 1�h�j�خ� -->
	<div class="container-fluid">
		<div class="row">
	<!-- 2�h�إ� -->	
			<jsp:include page="/store-end/store_left.jsp" /> <!-- leftSidebar -->
	<!-- 2�h�إk -->		
			<div class="col-xs-12 col-sm-8 col-sm-offset-3" >				
				<div class="block-center panelheight">
	<!--========================== �\���o�� =============================================-->


<%-- �d��+ListAll���s --%>
<jsp:include page="/store-end/pdc_mng/btn_select.jsp" />

	
<table id="table-1">
	<tr>
		<td><h3>�ӫ~��ƦX��</h3></td>		
	</tr>
</table>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/pdc_mng/StoPdcMng.do"  enctype="multipart/form-data">

	<div id="pdclist" class="table-responsive">
		<table class="table">
			<tr>
				<th><input type="checkbox" name="selectall"></th>
				<th>�ӫ~�s��</th>		
				<th>�ӫ~�W��</th>
				<th>�p�M����</th>
				<th>�j�M����</th>
				<th>�y�z</th>
				<th>�Ϥ�</th>
				<th>�ӫ~���O</th>
				<th>���A</th>
				<th>�X�֪��A</th>		
			</tr>		
			
		<c:forEach var="PdcVO" items="${list}" >
			<tr ${(PdcVO.com_num==param.com_num)?'bgcolor=#CCCCFF':''}>					
				<td>					
					<input type="checkbox" name="checkbox" value="${PdcVO.com_num}" 
						<c:forEach var="ckList" items="${ckList}">
							${( ckList==PdcVO.com_num ) ? 'checked':'' } 
						</c:forEach>
							${( PdcVO.mercom_num==null) ? '':'disabled'}
					>
				</td>
				<td>${PdcVO.com_num}</td>	
				<td class="com_name">${PdcVO.com_name}</td>
				<td class="m_price">${PdcVO.m_price}</td>
				<td class="l_price">${PdcVO.l_price}</td>
				<td>${PdcVO.discribe}</td>
				<td><img height=50 src="<%=request.getContextPath()%>/PdcGifReader?com_num=${PdcVO.com_num}"></td> 
				<td>${pdcTSvc.getOnePdcT(PdcVO.pt_num).pt_name}</td>
				<td>${PdcVO.status}</td>
				<td width=200>				
				<c:forEach var="mcVO" items="${mcSvc.getMerList(PdcVO.mercom_num)}" varStatus="p">
					<span>
					${p.count} -
					${pdSvc.getOneProduct(mcVO.com_num).com_name} 
					�p�M ${pdSvc.getOneProduct(mcVO.com_num).m_price}
					�j�M ${pdSvc.getOneProduct(mcVO.com_num).l_price}
					</span><br>
				</c:forEach>
				</td>						
			</tr>
		</c:forEach>	
		</table>
	</div>	<!--id=pdclist-->
	
<button id="getcheckbox">��ܰӫ~</button>	
<button id="reset">�M��</button>	

<hr>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

	<div id="insert">
		<table>
				<tr><td>�X�ְӫ~�M��</td><td id="mercom"></td></tr>
				<tr><td>�ӫ~�W��</td><td><input type="text" name="com_name" value="${productVO.com_name}"></td></tr>
				<tr><td>�p�M����</td><td><input type="text" name="m_price" value="${productVO.m_price}"></td></tr>
				<tr><td>�j�M����</td><td><input type="text" name="l_price" value="${productVO.l_price}"></td></tr>
				<tr><td>�y�z</td><td><input type="text" name="discribe" value="${productVO.discribe}"></td></tr>
				<tr><td>�Ϥ�</td><td><input type="file" name="img" ></td></tr>
				
				<tr>
					<td>�ӫ~���O�s��:<font color=red><b>*</b></font></td>
					<td>
						<select size="1" name="pt_num">
				         <c:forEach var="pdcTVO" items="${pdcTSvc.all}" > 
				         	<option value="${pdcTVO.pt_num}" ${(productVO.pt_num==pdcTVO.pt_num)? 'selected':'' } >${pdcTVO.pt_name}
				         </c:forEach>   
			       		</select>
			       </td>
				</tr>
				<tr>
					<td>�ӫ~���A:</td>
					<td>
						<select size="1" name="status">
						<option value="���W�[" ${(productVO.status=='���W�[')? 'selected':'' } >���W�[
						<option value="�w�W�[" ${(productVO.status=='�w�W�[')? 'selected':'' } >�w�W�[
						</select>
					</td>
				</tr>
		</table>	


	<input type="submit" value="�e�X�X�ְӫ~">
	<input type="hidden" name="sto_num" value="${sto_num}">
	<input type="hidden" name="action" value="insert_merge">
	</div><!--id=insert-->	
		
</FORM>
<!--========================== �\���o�� =============================================-->			
				</div><!-- class="block-center panelheight" -->			
			</div><!-- class="col-xs-12 col-sm-8 col-sm-offset-3" -->
		</div><!-- div class="row" -->
	</div><!-- div class="container-fluid" -->
	
	<jsp:include page="/store-end/store_foot.jsp" />
	
	
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>

	var add="";
	var mprice,lprice;

	$(document).ready(function () {
		$('#insert').hide();
		//�w�O�X�ְӫ~�ХܦǦ�
		$("input[type=checkbox]:disabled").closest('tr').css("background","#CCCCCC");
		
		//���~�B�z���������FORM���
		if(location.pathname=="/BA104G2/pdc_mng/StoPdcMng.do"){
			$("#pdclist").hide("slow");
			$('#insert').show("slow");
			$("#mercom").text("${productVO.com_name}");
		}
		
		//����+������
		$("input[name=selectall]").change('click',function(){
			var checkboxes = $('input[name="checkbox"]:enabled');
			$(this).is(':checked') ? checkboxes.prop('checked', 'checked') : checkboxes.removeAttr('checked');			
		});
		
		//�X�֫��s
		$('#getcheckbox').on('click',function(){
			var pname="";
			var m_price=0;
			var l_price=0;
			$("input[name=selectall]").removeAttr('checked');
			$("input[type=checkbox]:checked").each(function(i){
				if($(this).is(':enabled')){	//�D�X�ְӫ~�~�|�p��
					 pname = pname+$(this).parent().siblings("td.com_name").text()+"_";
					 m_price = m_price+parseInt($(this).parent().siblings("td.m_price").text());
					 l_price = l_price+parseInt($(this).parent().siblings("td.l_price").text());
				}
			})
		
			//��ȶi�h�U����FORM
			$("#mercom").text(pname);
			$("input[name=com_name]").val(pname.trim());
			$("input[name=m_price]").val(m_price);
			$("input[name=l_price]").val(l_price);
			
			//�ӫ~�M������
			$("#pdclist").toggle("slow");
			$('#insert').toggle("slow");
			return false;
		});		
		
		//�M�����s
		$('#reset').on('click',function(){	
			$("input[type=checkbox]").each(function(){
				$(this).prop("checked",false);
			});
			var all_Inputs = $("input[type=text]");
			all_Inputs.val("");
			$("#pdclist").show("slow");
			$('#insert').hide("slow");
			return false;		
		});
		
		
		
	});

</script>
</body>
</html>