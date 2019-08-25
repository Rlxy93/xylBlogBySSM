package Service;

import Entity.Admin;

public interface AdminService {
  String getAdmin();
  
  String login(Admin paramAdmin);
  
  boolean editAdmin(Admin paramAdmin);
}