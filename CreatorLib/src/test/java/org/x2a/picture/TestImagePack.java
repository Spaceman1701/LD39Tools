package org.x2a.picture;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by ethan on 7/16/17.
 */
public class TestImagePack {

    @Before
    public void setUp() {
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    public void testBuildPack() throws IOException {
        File imageFile = new File(getClass().getClassLoader().getResource("image.png").getFile());
        File palletFile = new File(getClass().getClassLoader().getResource("pallet.png").getFile());

        PngPicture image = new PngPicture(imageFile);
        PngPicture palletImage = new PngPicture(palletFile);

        Pallet pallet = new Pallet(palletImage.getBytes(), palletImage.isHasAlpha());
        Picture picture = new Picture(image.getBytes(), image.isHasAlpha());

        ImagePack pack = new ImagePack.ImagePackBuilder(256, 32)
                .addPallets(pallet)
                .addPicture(picture)
                .build();
    }
}
