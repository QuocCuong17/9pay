package com.otaliastudios.opengl.program;

import android.opengl.GLES20;
import com.otaliastudios.opengl.core.Egloo;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlProgramLocation.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001f\u0010\u000b\u001a\u00020\fX\u0080\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0014"}, d2 = {"Lcom/otaliastudios/opengl/program/GlProgramLocation;", "", "program", "", "type", "Lcom/otaliastudios/opengl/program/GlProgramLocation$Type;", "name", "", "(ILcom/otaliastudios/opengl/program/GlProgramLocation$Type;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "uvalue", "Lkotlin/UInt;", "getUvalue-pVg5ArA$library_release", "()I", "I", "value", "getValue", "Companion", "Type", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class GlProgramLocation {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String name;
    private final int uvalue;
    private final int value;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GlProgramLocation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/otaliastudios/opengl/program/GlProgramLocation$Type;", "", "(Ljava/lang/String;I)V", "ATTRIB", "UNIFORM", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public enum Type {
        ATTRIB,
        UNIFORM
    }

    /* compiled from: GlProgramLocation.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Type.values().length];
            iArr[Type.ATTRIB.ordinal()] = 1;
            iArr[Type.UNIFORM.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ GlProgramLocation(int i, Type type, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, type, str);
    }

    private GlProgramLocation(int i, Type type, String str) {
        int glGetAttribLocation;
        this.name = str;
        int i2 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i2 == 1) {
            glGetAttribLocation = GLES20.glGetAttribLocation(UInt.m1297constructorimpl(i), str);
        } else {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            glGetAttribLocation = GLES20.glGetUniformLocation(UInt.m1297constructorimpl(i), str);
        }
        this.value = glGetAttribLocation;
        Egloo.checkGlProgramLocation(glGetAttribLocation, str);
        this.uvalue = UInt.m1297constructorimpl(glGetAttribLocation);
    }

    public final String getName() {
        return this.name;
    }

    public final int getValue() {
        return this.value;
    }

    /* renamed from: getUvalue-pVg5ArA$library_release, reason: not valid java name and from getter */
    public final int getUvalue() {
        return this.uvalue;
    }

    /* compiled from: GlProgramLocation.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/otaliastudios/opengl/program/GlProgramLocation$Companion;", "", "()V", "getAttrib", "Lcom/otaliastudios/opengl/program/GlProgramLocation;", "program", "", "name", "", "getUniform", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GlProgramLocation getAttrib(int program, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new GlProgramLocation(program, Type.ATTRIB, name, null);
        }

        public final GlProgramLocation getUniform(int program, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new GlProgramLocation(program, Type.UNIFORM, name, null);
        }
    }
}
