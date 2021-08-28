$("#signup_btn").on("click", function(){
    location.href="/signup";
});

function login_btn()
{window.location.href="/login";}


const cmntForm = document.forms['registerForm'];
cmntForm.addEventListener('submit', function(event){
    if($('#chkYn').val() == 0) {
        alert('중복 확인이 필요합니다.');
        $(user_id).focus();
        event.preventDefault();
    } else {
        return true;
    }
});

function idcheck(){
    var user_id = $("#user_id").val();
    if (user_id == ''){
        alert("아이디를 입력하세요.");
    }
    else {
        $.ajax({
            url: "/IdCheck",
            type: "POST",
            cache: "false",
            dataType: "json",
            data: {
                user_id: user_id
            },
            success: function (rs) {
                if (rs == 0) {
                    alert("사용 가능합니다.");
                    $('#chkYn').val(1);
                } else {
                    alert("중복된 아이디입니다.");
                    $("#user_id").val("");
                    $(user_id).focus();
                }
            },
            error: function (request, status, error) {
                if (status == 500) {
                    alert("데이터 처리중 에러가 발생하였습니다.");
                } else {
                    alert("알 수 없는 에러가 발생하였습니다.");
                }
            }

        });
    }
};

