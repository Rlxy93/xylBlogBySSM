package Service;

import Entity.Blog;

public interface BlogService {
  String getBlogByCategoryId(String paramString);
  
  String getBlogByBlogId(String paramString);
  
  boolean deleteBlogByBlogId(String paramString);
  
  String getAllBlog();
  
  boolean addBlog(Blog paramBlog);
  
  String searchBlog(String paramString);
  
  String getBlogByCategory(String paramString);
}