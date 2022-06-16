package com.bazinga.eg.licensing.service.util;

@FunctionalInterface
public interface LicenseOperator<T, I, R> {

    R apply(T t, I i);
}
