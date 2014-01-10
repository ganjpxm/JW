<!--
<#if x = 1>  
...  
<#elseif x = 2>  
...  
<#else>  
...  
</#if>
 
<#switch being.size>  
  <#case "small">  
	This will be processed if it is small  
    <#break>  
  <#default>  
    This will be processed if it is neither  
</#switch>

<#switch x>  
  <#case x = 1>  
         1  
  <#default>  
         d  
</#switch>

<#assign seq = ["winter", "spring", "summer", "autumn"]>
<#list seq as item>  
  <#if item = "spring"><#break></#if>
  ${item_index + 1}. ${item}<#if item_has_next>,</#if>  
</#list>

<#include "/common/copyright.ftl" encoding="GBK">

<#import "/libs/mylib.ftl" as my>  
<@my.copyright date="1999-2002"/>      
--> 
