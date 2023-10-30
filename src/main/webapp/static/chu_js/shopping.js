$(function () {//domcontentloaded event


    var msg_btn = document.getElementById("msg");
    var msg_side = document.getElementById("msg_side");
    var info_btn = document.getElementById("info");
    var info_side = document.getElementById("info_side");
    var shop_btn = document.getElementById("shop");
    var shop_side = document.getElementById("shop_side");
    var add_btn = document.getElementById("add");
    var yes_btn = document.getElementById("yes");
    var no_btn = document.getElementById("no");
    var bg_alert = document.querySelector(".alert_bg")
    var al = document.querySelector(".alert");

    function say() {
        info_side.classList.remove("on");
        shop_side.classList.remove("on");
        msg_side.classList.toggle("on");
    }

    function info() {
        msg_side.classList.remove("on");
        shop_side.classList.remove("on");
        info_side.classList.toggle("on");
    }
    function add(e) {
        console.log("s");
        bg_alert.classList.add("on");
        al.classList.add("on");
        body = document.querySelector("body");
        body.classList.add("stop");
    }

    function no() {
        bg_alert.classList.remove("on");
        al.classList.remove("on");
        body.classList.remove("stop");
    }
    function yes() {
        $("#add-data").click();
    }

    msg_btn.addEventListener("click", say);
    info_btn.addEventListener("click", info);
    $(document).on("click", function (e) {
        if (!$(e.target).hasClass("nothing") && !$(e.target).hasClass("head_btn") &&
            !$(e.target).hasClass("icon")) {
            $(".all_side").removeClass("on");
        }
    })
    if (add_btn != null) {
        add_btn.addEventListener("click", add);
    }
    if (no_btn != null) {
        no_btn.addEventListener("click", no);
    }
    if (yes_btn != null) {
        yes_btn.addEventListener("click", yes);
    }


  
    let tab_title = $("ul.tab_list li a.tab");
    //籤換頁籤start
    tab_title.on("click", function () {

        let tab_list = $("div.tab_contents div.tab");

        tab_title.each(function (e) {
            tab_title.removeClass("-on")
        })

        tab_list.each(function (e) {
            tab_list.removeClass("-on")
        })

        // console.log(this);


        let tab_list1 = "div.tab_contents div.tab";

        switch ($(this).attr("data-target")) {
            case "plan1":
                $(this).addClass("-on");
                $(tab_list1 + ".plan1").addClass("-on");
                break;
            case "plan2":
                $(this).addClass("-on");
                $(tab_list1 + ".plan2").addClass("-on");
                break;
            case "plan3":
                $(this).addClass("-on");
                $(tab_list1 + ".plan3").addClass("-on");
                break;
            case "plan4":
                $(this).addClass("-on");
                $(tab_list1 + ".plan4").addClass("-on");
                break;
            case "plan5":
                $(this).addClass("-on");
                $(tab_list1 + ".plan5").addClass("-on");
                break;
        }


        showNoItemsMessage()



    })

    //籤換頁籤end


// console.log($("div.plan_tab_1.list").length);

console.log($("div.plan_tab_1.list").length);
    if ($("div.plan_tab_1.list").length == 0 ) {
        $("#tab_plan1").append('<div class="no-items">暫無商品</div>');
        
    }




    // 假如遇到頁籤沒有商品顯示的字



    // 函數來檢查並顯示/隱藏提示信息
    function showNoItemsMessage() {
        $("div.tab").each(function () {

            var tabContent = $(this);
            var noItemsMessage = tabContent.find(".no-items");
            var hasItems = tabContent.find("div.plan_tab_1.list").length > 0; // 假設每個商品都有一個.product類

            if (hasItems) {
                noItemsMessage.hide();
            } else {
                noItemsMessage.show();
            }
        });


    }
















})




