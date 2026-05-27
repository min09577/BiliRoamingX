<div align="center">

# BiliRoamingX (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-1.23.3-green)](https://github.com/min09577/BiliRoamingX)
[![AI](https://img.shields.io/badge/AI-Assisted-purple)](https://github.com/min09577/BiliRoamingX)

</div>

BiliRoamingX v1.23.3 AI Enhanced Fork

## Version Info

- Upstream: BiliRoamingX v1.23.3 (2024-09-20)
- Target: Bilibili 8.95.0 (arm64-v8a)
- Last Update: 2026-05-27
- Maintenance: AI-assisted development

## Improvements

### Done
- Fix local build issue (add libbiliroamingx.so to patches resources)
- Fix theseus_playlist_default_order missing in 8.95.0
- 60+ patches working with 8.95.0

### Working (13 patches need fingerprint update)
- BL route intercept
- Cache redirect
- Forbid live room auto float
- Force comment time navigable
- Force hardware codec
- Moss (protobuf hook)
- Music notification
- Pegasus hook (recommend feed)
- Remember playback speed
- Subtitle import and save
- Toast customization
- Trial quality
- Video default quality

## Original Features

- Unblock bangumi region limit
- Remove page components
- Custom live/video default quality
- Custom playback speed
- Subtitle style adjustment, translation, save and import
- Adjust APP display size
- Copy comments and video info freely
- Pinch to zoom video to fill screen
- Call external downloader to save video
- Add back channel function
- Auto claim B-coins
- Share link purification
- Recommend, popular, dynamic filtering
- Splash background follow dark mode

## Build

Requirements: JDK 17, Android SDK, NDK 26.3, CMake 3.22.1

```
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
./gradlew dist
java -jar revanced-cli.jar patch --merge integrations.apk --patch-bundle patches.jar --signing-levels 1,2,3 bilibili-8.95.0.apk
```

## Changelog

### 2026-05-27
- Fork from BiliRoamingX/BiliRoamingX
- Fix local build issue
- Add libbiliroamingx.so to patches resources
- Fix theseus_playlist_default_order for 8.95.0
- 60+ patches working with 8.95.0

## Notes

- Personal use version, for study and research only
- Original project by Kofua (github.com/zjns)
- Original project basically stopped updating

## Links

- Original BiliRoamingX: https://github.com/BiliRoamingX/BiliRoamingX
- Bilibili: https://www.bilibili.com
- ReVanced: https://revanced.app
