package org.x2a.file;

import org.x2a.image.ImageData;
import org.x2a.image.ImagePackageData;

import java.io.File;

/**
 * Created by Ethan on 6/16/2017.
 */
public interface ImagePackageSaver {
    File saveImagePackage(ImagePackageData data);
    File saveSingleImage(ImageData data);
}
