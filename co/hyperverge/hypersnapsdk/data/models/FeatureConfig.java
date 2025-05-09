package co.hyperverge.hypersnapsdk.data.models;

import android.os.Build;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.gson.annotations.SerializedName;
import io.sentry.protocol.Device;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class FeatureConfig {
    public static final String FEATURE_CAMERA2 = "camera2";
    private List<String> cameraLevels;

    @SerializedName("enable")
    private boolean enable;

    /* renamed from: id, reason: collision with root package name */
    @SerializedName("id")
    private String f56id;

    @SerializedName("override")
    private List<Override> overrides;

    /* loaded from: classes2.dex */
    public static class FeatureConfigBuilder {
        private List<String> cameraLevels;
        private boolean enable;

        /* renamed from: id, reason: collision with root package name */
        private String f57id;
        private boolean overrides$set;
        private List<Override> overrides$value;

        FeatureConfigBuilder() {
        }

        public FeatureConfig build() {
            List<Override> list = this.overrides$value;
            if (!this.overrides$set) {
                list = FeatureConfig.access$600();
            }
            return new FeatureConfig(this.f57id, this.enable, list, this.cameraLevels);
        }

        public FeatureConfigBuilder cameraLevels(List<String> list) {
            this.cameraLevels = list;
            return this;
        }

        public FeatureConfigBuilder enable(boolean z) {
            this.enable = z;
            return this;
        }

        public FeatureConfigBuilder id(String str) {
            this.f57id = str;
            return this;
        }

        public FeatureConfigBuilder overrides(List<Override> list) {
            this.overrides$value = list;
            this.overrides$set = true;
            return this;
        }

        public String toString() {
            return "FeatureConfig.FeatureConfigBuilder(id=" + this.f57id + ", enable=" + this.enable + ", overrides$value=" + this.overrides$value + ", cameraLevels=" + this.cameraLevels + ")";
        }
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FeatureConfig;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FeatureConfig)) {
            return false;
        }
        FeatureConfig featureConfig = (FeatureConfig) obj;
        if (!featureConfig.canEqual(this) || isEnable() != featureConfig.isEnable()) {
            return false;
        }
        String id2 = getId();
        String id3 = featureConfig.getId();
        if (id2 != null ? !id2.equals(id3) : id3 != null) {
            return false;
        }
        List<Override> overrides = getOverrides();
        List<Override> overrides2 = featureConfig.getOverrides();
        if (overrides != null ? !overrides.equals(overrides2) : overrides2 != null) {
            return false;
        }
        List<String> cameraLevels = getCameraLevels();
        List<String> cameraLevels2 = featureConfig.getCameraLevels();
        return cameraLevels != null ? cameraLevels.equals(cameraLevels2) : cameraLevels2 == null;
    }

    public int hashCode() {
        int i = isEnable() ? 79 : 97;
        String id2 = getId();
        int hashCode = ((i + 59) * 59) + (id2 == null ? 43 : id2.hashCode());
        List<Override> overrides = getOverrides();
        int hashCode2 = (hashCode * 59) + (overrides == null ? 43 : overrides.hashCode());
        List<String> cameraLevels = getCameraLevels();
        return (hashCode2 * 59) + (cameraLevels != null ? cameraLevels.hashCode() : 43);
    }

    public void setCameraLevels(List<String> list) {
        this.cameraLevels = list;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public void setId(String str) {
        this.f56id = str;
    }

    public void setOverrides(List<Override> list) {
        this.overrides = list;
    }

    public String toString() {
        return "FeatureConfig(id=" + getId() + ", enable=" + isEnable() + ", overrides=" + getOverrides() + ", cameraLevels=" + getCameraLevels() + ")";
    }

    private static List<Override> $default$overrides() {
        return new ArrayList();
    }

    FeatureConfig(String str, boolean z, List<Override> list, List<String> list2) {
        this.cameraLevels = new ArrayList();
        this.f56id = str;
        this.enable = z;
        this.overrides = list;
        this.cameraLevels = list2;
    }

    public static FeatureConfigBuilder builder() {
        return new FeatureConfigBuilder();
    }

    static /* synthetic */ List access$600() {
        return $default$overrides();
    }

    public boolean isEnable() {
        return this.enable;
    }

    public List<Override> getOverrides() {
        return this.overrides;
    }

    public String getId() {
        return this.f56id;
    }

    public boolean isShouldEnable() {
        return this.enable;
    }

    public List<String> getCameraLevels() {
        return this.cameraLevels;
    }

    public void shouldEnable() {
        for (Override override : this.overrides) {
            Boolean shouldOverride = override.shouldOverride();
            if (shouldOverride != null) {
                this.cameraLevels = override.cameraLevels;
                this.enable = shouldOverride.booleanValue();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Override {

        @SerializedName(Device.JsonKeys.BRAND)
        private String brand;

        @SerializedName("cameraLevel")
        private List<String> cameraLevels;

        @SerializedName("enable")
        private boolean enable;

        @SerializedName("models")
        private List<String> models;

        @SerializedName("os")
        private List<OS> osVersions;

        /* loaded from: classes2.dex */
        public static class OverrideBuilder {
            private String brand;
            private boolean cameraLevels$set;
            private List<String> cameraLevels$value;
            private boolean enable;
            private boolean models$set;
            private List<String> models$value;
            private boolean osVersions$set;
            private List<OS> osVersions$value;

            OverrideBuilder() {
            }

            public OverrideBuilder brand(String str) {
                this.brand = str;
                return this;
            }

            public Override build() {
                List<String> list = this.models$value;
                if (!this.models$set) {
                    list = Override.access$300();
                }
                List<String> list2 = list;
                List<OS> list3 = this.osVersions$value;
                if (!this.osVersions$set) {
                    list3 = Override.access$400();
                }
                List<OS> list4 = list3;
                List<String> list5 = this.cameraLevels$value;
                if (!this.cameraLevels$set) {
                    list5 = Override.access$500();
                }
                return new Override(this.brand, this.enable, list2, list4, list5);
            }

            public OverrideBuilder cameraLevels(List<String> list) {
                this.cameraLevels$value = list;
                this.cameraLevels$set = true;
                return this;
            }

            public OverrideBuilder enable(boolean z) {
                this.enable = z;
                return this;
            }

            public OverrideBuilder models(List<String> list) {
                this.models$value = list;
                this.models$set = true;
                return this;
            }

            public OverrideBuilder osVersions(List<OS> list) {
                this.osVersions$value = list;
                this.osVersions$set = true;
                return this;
            }

            public String toString() {
                return "FeatureConfig.Override.OverrideBuilder(brand=" + this.brand + ", enable=" + this.enable + ", models$value=" + this.models$value + ", osVersions$value=" + this.osVersions$value + ", cameraLevels$value=" + this.cameraLevels$value + ")";
            }
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Override;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Override)) {
                return false;
            }
            Override override = (Override) obj;
            if (!override.canEqual(this) || isEnable() != override.isEnable()) {
                return false;
            }
            String brand = getBrand();
            String brand2 = override.getBrand();
            if (brand != null ? !brand.equals(brand2) : brand2 != null) {
                return false;
            }
            List<String> models = getModels();
            List<String> models2 = override.getModels();
            if (models != null ? !models.equals(models2) : models2 != null) {
                return false;
            }
            List<OS> osVersions = getOsVersions();
            List<OS> osVersions2 = override.getOsVersions();
            if (osVersions != null ? !osVersions.equals(osVersions2) : osVersions2 != null) {
                return false;
            }
            List<String> cameraLevels = getCameraLevels();
            List<String> cameraLevels2 = override.getCameraLevels();
            return cameraLevels != null ? cameraLevels.equals(cameraLevels2) : cameraLevels2 == null;
        }

        public int hashCode() {
            int i = isEnable() ? 79 : 97;
            String brand = getBrand();
            int hashCode = ((i + 59) * 59) + (brand == null ? 43 : brand.hashCode());
            List<String> models = getModels();
            int hashCode2 = (hashCode * 59) + (models == null ? 43 : models.hashCode());
            List<OS> osVersions = getOsVersions();
            int hashCode3 = (hashCode2 * 59) + (osVersions == null ? 43 : osVersions.hashCode());
            List<String> cameraLevels = getCameraLevels();
            return (hashCode3 * 59) + (cameraLevels != null ? cameraLevels.hashCode() : 43);
        }

        public void setBrand(String str) {
            this.brand = str;
        }

        public void setCameraLevels(List<String> list) {
            this.cameraLevels = list;
        }

        public void setEnable(boolean z) {
            this.enable = z;
        }

        public void setModels(List<String> list) {
            this.models = list;
        }

        public void setOsVersions(List<OS> list) {
            this.osVersions = list;
        }

        public String toString() {
            return "FeatureConfig.Override(brand=" + getBrand() + ", enable=" + isEnable() + ", models=" + getModels() + ", osVersions=" + getOsVersions() + ", cameraLevels=" + getCameraLevels() + ")";
        }

        private static List<String> $default$cameraLevels() {
            return new ArrayList();
        }

        private static List<String> $default$models() {
            return new ArrayList();
        }

        private static List<OS> $default$osVersions() {
            return new ArrayList();
        }

        Override(String str, boolean z, List<String> list, List<OS> list2, List<String> list3) {
            this.brand = str;
            this.enable = z;
            this.models = list;
            this.osVersions = list2;
            this.cameraLevels = list3;
        }

        public static OverrideBuilder builder() {
            return new OverrideBuilder();
        }

        static /* synthetic */ List access$300() {
            return $default$models();
        }

        static /* synthetic */ List access$400() {
            return $default$osVersions();
        }

        static /* synthetic */ List access$500() {
            return $default$cameraLevels();
        }

        public String getBrand() {
            return this.brand;
        }

        public boolean isEnable() {
            return this.enable;
        }

        public List<String> getModels() {
            return this.models;
        }

        public List<OS> getOsVersions() {
            return this.osVersions;
        }

        public List<String> getCameraLevels() {
            return this.cameraLevels;
        }

        public Boolean shouldOverride() {
            if (!this.brand.equalsIgnoreCase(Build.BRAND) && !this.brand.equalsIgnoreCase(Build.MANUFACTURER)) {
                return null;
            }
            if ((this.models.isEmpty() || Utils.containsSubstring(this.models, Build.MODEL.toLowerCase())) && checkIfOsVersionIsSatisfied()) {
                return Boolean.valueOf(this.enable);
            }
            return null;
        }

        public boolean checkIfOsVersionIsSatisfied() {
            ArrayList arrayList = new ArrayList();
            List<OS> list = this.osVersions;
            if (list == null || list.isEmpty()) {
                arrayList.add(true);
            } else {
                for (OS os : this.osVersions) {
                    boolean z = false;
                    boolean z2 = os.min == null;
                    boolean z3 = os.max == null;
                    if (os.min != null && Utils.getBuildVersion() >= os.min.intValue()) {
                        z2 = true;
                    }
                    if (os.max != null && Utils.getBuildVersion() <= os.max.intValue()) {
                        z3 = true;
                    }
                    if (z2 && z3) {
                        z = true;
                    }
                    arrayList.add(Boolean.valueOf(z));
                }
            }
            return arrayList.contains(true);
        }
    }

    /* loaded from: classes2.dex */
    public static class OS {

        @SerializedName("max")
        private Integer max;

        @SerializedName("min")
        private Integer min;

        /* loaded from: classes2.dex */
        public static class OSBuilder {
            private Integer max;
            private Integer min;

            OSBuilder() {
            }

            public OS build() {
                return new OS(this.min, this.max);
            }

            public OSBuilder max(Integer num) {
                this.max = num;
                return this;
            }

            public OSBuilder min(Integer num) {
                this.min = num;
                return this;
            }

            public String toString() {
                return "FeatureConfig.OS.OSBuilder(min=" + this.min + ", max=" + this.max + ")";
            }
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof OS;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof OS)) {
                return false;
            }
            OS os = (OS) obj;
            if (!os.canEqual(this)) {
                return false;
            }
            Integer min = getMin();
            Integer min2 = os.getMin();
            if (min != null ? !min.equals(min2) : min2 != null) {
                return false;
            }
            Integer max = getMax();
            Integer max2 = os.getMax();
            return max != null ? max.equals(max2) : max2 == null;
        }

        public int hashCode() {
            Integer min = getMin();
            int hashCode = min == null ? 43 : min.hashCode();
            Integer max = getMax();
            return ((hashCode + 59) * 59) + (max != null ? max.hashCode() : 43);
        }

        public void setMax(Integer num) {
            this.max = num;
        }

        public void setMin(Integer num) {
            this.min = num;
        }

        public String toString() {
            return "FeatureConfig.OS(min=" + getMin() + ", max=" + getMax() + ")";
        }

        OS(Integer num, Integer num2) {
            this.min = num;
            this.max = num2;
        }

        public static OSBuilder builder() {
            return new OSBuilder();
        }

        public Integer getMin() {
            return this.min;
        }

        public Integer getMax() {
            return this.max;
        }
    }
}
