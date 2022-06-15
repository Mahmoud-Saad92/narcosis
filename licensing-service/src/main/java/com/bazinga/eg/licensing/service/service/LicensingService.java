package com.bazinga.eg.licensing.service.service;

import com.bazinga.eg.licensing.service.model.License;

import java.util.Locale;
import java.util.Optional;

public interface LicensingService {
    Optional<License> getLicense(String licenseId, String organizationId);

    Optional<String> createLicense(License license, String organizationId, Locale locale);

    Optional<String> updateLicense(License license, String organizationId, Locale locale);

    Optional<String> deleteLicense(String licenseId, String organizationId, Locale locale);
}
