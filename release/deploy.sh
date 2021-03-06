#!/usr/bin/env bash
set -x

openssl aes-256-cbc -K $encrypted_ee8fb316b8fd_key -iv $encrypted_ee8fb316b8fd_iv -in release/codesigning.asc.enc -out release/codesigning.asc -d
gpg --fast-import release/codesigning.asc

./mvnw org.codehaus.mojo:versions-maven-plugin:2.1:set -DgenerateBackupPoms=false -DnewVersion=$TRAVIS_TAG
./mvnw deploy -P release --settings release/settings.xml
