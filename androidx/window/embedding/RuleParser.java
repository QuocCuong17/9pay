package androidx.window.embedding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import androidx.webkit.ProxyConfig;
import androidx.window.R;
import androidx.window.embedding.ActivityRule;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitPairRule;
import androidx.window.embedding.SplitPlaceholderRule;
import androidx.window.embedding.SplitRule;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: RuleParser.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J'\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J$\u0010\u001d\u001a\u00020\u001e*\u0012\u0012\u0004\u0012\u00020\u00130\u001fj\b\u0012\u0004\u0012\u00020\u0013` 2\u0006\u0010!\u001a\u00020\u0013H\u0002¨\u0006\""}, d2 = {"Landroidx/window/embedding/RuleParser;", "", "()V", "buildClassName", "Landroid/content/ComponentName;", "pkg", "", "clsSeq", "", "parseActivityFilter", "Landroidx/window/embedding/ActivityFilter;", "context", "Landroid/content/Context;", "parser", "Landroid/content/res/XmlResourceParser;", "parseActivityRule", "Landroidx/window/embedding/ActivityRule;", "parseRules", "", "Landroidx/window/embedding/EmbeddingRule;", "staticRuleResourceId", "", "parseRules$window_release", "parseSplitPairFilter", "Landroidx/window/embedding/SplitPairFilter;", "parseSplitPairRule", "Landroidx/window/embedding/SplitPairRule;", "parseSplitPlaceholderRule", "Landroidx/window/embedding/SplitPlaceholderRule;", "addRuleWithDuplicatedTagCheck", "", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", WorkflowModule.Properties.Section.Component.Validation.Type.RULE, "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RuleParser {
    public static final RuleParser INSTANCE = new RuleParser();

    private RuleParser() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x004e. Please report as an issue. */
    public final Set<EmbeddingRule> parseRules$window_release(Context context, int staticRuleResourceId) {
        SplitPlaceholderRule plus$window_release;
        ActivityRule plus$window_release2;
        SplitPairRule parseSplitPairRule;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            XmlResourceParser xml = context.getResources().getXml(staticRuleResourceId);
            Intrinsics.checkNotNullExpressionValue(xml, "resources.getXml(staticRuleResourceId)");
            HashSet<EmbeddingRule> hashSet = new HashSet<>();
            int depth = xml.getDepth();
            int next = xml.next();
            ActivityRule activityRule = null;
            SplitPairRule splitPairRule = null;
            SplitPlaceholderRule splitPlaceholderRule = null;
            while (next != 1 && (next != 3 || xml.getDepth() > depth)) {
                if (xml.getEventType() != 2 || Intrinsics.areEqual("split-config", xml.getName())) {
                    next = xml.next();
                } else {
                    String name = xml.getName();
                    if (name != null) {
                        switch (name.hashCode()) {
                            case 511422343:
                                if (name.equals("ActivityFilter")) {
                                    if (activityRule != null || splitPlaceholderRule != null) {
                                        ActivityFilter parseActivityFilter = parseActivityFilter(context, xml);
                                        if (activityRule == null) {
                                            if (splitPlaceholderRule != null) {
                                                hashSet.remove(splitPlaceholderRule);
                                                plus$window_release = splitPlaceholderRule.plus$window_release(parseActivityFilter);
                                                addRuleWithDuplicatedTagCheck(hashSet, plus$window_release);
                                                splitPlaceholderRule = plus$window_release;
                                                break;
                                            }
                                        } else {
                                            hashSet.remove(activityRule);
                                            plus$window_release2 = activityRule.plus$window_release(parseActivityFilter);
                                            addRuleWithDuplicatedTagCheck(hashSet, plus$window_release2);
                                            activityRule = plus$window_release2;
                                            break;
                                        }
                                    } else {
                                        throw new IllegalArgumentException("Found orphaned ActivityFilter");
                                    }
                                }
                                break;
                            case 520447504:
                                if (name.equals("SplitPairRule")) {
                                    parseSplitPairRule = parseSplitPairRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, parseSplitPairRule);
                                    activityRule = null;
                                    splitPlaceholderRule = null;
                                    splitPairRule = parseSplitPairRule;
                                    break;
                                }
                                break;
                            case 1579230604:
                                if (name.equals("SplitPairFilter")) {
                                    if (splitPairRule == null) {
                                        throw new IllegalArgumentException("Found orphaned SplitPairFilter outside of SplitPairRule");
                                    }
                                    SplitPairFilter parseSplitPairFilter = parseSplitPairFilter(context, xml);
                                    hashSet.remove(splitPairRule);
                                    parseSplitPairRule = splitPairRule.plus$window_release(parseSplitPairFilter);
                                    addRuleWithDuplicatedTagCheck(hashSet, parseSplitPairRule);
                                    splitPairRule = parseSplitPairRule;
                                    break;
                                }
                                break;
                            case 1793077963:
                                if (name.equals("ActivityRule")) {
                                    plus$window_release2 = parseActivityRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, plus$window_release2);
                                    splitPairRule = null;
                                    splitPlaceholderRule = null;
                                    activityRule = plus$window_release2;
                                    break;
                                }
                                break;
                            case 2050988213:
                                if (name.equals("SplitPlaceholderRule")) {
                                    plus$window_release = parseSplitPlaceholderRule(context, xml);
                                    addRuleWithDuplicatedTagCheck(hashSet, plus$window_release);
                                    activityRule = null;
                                    splitPairRule = null;
                                    splitPlaceholderRule = plus$window_release;
                                    break;
                                }
                                break;
                        }
                    }
                    next = xml.next();
                }
            }
            return hashSet;
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    private final void addRuleWithDuplicatedTagCheck(HashSet<EmbeddingRule> hashSet, EmbeddingRule embeddingRule) {
        String tag = embeddingRule.getTag();
        for (EmbeddingRule embeddingRule2 : hashSet) {
            if (tag != null && Intrinsics.areEqual(tag, embeddingRule2.getTag())) {
                throw new IllegalArgumentException("Duplicated tag: " + tag + " for " + embeddingRule + ". The tag must be unique in XML rule definition.");
            }
        }
        hashSet.add(embeddingRule);
    }

    private final SplitPairRule parseSplitPairRule(Context context, XmlResourceParser parser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPairRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPairRule_tag);
        float f = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitRatio, 0.5f);
        int integer = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinWidthDp, 600);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinHeightDp, 600);
        int integer3 = obtainStyledAttributes.getInteger(R.styleable.SplitPairRule_splitMinSmallestWidthDp, 600);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue());
        float f3 = obtainStyledAttributes.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue());
        int i = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue());
        int i2 = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_finishPrimaryWithSecondary, SplitRule.FinishBehavior.NEVER.getValue());
        int i3 = obtainStyledAttributes.getInt(R.styleable.SplitPairRule_finishSecondaryWithPrimary, SplitRule.FinishBehavior.ALWAYS.getValue());
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SplitPairRule_clearTop, false);
        return new SplitPairRule.Builder(SetsKt.emptySet()).setTag(string).setMinWidthDp(integer).setMinHeightDp(integer2).setMinSmallestWidthDp(integer3).setMaxAspectRatioInPortrait(EmbeddingAspectRatio.INSTANCE.buildAspectRatioFromValue$window_release(f2)).setMaxAspectRatioInLandscape(EmbeddingAspectRatio.INSTANCE.buildAspectRatioFromValue$window_release(f3)).setFinishPrimaryWithSecondary(SplitRule.FinishBehavior.INSTANCE.getFinishBehaviorFromValue$window_release(i2)).setFinishSecondaryWithPrimary(SplitRule.FinishBehavior.INSTANCE.getFinishBehaviorFromValue$window_release(i3)).setClearTop(z).setDefaultSplitAttributes(new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.INSTANCE.buildSplitTypeFromValue$window_release(f)).setLayoutDirection(SplitAttributes.LayoutDirection.INSTANCE.getLayoutDirectionFromValue$window_release(i)).build()).build();
    }

    private final SplitPlaceholderRule parseSplitPlaceholderRule(Context context, XmlResourceParser parser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPlaceholderRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPlaceholderRule_tag);
        String string2 = obtainStyledAttributes.getString(R.styleable.SplitPlaceholderRule_placeholderActivityName);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SplitPlaceholderRule_stickyPlaceholder, false);
        int i = obtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_finishPrimaryWithPlaceholder, SplitRule.FinishBehavior.ALWAYS.getValue());
        if (i == SplitRule.FinishBehavior.NEVER.getValue()) {
            throw new IllegalArgumentException("Never is not a valid configuration for Placeholder activities. Please use FINISH_ALWAYS or FINISH_ADJACENT instead or refer to the current API");
        }
        float f = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitRatio, 0.5f);
        int integer = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinWidthDp, 600);
        int integer2 = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinHeightDp, 600);
        int integer3 = obtainStyledAttributes.getInteger(R.styleable.SplitPlaceholderRule_splitMinSmallestWidthDp, 600);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue());
        float f3 = obtainStyledAttributes.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue());
        SplitAttributes build = new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.INSTANCE.buildSplitTypeFromValue$window_release(f)).setLayoutDirection(SplitAttributes.LayoutDirection.INSTANCE.getLayoutDirectionFromValue$window_release(obtainStyledAttributes.getInt(R.styleable.SplitPlaceholderRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue()))).build();
        String packageName = context.getApplicationContext().getPackageName();
        RuleParser ruleParser = INSTANCE;
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        ComponentName buildClassName = ruleParser.buildClassName(packageName, string2);
        Set emptySet = SetsKt.emptySet();
        Intent component = new Intent().setComponent(buildClassName);
        Intrinsics.checkNotNullExpressionValue(component, "Intent().setComponent(pl…eholderActivityClassName)");
        return new SplitPlaceholderRule.Builder(emptySet, component).setTag(string).setMinWidthDp(integer).setMinHeightDp(integer2).setMinSmallestWidthDp(integer3).setMaxAspectRatioInPortrait(EmbeddingAspectRatio.INSTANCE.buildAspectRatioFromValue$window_release(f2)).setMaxAspectRatioInLandscape(EmbeddingAspectRatio.INSTANCE.buildAspectRatioFromValue$window_release(f3)).setSticky(z).setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior.INSTANCE.getFinishBehaviorFromValue$window_release(i)).setDefaultSplitAttributes(build).build();
    }

    private final SplitPairFilter parseSplitPairFilter(Context context, XmlResourceParser parser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPairFilter, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_primaryActivityName);
        String string2 = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_secondaryActivityName);
        String string3 = obtainStyledAttributes.getString(R.styleable.SplitPairFilter_secondaryActivityAction);
        String packageName = context.getApplicationContext().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        return new SplitPairFilter(buildClassName(packageName, string), buildClassName(packageName, string2), string3);
    }

    private final ActivityRule parseActivityRule(Context context, XmlResourceParser parser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.ActivityRule, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.ActivityRule_tag);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.ActivityRule_alwaysExpand, false);
        obtainStyledAttributes.recycle();
        ActivityRule.Builder alwaysExpand = new ActivityRule.Builder(SetsKt.emptySet()).setAlwaysExpand(z);
        if (string != null) {
            alwaysExpand.setTag(string);
        }
        return alwaysExpand.build();
    }

    private final ActivityFilter parseActivityFilter(Context context, XmlResourceParser parser) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(parser, R.styleable.ActivityFilter, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.ActivityFilter_activityName);
        String string2 = obtainStyledAttributes.getString(R.styleable.ActivityFilter_activityAction);
        String packageName = context.getApplicationContext().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        return new ActivityFilter(buildClassName(packageName, string), string2);
    }

    private final ComponentName buildClassName(String pkg, CharSequence clsSeq) {
        if (clsSeq == null || clsSeq.length() == 0) {
            throw new IllegalArgumentException("Activity name must not be null");
        }
        String obj = clsSeq.toString();
        if (obj.charAt(0) == '.') {
            return new ComponentName(pkg, pkg + obj);
        }
        int indexOf$default = StringsKt.indexOf$default((CharSequence) obj, '/', 0, false, 6, (Object) null);
        if (indexOf$default > 0) {
            pkg = obj.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(pkg, "this as java.lang.String…ing(startIndex, endIndex)");
            obj = obj.substring(indexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(obj, "this as java.lang.String).substring(startIndex)");
        }
        if (!Intrinsics.areEqual(obj, ProxyConfig.MATCH_ALL_SCHEMES) && StringsKt.indexOf$default((CharSequence) obj, FilenameUtils.EXTENSION_SEPARATOR, 0, false, 6, (Object) null) < 0) {
            return new ComponentName(pkg, pkg + FilenameUtils.EXTENSION_SEPARATOR + obj);
        }
        return new ComponentName(pkg, obj);
    }
}
