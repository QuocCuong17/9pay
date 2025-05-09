package com.facebook.appevents.codeless.internal;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ViewHierarchy.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00142\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\fH\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\fH\u0007J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u0016\u0010\u0019\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007J\u0012\u0010 \u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007J\u0014\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007J\u0012\u0010#\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007J\u001e\u0010$\u001a\u0004\u0018\u00010\f2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010(\u001a\u00020&2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\b\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010\u0012\u001a\u00020\fH\u0003J\u0018\u0010-\u001a\u00020,2\u0006\u0010\u0012\u001a\u00020\f2\b\u0010'\u001a\u0004\u0018\u00010\fJ\u0010\u0010.\u001a\u00020,2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u001a\u0010/\u001a\u00020*2\u0006\u0010\u0012\u001a\u00020\f2\b\u00100\u001a\u0004\u0018\u00010\u001dH\u0007J \u00101\u001a\u00020*2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u000204H\u0007J\u0018\u00105\u001a\u00020*2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u00102\u001a\u00020\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/facebook/appevents/codeless/internal/ViewHierarchy;", "", "()V", "CLASS_RCTROOTVIEW", "", "CLASS_RCTVIEWGROUP", "CLASS_TOUCHTARGETHELPER", "ICON_MAX_EDGE_LENGTH", "", "METHOD_FIND_TOUCHTARGET_VIEW", "RCTRootViewReference", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "TAG", "kotlin.jvm.PlatformType", "methodFindTouchTargetView", "Ljava/lang/reflect/Method;", "findRCTRootView", ViewHierarchyConstants.VIEW_KEY, "getChildrenOfView", "", "getClassTypeBitmask", "getDictionaryOfView", "Lorg/json/JSONObject;", "getDimensionOfView", "getExistingClass", "Ljava/lang/Class;", "className", "getExistingOnClickListener", "Landroid/view/View$OnClickListener;", "getExistingOnTouchListener", "Landroid/view/View$OnTouchListener;", "getHintOfView", "getParentOfView", "Landroid/view/ViewGroup;", "getTextOfView", "getTouchReactView", FirebaseAnalytics.Param.LOCATION, "", "RCTRootView", "getViewLocationOnScreen", "initTouchTargetHelperMethods", "", "isAdapterViewItem", "", "isRCTButton", "isRCTRootView", "setOnClickListener", "newListener", "updateAppearanceOfView", WorkflowRequestType.JSON, "displayDensity", "", "updateBasicInfoOfView", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewHierarchy {
    private static final String CLASS_RCTROOTVIEW = "com.facebook.react.ReactRootView";
    private static final String CLASS_RCTVIEWGROUP = "com.facebook.react.views.view.ReactViewGroup";
    private static final String CLASS_TOUCHTARGETHELPER = "com.facebook.react.uimanager.TouchTargetHelper";
    private static final int ICON_MAX_EDGE_LENGTH = 44;
    private static final String METHOD_FIND_TOUCHTARGET_VIEW = "findTouchTargetView";
    private static Method methodFindTouchTargetView;
    public static final ViewHierarchy INSTANCE = new ViewHierarchy();
    private static final String TAG = ViewHierarchy.class.getCanonicalName();
    private static WeakReference<View> RCTRootViewReference = new WeakReference<>(null);

    private ViewHierarchy() {
    }

    @JvmStatic
    public static final ViewGroup getParentOfView(View view) {
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class) || view == null) {
            return null;
        }
        try {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                return (ViewGroup) parent;
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return null;
        }
    }

    @JvmStatic
    public static final List<View> getChildrenOfView(View view) {
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            if (view instanceof ViewGroup) {
                int childCount = ((ViewGroup) view).getChildCount();
                for (int i = 0; i < childCount; i++) {
                    arrayList.add(((ViewGroup) view).getChildAt(i));
                }
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return null;
        }
    }

    @JvmStatic
    public static final void updateBasicInfoOfView(View view, JSONObject json) {
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(json, "json");
            try {
                ViewHierarchy viewHierarchy = INSTANCE;
                String textOfView = getTextOfView(view);
                String hintOfView = getHintOfView(view);
                Object tag = view.getTag();
                CharSequence contentDescription = view.getContentDescription();
                json.put(ViewHierarchyConstants.CLASS_NAME_KEY, view.getClass().getCanonicalName());
                json.put(ViewHierarchyConstants.CLASS_TYPE_BITMASK_KEY, getClassTypeBitmask(view));
                json.put("id", view.getId());
                if (!SensitiveUserDataUtils.isSensitiveUserData(view)) {
                    json.put("text", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(textOfView), ""));
                } else {
                    json.put("text", "");
                    json.put(ViewHierarchyConstants.IS_USER_INPUT_KEY, true);
                }
                json.put("hint", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(hintOfView), ""));
                if (tag != null) {
                    json.put("tag", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(tag.toString()), ""));
                }
                if (contentDescription != null) {
                    json.put("description", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(contentDescription.toString()), ""));
                }
                json.put("dimension", viewHierarchy.getDimensionOfView(view));
            } catch (JSONException e) {
                Utility.logd(TAG, e);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
        }
    }

    @JvmStatic
    public static final void updateAppearanceOfView(View view, JSONObject json, float displayDensity) {
        Bitmap bitmap;
        Typeface typeface;
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(json, "json");
            try {
                JSONObject jSONObject = new JSONObject();
                if ((view instanceof TextView) && (typeface = ((TextView) view).getTypeface()) != null) {
                    jSONObject.put(ViewHierarchyConstants.TEXT_SIZE, ((TextView) view).getTextSize());
                    jSONObject.put(ViewHierarchyConstants.TEXT_IS_BOLD, typeface.isBold());
                    jSONObject.put(ViewHierarchyConstants.TEXT_IS_ITALIC, typeface.isItalic());
                    json.put(ViewHierarchyConstants.TEXT_STYLE, jSONObject);
                }
                if (view instanceof ImageView) {
                    Drawable drawable = ((ImageView) view).getDrawable();
                    if (!(drawable instanceof BitmapDrawable) || view.getHeight() / displayDensity > 44.0f || view.getWidth() / displayDensity > 44.0f || (bitmap = ((BitmapDrawable) drawable).getBitmap()) == null) {
                        return;
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    json.put(ViewHierarchyConstants.ICON_BITMAP, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
                }
            } catch (JSONException e) {
                Utility.logd(TAG, e);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
        }
    }

    @JvmStatic
    public static final JSONObject getDictionaryOfView(View view) {
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            if (Intrinsics.areEqual(view.getClass().getName(), CLASS_RCTROOTVIEW)) {
                RCTRootViewReference = new WeakReference<>(view);
            }
            JSONObject jSONObject = new JSONObject();
            try {
                updateBasicInfoOfView(view, jSONObject);
                JSONArray jSONArray = new JSONArray();
                List<View> childrenOfView = getChildrenOfView(view);
                int size = childrenOfView.size();
                for (int i = 0; i < size; i++) {
                    jSONArray.put(getDictionaryOfView(childrenOfView.get(i)));
                }
                jSONObject.put(ViewHierarchyConstants.CHILDREN_VIEW_KEY, jSONArray);
            } catch (JSONException e) {
                Log.e(TAG, "Failed to create JSONObject for view.", e);
            }
            return jSONObject;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return null;
        }
    }

    @JvmStatic
    public static final int getClassTypeBitmask(View view) {
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return 0;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            int i = view instanceof ImageView ? 2 : 0;
            if (view.isClickable()) {
                i |= 32;
            }
            ViewHierarchy viewHierarchy = INSTANCE;
            if (isAdapterViewItem(view)) {
                i |= 512;
            }
            if (view instanceof TextView) {
                int i2 = i | 1024 | 1;
                if (view instanceof Button) {
                    i2 |= 4;
                    if (view instanceof Switch) {
                        i2 |= 8192;
                    } else if (view instanceof CheckBox) {
                        i2 |= 32768;
                    }
                }
                return view instanceof EditText ? i2 | 2048 : i2;
            }
            if (!(view instanceof Spinner) && !(view instanceof DatePicker)) {
                return view instanceof RatingBar ? i | 65536 : view instanceof RadioGroup ? i | 16384 : ((view instanceof ViewGroup) && viewHierarchy.isRCTButton(view, RCTRootViewReference.get())) ? i | 64 : i;
            }
            return i | 4096;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return 0;
        }
    }

    @JvmStatic
    private static final boolean isAdapterViewItem(View view) {
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return false;
        }
        try {
            ViewParent parent = view.getParent();
            if (parent instanceof AdapterView) {
                return true;
            }
            ViewHierarchy viewHierarchy = INSTANCE;
            Class<?> existingClass = viewHierarchy.getExistingClass("android.support.v4.view.NestedScrollingChild");
            if (existingClass != null && existingClass.isInstance(parent)) {
                return true;
            }
            Class<?> existingClass2 = viewHierarchy.getExistingClass("androidx.core.view.NestedScrollingChild");
            if (existingClass2 != null) {
                return existingClass2.isInstance(parent);
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return false;
        }
    }

    @JvmStatic
    public static final String getTextOfView(View view) {
        CharSequence valueOf;
        Object selectedItem;
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return null;
        }
        try {
            if (view instanceof TextView) {
                valueOf = ((TextView) view).getText();
                if (view instanceof Switch) {
                    valueOf = ((Switch) view).isChecked() ? "1" : "0";
                }
            } else if (view instanceof Spinner) {
                if (((Spinner) view).getCount() > 0 && (selectedItem = ((Spinner) view).getSelectedItem()) != null) {
                    valueOf = selectedItem.toString();
                }
                valueOf = null;
            } else {
                if (view instanceof DatePicker) {
                    int year = ((DatePicker) view).getYear();
                    int month = ((DatePicker) view).getMonth();
                    int dayOfMonth = ((DatePicker) view).getDayOfMonth();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    valueOf = String.format("%04d-%02d-%02d", Arrays.copyOf(new Object[]{Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(dayOfMonth)}, 3));
                    Intrinsics.checkNotNullExpressionValue(valueOf, "format(format, *args)");
                } else if (view instanceof TimePicker) {
                    Integer currentHour = ((TimePicker) view).getCurrentHour();
                    Intrinsics.checkNotNullExpressionValue(currentHour, "view.currentHour");
                    int intValue = currentHour.intValue();
                    Integer currentMinute = ((TimePicker) view).getCurrentMinute();
                    Intrinsics.checkNotNullExpressionValue(currentMinute, "view.currentMinute");
                    int intValue2 = currentMinute.intValue();
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    valueOf = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(intValue), Integer.valueOf(intValue2)}, 2));
                    Intrinsics.checkNotNullExpressionValue(valueOf, "format(format, *args)");
                } else if (view instanceof RadioGroup) {
                    int checkedRadioButtonId = ((RadioGroup) view).getCheckedRadioButtonId();
                    int childCount = ((RadioGroup) view).getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = ((RadioGroup) view).getChildAt(i);
                        if (childAt.getId() == checkedRadioButtonId && (childAt instanceof RadioButton)) {
                            valueOf = ((RadioButton) childAt).getText();
                            break;
                        }
                    }
                    valueOf = null;
                } else {
                    if (view instanceof RatingBar) {
                        valueOf = String.valueOf(((RatingBar) view).getRating());
                    }
                    valueOf = null;
                }
            }
            if (valueOf != null) {
                String obj = valueOf.toString();
                if (obj != null) {
                    return obj;
                }
            }
            return "";
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return null;
        }
    }

    @JvmStatic
    public static final String getHintOfView(View view) {
        CharSequence hint;
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return null;
        }
        try {
            if (view instanceof EditText) {
                hint = ((EditText) view).getHint();
            } else {
                hint = view instanceof TextView ? ((TextView) view).getHint() : null;
            }
            if (hint != null) {
                String obj = hint.toString();
                if (obj != null) {
                    return obj;
                }
            }
            return "";
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return null;
        }
    }

    private final JSONObject getDimensionOfView(View view) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ViewHierarchyConstants.DIMENSION_TOP_KEY, view.getTop());
                jSONObject.put("left", view.getLeft());
                jSONObject.put("width", view.getWidth());
                jSONObject.put("height", view.getHeight());
                jSONObject.put(ViewHierarchyConstants.DIMENSION_SCROLL_X_KEY, view.getScrollX());
                jSONObject.put(ViewHierarchyConstants.DIMENSION_SCROLL_Y_KEY, view.getScrollY());
                jSONObject.put("visibility", view.getVisibility());
            } catch (JSONException e) {
                Log.e(TAG, "Failed to create JSONObject for dimension.", e);
            }
            return jSONObject;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    @JvmStatic
    public static final View.OnClickListener getExistingOnClickListener(View view) {
        Field declaredField;
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return null;
        }
        try {
            Field declaredField2 = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (declaredField2 != null) {
                declaredField2.setAccessible(true);
            }
            Object obj = declaredField2.get(view);
            if (obj == null || (declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener")) == null) {
                return null;
            }
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type android.view.View.OnClickListener");
            return (View.OnClickListener) obj2;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return null;
        }
    }

    @JvmStatic
    public static final void setOnClickListener(View view, View.OnClickListener newListener) {
        Field field;
        Field field2;
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            Object obj = null;
            try {
                try {
                    field = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
                    try {
                        field2 = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener");
                    } catch (ClassNotFoundException | NoSuchFieldException unused) {
                        field2 = null;
                        if (field != null) {
                        }
                        view.setOnClickListener(newListener);
                        return;
                    }
                } catch (Exception unused2) {
                    return;
                }
            } catch (ClassNotFoundException | NoSuchFieldException unused3) {
                field = null;
            }
            if (field != null || field2 == null) {
                view.setOnClickListener(newListener);
                return;
            }
            field.setAccessible(true);
            field2.setAccessible(true);
            try {
                field.setAccessible(true);
                obj = field.get(view);
            } catch (IllegalAccessException unused4) {
            }
            if (obj == null) {
                view.setOnClickListener(newListener);
            } else {
                field2.set(obj, newListener);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
        }
    }

    @JvmStatic
    public static final View.OnTouchListener getExistingOnTouchListener(View view) {
        Field declaredField;
        try {
            if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
                return null;
            }
            try {
                try {
                    Field declaredField2 = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
                    if (declaredField2 != null) {
                        declaredField2.setAccessible(true);
                    }
                    Object obj = declaredField2.get(view);
                    if (obj == null || (declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener")) == null) {
                        return null;
                    }
                    declaredField.setAccessible(true);
                    Object obj2 = declaredField.get(obj);
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type android.view.View.OnTouchListener");
                    return (View.OnTouchListener) obj2;
                } catch (ClassNotFoundException e) {
                    Utility.logd(TAG, e);
                    return null;
                }
            } catch (IllegalAccessException e2) {
                Utility.logd(TAG, e2);
                return null;
            } catch (NoSuchFieldException e3) {
                Utility.logd(TAG, e3);
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            return null;
        }
    }

    private final View getTouchReactView(float[] location, View RCTRootView) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            initTouchTargetHelperMethods();
            Method method = methodFindTouchTargetView;
            if (method != null && RCTRootView != null) {
                try {
                    try {
                        if (method != null) {
                            Object invoke = method.invoke(null, location, RCTRootView);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.view.View");
                            View view = (View) invoke;
                            if (view.getId() > 0) {
                                Object parent = view.getParent();
                                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
                                return (View) parent;
                            }
                        } else {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                    } catch (InvocationTargetException e) {
                        Utility.logd(TAG, e);
                    }
                } catch (IllegalAccessException e2) {
                    Utility.logd(TAG, e2);
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final boolean isRCTButton(View view, View RCTRootView) {
        View touchReactView;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(view, "view");
            if (!Intrinsics.areEqual(view.getClass().getName(), CLASS_RCTVIEWGROUP) || (touchReactView = getTouchReactView(getViewLocationOnScreen(view), RCTRootView)) == null) {
                return false;
            }
            return touchReactView.getId() == view.getId();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final boolean isRCTRootView(View view) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return Intrinsics.areEqual(view.getClass().getName(), CLASS_RCTROOTVIEW);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    @JvmStatic
    public static final View findRCTRootView(View view) {
        if (CrashShieldHandler.isObjectCrashing(ViewHierarchy.class)) {
            return null;
        }
        while (view != null) {
            try {
                if (!INSTANCE.isRCTRootView(view)) {
                    Object parent = view.getParent();
                    if (!(parent instanceof View)) {
                        break;
                    }
                    view = (View) parent;
                } else {
                    return view;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, ViewHierarchy.class);
            }
        }
        return null;
    }

    private final float[] getViewLocationOnScreen(View view) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            view.getLocationOnScreen(new int[2]);
            return new float[]{r2[0], r2[1]};
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final void initTouchTargetHelperMethods() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            if (methodFindTouchTargetView != null) {
                return;
            }
            try {
                Method declaredMethod = Class.forName(CLASS_TOUCHTARGETHELPER).getDeclaredMethod(METHOD_FIND_TOUCHTARGET_VIEW, float[].class, ViewGroup.class);
                methodFindTouchTargetView = declaredMethod;
                if (declaredMethod == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                declaredMethod.setAccessible(true);
            } catch (ClassNotFoundException e) {
                Utility.logd(TAG, e);
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, e2);
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    private final Class<?> getExistingClass(String className) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
