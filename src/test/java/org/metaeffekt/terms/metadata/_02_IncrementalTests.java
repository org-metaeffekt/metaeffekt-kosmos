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
package org.metaeffekt.terms.metadata;

import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.metaeffekt.terms.metadata.update.UpdateStatisticsTest;
import org.metaeffekt.terms.metadata.update.UpdateTermsMetadataSpdxTest;
import org.metaeffekt.terms.metadata.validation.CanonicalNameIntegrityTest;
import org.metaeffekt.terms.metadata.validation.TermsMetaDataConventionTest;
import org.metaeffekt.terms.metadata.validation.TermsMetaDataIntegrityTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class _02_IncrementalTests {
    private static final Logger LOG = LoggerFactory.getLogger(_02_IncrementalTests.class);

    /**
     * Validates all rules regarding the terms metadata.
     *
     * @throws IOException
     */
    @Test
    // ###################################################### Run often (after changes are complete)
    public void testAll() throws IOException {
        LOG.info("Loading TermsMetaData...");
        TermsMetaDataResolver.get();
        LOG.info("Loading TermsMetaData completed.");

        final LauncherDiscoveryRequest request =
                LauncherDiscoveryRequestBuilder.request()
                        .selectors(selectClass(TermsMetaDataConventionTest.class))
                        .build();

        final Launcher launcher = LauncherFactory.create();
        final SummaryGeneratingListener listener = new SummaryGeneratingListener();

        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        List<TestExecutionSummary.Failure> failures = summary.getFailures();

        if (!failures.isEmpty()) {
            for (TestExecutionSummary.Failure failure : failures) {
                LOG.error("Failure in " + TermsMetaDataConventionTest.class.getName() + " - " + failure.getTestIdentifier());
                if (failure.getException() != null) {
                    Arrays.stream(failure.getException().getStackTrace()).forEach(l -> LOG.error("   " + l.toString()));
                }
            }
            Assertions.fail("Check for previous failure logs.");
        }

        new UpdateTermsMetadataSpdxTest().checkReferencedSpdxLicenseExists();
        new TermsMetaDataIntegrityTest().reportAndRemoveObsoleteMetaFolder();
        new TermsMetaDataIntegrityTest().reportAndRemoveEmptyFolders();
        new TermsMetaDataIntegrityTest().testReferencesAndMasksSuppliedWithSufficientContext();
        new CanonicalNameIntegrityTest().testCanonicalNames();

        new UpdateStatisticsTest().updateStatistics();
    }

    @Test
    public void updateStatistics() {
        new UpdateStatisticsTest().updateStatistics();
    }
}
