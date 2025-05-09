package com.google.crypto.tink.hybrid.internal;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
/* loaded from: classes4.dex */
interface HpkeKdf {
    byte[] extractAndExpand(byte[] salt, byte[] ikm, String ikmLabel, byte[] info, String infoLabel, byte[] suiteId, int length) throws GeneralSecurityException;

    byte[] getKdfId() throws GeneralSecurityException;

    byte[] labeledExpand(byte[] prk, byte[] info, String infoLabel, byte[] suiteId, int length) throws GeneralSecurityException;

    byte[] labeledExtract(byte[] salt, byte[] ikm, String ikmLabel, byte[] suiteId) throws GeneralSecurityException;
}
