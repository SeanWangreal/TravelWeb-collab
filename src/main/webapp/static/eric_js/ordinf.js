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

// -------------------------------------------------------------



$(document).ready(function() {
    $("#ordBtn").click(function(e) {
        e.stopPropagation(); // 防止事件冒泡到父元素
        e.preventDefault();
        $("#ord_dropdown").toggleClass(); // 切換訂單下拉菜單的顯示和隱藏
    });

});




$(".ordinf_btn").each(function() {
    var $ordinf_btn = $(this);
    
    // 滑鼠進入事件（針對 $ordinf_btn 內的 ".treedot"）
    $(".treedot", $ordinf_btn).on("mouseenter", function(e) {
        $(this).find(".dropdown").addClass("show");
    });

    // 滑鼠離開事件（針對 $ordinf_btn 內的 ".treedot"）
    $(".treedot", $ordinf_btn).on("mouseleave", function(e) {
        $(this).find(".dropdown").removeClass("show");
    });

    // 點擊移除按鈕事件
    $(".removeBtn").on("click", function(e) {
        let r = confirm("確認移除？");
        if (r) {
            let $button = $(this).closest(".ordinf_btn"); // 將 "removeBtn" 改為 "this" 並修正選擇器
            $button.animate({ "opacity": 0 }, 1000, function() {
                $button.remove();
            });
        }
    });
});





// ------------------------------會員資訊-------------------------------


// 編輯按鈕
//    $("#memBtn").on("click",function(e){
//        e.preventDefault();
//        e.stopPropagation();
//        // $('.mem').removeAttr("readonly");
//        $(".mem").toggleClass("ch_mem2");
////        $("readonly").toggleAttr("default value");
//    });


