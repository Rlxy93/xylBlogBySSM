package Dao;

import Entity.Comment;
import java.util.List;

public interface CommentDao {
  void addComment(Comment paramComment);
  
  List<Comment> getComment();
  
  List<Comment> getCommentByBlogId(String paramString);
}