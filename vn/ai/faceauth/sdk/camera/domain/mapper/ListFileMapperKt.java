package vn.ai.faceauth.sdk.camera.domain.mapper;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import vn.ai.faceauth.sdk.core.extensions.PrimitiveExtensionsKt;
import vn.ai.faceauth.sdk.domain.model.PhotoResultDomain;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0001H\u0000¨\u0006\u0004"}, d2 = {"toPhotoResult", "", "Lvn/ai/faceauth/sdk/domain/model/PhotoResultDomain;", "", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ListFileMapperKt {
    public static final List<PhotoResultDomain> toPhotoResult(List<String> list) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (String str : list) {
            arrayList.add(new PhotoResultDomain(PrimitiveExtensionsKt.getFileNameWithoutExtension(str), PrimitiveExtensionsKt.encoderFilePath(str), PrimitiveExtensionsKt.toBitmap(str)));
        }
        return arrayList;
    }
}
