package com.facebook.ppml.receiver;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IReceiverService extends IInterface {
    public static final String DESCRIPTOR = "com.facebook.ppml.receiver.IReceiverService";

    /* loaded from: classes2.dex */
    public static class Default implements IReceiverService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.facebook.ppml.receiver.IReceiverService
        public int sendEvents(Bundle bundle) throws RemoteException {
            return 0;
        }
    }

    int sendEvents(Bundle bundle) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IReceiverService {
        static final int TRANSACTION_sendEvents = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IReceiverService.DESCRIPTOR);
        }

        public static IReceiverService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IReceiverService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IReceiverService)) {
                return (IReceiverService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IReceiverService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IReceiverService.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int sendEvents = sendEvents((Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                parcel2.writeInt(sendEvents);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IReceiverService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IReceiverService.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.facebook.ppml.receiver.IReceiverService
            public int sendEvents(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IReceiverService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
