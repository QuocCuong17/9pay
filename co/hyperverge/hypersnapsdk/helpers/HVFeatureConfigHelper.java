package co.hyperverge.hypersnapsdk.helpers;

import co.hyperverge.hypersnapsdk.data.models.FeatureConfig;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class HVFeatureConfigHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.HVFeatureConfigHelper";

    public static Map<String, FeatureConfig> extractFeatureMap(List<FeatureConfig> list, List<FeatureConfig> list2) {
        String str = TAG;
        HVLogUtils.d(str, "extractFeatureMap() called with: sdkFeatureConfigs = [" + list + "], vsFeatureConfigs = [" + list2 + "]");
        HashMap hashMap = new HashMap();
        processFeatureConfigs(hashMap, list);
        processFeatureConfigs(hashMap, list2);
        HVLogUtils.d(str, "extractFeatureMap: featureToggleMap: " + hashMap);
        return hashMap;
    }

    private static void processFeatureConfigs(HashMap<String, FeatureConfig> hashMap, List<FeatureConfig> list) {
        HVLogUtils.d(TAG, "processFeatureConfigs() called with: featureToggleMap = [" + hashMap + "], featureConfigs = [" + list + "]");
        for (FeatureConfig featureConfig : list) {
            featureConfig.shouldEnable();
            hashMap.put(featureConfig.getId(), featureConfig);
        }
    }

    public static Map<String, FeatureConfig> getDefaultFeatureMap() {
        HVLogUtils.d(TAG, "getDefaultFeatureMap() called");
        return extractFeatureMap(Collections.singletonList(FeatureConfig.builder().id("camera2").enable(false).overrides(Arrays.asList(FeatureConfig.Override.builder().enable(true).brand("huawei").build(), FeatureConfig.Override.builder().enable(true).brand("infinix").build(), FeatureConfig.Override.builder().enable(true).brand("tecno").build(), FeatureConfig.Override.builder().enable(true).brand("motorola").models(Arrays.asList("xt2201-1", "motorola edge 30 pro", "xt-2201", "motorola edge 30 ultra", "pakf0002in")).build(), FeatureConfig.Override.builder().enable(true).brand("comio").models(Collections.singletonList("comio x1")).build(), FeatureConfig.Override.builder().enable(true).brand("lg").build(), FeatureConfig.Override.builder().enable(true).brand("google").models(Arrays.asList("nexus", "pixel 6", "pixel 6 pro", "pixel 6a", "pixel 7", "gx7as", "gb62z", "g1azg", "pixel 7 pro")).build(), FeatureConfig.Override.builder().enable(true).brand("xiaomi").models(Arrays.asList("poco x3", "2201123G", "2201122G", "M1903F11I", "2201123G", "2201123C", "22071212AG", "21121210C", "M2004J19C", "M2004J19G", "22081212C")).build(), FeatureConfig.Override.builder().enable(true).brand("samsung").models(Arrays.asList("sm-a505f", "sm-a305f", "sm-a037f", "sm-a336e", "sm-m315f", "sm-a105f", "sm-a105g", "sm-a105m", "sm-m536b", "sm-m115f", "sm-a105fn")).build(), FeatureConfig.Override.builder().enable(true).brand("oneplus").models(Arrays.asList("ac2001", "a5010", "iv2201", "ne2210", "ne2211", "ne2213", "ne2215", "ne2217", "cph2413", "pbh110", "a6010", "cph2487")).build(), FeatureConfig.Override.builder().enable(true).brand("realme").models(Arrays.asList("rmx2202", "rmx3301")).build(), FeatureConfig.Override.builder().enable(true).brand("vivo").models(Arrays.asList("v2047", "v2145", "v2117", "v1806", "v2230")).build(), FeatureConfig.Override.builder().enable(true).brand("oppo").models(Collections.singletonList("CPH2001")).build(), FeatureConfig.Override.builder().enable(true).brand("tcl").models(Collections.singletonList("v2117")).build())).build()), new ArrayList());
    }
}
