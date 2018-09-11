#!/usr/bin/env bash

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

set -e



cd configserver;                note "Build configserver...";         ./gradlew clean build; cd -
cd eureka-naming-server;                note "Build EurekaNammingServer...";         ./gradlew clean build; cd -
cd cerveja-ciclic;                note "Build CervejaCiclic...";         ./gradlew clean build; cd -
cd spotifyplaylist;                note "Build spotifyplaylist...";         ./gradlew clean build; cd -


find . -name *SNAPSHOT.jar -exec du -h {} \;