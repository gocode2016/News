<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
  <title>后台登陆</title> 
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="author" content="">
  <!-- Stylesheets -->
  <link href="style/bootstrap.css" rel="stylesheet">
  <link rel="stylesheet" href="style/font-awesome.css">
  <link href="style/style.css" rel="stylesheet">
  <link href="style/bootstrap-responsive.css" rel="stylesheet">

  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

<!-- Form area -->
<div class="admin-form">
  <div class="container">

    <div class="row">
      <div class="col-md-12">
        <!-- Widget starts -->
            <div class="widget worange">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-lock"></i> 后台登陆
              </div>

              <div class="widget-content">
                <div class="padd">
                  <!-- 登录表单 -->
                  <form class="form-horizontal" id="form" action="loginlogin.action" method="POST">
                    <!-- 账号 -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="admName">账号</label>
                      <div class="col-lg-9">
                        <input type="text" class="form-control" id="admName" name="admName"  placeholder="账号">
                      </div>
                    </div>
                    <!-- 密码 -->
                    <div class="form-group">
                      <label class="control-label col-lg-3" for="admPwd">密码</label>
                      <div class="col-lg-9">
                        <input type="password" class="form-control" id="admPwd" name="admPwd" placeholder="密码">
                      </div>
                    </div>
                    <!-- 记住我，验证码 ，登录按钮 -->
                    <div class="form-group">
                    	<label class="control-label col-lg-3" ></label>
                        <div class="col-lg-9">
                          <div class="checkbox">
                            <label>
                              <input type="checkbox">记住我
                            </label>
                            </div>
                        </div>
					</div>
                    <div class="col-lg-9 col-lg-offset-2">
							<button type="submit" class="btn btn-danger">登录</button>
							<button type="reset" class="btn btn-default">重置</button>
					</div>
                    <br />
                  </form>
				</div>
                </div>
            </div>  
      </div>
    </div>
  </div> 
</div>
	
		

<!-- JS -->
  <script src="js/jquery.js"></script>
  <script src="js/jquery.validate.js"></script>
 
<script src="js/bootstrap.js"></script>
 			<script src="http://api.geetest.com/get.php"></script>
				<script type="text/javascript">
					//get  geetest server status, use the failback solution
					$.ajax({
						url : "../StartCapthcaServlet",
						type : "get",
						dataType : 'JSON',
						success : function(result) {
							console.log(result);
							if (result.success) {
								//1. use geetest capthca
								window.gt_captcha_obj = new window.Geetest({
									gt : result.gt,
									challenge : result.challenge,
									product : 'float'
								});

								gt_captcha_obj.appendTo("#validateCode");

								//Ajax request demo,if you use submit form ,then ignore it 
								gt_captcha_obj.onSuccess(function() {
									geetest_ajax_results()
								});

							} else {
								//failback :use your own captcha template
								//Geetest Server is down,Please use your own captcha system	in your web page
								//or use the simple geetest failback solution
								$("#validateCode").html('failback:gt-server is down ,please use your own captcha front');
								//document.write('gt-server is down ,please use your own captcha')
							}

						}
					})
				</script>
				<script type="text/javascript">
				function geetest_refresh() {
					console.log("you can use this api in your own js function")
					gt_captcha_obj.refresh();
				}

				function geetest_ajax_results() {
					$.ajax({
						url : "../VerifyLoginServlet",//todo:set the servelet of your own
						type : "post",
						data : gt_captcha_obj.getValidate(),
						success : function(sdk_result) {
							console.log(sdk_result)
						}
					});
				}
			</script>
			<script type="text/javascript">
			$(function(){
				$('#form').validate({
						onfocusout: function(element){
			        		$(element).valid();
			    		},
						showErrors : function (errorMap, errorList) {
							this.defaultShowErrors();
						},

						submitHandler : function (form) {
								$.ajax({
									url : "VerifyLoginServlet",
									type : "post",
									data : gt_captcha_obj.getValidate(),
									success : function(sdk_result) {
										form.submit();
									}
								});
						},
						rules:{
							admName:{
								required:true,	
							},
							admPwd:{
								required:true,
							}
						},
						messages:{
							admName:{
								required:'账号不为空',
							},
							admPwd:{
								required:'密码不为空',
							}
						},
						highlight: function(element, errorClass) {
							$(element).css('border', '1px solid #666666');
						},
						unhighlight: function(element, errorClass) {
							$(element).css('border', '1px solid #CCC');
						},
					});
				});
			</script>
</body>
</html>
