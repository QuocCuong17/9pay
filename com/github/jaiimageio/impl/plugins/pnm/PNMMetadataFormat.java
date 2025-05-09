package com.github.jaiimageio.impl.plugins.pnm;

import com.google.common.net.HttpHeaders;
import java.util.Hashtable;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadataFormatImpl;

/* loaded from: classes3.dex */
public class PNMMetadataFormat extends IIOMetadataFormatImpl {
    private static PNMMetadataFormat instance;
    private static Hashtable parents;
    String resourceBaseName;

    static {
        Hashtable hashtable = new Hashtable();
        parents = hashtable;
        hashtable.put("FormatName", "com_sun_media_imageio_plugins_pnm_image_1.0");
        parents.put("Variant", "com_sun_media_imageio_plugins_pnm_image_1.0");
        parents.put(HttpHeaders.WIDTH, "com_sun_media_imageio_plugins_pnm_image_1.0");
        parents.put("Height", "com_sun_media_imageio_plugins_pnm_image_1.0");
        parents.put("MaximumSample", "com_sun_media_imageio_plugins_pnm_image_1.0");
        parents.put("Comment", "com_sun_media_imageio_plugins_pnm_image_1.0");
    }

    public static synchronized PNMMetadataFormat getInstance() {
        PNMMetadataFormat pNMMetadataFormat;
        synchronized (PNMMetadataFormat.class) {
            if (instance == null) {
                instance = new PNMMetadataFormat();
            }
            pNMMetadataFormat = instance;
        }
        return pNMMetadataFormat;
    }

    PNMMetadataFormat() {
        super("com_sun_media_imageio_plugins_pnm_image_1.0", 1);
        String str = getClass().getName() + "Resources";
        this.resourceBaseName = str;
        setResourceBaseName(str);
        addElements();
    }

    private void addElements() {
        addElement("FormatName", getParent("FormatName"), 0);
        addElement("Variant", getParent("Variant"), 0);
        addElement(HttpHeaders.WIDTH, getParent(HttpHeaders.WIDTH), 0);
        addElement("Height", getParent("Height"), 0);
        addElement("MaximumSample", getParent("MaximumSample"), 0);
        addElement("Comment", getParent("Comment"), 0);
    }

    public String getParent(String str) {
        return (String) parents.get(str);
    }

    public boolean canNodeAppear(String str, ImageTypeSpecifier imageTypeSpecifier) {
        return getParent(str) != null;
    }
}
