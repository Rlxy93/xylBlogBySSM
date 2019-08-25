$(function () {
    user = $.cookie('user');
    if (user == null) {
        alert("登录失败，请重新登录！");
        top.location.href="/xylBlog/admin/login";
    }
});