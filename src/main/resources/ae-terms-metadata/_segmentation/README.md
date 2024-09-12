# Segmentation

Contains segmentation mappings that are not directly associated with a license.

## Note

Current segmentation may be too offensive. With full segmentation activated we also segment the original license files. 
Effectively this can result in incomplete matches. However, this is regarded as acceptable for the time being. The 
approach is to revert false segments incrementally.

Currently false segmentation is identified in .meta/segments.txt for each license meta data folder. with the current 
set of data we have are around 400 false segments.

## Corrective Actions

In case the segmentation splits text that need to be preserved, it is possible to revert the segmentation of adjacent 
paragraphs:

    segmentation:
      revert: [
        {
          match: "Copyright",
          matchNext: "Permission to use"
        },
        {
          match: "Copyright",
          matchNext: "Redistribution and use"
        }
      ]
