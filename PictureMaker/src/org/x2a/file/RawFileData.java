package org.x2a.file;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ethan on 6/16/2017.
 */
public class RawFileData {
    private final byte[] data;

    public RawFileData(ByteArrayOutputStream stream) {
        data = stream.toByteArray();
    }

    public RawFileData(byte[] data) {
        this.data = data;
    }

    public File writeToFile(String location, boolean overwrite) throws IOException {
        return writeToFile(new File(location), overwrite);
    }

    public File writeToFile(File file, boolean overwrite) throws IOException {
        if (!file.exists() || overwrite) {
            if (file.createNewFile()) {
                FileUtils.writeByteArrayToFile(file, data);
            }
            return file;
        } else {
            return null;
        }
    }
}
