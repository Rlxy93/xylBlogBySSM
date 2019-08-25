package Service;

import Entity.Setting;

public interface SettingService {
  String getSetting();
  
  boolean editSetting(Setting paramSetting);
}