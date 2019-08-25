package Service.impl;

import Dao.CommentDao;
import Entity.Comment;
import Service.CommentService;
import Service.impl.CommentServiceImpl;

import java.util.List;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl
        implements CommentService {
    @Autowired
    private CommentDao commentDao;

    public boolean addComment(Comment comment) {
        try {
            this.commentDao.addComment(comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String getComment() {
        List<Comment> commentList = this.commentDao.getComment();
        JSONArray commentJson = JSONArray.fromObject(commentList);
        return commentJson.toString();
    }


    public String getCommentByBlogId(String blogId) {
        List<Comment> commentList = this.commentDao.getCommentByBlogId(blogId);
        JSONArray commentJson = JSONArray.fromObject(commentList);
        return commentJson.toString();
    }
}