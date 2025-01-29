@echo off
setlocal enabledelayedexpansion

rem Loop 200 times and send requests in parallel
for /l %%i in (1,1,200) do (
    start /b curl --location --request PUT "http://localhost:9000/employees/incrementBalance/1"
)

rem Wait for all processes to finish before exiting
pause
