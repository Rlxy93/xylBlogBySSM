package Service.impl;

import Dao.AdminDao;
import Entity.Admin;
import Service.AdminService;
import Service.impl.AdminServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl
        implements AdminService {
    @Autowired
    private AdminDao adminDao;

    public String getAdmin() {
        Admin admin = this.adminDao.getAdmin();
        JSONObject adminJson = JSONObject.fromObject(admin);
        return adminJson.toString();
    }


    public String login(Admin admin) {
        Admin login = this.adminDao.login(admin);
        JSONObject loginJson = JSONObject.fromObject(login);
        return loginJson.toString();
    }


    public boolean editAdmin(Admin admin) {
        try {
            this.adminDao.editAdmin(admin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}