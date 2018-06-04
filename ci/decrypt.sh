#!/bin/bash

set -euo pipefail

for f in boilerplate.jks.enc keystore.properties.enc google-play-publisher.json.enc; do
    openssl enc -aes256 -md sha256 -d -salt -pass pass:${ENC_PASSPHRASE} -in "${f}" -out "${f%.*}"
done
