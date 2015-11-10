<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<!-- Title and other stuffs -->
		<title>新闻管理</title>
		<meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文,后台管理系统模版,后台模版下载,后台管理系统,后台管理模版" />
		<meta name="description" content="代码家园-www.daimajiayuan.com提供Bootstrap模版,后台管理系统模版,后台管理界面,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="author" content="">
		<!-- Stylesheets -->
		<link href="style/bootstrap.css" rel="stylesheet">
		<!-- Font awesome icon -->
		<link rel="stylesheet" href="style/font-awesome.css">
		<!-- jQuery UI -->
		<link rel="stylesheet" href="style/jquery-ui.css">
		<!-- Calendar -->
		<link rel="stylesheet" href="style/fullcalendar.css">
		<!-- prettyPhoto -->
		<link rel="stylesheet" href="style/prettyPhoto.css">
		<!-- Star rating -->
		<link rel="stylesheet" href="style/rateit.css">
		<!-- Date picker -->
		<link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css">
		<!-- CLEditor -->
		<link rel="stylesheet" href="style/jquery.cleditor.css">
		<!-- Uniform -->
		<link rel="stylesheet" href="style/uniform.default.css">
		<!-- Bootstrap toggle -->
		<link rel="stylesheet" href="style/bootstrap-switch.css">
		<!-- Main stylesheet -->
		<link href="style/style.css" rel="stylesheet">
		<!-- Widgets stylesheet -->
		<link href="style/widgets.css" rel="stylesheet">

		<!-- HTML5 Support for IE -->
		<!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->
		<!-- Favicon -->
		<link rel="shortcut icon" href="img/favicon/favicon.png">

	</head>

	<body class="easyui-layout">

		<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">
  
    <div class="conjtainer">
      <!-- Menu button for smallar screens -->
      <div class="navbar-header">
		  <button class="navbar-toggle btn-navbar" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
			<span>菜单</span>
		  </button>
		  <!-- Site name for smallar screens -->
		  <a href="index.html" class="navbar-brand hidden-lg">首页</a>
		</div>
      
      

      <!-- Navigation starts -->
      <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">         

 	 <!-- Logo section -->
        <div class="col-md-4">
          <!-- Logo. -->
          <div class="logo">
            <h1><a href="#">达耳闻<span class="bold"></span></a></h1>
            <p class="meta">后台管理系统</p>
          </div>
          <!-- Logo ends -->
        </div>
        <!-- Links -->
        <ul class="nav navbar-nav pull-right">
          <li class="dropdown pull-right">            
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
              <i class="icon-user"></i> 管理员 <b class="caret"></b>              
            </a>
            
            <!-- Dropdown menu -->
            <ul class="dropdown-menu">
              <li><a href="login.html"><i class="icon-off"></i> 退出</a></li>
            </ul>
          </li>
          
        </ul>
      </nav>

    </div>
  </div>


<!-- Header starts -->
  <header>
    <div class="container">
      <div class="row">

      </div>
    </div>
  </header>

<!-- Header ends -->

<!-- Main content starts -->

