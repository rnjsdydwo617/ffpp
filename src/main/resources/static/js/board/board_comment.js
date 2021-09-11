//
// var board_code = '${list.board_code}'; //게시글 번호
//
// $("#commentInsertBtn").on("click", function() {
//     var insertData = $('[name=commentInsertForm]').serialize();
//     commentInsert(insertData);
//     alert("버튼눌림");
// })
//
//
//     function commentInsert(insertData) {
//         $.ajax({
//             url: '/CommRegister',
//             type: 'post',
//             data: insertData,
//             success: function (data) {
//                 if (data == 1) {
//                     commentList(); //댓글 작성 후 댓글 목록 reload
//                     $('[name=content]').val('');
//                 }
//             }
//         })
//     };


//
let a = window.location.pathname;
let b = a.split("/");
let c = b[2];
var board_code = 'S00000'; //게시글 번호 save

$('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시
    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
    commentInsert(insertData); //Insert 함수호출(아래)
});



//댓글 목록 save sabve
function commentList(){
    $.ajax({
        url : '/comment/list/'+c,
        type : 'POST',
        success : function(data){

            var a ='';
            $.each(data, function(key, value){
                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+value.cno+'">'+'작성일 : '+value.create_DAY+' / 작성자 : ' +value.user_ID;
                a += '<a onclick="commentUpdate('+value.cno+',\''+value.com_CONTENT+'\');"> 수정 </a>';
                a += '<a onclick="commentDelete('+value.cno+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+value.cno+'"> <p> 내용 : '+value.com_CONTENT +'</p>';
                a += '</div></div>';
            });
            $(".commentList").html(a);
        }
    });
}
//댓글 등록

function commentInsert(insertData){

    $.ajax({
        url : '/comment/insert',
        type : 'post',
        data : insertData,
        success : function(data){
            if(data == 1) {
                commentList(); //댓글 작성 후 댓글 목록 reload
                $('[name=content]').val('');
            }
        }
    });
}

//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경
function commentUpdate(cno, content){
    var a ='';

    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+cno+');">수정</button> </span>';
    a += '</div>';

    $('.commentContent'+cno).html(a);

}
//댓글 삭제
function commentDelete(cno){
    $.ajax({
        url : 'delete/'+cno,
        type : 'post',
        success : function(data){
            if(data == 1) commentList(bno); //댓글 삭제후 목록 출력
        }
    });
}






$(document).ready(function(){
    commentList(); //페이지 로딩시 댓글 목록 출력
});








