<div align="center">

# 哔哩漫游X (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-1.23.3-green)](https://github.com/min09577/BiliRoamingX)
[![AI](https://img.shields.io/badge/AI-Assisted-purple)](https://github.com/min09577/BiliRoamingX)

</div>

基于 BiliRoamingX v1.23.3 的 AI 增强自用版本。

## 版本信息

- 上游版本: BiliRoamingX v1.23.3 (2024-09-20)
- 适配版本: 哔哩哔哩 8.14.0 (arm64-v8a)
- 最后更新: 2026-05-27
- 维护方式: AI 辅助开发

## 相比原版的改进

### 已完成
- 修复本地构建问题 (添加 libbiliroamingx.so 到 patches 资源)
- 完整源码推送，可独立编译

### 计划中
- 适配更新版本哔哩哔哩
- 更多去广告规则
- 直播弹幕增强
- 视频下载优化
- 性能优化
- UI 美化

## 原版功能

- 解除番剧区域限制
- 自由移除页面组件
- 自定义直播、视频默认清晰度
- 自定义播放速度
- 字幕样式调整，翻译、保存及导入
- 调整 APP 显示大小
- 自由复制评论及视频信息
- 双指缩放视频填充屏幕
- 调用外部下载器保存视频
- 加回频道功能
- 自动领取B币券
- 分享链接净化
- 推荐、热门、动态过滤
- 开屏页背景色跟随深色模式

## 构建方法

环境要求: JDK 17, Android SDK, NDK 26.3, CMake 3.22.1

`
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
./gradlew dist
java -jar revanced-cli.jar patch --merge integrations.apk --patch-bundle patches.jar --signing-levels 1,2,3 bilibili-8.14.0.apk
`

## 更新日志

### 2026-05-27
- Fork 自 BiliRoamingX/BiliRoamingX
- 修复本地构建问题
- 添加 libbiliroamingx.so 到 patches 资源目录

## 注意事项

- 这是自用版本，仅供学习研究
- 原始项目由 Kofua (github.com/zjns) 开发
- 原项目已基本停更，此 fork 用于继续维护

## 相关链接

- 原版 BiliRoamingX: https://github.com/BiliRoamingX/BiliRoamingX
- 哔哩哔哩: https://www.bilibili.com
- ReVanced: https://revanced.app