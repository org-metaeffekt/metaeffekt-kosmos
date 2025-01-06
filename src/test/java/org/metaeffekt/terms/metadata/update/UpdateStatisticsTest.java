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
package org.metaeffekt.terms.metadata.update;

import org.junit.jupiter.api.Test;

import java.io.File;

public class UpdateStatisticsTest {

    @Test
    public void updateStatistics() {
        new TermsMetaDataStatistics().updateLicenseStatistics(
                new File("target/ae-workbench-report/ae-license-summary.html"));
    }

    @Test
    public void updateAllStatistics() {
        new TermsMetaDataStatistics().updateLicenseStatistics(
                new File("src/main/resources/ae-terms-metadata"),
                new File("target/ae-workbench-report/ae-license-summary.html"));

        new TermsMetaDataStatistics().updateLicenseStatistics_External(
                new File("src/main/resources/ae-terms-metadata"),
                new File("target/ae-workbench-report/ae-license-summary_external.html"));

        new TermsMetaDataStatistics().updateLicenseVariableStatistic(
                new File("src/main/resources/ae-terms-metadata"),
                new File("target/ae-workbench-report/ae-license-variable-info.html"));

        new TermsMetaDataStatistics().updateExceptionStatistic(
                new File("src/main/resources/ae-terms-metadata"),
                new File("target/ae-workbench-report/ae-exception-info.html"));
    }
}
