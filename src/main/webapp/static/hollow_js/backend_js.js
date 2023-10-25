$(function() {
	$("table.main_btn button").addClass("btn btn-outline-primary btn-lg");

	$("table.main_btn").on("click", "button.main_btn_comp_audit, button.main_btn_client_msg, button.main_btn_comp_search, button.main_btn_cust_search, button.main_btn_add_scene", function() {

		//console.log(this);
		//let that = this;

		$(this).toggleClass("-pressed");
	})

	$("a.link_title").on("click", function(e) {
		e.preventDefault();
		$(this).closest("li").toggleClass("-on");
		$(this).closest("li").find("div.inner_block").slideToggle();
	});

	$("button.main_btn_comp_audit").on("click", function() {
		$("article.art_comp_audit").slideToggle();
	})

	$("button.main_btn_client_msg").on("click", function() {
		$("article.art_client_msg").slideToggle();
	})

	$("button.main_btn_comp_search").on("click", function() {
		$("article.art_comp_search").slideToggle();

	})

	$("button.main_btn_cust_search").on("click", function() {
		$("article.art_cust_search").slideToggle();
	})

	$("button.main_btn_add_scene").on("click", function() {
		$("article.art_add_scene").slideToggle();
	})
	
	var compPoint = "/CompanyServlet";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var compEndPointURL = "http://" + window.location.host + webCtx + compPoint;
	
	var custPoint = "/eric/ConsumerServlet";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var custEndPointURL = "http://" + window.location.host + webCtx + custPoint;
	
	//業者查詢
	$("article.art_comp_search button.task_comp_search").on("click", function(e) {
		console.log(e);
		let compId = ($("input.comp_search_value").val()).trim();
		console.log(compId);
		$.ajax({
			url: compEndPointURL,           // 資料請求的網址
			type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			data: {
				"action": "getOneJSON",
				"compId": compId
			},                // 將物件資料(不用雙引號) 傳送到指定的 url
			dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			beforeSend: function() {       // 在 request 發送之前執行
			},
			success: function(data) {      // request 成功取得回應後執行
				compSearchResult(data);
			//$("input.task_name").val("");
			},
			error: function(xhr) {         // request 發生錯誤的話執行
				console.log(xhr);
			},
			complete: function(xhr) {      // request 完成之後執行(在 success / error 事件之後執行)
				// console.log(xhr);
			}
		});
	})
	
	function compSearchResult(data) {
		if (data.status == "Success") {
			let list_html = "";
			list_html += `
				<li>
					<div class="">
						<table class="table table-striped">
							<tr>
								<th>業者ID</th>
								<td><span class="spn_compId" data-id=${data.compId}>${data.compId}</span></td>
							</tr>
							<tr>
								<th>公司名稱</th>
								<td><input type="text" class="text inp_compName" value="${data.compName}"></td>
							</tr>
							<tr>
								<th>公司地址</th>
								<td><input type="text" class="text inp_compAddress" value="${data.compAddress}"></td>
							</tr>
							<tr>
								<th>公司電話</th>
								<td><input type="text" class="text inp_compPhone" value="${data.compPhone}"></td>
							</tr>
							<tr>
								<th>負責人</th>
								<td><input type="text" class="text inp_principalName" value="${data.principalName}"></td>
							</tr>
							<tr>
								<th>負責人電話</th>
								<td><input type="text" class="text inp_principalPhone" value="${data.principalPhone}"></td>
							</tr>
							<tr>
								<th>帳號</th>
								<td><input type="text" class="text inp_compAccount" value="${data.compAccount}"></td>
							</tr>
							<tr>
								<th>信箱</th>
								<td><input type="text" class="text inp_compMail" value="${data.compMail}"></td>
							</tr>
						</table>
						<button class="btn_comp_update btn btn-outline-primary btn-lg">更新</button>
					</div>                    
				</li>                
			`;

			$("ul.list_company > li").html(list_html);
		} else {
			let list_html = "";
			list_html += `
				<span style="color:red">${(data.noCompId != null? data.noCompId:"")}</span>
				<span style="color:red">${(data.wrongId != null? data.wrongId:"")}</span>
				<span style="color:red">${(data.noData != null? data.noData:"")}</span>
			`;
			$("ul.list_company > li").html(list_html);
		}
	}
	
	$("input.comp_search_value").on("keyup", function(e){
    	//console.log(e.which);
    	if(e.which == 13){ // 按下 Enter 鍵
      		$("button.task_comp_search").click();
    	}
  	});
	
	//業者更新
	$("ul.list_company").on("click", "button.btn_comp_update", function() {
		let compId=($(this).closest("li").find("span.spn_compId")).attr("data-id");
		let compName=($(this).closest("li").find("input.inp_compName")).val().trim();
		let compAddress=($(this).closest("li").find("input.inp_compAddress")).val().trim();
		let compPhone=($(this).closest("li").find("input.inp_compPhone")).val().trim();
		let principalName=($(this).closest("li").find("input.inp_principalName")).val().trim();
		let principalPhone=($(this).closest("li").find("input.inp_principalPhone")).val().trim();
		let compAccount=($(this).closest("li").find("input.inp_compAccount")).val().trim();
		let compMail=($(this).closest("li").find("input.inp_compMail")).val().trim();
		
		$.ajax({
			url: compEndPointURL,           // 資料請求的網址
			type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			data: {
			"action": "updFromBackend",
			"compId": compId,
			"compName":compName,
			"compAddress":compAddress,
			"compPhone":compPhone,
			"principalName":principalName,
			"principalPhone":principalPhone,
			"compAccount":compAccount,
			"compMail":compMail
			},                // 將物件資料(不用雙引號) 傳送到指定的 url
			dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			beforeSend: function() {       // 在 request 發送之前執行
			},
			success: function(data) {      // request 成功取得回應後執行
				console.log(data);
				compUpdateResult(data);
				//$("input.task_name").val("");
			},
			error: function(xhr) {         // request 發生錯誤的話執行
				console.log(xhr);
			},
			complete: function(xhr) {      // request 完成之後執行(在 success / error 事件之後執行)
				// console.log(xhr);
			}
		});
	})
	
	function compUpdateResult(data) {
		if (data.status == "Success") {
			alert("業者更新");
			let list_html = "";
			list_html += `
				<li>
					<div class="">
						<table class="table table-striped">
							<tr>
								<th>業者ID</th>
								<td><span class="spn_compId" data-id=${data.compId}>${data.compId}</span></td>
							</tr>
							<tr>
								<th>公司名稱</th>
								<td><input type="text" class="text inp_compName" value="${data.compName}"></td>
							</tr>
							<tr>
								<th>公司地址</th>
								<td><input type="text" class="text inp_compAddress" value="${data.compAddress}"></td>
							</tr>
							<tr>
								<th>公司電話</th>
								<td><input type="text" class="text inp_compPhone" value="${data.compPhone}"></td>
							</tr>
							<tr>
								<th>負責人</th>
								<td><input type="text" class="text inp_principalName" value="${data.principalName}"></td>
							</tr>
							<tr>
								<th>負責人電話</th>
								<td><input type="text" class="text inp_principalPhone" value="${data.principalPhone}"></td>
							</tr>
							<tr>
								<th>帳號</th>
								<td><input type="text" class="text inp_compAccount" value="${data.compAccount}"></td>
							</tr>
							<tr>
								<th>信箱</th>
								<td><input type="text" class="text inp_compMail" value="${data.compMail}"></td>
							</tr>
						</table>
						<button class="btn_comp_update btn btn-outline-primary btn-lg">更新</button>
					</div>                    
				</li>                
			`;

			$("ul.list_company > li").html(list_html);
		} else {
			let list_html = "";
			list_html += `
				<li>
					<div class="">
						<table class="table table-striped">
							<tr>
								<th>業者ID</th>
								<td><span class="spn_compId" data-id=${data.compId}>${data.compId}</span></td>
							</tr>
							<tr>
								<th>公司名稱</th>
								<td>	
									<span style="color:red">${(data.wrongName != null? data.wrongName:"")}</span>
									<input type="text" class="text inp_compName" value="${data.compName}">
								</td>
							</tr>
							<tr>
								<th>公司地址</th>
								<td>
									<span style="color:red">${(data.wrongAddress != null? data.wrongAddress:"")}</span>
									<input type="text" class="text inp_compAddress" value="${data.compAddress}">
								</td>
							</tr>
							<tr>
								<th>公司電話</th>
								<td>
									<span style="color:red">${(data.wrongPhone != null? data.wrongPhone:"")}</span>
									<input type="text" class="text inp_compPhone" value="${data.compPhone}">
								</td>
							</tr>
							<tr>
								<th>負責人</th>
								<td>
									<span style="color:red">${(data.wrongPrincipalName != null? data.wrongPrincipalName:"")}</span>
									<input type="text" class="text inp_principalName" value="${data.principalName}">
								</td>
							</tr>
							<tr>
								<th>負責人電話</th>
								<td>
									<span style="color:red">${(data.wrongPrincipalPhone != null? data.wrongPrincipalPhone:"")}</span>
									<input type="text" class="text inp_principalPhone" value="${data.principalPhone}">
								</td>
							</tr>
							<tr>
								<th>帳號</th>
								<td>
									<span style="color:red">${(data.wrongAccount != null? data.wrongAccount:"")}</span>
									<input type="text" class="text inp_compAccount" value="${data.compAccount}">
								</td>
							</tr>
							<tr>
								<th>信箱</th>
								<td>
									<span style="color:red">${(data.wrongMail != null? data.wrongMail:"")}</span>
									<input type="text" class="text inp_compMail" value="${data.compMail}">
								</td>
							</tr>
						</table>
						<button class="btn_comp_update btn btn-outline-primary btn-lg">更新</button>
					</div>                    
				</li>
			`;
			$("ul.list_company > li").html(list_html);
		}
	}

	//會員搜尋
	$("article.art_cust_search button.task_cust_search").on("click", function(e) {
		console.log(e);
		let custId = ($("input.cust_search_value").val()).trim();
		console.log(custId);
		$.ajax({
			url: custEndPointURL,           // 資料請求的網址
			type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			data: {
				"action": "getOneJSON",
				"cusId": custId
			},                // 將物件資料(不用雙引號) 傳送到指定的 url
			dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			beforeSend: function() {       // 在 request 發送之前執行
			},
			success: function(data) {      // request 成功取得回應後執行
				console.log(data);
				custSearchResult(data);
			//$("input.task_name").val("");
			},
			error: function(xhr) {         // request 發生錯誤的話執行
				console.log(xhr);
			},
			complete: function(xhr) {      // request 完成之後執行(在 success / error 事件之後執行)
				// console.log(xhr);
			}
		});
	})
	
	function custSearchResult(data) {
		if (data.status == "Success") {
			let list_html = "";
			list_html += `
				<li>
					<div class="">
						<table class="table table-striped">
							<tr>
								<th>會員ID</th>
								<td><span class="spn_custId" data-id=${data.cusId}>${data.cusId}</span></td>
							</tr>
							<tr>
								<th>姓名</th>
								<td><input type="text" class="text inp_custName" value="${data.cusName}"></td>
							</tr>
							<tr>
								<th>性別</th>
								<td>
                                    <input type="radio" name="custGender" class="inp_custGender" value="0" ${(data.cusGender)==0?"checked":""}>女
                                    <input type="radio" name="custGender" class="inp_custGender" value="1" ${(data.cusGender)==1?"checked":""}>男
                                </td>
							</tr>
							<tr>
								<th>帳號</th>
								<td><input type="text" class="text inp_custAccount" value="${data.cusAccount}"></td>
							</tr>
							<tr>
								<th>信箱</th>
								<td><input type="text" class="text inp_custMail" value="${data.cusMail}"></td>
							</tr>
							<tr>
								<th>電話</th>
								<td><input type="text" class="text inp_custPhone" value="${data.cusPhone}"></td>
							</tr>
							<tr>
								<th>住址</th>
								<td><input type="text" class="text inp_custAddress" value="${data.cusAddress}"></td>
							</tr>
						</table>
						<button class="btn_cust_update btn btn-outline-primary btn-lg">更新</button>
					</div>
				</li>
			`;
			
			$("ul.list_consumer > li").html(list_html);
		} else {
			let list_html = "";
			list_html += `
				<span style="color:red">${(data.noCustId != null? data.noCustId:"")}</span>
				<span style="color:red">${(data.wrongId != null? data.wrongId:"")}</span>
				<span style="color:red">${(data.noData != null? data.noData:"")}</span>
			`;
			$("ul.list_consumer > li").html(list_html);
		}
	}
	
	//會員更新
	$("ul.list_consumer").on("click", "button.btn_cust_update", function() {
		//alert("會員更新");
		let custId=($(this).closest("li").find("span.spn_custId")).attr("data-id");
		let custName=($(this).closest("li").find("input.inp_custName")).val().trim();
		let custGender=($(this).closest("li").find("[name=custGender]:checked")).val();
		let custAccount=($(this).closest("li").find("input.inp_custAccount")).val().trim();
		let custMail=($(this).closest("li").find("input.inp_custMail")).val().trim();
		let custPhone=($(this).closest("li").find("input.inp_custPhone")).val().trim();
		let custAddress=($(this).closest("li").find("input.inp_custAddress")).val().trim();
		
		$.ajax({
			url: custEndPointURL,           // 資料請求的網址
			type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			data: {
			"action": "updFromBackend",
			"custId": custId,
			"custName":custName,
			"custGender":custGender,
			"custAccount":custAccount,
			"custMail":custMail,
			"custPhone":custPhone,
			"custAddress":custAddress
			},                // 將物件資料(不用雙引號) 傳送到指定的 url
			dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			beforeSend: function() {       // 在 request 發送之前執行
			},
			success: function(data) {      // request 成功取得回應後執行
				console.log(data);
				custUpdateResult(data);
				//$("input.task_name").val("");
			},
			error: function(xhr) {         // request 發生錯誤的話執行
				console.log(xhr);
			},
			complete: function(xhr) {      // request 完成之後執行(在 success / error 事件之後執行)
				// console.log(xhr);
			}
		});
	})
	
	function custUpdateResult(data) {
		if (data.status == "Success") {
			alert("會員更新");
			let list_html = "";
			list_html += `
				<li>
					<div class="">
						<table class="table table-striped">
							<tr>
								<th>會員ID</th>
								<td><span class="spn_custId" data-id=${data.custId}>${data.custId}</span></td>
							</tr>
							<tr>
								<th>姓名</th>
								<td><input type="text" class="text inp_custName" value="${data.custName}"></td>
							</tr>
							<tr>
								<th>性別</th>
								<td>
                                    <input type="radio" name="custGender" class="inp_custGender" value="0" ${(data.custGender)==0?"checked":""}>女
                                    <input type="radio" name="custGender" class="inp_custGender" value="1" ${(data.custGender)==1?"checked":""}>男
                                </td>
							</tr>
							<tr>
								<th>帳號</th>
								<td><input type="text" class="text inp_custAccount" value="${data.custAccount}"></td>
							</tr>
							<tr>
								<th>信箱</th>
								<td><input type="text" class="text inp_custMail" value="${data.custMail}"></td>
							</tr>
							<tr>
								<th>電話</th>
								<td><input type="text" class="text inp_custPhone" value="${data.custPhone}"></td>
							</tr>
							<tr>
								<th>住址</th>
								<td><input type="text" class="text inp_custAddress" value="${data.custAddress}"></td>
							</tr>
						</table>
						<button class="btn_cust_update btn btn-outline-primary btn-lg">更新</button>
					</div>                    
				</li>                
			`;

			$("ul.list_consumer > li").html(list_html);
		} else {
			let list_html = "";
			list_html += `
				<li>
					<div class="">
						<table class="table table-striped">
							<tr>
								<th>會員ID</th>
								<td><span class="spn_custId" data-id=${data.custId}>${data.custId}</span></td>
							</tr>
							<tr>
								<th>姓名</th>
								<td>
									<span style="color:red">${(data.wrongName != null? data.wrongName:"")}</span>
									<input type="text" class="text inp_custName" value="${data.custName}">
								</td>
							</tr>
							<tr>
								<th>性別</th>
								<td>
                                    <input type="radio" name="custGender" class="inp_custGender" value="0" ${(data.custGender)==0?"checked":""}>女
                                    <input type="radio" name="custGender" class="inp_custGender" value="1" ${(data.custGender)==1?"checked":""}>男
                                </td>
							</tr>
							<tr>
								<th>帳號</th>
								<td>
									<span style="color:red">${(data.wrongAccount != null? data.wrongAccount:"")}</span>
									<input type="text" class="text inp_custAccount" value="${data.custAccount}">
								</td>
							</tr>
							<tr>
								<th>信箱</th>
								<td>
									<span style="color:red">${(data.wrongMail != null? data.wrongMail:"")}</span>
									<input type="text" class="text inp_custMail" value="${data.custMail}">
								</td>
							</tr>
							<tr>
								<th>電話</th>
								<td>
									<span style="color:red">${(data.wrongPhone != null? data.wrongPhone:"")}</span>
									<input type="text" class="text inp_custPhone" value="${data.custPhone}">
								</td>
							</tr>
							<tr>
								<th>住址</th>
								<td>
									<span style="color:red">${(data.wrongAddress != null? data.wrongAddress:"")}</span>
									<input type="text" class="text inp_custAddress" value="${data.custAddress}">
								</td>
							</tr>
						</table>
						<button class="btn_cust_update btn btn-outline-primary btn-lg">更新</button>
					</div>                    
				</li>
			`;
			$("ul.list_consumer > li").html(list_html);
		}
	}
})