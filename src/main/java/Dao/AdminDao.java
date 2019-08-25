package Dao;

import Entity.Admin;

public interface AdminDao {
  Admin getAdmin();
  
  Admin login(Admin paramAdmin);
  
  void editAdmin(Admin paramAdmin);
}