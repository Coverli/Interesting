:: @echo off

set /p msg=�������ύע��:
git add .
git commit -m %msg%
git branch -M main
git push -u origin main
echo ���ͳɹ�����%msg%��

SET daoTime=3
:dao
set /a daoTime=daoTime-1
ping -n 2 -w 500 127.1>nul
:: cls
echo �ϴ�Git��� ,����ʱ�˳�: %daoTime%��
if %daoTime%==0 (exit) else (goto dao)
