<div class="container">
	<div class="row">
		<div class="col-md-3">

			<%@include file="./shared/sidebar.jsp"%>


		</div>
		<!-- /.col-lg-3 -->

		<div class="col-md-9">
			<!-- Added breadcrumb component -->
			<div class="row">

				<div class="col-lg-12">
					<!-- breadcrumb for display all products -->
					<c:if test="${userClickAllproducts == true}">
						<ol class="breadcrumb custom-breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">All Products</li>
						</ol>
						<div class="row">

							<div class="col-xs-12">

								<%@include file="allproduct.jsp"%>


							</div>

						</div>

					</c:if>



					<!-- breadcrumb for display categroy products -->
					<c:if test="${userClickCategoryproducts == true}">
						<ol class="breadcrumb custom-breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item">Category</li>
							<li class="breadcrumb-item">${category.name}</li>
						</ol>
						<div class="row">

							<div class="col-xs-12">

								<%@include file="allproduct.jsp"%>


							</div>

						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

</div>