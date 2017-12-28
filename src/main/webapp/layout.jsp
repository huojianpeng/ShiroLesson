<!DOCTYPE html>
<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>layout.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<style type="text/css">
		html{
			height:100%;font-size:14px
		}
		body{
			height:100%;font-size:14px
		}
	</style>
	<script type="text/javascript" src="jquery.min.js"></script>
	<script type="text/javascript" src="jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function urlClick(myTitle,myUrl){
			var ifExist=$("#myTabs").tabs("exists",myTitle);
			if(!ifExist){
				$("#myTabs").tabs("add",{
					title:myTitle,
					closable:true,
					content:'<iframe frameborder=0 width="100%" height="100%" scrolling="no" src="'+myUrl+'"></iframe>'
				});
			}
			$("#myTabs").tabs("select",myTitle);
		}
	</script>

  </head>
  
  <body style="padding:1px;margin:1px;">
    <div class="easyui-layout" style="width:100%;height:100%;margin:0px">
    	<!-- 北部只能设置高度，一般不会设置宽度 -->
		<div data-options="region:'north'" style="height:15%">
			<div style="height:80%">
				<img alt="" src="ttt.png" width="100%" height="137px">
			</div>
			<div style="text-align: right;width: 90%"><a href="">安全退出</a></div>
		</div>
		<div data-options="region:'west',split:true" title="导航菜单" style="width:18%;">
			<div class="easyui-accordion" style="width:500px;height:300px;">
			<div title="权限管理"  style="overflow:auto;padding:10px;">
			   <c:forEach var="menu" items="${requestScope.menuList }">
				<a href="javascript:urlClick('${menu.menuName}','${pageContext.request.contextPath}${menu.menuUrl}')"  style="text-decoration: none;"><img alt="" src="themes/icons/search.png" style="margin-top:5px">${menu.menuName}</a><br/>
				</c:forEach>
			</div>
			<div title="系统设置"  style="padding:10px;">
				
			</div>
			</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<div id="myTabs" class="easyui-tabs" style="width:100%;height:100%">
				<div title="欢迎使用" style="padding:10px">
					<img alt="" src="ttt.png" width="100%" height="100%">
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
