@echo off

set WORK_DIR=c:\JavaTest
set USER= kero

echo ---------------------------------------------------------------------------
echo ExeControlのテスト用にアクセス権を指定したフォルダやファイルを作成します。
echo ---------------------------------------------------------------------------

echo.
echo 作業フォルダとして、%WORK_DIR%を作成します。JUnitのコードと一致していることを確認してください。
echo.

mkdir %WORK_DIR%

cd %WORK_DIR%

mkdir アクセス権がないフォルダ

echo a > %WORK_DIR%\アクセス権がないフォルダ\a.txt

echo.
echo %WORK_DIR%\アクセス権がないフォルダ\a.txtを作成しました。
echo.
echo %WORK_DIR%\アクセス権がないフォルダのアクセス権をアクセス禁止に変更します。
icacls %WORK_DIR%\アクセス権がないフォルダ /grant kero:N

pause