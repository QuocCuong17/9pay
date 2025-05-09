package com.otaliastudios.opengl.draw;

import kotlin.Metadata;

/* compiled from: Gl3dDrawable.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/otaliastudios/opengl/draw/Gl3dDrawable;", "Lcom/otaliastudios/opengl/draw/GlDrawable;", "()V", "coordsPerVertex", "", "getCoordsPerVertex", "()I", "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public abstract class Gl3dDrawable extends GlDrawable {
    private final int coordsPerVertex = 3;

    @Override // com.otaliastudios.opengl.draw.GlDrawable
    public final int getCoordsPerVertex() {
        return this.coordsPerVertex;
    }
}
