<%@ include file="../init.jsp" %>

<link href="<%= request.getContextPath() %>/login/main.css?t=<%= System.currentTimeMillis() %>" rel="stylesheet" type="text/css" />

<div class="web-content">
	<c:choose>
		<c:when test="<%= themeDisplay.isSignedIn() %>">
			<div style="text-align: center;">
				<h3>
					Hello, <%= themeDisplay.getUser().getFullName() %>. </br>
					Welcome to Dealer Information Link! </br>
					Go to <a href="/group/dealink/home" style="color: #014996;">home</a>
				</h3>
			</div>
		</c:when>
		<c:otherwise>
			<container id="loginPublic">
				<div style="width: 50%;">
					<div class="login">Login</div>
					<div id="form-title">Welcome back! Login to access your DEALINK account.</div>
				</div>
				<div class="login-public">
					<button class="btn btn-login-public" onclick="login()">Login</button>
				</div>
				<div class="login-public">
					<button class="btn btn-regist-public" onclick="register()">Register</button>
				</div>
			</container>
		</c:otherwise>
	</c:choose>
</div>

<script>
	var registerURL = "/web/dealink/register";
	var loginURL = "/group/dealink/home";

	function register() {
		window.location.href = registerURL;
	}

	function login() {
		window.location.href = loginURL;
	}

	<c:if test="${isSignedIn and isRegistered eq false}">
		window.location.href = "/web/dealink/not-found";
	</c:if>
</script>