var msg_btn = document.getElementById("msg");
var msg_side = document.getElementById("msg_side");
var info_btn = document.getElementById("info");
var info_side = document.getElementById("info_side");
var shop_btn = document.getElementById("shop");
var shop_side = document.getElementById("shop_side");
var bg_alert = document.querySelector(".alert_bg")
var al = document.querySelector(".alert");
var add_btn = document.getElementById("add");
var yes_btn = document.getElementById("yes");
var no_btn = document.getElementById("no");
var drop_place = document.querySelector(".drag");
var pic_file = document.getElementById("pic_file");
var pic_files = document.getElementById("pic_files");
var product_type = document.getElementById("type").innerText;


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
    var next_step = true;
    if ($("#name").val() === "") {
        next_step = false;
        alert(`請輸入${product_type}名稱`);
    }
    if ($("#bed-num").val() === "" || $("#bed-num").val() <= 0) {
        next_step = false;
        if (product_type === "客房") {
            alert("床位數請大於0");
        } else {
            alert("套票人數請大於0");
        }
    }
    if ($("#default-num").val() === "" || $("#default-num").val() <= 0) {
        next_step = false;
        if (product_type === "客房") {
            alert("預設每日房數請大於0");
        } else {
            alert("套票庫存請大於0");
        }
    }

    if ($("select[name='scene']") != undefined) {
        for (var i = 0; i < $("select[name='scene']").length; i++) {
            if ($("select[name='scene']")[i].value === "" || $(".time")[i].value === "") {
                next_step = false;
                alert("請填寫景點");
            }
        }
    }


    if ($("textarea").val().length < 100) {
        next_step = false;
        alert(product_type + "介紹至少100字");
    }
    if (($("#money").val()) <= 0) {
        next_step = false;
        alert(product_type + "每晚價格要大於0!")
    }
    if ($("img").length === 0) {
        next_step = false;
        alert("請上傳一張搜尋圖片");
    }
    if ($(".imgs").length < 3){
		next_step = false;
        alert("請上傳至少三張詳細圖片");
	}
    if (next_step) {
        bg_alert.classList.add("on");
        al.classList.add("on");
        body = document.querySelector("body");
        body.classList.add("stop");
    }
}

function no() {
    bg_alert.classList.remove("on");
    al.classList.remove("on");
    body.classList.remove("stop");
}
function yes() {
    $("#add-data").click();
}
function change_pic(pics) {
    $(".drag").html("");
    if (pics.length != 0) {
        for (var i = 0; i < pics.length; i++) {
            if (pics[i].type.split('/')[0] == "image") {
                let reader = new FileReader();
                reader.readAsDataURL(pics[i]);
                $(reader).on('load', function () {
                    var img = `<img src="${reader.result}" alt="" style="width: 100%;height:fit-content">`;
                    $(".drag").append(img);
                })
            }
        };
        pic_file.files = pics;
    }
}
function change_pics(pics) {
	 $(".multi-photo").html("");
    if (pics.length != 0) {
        for (var i = 0; i < pics.length; i++) {
            if (pics[i].type.split('/')[0] == "image") {
                let reader = new FileReader();
                reader.readAsDataURL(pics[i]);
                $(reader).on('load', function () {
                    var img = `<img class="imgs" src="${reader.result}" alt="" style="width: 23%;height:fit-content">`;
                    $(".multi-photo").append(img);
                })
            }
        };
        pic_files.files = pics;
    }
}
$(document).ready(function () {
    msg_btn.addEventListener("click", say);
    info_btn.addEventListener("click", info);
    add_btn.addEventListener("click", add);
    no_btn.addEventListener("click", no);
    yes_btn.addEventListener("click", yes);
    drop_place.addEventListener("dragover", function (e) {
        e.preventDefault();
    })
    drop_place.addEventListener("drop", function (e) {
        e.preventDefault();
//        $(".drag").html("");
        var pics = e.dataTransfer.files;
        change_pic(pics);
    })
    $(document).on("click", function (e) {
        if (!$(e.target).hasClass("nothing") && !$(e.target).hasClass("head_btn") &&
            !$(e.target).hasClass("icon")) {
            $(".all_side").removeClass("on");
        }
        if ($(e.target).hasClass("imgs") && !$(e.target).hasClass("demo")) {
            $(".imgs").removeClass("demo");
            $(e.target).addClass("demo");
        } else if ($(e.target).hasClass("imgs") && $(e.target).hasClass("demo")) {
            $(e.target).removeClass("demo");
        } else {
            $(".imgs").removeClass("demo");
        }
    })
    $("#money").on('change', function (e) {
        var percent = parseFloat($('#percent').text()) / 100;
        var money = $("#money").val();
        var revenue = Math.round((1 - percent) * money);
        $("#profit").text(revenue);
    })
    $("#pic_file").on('change', function (e) {
        var pics = e.target.files;
        change_pic(pics);
    })
    $("#pic_files").on('change', function (e) {
        var pics = e.target.files;
        change_pics(pics);
    })
    $("#money").on('keyup', function (e) {
        console.log(e.which);
        this.value = this.value.replace(/\D/g, "");
    })
})


