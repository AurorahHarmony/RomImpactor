package manager.services;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import manager.models.ApiSettings;
import manager.models.General;
import manager.models.Settings;

public class SettingsService {
    private static final String SETTINGS_FILE = "settings.json";
    
    public static Settings loadSettings() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File(SETTINGS_FILE);
            if (file.exists()) {
                return mapper.readValue(file, Settings.class);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void writeSettings(Settings settings) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File(SETTINGS_FILE);
            mapper.writeValue(file, settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSettings(
            String deviceProfile,
            int manualScaleSize,
            boolean manualScale,
            String steamGridDbKey,
            boolean steamGridDb,
            String igdbClientId,
            String igdbSecret,
            boolean igdb
    ) {
        // Create new settings object
        Settings settings = new Settings();

        General general = new General();
        general.setDeviceProfile(deviceProfile);
        general.setManualScaleSize(manualScaleSize);
        general.setManualScale(manualScale);
        settings.setGeneral(general);

        ApiSettings apiSettings = new ApiSettings();
        apiSettings.setSteamGridDbKey(steamGridDbKey);
        apiSettings.setSteamGridDb(steamGridDb);
        apiSettings.setIgdbClientId(igdbClientId);
        apiSettings.setIgdbSecret(igdbSecret);
        apiSettings.setIgdb(igdb);
        settings.setApiSettings(apiSettings);

        // Save settings to the JSON file
        writeSettings(settings);
    }

}
