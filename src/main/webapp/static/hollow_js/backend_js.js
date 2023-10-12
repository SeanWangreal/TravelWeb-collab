$(function(){
    $("table.main_btn button").addClass("btn btn-outline-primary btn-lg");

    $("table.main_btn").on("click", "button.btn_comp_audit, button.btn_client_msg, button.btn_comp_search, button.btn_cust_search, button.btn_add_scene", function(){
        console.log(this);
        let that=this;

        $(this).toggleClass("-pressed");
        
        //按鈕點擊時，其他按鈕會消除
        // $(that).closest("table.main_btn").find("button.btn").each(function(){
        //     // console.log(this.className);
        //     if (this.className != that.className) {
        //         // console.log(this.className);
        //         $(this).attr("aria-pressed",false);
        //     }
        // })
    })

    $("a.link_title").on("click", function(e){
        e.preventDefault();
        $(this).closest("li").toggleClass("-on");
        $(this).closest("li").find("div.inner_block").slideToggle();
      });

    $("button.btn_comp_audit").on("click",function(e){
        $("article.comp_audit").slideToggle();
    })

    $("button.btn_client_msg").on("click",function(e){
        $("article.client_msg").slideToggle();
    })

    $("button.btn_comp_search").on("click",function(e){
        $("article.comp_search").slideToggle();
    })

    $("button.btn_cust_search").on("click",function(e){
        $("article.cust_search").slideToggle();
    })

    $("button.btn_add_scene").on("click",function(e){
        $("article.add_scene").slideToggle();
    })
})