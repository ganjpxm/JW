<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
</head>
<body>
<div data-role="dialog">
  <div data-role="header" data-theme="d">
	<h1>Edit Survey Table</h1>
  </div>
  <div data-role="content" data-theme="c">
	<form id="cmEvaluateTableForm" action="<c:url value='/updateCmEvaluateTable'/>" method="post" data-ajax="false" OnSubmit="preHandle(this)">
	  <input type="hidden" name="evaluateTableId" value="${cmEvaluateTable.evaluateTableId}"/>
	  <input type="hidden" id="itemIndex" name="itemIndex" value="${evaluateItemsSize}"/>
	  <input type="hidden" id="roleIds" name="roleIds" />
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <div class="ui-body ui-body-c">
		  <div class="lable"><span class="red">*</span>Survey Title: </div>
		  <input id="tableName" name="tableName" class="validate[required]" value="${cmEvaluateTable.tableName}"/>

		  <div class="lable"><span class="red">*</span>Start Date : </div>
	      <input name="startDate" id="startDate" type="date" data-role="datebox" class="validate[required]" 
		    value="<fmt:formatDate  value='${cmEvaluateTable.startDate}' pattern='dd/MM/yyyy'/>" 
		    data-options='{"mode":"datebox", "overrideDateFormat":"%d/%m/%Y", "overrideDateFieldOrder":["d", "m", "y"], "useDialogForceFalse":true}'/>
		  <div class="lable"><span class="red">*</span>End Date : </div>
	      <input name="endDate" id="endDate" type="date" data-role="datebox" 
		    value="<fmt:formatDate  value='${cmEvaluateTable.endDate}' pattern='dd/MM/yyyy'/>" class="validate[required]"
		    data-options='{"mode":"datebox", "overrideDateFormat":"%d/%m/%Y", "overrideDateFieldOrder":["d", "m", "y"], "useDialogForceFalse":true}'/>
		  
		  <jp:jqmCheck name="tableType" checkType="checkbox" data="0:Rating;1:Voting;" lableValue="Survey Type" checkedIds="${cmEvaluateTable.tableType}"/>
		    
		  <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}" lableValue="amRole.roleName" checkedIds="${cmEvaluateTable.roleIds}"/>
		  <div style="height:15px">&nbsp; </div>
	  </div>
	  <div class="ui-body ui-body-c">
	    <h3>Answers & Questions <a href="javascript:addOneColumnManyRowItem();" data-role="button" data-icon="add" data-iconpos="notext" 
			data-rel="dialog" rel="external" data-inline="true"><fmt:message key="button.add"/></a>
	  	<div id="oneColumnManyRowItems">
		  <c:forEach var="map" items="${evaluateItems}" varStatus="stat">
	        <div id="item${stat.count}">
	          <div style="width:490px;float:left;"><span class="red">*</span>Question${stat.count} :
	            <textarea id="itemName${stat.count}" name="itemName${stat.count}" 
	            	class="validate[required]" style="width:470px;height:45px;display:inline;">${map.itemName}</textarea>
	          </div>
	          <div style="width:490px;float:left;"><span class="red">*</span>Answer${stat.count} :
	            <textarea id="itemOption${stat.count}" name="itemOption${stat.count}" 
	            	class="validate[required]" style="width:470px;height:45px;display:inline;">${map.itemOptions}</textarea>
	          </div>
              <a data-inline="true" data-iconpos="notext" data-icon="jp-delete" data-role="button"  
  			      href="javascript:hiddenItem('item${stat.count}','itemName${stat.count}');" 
  			      data-theme="c" style="float:right"><fmt:message key="button.delete"/></a>
  		    </div>
		  </c:forEach>	
		</div>		
		<div style="height:15px">&nbsp; </div>
	  </div>
	  <div style="text-align:right">
		<a href="#" data-role="button" data-inline="true" data-rel="back"><fmt:message key="button.back"/></a>
		<button type="submit" data-theme="${webConfig['dataTheme']}" name="submit" data-inline="true"><fmt:message key="button.save"/></button>
	  </div>
	</form>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
<!--
function preHandle(form) {
  if (!$('#cmEvaluateTableForm').validationEngine('validate')) {
	 return false;
  }
  $("#roleIds").val(jp.getCheckValues("roleId"));
  showLoading("cmEvaluateTableForm", "saving");
};
$(document).ready(function(){
  $("#cmEvaluateTableForm").validationEngine();
});

var itemIndex = "${evaluateItemsSize}" - 0;
function addOneColumnManyRowItem() {
  var divId = "item" + ++itemIndex;
  var itemNameId = "itemName" + itemIndex;
  var itemOption = "itemOption" + itemIndex;
  var itemHtml = getItemHtml(divId,itemNameId,itemOption,itemIndex);
  $('#oneColumnManyRowItems').append($(itemHtml)).trigger('create');
  $("#itemIndex").val(itemIndex);
}

function getItemHtml(divId,itemName,itemOption,index) {
  var colume1 = '<div id="' + divId + '"><div style="width:490px;float:left;"><span class="red">*</span>Question' + index + ' : <textarea id="' + itemName + '" name="' + itemName  
  	+ '" class="validate[required]" style="width:470px;height:45px;display:inline;"/></div>';
  var colume2 = '<div style="width:490px;float:left;"><span class="red">*</span>Answer' + index + ' :<textarea id="' + itemOption + '" name="' + itemOption  
  	+ '" class="validate[required]" style="width:470px;height:45px;display:inline;"/></div>';
  var column3 = '<a data-inline="true" data-iconpos="notext" data-icon="jp-delete" data-role="button"' +  
			'href="javascript:hiddenItem(&#39;' + divId + '&#39;,&#39;' + itemName + '&#39;);" ' + 
			' data-theme="c" style="float:right"/></div>';
  return colume1 + colume2 + column3;
}

function hiddenItem(divId, inputId) {
  $("#"+inputId).val("delete");	
  $("#"+divId).hide();	
};
//-->
</script>
