package co.edu.uptc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilProperties {
    private static Properties gameProperties = new Properties();
    private static String fileName = "src/data/files/Config.properties";

    static {
        try {
            InputStream propertiesFile = new FileInputStream(fileName);
            gameProperties.load(propertiesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return gameProperties.getProperty(key);
    }

//    private static boolean isKeyAssigned(String key) {
//        String fireKey = gameProperties.getProperty("fireCannonKey");
//        String rightKey = gameProperties.getProperty("cannonRightMovementKey");
//        String leftKey = gameProperties.getProperty("cannonLeftMovementKey");
//        String exitKey = gameProperties.getProperty("exitWhileInGameKey");
//
//        return key.equals(fireKey) || key.equals(rightKey) || key.equals(leftKey) || key.equals(exitKey);
//    }
//
//    public static void setFireButtonKeyProperty(String value) {
//        if (!isKeyAssigned(value)) {
//            gameProperties.setProperty("fireCannonKey", value);
//            try {
//                gameProperties.store(new FileOutputStream(fileName), "");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    public static void setRightMovementKeyProperty(String value) {
//        if (!isKeyAssigned(value)) {
//            gameProperties.setProperty("cannonRightMovementKey", value);
//            try {
//                gameProperties.store(new FileOutputStream(fileName), "");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void setLeftMovementKeyProperty(String value) {
//        if (!isKeyAssigned(value)) {
//            gameProperties.setProperty("cannonLeftMovementKey", value);
//            try {
//                gameProperties.store(new FileOutputStream(fileName), "");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void setExitGameButtonKeyProperty(String value) {
//        if (!isKeyAssigned(value)) {
//            gameProperties.setProperty("exitWhileInGameKey", value);
//            try {
//                gameProperties.store(new FileOutputStream(fileName), "");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static String getFireButtonKeyProperty() {
//        Properties gameProperties = new Properties();
//        try (InputStream propertiesFile = new FileInputStream(fileName)) {
//            gameProperties.load(propertiesFile);
//            return KeyEvent.getKeyText(Integer.parseInt(gameProperties.getProperty("fireCannonKey")));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static String getRightMovementKeyProperty() {
//        Properties gameProperties = new Properties();
//        try (InputStream propertiesFile = new FileInputStream(fileName)) {
//            gameProperties.load(propertiesFile);
//            return KeyEvent.getKeyText(Integer.parseInt(gameProperties.getProperty("cannonRightMovementKey")));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static String getLeftMovementKeyProperty() {
//        Properties gameProperties = new Properties();
//        try (InputStream propertiesFile = new FileInputStream(fileName)) {
//            gameProperties.load(propertiesFile);
//            return KeyEvent.getKeyText(Integer.parseInt(gameProperties.getProperty("cannonLeftMovementKey")));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static String getExitGameButtonKeyProperty() {
//        Properties gameProperties = new Properties();
//        try (InputStream propertiesFile = new FileInputStream(fileName)) {
//            gameProperties.load(propertiesFile);
//            return KeyEvent.getKeyText(Integer.parseInt(gameProperties.getProperty("exitWhileInGameKey")));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
