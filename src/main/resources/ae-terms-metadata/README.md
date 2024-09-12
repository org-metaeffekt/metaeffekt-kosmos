# ae-terms-metadata Structure

The TMD folder contains the license database of metæffekt universe.

The metadata is organised in a dedicated folder structure, which is primarily alphabetically sorted.
In addition, TMD contains several special folders, starting with an underscore *(_)*. 

## Special folders
* [_customer](_customer/README.md)
* [_exceptions](_exceptions/README.md)
* [_excludes](_excludes/README.md)
* [_external](_external/README.md)
* [_marker](_marker/README.md)
* [_normalization](_normalization/README.md)
* [_modifier](_modifier/README.md)
* [_multi-license](_multi-license/README.md)
* [_numbered-0-9](_numbered-0-9/README.md)
* [_premature](_premature/README.md)
* [_restriction](_restriction/README.md)
* [_segmentation](_segmentation/README.md)
* [_scancode-multi-licenses](_scancode-multi-licenses/README.md)

## Substructures

### License folders
Each license consists of a minimum of a single file called license.meta.yaml.
In this file all information digested from a license or a group of licenses is aggregated.

### .meta folders
In addition, a "license" folder, containing the license text and a ".meta" folder can be present.
The **".meta"** folder contains runtime information.

## Examples:

The Apache License 2.0 can be found under:
    
    ae-terms-metadata > a > apache > 2.0

The Classpath Exception can be found under:

    ae-terms-metadata > _exceptions > classpath-exception

## Conventions

### Folder structure conventions

The folder structure follows certain conventions:
* all lower case; use '-' to separate different name parts.
* Organise folder by the first letter
* Organise Terms Metadata by provider and version if several versions exist; e.g. check structure in license-metadata/a/apache

### `canonicalName` conventions
 
* Variants are expressed in the canonicalName either by adding a modifier in brackets or by numbering a variant (var-001)

### `combinedWith` conventions
* Exceptions use ... 
