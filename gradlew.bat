@ECHO OFF
REM Lightweight Gradle wrapper for environments where binary artifacts are not supported.
REM Requires Gradle to be installed and available on PATH.

gradle -v >NUL 2>&1
IF %ERRORLEVEL% NEQ 0 (
  ECHO ERROR: 'gradle' is not installed or not on PATH.
  EXIT /B 1
)

gradle %*
