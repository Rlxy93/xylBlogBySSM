package Service;

import Entity.Blog;

public interface CategoryService {
  boolean addCategory(String paramString);
  
  String getAllCategory();
  
  boolean updateCategoryByBlogId(String paramString);
  
  boolean updateCategoryByCategoryjia(Blog paramBlog);
  
  boolean updateCategoryByCategoryjian(Blog paramBlog);
}