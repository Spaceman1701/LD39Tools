package org.x2a.picture;

import java.util.List;

/**
 * Created by ethan on 7/16/17.
 */
public class PalletizedPicture {

    private int palletIndex;
    private int[] colors;


    public PalletizedPicture(int palletIndex, Pallet pallet, Picture picture) {
        this.colors = computeColors(picture, pallet);
        this.palletIndex = palletIndex;
    }

    private int[] computeColors(Picture picture, Pallet pallet) {
        int[] pixels = new int[8 * 8];
        for (int pixel = 0; pixel < picture.getPixels().length; pixel++) {
            for (int i = 0; i < 16; i++) {
                if (picture.getPixels()[pixel].equals(pallet.getColors().get(i))) {
                    pixels[pixel] = i;
                }
            }
        }

        return pixels;
    }
}
