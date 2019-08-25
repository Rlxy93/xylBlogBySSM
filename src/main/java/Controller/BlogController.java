package Controller;

import Controller.BlogController;
import Entity.Admin;
import Entity.Blog;
import Entity.Comment;
import Entity.Setting;
import Service.impl.AdminServiceImpl;
import Service.impl.BlogServiceImpl;
import Service.impl.CategoryServiceImpl;
import Service.impl.CommentServiceImpl;
import Service.impl.SettingServiceImpl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Transactional
public class BlogController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private SettingServiceImpl settingService;

    @RequestMapping({"/admin/editSetting"})
    @ResponseBody
    public Map<String, Object> editSetting(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String settingJson = request.getParameter("setting");
        Setting setting = (Setting) JSONObject.toBean(JSONObject.fromObject(settingJson), Setting.class);
        boolean isEdit = this.settingService.editSetting(setting);
        if (!isEdit) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("editSettingInfo", "修改失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("editSettingInfo", "修改成功！");
        return modelMap;
    }

    @RequestMapping({"/admin/editAdmin"})
    @ResponseBody
    public Map<String, Object> editAdmin(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String adminJson = request.getParameter("admin");
        Admin admin = (Admin) JSONObject.toBean(JSONObject.fromObject(adminJson), Admin.class);
        boolean isEdit = this.adminService.editAdmin(admin);
        if (!isEdit) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("editAdminInfo", "修改失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("editAdminInfo", "修改成功！");
        return modelMap;
    }

    @Autowired
    private CategoryServiceImpl categoryService;

    @RequestMapping({"/admin/deleteBlog"})
    @ResponseBody
    public Map<String, Object> deleteBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String blogId = request.getParameter("id");
        Blog blog = new Blog();
        blog.setId(Integer.parseInt(blogId));
        String blogByBlogId = blogService.getBlogByBlogId(blogId);
        Blog b = (Blog) JSONObject.toBean(JSONObject.fromObject(blogByBlogId),Blog.class);
        try {
            this.blogService.deleteBlogByBlogId(blogId);
            categoryService.updateCategoryByCategoryjian(b);
            modelMap.put("success", Boolean.valueOf(true));
            modelMap.put("deleteInfo", "删除成功！");
            return modelMap;
        } catch (Exception e) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("deleteInfo", "删除失败！");
            return modelMap;
        }
    }

    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping({"/admin/editBlogByBlog"})
    @ResponseBody
    public Map<String, Object> editBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String blogJson = request.getParameter("blog");
        String oldCategory = request.getParameter("oldCategory");
        Blog blog = (Blog) JSONObject.toBean(JSONObject.fromObject(blogJson), Blog.class);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String time = format.format(date);
        blog.setTime(time);
        if (!blog.getYzmm().equals("")) {
            blog.setMm("on");
        }
        Blog oldBlog = new Blog();
        oldBlog.setCategory(oldCategory);
        this.blogService.deleteBlogByBlogId(String.valueOf(blog.getId()));
        this.categoryService.updateCategoryByCategoryjian(oldBlog);
        boolean isAddBlog = this.blogService.addBlog(oldBlog);
        if (!isAddBlog) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("blogInfo", "修改博客失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("blogInfo", "修改博客成功！");
        return modelMap;
    }


    @RequestMapping({"/admin/editBlogByBlogId"})
    @ResponseBody
    public Map<String, Object> editBlogByBlogId(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String blogId = request.getParameter("id");
        String blog = this.blogService.getBlogByBlogId(blogId);
        if (blog == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("blogInfo", "获取博客信息失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("blogInfo", blog);
        return modelMap;
    }

    @RequestMapping({"/admin/addBlog"})
    @ResponseBody
    public Map<String, Object> addBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String blogJson = request.getParameter("blog");
        Blog blog = (Blog) JSONObject.toBean(JSONObject.fromObject(blogJson), Blog.class);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String time = format.format(date);
        blog.setTime(time);
        if (!blog.getYzmm().equals("")) {
            blog.setMm("on");
        }
        boolean isAddBlog = this.blogService.addBlog(blog);
        categoryService.updateCategoryByCategoryjia(blog);
        if (!isAddBlog) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("blogInfo", "添加博客失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("blogInfo", "添加博客成功！");
        return modelMap;
    }

    @RequestMapping({"/getBlogListByCategory"})
    @ResponseBody
    public Map<String, Object> getBlogListByCategory(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String category = "";
        try {
            category = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String blogListByCategory = this.blogService.getBlogByCategory(category);

        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regEx_html = "<[^>]+>";

        Pattern p_script = Pattern.compile(regEx_script, 2);
        Matcher m_script = p_script.matcher(blogListByCategory);
        blogListByCategory = m_script.replaceAll("");

        Pattern p_style = Pattern.compile(regEx_style, 2);
        Matcher m_style = p_style.matcher(blogListByCategory);
        blogListByCategory = m_style.replaceAll("");

        Pattern p_html = Pattern.compile(regEx_html, 2);
        Matcher m_html = p_html.matcher(blogListByCategory);
        blogListByCategory = m_html.replaceAll("");
        blogListByCategory = blogListByCategory.replace("&nbsp;", " ");

        if (blogListByCategory == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("blogListByCatgory", "获取博客列表失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("blogListByCatgory", blogListByCategory);
        return modelMap;
    }

    @RequestMapping({"/searchBlogList"})
    @ResponseBody
    public Map<String, Object> searchBlogList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String searchContent = "";
        try {
            searchContent = new String(request.getParameter("searchContent").getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String blogListBySearch = this.blogService.searchBlog(searchContent);

        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regEx_html = "<[^>]+>";

        Pattern p_script = Pattern.compile(regEx_script, 2);
        Matcher m_script = p_script.matcher(blogListBySearch);
        blogListBySearch = m_script.replaceAll("");

        Pattern p_style = Pattern.compile(regEx_style, 2);
        Matcher m_style = p_style.matcher(blogListBySearch);
        blogListBySearch = m_style.replaceAll("");

        Pattern p_html = Pattern.compile(regEx_html, 2);
        Matcher m_html = p_html.matcher(blogListBySearch);
        blogListBySearch = m_html.replaceAll("");
        blogListBySearch = blogListBySearch.replace("&nbsp;", " ");

        if (blogListBySearch == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("searchBlogList", "搜索失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("searchBlogList", blogListBySearch);
        return modelMap;
    }

    @RequestMapping({"/addComment"})
    @ResponseBody
    public Map<String, Object> addComment(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String commentJson = request.getParameter("comment");
        Comment comment = (Comment) JSONObject.toBean(JSONObject.fromObject(commentJson), Comment.class);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String time = format.format(date);
        comment.setTime(time);
        boolean isAddComment = this.commentService.addComment(comment);
        if (!isAddComment) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("commentInfo", "评论失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("commentInfo", "评论成功！");
        return modelMap;
    }

    @RequestMapping({"/getCommentListByBlogId"})
    @ResponseBody
    public Map<String, Object> getCommentListByBlogId(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String blogId = request.getParameter("id");
        String commentJson = this.commentService.getCommentByBlogId(blogId);
        if (commentJson == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("commentList", "获取评论列表失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("commentList", commentJson);
        return modelMap;
    }

    @RequestMapping({"/getBlogContent"})
    @ResponseBody
    public Map<String, Object> getBlogContent(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String blogId = request.getParameter("id");
        String blogContentJson = this.blogService.getBlogByBlogId(blogId);
        if (blogContentJson == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("blogContent", "获取博客详情失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("blogContent", blogContentJson);
        return modelMap;
    }

    @RequestMapping({"/getBlogList"})
    @ResponseBody
    public Map<String, Object> getBlogList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String allBlogJson = this.blogService.getAllBlog();

        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regEx_html = "<[^>]+>";

        Pattern p_script = Pattern.compile(regEx_script, 2);
        Matcher m_script = p_script.matcher(allBlogJson);
        allBlogJson = m_script.replaceAll("");

        Pattern p_style = Pattern.compile(regEx_style, 2);
        Matcher m_style = p_style.matcher(allBlogJson);
        allBlogJson = m_style.replaceAll("");

        Pattern p_html = Pattern.compile(regEx_html, 2);
        Matcher m_html = p_html.matcher(allBlogJson);
        allBlogJson = m_html.replaceAll("");
        allBlogJson = allBlogJson.replace("&nbsp;", " ");

        if (allBlogJson == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("blogList", "获取博客列表失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("blogList", allBlogJson);
        return modelMap;
    }

    @RequestMapping({"/getCommentList"})
    @ResponseBody
    public Map<String, Object> getCommentList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String commentJson = this.commentService.getComment();
        if (commentJson == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("commentList", "获取评论列表失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("commentList", commentJson);
        return modelMap;
    }


    @RequestMapping({"/getCategoryList"})
    @ResponseBody
    public Map<String, Object> getCategoryList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String allCategoryJson = this.categoryService.getAllCategory();
        if (allCategoryJson == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("categoryList", "获取分类列表失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("categoryList", allCategoryJson);
        return modelMap;
    }

    @RequestMapping({"/getCategorySize"})
    @ResponseBody
    public Map<String, Object> getCategorySize(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String allCategory = this.categoryService.getAllCategory();
        JSONArray jsonArray = JSONArray.fromObject(allCategory);
        List<Object> list = new ArrayList<Object>();
        for (Object arr : jsonArray) {
            list.add(arr);
        }
        int count = list.size();
        if (count == 0) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("categorySize", "获取分类数量失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("categorySize", Integer.valueOf(count));
        return modelMap;
    }

    @RequestMapping({"/admin/getSettingInfo"})
    @ResponseBody
    public Map<String, Object> getSettingInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String settingJson = this.settingService.getSetting();
        if (settingJson == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("settingInfo", "获取系统设置失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("settingInfo", settingJson);
        return modelMap;
    }

    @RequestMapping({"/admin/getAdminInfo"})
    @ResponseBody
    public Map<String, Object> getAdminInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String adminJson = this.adminService.getAdmin();
        if (adminJson == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("adminInfo", "获取管理员账户失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("adminInfo", adminJson);
        return modelMap;
    }

    @RequestMapping({"/admin/getAllBlog"})
    @ResponseBody
    public Map<String, Object> getAllBlog(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String allBlog = this.blogService.getAllBlog();
        if (allBlog == null) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("getBlogInfo", "获取博客列表失败！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        modelMap.put("getBlogInfo", allBlog);
        return modelMap;
    }


    @RequestMapping({"/xylBlog/login"})
    @ResponseBody
    public Map<String, Object> getIndexCarouselProduct(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String userCheck = request.getParameter("userCheck");
        Admin admin = (Admin) JSONObject.toBean(JSONObject.fromObject(userCheck), Admin.class);
        String adminJson = this.adminService.login(admin);
        if (adminJson.equals("null")) {
            modelMap.put("success", Boolean.valueOf(false));
            modelMap.put("loginInfo", "用户名或密码错误！");
            return modelMap;
        }
        modelMap.put("success", Boolean.valueOf(true));
        Cookie cookie = new Cookie("user", admin.getUser());
        cookie.setPath("/");
        response.addCookie(cookie);
        return modelMap;
    }
}