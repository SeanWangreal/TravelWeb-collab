$(function(){
    $("table.main_btn button").addClass("btn btn-outline-primary btn-lg");

    $("table.main_btn").on("click", "button.main_btn_comp_audit, button.main_btn_client_msg, button.main_btn_comp_search, button.main_btn_cust_search, button.main_btn_add_scene", function(){
		
        //console.log(this);
        let that=this;

        $(this).toggleClass("-pressed");
    })

    $("a.link_title").on("click", function(e){
        e.preventDefault();
        $(this).closest("li").toggleClass("-on");
        $(this).closest("li").find("div.inner_block").slideToggle();
      });

    $("button.main_btn_comp_audit").on("click",function(e){
        $("article.art_comp_audit").slideToggle();
    })

    $("button.main_btn_client_msg").on("click",function(e){
        $("article.art_client_msg").slideToggle();
    })

    $("button.main_btn_comp_search").on("click",function(e){
        $("article.art_comp_search").slideToggle();
        
    })

    $("button.main_btn_cust_search").on("click",function(e){
        $("article.art_cust_search").slideToggle();
    })

    $("button.main_btn_add_scene").on("click",function(e){
        $("article.art_add_scene").slideToggle();
    })
    
	$("article.art_comp_search button.task_comp_search").on("click",function(e){
		console.log(e);
	    let compId = ($("input.comp_search_value").val()).trim();
	    console.log(compId);
	
	    if(compId != ""){
	        let form_data={
	          "action":"getOneJSON",
	          "compId":compId
	        }
	        $.ajax({
	          url: "/TravelWeb-collab/CompanyServlet",           // 資料請求的網址
	          type: "POST",                  // GET | POST | PUT | DELETE | PATCH
	          data: form_data,                // 將物件資料(不用雙引號) 傳送到指定的 url
	          dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
	          beforeSend: function(){       // 在 request 發送之前執行
	            
	          },
	          success: function(data){      // request 成功取得回應後執行
		            console.log(data);
		            let list_html = "";				
		            list_html += `
				                <li>
				                    <div class="">
				                        <table class="table table-striped">
				                            <tr>
				                                <th>業者ID</th>
				                                <td>${data.compId}</td>
				                            </tr>
				                            <tr>
				                                <th>公司名稱</th>
				                                <td><input type="text" class="text" value="${data.compName}"></td>
				                            </tr>
				                            <tr>
				                                <th>公司地址</th>
				                                <td><input type="text" class="text" value="${data.compAddress}"></td>
				                            </tr>
				                            <tr>
				                                <th>公司電話</th>
				                                <td><input type="text" class="text" value="${data.compPhone}"></td>
				                            </tr>
				                            <tr>
				                                <th>負責人</th>
				                                <td><input type="text" class="text" value="${data.principalName}"></td>
				                            </tr>
				                            <tr>
				                                <th>負責人電話</th>
				                                <td><input type="text" class="text" value="${data.principalPhone}"></td>
				                            </tr>
				                            <tr>
				                                <th>帳號</th>
				                                <td><input type="text" class="text" value="${data.compAccount}"></td>
				                            </tr>
				                            <tr>
				                                <th>信箱</th>
				                                <td><input type="text" class="text" value="${data.compMail}"></td>
				                            </tr>
				                            <tr>
				                                <th>照片</th>
				                                <td><input type="file" class="text" value="照片在這"></td>
				                            </tr>
				                        </table>
				                        <div id="preview">
				                            <span class="text">預覽圖</span>
				                        </div><br>
				                        <button class="btn_update btn btn-outline-primary btn-lg">更新</button>
				                    </div>                    
				                </li>                
		             `;
					
		            $("ul.list_company > li").html(list_html);
		            $("input.task_name").val("");
	          },
	          error: function(xhr){         // request 發生錯誤的話執行
	            console.log(xhr);
	          },
	          complete: function(xhr){      // request 完成之後執行(在 success / error 事件之後執行)
	            // console.log(xhr);
	          }
	        });
	    }
    })

    $("article.art_cust_search button.task_add").on("click",function(e){
        alert("會員查尋");
    })
    
})