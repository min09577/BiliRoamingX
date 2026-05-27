<div align="center">

# BiliRoamingX (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-1.23.3-green)](https://github.com/min09577/BiliRoamingX)
[![AI](https://img.shields.io/badge/AI-Assisted-purple)](https://github.com/min09577/BiliRoamingX)

</div>

---

## [English](#english) | [中文](#中文) | [日本語](#日本語) | [한국어](#한국어)

---

<a name="english"></a>
## English

### BiliRoamingX - AI Enhanced Fork

Based on BiliRoamingX v1.23.3, enhanced with AI-assisted development.

**Target Version:** Bilibili 8.95.0 (Android 64-bit)

**Status:** 60+ patches working, 13 fingerprint patches under adaptation

**Verified:** Tested on MuMu emulator, all core features working

### Working Features
- Unblock bangumi region limit
- Remove page components (ads, VIP section, follow button)
- Custom playback speed
- Custom live/video default quality
- Subtitle style adjustment
- Pinch to zoom video to fill screen
- Auto claim B-coins
- Share link purification
- Recommend, popular, dynamic filtering
- Dark mode splash background
- Copy comments and video info
- Call external downloader

### Adapting Features (13 patches)
- Remember playback speed
- Video default quality
- Subtitle import and save
- Hardware codec
- Pegasus hook (recommend feed)
- Music notification
- Toast customization
- Cache redirect
- BL route intercept
- Forbid live room auto float
- Force comment time navigable
- Trial quality
- Moss (protobuf hook)

### Build
```
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
./gradlew dist
java -jar revanced-cli.jar patch --merge integrations.apk --patch-bundle patches.jar --signing-levels 1,2,3 bilibili-8.95.0.apk
```

### Changelog
#### 2026-05-27
- Fork from BiliRoamingX/BiliRoamingX
- Fix local build (add libbiliroamingx.so)
- Fix theseus_playlist_default_order for 8.95.0
- Fix PlayerSettingHelperFingerprint
- 60+ patches verified working on emulator

---

<a name="zh"></a>
## Chinese / 中文

### 哔哩漫游X - AI 增强版

基于 BiliRoamingX v1.23.3 的 AI 增强自用版本。

**适配版本：** 哔哩哔哩 8.95.0 (安卓64位)

**状态：** 60+ 个补丁可用，13 个 fingerprint 补丁适配中

**验证：** 在 MuMu 模拟器测试通过，核心功能正常

### 已实现功能
- 解除番剧区域限制
- 移除页面组件（广告、VIP 区域、关注按钮）
- 自定义播放速度
- 自定义直播/视频默认清晰度
- 字幕样式调整
- 双指缩放视频填充屏幕
- 自动领取B币券
- 分享链接净化
- 推荐、热门、动态过滤
- 深色模式开屏背景
- 复制评论和视频信息
- 调用外部下载器

### 构建方法
```
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
./gradlew dist
java -jar revanced-cli.jar patch --merge integrations.apk --patch-bundle patches.jar --signing-levels 1,2,3 bilibili-8.95.0.apk
```

### 更新日志
#### 2026-05-27
- 从 BiliRoamingX/BiliRoamingX fork
- 修复本地构建（添加 libbiliroamingx.so）
- 修复 8.95.0 的 theseus_playlist_default_order
- 修复 PlayerSettingHelperFingerprint
- 60+ 补丁在模拟器上验证通过

---

<a name="ja"></a>
## Japanese / 日本語

### BiliRoamingX - AI強化版

BiliRoamingX v1.23.3をベースにしたAI強化版。

**対象バージョン:** ビリビリ 8.95.0 (Android 64ビット)

**ステータス:** 60+パッチ動作中、13のfingerprintパッチ適応中

**検証済み:** MuMuエミュレーターでテスト済み、コア機能が正常動作

### 動作中の機能
- 番組制限解除
- ページコンポーネント削除（広告、VIPエリア、フォローボタン）
- カスタム再生速度
- カスタムライブ/ビデオデフォルト画質
- 字幕スタイル調整
- ピンチズームでビデオ全画面表示
- Bコイン自動受け取り
- 共有リンク净化
- おすすめ、人気、動態フィルタリング
- ダークモードスプラッシュ背景
- コメントとビデオ情報のコピー
- 外部ダウンローダー呼び出し

### ビルド方法
```
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
./gradlew dist
java -jar revanced-cli.jar patch --merge integrations.apk --patch-bundle patches.jar --signing-levels 1,2,3 bilibili-8.95.0.apk
```

### 変更履歴
#### 2026-05-27
- BiliRoamingX/BiliRoamingXからfork
- ローカルビルド修正（libbiliroamingx.so追加）
- 8.95.0のtheseus_playlist_default_order修正
- PlayerSettingHelperFingerprint修正
- 60+パッチがエミュレーターで検証済み

---

<a name="ko"></a>
## Korean / 한국어

### BiliRoamingX - AI 강화판

BiliRoamingX v1.23.3을 기반으로 한 AI 강화 버전.

**대상 버전:** 빌리빌리 8.95.0 (안드로이드 64비트)

**상태:** 60+ 패치 작동 중, 13개 fingerprint 패치 적응 중

**검증 완료:** MuMu 에뮬레이터에서 테스트 완료, 핵심 기능 정상 작동

### 작동 중인 기능
- 방송 제한 해제
- 페이지 컴포넌트 제거 (광고, VIP 영역, 팔로우 버튼)
- 사용자 정의 재생 속도
- 사용자 정의 라이브/비디오 기본 화질
- 자막 스타일 조절
- 핀치 줌으로 비디오 전체 화면
- B코인 자동 수령
- 공유 링크 정화
- 추천, 인기, 동적 필터링
- 다크 모드 스플래시 배경
- 댓글 및 비디오 정보 복사
- 외부 다운로더 호출

### 빌드 방법
```
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
./gradlew dist
java -jar revanced-cli.jar patch --merge integrations.apk --patch-bundle patches.jar --signing-levels 1,2,3 bilibili-8.95.0.apk
```

### 변경 이력
#### 2026-05-27
- BiliRoamingX/BiliRoamingX에서 fork
- 로컬 빌드 수정 (libbiliroamingx.so 추가)
- 8.95.0의 theseus_playlist_default_order 수정
- PlayerSettingHelperFingerprint 수정
- 60+ 패치가 에뮬레이터에서 검증됨

---

## Notes / 说明 / 注意事項 / 참고사항

- Personal use version, for study and research only
- Original project by Kofua (github.com/zjns)

## Links / 链接 / リンク / 링크

- Original: https://github.com/BiliRoamingX/BiliRoamingX
- Bilibili: https://www.bilibili.com
- ReVanced: https://revanced.app
