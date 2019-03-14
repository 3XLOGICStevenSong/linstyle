package com.djb.highway.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceUtil {
    private ResourceBundle[] resourceBundle = null;

    private static final String[] strCompResource = { "action_processor" };

    private static final String[] strEndStrings = {};

    public ResourceUtil(String bundleName) {
        boolean blnAdd = false;

        for (int i = 0; i < ResourceUtil.strCompResource.length; i++) {
            if (ResourceUtil.strCompResource[i].equals(bundleName)) {
                blnAdd = true;
            }
        }
        if (blnAdd) {
            resourceBundle = new ResourceBundle[ResourceUtil.strEndStrings.length + 1];

            resourceBundle[0] = ResourceBundle.getBundle(bundleName);

            for (int i = 1; i < resourceBundle.length; i++) {
                resourceBundle[i] = ResourceBundle.getBundle(bundleName + ResourceUtil.strEndStrings[i - 1]);
            }

        } else {
            resourceBundle = new ResourceBundle[1];

            resourceBundle[0] = ResourceBundle.getBundle(bundleName);
        }

    }

    public String getString(String key) {

        String strResult = null;

        for (int i = 0; i < resourceBundle.length && strResult == null; i++) {
            try {
                strResult = resourceBundle[i].getString(key);
                /*
                 * System.out.println("ResourceUtil.java#getString@【" + i + "】"
                 * + "【" + strResult + "】");
                 */
            } catch (MissingResourceException e) {
            }
        }

        return strResult;
    }
}