package Service.impl;

import Dao.CategoryDao;
import Entity.Blog;
import Entity.Category;
import Service.CategoryService;
import Service.impl.CategoryServiceImpl;

import java.util.List;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl
        implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public boolean addCategory(String category) {
        try {
            this.categoryDao.addCategory(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String getAllCategory() {
        List<Category> allCategory = this.categoryDao.getAllCategory();
        JSONArray allCategoryJson = JSONArray.fromObject(allCategory);
        return allCategoryJson.toString();
    }


    public boolean updateCategoryByBlogId(String BlogId) {
        try {
            this.categoryDao.updateCategoryByBlogId(BlogId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean updateCategoryByCategoryjia(Blog blog) {
        try {
            this.categoryDao.updateCategoryByCategoryjia(blog);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean updateCategoryByCategoryjian(Blog blog) {
        try {
            this.categoryDao.updateCategoryByCategoryjian(blog);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}