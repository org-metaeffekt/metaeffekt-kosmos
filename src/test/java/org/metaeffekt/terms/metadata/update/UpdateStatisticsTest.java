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

import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import com.metaeffekt.artifact.terms.model.TermsMetaData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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

    @Test
    public void countTerms() throws IOException {
        NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();


        // note that we count all licenses and expression; including the filtered ones

        int countLicenses = 0;
        int countExpressions = 0;
        int countMarker = 0;
        int countExceptions = 0;
        int countReferences = 0;
        int countModifiers = 0;
        int countRestrictions = 0;
        int countStructural = 0;

        for (TermsMetaData tmd : normalizationMetaData.getLicenseMetaDataMap().values()) {

            String type = tmd.getType();
            if (type == null) type = "terms";

            switch (type) {
                case "terms" : countLicenses++; break;
                case "marker" : countMarker++; break;
                case "expression" : countExpressions++; break;
                case "reference" : countReferences++; break;
                case "exception" : countExceptions++; break;
                case "restriction" : countRestrictions++; break;
                case "modifier" : countModifiers++; break;
                case "structural" : countStructural++; break;
                default:
                    throw new IllegalStateException("Unknown Type: [" + type + "]");
            }
        }

        System.out.println("Terms: " + countLicenses);
        System.out.println("Exceptions: " + countExceptions);
        System.out.println("Modifiers: " + countModifiers);
        System.out.println("Restrictions: " + countRestrictions);
        System.out.println("Expressions: " + countExpressions);
        System.out.println("Marker: " + countMarker);
        System.out.println("References: " + countReferences);
        System.out.println("Structural: " + countStructural);
    }

}
