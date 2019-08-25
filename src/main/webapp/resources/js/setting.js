$(function () {
    var getSettingUrl = '/xylBlog/admin/getSettingInfo';
    $.ajax({
        url: getSettingUrl,
        type: 'POST',
        contentType: false,
        processData: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                var setting = JSON.parse(data.settingInfo);
                $('#mainblog_name').val(setting.mainblogName);
                $('#blog_name').val(setting.blogName);
                $('#index').val(setting.index1);
                $('#beian').val(setting.beian);
                $('#admin').val(setting.admin);
                $('#qq').val(setting.qq);
            } else {
                alert(data.settingInfo);
                window.location.href = '/xylBlog/admin/login';
            }
        }
    });
});

$(function () {
    $('#edit').click(function () {
        var editSettingUrl = '/xylBlog/admin/editSetting';
        var setting = {};
        setting.mainblogName = $('#mainblog_name').val();
        setting.blogName = $('#blog_name').val();
        setting.index = $('#index').val();
        setting.beian = $('#beian').val();
        setting.admin = $('#admin').val();
        setting.qq = $('#qq').val();
        var formData = new FormData();
        formData.append('setting', JSON.stringify(setting));
        $.ajax({
            url: editSettingUrl,
            type: 'POST',
            contentType: false,
            processData: false,
            cache: false,
            data: formData,
            success: function (data) {
                if (data.success) {
                    alert(data.editSettingInfo);
                } else {
                    alert(data.editSettingInfo);
                }
            }
        });
    });
});