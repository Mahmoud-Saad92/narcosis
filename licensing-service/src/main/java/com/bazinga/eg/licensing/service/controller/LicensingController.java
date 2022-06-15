package com.bazinga.eg.licensing.service.controller;

import com.bazinga.eg.licensing.service.model.License;
import com.bazinga.eg.licensing.service.service.LicensingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(value = "api/v1/organization/{organizationId}/license")
public class LicensingController {

    private final LicensingService licensingService;

    @Autowired
    public LicensingController(LicensingService licensingService) {
        this.licensingService = licensingService;
    }

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") String organizationId,
                                              @PathVariable("licenseId") String licenseId) {

        return licensingService
                .getLicense(licenseId, organizationId)
                .map(v -> new ResponseEntity<>(v, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createLicence(@PathVariable("organizationId") String organizationId,
                                                @RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {

        return licensingService
                .createLicense(license, organizationId, locale)
                .map(v -> new ResponseEntity<>(v, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PutMapping
    public ResponseEntity<String> updateLicence(@PathVariable("organizationId") String organizationId,
                                                @RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {

        return licensingService
                .updateLicense(license, organizationId, locale)
                .map(v -> new ResponseEntity<>(v, HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicence(@PathVariable("organizationId") String organizationId,
                                                @PathVariable("licenseId") String licenseId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return licensingService
                .deleteLicense(licenseId, organizationId, locale)
                .map(v -> new ResponseEntity<>(v, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED));
    }

}
