function hc() {
    var aa = document.getElementById("search").value;
    if (aa == '') {
        alert("搜索内容不能为空！");
        return false;
    } else {
        return true;
    }
};

function addComment() {
    var word = document.getElementById('comment').value;
    var word1 = document.getElementById('name').value;
    if (word == '' && word1 == '') {
        alert("评论和昵称不能为空！");
        return false;
    }
    if (word == '') {
        alert("评论不能为空！");
        return false;
    }
    if (word1 == '') {
        alert("昵称不能为空！");
        return false;
    }
    var currentUrl = document.location.toString();
    var Url = currentUrl.split("?");

    var para = Url[1].substring(3);

    var comment = {};
    comment.id = para;
    comment.comment = $('#comment').val();
    comment.name = $('#name').val();
    var formData = new FormData();
    formData.append('comment', JSON.stringify(comment));
    var addCommentUrl = '/xylBlog/addComment';
    $.ajax({
        url: addCommentUrl,
        type: 'POST',
        contentType: false,
        processData: false,
        data: formData,
        cache: false,
        success: function (data) {
            if (data.success) {
                alert(data.commentInfo);
                window.location.reload();
            } else {
                alert(data.commentInfo);
            }
        }
    });
};
window.onscroll = function () {
    if (document.documentElement.scrollTop + document.body.scrollTop > 100)
        document.getElementById("button").style.display = "block";
    else
        document.getElementById("button").style.display = "none";
};

function t() {
    var top = document.getElementById("button");
    top.style.backgroundColor = "#D1D1D1";
};

function o() {
    var top = document.getElementById("button");
    top.style.backgroundColor = "#EAEAEA";
};