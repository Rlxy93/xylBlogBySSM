$(function () {
    var currentUrl = document.location.toString();
    var Url = currentUrl.split("?");
    var para = Url[1];
    var getBlogListByCategoryUrl = '/xylBlog/getBlogListByCategory?' + para;
    $.ajax({
        url: getBlogListByCategoryUrl,
        type: 'POST',
        contentType: false,
        processData: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                var blogListByCatgory = JSON.parse(data.blogListByCatgory);
                $(document).ready(function () {
                    var s = "";
                    for (var i = 0; i < blogListByCatgory.length; i++) {
                        if (blogListByCatgory[i].mm == "on") {
                            s += "\n" +
                                "        <div id=\"animated_div\" style=\"border:1px solid silver;\">\n" +
                                "            <br>\n" +
                                "            <img src='/xylBlog/resources/images/lock.png' style='width:20px;' title='已锁定'>\n" +
                                "            <a style=\"font-size:24px;\" href=\"./content?id=" + blogListByCatgory[i].id + "\">" + blogListByCatgory[i].title + "</a><br>\n" +
                                "            <img src='/xylBlog/resources/images/clock.png' style='width:20px;' title='发表时间'>\n" +
                                "            <span style=\"font-size:18px;\">" + blogListByCatgory[i].time + " &nbsp;\n" +
                                "\t\t<img src='/xylBlog/resources/images/male.png' style='width:20px;' title='作者'>" + setting.admin + "</span>\n" +
                                "            <hr>\n" +
                                "            <div style='width:70%;margin:0 auto;font-size:20px;'>\n" +
                                "                该博客已加密！\n" +
                                "            </div>\n" +
                                "        </div>";
                        } else {
                            s += "\n" +
                                "        <div id=\"animated_div\" style=\"border:1px solid silver;\">\n" +
                                "            <br>\n" +
                                "            <a style=\"font-size:24px;\" href=\"./content?id=" + blogListByCatgory[i].id + "\">" + blogListByCatgory[i].title + "</a><br>\n" +
                                "            <img src='/xylBlog/resources/images/clock.png' style='width:20px;' title='发表时间'>\n" +
                                "            <span style=\"font-size:18px;\">" + blogListByCatgory[i].time + " &nbsp;\n" +
                                "\t\t<img src='/xylBlog/resources/images/male.png' style='width:20px;' title='作者'>" + setting.admin + "</span>\n" +
                                "            <hr>\n" +
                                "            " + blogListByCatgory[i].body + "...\n" +
                                "            <br><a style='color:black;' href=\"/xylBlog/resources/content?id=" + blogListByCatgory[i].id + "\">[Read More]</a>\n" +
                                "        </div>";
                        }
                    }
                    $("#blogListByCatgory").append(s);
                });
            } else {
                alert(data.blogListByCatgory);
            }
        }
    });
});