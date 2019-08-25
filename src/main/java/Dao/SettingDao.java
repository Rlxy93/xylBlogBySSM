package Dao;

import Entity.Setting;

public interface SettingDao {
  Setting getSetting();
  
  void editSetting(Setting paramSetting);
}