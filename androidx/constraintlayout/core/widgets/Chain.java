package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.ArrayRow;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        ChainHead[] chainHeadArr;
        int i2;
        int i3;
        if (i == 0) {
            i3 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = 0;
        } else {
            int i4 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i2 = 2;
            i3 = i4;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            ChainHead chainHead = chainHeadArr[i5];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        if (r2.mHorizontalChainStyle == 2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004e, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x004c, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x004a, code lost:
    
        if (r2.mVerticalChainStyle == 2) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x052f  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x053f  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03e1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01bc  */
    /* JADX WARN: Type inference failed for: r2v63, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r8v39 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r8v46 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.constraintlayout.core.widgets.ConstraintWidget] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i, int i2, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        ArrayList<ConstraintWidget> arrayList;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        int i3;
        ConstraintWidget constraintWidget2;
        int i4;
        ConstraintWidget constraintWidget3;
        ConstraintAnchor constraintAnchor4;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        ConstraintWidget constraintWidget4;
        ConstraintAnchor constraintAnchor5;
        ConstraintWidget constraintWidget5;
        SolverVariable solverVariable3;
        ConstraintWidget constraintWidget6;
        ConstraintWidget constraintWidget7;
        SolverVariable solverVariable4;
        SolverVariable solverVariable5;
        float f;
        int size;
        int i5;
        ArrayList<ConstraintWidget> arrayList2;
        int i6;
        boolean z4;
        ConstraintWidget constraintWidget8;
        ConstraintWidget constraintWidget9;
        int i7;
        int i8 = i;
        ConstraintWidget constraintWidget10 = chainHead.mFirst;
        ConstraintWidget constraintWidget11 = chainHead.mLast;
        ConstraintWidget constraintWidget12 = chainHead.mFirstVisibleWidget;
        ConstraintWidget constraintWidget13 = chainHead.mLastVisibleWidget;
        ConstraintWidget constraintWidget14 = chainHead.mHead;
        float f2 = chainHead.mTotalWeight;
        ConstraintWidget constraintWidget15 = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget constraintWidget16 = chainHead.mLastMatchConstraintWidget;
        boolean z5 = constraintWidgetContainer.mListDimensionBehaviors[i8] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i8 == 0) {
            z = constraintWidget14.mHorizontalChainStyle == 0;
            z2 = constraintWidget14.mHorizontalChainStyle == 1;
        } else {
            z = constraintWidget14.mVerticalChainStyle == 0;
            z2 = constraintWidget14.mVerticalChainStyle == 1;
        }
        boolean z6 = z2;
        boolean z7 = false;
        boolean z8 = z;
        ?? r8 = constraintWidget10;
        while (true) {
            if (z7) {
                break;
            }
            ConstraintAnchor constraintAnchor6 = r8.mListAnchors[i2];
            int i9 = z3 ? 1 : 4;
            int margin = constraintAnchor6.getMargin();
            float f3 = f2;
            boolean z9 = z7;
            boolean z10 = r8.mListDimensionBehaviors[i8] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r8.mResolvedMatchConstraintDefault[i8] == 0;
            if (constraintAnchor6.mTarget != null && r8 != constraintWidget10) {
                margin += constraintAnchor6.mTarget.getMargin();
            }
            int i10 = margin;
            if (!z3 || r8 == constraintWidget10 || r8 == constraintWidget12) {
                z4 = z8;
            } else {
                z4 = z8;
                i9 = 8;
            }
            if (constraintAnchor6.mTarget != null) {
                if (r8 == constraintWidget12) {
                    constraintWidget8 = constraintWidget14;
                    constraintWidget9 = constraintWidget10;
                    linearSystem.addGreaterThan(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, i10, 6);
                } else {
                    constraintWidget8 = constraintWidget14;
                    constraintWidget9 = constraintWidget10;
                    linearSystem.addGreaterThan(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, i10, 8);
                }
                if (z10 && !z3) {
                    i9 = 5;
                }
                linearSystem.addEquality(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, i10, (r8 == constraintWidget12 && z3 && r8.isInBarrier(i8)) ? 5 : i9);
            } else {
                constraintWidget8 = constraintWidget14;
                constraintWidget9 = constraintWidget10;
            }
            if (z5) {
                if (r8.getVisibility() == 8 || r8.mListDimensionBehaviors[i8] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i7 = 0;
                } else {
                    i7 = 0;
                    linearSystem.addGreaterThan(r8.mListAnchors[i2 + 1].mSolverVariable, r8.mListAnchors[i2].mSolverVariable, 0, 5);
                }
                linearSystem.addGreaterThan(r8.mListAnchors[i2].mSolverVariable, constraintWidgetContainer.mListAnchors[i2].mSolverVariable, i7, 8);
            }
            ConstraintAnchor constraintAnchor7 = r8.mListAnchors[i2 + 1].mTarget;
            if (constraintAnchor7 != null) {
                ?? r2 = constraintAnchor7.mOwner;
                if (r2.mListAnchors[i2].mTarget != null && r2.mListAnchors[i2].mTarget.mOwner == r8) {
                    r22 = r2;
                }
            }
            if (r22 != null) {
                r8 = r22;
                z7 = z9;
            } else {
                z7 = true;
            }
            z8 = z4;
            f2 = f3;
            constraintWidget14 = constraintWidget8;
            constraintWidget10 = constraintWidget9;
            r8 = r8;
        }
        ConstraintWidget constraintWidget17 = constraintWidget14;
        float f4 = f2;
        ConstraintWidget constraintWidget18 = constraintWidget10;
        boolean z11 = z8;
        if (constraintWidget13 != null) {
            int i11 = i2 + 1;
            if (constraintWidget11.mListAnchors[i11].mTarget != null) {
                ConstraintAnchor constraintAnchor8 = constraintWidget13.mListAnchors[i11];
                if ((constraintWidget13.mListDimensionBehaviors[i8] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget13.mResolvedMatchConstraintDefault[i8] == 0) && !z3 && constraintAnchor8.mTarget.mOwner == constraintWidgetContainer) {
                    linearSystem.addEquality(constraintAnchor8.mSolverVariable, constraintAnchor8.mTarget.mSolverVariable, -constraintAnchor8.getMargin(), 5);
                } else if (z3 && constraintAnchor8.mTarget.mOwner == constraintWidgetContainer) {
                    linearSystem.addEquality(constraintAnchor8.mSolverVariable, constraintAnchor8.mTarget.mSolverVariable, -constraintAnchor8.getMargin(), 4);
                }
                linearSystem.addLowerThan(constraintAnchor8.mSolverVariable, constraintWidget11.mListAnchors[i11].mTarget.mSolverVariable, -constraintAnchor8.getMargin(), 6);
                if (z5) {
                    int i12 = i2 + 1;
                    linearSystem.addGreaterThan(constraintWidgetContainer.mListAnchors[i12].mSolverVariable, constraintWidget11.mListAnchors[i12].mSolverVariable, constraintWidget11.mListAnchors[i12].getMargin(), 8);
                }
                arrayList = chainHead.mWeightedMatchConstraintsWidgets;
                if (arrayList != null && (size = arrayList.size()) > 1) {
                    float f5 = (chainHead.mHasUndefinedWeights || chainHead.mHasComplexMatchWeights) ? f4 : chainHead.mWidgetsMatchCount;
                    float f6 = 0.0f;
                    float f7 = 0.0f;
                    ConstraintWidget constraintWidget19 = null;
                    i5 = 0;
                    while (i5 < size) {
                        ConstraintWidget constraintWidget20 = arrayList.get(i5);
                        float f8 = constraintWidget20.mWeight[i8];
                        if (f8 < f6) {
                            if (chainHead.mHasComplexMatchWeights) {
                                linearSystem.addEquality(constraintWidget20.mListAnchors[i2 + 1].mSolverVariable, constraintWidget20.mListAnchors[i2].mSolverVariable, 0, 4);
                                arrayList2 = arrayList;
                                i6 = size;
                                i5++;
                                size = i6;
                                arrayList = arrayList2;
                                f6 = 0.0f;
                            } else {
                                f8 = 1.0f;
                                f6 = 0.0f;
                            }
                        }
                        if (f8 == f6) {
                            linearSystem.addEquality(constraintWidget20.mListAnchors[i2 + 1].mSolverVariable, constraintWidget20.mListAnchors[i2].mSolverVariable, 0, 8);
                            arrayList2 = arrayList;
                            i6 = size;
                            i5++;
                            size = i6;
                            arrayList = arrayList2;
                            f6 = 0.0f;
                        } else {
                            if (constraintWidget19 != null) {
                                SolverVariable solverVariable6 = constraintWidget19.mListAnchors[i2].mSolverVariable;
                                int i13 = i2 + 1;
                                SolverVariable solverVariable7 = constraintWidget19.mListAnchors[i13].mSolverVariable;
                                SolverVariable solverVariable8 = constraintWidget20.mListAnchors[i2].mSolverVariable;
                                arrayList2 = arrayList;
                                SolverVariable solverVariable9 = constraintWidget20.mListAnchors[i13].mSolverVariable;
                                i6 = size;
                                ArrayRow createRow = linearSystem.createRow();
                                createRow.createRowEqualMatchDimensions(f7, f5, f8, solverVariable6, solverVariable7, solverVariable8, solverVariable9);
                                linearSystem.addConstraint(createRow);
                            } else {
                                arrayList2 = arrayList;
                                i6 = size;
                            }
                            f7 = f8;
                            constraintWidget19 = constraintWidget20;
                            i5++;
                            size = i6;
                            arrayList = arrayList2;
                            f6 = 0.0f;
                        }
                    }
                }
                if (constraintWidget12 == null && (constraintWidget12 == constraintWidget13 || z3)) {
                    ConstraintAnchor constraintAnchor9 = constraintWidget18.mListAnchors[i2];
                    int i14 = i2 + 1;
                    ConstraintAnchor constraintAnchor10 = constraintWidget11.mListAnchors[i14];
                    SolverVariable solverVariable10 = constraintAnchor9.mTarget != null ? constraintAnchor9.mTarget.mSolverVariable : null;
                    SolverVariable solverVariable11 = constraintAnchor10.mTarget != null ? constraintAnchor10.mTarget.mSolverVariable : null;
                    ConstraintAnchor constraintAnchor11 = constraintWidget12.mListAnchors[i2];
                    if (constraintWidget13 != null) {
                        constraintAnchor10 = constraintWidget13.mListAnchors[i14];
                    }
                    if (solverVariable10 != null && solverVariable11 != null) {
                        if (i8 == 0) {
                            f = constraintWidget17.mHorizontalBiasPercent;
                        } else {
                            f = constraintWidget17.mVerticalBiasPercent;
                        }
                        linearSystem.addCentering(constraintAnchor11.mSolverVariable, solverVariable10, constraintAnchor11.getMargin(), f, solverVariable11, constraintAnchor10.mSolverVariable, constraintAnchor10.getMargin(), 7);
                    }
                } else if (z11 || constraintWidget12 == null) {
                    int i15 = 8;
                    if (z6 && constraintWidget12 != null) {
                        boolean z12 = chainHead.mWidgetsMatchCount <= 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                        ConstraintWidget constraintWidget21 = constraintWidget12;
                        constraintWidget = constraintWidget21;
                        while (constraintWidget != null) {
                            ConstraintWidget constraintWidget22 = constraintWidget.mNextChainWidget[i8];
                            while (constraintWidget22 != null && constraintWidget22.getVisibility() == i15) {
                                constraintWidget22 = constraintWidget22.mNextChainWidget[i8];
                            }
                            if (constraintWidget == constraintWidget12 || constraintWidget == constraintWidget13 || constraintWidget22 == null) {
                                constraintWidget2 = constraintWidget21;
                                i4 = i15;
                            } else {
                                ConstraintWidget constraintWidget23 = constraintWidget22 == constraintWidget13 ? null : constraintWidget22;
                                ConstraintAnchor constraintAnchor12 = constraintWidget.mListAnchors[i2];
                                SolverVariable solverVariable12 = constraintAnchor12.mSolverVariable;
                                if (constraintAnchor12.mTarget != null) {
                                    SolverVariable solverVariable13 = constraintAnchor12.mTarget.mSolverVariable;
                                }
                                int i16 = i2 + 1;
                                SolverVariable solverVariable14 = constraintWidget21.mListAnchors[i16].mSolverVariable;
                                int margin2 = constraintAnchor12.getMargin();
                                int margin3 = constraintWidget.mListAnchors[i16].getMargin();
                                if (constraintWidget23 != null) {
                                    constraintAnchor4 = constraintWidget23.mListAnchors[i2];
                                    SolverVariable solverVariable15 = constraintAnchor4.mSolverVariable;
                                    constraintWidget3 = constraintWidget23;
                                    solverVariable2 = constraintAnchor4.mTarget != null ? constraintAnchor4.mTarget.mSolverVariable : null;
                                    solverVariable = solverVariable15;
                                } else {
                                    constraintWidget3 = constraintWidget23;
                                    constraintAnchor4 = constraintWidget13.mListAnchors[i2];
                                    solverVariable = constraintAnchor4 != null ? constraintAnchor4.mSolverVariable : null;
                                    solverVariable2 = constraintWidget.mListAnchors[i16].mSolverVariable;
                                }
                                if (constraintAnchor4 != null) {
                                    margin3 += constraintAnchor4.getMargin();
                                }
                                int i17 = margin3;
                                int margin4 = constraintWidget21.mListAnchors[i16].getMargin() + margin2;
                                int i18 = z12 ? 8 : 4;
                                if (solverVariable12 == null || solverVariable14 == null || solverVariable == null || solverVariable2 == null) {
                                    constraintWidget4 = constraintWidget3;
                                    constraintWidget2 = constraintWidget21;
                                    i4 = 8;
                                } else {
                                    constraintWidget4 = constraintWidget3;
                                    constraintWidget2 = constraintWidget21;
                                    i4 = 8;
                                    linearSystem.addCentering(solverVariable12, solverVariable14, margin4, 0.5f, solverVariable, solverVariable2, i17, i18);
                                }
                                constraintWidget22 = constraintWidget4;
                            }
                            constraintWidget21 = constraintWidget.getVisibility() != i4 ? constraintWidget : constraintWidget2;
                            constraintWidget = constraintWidget22;
                            i15 = i4;
                            i8 = i;
                        }
                        ConstraintAnchor constraintAnchor13 = constraintWidget12.mListAnchors[i2];
                        constraintAnchor = constraintWidget18.mListAnchors[i2].mTarget;
                        int i19 = i2 + 1;
                        constraintAnchor2 = constraintWidget13.mListAnchors[i19];
                        constraintAnchor3 = constraintWidget11.mListAnchors[i19].mTarget;
                        if (constraintAnchor != null) {
                            i3 = 5;
                        } else if (constraintWidget12 != constraintWidget13) {
                            i3 = 5;
                            linearSystem.addEquality(constraintAnchor13.mSolverVariable, constraintAnchor.mSolverVariable, constraintAnchor13.getMargin(), 5);
                        } else {
                            i3 = 5;
                            if (constraintAnchor3 != null) {
                                linearSystem.addCentering(constraintAnchor13.mSolverVariable, constraintAnchor.mSolverVariable, constraintAnchor13.getMargin(), 0.5f, constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, constraintAnchor2.getMargin(), 5);
                            }
                        }
                        if (constraintAnchor3 != null && constraintWidget12 != constraintWidget13) {
                            linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, -constraintAnchor2.getMargin(), i3);
                        }
                    }
                } else {
                    boolean z13 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                    ConstraintWidget constraintWidget24 = constraintWidget12;
                    ConstraintWidget constraintWidget25 = constraintWidget24;
                    while (constraintWidget25 != null) {
                        ConstraintWidget constraintWidget26 = constraintWidget25.mNextChainWidget[i8];
                        while (constraintWidget26 != null && constraintWidget26.getVisibility() == 8) {
                            constraintWidget26 = constraintWidget26.mNextChainWidget[i8];
                        }
                        if (constraintWidget26 != null || constraintWidget25 == constraintWidget13) {
                            ConstraintAnchor constraintAnchor14 = constraintWidget25.mListAnchors[i2];
                            SolverVariable solverVariable16 = constraintAnchor14.mSolverVariable;
                            SolverVariable solverVariable17 = constraintAnchor14.mTarget != null ? constraintAnchor14.mTarget.mSolverVariable : null;
                            if (constraintWidget24 != constraintWidget25) {
                                solverVariable17 = constraintWidget24.mListAnchors[i2 + 1].mSolverVariable;
                            } else if (constraintWidget25 == constraintWidget12) {
                                solverVariable17 = constraintWidget18.mListAnchors[i2].mTarget != null ? constraintWidget18.mListAnchors[i2].mTarget.mSolverVariable : null;
                            }
                            int margin5 = constraintAnchor14.getMargin();
                            int i20 = i2 + 1;
                            int margin6 = constraintWidget25.mListAnchors[i20].getMargin();
                            if (constraintWidget26 != null) {
                                constraintAnchor5 = constraintWidget26.mListAnchors[i2];
                                solverVariable3 = constraintAnchor5.mSolverVariable;
                            } else {
                                constraintAnchor5 = constraintWidget11.mListAnchors[i20].mTarget;
                                if (constraintAnchor5 != null) {
                                    solverVariable3 = constraintAnchor5.mSolverVariable;
                                } else {
                                    constraintWidget5 = constraintWidget26;
                                    solverVariable3 = null;
                                    SolverVariable solverVariable18 = constraintWidget25.mListAnchors[i20].mSolverVariable;
                                    if (constraintAnchor5 != null) {
                                        margin6 += constraintAnchor5.getMargin();
                                    }
                                    int margin7 = margin5 + constraintWidget24.mListAnchors[i20].getMargin();
                                    if (solverVariable16 != null || solverVariable17 == null || solverVariable3 == null || solverVariable18 == null) {
                                        constraintWidget6 = constraintWidget5;
                                    } else {
                                        if (constraintWidget25 == constraintWidget12) {
                                            margin7 = constraintWidget12.mListAnchors[i2].getMargin();
                                        }
                                        int i21 = margin7;
                                        constraintWidget6 = constraintWidget5;
                                        constraintWidget7 = constraintWidget24;
                                        linearSystem.addCentering(solverVariable16, solverVariable17, i21, 0.5f, solverVariable3, solverVariable18, constraintWidget25 == constraintWidget13 ? constraintWidget13.mListAnchors[i20].getMargin() : margin6, z13 ? 8 : 5);
                                        if (constraintWidget25.getVisibility() != 8) {
                                            constraintWidget25 = constraintWidget7;
                                        }
                                        constraintWidget24 = constraintWidget25;
                                        constraintWidget25 = constraintWidget6;
                                    }
                                }
                            }
                            constraintWidget5 = constraintWidget26;
                            SolverVariable solverVariable182 = constraintWidget25.mListAnchors[i20].mSolverVariable;
                            if (constraintAnchor5 != null) {
                            }
                            int margin72 = margin5 + constraintWidget24.mListAnchors[i20].getMargin();
                            if (solverVariable16 != null) {
                            }
                            constraintWidget6 = constraintWidget5;
                        } else {
                            constraintWidget6 = constraintWidget26;
                        }
                        constraintWidget7 = constraintWidget24;
                        if (constraintWidget25.getVisibility() != 8) {
                        }
                        constraintWidget24 = constraintWidget25;
                        constraintWidget25 = constraintWidget6;
                    }
                }
                if ((z11 && !z6) || constraintWidget12 == null || constraintWidget12 == constraintWidget13) {
                    return;
                }
                ConstraintAnchor constraintAnchor15 = constraintWidget12.mListAnchors[i2];
                if (constraintWidget13 == null) {
                    constraintWidget13 = constraintWidget12;
                }
                int i22 = i2 + 1;
                ConstraintAnchor constraintAnchor16 = constraintWidget13.mListAnchors[i22];
                solverVariable4 = constraintAnchor15.mTarget == null ? constraintAnchor15.mTarget.mSolverVariable : null;
                SolverVariable solverVariable19 = constraintAnchor16.mTarget == null ? constraintAnchor16.mTarget.mSolverVariable : null;
                if (constraintWidget11 == constraintWidget13) {
                    ConstraintAnchor constraintAnchor17 = constraintWidget11.mListAnchors[i22];
                    solverVariable5 = constraintAnchor17.mTarget != null ? constraintAnchor17.mTarget.mSolverVariable : null;
                } else {
                    solverVariable5 = solverVariable19;
                }
                if (constraintWidget12 == constraintWidget13) {
                    constraintAnchor15 = constraintWidget12.mListAnchors[i2];
                    constraintAnchor16 = constraintWidget12.mListAnchors[i22];
                }
                if (solverVariable4 != null || solverVariable5 == null) {
                }
                linearSystem.addCentering(constraintAnchor15.mSolverVariable, solverVariable4, constraintAnchor15.getMargin(), 0.5f, solverVariable5, constraintAnchor16.mSolverVariable, constraintWidget13.mListAnchors[i22].getMargin(), 5);
                return;
            }
        }
        if (z5) {
        }
        arrayList = chainHead.mWeightedMatchConstraintsWidgets;
        if (arrayList != null) {
            if (chainHead.mHasUndefinedWeights) {
            }
            float f62 = 0.0f;
            float f72 = 0.0f;
            ConstraintWidget constraintWidget192 = null;
            i5 = 0;
            while (i5 < size) {
            }
        }
        if (constraintWidget12 == null) {
        }
        if (z11) {
        }
        int i152 = 8;
        if (z6) {
            if (chainHead.mWidgetsMatchCount <= 0) {
            }
            ConstraintWidget constraintWidget212 = constraintWidget12;
            constraintWidget = constraintWidget212;
            while (constraintWidget != null) {
            }
            ConstraintAnchor constraintAnchor132 = constraintWidget12.mListAnchors[i2];
            constraintAnchor = constraintWidget18.mListAnchors[i2].mTarget;
            int i192 = i2 + 1;
            constraintAnchor2 = constraintWidget13.mListAnchors[i192];
            constraintAnchor3 = constraintWidget11.mListAnchors[i192].mTarget;
            if (constraintAnchor != null) {
            }
            if (constraintAnchor3 != null) {
                linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, -constraintAnchor2.getMargin(), i3);
            }
        }
        if (z11) {
        }
        ConstraintAnchor constraintAnchor152 = constraintWidget12.mListAnchors[i2];
        if (constraintWidget13 == null) {
        }
        int i222 = i2 + 1;
        ConstraintAnchor constraintAnchor162 = constraintWidget13.mListAnchors[i222];
        if (constraintAnchor152.mTarget == null) {
        }
        if (constraintAnchor162.mTarget == null) {
        }
        if (constraintWidget11 == constraintWidget13) {
        }
        if (constraintWidget12 == constraintWidget13) {
        }
        if (solverVariable4 != null) {
        }
    }
}
