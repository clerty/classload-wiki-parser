#!/bin/bash

  for request in "Hypertext_Transfer_Protocol,973449318" "Document_Object_Model,927574984" "CSS,985983322"; do
  IFS="," read name revision <<< "${request}"
  echo "Checking request $name for revision $revision"

  java -jar main-module/build/libs/main-module.jar $name $revision
  for plugin in "CounterPlugin" "FrequencyDictionaryPlugin"; do
    if ! diff --ignore-all-space \
      --ignore-case \
      integration-test/results/$name/results-$plugin.txt results/$name/results-$plugin.txt; then
      exit 1
    fi
  done
done