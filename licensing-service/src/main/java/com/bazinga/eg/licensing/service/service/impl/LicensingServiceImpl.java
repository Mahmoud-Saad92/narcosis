package com.bazinga.eg.licensing.service.service.impl;

import com.bazinga.eg.licensing.service.model.LicenseDTO;
import com.bazinga.eg.licensing.service.service.LicensingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

@Slf4j
@Service
public class LicensingServiceImpl implements LicensingService {

    private final MessageSource messages;

    private final Supplier<Integer> integerSupplier = () -> new Random().nextInt(1000);

    @Autowired
    public LicensingServiceImpl(MessageSource messages) {
        this.messages = messages;
    }

    @Override
    public Optional<LicenseDTO> getLicense(String licenseId, String organizationId) {
        log.info("retrieving request for licence with id {} on organization with id {}", licenseId, organizationId);

        return Optional.of(
                LicenseDTO
                        .builder()
                        .id(integerSupplier.get())
                        .licenseId(licenseId)
                        .organizationId(organizationId)
                        .description("Software product")
                        .productName("OStock")
                        .licenseType("full")
                        .build()
        );
    }

    @Override
    public Optional<String> createLicense(LicenseDTO licenseDTO, String organizationId, Locale locale) {
        log.info("creating request for a new licence on organization with id {}", organizationId);

        Optional<String> responseMsg = Optional.empty();

        if (Objects.nonNull(licenseDTO)) {
            licenseDTO.setOrganizationId(organizationId);
//            responseMsg = Optional.of(String.format("This is the post and the object is: %s", license));
            responseMsg = Optional.of(String.format(messages.getMessage("license.create.message", null, locale), licenseDTO));

            log.info("successfully creating a new licence with id {} on organization with id {}", licenseDTO.getId(), organizationId);
        }

        return responseMsg;
    }

    @Override
    public Optional<String> updateLicense(LicenseDTO licenseDTO, String organizationId, Locale locale) {
        log.info("updating request for a licence on organization with id {}", organizationId);

        Optional<String> responseMsg = Optional.empty();

        if (Objects.nonNull(licenseDTO)) {
            licenseDTO.setOrganizationId(organizationId);
//            responseMsg = Optional.of(String.format("This is the put and the object is: %s", license));
            responseMsg = Optional.of(String.format(messages.getMessage("license.update.message", null, locale), licenseDTO));

            log.info("Successfully updating licence with id {} on organization with id {}", licenseDTO.getId(), organizationId);
        }

        return responseMsg;
    }

    @Override
    public Optional<String> deleteLicense(String licenseId, String organizationId, Locale locale) {
        log.info("deleting request for licence with id {} on organization with id {}", licenseId, organizationId);

//        Optional<String> responseMsg = Optional.of(String.format("Deleting license with id %s for organization %s", licenseId, organizationId));
        Optional<String> responseMsg = Optional.of(String.format(messages.getMessage("license.delete.message", null, locale), licenseId, organizationId));

        log.info("successfully deleting licence with id {} on organization with id {}", licenseId, organizationId);

        return responseMsg;
    }
}
