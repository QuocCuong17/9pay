package com.airbnb.lottie.parser;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.google.firebase.dynamiclinks.DynamicLink;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class MaskParser {
    private MaskParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006a, code lost:
    
        if (r1.equals(androidx.media3.exoplayer.upstream.CmcdData.Factory.STREAMING_FORMAT_SS) == false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Mask parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        boolean z;
        jsonReader.beginObject();
        Mask.MaskMode maskMode = null;
        boolean z2 = false;
        AnimatableShapeValue animatableShapeValue = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            char c = 3;
            switch (nextName.hashCode()) {
                case 111:
                    if (nextName.equals("o")) {
                        z = false;
                        break;
                    }
                    break;
                case 3588:
                    if (nextName.equals(DynamicLink.ItunesConnectAnalyticsParameters.KEY_ITUNES_CONNECT_PT)) {
                        z = true;
                        break;
                    }
                    break;
                case 104433:
                    if (nextName.equals("inv")) {
                        z = 2;
                        break;
                    }
                    break;
                case 3357091:
                    if (nextName.equals("mode")) {
                        z = 3;
                        break;
                    }
                    break;
            }
            z = -1;
            switch (z) {
                case false:
                    animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
                    break;
                case true:
                    animatableShapeValue = AnimatableValueParser.parseShapeData(jsonReader, lottieComposition);
                    break;
                case true:
                    z2 = jsonReader.nextBoolean();
                    break;
                case true:
                    String nextString = jsonReader.nextString();
                    nextString.hashCode();
                    switch (nextString.hashCode()) {
                        case 97:
                            if (nextString.equals("a")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 105:
                            if (nextString.equals(CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT)) {
                                c = 1;
                                break;
                            }
                            break;
                        case 110:
                            if (nextString.equals("n")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 115:
                            break;
                    }
                    c = 65535;
                    switch (c) {
                        case 0:
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            break;
                        case 1:
                            lottieComposition.addWarning("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            maskMode = Mask.MaskMode.MASK_MODE_INTERSECT;
                            break;
                        case 2:
                            maskMode = Mask.MaskMode.MASK_MODE_NONE;
                            break;
                        case 3:
                            maskMode = Mask.MaskMode.MASK_MODE_SUBTRACT;
                            break;
                        default:
                            Logger.warning("Unknown mask mode " + nextName + ". Defaulting to Add.");
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            break;
                    }
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return new Mask(maskMode, animatableShapeValue, animatableIntegerValue, z2);
    }
}
