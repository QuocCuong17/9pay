package com.google.android.gms.measurement.internal;

import androidx.media3.common.C;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.extractor.AacUtil;
import com.google.android.gms.internal.measurement.zzod;
import com.google.android.gms.internal.measurement.zzog;
import com.google.android.gms.internal.measurement.zzoj;
import com.google.android.gms.internal.measurement.zzom;
import com.google.android.gms.internal.measurement.zzop;
import com.google.android.gms.internal.measurement.zzos;
import com.google.android.gms.internal.measurement.zzov;
import com.google.android.gms.internal.measurement.zzoy;
import com.google.android.gms.internal.measurement.zzpb;
import com.google.android.gms.internal.measurement.zzpe;
import com.google.android.gms.internal.measurement.zzph;
import com.google.android.gms.internal.measurement.zzpk;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.internal.measurement.zzpq;
import com.google.android.gms.internal.measurement.zzpt;
import com.google.android.gms.internal.measurement.zzpw;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqc;
import com.google.android.gms.internal.measurement.zzqf;
import com.google.android.gms.internal.measurement.zzqi;
import com.google.android.gms.internal.measurement.zzql;
import com.google.android.gms.internal.measurement.zzqo;
import com.google.android.gms.internal.measurement.zzqr;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzqx;
import com.google.android.gms.internal.measurement.zzra;
import com.google.android.gms.internal.measurement.zzrd;
import com.google.android.gms.internal.measurement.zzrg;
import com.google.android.gms.internal.measurement.zzrj;
import com.google.android.gms.internal.measurement.zzrm;
import com.google.android.gms.internal.measurement.zzrp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzeg {
    public static final zzef zzA;
    public static final zzef zzB;
    public static final zzef zzC;
    public static final zzef zzD;
    public static final zzef zzE;
    public static final zzef zzF;
    public static final zzef zzG;
    public static final zzef zzH;
    public static final zzef zzI;
    public static final zzef zzJ;
    public static final zzef zzK;
    public static final zzef zzL;
    public static final zzef zzM;
    public static final zzef zzN;
    public static final zzef zzO;
    public static final zzef zzP;
    public static final zzef zzQ;
    public static final zzef zzR;
    public static final zzef zzS;
    public static final zzef zzT;
    public static final zzef zzU;
    public static final zzef zzV;
    public static final zzef zzW;
    public static final zzef zzX;
    public static final zzef zzY;
    public static final zzef zzZ;
    public static final zzef zza;
    public static final zzef zzaA;
    public static final zzef zzaB;
    public static final zzef zzaC;
    public static final zzef zzaD;
    public static final zzef zzaE;
    public static final zzef zzaF;
    public static final zzef zzaG;
    public static final zzef zzaH;
    public static final zzef zzaI;
    private static final List zzaJ = Collections.synchronizedList(new ArrayList());
    private static final Set zzaK = Collections.synchronizedSet(new HashSet());
    public static final zzef zzaa;
    public static final zzef zzab;
    public static final zzef zzac;
    public static final zzef zzad;
    public static final zzef zzae;
    public static final zzef zzaf;
    public static final zzef zzag;
    public static final zzef zzah;
    public static final zzef zzai;
    public static final zzef zzaj;
    public static final zzef zzak;
    public static final zzef zzal;
    public static final zzef zzam;
    public static final zzef zzan;
    public static final zzef zzao;
    public static final zzef zzap;
    public static final zzef zzaq;
    public static final zzef zzar;
    public static final zzef zzas;
    public static final zzef zzat;
    public static final zzef zzau;
    public static final zzef zzav;
    public static final zzef zzaw;
    public static final zzef zzax;
    public static final zzef zzay;
    public static final zzef zzaz;
    public static final zzef zzb;
    public static final zzef zzc;
    public static final zzef zzd;
    public static final zzef zze;
    public static final zzef zzf;
    public static final zzef zzg;
    public static final zzef zzh;
    public static final zzef zzi;
    public static final zzef zzj;
    public static final zzef zzk;
    public static final zzef zzl;
    public static final zzef zzm;
    public static final zzef zzn;
    public static final zzef zzo;
    public static final zzef zzp;
    public static final zzef zzq;
    public static final zzef zzr;
    public static final zzef zzs;
    public static final zzef zzt;
    public static final zzef zzu;
    public static final zzef zzv;
    public static final zzef zzw;
    public static final zzef zzx;
    public static final zzef zzy;
    public static final zzef zzz;

    static {
        Long valueOf = Long.valueOf(Renderer.DEFAULT_DURATION_TO_PROGRESS_US);
        zza = zza("measurement.ad_id_cache_time", valueOf, valueOf, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbh
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzb());
            }
        });
        zzb = zza("measurement.app_uninstalled_additional_ad_id_cache_time", 3600000L, 3600000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzaz
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzc());
            }
        });
        zzc = zza("measurement.monitoring.sample_period_millis", 86400000L, 86400000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbl
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzn());
            }
        });
        zzd = zza("measurement.config.cache_time", 86400000L, 3600000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbx
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zze());
            }
        });
        zze = zza("measurement.config.url_scheme", "https", "https", new zzec() { // from class: com.google.android.gms.measurement.internal.zzcj
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return zzoj.zzM();
            }
        });
        zzf = zza("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", new zzec() { // from class: com.google.android.gms.measurement.internal.zzcv
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return zzoj.zzL();
            }
        });
        zzg = zza("measurement.upload.max_bundles", 100, 100, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdh
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzy());
            }
        });
        zzh = zza("measurement.upload.max_batch_size", 65536, 65536, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdt
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzG());
            }
        });
        zzi = zza("measurement.upload.max_bundle_size", 65536, 65536, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdy
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzx());
            }
        });
        zzj = zza("measurement.upload.max_events_per_bundle", 1000, 1000, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdz
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzB());
            }
        });
        Integer valueOf2 = Integer.valueOf(AacUtil.AAC_LC_MAX_RATE_BYTES_PER_SECOND);
        zzk = zza("measurement.upload.max_events_per_day", valueOf2, valueOf2, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbs
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzC());
            }
        });
        zzl = zza("measurement.upload.max_error_events_per_day", 1000, 1000, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcd
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzA());
            }
        });
        zzm = zza("measurement.upload.max_public_events_per_day", 50000, 50000, new zzec() { // from class: com.google.android.gms.measurement.internal.zzco
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzD());
            }
        });
        zzn = zza("measurement.upload.max_conversions_per_day", 10000, 10000, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcz
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzz());
            }
        });
        zzo = zza("measurement.upload.max_realtime_events_per_day", 10, 10, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdk
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzF());
            }
        });
        zzp = zza("measurement.store.max_stored_events_per_app", valueOf2, valueOf2, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdv
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzh());
            }
        });
        zzq = zza("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", new zzec() { // from class: com.google.android.gms.measurement.internal.zzea
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return zzoj.zzN();
            }
        });
        zzr = zza("measurement.upload.backoff_period", 43200000L, 43200000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzeb
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzu());
            }
        });
        zzs = zza("measurement.upload.window_interval", 3600000L, 3600000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzax
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzJ());
            }
        });
        zzt = zza("measurement.upload.interval", 3600000L, 3600000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzay
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzw());
            }
        });
        zzu = zza("measurement.upload.realtime_upload_interval", valueOf, valueOf, new zzec() { // from class: com.google.android.gms.measurement.internal.zzba
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzo());
            }
        });
        zzv = zza("measurement.upload.debug_upload_interval", 1000L, 1000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbb
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzf());
            }
        });
        zzw = zza("measurement.upload.minimum_delay", 500L, 500L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbc
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzm());
            }
        });
        zzx = zza("measurement.alarm_manager.minimum_interval", 60000L, 60000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbd
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzl());
            }
        });
        zzy = zza("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbe
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzr());
            }
        });
        zzz = zza("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbf
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzp());
            }
        });
        Long valueOf3 = Long.valueOf(C.DEFAULT_SEEK_FORWARD_INCREMENT_MS);
        zzA = zza("measurement.upload.initial_upload_delay_time", valueOf3, valueOf3, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbg
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzv());
            }
        });
        zzB = zza("measurement.upload.retry_time", 1800000L, 1800000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbi
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzI());
            }
        });
        zzC = zza("measurement.upload.retry_count", 6, 6, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbj
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzH());
            }
        });
        zzD = zza("measurement.upload.max_queue_time", 2419200000L, 2419200000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbk
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzE());
            }
        });
        zzE = zza("measurement.lifetimevalue.max_currency_tracked", 4, 4, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbm
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzg());
            }
        });
        zzF = zza("measurement.audience.filter_result_max_count", 200, 200, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbn
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzj());
            }
        });
        zzG = zza("measurement.upload.max_public_user_properties", 25, 25, null);
        zzH = zza("measurement.upload.max_event_name_cardinality", 500, 500, null);
        zzI = zza("measurement.upload.max_public_event_params", 25, 25, null);
        zzJ = zza("measurement.service_client.idle_disconnect_millis", 5000L, 5000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbo
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzq());
            }
        });
        zzK = zza("measurement.test.boolean_flag", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbp
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqc.zzg());
            }
        });
        zzL = zza("measurement.test.string_flag", "---", "---", new zzec() { // from class: com.google.android.gms.measurement.internal.zzbq
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return zzqc.zzf();
            }
        });
        zzM = zza("measurement.test.long_flag", -1L, -1L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbr
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzqc.zzd());
            }
        });
        zzN = zza("measurement.test.int_flag", -2, -2, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbt
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzqc.zzc());
            }
        });
        Double valueOf4 = Double.valueOf(-3.0d);
        zzO = zza("measurement.test.double_flag", valueOf4, valueOf4, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbu
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Double.valueOf(zzqc.zzb());
            }
        });
        zzP = zza("measurement.experiment.max_ids", 50, 50, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbv
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzi());
            }
        });
        zzQ = zza("measurement.upload.max_item_scoped_custom_parameters", 27, 27, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbw
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzk());
            }
        });
        zzR = zza("measurement.max_bundles_per_iteration", 100, 100, new zzec() { // from class: com.google.android.gms.measurement.internal.zzby
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzoj.zzd());
            }
        });
        zzS = zza("measurement.sdk.attribution.cache.ttl", 604800000L, 604800000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzbz
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzs());
            }
        });
        zzT = zza("measurement.redaction.app_instance_id.ttl", 7200000L, 7200000L, new zzec() { // from class: com.google.android.gms.measurement.internal.zzca
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Long.valueOf(zzoj.zzt());
            }
        });
        zzU = zza("measurement.collection.log_event_and_bundle_v2", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcb
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqf.zzc());
            }
        });
        zzV = zza("measurement.quality.checksum", false, false, null);
        zzW = zza("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcc
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzoy.zze());
            }
        });
        zzX = zza("measurement.audience.refresh_event_count_filters_timestamp", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzce
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzoy.zzd());
            }
        });
        zzY = zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcf
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzoy.zzf());
            }
        });
        zzZ = zza("measurement.sdk.collection.retrieve_deeplink_from_bow_2", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcg
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzrm.zzc());
            }
        });
        zzaa = zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzch
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzpt.zzc());
            }
        });
        zzab = zza("measurement.lifecycle.app_in_background_parameter", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzci
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzpw.zzc());
            }
        });
        zzac = zza("measurement.integration.disable_firebase_instance_id", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzck
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzrj.zzd());
            }
        });
        zzad = zza("measurement.collection.service.update_with_analytics_fix", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcl
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzrp.zzc());
            }
        });
        zzae = zza("measurement.client.firebase_feature_rollout.v1.enable", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcm
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzos.zzd());
            }
        });
        zzaf = zza("measurement.client.sessions.check_on_reset_and_enable2", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcn
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzph.zzd());
            }
        });
        zzag = zza("measurement.collection.synthetic_data_mitigation", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcp
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzrg.zzc());
            }
        });
        zzah = zza("measurement.service.storage_consent_support_version", 203600, 203600, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcq
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Integer.valueOf((int) zzom.zzb());
            }
        });
        zzai = zza("measurement.client.click_identifier_control.dev", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcr
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzod.zzc());
            }
        });
        zzaj = zza("measurement.service.click_identifier_control", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcs
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzog.zzc());
            }
        });
        zzak = zza("measurement.service.store_null_safelist", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzct
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzop.zzd());
            }
        });
        zzal = zza("measurement.service.store_safelist", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcu
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzop.zze());
            }
        });
        zzam = zza("measurement.collection.enable_session_stitching_token.first_open_fix", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcw
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqu.zze());
            }
        });
        zzan = zza("measurement.collection.enable_session_stitching_token.client.dev", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcx
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqu.zzd());
            }
        });
        zzao = zza("measurement.session_stitching_token_enabled", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzcy
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqu.zzf());
            }
        });
        zzap = zza("measurement.sgtm.client.dev", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzda
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzrd.zzd());
            }
        });
        zzaq = zza("measurement.sgtm.service", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdb
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzrd.zze());
            }
        });
        zzar = zza("measurement.redaction.retain_major_os_version", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdc
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzql.zzc());
            }
        });
        zzas = zza("measurement.redaction.scion_payload_generator", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdd
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzql.zzd());
            }
        });
        zzat = zza("measurement.service.clear_global_params_on_uninstall", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzde
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzpk.zzd());
            }
        });
        zzau = zza("measurement.sessionid.enable_client_session_id", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdf
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqr.zzd());
            }
        });
        zzav = zza("measurement.sfmc.client", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdg
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzra.zzd());
            }
        });
        zzaw = zza("measurement.sfmc.service", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdi
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzra.zze());
            }
        });
        zzax = zza("measurement.gmscore_feature_tracking", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdj
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzpn.zzd());
            }
        });
        zzay = zza("measurement.fix_health_monitor_stack_trace", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdl
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzpe.zzd());
            }
        });
        zzaz = zza("measurement.item_scoped_custom_parameters.client", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdm
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzpq.zzd());
            }
        });
        zzaA = zza("measurement.item_scoped_custom_parameters.service", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdn
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzpq.zze());
            }
        });
        zzaB = zza("measurement.remove_app_background.client", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdo
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqo.zzd());
            }
        });
        zzaC = zza("measurement.rb.attribution.service", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdp
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqi.zzc());
            }
        });
        zzaD = zza("measurement.collection.client.log_target_api_version", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdq
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                return Boolean.valueOf(zzpz.zzd());
            }
        });
        zzaE = zza("measurement.collection.service.log_target_api_version", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdr
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                return Boolean.valueOf(zzpz.zze());
            }
        });
        zzaF = zza("measurement.client.deep_link_referrer_fix", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzds
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                return Boolean.valueOf(zzpb.zzd());
            }
        });
        zzaG = zza("measurement.client.sessions.enable_fix_background_engagement", false, false, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdu
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                return Boolean.valueOf(zzqx.zzc());
            }
        });
        zzaH = zza("measurement.link_sst_to_sid", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdw
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                zzef zzefVar = zzeg.zza;
                return Boolean.valueOf(zzqu.zzg());
            }
        });
        zzaI = zza("measurement.client.ad_id_consent_fix", true, true, new zzec() { // from class: com.google.android.gms.measurement.internal.zzdx
            @Override // com.google.android.gms.measurement.internal.zzec
            public final Object zza() {
                return Boolean.valueOf(zzov.zzd());
            }
        });
    }

    static zzef zza(String str, Object obj, Object obj2, zzec zzecVar) {
        zzef zzefVar = new zzef(str, obj, obj2, zzecVar, null);
        zzaJ.add(zzefVar);
        return zzefVar;
    }
}
