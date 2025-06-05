/*
 * Copyright 2021-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.metaeffekt.terms.metadata.validation;

import com.metaeffekt.artifact.analysis.common.tmd.validation.AbstractTermsMetaDataConventionTest;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TermsMetaDataConventionTest extends AbstractTermsMetaDataConventionTest {

    private static final Logger LOG = LoggerFactory.getLogger(TermsMetaDataConventionTest.class);

    private static final File tmdBaseDir = new File("src/main/resources/ae-terms-metadata");
    private static final NormalizationMetaData nmd = new NormalizationMetaData(tmdBaseDir);

    /**
     * {@inheritDoc}
     */
    @Test
    public void testUniqueness_derivedIds() {
        testUniqueness_derivedIds(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testUniqueness_shortNames() {
        testUniqueness_shortNames(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testUniqueness_canonicalNames() {
        testUniqueness_canonicalNames(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testUniqueness_alternativeNames() {
        testUniqueness_alternativeNames(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testUniqueness_spdxIds() {
        testUniqueness_spdxIds(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testUniqueness_licenseMetaDataTmdValues() {
        testUniqueness_licenseMetaDataTmdValues(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testUniqueness_canonicalNamesHistory() {
        testUniqueness_canonicalNamesHistory(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testUniqueness_betweenHistoryAndActiveCanonicalNames() {
        testUniqueness_betweenHistoryAndActiveCanonicalNames(nmd);
    }


    /**
     * {@inheritDoc}
     */
    @Test
    public void validateNames() {
        validateNames(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void validateCanonicalNameHistory() {
        final NormalizationMetaData nmd = TermsMetaDataResolver.get();
        validateCanonicalNameHistory(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testFolderConventions() throws Exception {
        final NormalizationMetaData nmd = TermsMetaDataResolver.get();
        testFolderConventions(tmdBaseDir, nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testAlternativeNames() {
        final NormalizationMetaData nmd = TermsMetaDataResolver.get();

        Set<String> knownShorts = new HashSet<String>(Arrays.asList(
                "terms", ".1", ".0", "v1", "v2", "v3",
                "http:", "https:", "-", "_", "asl", "apache", "gpl", "beerware",
                "licence.visualidiot.com", "文鼎公众授权书", "文鼎公眾授權書",
                "0bsd", "giftware", "license", " ",
                "lppl", "newsletr", "tosl", "eplv´´",
                "fsfap", "fpl", "epics", "rscpl",
                "mit/x11", "mitnfa", "wtfpl", "linuxhowtos",
                "gdcl", "alexisisaac.net",
                "de.mach",
                "5a002", "5a003", "5a004", "5b002", "5e002", "5e992", "ear15", "ear740",
                "5d002", "5d992", "cryptography", "cryptographic", "ddtc", "ear99", "usml", "itar", "ccl", "ccats",
                "nlr", "wassenaar", "eccn", "tsu",
                "gfdl",
                "apafml", "APAFML",
                "bsd5", "bsd3",
                "linuxbios", "clartistic", "freertos", "sissl",
                "www.qhull.org",
                "mplus",
                "nlpl",
                "developercertificate.org"));

        testAlternativeNames(nmd, knownShorts);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testCombinerIntegrity() {
        testCombinerIntegrity(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testRepresentedAsIntegrity() {
        testRepresentedAsIntegrity(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testMultiLicensesReference() {
        final File multiLicenseDir = new File("src/main/resources/ae-terms-metadata/_multi-license/");
        final NormalizationMetaData multiLicenseMetaData = new NormalizationMetaData(multiLicenseDir);

        testMultiLicensesReference(nmd, multiLicenseMetaData, multiLicenseDir);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testRepresentedAsLicenseAttachments() {
        testRepresentedAsLicenseAttachments(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testIncompleteLicenseDetails() {
        testIncompleteLicenseDetails(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkLicenseTemplates() {
        final String[] tags = {"<lq>", "<codeph>", "<ol>", "<li>", "<p>", "<i>"};
        checkLicenseTemplates(nmd, tags);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkExpressionOfEvidences() {
        checkExpressionOfEvidences(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void combinedWithUsedAsDisplayName() {
        combinedWithUsedAsDisplayName(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkVariables() {
        checkVariables(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkShortNameExceptions() {
        checkShortNameExceptions(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Disabled // FIXME-KKL: may not apply to kosmos flow
    @Test
    public void reportRedundantClassifications() throws IOException {
        reportRedundantClassifications(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkReferences() {
        checkReferences(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testStandardVariableSets() {
        testStandardVariableSets(nmd);
    }


    /**
     * {@inheritDoc}
     */
    @Test
    public void checkTypesInDedicatedFolders() {
        HashMap<String, String> folders = new HashMap<>();
        folders.put("src/main/resources/ae-terms-metadata/_exceptions/", "exception");
        folders.put("src/main/resources/ae-terms-metadata/_marker", "marker");
        folders.put("src/main/resources/ae-terms-metadata/_excludes", "reference");
        folders.put("src/main/resources/ae-terms-metadata/_multi-license", "expression");
        boolean failure = false;

        checkTypesInDedicatedFolders(folders);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkScancodeReferencesExist() throws IOException {
        checkScancodeReferencesExist(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkBaseTermsIntegrity() {
        checkBaseTermsIntegrity(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void checkForExceptionsInWrongFolder() {
        checkForExceptionsInWrongFolder(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Disabled("There is no strict rule for uniqueness but the test is useful for manually finding issues.")
    public void testUniqueness_otherIds() {
        testUniqueness_otherIds(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Disabled
    @Test
    public void validateOSISPDXIdentifier() {
        validateOSISPDXIdentifier(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Disabled
    @Test
    public void checkScancodeReferencesAreUnique() {
        checkScancodeReferencesAreUnique(nmd);
    }

    /**
     * {@inheritDoc}
     */
    @Disabled
    @Test
    public void checkConsistencyUnspecificLicenses() {
        checkConsistencyUnspecificLicenses(nmd);
    }
}
