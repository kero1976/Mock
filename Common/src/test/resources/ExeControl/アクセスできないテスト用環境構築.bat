@echo off

set WORK_DIR=c:\JavaTest
set USER= kero

echo ---------------------------------------------------------------------------
echo ExeControl�̃e�X�g�p�ɃA�N�Z�X�����w�肵���t�H���_��t�@�C�����쐬���܂��B
echo ---------------------------------------------------------------------------

echo.
echo ��ƃt�H���_�Ƃ��āA%WORK_DIR%���쐬���܂��BJUnit�̃R�[�h�ƈ�v���Ă��邱�Ƃ��m�F���Ă��������B
echo.

mkdir %WORK_DIR%

cd %WORK_DIR%

mkdir �A�N�Z�X�����Ȃ��t�H���_

echo a > %WORK_DIR%\�A�N�Z�X�����Ȃ��t�H���_\a.txt

echo.
echo %WORK_DIR%\�A�N�Z�X�����Ȃ��t�H���_\a.txt���쐬���܂����B
echo.
echo %WORK_DIR%\�A�N�Z�X�����Ȃ��t�H���_�̃A�N�Z�X�����A�N�Z�X�֎~�ɕύX���܂��B
icacls %WORK_DIR%\�A�N�Z�X�����Ȃ��t�H���_ /grant kero:N

pause