<div class="content">

  	<!-- Sidebar -->
    <div class="sidebar">
        <div class="sidebar-dropdown"><a href="#">导航</a></div>

        <!--- Sidebar navigation -->
        <!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
        <ul id="nav">
          <!-- Main menu with font awesome icon -->
          <li><a href="index.jsp"><i class="icon-home"></i> 首页</a>
          </li>                  
          <li><a href="${pageContext.request.contextPath}/adminUser_getAllUser.action?page=1" ><i class="icon-user"></i>用户管理</a></li>                  
          <li><a href="${pageContext.request.contextPath}/adminNews_getAllNews.action?page=1" class="open"><i class="icon-bar-chart"></i>新闻管理</a></li> 
          <li><a href="${pageContext.request.contextPath}/adminComment_getAllComment.action?page=1"><i class="icon-table"></i>评论管理</a></li>
          <li><a href="${pageContext.request.contextPath}/manage-topic.action"><i class="icon-th-large"></i>专题管理</a>
          <li><a href="${pageContext.request.contextPath}/manage-label.action"><i class="icon-tag"></i> 标签管理</a>
        </ul>
    </div>

    <!-- Sidebar ends -->

			<!-- Main bar -->
			<div class="mainbar">
				<!-- Page heading -->
				<div class="page-head">
					<h2 class="pull-left"><i class="icon-bar-chart"></i> 新闻管理</h2>

					<!-- Breadcrumb -->
					<div class="bread-crumb pull-right">
						<a href="index.html"><i class="icon-home"></i> 主页</a>
						<!-- Divider -->
						<span class="divider">/</span>
						<a href="#" class="bread-current"><i class="icon-bar-chart"></i> 新闻管理</a>
					</div>

					<div class="clearfix"></div>

				</div>
				<!-- Page heading ends -->

				<!-- Matter -->

				<div class="matter">
					<div class="container">
					<div class="row">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 col-sm-offset-2 control-label lab3">新闻类型：</label>
									<div class="col-sm-2">
										<select class="form-control">
															<option>1</option>
															<option>2</option>
															<option>3</option>
															<option>4</option>
															<option>5</option>
										</select>
									</div>
									<label for="inputEmail3" class="col-sm-2 control-label lab3">标签：</label>
									<div class="col-sm-2">
										<select class="form-control">
															<option>1</option>
															<option>2</option>
															<option>3</option>
															<option>4</option>
															<option>5</option>
										</select>
									</div>
									<div class="col-sm-2">
										<button type="submit" class="btn btn-info">搜索</button>
									</div>
								</div>
							</form>
						</div>
						<div class="row">
							<div class=" col-sm-12">
								<div class="widget">

									<div class="widget-head">
										<div class="pull-left">
											新闻列表
										</div>
										<div class="widget-icons pull-right">

											<input type="checkbox" id="selectAll" value="option1">　全选
											<input type="checkbox" id="selectRevsern" value="option1">　反选
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>

											<!--<a href="#" class="wclose"><i class="icon-remove"></i></a>-->
										</div>
										<div class="clearfix"></div>
									</div>

									<div class="widget-content">
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>
														#
													</th>
													<th>编号</th>
													<th>新闻标题</th>
													<th>来源</th>
													<th>发布时间</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
											<s:iterator value="#newsList" var="vo">
												<tr>
													<td>
														<input type="checkbox" id="inlineCheckbox1" name="item" value="option1">
													</td>
													<td>${vo.newsId}</td>

													<td>${vo.newsTitle}</td>
													<td>${vo.newsSources}</td>
													<td>${vo.newsPubTime}</td>
													<td>

														<a class="btn btn-xs btn-success" href="${pageContext.request.contextPath}/adminNews_getNewsById.action?id=${vo.newsId}"><i class="icon-list"></i></a>
														<button class="btn btn-xs btn-danger delete"><i class="icon-remove"></i> </button>
													</td>
												</tr>
												</s:iterator>
											</tbody>
										</table>

										<div class="widget-foot">
											<div style="margin-top: 10px; float: left;">
												<button class="btn btn-xs btn-danger" id="deleteList"><i class="icon-remove">批量删除</i> </button>
											</div>

											<ul class="pagination pull-right">
												<li><a href="#">Prev</a>
												</li>
												<li class="active"><a href="#">1</a>
												</li>
												<li><a href="#">2</a>
												</li>
												<li><a href="#">3</a>
												</li>
												<li><a href="#">4</a>
												</li>
												<li><a href="#">Next</a>
												</li>
											</ul>

											<div class="clearfix"></div>

										</div>

									</div>

								</div>
							</div>
						</div>
						<div class="row" style="">
							<div class="col-sm-12">
								<div class="widget">
									<div class="widget-head">
										<div class="pull-left">
											页面抓取
										</div>
										<div class="widget-icons pull-right">
											<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>

											<a href="#" class="wclose"><i class="icon-remove"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content" >
										<form action="${pageContext.request.contextPath}/adminNews_getNewsList.action" method="post">
											<div class="form-group">
												<label class="col-sm-1 control-label col-sm-offset-1" style="line-height: 40px;">网站</label>
												<div class="col-lg-3 ">
													<input type="hidden" id="actionLocal" value="actionHref"/>
													<select class="form-control" name="location" id="newLoca" onChange="ChangeItem()">
														<option value="http://www.ifeng.com/" >凤凰网</option>
														<option value="http://www.xinhuanet.com/" selected="selected">新华网</option>
														<option value="http://news.qq.com/" >腾讯网</option>
													</select>
												</div>
												<label class="col-sm-1 col-sm-offset-1 control-label" style="line-height: 40px;" > 标签</label>
												<div class="col-lg-3 ">
													<select class="form-control" name="type" id="newType">
													 <s:iterator value="#newsLabelList" var="vo">
													 	<option value="${vo.newsLabelId}">${vo.newsLabelName}</option>
													 </s:iterator>
													</select>
												</div>

											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-info col-sm-1 " id="getAll">获取所有</button>
											</div>
										</form>
										<div class="row" style="margin-top: 20px;">
											<iframe frameborder="0" id="iframeL" src="${url}"  height="400" style="float: right;" class="col-sm-7">
												您的浏览器不支持嵌入式框架，或者当前配置为不显示嵌入式框架。
											</iframe>
											<div  class="col-sm-5" style="float: right;">
												<table class="table table-striped table-bordered table-hover" id="newsHref">
													<thead>
														<tr>
															
															<th style="text-align: center;">新闻标题</th>
															
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
													<s:iterator value="#linkList" var="vo" begin="0" end="5">
													 	<tr>
															<td>${vo.title}</td>
															<td>
																<a class="btn btn-xs btn-success" href="${pageContext.request.contextPath}/adminNews_getNewsByUrl.action?url=${vo.url}">获取</a>
															</td>
														</tr>
													 </s:iterator>
													</tbody>
												</table>
												<ul class="pagination pull-right">
												<li><a href="#">Prev</a>
												</li>
												<li class="active"><a href="#">1</a>
												</li>
												<li><a href="#">2</a>
												</li>
												<li><a href="#">3</a>
												</li>
												<li><a href="#">4</a>
												</li>
												<li><a href="#">5</a>
												</li>
												<li><a href="#">Next</a>
												</li>
											</ul>
											</div>
										</div>
										<!--<iframe  src="http://www.jb51.net" style="margin: 20px;"></iframe>-->
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

				<!-- Matter ends -->

			</div>

			<!-- Mainbar ends -->
			<div class="clearfix"></div>

		</div>
		<!-- Content ends -->
		<!--新闻内容 开始-->
		<div class="modal fade" id="news-data" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" data-backdrop="static" data-keyboard="false" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">新闻内容</h4>
					</div>
					<div class="modal-body">

						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-lg-4 control-label">新闻内容</label>
								<div class="col-lg-8">
									<textarea class="cleditor" name="input"></textarea>
								</div>
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!--新闻内容 结束-->
		<!-- Footer starts -->
		<footer>
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<!-- Copyright info -->
						<p class="copy">Copyright &copy; 2012 | <a href="#">Your Site</a> </p>
					</div>
				</div>
			</div>
		</footer>
