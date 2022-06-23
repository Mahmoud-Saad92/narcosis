package com.bazinga.eg.licensing.service.service;

import com.bazinga.eg.licensing.service.model.LicenseDTO;

import java.util.Locale;
import java.util.Optional;

public interface LicensingService {
    Optional<LicenseDTO> getLicense(String licenseId, String organizationId);

    Optional<String> createLicense(LicenseDTO licenseDTO, String organizationId, Locale locale);

    Optional<String> updateLicense(LicenseDTO licenseDTO, String organizationId, Locale locale);

    Optional<String> deleteLicense(String licenseId, String organizationId, Locale locale);
}
