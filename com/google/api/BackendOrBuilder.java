package com.google.api;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* loaded from: classes3.dex */
public interface BackendOrBuilder extends MessageLiteOrBuilder {
    BackendRule getRules(int i);

    int getRulesCount();

    List<BackendRule> getRulesList();
}
