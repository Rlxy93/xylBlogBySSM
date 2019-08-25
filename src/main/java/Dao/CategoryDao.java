package Dao;

import Entity.Blog;
import Entity.Category;
import java.util.List;

public interface CategoryDao {
  void addCategory(String paramString);
  
  List<Category> getAllCategory();
  
  void updateCategoryByBlogId(String paramString);
  
  void updateCategoryByCategoryjia(Blog paramBlog);
  
  void updateCategoryByCategoryjian(Blog paramBlog);
}