

$("#login_btn").on("click", function() {
    var user_id = $("#user_id").val();
    var user_pwd = $("#user_pwd").val();


    if(user_id == "" || user_pwd == "") {
        alert("아이디,비밀번호,이름을 입력하세요.");
        $("#user_id").val("");
        $("#user_pwd").val("");
        $("#user_id").focus();
    } else {
        $.ajax({
            url : "/loginAction",
            type : "POST",
            cache : "false",
            dataType : "json",
            data : {
                    user_id      : user_id,
                    user_pwd   : user_pwd,

            },
            success: function(rs){
                if(rs == 0) {
                    alert("아이디나 비밀번호를 확인해주세요.");
                    $("#user_id").val("");
                    $("#user_pwd").val("");
                    $(user_id).focus();
                } else {
                    alert("로그인 성공");
                    window.location.href="/"
                }
            },
            error: function (request, status, error){
                if(status == 500) {
                    alert("데이터 처리중 에러가 발생하였습니다.");
                } else {
                    alert("알 수 없는 에러가 발생하였습니다.");
                }
            }
        });
    }
});

