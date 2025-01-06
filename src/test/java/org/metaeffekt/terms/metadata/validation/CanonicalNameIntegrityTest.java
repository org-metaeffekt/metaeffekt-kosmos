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

import com.metaeffekt.artifact.analysis.common.tmd.validation.AbstractCanonicalNameIntegrityTest;
import com.metaeffekt.artifact.analysis.utils.FileUtils;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class CanonicalNameIntegrityTest extends AbstractCanonicalNameIntegrityTest{

    /**
     * {@inheritDoc}
     */
    @Disabled // creation is not implicit; this is a helper method
    @Test
    public void createCanonicalNamesFile() throws IOException {
        final File canonicalNamesFile = new File("src/main/resources/ae-terms-metadata/_external/history/all-canonical-names.txt");
        FileUtils.delete(canonicalNamesFile);
        final NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();

        createCanonicalNamesFile(canonicalNamesFile, normalizationMetaData);
    }

    /**
     * {@inheritDoc}
     */
    @Test
    public void testCanonicalNames() throws IOException {

        final File canonicalNamesFile = new File("src/main/resources/ae-terms-metadata/_external/history/all-canonical-names.txt");
        final File excludedCanonicalNamesFile = new File("src/main/resources/ae-terms-metadata/_external/history/excluded-canonical-names.txt");
        final NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();

        testCanonicalNames(canonicalNamesFile, excludedCanonicalNamesFile, normalizationMetaData);
    }
}
