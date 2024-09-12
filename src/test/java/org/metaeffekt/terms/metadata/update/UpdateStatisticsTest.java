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
