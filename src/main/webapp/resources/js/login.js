$(function () {
    var loginUrl = '/xylBlog/xylBlog/login';
    $('#login').click(function () {
        var userCheck = {};
        userCheck.user = $('#user').val();
        userCheck.password = $('#password').val();
        var formData = new FormData();
        formData.append('userCheck', JSON.stringify(userCheck));
        $.ajax({
            url: loginUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    window.location.href = '/xylBlog/admin/index.html';
                } else {
                    alert(data.loginInfo);
                    window.location.href = '/xylBlog/admin/login';
                }
            }
        });
    });
});


$(function () {
    var loginUrl = '/xylBlog/xylBlog/login';
    $('.denglu').keyup(function () {

        if (event.keyCode == 13) {
            var userCheck = {};
            userCheck.user = $('#user').val();
            userCheck.password = $('#password').val();
            var formData = new FormData();
            formData.append('userCheck', JSON.stringify(userCheck));
            $.ajax({
                url: loginUrl,
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        window.location.href = '/xylBlog/admin/index.html';
                    } else {
                        alert(data.loginInfo);
                        window.location.href = '/xylBlog/admin/login';
                    }
                }
            });
        }
        ;
    });
});
