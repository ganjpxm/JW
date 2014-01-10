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
	<h1>Add Survey Table</h1>
  </div>
  <div data-role="content" data-theme="c">
  	<!--evaluateTableId,tableCd,tableName,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	<form id="cmEvaluateTableForm" action="<c:url value='/saveCmEvaluateTable'/>" method="post" data-ajax="false" OnSubmit="preHandle(this)">
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <input type="hidden" id="itemIndex" name="itemIndex" value="0"/>
	  <input type="hidden" id="roleIds" name="roleIds" />
	  <div class="ui-body ui-body-c">
	  	  <!--  
		  <div class="lable"><span class="red">*</span>Table CD : </div>
	      <input id="tableCd" name="tableCd" class="validate[required]"/>
	      -->
		  <div class="lable"><span class="red">*</span>Survey Title : </div>
		  <input id="tableName" name="tableName" class="validate[required]"/>
		  <div class="lable"><span class="red">*</span>Start Date : </div>
	      <input name="startDate" id="startDate" type="date" data-role="datebox" class="validate[required]" 
		      data-options='{"mode":"datebox", "overrideDateFormat":"%d/%m/%Y", "overrideDateFieldOrder":["d", "m", "y"], "useDialogForceFalse":true}'/>
		  <div class="lable"><span class="red">*</span>End Date : </div>
	      <input name="endDate" id="endDate" type="date" data-role="datebox" class="validate[required]"
		      data-options='{"mode":"datebox", "overrideDateFormat":"%d/%m/%Y", "overrideDateFieldOrder":["d", "m", "y"], "useDialogForceFalse":true}'/>
		  
	      <jp:jqmCheck name="tableType" checkType="checkbox" data="0:Rating;1:Voting;" lableValue="Survey Type"/>
		  
		  <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}" lableValue="amRole.roleName"/>
		  
		  <div style="height:15px">&nbsp; </div>
	  </div>
      <div class="ui-body ui-body-c mt5">
	  	<h3>Answers & Questions <a href="javascript:addItem();" data-role="button" data-icon="add" data-iconpos="notext" 
			data-rel="dialog" rel="external" data-inline="true"><fmt:message key="button.add"/></a></h3>
		<div id="item">
				
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
  $("#cmEvaluateTableForm").validationEngine({validationEventTrigger:""});
});

var index = 0;
function addItem() {
  var item = "item" + ++index;
  var itemName = "itemName" + index;
  var itemOption = "itemOption" + index;
  var itemHtml = getItemHtml(item,itemName,itemOption,index);
  $('#item').append($(itemHtml)).trigger('create');
  $("#itemIndex").val(index);
}

function getItemHtml(divId, itemName, itemOption, index) {
  var colume1 = '<div id="' + divId + '"><div style="width:490px;float:left;"><span class="red">*</span>Question' + index + ' : <textarea id="' + itemName + '" name="' + itemName  
  	+ '" class="validate[required]" style="width:470px;height:45px;display:inline;"/></div>';
  var colume2 = '<div style="width:490px;float:left;"><span class="red">*</span>Answer' + index + ' :<textarea id="' + itemOption + '" name="' + itemOption  
  	+ '" class="validate[required]" style="width:470px;height:45px;display:inline;"/></div>';
  var column3 = '<a data-inline="true" data-iconpos="notext" data-icon="jp-delete" data-role="button"' +  
			'href="javascript:hiddenItem(&#39;' + divId + '&#39;,&#39;' + itemName + '&#39;);" ' + 
			' data-theme="c" style="float:right"/></div>';
  return colume1 + colume2 + column3;
}

function hiddenItem(item, itemName) {
  $("#"+itemName).val("delete");	
  $("#"+item).hide();	
}
//-->
</script>
