#!/bin/bash

set -euo pipefail

./gradlew clean build assembleAndroidTest
