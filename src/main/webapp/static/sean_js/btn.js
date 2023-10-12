var msg_btn = document.getElementById("msg");
var msg_side = document.getElementById("msg_side");
var info_btn = document.getElementById("info");
var info_side = document.getElementById("info_side");
var shop_btn = document.getElementById("shop");
var shop_side = document.getElementById("shop_side");

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

function grab() {
msg_side.classList.remove("on");
info_side.classList.remove("on");
shop_side.classList.toggle("on");
}
msg_btn.addEventListener("click", say);
info_btn.addEventListener("click", info);
shop_btn.addEventListener("click", grab);
$(document).on("click",function(e){
    if(!$(e.target).hasClass("nothing") && !$(e.target).hasClass("head_btn") && 
                                                !$(e.target).hasClass("icon")){
        $(".all_side").removeClass("on");
    }
})
$("#Hotel").on("click",function(){
    console.log(this);
    if(!$("#Hotel").hasClass("-on")){
        $(this).addClass("-on");
        $("#Trip").removeClass("-on");
        $('#type').val("hotel");
    }
})
$("#Trip").on("click",function(){
    console.log(this);
    if(!$("#Trip").hasClass("-on")){
        $(this).addClass("-on");
        $("#Hotel").removeClass("-on");
        $('#type').val("trip");
    }
})
