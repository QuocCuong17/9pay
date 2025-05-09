package co.hyperverge.hyperkyc.data.network;

import android.content.Context;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.WorkflowConfig;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import java.io.File;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetworkRepo.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2", f = "NetworkRepo.kt", i = {}, l = {69}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class NetworkRepo$prefetchInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $appId;
    final /* synthetic */ Context $context;
    final /* synthetic */ String $languageCode;
    final /* synthetic */ boolean $preLoadAll;
    final /* synthetic */ boolean $shouldPreLoadWebSDK;
    final /* synthetic */ String $workflowId;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkRepo$prefetchInternal$2(boolean z, Context context, String str, String str2, String str3, boolean z2, Continuation<? super NetworkRepo$prefetchInternal$2> continuation) {
        super(2, continuation);
        this.$preLoadAll = z;
        this.$context = context;
        this.$appId = str;
        this.$workflowId = str2;
        this.$languageCode = str3;
        this.$shouldPreLoadWebSDK = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NetworkRepo$prefetchInternal$2(this.$preLoadAll, this.$context, this.$appId, this.$workflowId, this.$languageCode, this.$shouldPreLoadWebSDK, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NetworkRepo$prefetchInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NetworkRepo.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1", f = "NetworkRepo.kt", i = {}, l = {76, 106}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $appId;
        final /* synthetic */ Context $context;
        final /* synthetic */ String $languageCode;
        final /* synthetic */ boolean $preLoadAll;
        final /* synthetic */ boolean $shouldPreLoadWebSDK;
        final /* synthetic */ String $workflowId;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, Context context, String str, String str2, String str3, boolean z2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$preLoadAll = z;
            this.$context = context;
            this.$appId = str;
            this.$workflowId = str2;
            this.$languageCode = str3;
            this.$shouldPreLoadWebSDK = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$preLoadAll, this.$context, this.$appId, this.$workflowId, this.$languageCode, this.$shouldPreLoadWebSDK, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x014e  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Deferred async$default;
            Deferred async$default2;
            Deferred async$default3;
            Deferred async$default4;
            Deferred async$default5;
            Deferred async$default6;
            Deferred deferred;
            Deferred deferred2;
            Deferred deferred3;
            Deferred deferred4;
            Deferred deferred5;
            Object awaitAll;
            Deferred async$default7;
            Deferred deferred6;
            Object await;
            HSUIConfig hSUIConfig;
            List list;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (!this.$preLoadAll) {
                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                    async$default7 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new C00081(this.$appId, this.$context, this.$workflowId, null), 3, null);
                    NetworkRepo.prefetchUIConfigDeferred = async$default7;
                    deferred6 = NetworkRepo.prefetchUIConfigDeferred;
                    if (deferred6 != null) {
                        this.label = 1;
                        await = deferred6.await(this);
                        if (await == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        hSUIConfig = (HSUIConfig) await;
                        if (hSUIConfig != null) {
                        }
                    }
                } else {
                    File cacheDir = this.$context.getApplicationContext().getCacheDir();
                    NetworkRepo networkRepo2 = NetworkRepo.INSTANCE;
                    async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass3(cacheDir, null), 3, null);
                    NetworkRepo.prefetchCountriesDeferred = async$default;
                    NetworkRepo networkRepo3 = NetworkRepo.INSTANCE;
                    async$default2 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass4(this.$appId, cacheDir, null), 3, null);
                    NetworkRepo.prefetchRemoteConfigDeferred = async$default2;
                    NetworkRepo networkRepo4 = NetworkRepo.INSTANCE;
                    async$default3 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass5(this.$appId, this.$workflowId, cacheDir, null), 3, null);
                    NetworkRepo.prefetchWorkflowConfigDeferred = async$default3;
                    NetworkRepo networkRepo5 = NetworkRepo.INSTANCE;
                    async$default4 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass6(this.$appId, this.$languageCode, this.$workflowId, cacheDir, null), 3, null);
                    NetworkRepo.prefetchTextConfigDeferred = async$default4;
                    NetworkRepo networkRepo6 = NetworkRepo.INSTANCE;
                    async$default5 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new AnonymousClass7(this.$appId, this.$context, this.$workflowId, null), 3, null);
                    NetworkRepo.prefetchUIConfigDeferred = async$default5;
                    async$default6 = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new NetworkRepo$prefetchInternal$2$1$preloadWebSDKDeferred$1(this.$context, this.$shouldPreLoadWebSDK, null), 3, null);
                    deferred = NetworkRepo.prefetchCountriesDeferred;
                    Intrinsics.checkNotNull(deferred);
                    deferred2 = NetworkRepo.prefetchRemoteConfigDeferred;
                    Intrinsics.checkNotNull(deferred2);
                    deferred3 = NetworkRepo.prefetchWorkflowConfigDeferred;
                    Intrinsics.checkNotNull(deferred3);
                    deferred4 = NetworkRepo.prefetchTextConfigDeferred;
                    Intrinsics.checkNotNull(deferred4);
                    deferred5 = NetworkRepo.prefetchUIConfigDeferred;
                    Intrinsics.checkNotNull(deferred5);
                    this.label = 2;
                    awaitAll = AwaitKt.awaitAll(new Deferred[]{deferred, deferred2, deferred3, deferred4, deferred5, async$default6}, this);
                    if (awaitAll == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    list = (List) awaitAll;
                    if (list.get(4) != null) {
                    }
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
                await = obj;
                hSUIConfig = (HSUIConfig) await;
                if (hSUIConfig != null) {
                    HyperSnapBridgeKt.setUIConfig(this.$context, hSUIConfig);
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                awaitAll = obj;
                list = (List) awaitAll;
                if (list.get(4) != null) {
                    Context context = this.$context;
                    Object obj2 = list.get(4);
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type co.hyperverge.hyperkyc.core.hv.models.HSUIConfig");
                    HyperSnapBridgeKt.setUIConfig(context, (HSUIConfig) obj2);
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: NetworkRepo.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$1", f = "NetworkRepo.kt", i = {}, l = {73}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C00081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSUIConfig>, Object> {
            final /* synthetic */ String $appId;
            final /* synthetic */ Context $context;
            final /* synthetic */ String $workflowId;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00081(String str, Context context, String str2, Continuation<? super C00081> continuation) {
                super(2, continuation);
                this.$appId = str;
                this.$context = context;
                this.$workflowId = str2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00081(this.$appId, this.$context, this.$workflowId, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSUIConfig> continuation) {
                return ((C00081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                    String str = this.$appId;
                    File cacheDir = this.$context.getApplicationContext().getCacheDir();
                    Intrinsics.checkNotNullExpressionValue(cacheDir, "context.applicationContext.cacheDir");
                    this.label = 1;
                    obj = networkRepo.prefetchUIColorConfig$hyperkyc_release(str, cacheDir, this.$workflowId, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: NetworkRepo.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lco/hyperverge/hyperkyc/data/models/KycCountry;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$3", f = "NetworkRepo.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$3, reason: invalid class name */
        /* loaded from: classes2.dex */
        public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends KycCountry>>, Object> {
            final /* synthetic */ File $cacheDir;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(File file, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$cacheDir = file;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.$cacheDir, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends KycCountry>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super List<KycCountry>>) continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<KycCountry>> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                    File cacheDir = this.$cacheDir;
                    Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
                    this.label = 1;
                    obj = networkRepo.prefetchCountries$hyperkyc_release(cacheDir, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: NetworkRepo.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$4", f = "NetworkRepo.kt", i = {}, l = {88}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$4, reason: invalid class name */
        /* loaded from: classes2.dex */
        public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSRemoteConfig>, Object> {
            final /* synthetic */ String $appId;
            final /* synthetic */ File $cacheDir;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass4(String str, File file, Continuation<? super AnonymousClass4> continuation) {
                super(2, continuation);
                this.$appId = str;
                this.$cacheDir = file;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass4(this.$appId, this.$cacheDir, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSRemoteConfig> continuation) {
                return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                    String str = this.$appId;
                    File cacheDir = this.$cacheDir;
                    Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
                    this.label = 1;
                    obj = networkRepo.prefetchRemoteConfig$hyperkyc_release(str, cacheDir, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: NetworkRepo.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/data/models/WorkflowConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$5", f = "NetworkRepo.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$5, reason: invalid class name */
        /* loaded from: classes2.dex */
        public static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super WorkflowConfig>, Object> {
            final /* synthetic */ String $appId;
            final /* synthetic */ File $cacheDir;
            final /* synthetic */ String $workflowId;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass5(String str, String str2, File file, Continuation<? super AnonymousClass5> continuation) {
                super(2, continuation);
                this.$appId = str;
                this.$workflowId = str2;
                this.$cacheDir = file;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass5(this.$appId, this.$workflowId, this.$cacheDir, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super WorkflowConfig> continuation) {
                return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                    String str = this.$appId;
                    String str2 = this.$workflowId;
                    File cacheDir = this.$cacheDir;
                    Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
                    this.label = 1;
                    obj = networkRepo.prefetchWorkflowConfig$hyperkyc_release(str, str2, cacheDir, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: NetworkRepo.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u001c\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u0018\u00010\u0001*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$6", f = "NetworkRepo.kt", i = {}, l = {94}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$6, reason: invalid class name */
        /* loaded from: classes2.dex */
        public static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>>, Object> {
            final /* synthetic */ String $appId;
            final /* synthetic */ File $cacheDir;
            final /* synthetic */ String $languageCode;
            final /* synthetic */ String $workflowId;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass6(String str, String str2, String str3, File file, Continuation<? super AnonymousClass6> continuation) {
                super(2, continuation);
                this.$appId = str;
                this.$languageCode = str2;
                this.$workflowId = str3;
                this.$cacheDir = file;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass6(this.$appId, this.$languageCode, this.$workflowId, this.$cacheDir, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Map<String, ? extends Map<String, ? extends Object>>> continuation) {
                return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                    String str = this.$appId;
                    String str2 = this.$languageCode;
                    String str3 = this.$workflowId;
                    File cacheDir = this.$cacheDir;
                    Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
                    this.label = 1;
                    obj = networkRepo.prefetchTextConfig$hyperkyc_release(str, str2, str3, cacheDir, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: NetworkRepo.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$7", f = "NetworkRepo.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: co.hyperverge.hyperkyc.data.network.NetworkRepo$prefetchInternal$2$1$7, reason: invalid class name */
        /* loaded from: classes2.dex */
        public static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super HSUIConfig>, Object> {
            final /* synthetic */ String $appId;
            final /* synthetic */ Context $context;
            final /* synthetic */ String $workflowId;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass7(String str, Context context, String str2, Continuation<? super AnonymousClass7> continuation) {
                super(2, continuation);
                this.$appId = str;
                this.$context = context;
                this.$workflowId = str2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass7(this.$appId, this.$context, this.$workflowId, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super HSUIConfig> continuation) {
                return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    NetworkRepo networkRepo = NetworkRepo.INSTANCE;
                    String str = this.$appId;
                    File cacheDir = this.$context.getApplicationContext().getCacheDir();
                    Intrinsics.checkNotNullExpressionValue(cacheDir, "context.applicationContext.cacheDir");
                    this.label = 1;
                    obj = networkRepo.prefetchUIColorConfig$hyperkyc_release(str, cacheDir, this.$workflowId, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (CoroutineExtsKt.onIO$default(null, new AnonymousClass1(this.$preLoadAll, this.$context, this.$appId, this.$workflowId, this.$languageCode, this.$shouldPreLoadWebSDK, null), this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
