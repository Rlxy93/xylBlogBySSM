package Service.impl;

import Dao.BlogDao;
import Entity.Blog;
import Service.BlogService;
import Service.impl.BlogServiceImpl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl
        implements BlogService {

    @Autowired
    private BlogDao blogDao;


    public String getBlogByCategoryId(String categoryId) {

        List<Blog> blogList = this.blogDao.getBlogByCategoryId(categoryId);

        JSONArray blogListJson = JSONArray.fromObject(blogList);

        return blogListJson.toString();

    }


    public String getBlogByBlogId(String blogId) {

        Blog blog = this.blogDao.getBlogByBlogId(blogId);

        JSONObject blogJson = JSONObject.fromObject(blog);

        return blogJson.toString();

    }


    public boolean deleteBlogByBlogId(String blogId) {

        this.blogDao.deleteBlogByBlogId(blogId);

        return true;

    }


    public String getAllBlog() {

        List<Blog> allBlog = this.blogDao.getAllBlog();

        JSONArray allBlogJson = JSONArray.fromObject(allBlog);

        return allBlogJson.toString();

    }


    public boolean addBlog(Blog blog) {

        try {

            this.blogDao.addBlog(blog);

            return true;

        } catch (Exception e) {

            return false;

        }

    }


    public String searchBlog(String searchContent) {

        List<Blog> blogBySearchContent = this.blogDao.searchBlog(searchContent);

        JSONArray blogBySearchContentJson = JSONArray.fromObject(blogBySearchContent);

        return blogBySearchContentJson.toString();

    }


    public String getBlogByCategory(String category) {

        List<Blog> blogByCategory = this.blogDao.getBlogByCategory(category);

        JSONArray blogByCategoryJson = JSONArray.fromObject(blogByCategory);

        return blogByCategoryJson.toString();

    }

}