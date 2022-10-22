:: @echo off

set /p msg=请输入提交注释:
git add .
git commit -m %msg%
git branch -M main
git push -u origin main
echo 推送成功：【%msg%】

SET daoTime=3
:dao
set /a daoTime=daoTime-1
ping -n 2 -w 500 127.1>nul
:: cls
echo 上传Git完成 ,倒计时退出: %daoTime%秒
if %daoTime%==0 (exit) else (goto dao)
