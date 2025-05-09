package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Set;

/* loaded from: classes5.dex */
public class PluginScript extends UserScript {
    private boolean requiredInAllContentWorlds;

    public PluginScript(String str, String str2, UserScriptInjectionTime userScriptInjectionTime, ContentWorld contentWorld, boolean z, Set<String> set) {
        super(str, str2, userScriptInjectionTime, contentWorld, set);
        this.requiredInAllContentWorlds = z;
    }

    public boolean isRequiredInAllContentWorlds() {
        return this.requiredInAllContentWorlds;
    }

    public void setRequiredInAllContentWorlds(boolean z) {
        this.requiredInAllContentWorlds = z;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.UserScript
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && super.equals(obj) && this.requiredInAllContentWorlds == ((PluginScript) obj).requiredInAllContentWorlds;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.UserScript
    public int hashCode() {
        return (super.hashCode() * 31) + (this.requiredInAllContentWorlds ? 1 : 0);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.UserScript
    public String toString() {
        return "PluginScript{requiredInContentWorld=" + this.requiredInAllContentWorlds + "} " + super.toString();
    }
}
