package org.x2a.picture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethan on 7/16/17.
 */
public class ImagePack {

    public static class ImagePackBuilder {
        private final int maxPictures;
        private final int maxPallets;
        private List<Picture> picutes;
        private List<Pallet> pallets;

        public ImagePackBuilder(int maxPictures, int maxPallets) {
            this.maxPictures = maxPictures;
            this.maxPallets = maxPallets;
            this.picutes = new ArrayList<>();
        }

        public void addPicture(Picture p) {
            if (picutes.size() + 1 >= maxPictures) {
                throw new IllegalStateException("too many images");
            }
            this.picutes.add(p);
        }

        public void addPallets(Pallet p) {
            if (pallets.size() + 1 >= maxPallets) {
                throw new IllegalStateException("too many pallets");
            }
            this.pallets.add(p);
        }

        public ImagePack build() {
            return new ImagePack(maxPictures, maxPallets, picutes, pallets);
        }
    }

    private final List<Picture> pictures;
    private final List<Pallet> pallets;
    private final List<PalletizedPicture> palletizedPictures;

    private final int maxPictures;
    private final int maxPallets;

    public ImagePack(int maxPictures, int maxPallets, List<Picture> pictures, List<Pallet> pallets) {
        this.maxPictures = maxPictures;
        this.maxPallets = maxPallets;
        this.pictures = pictures;
        this.pallets = pallets;
        this.palletizedPictures = palletizeImages();
    }


    private List<PalletizedPicture> palletizeImages() {
        List<PalletizedPicture> pPictures = new ArrayList<>();
        for (Picture picture : pictures) {
            for (int i = 0; i < pallets.size(); i++) {
                if (pallets.get(i).canDefinePicture(picture)) {
                    PalletizedPicture palletizedPicture
                            = new PalletizedPicture(i, pallets.get(i), picture);
                    pPictures.add(palletizedPicture);
                }
            }
        }
        return pPictures;
    }
}
