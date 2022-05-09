# NanoBot

<p align="center">
    <a href="https://github.com/Yoiul/NanoBot">
        <img width="400" src="https://i0.hdslb.com/bfs/album/af62186ee3d972b18a613dead7310415bd9471e8.jpg" alt="NanoBot">
    </a>
</p>

> 基于Mirai-core、Spring-Boot、Mysql/SQLite、Mybatis-Plus的QQ机器人

<p align="center">
<a href="https://github.com/Yoiul/NanoBot/releases"><img alt="GitHub release" src="https://img.shields.io/badge/releases-1.0.0-green"/></a>
</p>

------------------------------

## 简介

Nano一词取自动漫《日常》中角色[**东云名乃**](https://baike.baidu.com/item/%E4%B8%9C%E4%BA%91%E5%90%8D%E4%B9%83)

## 快速开始

配置文件：
下载[配置文件](https://nanobot-upload.oss-cn-hangzhou.aliyuncs.com/application.yml)，在jar包所在目录中新建config文件夹，将yml配置文件放置在config文件夹中，并修改配置

~~~yaml
nano:
  bot:
    account: #填入QQ账号
    password: #填入QQ密码
    admin: #填入管理员QQ账号
    protocol: android_phone #登陆协议，默认
    botLog: true #是否使用NanoBot日志
    networkLog: true #是否使用NanoBot网络日志
    head: . #指令头，可以配置为[.][#][!]等
    name: Nano #机器人名称
    groups: 4321,1234 #群号
~~~

由于企鹅服务器更新，滑块登陆模块无法使用，推荐使用如下方式解决

> 找一台安卓手机，下载 [TxCaptchaHelper](https://github.com/mzdluo123/TxCaptchaHelper/releases) apk，并且安装
>
> 使用下方启动命令启动项目，查看运行日志最后一行，获取`https://ssl.captcha.qq.com/……apptype=2`复制完整url路径，填写入apk中获取token，并输入token到控制台后回车，验证并登陆Bot



启动命令：请替换[your.ersion]为对应版本号，执行如下命令

```bash
java -Dmirai.slider.captcha.supported -jar NanoBot-[your.ersion].jar
```

## 帮助文档

NanoBot目前为测试实验阶段，基础功能完善中……

## 许可证

[![license](https://img.shields.io/badge/license-AGPLv3-orange)](https://github.com/Yoiul/NanoBot/blob/master/LICENSE)

NanoBot使用 AGPLv3 协议开源，请遵守此协议使用。

本程序基于Mirai实现QQ机器人功能，[Mirai GitHub仓库](https://github.com/mamoe/mirai)，在此特别感谢。
