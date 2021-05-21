git init
git commit .
git branch -M master
git remote add origin1  git@github.com:wmyBigdata-1/flume-interceptor.git
git push -u origin1 master

注意：
使用git来上传本地项目的话，首先配置免密登录，然后在创建远程仓库，提交项目，然后在连接远程库
连接的时候使用ssh，要不然会报错
