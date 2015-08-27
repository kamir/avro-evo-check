# avro-evo-check
__Avro-evo-check__ is a tool to verify the compatibility of Avro-schema files (up to three).

Simply test, if three Avro schema files are write-read compatible each other in one command:

> __aec.sh__ ascvFile1 ascvFile2 ascvFile3

Example:
> __aec.sh__ src evo1 evo2

The tool tells you this:

> Identity (src): COMPATIBLE

> src  -> evo1 : INCOMPATIBLE

> evo1 -> src  : COMPATIBLE

> src  -> evo2 : INCOMPATIBLE

> evo2 -> src  : INCOMPATIBLE
