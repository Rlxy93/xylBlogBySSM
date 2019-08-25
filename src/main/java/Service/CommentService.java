package Service;

import Entity.Comment;

public interface CommentService {
  boolean addComment(Comment paramComment);
  
  String getComment();
  
  String getCommentByBlogId(String paramString);
}