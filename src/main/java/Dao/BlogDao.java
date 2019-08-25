package Dao;

import Entity.Blog;
import java.util.List;

public interface BlogDao {
  List<Blog> getBlogByCategoryId(String paramString);
  
  Blog getBlogByBlogId(String paramString);
  
  void deleteBlogByBlogId(String paramString);
  
  List<Blog> getAllBlog();
  
  void addBlog(Blog paramBlog);
  
  List<Blog> searchBlog(String paramString);
  
  List<Blog> getBlogByCategory(String paramString);
}