#!/bin/bash

set -euo pipefail

openssl enc -aes256 -md sha256 -e -salt -in "${1}" -out "${1}.enc"
