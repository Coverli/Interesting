@echo off

set /p msg=请输入提交仓库地址(HTTPS):
git add .
git commit -m "bat批处理自动推送:%date:~0,10%,%time:~0,8%"
git remote add origin %msg%
git branch -M main
git push -u origin main
@echo 推送成功

SET daoTime=3
:dao
set /a daoTime=daoTime-1
ping -n 2 -w 500 127.1>nul
echo 上传Git完成 ,倒计时退出: %daoTime%秒
if %daoTime%==0 (exit) else (goto dao)
