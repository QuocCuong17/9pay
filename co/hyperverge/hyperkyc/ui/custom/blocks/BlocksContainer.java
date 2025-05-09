package co.hyperverge.hyperkyc.ui.custom.blocks;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.media3.exoplayer.upstream.CmcdData;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: BlocksContainer.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0015\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dH\u0017J\u0015\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b!J\u001f\u0010\u001e\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u0014H\u0000¢\u0006\u0002\b!J\u0010\u0010%\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0015\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0019H\u0000¢\u0006\u0002\b(R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksContainer;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "blocksContainerConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksContainerConfig;", "(Landroid/content/Context;Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksContainerConfig;)V", "blockEditText", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockEditText;", "blockItemViews", "", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockItemView;", "value", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;", "blocksViewListener", "getBlocksViewListener", "()Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;", "setBlocksViewListener", "(Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;)V", "ignoreTextChange", "", "generateBlocksContainer", "", "setFocus", "length", "", "setFocus$hyperkyc_release", "setOnTouchListener", CmcdData.Factory.STREAM_TYPE_LIVE, "Landroid/view/View$OnTouchListener;", "setText", CmcdData.Factory.STREAMING_FORMAT_SS, "", "setText$hyperkyc_release", "blockText", "", "ignoreChange", "setTextWatcher", "updateBlocksState", "blockState", "updateBlocksState$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlocksContainer extends FrameLayout {
    private BlockEditText blockEditText;
    private List<BlockItemView> blockItemViews;
    private final BlocksContainerConfig blocksContainerConfig;
    private BlocksViewListener blocksViewListener;
    private boolean ignoreTextChange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlocksContainer(Context context, BlocksContainerConfig blocksContainerConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(blocksContainerConfig, "blocksContainerConfig");
        this.blocksContainerConfig = blocksContainerConfig;
        this.blockItemViews = new ArrayList();
        generateBlocksContainer();
    }

    public final BlocksViewListener getBlocksViewListener() {
        return this.blocksViewListener;
    }

    public final void setBlocksViewListener(BlocksViewListener blocksViewListener) {
        this.blocksViewListener = blocksViewListener;
        BlockEditText blockEditText = this.blockEditText;
        if (blockEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockEditText");
            blockEditText = null;
        }
        blockEditText.setBlocksViewListener(blocksViewListener);
    }

    private final void generateBlocksContainer() {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        this.blockItemViews = new ArrayList();
        BlockEditText blockEditText = null;
        if (this.blocksContainerConfig.getBlockCount() > 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            BlockEditText blockEditText2 = new BlockEditText(context, this.blocksContainerConfig.getBlockEditTextConfig());
            this.blockEditText = blockEditText2;
            blockEditText2.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(this.blocksContainerConfig.getBlockCount() * this.blocksContainerConfig.getBlockLength())});
            BlockEditText blockEditText3 = this.blockEditText;
            if (blockEditText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("blockEditText");
                blockEditText3 = null;
            }
            setTextWatcher(blockEditText3);
            BlockEditText blockEditText4 = this.blockEditText;
            if (blockEditText4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("blockEditText");
            } else {
                blockEditText = blockEditText4;
            }
            addView(blockEditText, layoutParams);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            LinearLayout linearLayout = new LinearLayout(getContext());
            addView(linearLayout, layoutParams2);
            int blockCount = this.blocksContainerConfig.getBlockCount();
            int i = 0;
            while (i < blockCount) {
                Context context2 = getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "context");
                BlockItemView blockItemView = new BlockItemView(context2, this.blocksContainerConfig.getBlockItemViewConfig());
                blockItemView.setViewState$hyperkyc_release(0);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(this.blocksContainerConfig.getBlockWidth(), this.blocksContainerConfig.getBlockHeight());
                layoutParams3.setMargins(i == 0 ? ViewExtsKt.getDp(0) : ViewExtsKt.getDp(8), ViewExtsKt.getDp(8), i == this.blocksContainerConfig.getBlockCount() - 1 ? ViewExtsKt.getDp(0) : ViewExtsKt.getDp(8), ViewExtsKt.getDp(8));
                linearLayout.addView(blockItemView, i, layoutParams3);
                this.blockItemViews.add(blockItemView);
                i++;
            }
            return;
        }
        HyperLogger.Level level = HyperLogger.Level.ERROR;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("Please make sure blockCount is greater than 0");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        CoreExtsKt.isRelease();
        try {
            Result.Companion companion2 = Result.INSTANCE;
            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            m1202constructorimpl = "";
        }
        String packageName = (String) m1202constructorimpl;
        if (CoreExtsKt.isDebug()) {
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls2 = getClass();
                    String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    if (canonicalName2 != null) {
                        str = canonicalName2;
                    }
                } else {
                    str = substringAfterLast$default;
                }
                Matcher matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str);
                if (matcher2.find()) {
                    str = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                }
                if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str = str.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                Log.println(6, str, "Please make sure blockCount is greater than 0 ");
            }
        }
    }

    private final void setTextWatcher(BlockEditText blockEditText) {
        blockEditText.addTextChangedListener(new TextWatcher() { // from class: co.hyperverge.hyperkyc.ui.custom.blocks.BlocksContainer$setTextWatcher$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean z;
                BlocksViewListener blocksViewListener;
                Intrinsics.checkNotNullParameter(s, "s");
                BlocksContainer.this.setText$hyperkyc_release(s);
                BlocksContainer.this.setFocus$hyperkyc_release(s.length());
                z = BlocksContainer.this.ignoreTextChange;
                if (z || (blocksViewListener = BlocksContainer.this.getBlocksViewListener()) == null) {
                    return;
                }
                blocksViewListener.onTextChanged(s.toString());
            }
        });
    }

    public final void setText$hyperkyc_release(CharSequence s) {
        CharSequence charSequence;
        Intrinsics.checkNotNullParameter(s, "s");
        int i = 0;
        for (BlockItemView blockItemView : this.blockItemViews) {
            if (i < s.length()) {
                charSequence = s.subSequence(i, Math.min(this.blocksContainerConfig.getBlockLength() + i, s.length()));
            }
            blockItemView.setText$hyperkyc_release(charSequence.toString());
            i += this.blocksContainerConfig.getBlockLength();
        }
    }

    public final void setFocus$hyperkyc_release(int length) {
        if (this.blocksContainerConfig.getBlockEditTextConfig().getEnable()) {
            int size = this.blockItemViews.size();
            for (int i = 0; i < size; i++) {
                int i2 = 1;
                if (i != length / this.blocksContainerConfig.getBlockLength() && (length / this.blocksContainerConfig.getBlockLength() != this.blockItemViews.size() || i != this.blockItemViews.size() - 1)) {
                    i2 = 0;
                }
                this.blockItemViews.get(i).setViewState$hyperkyc_release(i2);
            }
        }
    }

    public final void updateBlocksState$hyperkyc_release(int blockState) {
        Iterator<T> it = this.blockItemViews.iterator();
        while (it.hasNext()) {
            ((BlockItemView) it.next()).setViewState$hyperkyc_release(blockState);
        }
    }

    public static /* synthetic */ void setText$hyperkyc_release$default(BlocksContainer blocksContainer, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        blocksContainer.setText$hyperkyc_release(str, z);
    }

    public final void setText$hyperkyc_release(String blockText, boolean ignoreChange) {
        Intrinsics.checkNotNullParameter(blockText, "blockText");
        this.ignoreTextChange = ignoreChange;
        BlockEditText blockEditText = this.blockEditText;
        if (blockEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockEditText");
            blockEditText = null;
        }
        blockEditText.setText(blockText);
        this.ignoreTextChange = false;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener l) {
        Intrinsics.checkNotNullParameter(l, "l");
        super.setOnTouchListener(l);
        BlockEditText blockEditText = this.blockEditText;
        if (blockEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockEditText");
            blockEditText = null;
        }
        blockEditText.setOnTouchListener(l);
    }
}
