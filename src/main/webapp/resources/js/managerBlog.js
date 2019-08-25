$(function () {
    var getBlogUrl = '/xylBlog/admin/getAllBlog';
    $.ajax({
        url: getBlogUrl,
        type: 'POST',
        contentType: false,
        processData: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                var blog = JSON.parse(data.getBlogInfo);
                $(document).ready(function () {
                    var s = "";
                    for (var i = 0; i < blog.length; i++) {
                        s += "<tr>\n" +
                            "        <td>" + (i + 1) + "</td>\n" +
                            "        <td>" + blog[i].title + "</td>\n" +
                            "        <td>" + blog[i].time + "</td>\n" +
                            "        <td><a onclick=\"return del('/xylBlog/admin/deleteBlog?id=" + blog[i].id + "')\" href=>删除</a>\n" +
                            "            <a href=\"/xylBlog/admin/editBlog?id=" + blog[i].id + "\">修改</a></td>\n" +
                            "    </tr>";
                    }
                    $("#blogList").append(s);
                });
            } else {
                alert(data.getBlogInfo);
            }
        }
    });
});

function del(deleteUrl) {
    var bool = confirm("是否删除此博客？");
    if (bool) {
        $(function () {
            $.ajax({
                url: deleteUrl,
                type: 'POST',
                contentType: false,
                processData: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        alert(data.deleteInfo);
                    } else {
                        alert(data.deleteInfo);
                    }
                }
            });
        });
        return true;
    } else {
        alert("删除失败！");
        return false;
    }
}