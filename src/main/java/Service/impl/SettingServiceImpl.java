package Service.impl;

import Dao.SettingDao;
import Entity.Setting;
import Service.SettingService;
import Service.impl.SettingServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SettingServiceImpl
        implements SettingService {
    @Autowired
    private SettingDao settingDao;

    public String getSetting() {
        Setting setting = this.settingDao.getSetting();
        JSONObject settingJson = JSONObject.fromObject(setting);
        return settingJson.toString();
    }


    public boolean editSetting(Setting setting) {
        try {
            this.settingDao.editSetting(setting);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}