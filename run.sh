#!/usr/bin/env bash

docker run --rm -v ${PWD}:/opt \
-it apple scala -classpath  target/scala-2.12/common-element-assembly-0.1.jar com.apple.CommonElementsDriver \
--first-file-path input/first_input_file.txt \
--second-file-path input/second_input_file.txt \
--output-file-path output/common_elements.txt \
--bloom-filter-length 400 \
--bloom-filter-num-hash 600 \
--bloom-filter-algorithm SHA1