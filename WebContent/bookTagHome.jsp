	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	  
		<h1>PUBHUB <small>Search Book by Tag</small></h1>
		<hr class="book-primary">
		
		<!-- modified page for tag search -->
		<form action="BookReturn" method="post" class="form-horizontal">
			<div class="form-group">
			    <div class="col-xs-4 col-xs-push-3">
			      <input type="text" class="form-control" id="searchTag" name="searchTag" placeholder="Tag . . ." required="required"/>
			    </div>
			    <button type="submit" class="btn btn-info">Search</button>
			</div>
		</form>

	  </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />