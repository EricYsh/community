function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    if (!content) {
        alert("Please input your comment!");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=8ca0d9325da346901696&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}


function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();

    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + "," + value);
        } else {
            $("#tag").val(value);
        }
    }

    // if (previous) {
    //     var index = 0;
    //     var appear = false; //记录value是否已经作为一个独立的标签出现过
    //     while (true) {
    //         index = previous.indexOf(value, index); //value字符串在previous中出现的位置
    //         if (index == -1) break;
    //         //判断previous中出现的value是否是另一个标签的一部分
    //         //即value的前一个和后一个字符都是逗号","或者没有字符时，才说明value是一个独立的标签
    //         if ((index == 0 || previous.charAt(index - 1) == ",")
    //             && (index + value.length == previous.length || previous.charAt(index + value.length) == ",")
    //         ) {
    //             appear = true;
    //             break;
    //         }
    //         index++; //用于搜索下一个出现位置
    //     }
    //     if (!appear) {
    //         //若value没有作为一个独立的标签出现过
    //         $("#tag").val(previous + ',' + value);
    //     }
    // }
    // else {
    //     $("#tag").val(value);
    // }
}