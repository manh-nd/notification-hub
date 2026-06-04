#!/usr/bin/env sh

# Lightweight Gradle wrapper for environments where binary artifacts are not supported.
# Requires `gradle` to be installed and available on PATH.

if ! command -v gradle >/dev/null 2>&1; then
  echo "ERROR: 'gradle' is not installed or not on PATH." >&2
  exit 1
fi

exec gradle "$@"
