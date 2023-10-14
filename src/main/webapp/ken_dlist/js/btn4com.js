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
$(document).on("click",function(e){
    if(!$(e.target).hasClass("nothing") && !$(e.target).hasClass("head_btn") && 
                                                !$(e.target).hasClass("icon")){
        $(".all_side").removeClass("on");
    }
})
if(add_btn != null){
    add_btn.addEventListener("click", add);
}
if (no_btn != null){
    no_btn.addEventListener("click", no);
}
if (yes_btn != null){
    yes_btn.addEventListener("click", yes);
}
