package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.HSBridgeException;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.HSStateHandler;
import co.hyperverge.hyperkyc.utils.HSStateHandler$retrieveState$3$type$1;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import com.google.gson.Gson;
import java.io.File;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startBarcodeFlow$2", f = "HKMainActivity.kt", i = {0}, l = {1557}, m = "invokeSuspend", n = {"$this$coroutineScope"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class HKMainActivity$startBarcodeFlow$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ WorkflowUIState.BarcodeCapture $barcodeFlowUIState;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$startBarcodeFlow$2(HKMainActivity hKMainActivity, WorkflowUIState.BarcodeCapture barcodeCapture, Continuation<? super HKMainActivity$startBarcodeFlow$2> continuation) {
        super(2, continuation);
        this.this$0 = hKMainActivity;
        this.$barcodeFlowUIState = barcodeCapture;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HKMainActivity$startBarcodeFlow$2 hKMainActivity$startBarcodeFlow$2 = new HKMainActivity$startBarcodeFlow$2(this.this$0, this.$barcodeFlowUIState, continuation);
        hKMainActivity$startBarcodeFlow$2.L$0 = obj;
        return hKMainActivity$startBarcodeFlow$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Unit>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Unit>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((HKMainActivity$startBarcodeFlow$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(2:3|(4:5|6|7|8)(2:108|109))(20:110|(1:887)(1:114)|(1:123)(1:119)|(1:121)(1:122)|124|(1:126)|127|(1:886)(1:131)|132|(1:134)(10:841|842|843|844|845|846|(1:848)|849|(2:851|(9:853|(1:857)|(1:877)(1:873)|(1:875)(1:876)|859|(1:861)|862|(1:869)(1:866)|867)(1:878))(1:879)|868)|135|136|137|(48:155|156|157|158|159|(3:820|821|(44:823|824|825|(39:827|168|169|(1:171)|172|173|(32:178|179|(1:181)(9:750|751|752|753|754|755|(1:757)|758|(4:760|761|762|(11:764|765|766|767|(2:(1:792)(1:789)|(1:791))(1:773)|774|(1:776)|777|(2:782|783)|785|783)(1:796))(1:799))|182|183|(1:185)(1:747)|186|187|188|189|(3:730|731|(23:733|734|735|(18:737|198|199|(1:201)|202|203|(11:208|209|(1:211)|212|(1:214)(13:642|643|644|645|646|647|648|649|650|(1:652)|653|(7:655|656|657|658|659|660|(22:662|663|664|665|(2:667|(19:669|670|671|(14:673|674|(1:676)|677|(9:682|683|(1:685)|686|216|217|(25:361|362|363|364|(3:623|624|(22:626|627|628|(17:630|373|374|(1:376)|377|378|(10:383|384|(1:386)|387|(1:389)(13:539|540|541|542|543|544|545|546|547|(1:549)|550|(4:552|553|554|(17:556|557|558|559|(2:561|(14:563|564|565|(9:567|568|(1:570)|571|(4:576|577|(1:579)|580)|581|577|(0)|580)|(1:588)(1:585)|(1:587)|568|(0)|571|(5:573|576|577|(0)|580)|581|577|(0)|580))|590|(1:583)|588|(0)|568|(0)|571|(0)|581|577|(0)|580)(1:594))|598)|390|391|(26:393|(1:529)(3:397|398|(23:400|401|(1:403)|404|(18:409|410|(1:412)(1:520)|(1:414)(1:519)|415|419|420|421|422|423|424|425|426|427|(1:429)|430|(4:432|433|434|(18:436|437|438|439|(2:441|(14:443|444|(10:446|447|(1:449)|450|(5:455|456|(1:458)(1:485)|(1:460)(1:484)|461)|486|456|(0)(0)|(0)(0)|461)|(1:493)(1:490)|(1:492)|447|(0)|450|(6:452|455|456|(0)(0)|(0)(0)|461)|486|456|(0)(0)|(0)(0)|461))|495|(1:488)|493|(0)|447|(0)|450|(0)|486|456|(0)(0)|(0)(0)|461)(1:500))|503)|521|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503))|(1:528)(1:525)|(1:527)|401|(0)|404|(19:406|409|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503)|521|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503)(1:530)|462|(4:(1:467)|468|(1:470)(2:475|(1:477)(2:478|(2:480|481)))|(7:472|12|13|14|(21:16|(1:96)(1:20)|(1:28)(1:25)|(1:27)|29|(1:31)|32|(1:95)(1:36)|37|(1:39)|40|41|42|43|(1:45)|46|(2:48|(14:50|(1:90)(1:54)|(1:62)(1:59)|(1:61)|63|(1:65)|66|(1:89)(1:70)|71|(1:73)(1:88)|74|75|(4:77|(1:79)|80|(1:82))(1:87)|83))|91|75|(0)(0)|83)(1:97)|84|85)(2:473|474))(2:482|483))|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0))|(1:622)(1:370)|(1:372)(1:621)|373|374|(0)|377|378|(11:380|383|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0))|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0)))|366|(1:368)|622|(0)(0)|373|374|(0)|377|378|(0)|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0))(22:219|220|221|222|223|224|(3:343|344|(17:346|347|348|(12:350|233|234|(1:236)|237|238|(5:243|244|(1:246)|247|(13:255|256|257|258|259|260|261|262|263|(1:265)|266|(4:268|269|270|(17:272|273|274|275|(2:277|(14:279|280|281|(9:283|284|(1:286)|287|(4:292|293|(1:295)|296)|297|293|(0)|296)|(1:305)(1:301)|(1:303)(1:304)|284|(0)|287|(5:289|292|293|(0)|296)|297|293|(0)|296))|307|(1:299)|305|(0)(0)|284|(0)|287|(0)|297|293|(0)|296)(1:311))|315)(1:249))|337|244|(0)|247|(0)(0))|(1:342)(1:230)|(1:232)|233|234|(0)|237|238|(6:240|243|244|(0)|247|(0)(0))|337|244|(0)|247|(0)(0)))|226|(1:228)|342|(0)|233|234|(0)|237|238|(0)|337|244|(0)|247|(0)(0))|250|251)|688|683|(0)|686|216|217|(0)(0)|250|251)|(1:696)(1:692)|(1:694)(1:695)|674|(0)|677|(10:679|682|683|(0)|686|216|217|(0)(0)|250|251)|688|683|(0)|686|216|217|(0)(0)|250|251))|700|(1:690)|696|(0)(0)|674|(0)|677|(0)|688|683|(0)|686|216|217|(0)(0)|250|251))|709)|215|216|217|(0)(0)|250|251)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|(1:729)(1:195)|(1:197)(1:728)|198|199|(0)|202|203|(12:205|208|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251))|191|(1:193)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|815|179|(0)(0)|182|183|(0)(0)|186|187|188|189|(0)|191|(0)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|(1:819)(1:165)|(1:167)(1:818)|168|169|(0)|172|173|(33:175|178|179|(0)(0)|182|183|(0)(0)|186|187|188|189|(0)|191|(0)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|815|179|(0)(0)|182|183|(0)(0)|186|187|188|189|(0)|191|(0)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251))|161|(1:163)|819|(0)(0)|168|169|(0)|172|173|(0)|815|179|(0)(0)|182|183|(0)(0)|186|187|188|189|(0)|191|(0)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)(8:139|140|141|142|143|144|145|(1:147)(1:148))|100|101|14|(0)(0)|84|85)|9|10|11|12|13|14|(0)(0)|84|85|(2:(0)|(1:252))) */
    /* JADX WARN: Can't wrap try/catch for region: R(22:(4:361|362|363|364)|(3:623|624|(22:626|627|628|(17:630|373|374|(1:376)|377|378|(10:383|384|(1:386)|387|(1:389)(13:539|540|541|542|543|544|545|546|547|(1:549)|550|(4:552|553|554|(17:556|557|558|559|(2:561|(14:563|564|565|(9:567|568|(1:570)|571|(4:576|577|(1:579)|580)|581|577|(0)|580)|(1:588)(1:585)|(1:587)|568|(0)|571|(5:573|576|577|(0)|580)|581|577|(0)|580))|590|(1:583)|588|(0)|568|(0)|571|(0)|581|577|(0)|580)(1:594))|598)|390|391|(26:393|(1:529)(3:397|398|(23:400|401|(1:403)|404|(18:409|410|(1:412)(1:520)|(1:414)(1:519)|415|419|420|421|422|423|424|425|426|427|(1:429)|430|(4:432|433|434|(18:436|437|438|439|(2:441|(14:443|444|(10:446|447|(1:449)|450|(5:455|456|(1:458)(1:485)|(1:460)(1:484)|461)|486|456|(0)(0)|(0)(0)|461)|(1:493)(1:490)|(1:492)|447|(0)|450|(6:452|455|456|(0)(0)|(0)(0)|461)|486|456|(0)(0)|(0)(0)|461))|495|(1:488)|493|(0)|447|(0)|450|(0)|486|456|(0)(0)|(0)(0)|461)(1:500))|503)|521|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503))|(1:528)(1:525)|(1:527)|401|(0)|404|(19:406|409|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503)|521|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503)(1:530)|462|(4:(1:467)|468|(1:470)(2:475|(1:477)(2:478|(2:480|481)))|(7:472|12|13|14|(21:16|(1:96)(1:20)|(1:28)(1:25)|(1:27)|29|(1:31)|32|(1:95)(1:36)|37|(1:39)|40|41|42|43|(1:45)|46|(2:48|(14:50|(1:90)(1:54)|(1:62)(1:59)|(1:61)|63|(1:65)|66|(1:89)(1:70)|71|(1:73)(1:88)|74|75|(4:77|(1:79)|80|(1:82))(1:87)|83))|91|75|(0)(0)|83)(1:97)|84|85)(2:473|474))(2:482|483))|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0))|(1:622)(1:370)|(1:372)(1:621)|373|374|(0)|377|378|(11:380|383|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0))|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0)))|366|(1:368)|622|(0)(0)|373|374|(0)|377|378|(0)|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(26:393|(1:529)(3:397|398|(23:400|401|(1:403)|404|(18:409|410|(1:412)(1:520)|(1:414)(1:519)|415|419|420|421|422|423|424|425|426|427|(1:429)|430|(4:432|433|434|(18:436|437|438|439|(2:441|(14:443|444|(10:446|447|(1:449)|450|(5:455|456|(1:458)(1:485)|(1:460)(1:484)|461)|486|456|(0)(0)|(0)(0)|461)|(1:493)(1:490)|(1:492)|447|(0)|450|(6:452|455|456|(0)(0)|(0)(0)|461)|486|456|(0)(0)|(0)(0)|461))|495|(1:488)|493|(0)|447|(0)|450|(0)|486|456|(0)(0)|(0)(0)|461)(1:500))|503)|521|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503))|(1:528)(1:525)|(1:527)|401|(0)|404|(19:406|409|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503)|521|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503) */
    /* JADX WARN: Can't wrap try/catch for region: R(47:155|156|157|(2:158|159)|(3:820|821|(44:823|824|825|(39:827|168|169|(1:171)|172|173|(32:178|179|(1:181)(9:750|751|752|753|754|755|(1:757)|758|(4:760|761|762|(11:764|765|766|767|(2:(1:792)(1:789)|(1:791))(1:773)|774|(1:776)|777|(2:782|783)|785|783)(1:796))(1:799))|182|183|(1:185)(1:747)|186|187|188|189|(3:730|731|(23:733|734|735|(18:737|198|199|(1:201)|202|203|(11:208|209|(1:211)|212|(1:214)(13:642|643|644|645|646|647|648|649|650|(1:652)|653|(7:655|656|657|658|659|660|(22:662|663|664|665|(2:667|(19:669|670|671|(14:673|674|(1:676)|677|(9:682|683|(1:685)|686|216|217|(25:361|362|363|364|(3:623|624|(22:626|627|628|(17:630|373|374|(1:376)|377|378|(10:383|384|(1:386)|387|(1:389)(13:539|540|541|542|543|544|545|546|547|(1:549)|550|(4:552|553|554|(17:556|557|558|559|(2:561|(14:563|564|565|(9:567|568|(1:570)|571|(4:576|577|(1:579)|580)|581|577|(0)|580)|(1:588)(1:585)|(1:587)|568|(0)|571|(5:573|576|577|(0)|580)|581|577|(0)|580))|590|(1:583)|588|(0)|568|(0)|571|(0)|581|577|(0)|580)(1:594))|598)|390|391|(26:393|(1:529)(3:397|398|(23:400|401|(1:403)|404|(18:409|410|(1:412)(1:520)|(1:414)(1:519)|415|419|420|421|422|423|424|425|426|427|(1:429)|430|(4:432|433|434|(18:436|437|438|439|(2:441|(14:443|444|(10:446|447|(1:449)|450|(5:455|456|(1:458)(1:485)|(1:460)(1:484)|461)|486|456|(0)(0)|(0)(0)|461)|(1:493)(1:490)|(1:492)|447|(0)|450|(6:452|455|456|(0)(0)|(0)(0)|461)|486|456|(0)(0)|(0)(0)|461))|495|(1:488)|493|(0)|447|(0)|450|(0)|486|456|(0)(0)|(0)(0)|461)(1:500))|503)|521|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503))|(1:528)(1:525)|(1:527)|401|(0)|404|(19:406|409|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503)|521|410|(0)(0)|(0)(0)|415|419|420|421|422|423|424|425|426|427|(0)|430|(0)|503)(1:530)|462|(4:(1:467)|468|(1:470)(2:475|(1:477)(2:478|(2:480|481)))|(7:472|12|13|14|(21:16|(1:96)(1:20)|(1:28)(1:25)|(1:27)|29|(1:31)|32|(1:95)(1:36)|37|(1:39)|40|41|42|43|(1:45)|46|(2:48|(14:50|(1:90)(1:54)|(1:62)(1:59)|(1:61)|63|(1:65)|66|(1:89)(1:70)|71|(1:73)(1:88)|74|75|(4:77|(1:79)|80|(1:82))(1:87)|83))|91|75|(0)(0)|83)(1:97)|84|85)(2:473|474))(2:482|483))|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0))|(1:622)(1:370)|(1:372)(1:621)|373|374|(0)|377|378|(11:380|383|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0))|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0)))|366|(1:368)|622|(0)(0)|373|374|(0)|377|378|(0)|617|384|(0)|387|(0)(0)|390|391|(0)(0)|462|(0)(0))(22:219|220|221|222|223|224|(3:343|344|(17:346|347|348|(12:350|233|234|(1:236)|237|238|(5:243|244|(1:246)|247|(13:255|256|257|258|259|260|261|262|263|(1:265)|266|(4:268|269|270|(17:272|273|274|275|(2:277|(14:279|280|281|(9:283|284|(1:286)|287|(4:292|293|(1:295)|296)|297|293|(0)|296)|(1:305)(1:301)|(1:303)(1:304)|284|(0)|287|(5:289|292|293|(0)|296)|297|293|(0)|296))|307|(1:299)|305|(0)(0)|284|(0)|287|(0)|297|293|(0)|296)(1:311))|315)(1:249))|337|244|(0)|247|(0)(0))|(1:342)(1:230)|(1:232)|233|234|(0)|237|238|(6:240|243|244|(0)|247|(0)(0))|337|244|(0)|247|(0)(0)))|226|(1:228)|342|(0)|233|234|(0)|237|238|(0)|337|244|(0)|247|(0)(0))|250|251)|688|683|(0)|686|216|217|(0)(0)|250|251)|(1:696)(1:692)|(1:694)(1:695)|674|(0)|677|(10:679|682|683|(0)|686|216|217|(0)(0)|250|251)|688|683|(0)|686|216|217|(0)(0)|250|251))|700|(1:690)|696|(0)(0)|674|(0)|677|(0)|688|683|(0)|686|216|217|(0)(0)|250|251))|709)|215|216|217|(0)(0)|250|251)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|(1:729)(1:195)|(1:197)(1:728)|198|199|(0)|202|203|(12:205|208|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251))|191|(1:193)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|815|179|(0)(0)|182|183|(0)(0)|186|187|188|189|(0)|191|(0)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|(1:819)(1:165)|(1:167)(1:818)|168|169|(0)|172|173|(33:175|178|179|(0)(0)|182|183|(0)(0)|186|187|188|189|(0)|191|(0)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251)|815|179|(0)(0)|182|183|(0)(0)|186|187|188|189|(0)|191|(0)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251))|161|(1:163)|819|(0)(0)|168|169|(0)|172|173|(0)|815|179|(0)(0)|182|183|(0)(0)|186|187|188|189|(0)|191|(0)|729|(0)(0)|198|199|(0)|202|203|(0)|725|209|(0)|212|(0)(0)|215|216|217|(0)(0)|250|251) */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0e8f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0e90, code lost:
    
        r2 = r0;
        r23 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x00aa, code lost:
    
        if (r7 != null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0ef9, code lost:
    
        if (r1 != 0) goto L734;
     */
    /* JADX WARN: Code restructure failed: missing block: B:505:0x0af3, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:506:0x0af4, code lost:
    
        r1 = r36;
        r2 = r0;
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:509:0x09e2, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:510:0x09eb, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:512:0x09ec, code lost:
    
        r3 = kotlin.Result.INSTANCE;
        r1 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:514:0x09e4, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:515:0x09e9, code lost:
    
        r4 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:517:0x09e6, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:518:0x09e7, code lost:
    
        r6 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x1006, code lost:
    
        if (r2 != 0) goto L774;
     */
    /* JADX WARN: Code restructure failed: missing block: B:618:0x08c6, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:619:0x08c7, code lost:
    
        r6 = r25;
        r8 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:620:0x08ce, code lost:
    
        r4 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:641:0x0df0, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:726:0x0df7, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:727:0x0df8, code lost:
    
        r19 = "android.app.AppGlobals";
     */
    /* JADX WARN: Code restructure failed: missing block: B:743:0x0dfd, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:744:0x0dfe, code lost:
    
        r4 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:745:0x0e04, code lost:
    
        r7 = "getInitialApplication";
        r19 = "android.app.AppGlobals";
        r9 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:748:0x0e00, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:749:0x0e01, code lost:
    
        r4 = r2;
        r27 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:816:0x0e11, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:817:0x0e12, code lost:
    
        r7 = "getInitialApplication";
        r19 = "android.app.AppGlobals";
     */
    /* JADX WARN: Code restructure failed: missing block: B:858:0x01b4, code lost:
    
        if (r7 != null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0e8c, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0e2f  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0244 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0ebd  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02eb A[Catch: all -> 0x02c3, TRY_ENTER, TRY_LEAVE, TryCatch #15 {all -> 0x02c3, blocks: (B:825:0x029c, B:171:0x02eb, B:175:0x0300, B:178:0x0307, B:163:0x02b8, B:165:0x02be), top: B:824:0x029c }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0300 A[Catch: all -> 0x02c3, TRY_ENTER, TryCatch #15 {all -> 0x02c3, blocks: (B:825:0x029c, B:171:0x02eb, B:175:0x0300, B:178:0x0307, B:163:0x02b8, B:165:0x02be), top: B:824:0x029c }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x04ca A[Catch: all -> 0x04d5, TryCatch #26 {all -> 0x04d5, blocks: (B:735:0x04b5, B:201:0x04fa, B:205:0x050f, B:208:0x0516, B:398:0x0922, B:401:0x0941, B:403:0x0955, B:404:0x095e, B:406:0x096a, B:409:0x0971, B:410:0x0982, B:412:0x0994, B:414:0x099c, B:415:0x09af, B:521:0x097e, B:523:0x0931, B:525:0x0937, B:193:0x04ca, B:195:0x04d0), top: B:734:0x04b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x04e2  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x04fa A[Catch: all -> 0x04d5, TRY_ENTER, TRY_LEAVE, TryCatch #26 {all -> 0x04d5, blocks: (B:735:0x04b5, B:201:0x04fa, B:205:0x050f, B:208:0x0516, B:398:0x0922, B:401:0x0941, B:403:0x0955, B:404:0x095e, B:406:0x096a, B:409:0x0971, B:410:0x0982, B:412:0x0994, B:414:0x099c, B:415:0x09af, B:521:0x097e, B:523:0x0931, B:525:0x0937, B:193:0x04ca, B:195:0x04d0), top: B:734:0x04b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x050f A[Catch: all -> 0x04d5, TRY_ENTER, TryCatch #26 {all -> 0x04d5, blocks: (B:735:0x04b5, B:201:0x04fa, B:205:0x050f, B:208:0x0516, B:398:0x0922, B:401:0x0941, B:403:0x0955, B:404:0x095e, B:406:0x096a, B:409:0x0971, B:410:0x0982, B:412:0x0994, B:414:0x099c, B:415:0x09af, B:521:0x097e, B:523:0x0931, B:525:0x0937, B:193:0x04ca, B:195:0x04d0), top: B:734:0x04b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x055e  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0bc0  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0c2a  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0c40 A[Catch: all -> 0x0c21, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0c21, blocks: (B:348:0x0bfd, B:236:0x0c40, B:240:0x0c55, B:243:0x0c5c, B:228:0x0c16, B:230:0x0c1c), top: B:347:0x0bfd }] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0c55 A[Catch: all -> 0x0c21, TRY_ENTER, TryCatch #0 {all -> 0x0c21, blocks: (B:348:0x0bfd, B:236:0x0c40, B:240:0x0c55, B:243:0x0c5c, B:228:0x0c16, B:230:0x0c1c), top: B:347:0x0bfd }] */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0c88  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0dc8  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0ca2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0ce2  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0ceb  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0d59 A[Catch: all -> 0x0de2, TryCatch #30 {all -> 0x0de2, blocks: (B:250:0x0dce, B:251:0x0de1, B:281:0x0d28, B:284:0x0d45, B:286:0x0d59, B:287:0x0d62, B:289:0x0d6e, B:292:0x0d75, B:293:0x0d86, B:296:0x0da2, B:297:0x0d82, B:299:0x0d33, B:301:0x0d39), top: B:280:0x0d28 }] */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0d6e A[Catch: all -> 0x0de2, TryCatch #30 {all -> 0x0de2, blocks: (B:250:0x0dce, B:251:0x0de1, B:281:0x0d28, B:284:0x0d45, B:286:0x0d59, B:287:0x0d62, B:289:0x0d6e, B:292:0x0d75, B:293:0x0d86, B:296:0x0da2, B:297:0x0d82, B:299:0x0d33, B:301:0x0d39), top: B:280:0x0d28 }] */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0da0  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0d41  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0d44  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x06a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x071c  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0734 A[Catch: all -> 0x0712, TRY_ENTER, TRY_LEAVE, TryCatch #59 {all -> 0x0712, blocks: (B:628:0x06f3, B:376:0x0734, B:380:0x0749, B:383:0x0750, B:368:0x0707, B:370:0x070d), top: B:627:0x06f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0749 A[Catch: all -> 0x0712, TRY_ENTER, TryCatch #59 {all -> 0x0712, blocks: (B:628:0x06f3, B:376:0x0734, B:380:0x0749, B:383:0x0750, B:368:0x0707, B:370:0x070d), top: B:627:0x06f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:386:0x077a  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0794  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x08e8 A[Catch: all -> 0x0bb6, TryCatch #8 {all -> 0x0bb6, blocks: (B:391:0x08e2, B:393:0x08e8, B:395:0x0912, B:444:0x0a42, B:447:0x0a5d, B:449:0x0a71, B:450:0x0a7a, B:452:0x0a86, B:455:0x0a8d, B:456:0x0a9e, B:458:0x0aaf, B:460:0x0ab7, B:461:0x0aca, B:462:0x0afc, B:468:0x0b25, B:470:0x0b37, B:472:0x0b6d, B:473:0x0b7a, B:474:0x0b81, B:475:0x0b45, B:477:0x0b51, B:478:0x0b5f, B:480:0x0b82, B:481:0x0baf, B:482:0x0bb0, B:483:0x0bb5, B:486:0x0a9a, B:488:0x0a4d, B:490:0x0a53, B:535:0x08d8), top: B:534:0x08d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0955 A[Catch: all -> 0x04d5, TryCatch #26 {all -> 0x04d5, blocks: (B:735:0x04b5, B:201:0x04fa, B:205:0x050f, B:208:0x0516, B:398:0x0922, B:401:0x0941, B:403:0x0955, B:404:0x095e, B:406:0x096a, B:409:0x0971, B:410:0x0982, B:412:0x0994, B:414:0x099c, B:415:0x09af, B:521:0x097e, B:523:0x0931, B:525:0x0937, B:193:0x04ca, B:195:0x04d0), top: B:734:0x04b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0994 A[Catch: all -> 0x04d5, TryCatch #26 {all -> 0x04d5, blocks: (B:735:0x04b5, B:201:0x04fa, B:205:0x050f, B:208:0x0516, B:398:0x0922, B:401:0x0941, B:403:0x0955, B:404:0x095e, B:406:0x096a, B:409:0x0971, B:410:0x0982, B:412:0x0994, B:414:0x099c, B:415:0x09af, B:521:0x097e, B:523:0x0931, B:525:0x0937, B:193:0x04ca, B:195:0x04d0), top: B:734:0x04b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:414:0x099c A[Catch: all -> 0x04d5, TryCatch #26 {all -> 0x04d5, blocks: (B:735:0x04b5, B:201:0x04fa, B:205:0x050f, B:208:0x0516, B:398:0x0922, B:401:0x0941, B:403:0x0955, B:404:0x095e, B:406:0x096a, B:409:0x0971, B:410:0x0982, B:412:0x0994, B:414:0x099c, B:415:0x09af, B:521:0x097e, B:523:0x0931, B:525:0x0937, B:193:0x04ca, B:195:0x04d0), top: B:734:0x04b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:429:0x09fc  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0a05  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0a71 A[Catch: all -> 0x0bb6, TryCatch #8 {all -> 0x0bb6, blocks: (B:391:0x08e2, B:393:0x08e8, B:395:0x0912, B:444:0x0a42, B:447:0x0a5d, B:449:0x0a71, B:450:0x0a7a, B:452:0x0a86, B:455:0x0a8d, B:456:0x0a9e, B:458:0x0aaf, B:460:0x0ab7, B:461:0x0aca, B:462:0x0afc, B:468:0x0b25, B:470:0x0b37, B:472:0x0b6d, B:473:0x0b7a, B:474:0x0b81, B:475:0x0b45, B:477:0x0b51, B:478:0x0b5f, B:480:0x0b82, B:481:0x0baf, B:482:0x0bb0, B:483:0x0bb5, B:486:0x0a9a, B:488:0x0a4d, B:490:0x0a53, B:535:0x08d8), top: B:534:0x08d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0a86 A[Catch: all -> 0x0bb6, TryCatch #8 {all -> 0x0bb6, blocks: (B:391:0x08e2, B:393:0x08e8, B:395:0x0912, B:444:0x0a42, B:447:0x0a5d, B:449:0x0a71, B:450:0x0a7a, B:452:0x0a86, B:455:0x0a8d, B:456:0x0a9e, B:458:0x0aaf, B:460:0x0ab7, B:461:0x0aca, B:462:0x0afc, B:468:0x0b25, B:470:0x0b37, B:472:0x0b6d, B:473:0x0b7a, B:474:0x0b81, B:475:0x0b45, B:477:0x0b51, B:478:0x0b5f, B:480:0x0b82, B:481:0x0baf, B:482:0x0bb0, B:483:0x0bb5, B:486:0x0a9a, B:488:0x0a4d, B:490:0x0a53, B:535:0x08d8), top: B:534:0x08d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0aaf A[Catch: all -> 0x0bb6, TryCatch #8 {all -> 0x0bb6, blocks: (B:391:0x08e2, B:393:0x08e8, B:395:0x0912, B:444:0x0a42, B:447:0x0a5d, B:449:0x0a71, B:450:0x0a7a, B:452:0x0a86, B:455:0x0a8d, B:456:0x0a9e, B:458:0x0aaf, B:460:0x0ab7, B:461:0x0aca, B:462:0x0afc, B:468:0x0b25, B:470:0x0b37, B:472:0x0b6d, B:473:0x0b7a, B:474:0x0b81, B:475:0x0b45, B:477:0x0b51, B:478:0x0b5f, B:480:0x0b82, B:481:0x0baf, B:482:0x0bb0, B:483:0x0bb5, B:486:0x0a9a, B:488:0x0a4d, B:490:0x0a53, B:535:0x08d8), top: B:534:0x08d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0ab7 A[Catch: all -> 0x0bb6, TryCatch #8 {all -> 0x0bb6, blocks: (B:391:0x08e2, B:393:0x08e8, B:395:0x0912, B:444:0x0a42, B:447:0x0a5d, B:449:0x0a71, B:450:0x0a7a, B:452:0x0a86, B:455:0x0a8d, B:456:0x0a9e, B:458:0x0aaf, B:460:0x0ab7, B:461:0x0aca, B:462:0x0afc, B:468:0x0b25, B:470:0x0b37, B:472:0x0b6d, B:473:0x0b7a, B:474:0x0b81, B:475:0x0b45, B:477:0x0b51, B:478:0x0b5f, B:480:0x0b82, B:481:0x0baf, B:482:0x0bb0, B:483:0x0bb5, B:486:0x0a9a, B:488:0x0a4d, B:490:0x0a53, B:535:0x08d8), top: B:534:0x08d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0b1a  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0bb0 A[Catch: all -> 0x0bb6, TryCatch #8 {all -> 0x0bb6, blocks: (B:391:0x08e2, B:393:0x08e8, B:395:0x0912, B:444:0x0a42, B:447:0x0a5d, B:449:0x0a71, B:450:0x0a7a, B:452:0x0a86, B:455:0x0a8d, B:456:0x0a9e, B:458:0x0aaf, B:460:0x0ab7, B:461:0x0aca, B:462:0x0afc, B:468:0x0b25, B:470:0x0b37, B:472:0x0b6d, B:473:0x0b7a, B:474:0x0b81, B:475:0x0b45, B:477:0x0b51, B:478:0x0b5f, B:480:0x0b82, B:481:0x0baf, B:482:0x0bb0, B:483:0x0bb5, B:486:0x0a9a, B:488:0x0a4d, B:490:0x0a53, B:535:0x08d8), top: B:534:0x08d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0ac9  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0ab4  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0a5b  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x09ae  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x0999  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x0afa  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x079c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:549:0x07dc  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x07e5  */
    /* JADX WARN: Removed duplicated region for block: B:570:0x0851 A[Catch: all -> 0x08c0, TryCatch #11 {all -> 0x08c0, blocks: (B:390:0x08b9, B:565:0x0822, B:568:0x083d, B:570:0x0851, B:571:0x085a, B:573:0x0866, B:576:0x086d, B:577:0x087e, B:580:0x0898, B:581:0x087a, B:583:0x082d, B:585:0x0833), top: B:564:0x0822 }] */
    /* JADX WARN: Removed duplicated region for block: B:573:0x0866 A[Catch: all -> 0x08c0, TryCatch #11 {all -> 0x08c0, blocks: (B:390:0x08b9, B:565:0x0822, B:568:0x083d, B:570:0x0851, B:571:0x085a, B:573:0x0866, B:576:0x086d, B:577:0x087e, B:580:0x0898, B:581:0x087a, B:583:0x082d, B:585:0x0833), top: B:564:0x0822 }] */
    /* JADX WARN: Removed duplicated region for block: B:579:0x0896  */
    /* JADX WARN: Removed duplicated region for block: B:587:0x083b  */
    /* JADX WARN: Removed duplicated region for block: B:621:0x071f  */
    /* JADX WARN: Removed duplicated region for block: B:642:0x0564 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:652:0x05a4  */
    /* JADX WARN: Removed duplicated region for block: B:655:0x05ad  */
    /* JADX WARN: Removed duplicated region for block: B:676:0x0626 A[Catch: all -> 0x0602, TryCatch #47 {all -> 0x0602, blocks: (B:671:0x05ec, B:674:0x0612, B:676:0x0626, B:677:0x062f, B:679:0x063b, B:682:0x0642, B:683:0x0653, B:686:0x066f, B:688:0x064f, B:690:0x05f7, B:692:0x05fd), top: B:670:0x05ec }] */
    /* JADX WARN: Removed duplicated region for block: B:679:0x063b A[Catch: all -> 0x0602, TryCatch #47 {all -> 0x0602, blocks: (B:671:0x05ec, B:674:0x0612, B:676:0x0626, B:677:0x062f, B:679:0x063b, B:682:0x0642, B:683:0x0653, B:686:0x066f, B:688:0x064f, B:690:0x05f7, B:692:0x05fd), top: B:670:0x05ec }] */
    /* JADX WARN: Removed duplicated region for block: B:685:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:694:0x060e  */
    /* JADX WARN: Removed duplicated region for block: B:695:0x0611  */
    /* JADX WARN: Removed duplicated region for block: B:728:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x04a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:747:0x0477 A[Catch: all -> 0x0e00, TryCatch #46 {all -> 0x0e00, blocks: (B:183:0x0454, B:186:0x047b, B:747:0x0477), top: B:182:0x0454 }] */
    /* JADX WARN: Removed duplicated region for block: B:750:0x0340 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:757:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:760:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x1099  */
    /* JADX WARN: Removed duplicated region for block: B:799:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:818:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:848:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:851:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:879:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x10b4  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x10c1  */
    /* JADX WARN: Type inference failed for: r15v11, types: [T] */
    /* JADX WARN: Type inference failed for: r15v25 */
    /* JADX WARN: Type inference failed for: r15v26 */
    /* JADX WARN: Type inference failed for: r15v29 */
    /* JADX WARN: Type inference failed for: r1v105 */
    /* JADX WARN: Type inference failed for: r1v106 */
    /* JADX WARN: Type inference failed for: r1v107 */
    /* JADX WARN: Type inference failed for: r1v110, types: [T] */
    /* JADX WARN: Type inference failed for: r1v159, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v161, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v187, types: [T] */
    /* JADX WARN: Type inference failed for: r1v233, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v234 */
    /* JADX WARN: Type inference failed for: r1v235 */
    /* JADX WARN: Type inference failed for: r1v236 */
    /* JADX WARN: Type inference failed for: r1v240, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v243 */
    /* JADX WARN: Type inference failed for: r1v244 */
    /* JADX WARN: Type inference failed for: r2v15, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v185, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v224, types: [T] */
    /* JADX WARN: Type inference failed for: r2v234, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v235 */
    /* JADX WARN: Type inference failed for: r2v236 */
    /* JADX WARN: Type inference failed for: r2v237 */
    /* JADX WARN: Type inference failed for: r2v241, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v256 */
    /* JADX WARN: Type inference failed for: r3v104, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v106, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v112 */
    /* JADX WARN: Type inference failed for: r3v113 */
    /* JADX WARN: Type inference failed for: r3v117, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v150 */
    /* JADX WARN: Type inference failed for: r3v151 */
    /* JADX WARN: Type inference failed for: r3v152 */
    /* JADX WARN: Type inference failed for: r3v31, types: [T] */
    /* JADX WARN: Type inference failed for: r3v54 */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v59, types: [T] */
    /* JADX WARN: Type inference failed for: r3v69, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v71, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v89 */
    /* JADX WARN: Type inference failed for: r3v90 */
    /* JADX WARN: Type inference failed for: r3v91 */
    /* JADX WARN: Type inference failed for: r3v94, types: [T] */
    /* JADX WARN: Type inference failed for: r4v116, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v117, types: [T] */
    /* JADX WARN: Type inference failed for: r4v144, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v145 */
    /* JADX WARN: Type inference failed for: r4v146 */
    /* JADX WARN: Type inference failed for: r4v147 */
    /* JADX WARN: Type inference failed for: r4v171 */
    /* JADX WARN: Type inference failed for: r4v172 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v27, types: [T] */
    /* JADX WARN: Type inference failed for: r4v37, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v39, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v67, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v121, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v133, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v142 */
    /* JADX WARN: Type inference failed for: r6v38 */
    /* JADX WARN: Type inference failed for: r6v39 */
    /* JADX WARN: Type inference failed for: r6v40, types: [T] */
    /* JADX WARN: Type inference failed for: r7v119, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v141 */
    /* JADX WARN: Type inference failed for: r7v142 */
    /* JADX WARN: Type inference failed for: r7v158 */
    /* JADX WARN: Type inference failed for: r7v159 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19, types: [T] */
    /* JADX WARN: Type inference failed for: r7v5, types: [T] */
    /* JADX WARN: Type inference failed for: r8v52 */
    /* JADX WARN: Type inference failed for: r8v53 */
    /* JADX WARN: Type inference failed for: r8v54, types: [T] */
    /* JADX WARN: Type inference failed for: r8v74 */
    /* JADX WARN: Type inference failed for: r9v41, types: [T] */
    /* JADX WARN: Type inference failed for: r9v47 */
    /* JADX WARN: Type inference failed for: r9v48 */
    /* JADX WARN: Type inference failed for: r9v67 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CharSequence charSequence;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Object m1202constructorimpl;
        String str7;
        String str8;
        String str9;
        Class<?> cls;
        String str10;
        String className;
        HKMainActivity hKMainActivity;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        CoroutineScope coroutineScope;
        Throwable th;
        CoroutineScope coroutineScope2;
        HSStateHandler hsStateHandler;
        String tag;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        HKMainActivity hKMainActivity2;
        Ref.ObjectRef objectRef;
        StackTraceElement stackTraceElement;
        String className2;
        String str16;
        String str17;
        String substringAfterLast$default;
        String str18;
        Matcher matcher;
        String str19;
        String str20;
        Object m1202constructorimpl2;
        String str21;
        String str22;
        ?? canonicalName;
        Class<?> cls2;
        String str23;
        String className3;
        File file;
        Ref.ObjectRef objectRef2;
        StackTraceElement stackTraceElement2;
        String className4;
        String str24;
        String substringAfterLast$default2;
        String str25;
        Matcher matcher2;
        String str26;
        String str27;
        Object m1202constructorimpl3;
        String str28;
        StackTraceElement stackTraceElement3;
        String str29;
        Class<?> cls3;
        Matcher matcher3;
        String str30;
        String str31;
        String substringAfterLast$default3;
        String str32;
        Throwable th2;
        Object obj2;
        Object m1202constructorimpl4;
        Throwable m1205exceptionOrNullimpl;
        Object obj3;
        HVError hVError;
        JSONObject jSONObject;
        WorkflowUIState.BarcodeCapture barcodeCapture;
        ?? canonicalName2;
        Class<?> cls4;
        Matcher matcher4;
        String str33;
        String localizedMessage;
        String str34;
        Object m1202constructorimpl5;
        ?? canonicalName3;
        Class<?> cls5;
        Matcher matcher5;
        String str35;
        String localizedMessage2;
        String str36;
        String className5;
        HyperLogger.Level level2;
        HyperLogger companion2;
        StringBuilder sb2;
        Ref.ObjectRef objectRef3;
        StackTraceElement stackTraceElement4;
        String className6;
        String str37;
        String str38;
        String substringAfterLast$default4;
        String str39;
        Matcher matcher6;
        String str40;
        String str41;
        Object m1202constructorimpl6;
        ?? canonicalName4;
        Class<?> cls6;
        Matcher matcher7;
        String str42;
        String str43;
        Class<?> cls7;
        ?? substringAfterLast$default5;
        Matcher matcher8;
        String str44;
        String str45;
        Object m1202constructorimpl7;
        String str46;
        Class<?> cls8;
        Matcher matcher9;
        String str47;
        String str48;
        Class<?> cls9;
        Class<?> cls10;
        Class<?> cls11;
        Object performBarcodeCapture;
        CoroutineScope coroutineScope3;
        HKMainActivity hKMainActivity3;
        Class<?> cls12;
        String className7;
        Object m1202constructorimpl8;
        Throwable m1205exceptionOrNullimpl2;
        Object obj4;
        HKMainActivity hKMainActivity4;
        String str49;
        ?? r1;
        String str50;
        Object m1202constructorimpl9;
        String str51;
        String str52;
        ?? r2;
        String str53;
        Class<?> cls13;
        String className8;
        Class<?> cls14;
        String className9;
        HKMainActivity$startBarcodeFlow$2 hKMainActivity$startBarcodeFlow$2 = this;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = hKMainActivity$startBarcodeFlow$2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope4 = (CoroutineScope) hKMainActivity$startBarcodeFlow$2.L$0;
            HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
            charSequence = "co.hyperverge";
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement5 == null || (className7 = stackTraceElement5.getClassName()) == null) {
                str = "null cannot be cast to non-null type android.app.Application";
                str2 = "packageName";
                str3 = "Throwable().stackTrace";
            } else {
                str = "null cannot be cast to non-null type android.app.Application";
                str2 = "packageName";
                str3 = "Throwable().stackTrace";
                String substringAfterLast$default6 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                str4 = substringAfterLast$default6;
            }
            String canonicalName5 = (coroutineScope4 == null || (cls12 = coroutineScope4.getClass()) == null) ? null : cls12.getCanonicalName();
            str4 = canonicalName5 == null ? "N/A" : canonicalName5;
            objectRef4.element = str4;
            Matcher matcher10 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
            if (matcher10.find()) {
                ?? replaceAll = matcher10.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef4.element = replaceAll;
            }
            if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str5 = (String) objectRef4.element;
            } else {
                str5 = ((String) objectRef4.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str5);
            sb3.append(" - ");
            sb3.append("startBarcodeFlow() called");
            sb3.append(' ');
            sb3.append("");
            companion3.log(level3, sb3.toString());
            if (CoreExtsKt.isRelease()) {
                hKMainActivity$startBarcodeFlow$2 = this;
                str7 = str3;
                str8 = str2;
                str6 = str;
            } else {
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    str6 = str;
                    try {
                        Intrinsics.checkNotNull(invoke, str6);
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th3) {
                        th = th3;
                        Result.Companion companion5 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String str54 = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                        }
                        hKMainActivity$startBarcodeFlow$2 = this;
                        hKMainActivity = hKMainActivity$startBarcodeFlow$2.this$0;
                        WorkflowUIState.BarcodeCapture barcodeCapture2 = hKMainActivity$startBarcodeFlow$2.$barcodeFlowUIState;
                        Result.Companion companion6 = Result.INSTANCE;
                        if (!hKMainActivity.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                        }
                        th = th;
                        Result.Companion companion7 = Result.INSTANCE;
                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        HKMainActivity hKMainActivity5 = hKMainActivity$startBarcodeFlow$2.this$0;
                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                        if (m1205exceptionOrNullimpl2 != null) {
                        }
                        return Result.m1201boximpl(obj4);
                    }
                } catch (Throwable th4) {
                    th = th4;
                    str6 = str;
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String str542 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str7 = str3;
                    str8 = str2;
                } else {
                    str8 = str2;
                    Intrinsics.checkNotNullExpressionValue(str542, str8);
                    if (StringsKt.contains$default((CharSequence) str542, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str7 = str3;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str7);
                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement6 != null && (className = stackTraceElement6.getClassName()) != null) {
                            String substringAfterLast$default7 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            str9 = substringAfterLast$default7;
                        }
                        String canonicalName6 = (coroutineScope4 == null || (cls = coroutineScope4.getClass()) == null) ? null : cls.getCanonicalName();
                        str9 = canonicalName6 == null ? "N/A" : canonicalName6;
                        objectRef5.element = str9;
                        Matcher matcher11 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                        if (matcher11.find()) {
                            ?? replaceAll2 = matcher11.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef5.element = replaceAll2;
                        }
                        if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str10 = (String) objectRef5.element;
                        } else {
                            str10 = ((String) objectRef5.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str10, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(3, str10, "startBarcodeFlow() called ");
                    } else {
                        str7 = str3;
                    }
                }
                hKMainActivity$startBarcodeFlow$2 = this;
            }
            hKMainActivity = hKMainActivity$startBarcodeFlow$2.this$0;
            WorkflowUIState.BarcodeCapture barcodeCapture22 = hKMainActivity$startBarcodeFlow$2.$barcodeFlowUIState;
            try {
                Result.Companion companion62 = Result.INSTANCE;
            } catch (Throwable th5) {
                th = th5;
                str11 = str8;
                str12 = "getInitialApplication";
                str13 = "android.app.AppGlobals";
                str14 = str7;
                str15 = str6;
                coroutineScope = coroutineScope4;
            }
            if (!hKMainActivity.getMainVM().getHsStateHandler().getIsActivityRecreated()) {
                try {
                    hKMainActivity.getMainVM().getHsStateHandler().setActivityRecreated(false);
                    hsStateHandler = hKMainActivity.getMainVM().getHsStateHandler();
                    tag = barcodeCapture22.getTag();
                    level = HyperLogger.Level.DEBUG;
                    companion = HyperLogger.INSTANCE.getInstance();
                    coroutineScope2 = coroutineScope4;
                } catch (Throwable th6) {
                    th = th6;
                    coroutineScope2 = coroutineScope4;
                    str11 = str8;
                    str12 = "getInitialApplication";
                    str13 = "android.app.AppGlobals";
                    str15 = str6;
                }
                try {
                    sb = new StringBuilder();
                    hKMainActivity2 = hKMainActivity;
                    objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str7);
                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                } catch (Throwable th7) {
                    th = th7;
                    str15 = str6;
                    str11 = str8;
                    str12 = "getInitialApplication";
                    str13 = "android.app.AppGlobals";
                    str14 = str7;
                    hKMainActivity$startBarcodeFlow$2 = this;
                    th = th;
                    Result.Companion companion72 = Result.INSTANCE;
                    m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    HKMainActivity hKMainActivity52 = hKMainActivity$startBarcodeFlow$2.this$0;
                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                    if (m1205exceptionOrNullimpl2 != null) {
                    }
                    return Result.m1201boximpl(obj4);
                }
                if (stackTraceElement != null) {
                    try {
                        className2 = stackTraceElement.getClassName();
                    } catch (Throwable th8) {
                        hKMainActivity$startBarcodeFlow$2 = this;
                        th = th8;
                        str15 = str6;
                        str11 = str8;
                        str12 = "getInitialApplication";
                        str13 = "android.app.AppGlobals";
                        str14 = str7;
                    }
                    if (className2 != null) {
                        str16 = str6;
                        str11 = str8;
                        str17 = str7;
                        try {
                            substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        } catch (Throwable th9) {
                            hKMainActivity$startBarcodeFlow$2 = this;
                            th = th9;
                            str12 = "getInitialApplication";
                            str13 = "android.app.AppGlobals";
                            str14 = str17;
                            str15 = str16;
                            Result.Companion companion722 = Result.INSTANCE;
                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            HKMainActivity hKMainActivity522 = hKMainActivity$startBarcodeFlow$2.this$0;
                            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                            if (m1205exceptionOrNullimpl2 != null) {
                            }
                            return Result.m1201boximpl(obj4);
                        }
                        if (substringAfterLast$default != null) {
                            str18 = substringAfterLast$default;
                            objectRef.element = str18;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                            if (matcher.find()) {
                                ?? replaceAll3 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                                objectRef.element = replaceAll3;
                            }
                            if (((String) objectRef.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str19 = ((String) objectRef.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str19, "this as java.lang.String…ing(startIndex, endIndex)");
                                sb.append(str19);
                                sb.append(" - ");
                                sb.append("retrieveState() called");
                                sb.append(' ');
                                sb.append("");
                                companion.log(level, sb.toString());
                                if (CoreExtsKt.isRelease()) {
                                    str21 = str17;
                                    str22 = str11;
                                    str20 = str16;
                                } else {
                                    try {
                                        Result.Companion companion8 = Result.INSTANCE;
                                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                        str20 = str16;
                                        try {
                                            Intrinsics.checkNotNull(invoke2, str20);
                                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                        } catch (Throwable th10) {
                                            th = th10;
                                            Throwable th11 = th;
                                            try {
                                                Result.Companion companion9 = Result.INSTANCE;
                                                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th11));
                                                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                                }
                                                String str55 = (String) m1202constructorimpl2;
                                                if (CoreExtsKt.isDebug()) {
                                                }
                                                file = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                                                if (file.exists()) {
                                                }
                                                HyperLogger.Level level4 = HyperLogger.Level.DEBUG;
                                                HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
                                                StringBuilder sb4 = new StringBuilder();
                                                objectRef2 = new Ref.ObjectRef();
                                                str11 = str22;
                                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str21);
                                                stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                                if (stackTraceElement2 != null) {
                                                }
                                                str16 = str20;
                                                str17 = str21;
                                                str24 = "getInitialApplication";
                                                if (hsStateHandler != null) {
                                                }
                                                if (r2 == null) {
                                                }
                                                objectRef2.element = str25;
                                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                                if (matcher2.find()) {
                                                }
                                                if (((String) objectRef2.element).length() > 23) {
                                                }
                                                str26 = (String) objectRef2.element;
                                                sb4.append(str26);
                                                sb4.append(" - ");
                                                str27 = "retrieveState: state targetFile exists : " + file.exists();
                                                if (str27 == null) {
                                                }
                                                sb4.append(str27);
                                                sb4.append(' ');
                                                sb4.append("");
                                                companion10.log(level4, sb4.toString());
                                                if (CoreExtsKt.isRelease()) {
                                                }
                                                str28 = str17;
                                                if (file.exists()) {
                                                }
                                                throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                            } catch (Throwable th12) {
                                                th = th12;
                                                str15 = str20;
                                                str12 = "getInitialApplication";
                                                str13 = "android.app.AppGlobals";
                                                str14 = str17;
                                                hKMainActivity$startBarcodeFlow$2 = this;
                                                th = th;
                                                Result.Companion companion7222 = Result.INSTANCE;
                                                m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                HKMainActivity hKMainActivity5222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                if (m1205exceptionOrNullimpl2 != null) {
                                                }
                                                return Result.m1201boximpl(obj4);
                                            }
                                        }
                                    } catch (Throwable th13) {
                                        th = th13;
                                        str20 = str16;
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                        m1202constructorimpl2 = "";
                                    }
                                    String str552 = (String) m1202constructorimpl2;
                                    if (CoreExtsKt.isDebug()) {
                                        str21 = str17;
                                        str22 = str11;
                                    } else {
                                        str22 = str11;
                                        try {
                                            Intrinsics.checkNotNullExpressionValue(str552, str22);
                                            if (StringsKt.contains$default((CharSequence) str552, charSequence, false, 2, (Object) null)) {
                                                Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                                                StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                                str21 = str17;
                                                try {
                                                    Intrinsics.checkNotNullExpressionValue(stackTrace5, str21);
                                                    StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                                    if (stackTraceElement7 == null || (className3 = stackTraceElement7.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                                        canonicalName = (hsStateHandler == null || (cls2 = hsStateHandler.getClass()) == null) ? 0 : cls2.getCanonicalName();
                                                        if (canonicalName == 0) {
                                                            canonicalName = "N/A";
                                                        }
                                                    }
                                                    objectRef6.element = canonicalName;
                                                    Matcher matcher12 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                                    if (matcher12.find()) {
                                                        ?? replaceAll4 = matcher12.replaceAll("");
                                                        Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                                        objectRef6.element = replaceAll4;
                                                    }
                                                    if (((String) objectRef6.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                        str23 = ((String) objectRef6.element).substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(str23, "this as java.lang.String…ing(startIndex, endIndex)");
                                                        Log.println(3, str23, "retrieveState() called ");
                                                    }
                                                    str23 = (String) objectRef6.element;
                                                    Log.println(3, str23, "retrieveState() called ");
                                                } catch (Throwable th14) {
                                                    th = th14;
                                                    hKMainActivity$startBarcodeFlow$2 = this;
                                                    str15 = str20;
                                                    str11 = str22;
                                                    str12 = "getInitialApplication";
                                                    str13 = "android.app.AppGlobals";
                                                    th = th;
                                                    str14 = str21;
                                                    Result.Companion companion72222 = Result.INSTANCE;
                                                    m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                    HKMainActivity hKMainActivity52222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                    if (m1205exceptionOrNullimpl2 != null) {
                                                    }
                                                    return Result.m1201boximpl(obj4);
                                                }
                                            } else {
                                                str21 = str17;
                                            }
                                        } catch (Throwable th15) {
                                            th = th15;
                                            hKMainActivity$startBarcodeFlow$2 = this;
                                            str15 = str20;
                                            str11 = str22;
                                            str12 = "getInitialApplication";
                                            str13 = "android.app.AppGlobals";
                                            str14 = str17;
                                        }
                                    }
                                }
                                file = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                                if (file.exists()) {
                                    file = hsStateHandler.getHsDataFile();
                                }
                                HyperLogger.Level level42 = HyperLogger.Level.DEBUG;
                                HyperLogger companion102 = HyperLogger.INSTANCE.getInstance();
                                StringBuilder sb42 = new StringBuilder();
                                objectRef2 = new Ref.ObjectRef();
                                str11 = str22;
                                StackTraceElement[] stackTrace42 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace42, str21);
                                stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace42);
                                if (stackTraceElement2 != null) {
                                    try {
                                        className4 = stackTraceElement2.getClassName();
                                    } catch (Throwable th16) {
                                        th = th16;
                                        hKMainActivity$startBarcodeFlow$2 = this;
                                        str15 = str20;
                                        str12 = "getInitialApplication";
                                        str13 = "android.app.AppGlobals";
                                        th = th;
                                        str14 = str21;
                                        Result.Companion companion722222 = Result.INSTANCE;
                                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        HKMainActivity hKMainActivity522222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                        if (m1205exceptionOrNullimpl2 != null) {
                                        }
                                        return Result.m1201boximpl(obj4);
                                    }
                                    if (className4 != null) {
                                        str16 = str20;
                                        str17 = str21;
                                        str24 = "getInitialApplication";
                                        try {
                                            substringAfterLast$default2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        } catch (Throwable th17) {
                                            hKMainActivity$startBarcodeFlow$2 = this;
                                            th = th17;
                                            str13 = "android.app.AppGlobals";
                                            str12 = str24;
                                            str14 = str17;
                                            str15 = str16;
                                            Result.Companion companion7222222 = Result.INSTANCE;
                                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            HKMainActivity hKMainActivity5222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                            if (m1205exceptionOrNullimpl2 != null) {
                                            }
                                            return Result.m1201boximpl(obj4);
                                        }
                                        if (substringAfterLast$default2 != null) {
                                            str25 = substringAfterLast$default2;
                                            objectRef2.element = str25;
                                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                            if (matcher2.find()) {
                                                ?? replaceAll5 = matcher2.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(replaceAll5, "replaceAll(\"\")");
                                                objectRef2.element = replaceAll5;
                                            }
                                            if (((String) objectRef2.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                str26 = ((String) objectRef2.element).substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str26, "this as java.lang.String…ing(startIndex, endIndex)");
                                                sb42.append(str26);
                                                sb42.append(" - ");
                                                str27 = "retrieveState: state targetFile exists : " + file.exists();
                                                if (str27 == null) {
                                                    str27 = "null ";
                                                }
                                                sb42.append(str27);
                                                sb42.append(' ');
                                                sb42.append("");
                                                companion102.log(level42, sb42.toString());
                                                if (CoreExtsKt.isRelease()) {
                                                    try {
                                                        Result.Companion companion11 = Result.INSTANCE;
                                                        str12 = str24;
                                                        try {
                                                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod(str12, new Class[0]).invoke(null, new Object[0]);
                                                            str15 = str16;
                                                            try {
                                                                Intrinsics.checkNotNull(invoke3, str15);
                                                                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                                            } catch (Throwable th18) {
                                                                th = th18;
                                                                Throwable th19 = th;
                                                                try {
                                                                    Result.Companion companion12 = Result.INSTANCE;
                                                                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th19));
                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                                                    }
                                                                    String str56 = (String) m1202constructorimpl3;
                                                                    if (CoreExtsKt.isDebug()) {
                                                                    }
                                                                    str16 = str15;
                                                                    str28 = str17;
                                                                    if (file.exists()) {
                                                                    }
                                                                    throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                } catch (Throwable th20) {
                                                                    th = th20;
                                                                    str13 = "android.app.AppGlobals";
                                                                    str14 = str17;
                                                                    hKMainActivity$startBarcodeFlow$2 = this;
                                                                    th = th;
                                                                    Result.Companion companion72222222 = Result.INSTANCE;
                                                                    m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                    HKMainActivity hKMainActivity52222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                    if (m1205exceptionOrNullimpl2 != null) {
                                                                    }
                                                                    return Result.m1201boximpl(obj4);
                                                                }
                                                            }
                                                        } catch (Throwable th21) {
                                                            th = th21;
                                                            str15 = str16;
                                                            Throwable th192 = th;
                                                            Result.Companion companion122 = Result.INSTANCE;
                                                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th192));
                                                            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                                            }
                                                            String str562 = (String) m1202constructorimpl3;
                                                            if (CoreExtsKt.isDebug()) {
                                                            }
                                                            str16 = str15;
                                                            str28 = str17;
                                                            if (file.exists()) {
                                                            }
                                                            throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                        }
                                                    } catch (Throwable th22) {
                                                        th = th22;
                                                        str12 = str24;
                                                    }
                                                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                                                        m1202constructorimpl3 = "";
                                                    }
                                                    String str5622 = (String) m1202constructorimpl3;
                                                    if (CoreExtsKt.isDebug()) {
                                                        try {
                                                            Intrinsics.checkNotNullExpressionValue(str5622, str11);
                                                            str11 = str11;
                                                            try {
                                                            } catch (Throwable th23) {
                                                                hKMainActivity$startBarcodeFlow$2 = this;
                                                                th = th23;
                                                                str13 = "android.app.AppGlobals";
                                                                str14 = str17;
                                                                Result.Companion companion722222222 = Result.INSTANCE;
                                                                m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                HKMainActivity hKMainActivity522222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                if (m1205exceptionOrNullimpl2 != null) {
                                                                }
                                                                return Result.m1201boximpl(obj4);
                                                            }
                                                        } catch (Throwable th24) {
                                                            hKMainActivity$startBarcodeFlow$2 = this;
                                                            th = th24;
                                                            str11 = str11;
                                                        }
                                                        if (StringsKt.contains$default((CharSequence) str5622, charSequence, false, 2, (Object) null)) {
                                                            Ref.ObjectRef objectRef7 = new Ref.ObjectRef();
                                                            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                                            str28 = str17;
                                                            try {
                                                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str28);
                                                                stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                                            } catch (Throwable th25) {
                                                                hKMainActivity$startBarcodeFlow$2 = this;
                                                                th = th25;
                                                                str14 = str28;
                                                                str13 = "android.app.AppGlobals";
                                                                Result.Companion companion7222222222 = Result.INSTANCE;
                                                                m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                HKMainActivity hKMainActivity5222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                if (m1205exceptionOrNullimpl2 != null) {
                                                                }
                                                                return Result.m1201boximpl(obj4);
                                                            }
                                                            if (stackTraceElement3 != null) {
                                                                String className10 = stackTraceElement3.getClassName();
                                                                if (className10 != null) {
                                                                    str16 = str15;
                                                                    try {
                                                                        substringAfterLast$default3 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                    } catch (Throwable th26) {
                                                                        hKMainActivity$startBarcodeFlow$2 = this;
                                                                        th = th26;
                                                                        str14 = str28;
                                                                        str13 = "android.app.AppGlobals";
                                                                        str15 = str16;
                                                                        Result.Companion companion72222222222 = Result.INSTANCE;
                                                                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                        HKMainActivity hKMainActivity52222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                        if (m1205exceptionOrNullimpl2 != null) {
                                                                        }
                                                                        return Result.m1201boximpl(obj4);
                                                                    }
                                                                    if (substringAfterLast$default3 != null) {
                                                                        str29 = substringAfterLast$default3;
                                                                        objectRef7.element = str29;
                                                                        matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef7.element);
                                                                        if (matcher3.find()) {
                                                                            ?? replaceAll6 = matcher3.replaceAll("");
                                                                            Intrinsics.checkNotNullExpressionValue(replaceAll6, "replaceAll(\"\")");
                                                                            objectRef7.element = replaceAll6;
                                                                        }
                                                                        if (((String) objectRef7.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                            str30 = ((String) objectRef7.element).substring(0, 23);
                                                                            Intrinsics.checkNotNullExpressionValue(str30, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                            StringBuilder sb5 = new StringBuilder();
                                                                            str31 = "retrieveState: state targetFile exists : " + file.exists();
                                                                            if (str31 == null) {
                                                                                str31 = "null ";
                                                                            }
                                                                            sb5.append(str31);
                                                                            sb5.append(' ');
                                                                            sb5.append("");
                                                                            Log.println(3, str30, sb5.toString());
                                                                            if (file.exists()) {
                                                                                try {
                                                                                    Result.Companion companion13 = Result.INSTANCE;
                                                                                    obj2 = hsStateHandler.getGson().fromJson(FilesKt.readText$default(file, null, 1, null), new HSStateHandler$retrieveState$3$type$1().getType());
                                                                                    try {
                                                                                        level2 = HyperLogger.Level.DEBUG;
                                                                                        companion2 = HyperLogger.INSTANCE.getInstance();
                                                                                        sb2 = new StringBuilder();
                                                                                        objectRef3 = new Ref.ObjectRef();
                                                                                        StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                                                                                        Intrinsics.checkNotNullExpressionValue(stackTrace7, str28);
                                                                                        stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                                                                                    } catch (Throwable th27) {
                                                                                        th = th27;
                                                                                        str32 = str12;
                                                                                    }
                                                                                } catch (Throwable th28) {
                                                                                    str32 = str12;
                                                                                    str15 = str16;
                                                                                    th2 = th28;
                                                                                    obj2 = null;
                                                                                }
                                                                                if (stackTraceElement4 != null) {
                                                                                    try {
                                                                                        className6 = stackTraceElement4.getClassName();
                                                                                    } catch (Throwable th29) {
                                                                                        th2 = th29;
                                                                                        str32 = str12;
                                                                                        str15 = str16;
                                                                                        try {
                                                                                            Result.Companion companion14 = Result.INSTANCE;
                                                                                            m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                            }
                                                                                            Object obj5 = obj3;
                                                                                            Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                            Triple triple = (Triple) obj5;
                                                                                            hVError = (HVError) triple.component1();
                                                                                            HVResponse hVResponse = (HVResponse) triple.component2();
                                                                                            Object obj6 = (JSONObject) triple.component3();
                                                                                            if (hVError == null) {
                                                                                            }
                                                                                        } catch (Throwable th30) {
                                                                                            hKMainActivity$startBarcodeFlow$2 = this;
                                                                                            th = th30;
                                                                                            str12 = str32;
                                                                                            str14 = str28;
                                                                                            str13 = "android.app.AppGlobals";
                                                                                            Result.Companion companion722222222222 = Result.INSTANCE;
                                                                                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                            HKMainActivity hKMainActivity522222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                            if (m1205exceptionOrNullimpl2 != null) {
                                                                                            }
                                                                                            return Result.m1201boximpl(obj4);
                                                                                        }
                                                                                    }
                                                                                    if (className6 != null) {
                                                                                        str37 = str12;
                                                                                        str38 = str28;
                                                                                        try {
                                                                                            substringAfterLast$default4 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                        } catch (Throwable th31) {
                                                                                            th2 = th31;
                                                                                            str32 = str37;
                                                                                            str28 = str38;
                                                                                            str15 = str16;
                                                                                            Result.Companion companion142 = Result.INSTANCE;
                                                                                            m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                            }
                                                                                            Object obj52 = obj3;
                                                                                            Intrinsics.checkNotNull(obj52, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                            Triple triple2 = (Triple) obj52;
                                                                                            hVError = (HVError) triple2.component1();
                                                                                            HVResponse hVResponse2 = (HVResponse) triple2.component2();
                                                                                            Object obj62 = (JSONObject) triple2.component3();
                                                                                            if (hVError == null) {
                                                                                            }
                                                                                        }
                                                                                        if (substringAfterLast$default4 != null) {
                                                                                            str39 = substringAfterLast$default4;
                                                                                            objectRef3.element = str39;
                                                                                            matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                                                                            if (matcher6.find()) {
                                                                                                ?? replaceAll7 = matcher6.replaceAll("");
                                                                                                Intrinsics.checkNotNullExpressionValue(replaceAll7, "replaceAll(\"\")");
                                                                                                objectRef3.element = replaceAll7;
                                                                                            }
                                                                                            if (((String) objectRef3.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                str40 = ((String) objectRef3.element).substring(0, 23);
                                                                                                Intrinsics.checkNotNullExpressionValue(str40, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                sb2.append(str40);
                                                                                                sb2.append(" - ");
                                                                                                str41 = "retrieveState: hvData from file: " + obj2;
                                                                                                if (str41 == null) {
                                                                                                    str41 = "null ";
                                                                                                }
                                                                                                sb2.append(str41);
                                                                                                sb2.append(' ');
                                                                                                sb2.append("");
                                                                                                companion2.log(level2, sb2.toString());
                                                                                                if (CoreExtsKt.isRelease()) {
                                                                                                    str32 = str37;
                                                                                                    str28 = str38;
                                                                                                    str15 = str16;
                                                                                                } else {
                                                                                                    try {
                                                                                                        Result.Companion companion15 = Result.INSTANCE;
                                                                                                        str32 = str37;
                                                                                                        try {
                                                                                                            Object invoke4 = Class.forName("android.app.AppGlobals").getMethod(str32, new Class[0]).invoke(null, new Object[0]);
                                                                                                            str15 = str16;
                                                                                                            try {
                                                                                                                Intrinsics.checkNotNull(invoke4, str15);
                                                                                                                m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                                                                                                            } catch (Throwable th32) {
                                                                                                                th = th32;
                                                                                                                Throwable th33 = th;
                                                                                                                try {
                                                                                                                    Result.Companion companion16 = Result.INSTANCE;
                                                                                                                    m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th33));
                                                                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                                                                                                    }
                                                                                                                    String str57 = (String) m1202constructorimpl6;
                                                                                                                    if (CoreExtsKt.isDebug()) {
                                                                                                                    }
                                                                                                                    str28 = str38;
                                                                                                                    m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                                                } catch (Throwable th34) {
                                                                                                                    th = th34;
                                                                                                                    str28 = str38;
                                                                                                                    th2 = th;
                                                                                                                    Result.Companion companion1422 = Result.INSTANCE;
                                                                                                                    m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                                                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                                                    }
                                                                                                                    Object obj522 = obj3;
                                                                                                                    Intrinsics.checkNotNull(obj522, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                                    Triple triple22 = (Triple) obj522;
                                                                                                                    hVError = (HVError) triple22.component1();
                                                                                                                    HVResponse hVResponse22 = (HVResponse) triple22.component2();
                                                                                                                    Object obj622 = (JSONObject) triple22.component3();
                                                                                                                    if (hVError == null) {
                                                                                                                    }
                                                                                                                }
                                                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                                                if (m1205exceptionOrNullimpl == null) {
                                                                                                                }
                                                                                                                Object obj5222 = obj3;
                                                                                                                Intrinsics.checkNotNull(obj5222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                                Triple triple222 = (Triple) obj5222;
                                                                                                                hVError = (HVError) triple222.component1();
                                                                                                                HVResponse hVResponse222 = (HVResponse) triple222.component2();
                                                                                                                Object obj6222 = (JSONObject) triple222.component3();
                                                                                                                if (hVError == null) {
                                                                                                                }
                                                                                                            }
                                                                                                        } catch (Throwable th35) {
                                                                                                            th = th35;
                                                                                                            str15 = str16;
                                                                                                            Throwable th332 = th;
                                                                                                            Result.Companion companion162 = Result.INSTANCE;
                                                                                                            m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th332));
                                                                                                            if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                                                                                            }
                                                                                                            String str572 = (String) m1202constructorimpl6;
                                                                                                            if (CoreExtsKt.isDebug()) {
                                                                                                            }
                                                                                                            str28 = str38;
                                                                                                            m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                                            }
                                                                                                            Object obj52222 = obj3;
                                                                                                            Intrinsics.checkNotNull(obj52222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                            Triple triple2222 = (Triple) obj52222;
                                                                                                            hVError = (HVError) triple2222.component1();
                                                                                                            HVResponse hVResponse2222 = (HVResponse) triple2222.component2();
                                                                                                            Object obj62222 = (JSONObject) triple2222.component3();
                                                                                                            if (hVError == null) {
                                                                                                            }
                                                                                                        }
                                                                                                    } catch (Throwable th36) {
                                                                                                        th = th36;
                                                                                                        str32 = str37;
                                                                                                    }
                                                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                                                                                        m1202constructorimpl6 = "";
                                                                                                    }
                                                                                                    String str5722 = (String) m1202constructorimpl6;
                                                                                                    if (CoreExtsKt.isDebug()) {
                                                                                                        String str58 = str11;
                                                                                                        try {
                                                                                                            Intrinsics.checkNotNullExpressionValue(str5722, str58);
                                                                                                            if (StringsKt.contains$default((CharSequence) str5722, charSequence, false, 2, (Object) null)) {
                                                                                                                Ref.ObjectRef objectRef8 = new Ref.ObjectRef();
                                                                                                                StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                                                                                                str28 = str38;
                                                                                                                try {
                                                                                                                    Intrinsics.checkNotNullExpressionValue(stackTrace8, str28);
                                                                                                                    StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                                                                                                    if (stackTraceElement8 != null) {
                                                                                                                        String className11 = stackTraceElement8.getClassName();
                                                                                                                        if (className11 != null) {
                                                                                                                            str11 = str58;
                                                                                                                            try {
                                                                                                                                canonicalName4 = StringsKt.substringAfterLast$default(className11, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                                                if (canonicalName4 != 0) {
                                                                                                                                    objectRef8.element = canonicalName4;
                                                                                                                                    matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef8.element);
                                                                                                                                    if (matcher7.find()) {
                                                                                                                                        ?? replaceAll8 = matcher7.replaceAll("");
                                                                                                                                        Intrinsics.checkNotNullExpressionValue(replaceAll8, "replaceAll(\"\")");
                                                                                                                                        objectRef8.element = replaceAll8;
                                                                                                                                    }
                                                                                                                                    if (((String) objectRef8.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                                        str42 = ((String) objectRef8.element).substring(0, 23);
                                                                                                                                        Intrinsics.checkNotNullExpressionValue(str42, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                                        StringBuilder sb6 = new StringBuilder();
                                                                                                                                        str43 = "retrieveState: hvData from file: " + obj2;
                                                                                                                                        if (str43 == null) {
                                                                                                                                            str43 = "null ";
                                                                                                                                        }
                                                                                                                                        sb6.append(str43);
                                                                                                                                        sb6.append(' ');
                                                                                                                                        sb6.append("");
                                                                                                                                        Log.println(3, str42, sb6.toString());
                                                                                                                                    }
                                                                                                                                    str42 = (String) objectRef8.element;
                                                                                                                                    StringBuilder sb62 = new StringBuilder();
                                                                                                                                    str43 = "retrieveState: hvData from file: " + obj2;
                                                                                                                                    if (str43 == null) {
                                                                                                                                    }
                                                                                                                                    sb62.append(str43);
                                                                                                                                    sb62.append(' ');
                                                                                                                                    sb62.append("");
                                                                                                                                    Log.println(3, str42, sb62.toString());
                                                                                                                                }
                                                                                                                                canonicalName4 = (hsStateHandler != null || (cls6 = hsStateHandler.getClass()) == null) ? 0 : cls6.getCanonicalName();
                                                                                                                                if (canonicalName4 == 0) {
                                                                                                                                    canonicalName4 = "N/A";
                                                                                                                                }
                                                                                                                                objectRef8.element = canonicalName4;
                                                                                                                                matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef8.element);
                                                                                                                                if (matcher7.find()) {
                                                                                                                                }
                                                                                                                                if (((String) objectRef8.element).length() > 23) {
                                                                                                                                    str42 = ((String) objectRef8.element).substring(0, 23);
                                                                                                                                    Intrinsics.checkNotNullExpressionValue(str42, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                                    StringBuilder sb622 = new StringBuilder();
                                                                                                                                    str43 = "retrieveState: hvData from file: " + obj2;
                                                                                                                                    if (str43 == null) {
                                                                                                                                    }
                                                                                                                                    sb622.append(str43);
                                                                                                                                    sb622.append(' ');
                                                                                                                                    sb622.append("");
                                                                                                                                    Log.println(3, str42, sb622.toString());
                                                                                                                                }
                                                                                                                                str42 = (String) objectRef8.element;
                                                                                                                                StringBuilder sb6222 = new StringBuilder();
                                                                                                                                str43 = "retrieveState: hvData from file: " + obj2;
                                                                                                                                if (str43 == null) {
                                                                                                                                }
                                                                                                                                sb6222.append(str43);
                                                                                                                                sb6222.append(' ');
                                                                                                                                sb6222.append("");
                                                                                                                                Log.println(3, str42, sb6222.toString());
                                                                                                                            } catch (Throwable th37) {
                                                                                                                                th = th37;
                                                                                                                                th2 = th;
                                                                                                                                Result.Companion companion14222 = Result.INSTANCE;
                                                                                                                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                                                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                                                                if (m1205exceptionOrNullimpl == null) {
                                                                                                                                }
                                                                                                                                Object obj522222 = obj3;
                                                                                                                                Intrinsics.checkNotNull(obj522222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                                                Triple triple22222 = (Triple) obj522222;
                                                                                                                                hVError = (HVError) triple22222.component1();
                                                                                                                                HVResponse hVResponse22222 = (HVResponse) triple22222.component2();
                                                                                                                                Object obj622222 = (JSONObject) triple22222.component3();
                                                                                                                                if (hVError == null) {
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                    str11 = str58;
                                                                                                                    if (hsStateHandler != null) {
                                                                                                                    }
                                                                                                                    if (canonicalName4 == 0) {
                                                                                                                    }
                                                                                                                    objectRef8.element = canonicalName4;
                                                                                                                    matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef8.element);
                                                                                                                    if (matcher7.find()) {
                                                                                                                    }
                                                                                                                    if (((String) objectRef8.element).length() > 23) {
                                                                                                                    }
                                                                                                                    str42 = (String) objectRef8.element;
                                                                                                                    StringBuilder sb62222 = new StringBuilder();
                                                                                                                    str43 = "retrieveState: hvData from file: " + obj2;
                                                                                                                    if (str43 == null) {
                                                                                                                    }
                                                                                                                    sb62222.append(str43);
                                                                                                                    sb62222.append(' ');
                                                                                                                    sb62222.append("");
                                                                                                                    Log.println(3, str42, sb62222.toString());
                                                                                                                } catch (Throwable th38) {
                                                                                                                    th = th38;
                                                                                                                    str11 = str58;
                                                                                                                    th2 = th;
                                                                                                                    Result.Companion companion142222 = Result.INSTANCE;
                                                                                                                    m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                                                                                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                                                    }
                                                                                                                    Object obj5222222 = obj3;
                                                                                                                    Intrinsics.checkNotNull(obj5222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                                    Triple triple222222 = (Triple) obj5222222;
                                                                                                                    hVError = (HVError) triple222222.component1();
                                                                                                                    HVResponse hVResponse222222 = (HVResponse) triple222222.component2();
                                                                                                                    Object obj6222222 = (JSONObject) triple222222.component3();
                                                                                                                    if (hVError == null) {
                                                                                                                    }
                                                                                                                }
                                                                                                            } else {
                                                                                                                str11 = str58;
                                                                                                            }
                                                                                                        } catch (Throwable th39) {
                                                                                                            th = th39;
                                                                                                            str11 = str58;
                                                                                                            str28 = str38;
                                                                                                            th2 = th;
                                                                                                            Result.Companion companion1422222 = Result.INSTANCE;
                                                                                                            m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                                            }
                                                                                                            Object obj52222222 = obj3;
                                                                                                            Intrinsics.checkNotNull(obj52222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                            Triple triple2222222 = (Triple) obj52222222;
                                                                                                            hVError = (HVError) triple2222222.component1();
                                                                                                            HVResponse hVResponse2222222 = (HVResponse) triple2222222.component2();
                                                                                                            Object obj62222222 = (JSONObject) triple2222222.component3();
                                                                                                            if (hVError == null) {
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                    str28 = str38;
                                                                                                }
                                                                                                m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                                if (m1205exceptionOrNullimpl == null) {
                                                                                                    HyperLogger.Level level5 = HyperLogger.Level.ERROR;
                                                                                                    HyperLogger companion17 = HyperLogger.INSTANCE.getInstance();
                                                                                                    StringBuilder sb7 = new StringBuilder();
                                                                                                    Ref.ObjectRef objectRef9 = new Ref.ObjectRef();
                                                                                                    obj3 = obj2;
                                                                                                    StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
                                                                                                    Intrinsics.checkNotNullExpressionValue(stackTrace9, str28);
                                                                                                    StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
                                                                                                    if (stackTraceElement9 == null || (className5 = stackTraceElement9.getClassName()) == null) {
                                                                                                        str16 = str15;
                                                                                                        str24 = str32;
                                                                                                        str17 = str28;
                                                                                                    } else {
                                                                                                        str16 = str15;
                                                                                                        str24 = str32;
                                                                                                        str17 = str28;
                                                                                                        canonicalName2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                        if (canonicalName2 != 0) {
                                                                                                            objectRef9.element = canonicalName2;
                                                                                                            matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef9.element);
                                                                                                            if (matcher4.find()) {
                                                                                                                ?? replaceAll9 = matcher4.replaceAll("");
                                                                                                                Intrinsics.checkNotNullExpressionValue(replaceAll9, "replaceAll(\"\")");
                                                                                                                objectRef9.element = replaceAll9;
                                                                                                            }
                                                                                                            if (((String) objectRef9.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                str33 = ((String) objectRef9.element).substring(0, 23);
                                                                                                                Intrinsics.checkNotNullExpressionValue(str33, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                sb7.append(str33);
                                                                                                                sb7.append(" - ");
                                                                                                                sb7.append("unable to retrieve hv_data state json from file");
                                                                                                                sb7.append(' ');
                                                                                                                localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                                                                                                if (localizedMessage == null) {
                                                                                                                    str34 = '\n' + localizedMessage;
                                                                                                                } else {
                                                                                                                    str34 = "";
                                                                                                                }
                                                                                                                sb7.append(str34);
                                                                                                                companion17.log(level5, sb7.toString());
                                                                                                                CoreExtsKt.isRelease();
                                                                                                                Result.Companion companion18 = Result.INSTANCE;
                                                                                                                str32 = str24;
                                                                                                                Object invoke5 = Class.forName("android.app.AppGlobals").getMethod(str32, new Class[0]).invoke(null, new Object[0]);
                                                                                                                str15 = str16;
                                                                                                                Intrinsics.checkNotNull(invoke5, str15);
                                                                                                                m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                                                                                                                if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                                                                                    m1202constructorimpl5 = "";
                                                                                                                }
                                                                                                                String str59 = (String) m1202constructorimpl5;
                                                                                                                if (CoreExtsKt.isDebug()) {
                                                                                                                    String str60 = str11;
                                                                                                                    try {
                                                                                                                        Intrinsics.checkNotNullExpressionValue(str59, str60);
                                                                                                                        if (StringsKt.contains$default((CharSequence) str59, charSequence, false, 2, (Object) null)) {
                                                                                                                            Ref.ObjectRef objectRef10 = new Ref.ObjectRef();
                                                                                                                            StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                                                                                                                            str28 = str17;
                                                                                                                            try {
                                                                                                                                Intrinsics.checkNotNullExpressionValue(stackTrace10, str28);
                                                                                                                                StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                                                                                                                                if (stackTraceElement10 != null) {
                                                                                                                                    String className12 = stackTraceElement10.getClassName();
                                                                                                                                    if (className12 != null) {
                                                                                                                                        str11 = str60;
                                                                                                                                        canonicalName3 = StringsKt.substringAfterLast$default(className12, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                                                        if (canonicalName3 != 0) {
                                                                                                                                            objectRef10.element = canonicalName3;
                                                                                                                                            matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef10.element);
                                                                                                                                            if (matcher5.find()) {
                                                                                                                                                ?? replaceAll10 = matcher5.replaceAll("");
                                                                                                                                                Intrinsics.checkNotNullExpressionValue(replaceAll10, "replaceAll(\"\")");
                                                                                                                                                objectRef10.element = replaceAll10;
                                                                                                                                            }
                                                                                                                                            if (((String) objectRef10.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                                                str35 = ((String) objectRef10.element).substring(0, 23);
                                                                                                                                                Intrinsics.checkNotNullExpressionValue(str35, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                                                StringBuilder sb8 = new StringBuilder();
                                                                                                                                                sb8.append("unable to retrieve hv_data state json from file");
                                                                                                                                                sb8.append(' ');
                                                                                                                                                localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                                                                                                                                if (localizedMessage2 != null) {
                                                                                                                                                    str36 = '\n' + localizedMessage2;
                                                                                                                                                } else {
                                                                                                                                                    str36 = "";
                                                                                                                                                }
                                                                                                                                                sb8.append(str36);
                                                                                                                                                Log.println(6, str35, sb8.toString());
                                                                                                                                            }
                                                                                                                                            str35 = (String) objectRef10.element;
                                                                                                                                            StringBuilder sb82 = new StringBuilder();
                                                                                                                                            sb82.append("unable to retrieve hv_data state json from file");
                                                                                                                                            sb82.append(' ');
                                                                                                                                            if (m1205exceptionOrNullimpl != null) {
                                                                                                                                            }
                                                                                                                                            if (localizedMessage2 != null) {
                                                                                                                                            }
                                                                                                                                            sb82.append(str36);
                                                                                                                                            Log.println(6, str35, sb82.toString());
                                                                                                                                        }
                                                                                                                                        canonicalName3 = (hsStateHandler != null || (cls5 = hsStateHandler.getClass()) == null) ? 0 : cls5.getCanonicalName();
                                                                                                                                        if (canonicalName3 == 0) {
                                                                                                                                            canonicalName3 = "N/A";
                                                                                                                                        }
                                                                                                                                        objectRef10.element = canonicalName3;
                                                                                                                                        matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef10.element);
                                                                                                                                        if (matcher5.find()) {
                                                                                                                                        }
                                                                                                                                        if (((String) objectRef10.element).length() > 23) {
                                                                                                                                            str35 = ((String) objectRef10.element).substring(0, 23);
                                                                                                                                            Intrinsics.checkNotNullExpressionValue(str35, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                                            StringBuilder sb822 = new StringBuilder();
                                                                                                                                            sb822.append("unable to retrieve hv_data state json from file");
                                                                                                                                            sb822.append(' ');
                                                                                                                                            if (m1205exceptionOrNullimpl != null) {
                                                                                                                                            }
                                                                                                                                            if (localizedMessage2 != null) {
                                                                                                                                            }
                                                                                                                                            sb822.append(str36);
                                                                                                                                            Log.println(6, str35, sb822.toString());
                                                                                                                                        }
                                                                                                                                        str35 = (String) objectRef10.element;
                                                                                                                                        StringBuilder sb8222 = new StringBuilder();
                                                                                                                                        sb8222.append("unable to retrieve hv_data state json from file");
                                                                                                                                        sb8222.append(' ');
                                                                                                                                        if (m1205exceptionOrNullimpl != null) {
                                                                                                                                        }
                                                                                                                                        if (localizedMessage2 != null) {
                                                                                                                                        }
                                                                                                                                        sb8222.append(str36);
                                                                                                                                        Log.println(6, str35, sb8222.toString());
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                str11 = str60;
                                                                                                                                if (hsStateHandler != null) {
                                                                                                                                }
                                                                                                                                if (canonicalName3 == 0) {
                                                                                                                                }
                                                                                                                                objectRef10.element = canonicalName3;
                                                                                                                                matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef10.element);
                                                                                                                                if (matcher5.find()) {
                                                                                                                                }
                                                                                                                                if (((String) objectRef10.element).length() > 23) {
                                                                                                                                }
                                                                                                                                str35 = (String) objectRef10.element;
                                                                                                                                StringBuilder sb82222 = new StringBuilder();
                                                                                                                                sb82222.append("unable to retrieve hv_data state json from file");
                                                                                                                                sb82222.append(' ');
                                                                                                                                if (m1205exceptionOrNullimpl != null) {
                                                                                                                                }
                                                                                                                                if (localizedMessage2 != null) {
                                                                                                                                }
                                                                                                                                sb82222.append(str36);
                                                                                                                                Log.println(6, str35, sb82222.toString());
                                                                                                                            } catch (Throwable th40) {
                                                                                                                                hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                                                th = th40;
                                                                                                                                str11 = str60;
                                                                                                                                str14 = str28;
                                                                                                                                str13 = "android.app.AppGlobals";
                                                                                                                                str12 = str32;
                                                                                                                                Result.Companion companion7222222222222 = Result.INSTANCE;
                                                                                                                                m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                                                HKMainActivity hKMainActivity5222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                                                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                                                                if (m1205exceptionOrNullimpl2 != null) {
                                                                                                                                }
                                                                                                                                return Result.m1201boximpl(obj4);
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            str11 = str60;
                                                                                                                        }
                                                                                                                    } catch (Throwable th41) {
                                                                                                                        hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                                        th = th41;
                                                                                                                        str11 = str60;
                                                                                                                        str13 = "android.app.AppGlobals";
                                                                                                                        str14 = str17;
                                                                                                                    }
                                                                                                                }
                                                                                                                str28 = str17;
                                                                                                            }
                                                                                                            str33 = (String) objectRef9.element;
                                                                                                            sb7.append(str33);
                                                                                                            sb7.append(" - ");
                                                                                                            sb7.append("unable to retrieve hv_data state json from file");
                                                                                                            sb7.append(' ');
                                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                                            }
                                                                                                            if (localizedMessage == null) {
                                                                                                            }
                                                                                                            sb7.append(str34);
                                                                                                            companion17.log(level5, sb7.toString());
                                                                                                            CoreExtsKt.isRelease();
                                                                                                            Result.Companion companion182 = Result.INSTANCE;
                                                                                                            str32 = str24;
                                                                                                            Object invoke52 = Class.forName("android.app.AppGlobals").getMethod(str32, new Class[0]).invoke(null, new Object[0]);
                                                                                                            str15 = str16;
                                                                                                            Intrinsics.checkNotNull(invoke52, str15);
                                                                                                            m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke52).getPackageName());
                                                                                                            if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                                                                            }
                                                                                                            String str592 = (String) m1202constructorimpl5;
                                                                                                            if (CoreExtsKt.isDebug()) {
                                                                                                            }
                                                                                                            str28 = str17;
                                                                                                        }
                                                                                                    }
                                                                                                    canonicalName2 = (hsStateHandler == null || (cls4 = hsStateHandler.getClass()) == null) ? 0 : cls4.getCanonicalName();
                                                                                                    if (canonicalName2 == 0) {
                                                                                                        canonicalName2 = "N/A";
                                                                                                    }
                                                                                                    objectRef9.element = canonicalName2;
                                                                                                    matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef9.element);
                                                                                                    if (matcher4.find()) {
                                                                                                    }
                                                                                                    if (((String) objectRef9.element).length() > 23) {
                                                                                                        str33 = ((String) objectRef9.element).substring(0, 23);
                                                                                                        Intrinsics.checkNotNullExpressionValue(str33, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                        sb7.append(str33);
                                                                                                        sb7.append(" - ");
                                                                                                        sb7.append("unable to retrieve hv_data state json from file");
                                                                                                        sb7.append(' ');
                                                                                                        if (m1205exceptionOrNullimpl == null) {
                                                                                                        }
                                                                                                        if (localizedMessage == null) {
                                                                                                        }
                                                                                                        sb7.append(str34);
                                                                                                        companion17.log(level5, sb7.toString());
                                                                                                        CoreExtsKt.isRelease();
                                                                                                        Result.Companion companion1822 = Result.INSTANCE;
                                                                                                        str32 = str24;
                                                                                                        Object invoke522 = Class.forName("android.app.AppGlobals").getMethod(str32, new Class[0]).invoke(null, new Object[0]);
                                                                                                        str15 = str16;
                                                                                                        Intrinsics.checkNotNull(invoke522, str15);
                                                                                                        m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke522).getPackageName());
                                                                                                        if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                                                                        }
                                                                                                        String str5922 = (String) m1202constructorimpl5;
                                                                                                        if (CoreExtsKt.isDebug()) {
                                                                                                        }
                                                                                                        str28 = str17;
                                                                                                    }
                                                                                                    str33 = (String) objectRef9.element;
                                                                                                    sb7.append(str33);
                                                                                                    sb7.append(" - ");
                                                                                                    sb7.append("unable to retrieve hv_data state json from file");
                                                                                                    sb7.append(' ');
                                                                                                    if (m1205exceptionOrNullimpl == null) {
                                                                                                    }
                                                                                                    if (localizedMessage == null) {
                                                                                                    }
                                                                                                    sb7.append(str34);
                                                                                                    companion17.log(level5, sb7.toString());
                                                                                                    CoreExtsKt.isRelease();
                                                                                                    Result.Companion companion18222 = Result.INSTANCE;
                                                                                                    str32 = str24;
                                                                                                    Object invoke5222 = Class.forName("android.app.AppGlobals").getMethod(str32, new Class[0]).invoke(null, new Object[0]);
                                                                                                    str15 = str16;
                                                                                                    Intrinsics.checkNotNull(invoke5222, str15);
                                                                                                    m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke5222).getPackageName());
                                                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                                                                                    }
                                                                                                    String str59222 = (String) m1202constructorimpl5;
                                                                                                    if (CoreExtsKt.isDebug()) {
                                                                                                    }
                                                                                                    str28 = str17;
                                                                                                } else {
                                                                                                    obj3 = obj2;
                                                                                                }
                                                                                                Object obj522222222 = obj3;
                                                                                                Intrinsics.checkNotNull(obj522222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                                Triple triple22222222 = (Triple) obj522222222;
                                                                                                hVError = (HVError) triple22222222.component1();
                                                                                                HVResponse hVResponse22222222 = (HVResponse) triple22222222.component2();
                                                                                                Object obj622222222 = (JSONObject) triple22222222.component3();
                                                                                                if (hVError == null) {
                                                                                                    throw new HSBridgeException(hVError, hVResponse22222222);
                                                                                                }
                                                                                                if (hVResponse22222222 == null && obj622222222 == null) {
                                                                                                    str12 = str32;
                                                                                                    str14 = str28;
                                                                                                    str13 = "android.app.AppGlobals";
                                                                                                }
                                                                                                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
                                                                                                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(HyperKycData.FaceData.class))) {
                                                                                                    HyperKycData.FaceData.Companion companion19 = HyperKycData.FaceData.INSTANCE;
                                                                                                    Gson gson = hsStateHandler.getGson();
                                                                                                    Intrinsics.checkNotNull(hVResponse22222222);
                                                                                                    obj622222222 = companion19.from$hyperkyc_release(gson, hVResponse22222222);
                                                                                                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(HyperKycData.DocData.class))) {
                                                                                                    HyperKycData.DocData.Companion companion20 = HyperKycData.DocData.INSTANCE;
                                                                                                    Gson gson2 = hsStateHandler.getGson();
                                                                                                    Intrinsics.checkNotNull(hVResponse22222222);
                                                                                                    obj622222222 = companion20.from$hyperkyc_release(gson2, "", hVResponse22222222);
                                                                                                } else if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                                                                                                    throw new NotImplementedError("An operation is not implemented: " + ("Not supported - " + JSONObject.class.getCanonicalName()));
                                                                                                }
                                                                                                if (obj622222222 == null) {
                                                                                                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                                                                                                }
                                                                                                jSONObject = (JSONObject) obj622222222;
                                                                                                str12 = str32;
                                                                                                str14 = str28;
                                                                                                str13 = "android.app.AppGlobals";
                                                                                                barcodeCapture = barcodeCapture22;
                                                                                                hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                MainVM.updateBarcodeData$hyperkyc_release$default(hKMainActivity2.getMainVM(), barcodeCapture.getTag(), jSONObject.getString("qr"), jSONObject.getString("status"), false, 8, null);
                                                                                                hKMainActivity2.flowForwardOrFinish();
                                                                                                m1202constructorimpl8 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                                HKMainActivity hKMainActivity52222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                                if (m1205exceptionOrNullimpl2 != null) {
                                                                                                    HyperLogger.Level level6 = HyperLogger.Level.ERROR;
                                                                                                    HyperLogger companion21 = HyperLogger.INSTANCE.getInstance();
                                                                                                    StringBuilder sb9 = new StringBuilder();
                                                                                                    Ref.ObjectRef objectRef11 = new Ref.ObjectRef();
                                                                                                    StackTraceElement[] stackTrace11 = new Throwable().getStackTrace();
                                                                                                    Intrinsics.checkNotNullExpressionValue(stackTrace11, str14);
                                                                                                    StackTraceElement stackTraceElement11 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace11);
                                                                                                    if (stackTraceElement11 == null || (className9 = stackTraceElement11.getClassName()) == null) {
                                                                                                        obj4 = m1202constructorimpl8;
                                                                                                        hKMainActivity4 = hKMainActivity52222222222222;
                                                                                                        str49 = str14;
                                                                                                    } else {
                                                                                                        obj4 = m1202constructorimpl8;
                                                                                                        hKMainActivity4 = hKMainActivity52222222222222;
                                                                                                        str49 = str14;
                                                                                                        r1 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                    }
                                                                                                    r1 = (coroutineScope2 == null || (cls14 = coroutineScope2.getClass()) == null) ? 0 : cls14.getCanonicalName();
                                                                                                    if (r1 == 0) {
                                                                                                        r1 = "N/A";
                                                                                                    }
                                                                                                    objectRef11.element = r1;
                                                                                                    Matcher matcher13 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef11.element);
                                                                                                    if (matcher13.find()) {
                                                                                                        ?? replaceAll11 = matcher13.replaceAll("");
                                                                                                        Intrinsics.checkNotNullExpressionValue(replaceAll11, "replaceAll(\"\")");
                                                                                                        objectRef11.element = replaceAll11;
                                                                                                    }
                                                                                                    if (((String) objectRef11.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                                                                        str50 = (String) objectRef11.element;
                                                                                                    } else {
                                                                                                        str50 = ((String) objectRef11.element).substring(0, 23);
                                                                                                        Intrinsics.checkNotNullExpressionValue(str50, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                    }
                                                                                                    sb9.append(str50);
                                                                                                    sb9.append(" - ");
                                                                                                    String str61 = "startBarcodeFlow() onFailure called with: t = [" + m1205exceptionOrNullimpl2 + ']';
                                                                                                    if (str61 == null) {
                                                                                                        str61 = "null ";
                                                                                                    }
                                                                                                    sb9.append(str61);
                                                                                                    sb9.append(' ');
                                                                                                    sb9.append("");
                                                                                                    companion21.log(level6, sb9.toString());
                                                                                                    CoreExtsKt.isRelease();
                                                                                                    try {
                                                                                                        Result.Companion companion22 = Result.INSTANCE;
                                                                                                        Object invoke6 = Class.forName(str13).getMethod(str12, new Class[0]).invoke(null, new Object[0]);
                                                                                                        Intrinsics.checkNotNull(invoke6, str15);
                                                                                                        m1202constructorimpl9 = Result.m1202constructorimpl(((Application) invoke6).getPackageName());
                                                                                                    } catch (Throwable th42) {
                                                                                                        Result.Companion companion23 = Result.INSTANCE;
                                                                                                        m1202constructorimpl9 = Result.m1202constructorimpl(ResultKt.createFailure(th42));
                                                                                                    }
                                                                                                    if (Result.m1208isFailureimpl(m1202constructorimpl9)) {
                                                                                                        m1202constructorimpl9 = "";
                                                                                                    }
                                                                                                    String str62 = (String) m1202constructorimpl9;
                                                                                                    if (CoreExtsKt.isDebug()) {
                                                                                                        Intrinsics.checkNotNullExpressionValue(str62, str11);
                                                                                                        if (StringsKt.contains$default((CharSequence) str62, charSequence, false, 2, (Object) null)) {
                                                                                                            Ref.ObjectRef objectRef12 = new Ref.ObjectRef();
                                                                                                            StackTraceElement[] stackTrace12 = new Throwable().getStackTrace();
                                                                                                            Intrinsics.checkNotNullExpressionValue(stackTrace12, str49);
                                                                                                            StackTraceElement stackTraceElement12 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace12);
                                                                                                            if (stackTraceElement12 == null || (className8 = stackTraceElement12.getClassName()) == null) {
                                                                                                                str51 = null;
                                                                                                            } else {
                                                                                                                str51 = null;
                                                                                                                r2 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                            }
                                                                                                            r2 = (coroutineScope2 == null || (cls13 = coroutineScope2.getClass()) == null) ? str51 : cls13.getCanonicalName();
                                                                                                            if (r2 == 0) {
                                                                                                                r2 = "N/A";
                                                                                                            }
                                                                                                            objectRef12.element = r2;
                                                                                                            Matcher matcher14 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef12.element);
                                                                                                            if (matcher14.find()) {
                                                                                                                ?? replaceAll12 = matcher14.replaceAll("");
                                                                                                                Intrinsics.checkNotNullExpressionValue(replaceAll12, "replaceAll(\"\")");
                                                                                                                objectRef12.element = replaceAll12;
                                                                                                            }
                                                                                                            if (((String) objectRef12.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                                                                                str53 = (String) objectRef12.element;
                                                                                                            } else {
                                                                                                                str53 = ((String) objectRef12.element).substring(0, 23);
                                                                                                                Intrinsics.checkNotNullExpressionValue(str53, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                            }
                                                                                                            StringBuilder sb10 = new StringBuilder();
                                                                                                            String str63 = "startBarcodeFlow() onFailure called with: t = [" + m1205exceptionOrNullimpl2 + ']';
                                                                                                            sb10.append(str63 == null ? "null " : str63);
                                                                                                            sb10.append(' ');
                                                                                                            sb10.append("");
                                                                                                            Log.println(6, str53, sb10.toString());
                                                                                                            int i2 = 104;
                                                                                                            if (m1205exceptionOrNullimpl2 instanceof HSBridgeException) {
                                                                                                                str52 = str51;
                                                                                                            } else {
                                                                                                                HSBridgeException hSBridgeException = (HSBridgeException) m1205exceptionOrNullimpl2;
                                                                                                                HVError hvError = hSBridgeException.getHvError();
                                                                                                                if (hvError != null) {
                                                                                                                    str51 = hvError.getErrorMessage();
                                                                                                                }
                                                                                                                str52 = str51;
                                                                                                                HVError hvError2 = hSBridgeException.getHvError();
                                                                                                                if (hvError2 != null) {
                                                                                                                    i2 = hvError2.getErrorCode();
                                                                                                                    Unit unit = Unit.INSTANCE;
                                                                                                                }
                                                                                                            }
                                                                                                            hKMainActivity4.processHVBridgeError("startBarcodeFlow", m1205exceptionOrNullimpl2, Boxing.boxInt(i2), str52);
                                                                                                        }
                                                                                                    }
                                                                                                    str51 = null;
                                                                                                    int i22 = 104;
                                                                                                    if (m1205exceptionOrNullimpl2 instanceof HSBridgeException) {
                                                                                                    }
                                                                                                    hKMainActivity4.processHVBridgeError("startBarcodeFlow", m1205exceptionOrNullimpl2, Boxing.boxInt(i22), str52);
                                                                                                } else {
                                                                                                    obj4 = m1202constructorimpl8;
                                                                                                }
                                                                                                return Result.m1201boximpl(obj4);
                                                                                            }
                                                                                            str40 = (String) objectRef3.element;
                                                                                            sb2.append(str40);
                                                                                            sb2.append(" - ");
                                                                                            str41 = "retrieveState: hvData from file: " + obj2;
                                                                                            if (str41 == null) {
                                                                                            }
                                                                                            sb2.append(str41);
                                                                                            sb2.append(' ');
                                                                                            sb2.append("");
                                                                                            companion2.log(level2, sb2.toString());
                                                                                            if (CoreExtsKt.isRelease()) {
                                                                                            }
                                                                                            m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                            }
                                                                                            Object obj5222222222 = obj3;
                                                                                            Intrinsics.checkNotNull(obj5222222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                            Triple triple222222222 = (Triple) obj5222222222;
                                                                                            hVError = (HVError) triple222222222.component1();
                                                                                            HVResponse hVResponse222222222 = (HVResponse) triple222222222.component2();
                                                                                            Object obj6222222222 = (JSONObject) triple222222222.component3();
                                                                                            if (hVError == null) {
                                                                                            }
                                                                                        }
                                                                                        String canonicalName7 = (hsStateHandler != null || (cls7 = hsStateHandler.getClass()) == null) ? null : cls7.getCanonicalName();
                                                                                        str39 = canonicalName7 != null ? "N/A" : canonicalName7;
                                                                                        objectRef3.element = str39;
                                                                                        matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                                                                        if (matcher6.find()) {
                                                                                        }
                                                                                        if (((String) objectRef3.element).length() > 23) {
                                                                                            str40 = ((String) objectRef3.element).substring(0, 23);
                                                                                            Intrinsics.checkNotNullExpressionValue(str40, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                            sb2.append(str40);
                                                                                            sb2.append(" - ");
                                                                                            str41 = "retrieveState: hvData from file: " + obj2;
                                                                                            if (str41 == null) {
                                                                                            }
                                                                                            sb2.append(str41);
                                                                                            sb2.append(' ');
                                                                                            sb2.append("");
                                                                                            companion2.log(level2, sb2.toString());
                                                                                            if (CoreExtsKt.isRelease()) {
                                                                                            }
                                                                                            m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                            if (m1205exceptionOrNullimpl == null) {
                                                                                            }
                                                                                            Object obj52222222222 = obj3;
                                                                                            Intrinsics.checkNotNull(obj52222222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                            Triple triple2222222222 = (Triple) obj52222222222;
                                                                                            hVError = (HVError) triple2222222222.component1();
                                                                                            HVResponse hVResponse2222222222 = (HVResponse) triple2222222222.component2();
                                                                                            Object obj62222222222 = (JSONObject) triple2222222222.component3();
                                                                                            if (hVError == null) {
                                                                                            }
                                                                                        }
                                                                                        str40 = (String) objectRef3.element;
                                                                                        sb2.append(str40);
                                                                                        sb2.append(" - ");
                                                                                        str41 = "retrieveState: hvData from file: " + obj2;
                                                                                        if (str41 == null) {
                                                                                        }
                                                                                        sb2.append(str41);
                                                                                        sb2.append(' ');
                                                                                        sb2.append("");
                                                                                        companion2.log(level2, sb2.toString());
                                                                                        if (CoreExtsKt.isRelease()) {
                                                                                        }
                                                                                        m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                        if (m1205exceptionOrNullimpl == null) {
                                                                                        }
                                                                                        Object obj522222222222 = obj3;
                                                                                        Intrinsics.checkNotNull(obj522222222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                        Triple triple22222222222 = (Triple) obj522222222222;
                                                                                        hVError = (HVError) triple22222222222.component1();
                                                                                        HVResponse hVResponse22222222222 = (HVResponse) triple22222222222.component2();
                                                                                        Object obj622222222222 = (JSONObject) triple22222222222.component3();
                                                                                        if (hVError == null) {
                                                                                        }
                                                                                    }
                                                                                }
                                                                                str37 = str12;
                                                                                str38 = str28;
                                                                                if (hsStateHandler != null) {
                                                                                }
                                                                                if (canonicalName7 != null) {
                                                                                }
                                                                                objectRef3.element = str39;
                                                                                matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                                                                if (matcher6.find()) {
                                                                                }
                                                                                if (((String) objectRef3.element).length() > 23) {
                                                                                }
                                                                                str40 = (String) objectRef3.element;
                                                                                sb2.append(str40);
                                                                                sb2.append(" - ");
                                                                                str41 = "retrieveState: hvData from file: " + obj2;
                                                                                if (str41 == null) {
                                                                                }
                                                                                sb2.append(str41);
                                                                                sb2.append(' ');
                                                                                sb2.append("");
                                                                                companion2.log(level2, sb2.toString());
                                                                                if (CoreExtsKt.isRelease()) {
                                                                                }
                                                                                m1202constructorimpl4 = Result.m1202constructorimpl(Unit.INSTANCE);
                                                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl4);
                                                                                if (m1205exceptionOrNullimpl == null) {
                                                                                }
                                                                                Object obj5222222222222 = obj3;
                                                                                Intrinsics.checkNotNull(obj5222222222222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                                                                                Triple triple222222222222 = (Triple) obj5222222222222;
                                                                                hVError = (HVError) triple222222222222.component1();
                                                                                HVResponse hVResponse222222222222 = (HVResponse) triple222222222222.component2();
                                                                                Object obj6222222222222 = (JSONObject) triple222222222222.component3();
                                                                                if (hVError == null) {
                                                                                }
                                                                            } else {
                                                                                String str64 = str12;
                                                                                str15 = str16;
                                                                                try {
                                                                                    HyperLogger.Level level7 = HyperLogger.Level.DEBUG;
                                                                                    HyperLogger companion24 = HyperLogger.INSTANCE.getInstance();
                                                                                    StringBuilder sb11 = new StringBuilder();
                                                                                    Ref.ObjectRef objectRef13 = new Ref.ObjectRef();
                                                                                    str16 = str15;
                                                                                    try {
                                                                                        StackTraceElement[] stackTrace13 = new Throwable().getStackTrace();
                                                                                        Intrinsics.checkNotNullExpressionValue(stackTrace13, str28);
                                                                                        StackTraceElement stackTraceElement13 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace13);
                                                                                        try {
                                                                                            if (stackTraceElement13 != null) {
                                                                                                try {
                                                                                                    String className13 = stackTraceElement13.getClassName();
                                                                                                    if (className13 != null) {
                                                                                                        str24 = str64;
                                                                                                        str17 = str28;
                                                                                                        str13 = "android.app.AppGlobals";
                                                                                                        try {
                                                                                                            substringAfterLast$default5 = StringsKt.substringAfterLast$default(className13, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                            if (substringAfterLast$default5 != 0) {
                                                                                                                objectRef13.element = substringAfterLast$default5;
                                                                                                                matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef13.element);
                                                                                                                if (matcher8.find()) {
                                                                                                                    ?? replaceAll13 = matcher8.replaceAll("");
                                                                                                                    Intrinsics.checkNotNullExpressionValue(replaceAll13, "replaceAll(\"\")");
                                                                                                                    objectRef13.element = replaceAll13;
                                                                                                                }
                                                                                                                if (((String) objectRef13.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                    str44 = ((String) objectRef13.element).substring(0, 23);
                                                                                                                    Intrinsics.checkNotNullExpressionValue(str44, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                    sb11.append(str44);
                                                                                                                    sb11.append(" - ");
                                                                                                                    str45 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                                    if (str45 == null) {
                                                                                                                        str45 = "null ";
                                                                                                                    }
                                                                                                                    sb11.append(str45);
                                                                                                                    sb11.append(' ');
                                                                                                                    sb11.append("");
                                                                                                                    companion24.log(level7, sb11.toString());
                                                                                                                    if (CoreExtsKt.isRelease()) {
                                                                                                                        str12 = str24;
                                                                                                                        str14 = str17;
                                                                                                                        str15 = str16;
                                                                                                                    } else {
                                                                                                                        try {
                                                                                                                            Result.Companion companion25 = Result.INSTANCE;
                                                                                                                            str12 = str24;
                                                                                                                            try {
                                                                                                                                Object invoke7 = Class.forName(str13).getMethod(str12, new Class[0]).invoke(null, new Object[0]);
                                                                                                                                str15 = str16;
                                                                                                                                try {
                                                                                                                                    Intrinsics.checkNotNull(invoke7, str15);
                                                                                                                                    m1202constructorimpl7 = Result.m1202constructorimpl(((Application) invoke7).getPackageName());
                                                                                                                                } catch (Throwable th43) {
                                                                                                                                    th = th43;
                                                                                                                                    Throwable th44 = th;
                                                                                                                                    try {
                                                                                                                                        Result.Companion companion26 = Result.INSTANCE;
                                                                                                                                        m1202constructorimpl7 = Result.m1202constructorimpl(ResultKt.createFailure(th44));
                                                                                                                                        if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                                                                                                                                        }
                                                                                                                                        String str65 = (String) m1202constructorimpl7;
                                                                                                                                        if (CoreExtsKt.isDebug()) {
                                                                                                                                        }
                                                                                                                                        str14 = str17;
                                                                                                                                        throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                                                                                    } catch (Throwable th45) {
                                                                                                                                        th = th45;
                                                                                                                                        str14 = str17;
                                                                                                                                        hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                                                        th = th;
                                                                                                                                        Result.Companion companion72222222222222 = Result.INSTANCE;
                                                                                                                                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                                                        HKMainActivity hKMainActivity522222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                                                                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                                                                        if (m1205exceptionOrNullimpl2 != null) {
                                                                                                                                        }
                                                                                                                                        return Result.m1201boximpl(obj4);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            } catch (Throwable th46) {
                                                                                                                                th = th46;
                                                                                                                                str15 = str16;
                                                                                                                                Throwable th442 = th;
                                                                                                                                Result.Companion companion262 = Result.INSTANCE;
                                                                                                                                m1202constructorimpl7 = Result.m1202constructorimpl(ResultKt.createFailure(th442));
                                                                                                                                if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                                                                                                                                }
                                                                                                                                String str652 = (String) m1202constructorimpl7;
                                                                                                                                if (CoreExtsKt.isDebug()) {
                                                                                                                                }
                                                                                                                                str14 = str17;
                                                                                                                                throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                                                                            }
                                                                                                                        } catch (Throwable th47) {
                                                                                                                            th = th47;
                                                                                                                            str12 = str24;
                                                                                                                        }
                                                                                                                        if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                                                                                                                            m1202constructorimpl7 = "";
                                                                                                                        }
                                                                                                                        String str6522 = (String) m1202constructorimpl7;
                                                                                                                        if (CoreExtsKt.isDebug()) {
                                                                                                                            String str66 = str11;
                                                                                                                            try {
                                                                                                                                Intrinsics.checkNotNullExpressionValue(str6522, str66);
                                                                                                                                if (StringsKt.contains$default((CharSequence) str6522, charSequence, false, 2, (Object) null)) {
                                                                                                                                    Ref.ObjectRef objectRef14 = new Ref.ObjectRef();
                                                                                                                                    StackTraceElement[] stackTrace14 = new Throwable().getStackTrace();
                                                                                                                                    str14 = str17;
                                                                                                                                    try {
                                                                                                                                        Intrinsics.checkNotNullExpressionValue(stackTrace14, str14);
                                                                                                                                        StackTraceElement stackTraceElement14 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace14);
                                                                                                                                        if (stackTraceElement14 != null) {
                                                                                                                                            String className14 = stackTraceElement14.getClassName();
                                                                                                                                            if (className14 != null) {
                                                                                                                                                str11 = str66;
                                                                                                                                                try {
                                                                                                                                                    String substringAfterLast$default8 = StringsKt.substringAfterLast$default(className14, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                                                                                                                    if (substringAfterLast$default8 != null) {
                                                                                                                                                        str46 = substringAfterLast$default8;
                                                                                                                                                        objectRef14.element = str46;
                                                                                                                                                        matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef14.element);
                                                                                                                                                        if (matcher9.find()) {
                                                                                                                                                            ?? replaceAll14 = matcher9.replaceAll("");
                                                                                                                                                            Intrinsics.checkNotNullExpressionValue(replaceAll14, "replaceAll(\"\")");
                                                                                                                                                            objectRef14.element = replaceAll14;
                                                                                                                                                        }
                                                                                                                                                        if (((String) objectRef14.element).length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                                                                                                                            str47 = ((String) objectRef14.element).substring(0, 23);
                                                                                                                                                            Intrinsics.checkNotNullExpressionValue(str47, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                                                            StringBuilder sb12 = new StringBuilder();
                                                                                                                                                            str48 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                                                                            if (str48 == null) {
                                                                                                                                                                str48 = "null ";
                                                                                                                                                            }
                                                                                                                                                            sb12.append(str48);
                                                                                                                                                            sb12.append(' ');
                                                                                                                                                            sb12.append("");
                                                                                                                                                            Log.println(3, str47, sb12.toString());
                                                                                                                                                        }
                                                                                                                                                        str47 = (String) objectRef14.element;
                                                                                                                                                        StringBuilder sb122 = new StringBuilder();
                                                                                                                                                        str48 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                                                                        if (str48 == null) {
                                                                                                                                                        }
                                                                                                                                                        sb122.append(str48);
                                                                                                                                                        sb122.append(' ');
                                                                                                                                                        sb122.append("");
                                                                                                                                                        Log.println(3, str47, sb122.toString());
                                                                                                                                                    }
                                                                                                                                                    String canonicalName8 = (hsStateHandler != null || (cls8 = hsStateHandler.getClass()) == null) ? null : cls8.getCanonicalName();
                                                                                                                                                    str46 = canonicalName8 != null ? "N/A" : canonicalName8;
                                                                                                                                                    objectRef14.element = str46;
                                                                                                                                                    matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef14.element);
                                                                                                                                                    if (matcher9.find()) {
                                                                                                                                                    }
                                                                                                                                                    if (((String) objectRef14.element).length() > 23) {
                                                                                                                                                        str47 = ((String) objectRef14.element).substring(0, 23);
                                                                                                                                                        Intrinsics.checkNotNullExpressionValue(str47, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                                                        StringBuilder sb1222 = new StringBuilder();
                                                                                                                                                        str48 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                                                                        if (str48 == null) {
                                                                                                                                                        }
                                                                                                                                                        sb1222.append(str48);
                                                                                                                                                        sb1222.append(' ');
                                                                                                                                                        sb1222.append("");
                                                                                                                                                        Log.println(3, str47, sb1222.toString());
                                                                                                                                                    }
                                                                                                                                                    str47 = (String) objectRef14.element;
                                                                                                                                                    StringBuilder sb12222 = new StringBuilder();
                                                                                                                                                    str48 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                                                                    if (str48 == null) {
                                                                                                                                                    }
                                                                                                                                                    sb12222.append(str48);
                                                                                                                                                    sb12222.append(' ');
                                                                                                                                                    sb12222.append("");
                                                                                                                                                    Log.println(3, str47, sb12222.toString());
                                                                                                                                                } catch (Throwable th48) {
                                                                                                                                                    th = th48;
                                                                                                                                                    hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                                                                    th = th;
                                                                                                                                                    Result.Companion companion722222222222222 = Result.INSTANCE;
                                                                                                                                                    m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                                                                    HKMainActivity hKMainActivity5222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                                                                                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                                                                                    if (m1205exceptionOrNullimpl2 != null) {
                                                                                                                                                    }
                                                                                                                                                    return Result.m1201boximpl(obj4);
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        str11 = str66;
                                                                                                                                        if (hsStateHandler != null) {
                                                                                                                                        }
                                                                                                                                        if (canonicalName8 != null) {
                                                                                                                                        }
                                                                                                                                        objectRef14.element = str46;
                                                                                                                                        matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef14.element);
                                                                                                                                        if (matcher9.find()) {
                                                                                                                                        }
                                                                                                                                        if (((String) objectRef14.element).length() > 23) {
                                                                                                                                        }
                                                                                                                                        str47 = (String) objectRef14.element;
                                                                                                                                        StringBuilder sb122222 = new StringBuilder();
                                                                                                                                        str48 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                                                        if (str48 == null) {
                                                                                                                                        }
                                                                                                                                        sb122222.append(str48);
                                                                                                                                        sb122222.append(' ');
                                                                                                                                        sb122222.append("");
                                                                                                                                        Log.println(3, str47, sb122222.toString());
                                                                                                                                    } catch (Throwable th49) {
                                                                                                                                        th = th49;
                                                                                                                                        str11 = str66;
                                                                                                                                        hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                                                        th = th;
                                                                                                                                        Result.Companion companion7222222222222222 = Result.INSTANCE;
                                                                                                                                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                                                        HKMainActivity hKMainActivity52222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                                                                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                                                                        if (m1205exceptionOrNullimpl2 != null) {
                                                                                                                                        }
                                                                                                                                        return Result.m1201boximpl(obj4);
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    str11 = str66;
                                                                                                                                }
                                                                                                                            } catch (Throwable th50) {
                                                                                                                                th = th50;
                                                                                                                                str11 = str66;
                                                                                                                                str14 = str17;
                                                                                                                                hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                                                th = th;
                                                                                                                                Result.Companion companion72222222222222222 = Result.INSTANCE;
                                                                                                                                m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                                                HKMainActivity hKMainActivity522222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                                                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                                                                if (m1205exceptionOrNullimpl2 != null) {
                                                                                                                                }
                                                                                                                                return Result.m1201boximpl(obj4);
                                                                                                                            }
                                                                                                                        }
                                                                                                                        str14 = str17;
                                                                                                                    }
                                                                                                                }
                                                                                                                str44 = (String) objectRef13.element;
                                                                                                                sb11.append(str44);
                                                                                                                sb11.append(" - ");
                                                                                                                str45 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                                if (str45 == null) {
                                                                                                                }
                                                                                                                sb11.append(str45);
                                                                                                                sb11.append(' ');
                                                                                                                sb11.append("");
                                                                                                                companion24.log(level7, sb11.toString());
                                                                                                                if (CoreExtsKt.isRelease()) {
                                                                                                                }
                                                                                                            }
                                                                                                            substringAfterLast$default5 = (hsStateHandler != null || (cls9 = hsStateHandler.getClass()) == null) ? 0 : cls9.getCanonicalName();
                                                                                                            if (substringAfterLast$default5 == 0) {
                                                                                                                substringAfterLast$default5 = "N/A";
                                                                                                            }
                                                                                                            objectRef13.element = substringAfterLast$default5;
                                                                                                            matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef13.element);
                                                                                                            if (matcher8.find()) {
                                                                                                            }
                                                                                                            if (((String) objectRef13.element).length() > 23) {
                                                                                                                str44 = ((String) objectRef13.element).substring(0, 23);
                                                                                                                Intrinsics.checkNotNullExpressionValue(str44, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                                                                sb11.append(str44);
                                                                                                                sb11.append(" - ");
                                                                                                                str45 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                                if (str45 == null) {
                                                                                                                }
                                                                                                                sb11.append(str45);
                                                                                                                sb11.append(' ');
                                                                                                                sb11.append("");
                                                                                                                companion24.log(level7, sb11.toString());
                                                                                                                if (CoreExtsKt.isRelease()) {
                                                                                                                }
                                                                                                            }
                                                                                                            str44 = (String) objectRef13.element;
                                                                                                            sb11.append(str44);
                                                                                                            sb11.append(" - ");
                                                                                                            str45 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                                            if (str45 == null) {
                                                                                                            }
                                                                                                            sb11.append(str45);
                                                                                                            sb11.append(' ');
                                                                                                            sb11.append("");
                                                                                                            companion24.log(level7, sb11.toString());
                                                                                                            if (CoreExtsKt.isRelease()) {
                                                                                                            }
                                                                                                        } catch (Throwable th51) {
                                                                                                            hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                            th = th51;
                                                                                                            str12 = str24;
                                                                                                            str14 = str17;
                                                                                                            str15 = str16;
                                                                                                            Result.Companion companion722222222222222222 = Result.INSTANCE;
                                                                                                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                            HKMainActivity hKMainActivity5222222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                                            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                                            if (m1205exceptionOrNullimpl2 != null) {
                                                                                                            }
                                                                                                            return Result.m1201boximpl(obj4);
                                                                                                        }
                                                                                                    }
                                                                                                } catch (Throwable th52) {
                                                                                                    str13 = "android.app.AppGlobals";
                                                                                                    hKMainActivity$startBarcodeFlow$2 = this;
                                                                                                    th = th52;
                                                                                                    str12 = str64;
                                                                                                    str14 = str28;
                                                                                                    str15 = str16;
                                                                                                    Result.Companion companion7222222222222222222 = Result.INSTANCE;
                                                                                                    m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                                    HKMainActivity hKMainActivity52222222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                                    m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                                    if (m1205exceptionOrNullimpl2 != null) {
                                                                                                    }
                                                                                                    return Result.m1201boximpl(obj4);
                                                                                                }
                                                                                            }
                                                                                            objectRef13.element = substringAfterLast$default5;
                                                                                            matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef13.element);
                                                                                            if (matcher8.find()) {
                                                                                            }
                                                                                            if (((String) objectRef13.element).length() > 23) {
                                                                                            }
                                                                                            str44 = (String) objectRef13.element;
                                                                                            sb11.append(str44);
                                                                                            sb11.append(" - ");
                                                                                            str45 = "retrieveState: state targetFile exists : " + file.exists();
                                                                                            if (str45 == null) {
                                                                                            }
                                                                                            sb11.append(str45);
                                                                                            sb11.append(' ');
                                                                                            sb11.append("");
                                                                                            companion24.log(level7, sb11.toString());
                                                                                            if (CoreExtsKt.isRelease()) {
                                                                                            }
                                                                                        } catch (Throwable th53) {
                                                                                            th = th53;
                                                                                            str12 = str24;
                                                                                            str14 = str17;
                                                                                            str15 = str16;
                                                                                            hKMainActivity$startBarcodeFlow$2 = this;
                                                                                            th = th;
                                                                                            Result.Companion companion72222222222222222222 = Result.INSTANCE;
                                                                                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                            HKMainActivity hKMainActivity522222222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                            if (m1205exceptionOrNullimpl2 != null) {
                                                                                            }
                                                                                            return Result.m1201boximpl(obj4);
                                                                                        }
                                                                                        str24 = str64;
                                                                                        str17 = str28;
                                                                                        str13 = "android.app.AppGlobals";
                                                                                        if (hsStateHandler != null) {
                                                                                        }
                                                                                        if (substringAfterLast$default5 == 0) {
                                                                                        }
                                                                                    } catch (Throwable th54) {
                                                                                        th = th54;
                                                                                        str12 = str64;
                                                                                        str14 = str28;
                                                                                        str13 = "android.app.AppGlobals";
                                                                                        str15 = str16;
                                                                                        hKMainActivity$startBarcodeFlow$2 = this;
                                                                                        th = th;
                                                                                        Result.Companion companion722222222222222222222 = Result.INSTANCE;
                                                                                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                                                        HKMainActivity hKMainActivity5222222222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                                                                                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                                                                                        if (m1205exceptionOrNullimpl2 != null) {
                                                                                        }
                                                                                        return Result.m1201boximpl(obj4);
                                                                                    }
                                                                                } catch (Throwable th55) {
                                                                                    th = th55;
                                                                                    str12 = str64;
                                                                                    str14 = str28;
                                                                                    str13 = "android.app.AppGlobals";
                                                                                }
                                                                            }
                                                                            throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                        }
                                                                        str30 = (String) objectRef7.element;
                                                                        StringBuilder sb52 = new StringBuilder();
                                                                        str31 = "retrieveState: state targetFile exists : " + file.exists();
                                                                        if (str31 == null) {
                                                                        }
                                                                        sb52.append(str31);
                                                                        sb52.append(' ');
                                                                        sb52.append("");
                                                                        Log.println(3, str30, sb52.toString());
                                                                        if (file.exists()) {
                                                                        }
                                                                        throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                    }
                                                                    String canonicalName9 = (hsStateHandler != null || (cls3 = hsStateHandler.getClass()) == null) ? null : cls3.getCanonicalName();
                                                                    str29 = canonicalName9 != null ? "N/A" : canonicalName9;
                                                                    objectRef7.element = str29;
                                                                    matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef7.element);
                                                                    if (matcher3.find()) {
                                                                    }
                                                                    if (((String) objectRef7.element).length() > 23) {
                                                                        str30 = ((String) objectRef7.element).substring(0, 23);
                                                                        Intrinsics.checkNotNullExpressionValue(str30, "this as java.lang.String…ing(startIndex, endIndex)");
                                                                        StringBuilder sb522 = new StringBuilder();
                                                                        str31 = "retrieveState: state targetFile exists : " + file.exists();
                                                                        if (str31 == null) {
                                                                        }
                                                                        sb522.append(str31);
                                                                        sb522.append(' ');
                                                                        sb522.append("");
                                                                        Log.println(3, str30, sb522.toString());
                                                                        if (file.exists()) {
                                                                        }
                                                                        throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                    }
                                                                    str30 = (String) objectRef7.element;
                                                                    StringBuilder sb5222 = new StringBuilder();
                                                                    str31 = "retrieveState: state targetFile exists : " + file.exists();
                                                                    if (str31 == null) {
                                                                    }
                                                                    sb5222.append(str31);
                                                                    sb5222.append(' ');
                                                                    sb5222.append("");
                                                                    Log.println(3, str30, sb5222.toString());
                                                                    if (file.exists()) {
                                                                    }
                                                                    throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                                }
                                                            }
                                                            str16 = str15;
                                                            if (hsStateHandler != null) {
                                                            }
                                                            if (canonicalName9 != null) {
                                                            }
                                                            objectRef7.element = str29;
                                                            matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef7.element);
                                                            if (matcher3.find()) {
                                                            }
                                                            if (((String) objectRef7.element).length() > 23) {
                                                            }
                                                            str30 = (String) objectRef7.element;
                                                            StringBuilder sb52222 = new StringBuilder();
                                                            str31 = "retrieveState: state targetFile exists : " + file.exists();
                                                            if (str31 == null) {
                                                            }
                                                            sb52222.append(str31);
                                                            sb52222.append(' ');
                                                            sb52222.append("");
                                                            Log.println(3, str30, sb52222.toString());
                                                            if (file.exists()) {
                                                            }
                                                            throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                                        }
                                                    }
                                                    str16 = str15;
                                                } else {
                                                    str12 = str24;
                                                }
                                                str28 = str17;
                                                if (file.exists()) {
                                                }
                                                throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                            }
                                            str26 = (String) objectRef2.element;
                                            sb42.append(str26);
                                            sb42.append(" - ");
                                            str27 = "retrieveState: state targetFile exists : " + file.exists();
                                            if (str27 == null) {
                                            }
                                            sb42.append(str27);
                                            sb42.append(' ');
                                            sb42.append("");
                                            companion102.log(level42, sb42.toString());
                                            if (CoreExtsKt.isRelease()) {
                                            }
                                            str28 = str17;
                                            if (file.exists()) {
                                            }
                                            throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                        }
                                        String canonicalName10 = (hsStateHandler != null || (cls10 = hsStateHandler.getClass()) == null) ? null : cls10.getCanonicalName();
                                        str25 = canonicalName10 == null ? "N/A" : canonicalName10;
                                        objectRef2.element = str25;
                                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                        if (matcher2.find()) {
                                        }
                                        if (((String) objectRef2.element).length() > 23) {
                                            str26 = ((String) objectRef2.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str26, "this as java.lang.String…ing(startIndex, endIndex)");
                                            sb42.append(str26);
                                            sb42.append(" - ");
                                            str27 = "retrieveState: state targetFile exists : " + file.exists();
                                            if (str27 == null) {
                                            }
                                            sb42.append(str27);
                                            sb42.append(' ');
                                            sb42.append("");
                                            companion102.log(level42, sb42.toString());
                                            if (CoreExtsKt.isRelease()) {
                                            }
                                            str28 = str17;
                                            if (file.exists()) {
                                            }
                                            throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                        }
                                        str26 = (String) objectRef2.element;
                                        sb42.append(str26);
                                        sb42.append(" - ");
                                        str27 = "retrieveState: state targetFile exists : " + file.exists();
                                        if (str27 == null) {
                                        }
                                        sb42.append(str27);
                                        sb42.append(' ');
                                        sb42.append("");
                                        companion102.log(level42, sb42.toString());
                                        if (CoreExtsKt.isRelease()) {
                                        }
                                        str28 = str17;
                                        if (file.exists()) {
                                        }
                                        throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                                    }
                                }
                                str16 = str20;
                                str17 = str21;
                                str24 = "getInitialApplication";
                                if (hsStateHandler != null) {
                                }
                                if (canonicalName10 == null) {
                                }
                                objectRef2.element = str25;
                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                }
                                if (((String) objectRef2.element).length() > 23) {
                                }
                                str26 = (String) objectRef2.element;
                                sb42.append(str26);
                                sb42.append(" - ");
                                str27 = "retrieveState: state targetFile exists : " + file.exists();
                                if (str27 == null) {
                                }
                                sb42.append(str27);
                                sb42.append(' ');
                                sb42.append("");
                                companion102.log(level42, sb42.toString());
                                if (CoreExtsKt.isRelease()) {
                                }
                                str28 = str17;
                                if (file.exists()) {
                                }
                                throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                            }
                            str19 = (String) objectRef.element;
                            sb.append(str19);
                            sb.append(" - ");
                            sb.append("retrieveState() called");
                            sb.append(' ');
                            sb.append("");
                            companion.log(level, sb.toString());
                            if (CoreExtsKt.isRelease()) {
                            }
                            file = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                            if (file.exists()) {
                            }
                            HyperLogger.Level level422 = HyperLogger.Level.DEBUG;
                            HyperLogger companion1022 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb422 = new StringBuilder();
                            objectRef2 = new Ref.ObjectRef();
                            str11 = str22;
                            StackTraceElement[] stackTrace422 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace422, str21);
                            stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace422);
                            if (stackTraceElement2 != null) {
                            }
                            str16 = str20;
                            str17 = str21;
                            str24 = "getInitialApplication";
                            if (hsStateHandler != null) {
                            }
                            if (canonicalName10 == null) {
                            }
                            objectRef2.element = str25;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher2.find()) {
                            }
                            if (((String) objectRef2.element).length() > 23) {
                            }
                            str26 = (String) objectRef2.element;
                            sb422.append(str26);
                            sb422.append(" - ");
                            str27 = "retrieveState: state targetFile exists : " + file.exists();
                            if (str27 == null) {
                            }
                            sb422.append(str27);
                            sb422.append(' ');
                            sb422.append("");
                            companion1022.log(level422, sb422.toString());
                            if (CoreExtsKt.isRelease()) {
                            }
                            str28 = str17;
                            if (file.exists()) {
                            }
                            throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                        }
                        String canonicalName11 = (hsStateHandler != null || (cls11 = hsStateHandler.getClass()) == null) ? null : cls11.getCanonicalName();
                        str18 = canonicalName11 != null ? "N/A" : canonicalName11;
                        objectRef.element = str18;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                        if (matcher.find()) {
                        }
                        if (((String) objectRef.element).length() > 23) {
                            str19 = ((String) objectRef.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str19, "this as java.lang.String…ing(startIndex, endIndex)");
                            sb.append(str19);
                            sb.append(" - ");
                            sb.append("retrieveState() called");
                            sb.append(' ');
                            sb.append("");
                            companion.log(level, sb.toString());
                            if (CoreExtsKt.isRelease()) {
                            }
                            file = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                            if (file.exists()) {
                            }
                            HyperLogger.Level level4222 = HyperLogger.Level.DEBUG;
                            HyperLogger companion10222 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb4222 = new StringBuilder();
                            objectRef2 = new Ref.ObjectRef();
                            str11 = str22;
                            StackTraceElement[] stackTrace4222 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4222, str21);
                            stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4222);
                            if (stackTraceElement2 != null) {
                            }
                            str16 = str20;
                            str17 = str21;
                            str24 = "getInitialApplication";
                            if (hsStateHandler != null) {
                            }
                            if (canonicalName10 == null) {
                            }
                            objectRef2.element = str25;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher2.find()) {
                            }
                            if (((String) objectRef2.element).length() > 23) {
                            }
                            str26 = (String) objectRef2.element;
                            sb4222.append(str26);
                            sb4222.append(" - ");
                            str27 = "retrieveState: state targetFile exists : " + file.exists();
                            if (str27 == null) {
                            }
                            sb4222.append(str27);
                            sb4222.append(' ');
                            sb4222.append("");
                            companion10222.log(level4222, sb4222.toString());
                            if (CoreExtsKt.isRelease()) {
                            }
                            str28 = str17;
                            if (file.exists()) {
                            }
                            throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                        }
                        str19 = (String) objectRef.element;
                        sb.append(str19);
                        sb.append(" - ");
                        sb.append("retrieveState() called");
                        sb.append(' ');
                        sb.append("");
                        companion.log(level, sb.toString());
                        if (CoreExtsKt.isRelease()) {
                        }
                        file = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                        if (file.exists()) {
                        }
                        HyperLogger.Level level42222 = HyperLogger.Level.DEBUG;
                        HyperLogger companion102222 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb42222 = new StringBuilder();
                        objectRef2 = new Ref.ObjectRef();
                        str11 = str22;
                        StackTraceElement[] stackTrace42222 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace42222, str21);
                        stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace42222);
                        if (stackTraceElement2 != null) {
                        }
                        str16 = str20;
                        str17 = str21;
                        str24 = "getInitialApplication";
                        if (hsStateHandler != null) {
                        }
                        if (canonicalName10 == null) {
                        }
                        objectRef2.element = str25;
                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                        }
                        if (((String) objectRef2.element).length() > 23) {
                        }
                        str26 = (String) objectRef2.element;
                        sb42222.append(str26);
                        sb42222.append(" - ");
                        str27 = "retrieveState: state targetFile exists : " + file.exists();
                        if (str27 == null) {
                        }
                        sb42222.append(str27);
                        sb42222.append(' ');
                        sb42222.append("");
                        companion102222.log(level42222, sb42222.toString());
                        if (CoreExtsKt.isRelease()) {
                        }
                        str28 = str17;
                        if (file.exists()) {
                        }
                        throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
                    }
                }
                str16 = str6;
                str11 = str8;
                str17 = str7;
                if (hsStateHandler != null) {
                }
                if (canonicalName11 != null) {
                }
                objectRef.element = str18;
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                if (matcher.find()) {
                }
                if (((String) objectRef.element).length() > 23) {
                }
                str19 = (String) objectRef.element;
                sb.append(str19);
                sb.append(" - ");
                sb.append("retrieveState() called");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                }
                file = new File(hsStateHandler.getResultDataDir(), tag + ".json");
                if (file.exists()) {
                }
                HyperLogger.Level level422222 = HyperLogger.Level.DEBUG;
                HyperLogger companion1022222 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb422222 = new StringBuilder();
                objectRef2 = new Ref.ObjectRef();
                str11 = str22;
                StackTraceElement[] stackTrace422222 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace422222, str21);
                stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace422222);
                if (stackTraceElement2 != null) {
                }
                str16 = str20;
                str17 = str21;
                str24 = "getInitialApplication";
                if (hsStateHandler != null) {
                }
                if (canonicalName10 == null) {
                }
                objectRef2.element = str25;
                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                if (matcher2.find()) {
                }
                if (((String) objectRef2.element).length() > 23) {
                }
                str26 = (String) objectRef2.element;
                sb422222.append(str26);
                sb422222.append(" - ");
                str27 = "retrieveState: state targetFile exists : " + file.exists();
                if (str27 == null) {
                }
                sb422222.append(str27);
                sb422222.append(' ');
                sb422222.append("");
                companion1022222.log(level422222, sb422222.toString());
                if (CoreExtsKt.isRelease()) {
                }
                str28 = str17;
                if (file.exists()) {
                }
                throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
            } else {
                coroutineScope2 = coroutineScope4;
                str11 = str8;
                str12 = "getInitialApplication";
                str13 = "android.app.AppGlobals";
                str15 = str6;
                str14 = str7;
                try {
                    HKMainActivity hKMainActivity6 = hKMainActivity;
                    hKMainActivity$startBarcodeFlow$2 = this;
                    coroutineScope = coroutineScope2;
                    try {
                        hKMainActivity$startBarcodeFlow$2.L$0 = coroutineScope;
                        hKMainActivity$startBarcodeFlow$2.L$1 = hKMainActivity;
                        barcodeCapture = barcodeCapture22;
                        hKMainActivity$startBarcodeFlow$2.L$2 = barcodeCapture;
                        hKMainActivity$startBarcodeFlow$2.label = 1;
                        performBarcodeCapture = HyperSnapBridgeKt.performBarcodeCapture(hKMainActivity6, barcodeCapture, hKMainActivity$startBarcodeFlow$2);
                        if (performBarcodeCapture == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        coroutineScope3 = coroutineScope;
                        hKMainActivity3 = hKMainActivity;
                    } catch (Throwable th56) {
                        th = th56;
                        th = th;
                        coroutineScope2 = coroutineScope;
                        Result.Companion companion7222222222222222222222 = Result.INSTANCE;
                        m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        HKMainActivity hKMainActivity52222222222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
                        if (m1205exceptionOrNullimpl2 != null) {
                        }
                        return Result.m1201boximpl(obj4);
                    }
                } catch (Throwable th57) {
                    th = th57;
                    hKMainActivity$startBarcodeFlow$2 = this;
                }
            }
            th = th;
            Result.Companion companion72222222222222222222222 = Result.INSTANCE;
            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th));
            HKMainActivity hKMainActivity522222222222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
            if (m1205exceptionOrNullimpl2 != null) {
            }
            return Result.m1201boximpl(obj4);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        WorkflowUIState.BarcodeCapture barcodeCapture3 = (WorkflowUIState.BarcodeCapture) hKMainActivity$startBarcodeFlow$2.L$2;
        hKMainActivity3 = (HKMainActivity) hKMainActivity$startBarcodeFlow$2.L$1;
        CoroutineScope coroutineScope5 = (CoroutineScope) hKMainActivity$startBarcodeFlow$2.L$0;
        try {
            ResultKt.throwOnFailure(obj);
            charSequence = "co.hyperverge";
            str11 = "packageName";
            str12 = "getInitialApplication";
            str13 = "android.app.AppGlobals";
            str14 = "Throwable().stackTrace";
            barcodeCapture = barcodeCapture3;
            performBarcodeCapture = obj;
            coroutineScope3 = coroutineScope5;
            str15 = "null cannot be cast to non-null type android.app.Application";
        } catch (Throwable th58) {
            th = th58;
            coroutineScope2 = coroutineScope5;
            str15 = "null cannot be cast to non-null type android.app.Application";
            charSequence = "co.hyperverge";
            str11 = "packageName";
            str12 = "getInitialApplication";
            str13 = "android.app.AppGlobals";
            str14 = "Throwable().stackTrace";
        }
        jSONObject = (JSONObject) performBarcodeCapture;
        hKMainActivity2 = hKMainActivity3;
        coroutineScope2 = coroutineScope3;
        MainVM.updateBarcodeData$hyperkyc_release$default(hKMainActivity2.getMainVM(), barcodeCapture.getTag(), jSONObject.getString("qr"), jSONObject.getString("status"), false, 8, null);
        hKMainActivity2.flowForwardOrFinish();
        m1202constructorimpl8 = Result.m1202constructorimpl(Unit.INSTANCE);
        HKMainActivity hKMainActivity5222222222222222222222222 = hKMainActivity$startBarcodeFlow$2.this$0;
        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl8);
        if (m1205exceptionOrNullimpl2 != null) {
        }
        return Result.m1201boximpl(obj4);
    }
}
