package com.google.firebase.dynamiclinks.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public interface IDynamicLinksCallbacks extends IInterface {

    /* loaded from: classes4.dex */
    public static class Default implements IDynamicLinksCallbacks {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks
        public void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException {
        }

        @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks
        public void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException {
        }
    }

    void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException;

    void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException;

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IDynamicLinksCallbacks {
        private static final String DESCRIPTOR = "com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks";
        static final int TRANSACTION_onCreateShortDynamicLink = 2;
        static final int TRANSACTION_onGetDynamicLink = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDynamicLinksCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDynamicLinksCallbacks)) {
                return (IDynamicLinksCallbacks) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onGetDynamicLink(parcel.readInt() != 0 ? Status.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? DynamicLinkData.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onCreateShortDynamicLink(parcel.readInt() != 0 ? Status.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ShortDynamicLinkImpl.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IDynamicLinksCallbacks {
            public static IDynamicLinksCallbacks sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks
            public void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (dynamicLinkData != null) {
                        obtain.writeInt(1);
                        dynamicLinkData.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetDynamicLink(status, dynamicLinkData);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks
            public void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (shortDynamicLinkImpl != null) {
                        obtain.writeInt(1);
                        shortDynamicLinkImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCreateShortDynamicLink(status, shortDynamicLinkImpl);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDynamicLinksCallbacks iDynamicLinksCallbacks) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDynamicLinksCallbacks == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDynamicLinksCallbacks;
            return true;
        }

        public static IDynamicLinksCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