<div style="position:fixed; top:40%;left:50%;height:50px; width: 250px; padding-top:20px; z-index:99999;background: #00FF00; text-align: center; display:none;" id="showIn">
	正在加载。。。。。
</div>
		<!-- Footer ends -->

		<!-- Scroll to top -->
		<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>

		<!-- JS -->
		<script src="js/jquery.js"></script>
		<!-- jQuery -->
		<script src="js/bootstrap.js"></script>
		<!-- Bootstrap -->
		<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
		<!-- jQuery UI -->
		<script src="js/fullcalendar.min.js"></script>
		<!-- Full Google Calendar - Calendar -->
		<script src="js/jquery.rateit.min.js"></script>
		<!-- RateIt - Star rating -->
		<script src="js/jquery.prettyPhoto.js"></script>
		<!-- prettyPhoto -->

		<!-- jQuery Flot -->
		<script src="js/excanvas.min.js"></script>
		<script src="js/jquery.flot.js"></script>
		<script src="js/jquery.flot.resize.js"></script>
		<script src="js/jquery.flot.pie.js"></script>
		<script src="js/jquery.flot.stack.js"></script>

		<!-- jQuery Notification - Noty -->
		<script src="js/jquery.noty.js"></script>
		<!-- jQuery Notify -->
		<script src="js/themes/default.js"></script>
		<!-- jQuery Notify -->
		<script src="js/layouts/bottom.js"></script>
		<!-- jQuery Notify -->
		<script src="js/layouts/topRight.js"></script>
		<!-- jQuery Notify -->
		<script src="js/layouts/top.js"></script>
		<!-- jQuery Notify -->
		<!-- jQuery Notification ends -->

		<script src="js/sparklines.js"></script>
		<!-- Sparklines -->
		<script src="js/jquery.cleditor.min.js"></script>
		<!-- CLEditor -->
		<script src="js/bootstrap-datetimepicker.min.js"></script>
		<!-- Date picker -->
		<script src="js/jquery.uniform.min.js"></script>
		<!-- jQuery Uniform -->
		<script src="js/bootstrap-switch.min.js"></script>
		<!-- Bootstrap Toggle -->
		<script src="js/filter.js"></script>
		<!-- Filter for support page -->
		<script src="js/custom.js"></script>
		<!-- Custom codes -->
		<script src="js/charts.js"></script>
		<!-- Charts & Graphs -->
		<link href="css/jquery.ui.datepicker.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="js/jquery.ui.core.js"></script>
		<script type="text/javascript" src="js/jquery.ui.datepicker.js"></script>
		<script type="text/javascript" src="js/style.js"></script>
		<script type="text/javascript">
			function ChangeItem(){
				/* var href = $('#newLoca option:selected').attr("value");
				var action = $("#actionLocal").attr("value");
				window.open(action+"?href="+href);
				window.close(); */
				//alert(href);
			}
			$(function(){
				var newHref = $("#newsHref").find('button');
				//alert(newHref.length);
				//newHref.click(function(){
					//alert($(this).attr("value"));
					//alert($("#iframeL").attr("src"));
					//var newType = $('#newType option:selected').attr("value");//选中的文本
					//alert($(this).attr("value")+"?type="+newType+"&url="+$(this).next().attr("value"));
					//$("#showIn").attr("display",block);
					//window.showModalDialog("http://www.thugx.com","","dialogWidth:300px;dialogHeight:300px;scroll:no;status:no")
					//alert($(this).attr("value"));
					//window.open($(this).attr("value")+"?url="+$(this).next().attr("value")+"&type="+newType);
					 
					//window.close();
					//$("#iframeL").attr("src",$(this).attr("value"));
				//});
				$("#getAll").click(function(){
					$("#showIn").css('display','block');
					//alert($("#showIn"));
				});
			});
		</script>
	</body>

</html>
