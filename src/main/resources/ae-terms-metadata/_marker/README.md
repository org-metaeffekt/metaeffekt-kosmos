# Marker

Markers are used to give hints for the result interpretation and provide additional information about the licenses.

## Modelling

- document purpose of marker in `comments`
- use one-of matching when dealing with marker modelling

consider major license categories while modelling such as:
Apache, GPL, AGPL, LGPL, MIT, BSD, MPL, Artistic License, EUPL, Creative Commons, Permission Terms, Redistribution Terms, and individual cases
## Categories

This is a concept and not final! - most markers are not even modelled yet, specific use case to be determined.


### informational markers:

    No Patent Rights Granted Marker
    Patent Information Marker
    Facebook Patent Rights Grant 2.0
    EULA Marker
    Import/Export Marker
    No Effect on Binary Marker
    Ownership Marker
    Incompatible with Secondary License Marker
    Secondary License Marker
    General Terms Matches Marker
    Extended Source Obligation Marker
    Pending License Marker 
    No Warranty Marker
    Linked License Marker
    Changed License Marker
    Public Domain Fallback Marker
    Authors View Marker
    US Copyright Act Marker
    Patent Pending Marker
    Cryptographic Content Marker
    OEM Marker
    Other Copyleft Licenses Marker
    Other Permissive Licenses Marker

### proprietary (commercial) markers:

    Trademark Marker
    Commercial Marker
    Non-commercial Marker
    Proprietary Marker
    Commercial Option Marker

### obligation markers:

    Do Not Promote Marker
    Display Obligation Marker
    Printed Document Attribution Marker
    Preserve Text Obligation Marker

### restriction/restrictive markers:

    Export Restriction Note (US)
    Commercial Trademark Restriction Marker
    No Eligible Content Marker
    Written Permission Required Marker
    Written Permission Required (for promotion/endorsement) Marker
    Not for Redistribution Marker
    No Military Use Marker

### markers designated for specific origins:

    Origin Marker (Sun Glassfish)
    ASM INRIA Marker (France Telekom)
    LLVM Exception Handling Marker
    Oracle Classpath Exception Designation
    Oracle Licensing Information
    CUPS Marks Marker 
    GSA ADP Schedule Contract

### markers designated for specific development boundary conditions:

    Developer Certificate of Origin 1.1
    Developer Certificate of Origin 1.0
    Generic Contributor License Agreement Marker

### technical markers / issue indication:

    Licensing Option (..Marker) (technical, evidence-based)
    Indicated Exception Marker (technical)
    Incomplete Match Marker (not modelled with evidences; technical or combined)
