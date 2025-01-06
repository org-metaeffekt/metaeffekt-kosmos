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

import com.metaeffekt.artifact.analysis.utils.FileUtils;
import com.metaeffekt.artifact.analysis.utils.InventoryUtils;
import com.metaeffekt.artifact.analysis.utils.PropertyUtils;
import com.metaeffekt.artifact.analysis.utils.StringUtils;
import com.metaeffekt.artifact.terms.TermsMetaDataResolver;
import com.metaeffekt.artifact.terms.model.Grant;
import com.metaeffekt.artifact.terms.model.NormalizationMetaData;
import com.metaeffekt.artifact.terms.model.Obligation;
import com.metaeffekt.artifact.terms.model.TermsMetaData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class TermsMetaDataStatistics {
    public void updateLicenseStatistics(File licenseMetaDataDir, File reportTargetFile) {
        NormalizationMetaData normalizationMetaData = new NormalizationMetaData(licenseMetaDataDir);
        InventoryUtils.initialize(normalizationMetaData);
        updateLicenseStatistics(normalizationMetaData, reportTargetFile);
    }

    public void updateLicenseStatistics(File reportTargetFile) {
        NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();
        InventoryUtils.initialize(normalizationMetaData);
        updateLicenseStatistics(normalizationMetaData, reportTargetFile);
    }

    public void updateLicenseStatistics_External(File licenseMetaDataDir, File reportTargetFile) {
        NormalizationMetaData normalizationMetaData = new NormalizationMetaData(licenseMetaDataDir);

        for (TermsMetaData tmd : new ArrayList<>(normalizationMetaData.getLicenseMetaDataMap().values())) {
            if (tmd.isExpression()) {
                normalizationMetaData.remove(tmd);
            }
            if (tmd.getFile().getPath().contains("ae-terms-metadata/_customer")) {
                normalizationMetaData.remove(tmd);
            }

            if (StringUtils.isEmpty(tmd.getType())) {
                if (tmd.isUnspecific()) {
                    tmd.setType("reference");
                } else {
                    tmd.setType("terms");
                }
            }
        }

        InventoryUtils.initialize(normalizationMetaData);

        updateLicenseStatistics(normalizationMetaData, reportTargetFile);
    }

    public void updateLicenseStatistics(NormalizationMetaData normalizationMetaData, File reportTargetFile) {
        String htmlTable = "<style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\n" +
                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:bold;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\n" +
                ".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
                ".tg .tg-ERROR{text-align:left;vertical-align:top;background-color:rgb(247, 163, 111)}\n" +
                ".tg .tg-WARN{text-align:left;vertical-align:top;background-color:rgb(252, 212, 101)}\n" +
                ".tg .tg-OK{text-align:left;vertical-align:top;background-color:rgb(150, 224, 160)}\n" +
                ".tg .tg-NONE{text-align:left;vertical-align:top;background-color:rgb(255,255,255)}\n" +
                "</style>\n" +
                "<table class=\"tg\">\n" +
                "  <tr>\n" +
                "    <th class=\"tg-0lax\" width=\"3%\">Number</th>\n" +
                "    <th class=\"tg-0lax\" width=\"23%\">Canonical Name</th>\n" +
                "    <th class=\"tg-0lax\" width=\"5%\">Type</th>\n" +
                "    <th class=\"tg-0lax\" width=\"10%\">SPDX Id</th>\n" +
                "    <th class=\"tg-0lax\" width=\"10%\">Short Name</th>\n" +
                "    <th class=\"tg-0lax\" width=\"10%\">Other Ids</th>\n" +
                "    <th class=\"tg-0lax\" width=\"5%\">Classification</th>\n" +
                "    <th class=\"tg-0lax\" width=\"4%\">OSI Approved</th>\n" +
                "    <th class=\"tg-0lax\" width=\"4%\"># Alternative Names</th>\n" +
                "    <th class=\"tg-0lax\" width=\"4%\"># Evidence Matches</th>\n" +
                "    <th class=\"tg-0lax\" width=\"4%\"># Modelled Grants</th>\n" +
                "    <th class=\"tg-0lax\" width=\"11%\">Matched Scancode Ids</th>\n" +
                "    <th class=\"tg-0lax\" width=\"7%\">Scancode Deviation</th>\n" +
                "  </tr>\n" +
                "  $row" +
                "</table>";

        String rowPattern = "  <tr>\n" +
                "    <td class=\"tg-0lax\">$number</td>\n" +
                "    <td class=\"$classCanonicalName\">$canonicalName</td>\n" +
                "    <td class=\"tg-0lax\">$type</td>\n" +
                "    <td class=\"$classId\"><a href=\"https://spdx.org/licenses/$spdxIdentifier.html\">$spdxIdentifier</a></td>\n" +
                "    <td class=\"$classId\">$shortName</td>\n" +
                "    <td class=\"$classOtherIds\">$otherIds</td>\n" +
                "    <td class=\"tg-0lax\">$classification</td>\n" +
                "    <td class=\"tg-0lax\">$osiApproved</td>\n" +
                "    <td class=\"$classAlternativeNames\">$alternativeNames</td>\n" +
                "    <td class=\"$classMatchCount\">$matchCount</td>\n" +
                "    <td class=\"$classMatchCount\">$grantCount</td>\n" +
                "    <td class=\"$classScanCodeIds\">$scanCodeIds</td>\n" +
                "    <td class=\"$classScanCodeDeviation\">$scanCodeDeviation</td>\n" +
                "  </tr>\n";

        List<String> canonicalNames = new ArrayList<>(normalizationMetaData.getLicenseMetaDataMap().keySet());
        Collections.sort(canonicalNames, String::compareToIgnoreCase);

        Map<String, List<TermsMetaData>> scancodeIdsToTmd = new HashMap<>();
        for (String canonicalName : canonicalNames) {
            TermsMetaData lmd = normalizationMetaData.getTermsMetaData(canonicalName);

            if (lmd.getOtherIds() != null) {
                for (String otherId : lmd.getOtherIds()) {
                    if (otherId == null) continue;
                    if (otherId.startsWith("scancode:")) {
                        String scancodeId = otherId.replace("scancode:", "");
                        List<TermsMetaData> list = scancodeIdsToTmd.get(scancodeId);
                        if (list == null) {
                            list = new ArrayList<>();
                            scancodeIdsToTmd.put(scancodeId, list);
                        }
                        list.add(lmd);
                    }
                }
            }
        }

        int i = 1;
        for (String canonicalName : canonicalNames) {
            TermsMetaData tmd = normalizationMetaData.getTermsMetaData(canonicalName);

            String htmlRow = rowPattern;
            File matchHtmlFile = new File(tmd.getFile().getParentFile(), ".meta/match.html");
            if (matchHtmlFile.exists()) {
                htmlRow = htmlRow.replace("$canonicalName", "<a href=file://" + matchHtmlFile.getAbsolutePath() + ">" +
                        InventoryUtils.escapeXml(valueOf(tmd.getCanonicalName())) + "</a>");
            } else {
                htmlRow = htmlRow.replace("$canonicalName", InventoryUtils.escapeXml(valueOf(tmd.getCanonicalName())));
            }

            if (!matchHtmlFile.exists()) {
                if (tmd.isUnspecific() || tmd.isMarker() || tmd.isExpression() || tmd.isReference() || tmd.ignore()) {
                    if (tmd.getCanonicalName().contains("license") || tmd.getCanonicalName().contains("exception")
                            || tmd.getCanonicalName().contains("plus") || tmd.getCanonicalName().contains("conditions")) {

                        if (tmd.getCanonicalName().contains("exception") && tmd.getCanonicalName().contains("exception)")) {
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
                        } else {
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-WARN");
                        }
                    } else {
                        htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
                    }
                } else {
                    if (tmd.getSpdxIdentifier() == null) {
                        htmlRow = htmlRow.replace("$classCanonicalName", "tg-WARN");
                    } else {
                        if (tmd.getCanonicalName().contains("(or any later version)")
                                || tmd.getCanonicalName().contains("(invariants)")
                                || tmd.getCanonicalName().contains("(no invariants)")) {
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
                        } else {
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-ERROR");
                        }
                    }
                }
            } else {
                htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
            }

            htmlRow = htmlRow.replace("$spdxIdentifier", valueOf(tmd.getSpdxIdentifier()));
            htmlRow = htmlRow.replace("$shortName", valueOf(tmd.getShortName()));

            String spdxIdentifier = tmd.getSpdxIdentifier();
            String shortName = tmd.getShortName();
            if (spdxIdentifier == null) spdxIdentifier = "";
            if (shortName == null) shortName = "";
            if (StringUtils.isEmpty(spdxIdentifier) && StringUtils.isEmpty(shortName)) {
                if (tmd.getCombinedWith() != null && tmd.getCombinedWith().containsKey(tmd.getCanonicalName())) {
                    htmlRow = htmlRow.replace("$classId", "tg-OK");
                } else {
                    htmlRow = htmlRow.replace("$classId", "tg-ERROR");
                }
            } else {
                if (spdxIdentifier.equalsIgnoreCase(shortName) && !shortName.contains("Exception")) {
                    htmlRow = htmlRow.replace("$classId", "tg-ERROR");
                } else {
                    if (!StringUtils.isEmpty(shortName) && shortName.toLowerCase().equals(shortName) && !canonicalName.contains(shortName) ||
                            shortName.endsWith("-plus") || shortName.toLowerCase().contains("license") || shortName.trim().contains(" ")) {
                        htmlRow = htmlRow.replace("$classId", "tg-WARN");
                    } else {
                        htmlRow = htmlRow.replace("$classId", "tg-OK");
                    }
                }
            }

            htmlRow = htmlRow.replace("$type", valueOf(tmd.getType()));
            htmlRow = htmlRow.replace("$classification", valueOf(tmd.getClassification()));
            htmlRow = htmlRow.replace("$osiApproved", valueOf(tmd.isOsiApproved()));
            List<String> matches = collectMatches(tmd);
            htmlRow = htmlRow.replace("$matchCount", valueOf(matches.size()));
            List<Grant> grants = collectGrants(tmd);
            htmlRow = htmlRow.replace("$grantCount", valueOf(grants.size()));
            htmlRow = htmlRow.replace("$alternativeNames", valueOf(tmd.getAlternativeNames().size()));
            htmlRow = htmlRow.replace("$number", valueOf(i));
            htmlRow = htmlRow.replace("$otherIds", valueOf(tmd.getOtherIds()));

            List<String> scancodeId = tmd.getOtherIds("scancode");
            if (scancodeId.size() == 0) {
                htmlRow = htmlRow.replace("$classOtherIds", "tg-0lax");
            } else {
                if (scancodeId.size() == 1) {
                    if (scancodeIdsToTmd.get(scancodeId.get(0)).size() == 1) {
                        htmlRow = htmlRow.replace("$classOtherIds", "tg-OK");
                    } else {
                        htmlRow = htmlRow.replace("$classOtherIds", "tg-WARN");
                    }
                } else {
                    htmlRow = htmlRow.replace("$classOtherIds", "tg-WARN");
                }
            }

            if (matches.size() + grants.size() == 0) {
                if (tmd.isUnspecific()) {
                    htmlRow = htmlRow.replace("$classMatchCount", "tg-OK");
                } else {
                    htmlRow = htmlRow.replace("$classMatchCount", "tg-ERROR");
                }
            } else {
                if (matches.size() + grants.size() <= 1 && !tmd.isReference() && !tmd.isMarker() && !tmd.isExpression()) {
                    htmlRow = htmlRow.replace("$classMatchCount", "tg-WARN");
                } else {
                    htmlRow = htmlRow.replace("$classMatchCount", "tg-OK");
                }
            }

            if (tmd.getAlternativeNames().size() == 0 && tmd.isUnspecific()) {
                htmlRow = htmlRow.replace("$classAlternativeNames", "tg-ERROR");
            } else {
                htmlRow = htmlRow.replace("$classAlternativeNames", "tg-OK");
            }

            File scanCodeProperties = new File(tmd.getFile().getParentFile(), ".meta/local_scancode.properties");
            if (scanCodeProperties.exists()) {
                Properties p = PropertyUtils.loadProperties(scanCodeProperties);
                String matchedScanCodeIds = p.getProperty("detected.licenses", "");
                htmlRow = htmlRow.replace("$scanCodeIds", valueOf(matchedScanCodeIds));

                String matchIds = matchedScanCodeIds.replace("[", "");
                matchIds = matchIds.replace("]", "");
                matchIds = matchIds.trim();
                String[] matchedIdsArray = matchIds.split(",");

                if (matchedIdsArray.length > 1) {
                    htmlRow = htmlRow.replace("$classScanCodeIds", "tg-ERROR");
                } else {
                    String mappedScanCodeId = tmd.getOtherId("scancode");
                    if (mappedScanCodeId == null) mappedScanCodeId = "";
                    if (mappedScanCodeId.equals(matchedIdsArray[0])) {
                        if (scancodeIdsToTmd.get(mappedScanCodeId) != null && scancodeIdsToTmd.get(mappedScanCodeId).size() == 1) {
                            htmlRow = htmlRow.replace("$classScanCodeIds", "tg-OK");
                        } else {
                            htmlRow = htmlRow.replace("$classScanCodeIds", "tg-WARN");
                        }
                    } else {
                        htmlRow = htmlRow.replace("$classScanCodeIds", "tg-WARN");
                    }
                }
            } else {
                htmlRow = htmlRow.replace("$scanCodeIds", "");
                htmlRow = htmlRow.replace("$classScanCodeIds", "tg-NONE");
            }

            // handle scancode deviations
            File scancodeDerived = new File(tmd.getFile().getParentFile(), ".meta/scancode-tmd.yaml");
            File scancodeMatched = new File(tmd.getFile().getParentFile(), ".meta/scancode-tmd_matched.yaml");

            List<String> scancodeIds = tmd.getOtherIds("scancode");
            String scancodeDerivedId = scancodeIds.stream().collect(Collectors.joining(","));

            // String scancodeDerivedId = readScancodeId(scancodeDerived);
            String scancodeMatchedId = readScancodeId(scancodeMatched);

            String scancodeDeviation =
                    valueOf(scancodeDerivedId, "<none-derived>") +
                            " / " +
                            valueOf(scancodeMatchedId, "<none-matched>");
            if (Objects.equals(scancodeMatchedId, scancodeDerivedId)) {
                if (scancodeMatchedId == null) {
                    htmlRow = htmlRow.replace("$classScanCodeDeviation", "tg-WARN");
                    htmlRow = htmlRow.replace("$scanCodeDeviation", InventoryUtils.escapeXml(scancodeDeviation));
                } else {
                    htmlRow = htmlRow.replace("$classScanCodeDeviation", "tg-OK");
                    htmlRow = htmlRow.replace("$scanCodeDeviation", InventoryUtils.escapeXml(scancodeDerivedId));

                }
            } else {
                htmlRow = htmlRow.replace("$classScanCodeDeviation", "tg-ERROR");
                htmlRow = htmlRow.replace("$scanCodeDeviation", InventoryUtils.escapeXml(scancodeDeviation));
            }

            htmlTable = htmlTable.replace("$row", htmlRow + "$row");

            i++;
        }

        // eliminate placeholder
        htmlTable = htmlTable.replace("$row", "");

        try {
            FileUtils.write(reportTargetFile, htmlTable, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateLicenseVariableStatistic(File licenseMetaDataDir, File reportTargetFile){
        NormalizationMetaData normalizationMetaData = new NormalizationMetaData(licenseMetaDataDir);
        InventoryUtils.initialize(normalizationMetaData);


        String htmlTable = "<style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\n" +
                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:bold;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\n" +
                ".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
                ".tg .tg-ERROR{text-align:left;vertical-align:top;background-color:rgb(247, 163, 111)}\n" +
                ".tg .tg-WARN{text-align:left;vertical-align:top;background-color:rgb(252, 212, 101)}\n" +
                ".tg .tg-OK{text-align:left;vertical-align:top;background-color:rgb(150, 224, 160)}\n" +
                ".tg .tg-NONE{text-align:left;vertical-align:top;background-color:rgb(255,255,255)}\n" +
                "</style>\n" +
                "<table class=\"tg\">\n" +
                "  <tr>\n" +
                "    <th class=\"tg-0lax\" width=\"3%\">Number</th>\n" +
                "    <th class=\"tg-0lax\" width=\"47%\">Canonical Name</th>\n" +
                "    <th class=\"tg-0lax\" width=\"40%\">Variables</th>\n" +
                "    <th class=\"tg-0lax\" width=\"10%\">Default</th>\n" +
                "    <th class=\"tg-0lax\" width=\"3%\">Requires Copyright</th>\n" +
                "    <th class=\"tg-0lax\" width=\"3%\">Requires License Text</th>\n" +
                "    <th class=\"tg-0lax\" width=\"3%\">Status</th>\n" +
                "  </tr>\n" +
                "  $row" +
                "</table>";

        String rowPattern = "  <tr>\n" +
                "    <td class=\"tg-0lax\">$number</td>\n" +
                "    <td class=\"$classCanonicalName\">$canonicalName</td>\n" +
                "    <td class=\"$classStatus\">$variables</td>\n" +
                "    <td class=\"$classStatus\">$default</td>\n" +
                "    <td class=\"$classStatus\">$requiresCopyright</td>\n" +
                "    <td class=\"$classStatus\">$requiresLicenseText</td>\n" +
                "    <td class=\"$classStatus\">$status</td>\n" +
                "  </tr>\n";

        List<String> canonicalNames = new ArrayList<>(normalizationMetaData.getLicenseMetaDataMap().keySet());
        Collections.sort(canonicalNames, String::compareToIgnoreCase);

        int i = 1;
        for (String canonicalName : canonicalNames) {
            TermsMetaData tmd = normalizationMetaData.getTermsMetaData(canonicalName);
            String licenseTemplate = tmd.getLicenseTemplate();
            List<String> variables = new ArrayList<>();
            if (licenseTemplate != null){
                if (licenseTemplate.contains("{{")){
                    String[] templateParts = licenseTemplate.split("}}");
                    for (String part:templateParts) {
                        int index = part.indexOf("{{");
                        if (index != -1) {
                            variables.add(part.substring(index + 2).trim());
                        }
                    }

                    String htmlRow = rowPattern;
                    File matchHtmlFile = new File(tmd.getFile().getParentFile(), ".meta/match.html");
                    if (matchHtmlFile.exists()) {
                        htmlRow = htmlRow.replace("$canonicalName", "<a href=file://" + matchHtmlFile.getAbsolutePath() + ">" +
                                InventoryUtils.escapeXml(valueOf(tmd.getCanonicalName())) + "</a>");
                    } else {
                        htmlRow = htmlRow.replace("$canonicalName", InventoryUtils.escapeXml(valueOf(tmd.getCanonicalName())));
                    }
                    htmlRow = htmlRow.replace("$number", valueOf(i));
                    htmlRow = htmlRow.replace("$variables","variables:<br>&nbsp;&nbsp;"+ String.join(":<br>&nbsp;&nbsp;", variables)+ ":");

                    boolean defaultRequired = !tmd.getAlternativeNames().isEmpty();
                    if (defaultRequired && !variables.isEmpty()){
                        htmlRow = htmlRow.replace("$default",valueOf(true));

                    }
                    else{
                        htmlRow = htmlRow.replace("$default", "");
                    }
                    htmlRow = htmlRow.replace("$requiresCopyright",valueOf(tmd.getRequiresCopyright()));
                    htmlRow = htmlRow.replace("$requiresLicenseText",valueOf(tmd.getRequiresLicenseText()));
                    if (!tmd.getStatus().isEmpty()){
                        htmlRow = htmlRow.replace("$status",tmd.getStatus().get(tmd.getStatus().size() - 1));
                        if (tmd.getStatus().get(tmd.getStatus().size() - 1).contains("reviewed")){
                            htmlRow = htmlRow.replace("$classStatus", "tg-OK");
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
                        }
                    }
                    else{
                        htmlRow = htmlRow.replace("$status","");
                    }
                    htmlTable = htmlTable.replace("$row", htmlRow + "$row");
                    i++;
                }
            }
        }
        htmlTable = htmlTable.replace("$row", "");

        try {
            FileUtils.write(reportTargetFile, htmlTable, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readScancodeId(File scancodeDerived) {
        if (!scancodeDerived.exists()) return null;
        try {
            TermsMetaData scancodeDerivedTmd = TermsMetaData.parseTermsMetaData(scancodeDerived);
            return scancodeDerivedTmd.getOtherId("scancode");
        } catch (IOException e) {
            // do nothing;
        }
        return null;
    }


    public String valueOf(Object value) {
        if (value == null) return "";
        return String.valueOf(value);
    }

    public String valueOf(String value, String defaultValue) {
        if (StringUtils.isEmpty(value)) return defaultValue;
        return value;
    }

    private List<Grant> collectGrants(TermsMetaData lmd) {
        List<Grant> matches = new LinkedList<>();
        if (lmd.getGrants() != null) {
            matches.addAll(lmd.getGrants().values());
        }
        return matches;
    }

    private List<String> collectMatches(TermsMetaData lmd) {
        List<String> matches = new LinkedList<>();
        if (lmd.getEvidence() != null && lmd.getEvidence().getMatches() != null) {
            matches.addAll(lmd.getEvidence().getMatches());
        }
        if (lmd.getGrants() != null && lmd.getGrants().values() != null) {
            for (Grant grant : lmd.getGrants().values()) {
                if (grant.getMatches() != null) {
                    matches.addAll(grant.getMatches());
                }
                if (grant.getObligations() != null) {
                    if (grant.getObligations().values() != null) {
                        for (Obligation obligation : grant.getObligations().values()) {
                            if (obligation.getMatches() != null) {
                                matches.addAll(obligation.getMatches());
                            }
                        }
                    }
                }
            }
        }
        return matches;
    }

    public void updateExceptionStatistic(File licenseMetaDataDir, File reportTargetFile){
        NormalizationMetaData normalizationMetaData = new NormalizationMetaData(licenseMetaDataDir);
        InventoryUtils.initialize(normalizationMetaData);


        String htmlTable = "<style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\n" +
                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:bold;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\n" +
                ".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
                ".tg .tg-ERROR{text-align:left;vertical-align:top;background-color:rgb(247, 163, 111)}\n" +
                ".tg .tg-WARN{text-align:left;vertical-align:top;background-color:rgb(252, 212, 101)}\n" +
                ".tg .tg-OK{text-align:left;vertical-align:top;background-color:rgb(150, 224, 160)}\n" +
                ".tg .tg-NONE{text-align:left;vertical-align:top;background-color:rgb(255,255,255)}\n" +
                "</style>\n" +
                "<table class=\"tg\">\n" +
                "  <tr>\n" +
                "    <th class=\"tg-0lax\" width=\"3%\">Number</th>\n" +
                "    <th class=\"tg-0lax\" width=\"23%\">Canonical Name</th>\n" +
                "    <th class=\"tg-0lax\" width=\"12%\">Category</th>\n" +
                "    <th class=\"tg-0lax\" width=\"15%\">SPDX Id</th>\n" +
                "    <th class=\"tg-0lax\" width=\"20%\">Short Name</th>\n" +
                "    <th class=\"tg-0lax\" width=\"10%\">Other Ids</th>\n" +
                "    <th class=\"tg-0lax\" width=\"5%\">Classification</th>\n" +
                "    <th class=\"tg-0lax\" width=\"4%\"># Alternative Names</th>\n" +
                "    <th class=\"tg-0lax\" width=\"4%\"># Evidence Matches</th>\n" +
                "    <th class=\"tg-0lax\" width=\"4%\"># Modelled Grants</th>\n" +
                "  </tr>\n" +
                "  $row" +
                "</table>";

        String rowPattern = "  <tr>\n" +
                "    <td class=\"tg-0lax\">$number</td>\n" +
                "    <td class=\"$classCanonicalName\">$canonicalName</td>\n" +
                "    <td class=\"$classCategory\">$category</td>\n" +
                "    <td class=\"$classId\"><a href=\"https://spdx.org/licenses/$spdxIdentifier.html\">$spdxIdentifier</a></td>\n" +
                "    <td class=\"$classId\">$shortName</td>\n" +
                "    <td class=\"$classOtherIds\">$otherIds</td>\n" +
                "    <td class=\"tg-0lax\">$classification</td>\n" +
                "    <td class=\"$classAlternativeNames\">$alternativeNames</td>\n" +
                "    <td class=\"$classMatchCount\">$matchCount</td>\n" +
                "    <td class=\"$classMatchCount\">$grantCount</td>\n" +
                "  </tr>\n";


        List<String> canonicalNames = new ArrayList<>();
        for (TermsMetaData licenseMetaData : normalizationMetaData.getLicenseMetaDataMap().values()) {
            if (licenseMetaData.getType() != null){
                if (licenseMetaData.getType().equals("exception")){
                    canonicalNames.add(licenseMetaData.getCanonicalName());
                }
            }
        }
        Collections.sort(canonicalNames, String::compareToIgnoreCase);

        Map<String, List<TermsMetaData>> scancodeIdsToTmd = new HashMap<>();
        int i = 1;
        for (String canonicalName : canonicalNames) {
            TermsMetaData lmd = normalizationMetaData.getTermsMetaData(canonicalName);

            if (lmd.getOtherIds() != null) {
                for (String otherId : lmd.getOtherIds()) {
                    if (otherId == null) continue;
                    if (otherId.startsWith("scancode:")) {
                        String scancodeId = otherId.replace("scancode:", "");
                        List<TermsMetaData> list = scancodeIdsToTmd.get(scancodeId);
                        if (list == null) {
                            list = new ArrayList<>();
                            scancodeIdsToTmd.put(scancodeId, list);
                        }
                        list.add(lmd);
                    }
                }
            }
        }

        for (String canonicalName : canonicalNames) {
            TermsMetaData tmd = normalizationMetaData.getTermsMetaData(canonicalName);

            String htmlRow = rowPattern;
            File matchHtmlFile = new File(tmd.getFile().getParentFile(), ".meta/match.html");
            if (matchHtmlFile.exists()) {
                htmlRow = htmlRow.replace("$canonicalName", "<a href=file://" + matchHtmlFile.getAbsolutePath() + ">" +
                        InventoryUtils.escapeXml(valueOf(tmd.getCanonicalName())) + "</a>");
            } else {
                htmlRow = htmlRow.replace("$canonicalName", InventoryUtils.escapeXml(valueOf(tmd.getCanonicalName())));
            }

            if (!matchHtmlFile.exists()) {
                if (tmd.isUnspecific() || tmd.isMarker() || tmd.isExpression() || tmd.isReference() || tmd.ignore()) {
                    if (tmd.getCanonicalName().contains("license") || tmd.getCanonicalName().contains("exception")
                            || tmd.getCanonicalName().contains("plus") || tmd.getCanonicalName().contains("conditions")) {

                        if (tmd.getCanonicalName().contains("exception") && tmd.getCanonicalName().contains("exception)")) {
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
                        } else {
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-WARN");
                        }
                    } else {
                        htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
                    }
                } else {
                    if (tmd.getSpdxIdentifier() == null) {
                        htmlRow = htmlRow.replace("$classCanonicalName", "tg-WARN");
                    } else {
                        if (tmd.getCanonicalName().contains("(or any later version)")
                                || tmd.getCanonicalName().contains("(invariants)")
                                || tmd.getCanonicalName().contains("(no invariants)")) {
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
                        } else {
                            htmlRow = htmlRow.replace("$classCanonicalName", "tg-ERROR");
                        }
                    }
                }
            } else {
                htmlRow = htmlRow.replace("$classCanonicalName", "tg-OK");
            }

            htmlRow = htmlRow.replace("$spdxIdentifier", valueOf(tmd.getSpdxIdentifier()));
            htmlRow = htmlRow.replace("$shortName", valueOf(tmd.getShortName()));
            htmlRow = htmlRow.replace("$category", valueOf(tmd.getCategory()));

            String spdxIdentifier = tmd.getSpdxIdentifier();
            String shortName = tmd.getShortName();
            if (spdxIdentifier == null) spdxIdentifier = "";
            if (shortName == null) shortName = "";
            if (StringUtils.isEmpty(spdxIdentifier) && StringUtils.isEmpty(shortName)) {
                if (tmd.getCombinedWith() != null && tmd.getCombinedWith().containsKey(tmd.getCanonicalName())) {
                    htmlRow = htmlRow.replace("$classId", "tg-OK");
                } else {
                    htmlRow = htmlRow.replace("$classId", "tg-ERROR");
                }
            } else {
                if (spdxIdentifier.equalsIgnoreCase(shortName) && !shortName.contains("Exception")) {
                    htmlRow = htmlRow.replace("$classId", "tg-ERROR");
                } else {
                    if (!StringUtils.isEmpty(shortName) && shortName.toLowerCase().equals(shortName) && !canonicalName.contains(shortName) ||
                            shortName.endsWith("-plus") || shortName.toLowerCase().contains("license") || shortName.trim().contains(" ")) {
                        htmlRow = htmlRow.replace("$classId", "tg-WARN");
                    } else {
                        htmlRow = htmlRow.replace("$classId", "tg-OK");
                    }
                }
            }

            htmlRow = htmlRow.replace("$type", valueOf(tmd.getType()));
            htmlRow = htmlRow.replace("$classification", valueOf(tmd.getClassification()));
            htmlRow = htmlRow.replace("$osiApproved", valueOf(tmd.isOsiApproved()));
            List<String> matches = collectMatches(tmd);
            htmlRow = htmlRow.replace("$matchCount", valueOf(matches.size()));
            List<Grant> grants = collectGrants(tmd);
            htmlRow = htmlRow.replace("$grantCount", valueOf(grants.size()));
            htmlRow = htmlRow.replace("$alternativeNames", valueOf(tmd.getAlternativeNames().size()));
            htmlRow = htmlRow.replace("$number", valueOf(i));
            htmlRow = htmlRow.replace("$otherIds", valueOf(tmd.getOtherIds()));

            List<String> scancodeId = tmd.getOtherIds("scancode");
            if (scancodeId.size() == 0) {
                htmlRow = htmlRow.replace("$classOtherIds", "tg-0lax");
            } else {
                if (scancodeId.size() == 1) {
                    if (scancodeIdsToTmd.get(scancodeId.get(0)).size() == 1) {
                        htmlRow = htmlRow.replace("$classOtherIds", "tg-OK");
                    } else {
                        htmlRow = htmlRow.replace("$classOtherIds", "tg-WARN");
                    }
                } else {
                    htmlRow = htmlRow.replace("$classOtherIds", "tg-WARN");
                }
            }

            if (matches.size() + grants.size() == 0) {
                if (tmd.isUnspecific()) {
                    htmlRow = htmlRow.replace("$classMatchCount", "tg-OK");
                } else {
                    htmlRow = htmlRow.replace("$classMatchCount", "tg-ERROR");
                }
            } else {
                if (matches.size() + grants.size() <= 1 && !tmd.isReference() && !tmd.isMarker() && !tmd.isExpression()) {
                    htmlRow = htmlRow.replace("$classMatchCount", "tg-WARN");
                } else {
                    htmlRow = htmlRow.replace("$classMatchCount", "tg-OK");
                }
            }

            if (tmd.getAlternativeNames().size() == 0 && tmd.isUnspecific()) {
                htmlRow = htmlRow.replace("$classAlternativeNames", "tg-ERROR");
            } else {
                htmlRow = htmlRow.replace("$classAlternativeNames", "tg-OK");
            }

            File scanCodeProperties = new File(tmd.getFile().getParentFile(), ".meta/local_scancode.properties");
            if (scanCodeProperties.exists()) {
                Properties p = PropertyUtils.loadProperties(scanCodeProperties);
                String matchedScanCodeIds = p.getProperty("detected.licenses", "");
                htmlRow = htmlRow.replace("$scanCodeIds", valueOf(matchedScanCodeIds));

                String matchIds = matchedScanCodeIds.replace("[", "");
                matchIds = matchIds.replace("]", "");
                matchIds = matchIds.trim();
                String[] matchedIdsArray = matchIds.split(",");

                if (matchedIdsArray.length > 1) {
                    htmlRow = htmlRow.replace("$classScanCodeIds", "tg-ERROR");
                } else {
                    String mappedScanCodeId = tmd.getOtherId("scancode");
                    if (mappedScanCodeId == null) mappedScanCodeId = "";
                    if (mappedScanCodeId.equals(matchedIdsArray[0])) {
                        if (scancodeIdsToTmd.get(mappedScanCodeId) != null && scancodeIdsToTmd.get(mappedScanCodeId).size() == 1) {
                            htmlRow = htmlRow.replace("$classScanCodeIds", "tg-OK");
                        } else {
                            htmlRow = htmlRow.replace("$classScanCodeIds", "tg-WARN");
                        }
                    } else {
                        htmlRow = htmlRow.replace("$classScanCodeIds", "tg-WARN");
                    }
                }
            } else {
                htmlRow = htmlRow.replace("$scanCodeIds", "");
                htmlRow = htmlRow.replace("$classScanCodeIds", "tg-NONE");
            }

            // handle scancode deviations
            File scancodeDerived = new File(tmd.getFile().getParentFile(), ".meta/scancode-tmd.yaml");
            File scancodeMatched = new File(tmd.getFile().getParentFile(), ".meta/scancode-tmd_matched.yaml");

            String scancodeDerivedId = readScancodeId(scancodeDerived);
            String scancodeMatchedId = readScancodeId(scancodeMatched);

            String scancodeDeviation =
                    valueOf(scancodeDerivedId, "<none-derived>") +
                            " / " +
                            valueOf(scancodeMatchedId, "<none-matched>");
            if (Objects.equals(scancodeMatchedId, scancodeDerivedId)) {
                if (scancodeMatchedId == null) {
                    htmlRow = htmlRow.replace("$classScanCodeDeviation", "tg-WARN");
                    htmlRow = htmlRow.replace("$scanCodeDeviation", InventoryUtils.escapeXml(scancodeDeviation));
                } else {
                    htmlRow = htmlRow.replace("$classScanCodeDeviation", "tg-OK");
                    htmlRow = htmlRow.replace("$scanCodeDeviation", InventoryUtils.escapeXml(scancodeDerivedId));

                }
            } else {
                htmlRow = htmlRow.replace("$classScanCodeDeviation", "tg-ERROR");
                htmlRow = htmlRow.replace("$scanCodeDeviation", InventoryUtils.escapeXml(scancodeDeviation));
            }

            htmlTable = htmlTable.replace("$row", htmlRow + "$row");

            i++;
        }

        // eliminate placeholder
        htmlTable = htmlTable.replace("$row", "");

        try {
            FileUtils.write(reportTargetFile, htmlTable, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void LicenseDataStatus() {
        NormalizationMetaData normalizationMetaData = TermsMetaDataResolver.get();
        float countAllLicenses = 0;
        float countLicensesWithData = 0;
        for (TermsMetaData tmd : new ArrayList<>(normalizationMetaData.getLicenseMetaDataMap().values())) {
            if (tmd.getType() != null && !tmd.getType().isEmpty()) continue;
            countAllLicenses++;
            if (tmd.getRequiresCopyright() != null) {
                countLicensesWithData++;
            }
        }
        log.info("{}", countAllLicenses);
        log.info("{}", countLicensesWithData);
        log.info("{}", countLicensesWithData / countAllLicenses * 100 + "%");
    }

}

