package org.apache.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

/* loaded from: classes5.dex */
class ArithmeticOperators {
    ArithmeticOperators() {
    }

    /* loaded from: classes5.dex */
    static class Abs implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            if (popNumber instanceof Integer) {
                executionContext.getStack().push(Integer.valueOf(Math.abs(popNumber.intValue())));
            } else {
                executionContext.getStack().push(Float.valueOf(Math.abs(popNumber.floatValue())));
            }
        }
    }

    /* loaded from: classes5.dex */
    static class Add implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            Number popNumber2 = executionContext.popNumber();
            if ((popNumber2 instanceof Integer) && (popNumber instanceof Integer)) {
                long longValue = popNumber2.longValue() + popNumber.longValue();
                if (longValue < -2147483648L || longValue > 2147483647L) {
                    executionContext.getStack().push(Float.valueOf((float) longValue));
                    return;
                } else {
                    executionContext.getStack().push(Integer.valueOf((int) longValue));
                    return;
                }
            }
            executionContext.getStack().push(Float.valueOf(popNumber2.floatValue() + popNumber.floatValue()));
        }
    }

    /* loaded from: classes5.dex */
    static class Atan implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            float degrees = ((float) Math.toDegrees((float) Math.atan2(executionContext.popReal(), executionContext.popReal()))) % 360.0f;
            if (degrees < 0.0f) {
                degrees += 360.0f;
            }
            executionContext.getStack().push(Float.valueOf(degrees));
        }
    }

    /* loaded from: classes5.dex */
    static class Ceiling implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            if (popNumber instanceof Integer) {
                executionContext.getStack().push(popNumber);
            } else {
                executionContext.getStack().push(Float.valueOf((float) Math.ceil(popNumber.doubleValue())));
            }
        }
    }

    /* loaded from: classes5.dex */
    static class Cos implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.cos(Math.toRadians(executionContext.popReal()))));
        }
    }

    /* loaded from: classes5.dex */
    static class Cvi implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Integer.valueOf(executionContext.popNumber().intValue()));
        }
    }

    /* loaded from: classes5.dex */
    static class Cvr implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf(executionContext.popNumber().floatValue()));
        }
    }

    /* loaded from: classes5.dex */
    static class Div implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            executionContext.getStack().push(Float.valueOf(executionContext.popNumber().floatValue() / popNumber.floatValue()));
        }
    }

    /* loaded from: classes5.dex */
    static class Exp implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.pow(executionContext.popNumber().doubleValue(), executionContext.popNumber().doubleValue())));
        }
    }

    /* loaded from: classes5.dex */
    static class Floor implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            if (popNumber instanceof Integer) {
                executionContext.getStack().push(popNumber);
            } else {
                executionContext.getStack().push(Float.valueOf((float) Math.floor(popNumber.doubleValue())));
            }
        }
    }

    /* loaded from: classes5.dex */
    static class IDiv implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            int popInt = executionContext.popInt();
            executionContext.getStack().push(Integer.valueOf(executionContext.popInt() / popInt));
        }
    }

    /* loaded from: classes5.dex */
    static class Ln implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.log(executionContext.popNumber().doubleValue())));
        }
    }

    /* loaded from: classes5.dex */
    static class Log implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.log10(executionContext.popNumber().doubleValue())));
        }
    }

    /* loaded from: classes5.dex */
    static class Mod implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            int popInt = executionContext.popInt();
            executionContext.getStack().push(Integer.valueOf(executionContext.popInt() % popInt));
        }
    }

    /* loaded from: classes5.dex */
    static class Mul implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            Number popNumber2 = executionContext.popNumber();
            if ((popNumber2 instanceof Integer) && (popNumber instanceof Integer)) {
                long longValue = popNumber2.longValue() * popNumber.longValue();
                if (longValue >= -2147483648L && longValue <= 2147483647L) {
                    executionContext.getStack().push(Integer.valueOf((int) longValue));
                    return;
                } else {
                    executionContext.getStack().push(Float.valueOf((float) longValue));
                    return;
                }
            }
            executionContext.getStack().push(Float.valueOf((float) (popNumber2.doubleValue() * popNumber.doubleValue())));
        }
    }

    /* loaded from: classes5.dex */
    static class Neg implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            if (popNumber instanceof Integer) {
                if (popNumber.intValue() == Integer.MIN_VALUE) {
                    executionContext.getStack().push(Float.valueOf(-popNumber.floatValue()));
                    return;
                } else {
                    executionContext.getStack().push(Integer.valueOf(-popNumber.intValue()));
                    return;
                }
            }
            executionContext.getStack().push(Float.valueOf(-popNumber.floatValue()));
        }
    }

    /* loaded from: classes5.dex */
    static class Round implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            if (popNumber instanceof Integer) {
                executionContext.getStack().push(Integer.valueOf(popNumber.intValue()));
            } else {
                executionContext.getStack().push(Float.valueOf((float) Math.round(popNumber.doubleValue())));
            }
        }
    }

    /* loaded from: classes5.dex */
    static class Sin implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            executionContext.getStack().push(Float.valueOf((float) Math.sin(Math.toRadians(executionContext.popReal()))));
        }
    }

    /* loaded from: classes5.dex */
    static class Sqrt implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            float popReal = executionContext.popReal();
            if (popReal < 0.0f) {
                throw new IllegalArgumentException("argument must be nonnegative");
            }
            executionContext.getStack().push(Float.valueOf((float) Math.sqrt(popReal)));
        }
    }

    /* loaded from: classes5.dex */
    static class Sub implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Stack<Object> stack = executionContext.getStack();
            Number popNumber = executionContext.popNumber();
            Number popNumber2 = executionContext.popNumber();
            if ((popNumber2 instanceof Integer) && (popNumber instanceof Integer)) {
                long longValue = popNumber2.longValue() - popNumber.longValue();
                if (longValue < -2147483648L || longValue > 2147483647L) {
                    stack.push(Float.valueOf((float) longValue));
                    return;
                } else {
                    stack.push(Integer.valueOf((int) longValue));
                    return;
                }
            }
            stack.push(Float.valueOf(popNumber2.floatValue() - popNumber.floatValue()));
        }
    }

    /* loaded from: classes5.dex */
    static class Truncate implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext executionContext) {
            Number popNumber = executionContext.popNumber();
            if (popNumber instanceof Integer) {
                executionContext.getStack().push(Integer.valueOf(popNumber.intValue()));
            } else {
                executionContext.getStack().push(Float.valueOf((int) popNumber.floatValue()));
            }
        }
    }
}
