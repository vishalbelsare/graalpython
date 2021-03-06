/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oracle.graal.python.builtins.objects.cext;

import com.oracle.graal.python.builtins.objects.cext.NativeWrappers.PythonNativeWrapper;
import com.oracle.truffle.api.interop.ForeignAccess;
import com.oracle.truffle.api.interop.TruffleObject;

/**
 * Wrappers for methods used by native code.
 */
public abstract class ManagedMethodWrappers {

    public abstract static class MethodWrapper extends PythonNativeWrapper {
        private final Object method;

        public MethodWrapper(Object method) {
            this.method = method;
        }

        @Override
        public Object getDelegate() {
            return method;
        }

        static boolean isInstance(TruffleObject o) {
            return o instanceof MethodWrapper;
        }

        @Override
        public ForeignAccess getForeignAccess() {
            return ManagedMethodWrappersMRForeign.ACCESS;
        }
    }

    static class MethKeywords extends MethodWrapper {

        public MethKeywords(Object method) {
            super(method);
        }
    }

    static class MethVarargs extends MethodWrapper {

        public MethVarargs(Object method) {
            super(method);
        }
    }

    /**
     * Creates a wrapper for signature {@code meth(*args, **kwargs)}.
     */
    public static MethodWrapper createKeywords(Object method) {
        return new MethKeywords(method);
    }

    /**
     * Creates a wrapper for signature {@code meth(*args)}.
     */
    public static MethodWrapper createVarargs(Object method) {
        return new MethVarargs(method);
    }

}
