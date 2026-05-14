package org.metaeffekt.terms.metadata.update;

import com.metaeffekt.artifact.analysis.utils.FileUtils;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import com.metaeffekt.artifact.terms.model.TermsMetaData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CountingTest {

    @Test
    public void countTerms() throws IOException {

        final NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();

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

        System.out.println("Terms + Exceptions: " + (countLicenses + countExceptions));
        System.out.println("Terms + Exceptions + Modifiers + Restrictions: " + (countLicenses + countExceptions + countModifiers + countRestrictions));
    }

}
