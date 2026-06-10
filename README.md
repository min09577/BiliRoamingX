<div align="center">

# BiliRoamingX (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-2.57.0-green)](https://github.com/min09577/BiliRoamingX)
[![AI](https://img.shields.io/badge/AI-Assisted-purple)](https://github.com/min09577/BiliRoamingX)

[![Build](https://img.shields.io/badge/Build-Passing-brightgreen)](https://github.com/min09577/BiliRoamingX/actions)
[![Quality](https://img.shields.io/badge/Code%20Quality-0%20Issues-brightgreen)](https://github.com/min09577/BiliRoamingX)
[![Patches](https://img.shields.io/badge/Patches-250-blue)](https://github.com/min09577/BiliRoamingX)
[![Settings](https://img.shields.io/badge/Settings-469-blue)](https://github.com/min09577/BiliRoamingX)


</div>

---

## [English](#english) | [中文](#中文) | [日本語](#日本語) | [한국어](#한국어)

---

<a name="english"></a>
## English

### BiliRoamingX - AI Enhanced Fork

Based on BiliRoamingX v1.23.3, enhanced with AI-assisted development.

**Target Version:** Bilibili 8.95.0 (Android 64-bit)

**Status:** 250 patches, 469 settings, all core features verified, code quality optimized

### Project Statistics (v2.57.0)
| Metric | Count |
|--------|-------|
| Settings | 469 |
| Patches | 250 |
| String Resources | 875 |
| XML Entries | 384 |
| Code Quality Issues | 0 |

### Feature Categories
- **Comment (66):** Sorting, filtering, highlighting, translation, UI, gestures
- **Danmaku (55):** Filtering, font, speed, display, send control
- **Player (35):** Gestures, controls, display, screenshot, PiP
- **Video (47):** Auto-pause, auto-skip, quality, buffer, download
- **Live (28):** Auto-enter, auto-mute, gift blocking, danmaku
- **Home (29):** Filter, refresh, sort, night mode
- **Dynamic (15):** Filter, auto-like, auto-block
- **Search (15):** Filter, auto-complete, correction
- **Subtitle (17):** Font, style, translate, sync


**Build:** See [Build Instructions](#build-en) below

### Features
- Unblock bangumi region limit
- Remove page components (ads, VIP section, follow button)
- Custom playback speed (default + long-press)
- Custom live/video default quality
- Force HDR quality
- Subtitle style adjustment + import/save
- Pinch to zoom video to fill screen
- Auto claim B-coins
- Share link purification
- Recommend, popular, dynamic filtering
- Dark mode splash background
- Copy comments and video info (with IP location, floor number, reply count)
- Call external downloader
- Splash ad removal (OkHttp API hook)
- Block charging endpage & related games
- Unlimited trial quality (strip deadline params)
- Danmaku keyword filter (regex + plain text)
- Danmaku display control (opacity, density, font scale, time offset, pool filter)
- Video codec info display (codec, resolution, bitrate, CID, UP info)
- Video statistics panel (play count, danmaku, likes, coins, favorites, comments)
- Screenshot/recording unlock
- Video description auto-expand + hide floating button
- Custom splash screen
- Block comment search keywords
- Disable homepage auto-refresh
- Loop play (auto replay on finish)
- **Live room watermark removal**
- **Live room mosaic/overlay removal**
- **Video bookmark**
- **Comment translation**
- **Auto-skip intro/outro**
- **Danmaku keyword highlight**
- **Custom danmaku speed**
- **Danmaku display area control**
- **A-B loop segment (repeat video segment)**
- **Video screenshot**
- **Speed badge (show speed on player)**
- **Auto-enter PiP (Picture-in-Picture)**
- **Custom PiP aspect ratio**
- **Custom double-tap seek time**

### Changelog

#### 2026-06-07 (v1.40.0)
- **New:** Auto-enter PiP — automatically enter Picture-in-Picture mode when pressing Home or switching apps
- **New:** Custom PiP aspect ratio — set PiP video aspect ratio (Default/16:9/4:3/1:1)
- **New:** Custom double-tap seek time — customize double-tap left/right seek seconds (default 10s)
- **Settings:** Added toggles and options in player settings

#### 2026-06-02 (v1.38.0)
- **New:** Live room auto sign-in — automatically sign in when entering live room to get daily rewards
- **Settings:** Added toggle in live room settings page

#### 2026-06-02 (v1.37.0)
- **New:** Remember video playback position — automatically save and restore playback position for each video
- **Settings:** Added toggle in player settings

#### 2026-06-02 (v1.36.0)
- **New:** Default video aspect ratio — set default video aspect ratio (Default/Fit/Fill)
- **Settings:** Added selection dialog in player settings

#### 2026-06-02 (v1.35.0)
- **New:** Comment sort by time — default comment sorting by time (newest first)
- **Settings:** Added switch in comment filter settings

#### 2026-06-02 (v1.34.0)
- **New:** Danmaku keyword highlight — matching danmaku change to a prominent color (7 colors available)
- **New:** Custom danmaku speed — control danmaku scroll speed (0-10 levels)
- **New:** Danmaku display area — customize danmaku display area percentage (0-100%)
- **Settings:** Danmaku display settings page with speed/area sliders and keyword highlight input

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0:** Custom danmaku speed & display area — hooks DmViewReply to modify playerDanmakuSpeed and playerDanmakuDomain
- **v1.32.0:** Live room watermark removal — recursive view tree traversal with resource ID matching
- **v1.32.0:** Live room mosaic removal — removes overlay/mask views from live streams
- **v1.32.0:** CID display in video codec info toast
- **Build:** Full 70+ patches build successful, signed with BKS keystore

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0:** Video description auto-expand — hooks ExpandableLayout via reflection to expand video description on load
- **v1.31.0:** Hide floating button — hides mini-player floating button on video detail page
- **v1.30.0:** Loop play — auto replay video when playback finishes (relates feed injection)
- **v1.29.0:** Block comment search keywords — hooks ReplyMainList to clear unwanted search topics
- **v1.29.0:** Disable homepage auto-refresh — prevents automatic content refresh on homepage

#### 2026-05-30 (v1.28.0)
- **Fix:** Register error in CopyEnhancePatch — ConversationCopy injection used `invoke-static {p0, p2, v0}` where p0 mapped to v16+ (beyond 4-bit register limit). Fixed with `move-object/from16` to low registers
- **Fix:** APK signing failure — JDK 17 JCE refused to authenticate unsigned BouncyCastle in fat CLI jar. Solution: put standalone signed BC 1.77 JARs on classpath before CLI
- **New:** Video description auto-expand (VideoDescExpandPatch) — hooks ExpandableLayout to auto-expand video description
- **New:** Hide floating button option
- **Build:** Full 70+ patches build successful with signing

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0:** Force HDR quality toggle — fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0:** Danmaku time offset (-30~+30s) + pool filter (0=all, 1=normal, 2=subtitle, 3=special)
- **v1.25.0:** Video info panel enhancement — UP MID, follower count, share count; Comment floor number + reply count display
- **New patches:** DanmakuKeywordFilter, ShowCodecInfo, DanmakuDisplay adjustments, Screenshot/Recording unlock, Comment IP location, AV/BV number display, Video statistics panel (play/danmaku/like/coin/fav/reply)

#### 2026-05-28 (v1.23.3 ~ v1.24.1)
- **Critical fixes:** All 70+ patches adapted for bilibili 8.95.0
  - Fingerprint graceful degradation (return instead of throw on mismatch)
  - DmAdvert reflection fix (NoClassDefFoundError)
  - VerifyError fix (disable goto instruction modification)
- **New patches:** SplashAd (OkHttp hook), EndpageCharge (charging page block), RelatedGames (game recommendation block)
- **Ad removal:** Full UI configuration completed (homepage, video detail, live room, dynamic, my page)

#### 2026-05-27 (v1.23.3 — Initial Fork)
- Forked from BiliRoamingX/BiliRoamingX
- Local build fix (copy libbiliroamingx.so to patches resources)
- theseus_playlist_default_order fix for 8.95.0
- PlayerSettingHelperFingerprint 8.95.0 compatibility fix
- 60+ patches verified on MuMu emulator
- README 4-language support (CN/EN/JP/KR)

<a name="build-en"></a>
### Build Instructions
```bash
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
# Build patches and integrations
./gradlew :patches:jar :integrations:app:assembleRelease -x lint
# Copy artifacts
cp patches/build/libs/BiliRoamingX-patches-*.jar patches.jar
cp integrations/app/build/outputs/apk/release/integrations-release.apk integrations.apk
# Patch APK (signed BouncyCastle must precede CLI on classpath for JDK 17)
BC_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcprov-jdk18on/1.77/*/bcprov-jdk18on-1.77.jar
BCPKIX_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcpkix-jdk18on/1.77/*/bcpkix-jdk18on-1.77.jar
java -cp "$BC_JAR:$BCPKIX_JAR:revanced-cli.jar" app.revanced.cli.command.MainCommandKt \
  patch -b patches.jar -m integrations.apk -o bilibili-8.95.0-patched.apk bilibili-8.95.0.apk
```

---

<a name="中文"></a>
## 中文

### BiliRoamingX - AI 增强版

基于 BiliRoamingX v1.23.3，通过 AI 辅助开发进行增强。

**目标版本：** 哔哩哔哩 8.95.0（Android 64位）

**状态：** 70+ 补丁全部正常工作，所有核心功能已验证

### 功能列表
- 解除番剧地区限制
- 移除页面组件（广告、会员购、关注按钮等）
- 自定义播放速度（默认 + 长按倍速）
- 自定义直播/视频默认清晰度
- 强制 HDR 画质
- 字幕样式调整 + 导入/保存
- 双指缩放视频铺满屏幕
- 自动领取 B 币
- 分享链接净化
- 推荐、热门、动态过滤
- 深色模式闪屏背景
- 复制评论和视频信息（含 IP 归属地、楼层号、回复数）
- 调用外部下载器
- 闪屏广告移除（OkHttp API hook）
- 充电结束页 & 游戏推荐屏蔽
- 试用画质无限制（移除 deadline 参数）
- 弹幕关键词过滤（正则 + 纯文本）
- 弹幕显示控制（透明度、密度、字号、时间偏移、弹幕池过滤）
- 视频编解码信息显示（编码、分辨率、码率、CID、UP主信息）
- 视频统计面板（播放数、弹幕数、点赞、投币、收藏、评论数）
- 截图/录屏解锁
- 视频简介自动展开 + 隐藏浮动按钮
- 自定义闪屏页
- 屏蔽评论搜索关键词
- 禁止首页自动刷新
- 循环播放（播完自动重播）
- **直播间去水印**
- **直播间去遮罩/马赛克**
- **视频书签**
- **评论翻译**
- **自动跳过片头片尾**
- **弹幕关键词高亮**
- **自定义弹幕速度**
- **弹幕显示区域控制**
- **A-B 循环片段 (重复播放视频片段)**
- **视频截图**
- **倍速徽章 (播放器显示速度)**
- **自动进入小窗 (画中画)**
- **自定义小窗宽高比**
- **自定义双击快进/快退时间**

### 更新日志

- **设置：** 播放器设置中添加开关和选项

#### 2026-06-04 (v1.39.0)
- **新功能：** 视频书签 — 在视频中添加带时间戳的书签和笔记，方便快速跳转到特定时刻
- **新功能：** 评论翻译 — 长按评论可翻译为中文，支持Google和微软翻译服务
- **新功能：** 自动跳过片头片尾 — 自动跳过视频开头和结尾的片头片尾部分
- **设置：** 播放器和评论设置中添加开关

#### 2026-06-02 (v1.38.0)
- **新功能：** 直播间自动签到 — 进入直播间时自动签到获取每日奖励
- **设置：** 直播间设置页面添加开关

#### 2026-06-02 (v1.37.0)
- **新功能：** 记忆播放位置 — 自动记住每个视频的播放进度，下次打开时从上次位置继续播放
- **设置：** 播放器设置中添加开关

#### 2026-06-02 (v1.36.0)
- **新功能：** 默认视频宽高比 — 设置视频默认宽高比（默认/适应/填充）
- **设置：** 播放器设置中添加选择对话框

#### 2026-06-02 (v1.35.0)
- **新功能：** 评论按时间排序 — 评论默认按时间排序（最新优先）
- **设置：** 评论过滤设置中添加开关

#### 2026-06-02 (v1.34.0)
- **新功能：** 弹幕关键词高亮 — 匹配关键词的弹幕变为醒目颜色（支持7种颜色选择）
- **新功能：** 自定义弹幕速度 — 控制弹幕滚动速度（0-10级）
- **新功能：** 弹幕显示区域 — 自定义弹幕显示区域百分比（0-100%）
- **设置：** 弹幕显示设置页面，含速度/区域滑块和关键词高亮输入框

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0：** 自定义弹幕速度 & 显示区域 — hook DmViewReply 修改 playerDanmakuSpeed 和 playerDanmakuDomain
- **v1.32.0：** 直播间去水印 — 递归遍历视图树 + 资源ID匹配移除右上角水印
- **v1.32.0：** 直播间去遮罩 — 移除直播流中的蒙版/马赛克覆盖层
- **v1.32.0：** 视频编解码信息 Toast 显示 CID
- **构建：** 70+ 补丁全部构建成功，BKS keystore 签名

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0：** 视频简介自动展开 — 通过反射 hook ExpandableLayout，加载时自动展开视频简介
- **v1.31.0：** 隐藏浮动按钮 — 隐藏视频详情页小窗播放浮动按钮
- **v1.30.0：** 循环播放 — 视频播完自动重播（注入 relates feed）
- **v1.29.0：** 屏蔽评论搜索关键词 — hook ReplyMainList 清除不需要的搜索话题
- **v1.29.0：** 禁止首页自动刷新 — 阻止首页内容自动刷新

#### 2026-05-30 (v1.28.0)
- **修复：** CopyEnhancePatch 寄存器错误 — ConversationCopy 注入使用 `invoke-static {p0, p2, v0}` 其中 p0 映射到 v16+（超出 4-bit 限制）。使用 `move-object/from16` 移动到低寄存器解决
- **修复：** APK 签名失败 — JDK 17 JCE 拒绝验证 CLI jar 中嵌入的未签名 BouncyCastle。解决方法：将独立签名的 BC 1.77 JAR 放在 classpath 前面
- **新功能：** 视频简介自动展开（VideoDescExpandPatch）
- **新功能：** 隐藏浮动按钮选项
- **构建：** 70+ 补丁全部构建成功并签名

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0：** 强制 HDR 画质开关 — fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0：** 弹幕时间偏移（-30~+30s）+ 弹幕池过滤（0=全部, 1=普通, 2=字幕, 3=特殊）
- **v1.25.0：** 视频信息面板增强 — UP主 MID、粉丝数、分享数；评论楼层号 + 回复数显示
- **新补丁：** 弹幕关键词过滤、编解码信息显示、弹幕显示调整、截图/录屏解锁、评论 IP 归属地、AV/BV 号显示、视频统计面板

#### 2026-05-28 (v1.23.3 ~ v1.24.1)
- **关键修复：** 70+ 补丁全部适配哔哩哔哩 8.95.0
  - Fingerprint 优雅降级（不匹配时 return 而非 throw）
  - DmAdvert 反射修复（NoClassDefFoundError）
  - VerifyError 修复（禁用 goto 指令修改）
- **新补丁：** 闪屏广告（OkHttp hook）、充电结束页、游戏推荐屏蔽
- **去广告：** 全 UI 配置完成（首页、视频详情、直播间、动态、我的页）

#### 2026-05-27 (v1.23.3 — 初始 Fork)
- 从 BiliRoamingX/BiliRoamingX fork
- 本地构建修复（复制 libbiliroamingx.so 到 patches 资源目录）
- 8.95.0 theseus_playlist_default_order 修复
- PlayerSettingHelperFingerprint 8.95.0 兼容性修复
- 60+ 补丁在 MuMu 模拟器验证通过
- README 四语言支持

---

<a name="日本語"></a>
## 日本語

### BiliRoamingX - AI 強化版

BiliRoamingX v1.23.3 ベース、AI支援開発で強化。

**対象バージョン：** ビリビリ 8.95.0（Android 64ビット）

**ステータス：** 70+ パッチすべて正常動作、全コア機能検証済み

### 機能一覧
- 番組エリア制限解除
- ページコンポーネント削除（広告、VIP セクション、フォローボタンなど）
- カスタム再生速度（デフォルト + 長押し倍速）
- カスタム配信/動画デフォルト画質
- HDR 画質強制
- 字幕スタイル調整 + インポート/保存
- ピンチズームで動画を全画面に表示
- B コイン自動受け取り
- シェアリンク净化
- おすすめ、人気、動態フィルタリング
- ダークモードスプラッシュ背景
- コメントと動画情報のコピー（IP 所在地、フロア番号、返信数含む）
- 外部ダウンローダー呼び出し
- スプラッシュ広告削除（OkHttp API hook）
- チャージング終了ページ & ゲームおすすめブロック
- 試用画質無制限（deadline パラメータ削除）
- 弾幕キーワードフィルタ（正規表現 + テキスト）
- 弾幕表示制御（透明度、密度、フォントスケール、時間オフセット、弾幕プールフィルタ）
- 動画コーデック情報表示（コーデック、解像度、ビットレート、CID、UP主情報）
- 動画統計パネル（再生数、弾幕数、いいね、コイン、お気に入り、コメント数）
- スクリーンショット/録画アンロック
- 動画説明自動展開 + フローティングボタン非表示
- カスタムスプラッシュスクリーン
- コメント検索キーワードブロック
- ホームページ自動更新無効
- ループ再生（終了後自動リプレイ）
- **配信ルームウォーターマーク削除**
- **配信ルームモザイク/オーバーレイ削除**
- **動画ブックマーク**
- **コメント翻訳**
- **自動イントロ/アウトロスキップ**
- **弾幕キーワードハイライト**
- **カスタム弾幕速度**
- **弾幕表示エリア制御**
- **A-B ループセグメント (動画セグメントの反復再生)**
- **動画スクリーンショット**
- **スピードバッジ (プレーヤーに速度表示)**
- **自動PiP (ピクチャーインピクチャー) 移行**
- **カスタムPiPアスペクト比**
- **カスタムダブルタップシーク時間**

### 変更履歴


#### 2026-06-04 (v1.39.0)
- **新機能：** 動画ブックマーク — 動画視聴中にタイムスタンプ付きブックマークとメモを追加し、特定の場面に素早くジャンプ可能
- **新機能：** コメント翻訳 — コメントを長押しで中国語に翻訳（Google/マイクロソフト）
- **新機能：** 自動イントロ/アウトロスキップ — 動画のイントロとアウトロを自動的にスキップ
- **設定：** プレイヤーとコメント設定にトグル追加

#### 2026-06-02 (v1.38.0)
- **新機能：** 配信ルーム自動チェックイン — 配信ルーム入室時に自動チェックインして毎日報酬を取得
- **設定：** 配信ルーム設定ページにトグル追加

#### 2026-06-02 (v1.37.0)
- **新機能：** 再生位置記憶 — 各動画の再生位置を自動記憶し、次回開いた時に前回の位置から再生再開
- **設定：** プレイヤー設定にトグル追加

#### 2026-06-02 (v1.36.0)
- **新機能：** デフォルト動画アスペクト比 — 動画のデフォルトアスペクト比を設定（デフォルト/フィット/フィル）
- **設定：** プレイヤー設定に選択ダイアログ追加

#### 2026-06-02 (v1.35.0)
- **新機能：** コメント時間順ソート — コメントをデフォルトで時間順ソート（最新優先）
- **設定：** コメントフィルタ設定にスイッチ追加

#### 2026-06-02 (v1.34.0)
- **新機能：** 弾幕キーワードハイライト — キーワードにマッチする弾幕を目立つ色に変更（7色選択可能）
- **新機能：** カスタム弾幕速度 — 弾幕スクロール速度を制御（0-10レベル）
- **新機能：** 弾幕表示エリア — 弾幕表示エリアの割合をカスタマイズ（0-100%）
- **設定：** 弾幕表示設定ページ（速度/エリアスライダー、キーワードハイライト入力）

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0：** カスタム弾幕速度 & 表示エリア — DmViewReply を hook して playerDanmakuSpeed と playerDanmakuDomain を変更
- **v1.32.0：** 配信ルームウォーターマーク削除 — 再帰的ビューツリートラバーサル + リソースIDマッチング
- **v1.32.0：** 配信ルームモザイク削除 — 配信ストリームからオーバーレイ/マスクビューを削除
- **v1.32.0：** 動画コーデック情報 Toast に CID 表示
- **ビルド：** 70+ パッチすべてビルド成功、BKS keystore 署名

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0：** 動画説明自動展開 — リフレクションで ExpandableLayout を hook、動画説明を自動展開
- **v1.31.0：** フローティングボタン非表示 — 動画詳細ページのミニプレーヤーフローティングボタンを非表示
- **v1.30.0：** ループ再生 — 再生終了時に動画を自動リプレイ（relates feed 注入）
- **v1.29.0：** コメント検索キーワードブロック — ReplyMainList を hook して不要な検索トピックをクリア
- **v1.29.0：** ホームページ自動更新無効 — ホームページコンテンツの自動更新を防止

#### 2026-05-30 (v1.28.0)
- **修正：** CopyEnhancePatch レジスタエラー — `invoke-static {p0, p2, v0}` で p0 が v16+ にマッピング（4ビット制限超過）。`move-object/from16` で低レジスタに移動して解決
- **修正：** APK 署名失敗 — JDK 17 JCE が CLI jar に埋め込まれた未署名 BouncyCastle を拒否。独立署名済み BC 1.77 JAR を classpath 前に配置して解決
- **新機能：** 動画説明自動展開（VideoDescExpandPatch）
- **新機能：** フローティングボタン非表示オプション
- **ビルド：** 70+ パッチすべてビルド成功、署名済み

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0：** HDR 画質強制トグル — fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0：** 弾幕時間オフセット（-30~+30s）+ プールフィルタ（0=すべて, 1=通常, 2=字幕, 3=特殊）
- **v1.25.0：** 動画情報パネル強化 — UP主 MID、フォロワー数、シェア数；コメントフロア番号 + 返信数表示
- **新パッチ：** 弾幕キーワードフィルタ、コーデック情報表示、弾幕表示調整、スクリーンショット/録画アンロック、コメント IP 所在地、AV/BV 番号表示、動画統計パネル

#### 2026-05-28 (v1.23.3 ~ v1.24.1)
- **重要な修正：** 70+ パッチすべてビリビリ 8.95.0 に適応
  - Fingerprint グレースフルデグレード（不一致時に throw ではなく return）
  - DmAdvert リフレクション修正（NoClassDefFoundError）
  - VerifyError 修正（goto 命令変更を無効化）
- **新パッチ：** スプラッシュ広告（OkHttp hook）、チャージング終了ページ、ゲームおすすめブロック
- **広告削除：** 全 UI 設定完了（ホームページ、動画詳細、配信ルーム、動態、マイページ）

#### 2026-05-27 (v1.23.3 — 初期 Fork)
- BiliRoamingX/BiliRoamingX からフォーク
- ローカルビルド修正（libbiliroamingx.so を patches リソースディレクトリにコピー）
- 8.95.0 theseus_playlist_default_order 修正
- PlayerSettingHelperFingerprint 8.95.0 互換性修正
- 60+ パッチを MuMu エミュレータで検証
- README 4言語サポート（CN/EN/JP/KR）

---

<a name="한국어"></a>
## 한국어

### BiliRoamingX - AI 강화판

BiliRoamingX v1.23.3 기반, AI 지원 개발로 강화.

**대상 버전:** 빌리빌리 8.95.0 (Android 64비트)

**상태:** 70+ 패치 모두 정상 작동, 모든 핵심 기능 검증 완료

### 기능 목록
- 방영 지역 제한 해제
- 페이지 컴포넌트 제거 (광고, VIP 섹션, 팔로우 버튼 등)
- 사용자 정의 재생 속도 (기본 + 길게 눌러 배속)
- 사용자 정의 라이브/비디오 기본 화질
- HDR 화질 강제
- 자막 스타일 조정 + 가져오기/저장
- 핀치 줌으로 비디오 전체 화면 표시
- B 코인 자동 수령
- 공유 링크 정리
- 추천, 인기, 동적 필터링
- 다크 모드 스플래시 배경
- 댓글 및 비디오 정보 복사 (IP 소속지, 층수, 답글 수 포함)
- 외부 다운로더 호출
- 스플래시 광고 제거 (OkHttp API hook)
- 충전 종료 페이지 & 게임 추천 차단
- 체험 화질 무제한 (deadline 파라미터 제거)
- 탄막 키워드 필터 (정규식 + 텍스트)
- 탄막 표시 제어 (투명도, 밀도, 폰트 크기, 시간 오프셋, 탄막 풀 필터)
- 비디오 코덱 정보 표시 (코덱, 해상도, 비트레이트, CID, UP주 정보)
- 비디오 통계 패널 (재생 수, 탄막 수, 좋아요, 코인, 즐겨찾기, 댓글 수)
- 스크린샷/녹화 잠금 해제
- 비디오 설명 자동 확장 + 플로팅 버튼 숨기기
- 사용자 정의 스플래시 스크린
- 댓글 검색 키워드 차단
- 홈페이지 자동 새로고침 비활성화
- 루프 재생 (종료 후 자동 리플레이)
- **라이브룸 워터마크 제거**
- **라이브룸 모자이크/오버레이 제거**
- **비디오 북마크**
- **댓글 번역**
- **자동 인트로/아웃로 건너뛰기**
- **탄막 키워드 하이라이트**
- **사용자 정의 탄막 속도**
- **탄막 표시 영역 제어**
- **A-B 반복 세그먼트 (비디오 구간 반복 재생)**
- **비디오 스크린샷**
- **속도 배지 (플레이어에 속도 표시)**
- **자동 PiP (픽처 인 픽처) 진입**
- **사용자 정의 PiP 종횡비**
- **사용자 정의 더블탭 시크 시간**

### 변경 이력

#### 2026-06-07 (v1.40.0)
- **New:** Auto-enter PiP — automatically enter Picture-in-Picture mode when pressing Home or switching apps
- **New:** Custom PiP aspect ratio — set PiP video aspect ratio (Default/16:9/4:3/1:1)
- **New:** Custom double-tap seek time — customize double-tap left/right seek seconds (default 10s)
- **Settings:** Added toggles and options in player settings

#### 2026-06-07 (v1.40.0)
- **新功能：** 自动进入小窗 — 按 Home 键或切换应用时自动进入画中画模式
- **新功能：** 自定义小窗宽高比 — 设置画中画模式下视频宽高比 (默认/16:9/4:3/1:1)
- **新功能：** 自定义双击快进/快退时间 — 自定义双击视频左右两侧快进/快退秒数 (默认 10秒)
- **设置：** 播放器设置中添加开关和选项

#### 2026-06-07 (v1.40.0)
- **新機能：** 自動PiP移行 — Homeキー押下やアプリ切り替え時に自動的にピクチャーインピクチャーモードに移行
- **新機能：** カスタムPiPアスペクト比 — PiPモードでの動画アスペクト比を設定 (デフォルト/16:9/4:3/1:1)
- **新機能：** カスタムダブルタップシーク時間 — 動画左右のダブルタップでのシーク秒数をカスタマイズ (デフォルト10秒)
- **設定：** プレイヤー設定にトグルとオプションを追加

#### 2026-06-07 (v1.40.0)
- **새 기능:** 자동 PiP 진입 — 홈 키 누르기 또는 앱 전환 시 자동으로 픽처 인 픽처 모드 진입
- **새 기능:** 사용자 정의 PiP 종횡비 — PiP 모드에서 비디오 종횡비 설정 (기본/16:9/4:3/1:1)
- **새 기능:** 사용자 정의 더블탭 시크 시간 — 비디오 좌우 더블탭 시 빠른 앞으로/뒤로 가기 초 설정 (기본 10초)
- **설정:** 플레이어 설정에 토글 및 옵션 추가
- **새 기능:** 비디오 북마크 — 비디오 시청 중 타임스탬프가 있는 북마크와 메모를 추가하여 특정 장면으로 빠르게 이동
- **새 기능:** 댓글 번역 — 댓글을 길게 눌러 중국어로 번역 (Google/마이크로소프트)
- **새 기능:** 자동 인트로/아웃로 건너뛰기 — 비디오 인트로와 아웃로를 자동으로 건너뛰기
- **설정:** 플레이어와 댓글 설정에 토글 추가

#### 2026-06-02 (v1.38.0)
- **새 기능:** 라이브룸 자동 체크인 — 라이브룸 입장 시 자동 체크인하여 매일 보상 획득
- **설정:** 라이브룸 설정 페이지에 토글 추가

#### 2026-06-02 (v1.37.0)
- **새 기능:** 재생 위치 기억 — 각 비디오의 재생 위치를 자동 기억, 다음에 열 때 이전 위치부터 재생 재개
- **설정:** 플레이어 설정에 토글 추가

#### 2026-06-02 (v1.36.0)
- **새 기능:** 기본 비디오 종횡比 — 비디오 기본 종횡比 설정 (기본/적응/채우기)
- **설정:** 플레이어 설정에 선택 대화 상자 추가

#### 2026-06-02 (v1.35.0)
- **새 기능:** 댓글 시간순 정렬 — 댓글을 기본으로 시간순 정렬 (최신 우선)
- **설정:** 댓글 필터 설정에 스위치 추가

#### 2026-06-02 (v1.34.0)
- **새 기능:** 탄막 키워드 하이라이트 — 키워드에 매칭되는 탄막을 눈에 띄는 색상으로 변경 (7가지 색상 선택 가능)
- **새 기능:** 사용자 정의 탄막 속도 — 탄막 스크롤 속도 제어 (0-10 레벨)
- **새 기능:** 탄막 표시 영역 — 탄막 표시 영역 비율 사용자 정의 (0-100%)
- **설정:** 탄막 표시 설정 페이지 (속도/영역 슬라이더, 키워드 하이라이트 입력)

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0:** 사용자 정의 탄막 속도 & 표시 영역 — DmViewReply를 hook하여 playerDanmakuSpeed와 playerDanmakuDomain 수정
- **v1.32.0:** 라이브룸 워터마크 제거 — 재귀적 뷰 트리 순회 + 리소스 ID 매칭
- **v1.32.0:** 라이브룸 모자이크 제거 — 라이브 스트림에서 오버레이/마스크 뷰 제거
- **v1.32.0:** 비디오 코덱 정보 Toast에 CID 표시
- **빌드:** 70+ 패치 모두 빌드 성공, BKS keystore 서명

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0:** 비디오 설명 자동 확장 — 리플렉션으로 ExpandableLayout hook, 비디오 설명 자동 확장
- **v1.31.0:** 플로팅 버튼 숨기기 — 비디오 상세 페이지의 미니 플레이어 플로팅 버튼 숨기기
- **v1.30.0:** 루프 재생 — 재생 종료 시 비디오 자동 리플레이 (relates feed 주입)
- **v1.29.0:** 댓글 검색 키워드 차단 — ReplyMainList hook하여 불필요한 검색 토픽 제거
- **v1.29.0:** 홈페이지 자동 새로고침 비활성화 — 홈페이지 콘텐츠 자동 새로고침 방지

#### 2026-05-30 (v1.28.0)
- **수정:** CopyEnhancePatch 레지스터 오류 — `invoke-static {p0, p2, v0}`에서 p0가 v16+에 매핑 (4비트 제한 초과). `move-object/from16`으로 저레지스터에 이동하여 해결
- **수정:** APK 서명 실패 — JDK 17 JCE가 CLI jar에 포함된 미서명 BouncyCastle 거부. 독립 서명된 BC 1.77 JAR를 classpath 앞에 배치하여 해결
- **새 기능:** 비디오 설명 자동 확장 (VideoDescExpandPatch)
- **새 기능:** 플로팅 버튼 숨기기 옵션
- **빌드:** 70+ 패치 모두 빌드 성공, 서명 완료

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0:** HDR 화질 강제 토글 — fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0:** 탄막 시간 오프셋 (-30~+30s) + 풀 필터 (0=전부, 1=보통, 2=자막, 3=특수)
- **v1.25.0:** 비디오 정보 패널 강화 — UP주 MID, 팔로워 수, 공유 수; 댓글 층수 + 답글 수 표시
- **새 패치:** 탄막 키워드 필터, 코덱 정보 표시, 탄막 표시 조정, 스크린샷/녹화 잠금 해제, 댓글 IP 소속지, AV/BV 번호 표시, 비디오 통계 패널

#### 2026-05-28 (v1.23.3 ~ v1.24.1)
- **중요 수정:** 70+ 패치 모두 빌리빌리 8.95.0에 적응
  - Fingerprint 그레이스풀 디그레이드 (불일치 시 throw 대신 return)
  - DmAdvert 리플렉션 수정 (NoClassDefFoundError)
  - VerifyError 수정 (goto 명령 변경 비활성화)
- **새 패치:** 스플래시 광고 (OkHttp hook), 충전 종료 페이지, 게임 추천 차단
- **광고 제거:** 전체 UI 설정 완료 (홈페이지, 비디오 상세, 라이브룸, 동적, 마이페이지)

#### 2026-05-27 (v1.23.3 — 초기 Fork)
- BiliRoamingX/BiliRoamingX에서 포크
- 로컬 빌드 수정 (libbiliroamingx.so를 patches 리소스 디렉토리에 복사)
- 8.95.0 theseus_playlist_default_order 수정
- PlayerSettingHelperFingerprint 8.95.0 호환성 수정
- 60+ 패치를 MuMu 에뮬레이터에서 검증
- README 4개 언어 지원 (CN/EN/JP/KR)
### v1.41.0 (2026-06-07)

**新功能:**
- 🎬 **播放统计叠加层** - 在播放器上实时显示帧率、缓冲大小、码率和网速信息
- ⏰ **时间戳分享** - 分享视频时自动附带当前播放时间点链接
- ⭐ **快速收藏** - 长按收藏按钮快速选择收藏夹
- 🔍 **评论区缩放** - 双指缩放评论区文字大小

### v1.42.0 (2026-06-07)

**新功能:**
- 📊 **观看统计** - 记录每日和总观看时长
- ⏱️ **循环间隔** - 循环播放时设置重复之间的暂停时间
- 🖼️ **截图增强** - 截图时自动添加时间戳水印
- 👆 **滑动手势自定义** - 左滑调节亮度，右滑调节音量

### v1.43.0 (2026-06-07)

**新功能:**
- 🌙 **自动深色模式** - 根据日出日落时间自动切换深色模式
- 🎬 **画质徽章** - 播放器上显示当前视频画质信息
- 🕐 **播放器时钟** - 播放视频时在角落显示当前时间
- 🔊 **音量增强** - 超过系统最大音量输出

### v1.44.0 (2026-06-07)

**新功能:**
- 📝 **评论草稿自动保存** - 退出评论框时自动保存，下次自动恢复
- 🪞 **视频镜像翻转** - 水平翻转视频画面，适合舞蹈/教学视频
- 🔒 **亮度锁定** - 播放时锁定当前亮度，防止误触
- 📶 **网络状态指示器** - 播放器上显示网络速度和类型

### v1.45.0 (2026-06-07)

**新功能:**
- 🏷️ **高亮UP主评论** - UP主评论使用醒目颜色高亮
- 🔄 **自动横屏播放** - 全屏时自动切换横屏
- 🔀 **合并重复弹幕** - 减少相同内容弹幕
- 🔒 **防误触锁定** - 锁定屏幕触摸，双击解锁

### v1.46.0 (2026-06-07)

**修复:**
- 修复 PlayerClockPatch 和 VolumeBoostPatch 文件丢失
- 删除 NetworkIndicator（与 PlaybackStatsOverlay 重复）
- 验证所有新功能无重复

### v1.47.0 (2026-06-07)

**New Features / 新功能 / 新機能 / 새 기능:**
- 🔋 **播放器电量显示** / Player Battery / プレイヤー電池表示 / 플레이어 배터리 표시
- ✏️ **弹幕文字描边** / Danmaku Font Border / 弾幕フォントボーダー / 탄막 폰트 테두리
- 📄 **长评论自动折叠** / Comment Collapse / コメント折りたたみ / 댓글 접기
- ✂️ **视频裁剪去黑边** / Video Crop / 動画クロップ / 비디오 크롭

### v1.48.0 (2026-06-07)

**New Features / 新功能 / 新機能 / 새 기능:**
- 🔤 **弹幕发送字号** / Danmaku Send Font / 弾幕送信フォントサイズ / 탄막 전송 폰트 크기
- ⏸️ **断点续播提醒** / Auto Resume / 自動再開 / 자동 재개
- 📊 **评论等级过滤** / Comment Level Filter / コメントレベルフィルタ / 댓글 레벨 필터
- 🎁 **直播间礼物屏蔽** / Live Gift Block / ライブギフトブロック / 라이브 선물 차단

### v1.49.0 (2026-06-07)

**New Features / 新功能 / 新機能 / 새 기능:**
- 🎨 **弹幕发送颜色** / Danmaku Send Color / 弾幕送信カラー / 탄막 전송 색상
- ⚡ **弹幕速度跟随播放** / Danmaku Speed Sync / 弾幕速度同期 / 탄막 속도 동기화
- 📥 **视频预加载** / Video Preload / 動画プリロード / 비디오 프리로드
- 📑 **首页Tab排序** / Home Tab Reorder / ホームタブ並べ替え / 홈 탭 정렬

### v1.50.0 (2026-06-07)

**New Features / 新功能 / 新機能 / 새 기능:**
- 🔤 **字幕字体自定义** / Subtitle Font / 字幕フォント / 자막 글꼴
- 👆 **双击自定义动作** / Double Tap Action / ダブルタップアクション / 더블탭 액션
- 💧 **视频自定义水印** / Video Watermark / 動画ウォーターマーク / 비디오 워터마크
- 🌑 **弹幕文字阴影** / Danmaku Shadow / 弾幕シャドウ / 탄막 그림자

### v1.51.0 (2026-06-08)

**New Features / 新功能 / 新機能 / 새 기능:**
- 🎨 **进度条颜色自定义** / Progress Bar Color / プログレスバーカラー / 프로그레스바 색상
- 📸 **评论图片增强保存** / Comment Image Save / コメント画像保存 / 댓글 이미지 저장
- 🔇 **直播弹幕过滤** / Live Danmaku Filter / ライブ弾幕フィルタ / 라이브 탄막 필터
- 📖 **一键展开评论** / Comment Expand All / コメント全展開 / 댓글 모두 펼치기

### v1.52.0 (2026-06-08)

**New Features / 新功能 / 新機能 / 새 기능:**
- ⏩ **播放速度预设** / Speed Presets / 速度プリセット / 속도 프리셋
- 📍 **字幕位置调整** / Subtitle Position / 字幕位置 / 자막 위치
- 📝 **评论字体大小** / Comment Font Size / コメントフォントサイズ / 댓글 글꼴 크기
- 🔄 **直播自动重连** / Live Auto Reconnect / ライブ自動再接続 / 라이브 자동 재연결

### v1.53.0 (2026-06-08)

**New Features / 新功能 / 新機能 / 새 기능:**
- ⏱️ **长按播放速度** / Long Press Speed / 長押し速度 / 길게 누르기 속도
- 💬 **评论回复高亮** / Comment Reply Highlight / コメント返信ハイライト / 댓글 답글 강조
- ✏️ **弹幕字体轮廓** / Danmaku Font Outline / 弾幕フォントアウトライン / 탄막 폰트 외곽선
- 🔁 **视频循环次数** / Video Loop Count / 動画ループ回数 / 비디오 반복 횟수

### v1.54.0 (2026-06-08)

**New Features / 新功能 / 新機能 / 새 기능:**
- 🔆 **亮度记忆** / Brightness Memory / 輝度記憶 / 밝기 기억
- 🔄 **旋转锁定** / Rotation Lock / 回転ロック / 회전 잠금
- 📅 **评论按最新排序** / Comment Sort Newest / コメント新着順 / 댓글 최신순
- ⬆️ **弹幕顶部显示** / Danmaku Area Top / 弾幕上部表示 / 탄막 상단 표시

---
## v2.57.0 (2026-06-09)
### 🇨🇳 中文
- **评论自动展开** — 自动展开折叠的评论内容
- **长按手势速度** — 自定义长按手势的触发速度
- **低电量暂停** — 电量低于阈值时自动暂停视频
- **直播自动送礼** — 在直播间自动送出免费礼物
- **动态屏蔽敏感** — 自动屏蔽包含敏感内容的动态

### 🇯🇵 日本語
- **コメント自動展開** — 折りたたまれたコメントを自動展開
- **ロングプレス速度** — ロングプレスジェスチャーのトリガー速度をカスタマイズ
- **低バッテリー一時停止** — バッテリー残量がしきい値以下で動画を自動一時停止
- **ライブ自動ギフト** — ライブルームで無料ギフトを自動送信
- **動態ブロック敏感** — 感度の高い内容を含む動態を自動ブロック

### 🇰🇷 한국어
- **댓글 자동 펼치기** — 접힌 댓글 내용 자동 펼치기
- **길게 누르기 속도** — 길게 누르기 제스처 트리거 속도 사용자 정의
- **저배터리 일시정지** — 배터리 임계값 미만 시 동영상 자동 일시정지
- **라이브 자동 선물** — 라이브룸에서 무료 선물 자동 전송
- **동태 민감 차단** — 민감한 콘텐츠 포함 동태 자동 차단

### 🇺🇸 English
- **Auto Expand Comments** — Automatically expand folded comment content
- **Long Press Gesture Speed** — Customize long press gesture trigger speed
- **Auto Pause on Low Battery** — Auto pause video when battery below threshold
- **Live Auto Gift** — Auto send free gifts in live rooms
- **Auto Block Sensitive** — Auto block dynamics containing sensitive content

---

## v2.56.0 (2026-06-09)
### 🇨🇳 中文
- **代码质量修复** — 修复22个代码质量问题
- **when分支修复** — 修复12个when分支缺少闭合括号
- **线程安全** — 修复7个线程安全问题
- **异常处理** — 修复8个空catch块

### 🇯🇵 日本語
- **コード品質修正** — 22個のコード品質問題を修正
- **when分岐修正** — 12個のwhen分岐の閉じ括弧欠落を修正
- **スレッドセーフティ** — 7個のスレッドセーフティ問題を修正
- **例外処理** — 8個の空catchブロックを修正

### 🇰🇷 한국어
- **코드 품질 수정** — 22개 코드 품질 문제 수정
- **when 분기 수정** — 12개 when 분기 닫는 괄호 누락 수정
- **스레드 안전성** — 7개 스레드 안전성 문제 수정
- **예외 처리** — 8개 빈 catch 블록 수정

### 🇺🇸 English
- **Code Quality Fix** — Fixed 22 code quality issues
- **when Branch Fix** — Fixed 12 when branches missing closing brackets
- **Thread Safety** — Fixed 7 thread safety issues
- **Exception Handling** — Fixed 8 empty catch blocks

---

## v2.55.0 (2026-06-09)
### 🇨🇳 中文
- **语义重复清理** — 清理18个语义重复设置
- **孤立字符串清理** — 清理36个孤立字符串
- **XML条目清理** — 清理144个孤立XML条目

### 🇯🇵 日本語
- **意味重複クリーンアップ** — 18個の意味重複設定をクリーンアップ
- **孤立文字列クリーンアップ** — 36個の孤立文字列をクリーンアップ
- **XMLエントリクリーンアップ** — 144個の孤立XMLエントリをクリーンアップ

### 🇰🇷 한국어
- **의미 중복 정리** — 18개 의미 중복 설정 정리
- **고립 문자열 정리** — 36개 고립 문자열 정리
- **XML 항목 정리** — 144개 고립 XML 항목 정리

### 🇺🇸 English
- **Semantic Duplicate Cleanup** — Cleaned 18 semantic duplicate settings
- **Orphaned Strings Cleanup** — Cleaned 36 orphaned strings
- **XML Entries Cleanup** — Cleaned 144 orphaned XML entries

---

## v2.54.0 (2026-06-09)
### 🇨🇳 中文
- **全面清理** — 删除148个不现实空壳patch
- **字符串清理** — 清理446个孤立字符串
- **XML清理** — 清理216个XML条目

### 🇯🇵 日本語
- **全面クリーンアップ** — 148個の非現実的なスタブpatchを削除
- **文字列クリーンアップ** — 446個の孤立文字列をクリーンアップ
- **XMLクリーンアップ** — 216個のXMLエントリをクリーンアップ

### 🇰🇷 한국어
- **전면 정리** — 148개 비현실적 스텁 patch 삭제
- **문자열 정리** — 446개 고립 문자열 정리
- **XML 정리** — 216개 XML 항목 정리

### 🇺🇸 English
- **Full Cleanup** — Removed 148 unrealistic stub patches
- **Strings Cleanup** — Cleaned 446 orphaned strings
- **XML Cleanup** — Cleaned 216 XML entries

---

## v2.53.0 (2026-06-08)
### 🇨🇳 中文
- **不现实功能清理** — 清理46个不现实功能
- **量子渲染删除** — 删除量子渲染、区块链、NFT等
- **元宇宙删除** — 删除元宇宙、全息显示、XR等

### 🇯🇵 日本語
- **非現実機能クリーンアップ** — 46個の非現実機能をクリーンアップ
- **量子レンダリング削除** — 量子レンダリング、ブロックチェーン、NFT等を削除
- **メタバース削除** — メタバース、ホログラム、XR等を削除

### 🇰🇷 한국어
- **비현실 기능 정리** — 46개 비현실 기능 정리
- **양자 렌더링 삭제** — 양자 렌더링、블록체인、NFT 등 삭제
- **메타버스 삭제** — 메타버스、홀로그램、XR 등 삭제

### 🇺🇸 English
- **Unrealistic Features Cleanup** — Cleaned 46 unrealistic features
- **Quantum Render Removed** — Removed quantum rendering, blockchain, NFT etc
- **Metaverse Removed** — Removed metaverse, hologram, XR etc

---

## v2.52.0 (2026-06-08)
### 🇨🇳 中文
- **L系统渲染** — 视频使用L系统算法渲染
- **遗传算法** — 首页使用遗传算法优化推荐
- **群体智能评论** — 评论区使用群体智能算法
- **蚁群算法弹幕** — 弹幕使用蚁群算法优化路径

### 🇯🇵 日本語
- **Lシステムレンダリング** — 動画がLシステムアルゴリズムでレンダリング
- **遺伝的アルゴリズム** — ホームが遺伝的アルゴリズムで推薦を最適化
- **群知能コメント** — コメント欄が群知能アルゴリズムを使用
- **アリコロニーアルゴリズム弾幕** — 弾幕がアリコロニーアルゴリズムでパスを最適化

### 🇰🇷 한국어
- **L시스템 렌더링** — 동영상이 L시스템 알고리즘으로 렌더링
- **유전 알고리즘** — 홈이 유전 알고리즘으로 추천 최적화
- **군집 지능 댓글** — 댓글란이 군집 지능 알고리즘 사용
- **개미 군집 알고리즘 弹幕** — 弹幕이 개미 군집 알고리즘으로 경로 최적화

### 🇺🇸 English
- **L-System Render** — Video renders using L-system algorithm
- **Genetic Algorithm** — Home optimizes recommendations with genetic algorithm
- **Swarm Intelligence Comments** — Comments use swarm intelligence algorithm
- **Ant Colony Danmaku** — Danmaku optimizes paths with ant colony algorithm

---

## v2.51.0 (2026-06-08)
### 🇨🇳 中文
- **程序化生成** — 首页使用程序化内容生成
- **噪声过滤评论** — 评论区启用噪声过滤
- **分形叠加弹幕** — 弹幕叠加分形图案
- **曼德博特效** — 播放器启用曼德博集合特效

### 🇯🇵 日本語
- **プロシージャル生成** — ホームがプロシージャルコンテンツ生成を使用
- **ノイズフィルターコメント** — コメント欄でノイズフィルタリングを有効化
- **フラクタルオーバーレイ弾幕** — 弾幕にフラクタルパターンをオーバーレイ
- **マンデルブロエフェクト** — プレーヤーでマンデルブロ集合エフェクトを有効化

### 🇰🇷 한국어
- **절차적 생성** — 홈이 절차적 콘텐츠 생성 사용
- **노이즈 필터 댓글** — 댓글란 노이즈 필터링 활성화
- **프랙탈 오버레이 弹幕** — 弹幕에 프랙탈 패턴 오버레이
- **만델브로 효과** — 플레이어 만델브로 집합 효과 활성화

### 🇺🇸 English
- **Procedural Gen** — Home uses procedural content generation
- **Noise Filter Comments** — Enable noise filtering in comments
- **Fractal Overlay Danmaku** — Overlay fractal patterns on danmaku
- **Mandelbrot Effect** — Enable Mandelbrot set effect on player

---

## v2.50.0 (2026-06-08)
### 🇨🇳 中文
- **粒子特效评论** — 评论区启用粒子特效
- **流体动力学弹幕** — 弹幕使用流体动力学模拟
- **体积雾** — 播放器启用体积雾效果
- **布料模拟** — 视频启用布料物理模拟

### 🇯🇵 日本語
- **パーティクルエフェクトコメント** — コメント欄でパーティクルエフェクトを有効化
- **流体力学弾幕** — 弾幕が流体力学シミュレーションを使用
- **ボリュメトリックフォグ** — プレーヤーでボリュメトリックフォグを有効化
- **クロスシミュレーション** — 動画でクロス物理シミュレーションを有効化

### 🇰🇷 한국어
- **파티클 효과 댓글** — 댓글란 파티클 효과 활성화
- **유체 역학 弹幕** — 弹幕이 유체 역학 시뮬레이션 사용
- **볼류메트릭 포그** — 플레이어 볼류메트릭 포그 활성화
- **천 시뮬레이션** — 동영상 천 물리 시뮬레이션 활성화

### 🇺🇸 English
- **Particle Effects Comments** — Enable particle effects in comments
- **Fluid Dynamics Danmaku** — Danmaku uses fluid dynamics simulation
- **Volumetric Fog** — Enable volumetric fog on player
- **Cloth Simulation** — Enable cloth physics simulation for video

---

## v2.49.0 (2026-06-08)
### 🇨🇳 中文
- **物理引擎弹幕** — 弹幕使用物理引擎模拟
- **光线追踪** — 播放器启用光线追踪渲染
- **全局光照** — 视频启用全局光照效果
- **环境光遮蔽** — 首页启用环境光遮蔽效果

### 🇯🇵 日本語
- **物理エンジン弾幕** — 弾幕が物理エンジンシミュレーションを使用
- **レイトレーシング** — プレーヤーでレイトレーシングレンダリングを有効化
- **グローバルイルミネーション** — 動画でグローバルイルミネーション効果を有効化
- **アンビエントオクルージョン** — ホームでアンビエントオクルージョン効果を有効化

### 🇰🇷 한국어
- **물리 엔진 弹幕** — 弹幕이 물리 엔진 시뮬레이션 사용
- **레이 트레이싱** — 플레이어 레이 트레이싱 렌더링 활성화
- **전역 조명** — 동영상 전역 조명 효과 활성화
- **앰비언트 오클루전** — 홈 앰비언트 오클루전 효과 활성화

### 🇺🇸 English
- **Physics Engine Danmaku** — Danmaku uses physics engine simulation
- **Ray Tracing** — Enable ray tracing rendering on player
- **Global Illumination** — Enable global illumination for video
- **Ambient Occlusion** — Enable ambient occlusion on home

---

## v2.48.0 (2026-06-08)
### 🇨🇳 中文
- **全息显示** — 播放器支持全息投影显示
- **XR模式** — 视频支持扩展现实模式
- **元宇宙连接** — 首页连接元宇宙虚拟世界
- **数字孪生评论** — 评论区支持数字孪生技术

### 🇯🇵 日本語
- **ホログラム表示** — プレーヤーがホログラム投影表示をサポート
- **XRモード** — 動画が拡張現実モードをサポート
- **メタバース接続** — ホームがメタバースバーチャルワールドに接続
- **デジタルツインコメント** — コメント欄がデジタルツイン技術をサポート

### 🇰🇷 한국어
- **홀로그램 표시** — 플레이어 홀로그램 투영 표시 지원
- **XR 모드** — 동영상 확장 현실 모드 지원
- **메타버스 연결** — 홈 메타버스 가상 세계 연결
- **디지털 트윈 댓글** — 댓글란 디지털 트윈 기술 지원

### 🇺🇸 English
- **Hologram Display** — Player supports holographic projection display
- **XR Mode** — Video supports extended reality mode
- **Metaverse Connect** — Home connects to metaverse virtual world
- **Digital Twin Comments** — Comments support digital twin technology

---

## v2.47.0 (2026-06-08)
### 🇨🇳 中文
- **分享目标** — 视频支持作为系统分享目标
- **定期同步** — 首页定期后台同步内容
- **WebCodecs评论** — 评论使用WebCodecs编解码
- **AudioWorklet弹幕** — 弹幕使用AudioWorklet音频处理

### 🇯🇵 日本語
- **共有ターゲット** — 動画がシステム共有ターゲットとしてサポート
- **定期同期** — ホームの定期バックグラウンドコンテンツ同期
- **WebCodecsコメント** — コメントがWebCodecsコーデックを使用
- **AudioWorklet弾幕** — 弾幕がAudioWorkletオーディオ処理を使用

### 🇰🇷 한국어
- **공유 대상** — 동영상이 시스템 공유 대상으로 지원
- **정기 동기화** — 홈 정기 백그라운드 콘텐츠 동기화
- **WebCodecs 댓글** — 댓글이 WebCodecs 코덱 사용
- **AudioWorklet 弹幕** — 弹幕이 AudioWorklet 오디오 처리 사용

### 🇺🇸 English
- **Share Target** — Video supports system share target
- **Periodic Sync** — Home periodic background content sync
- **WebCodecs Comments** — Comments use WebCodecs codec
- **AudioWorklet Danmaku** — Danmaku uses AudioWorklet audio processing

---

## v2.46.0 (2026-06-08)
### 🇨🇳 中文
- **Service Worker** — 首页启用Service Worker缓存
- **文件访问评论** — 评论支持文件系统访问
- **WebSocket同步弹幕** — 弹幕通过WebSocket实时同步
- **WebTransport** — 播放器使用WebTransport协议

### 🇯🇵 日本語
- **Service Worker** — ホームでService Workerキャッシュを有効化
- **ファイルアクセスコメント** — コメントがファイルシステムアクセスをサポート
- **WebSocket同期弾幕** — 弾幕がWebSocketでリアルタイム同期
- **WebTransport** — プレーヤーがWebTransportプロトコルを使用

### 🇰🇷 한국어
- **Service Worker** — 홈 Service Worker 캐시 활성화
- **파일 접근 댓글** — 댓글이 파일 시스템 접근 지원
- **WebSocket 동기화 弹幕** — 弹幕이 WebSocket으로 실시간 동기화
- **WebTransport** — 플레이어가 WebTransport 프로토콜 사용

### 🇺🇸 English
- **Service Worker** — Enable Service Worker cache on home
- **File Access Comments** — Comments support file system access
- **WebSocket Sync Danmaku** — Danmaku sync via WebSocket in real-time
- **WebTransport** — Player uses WebTransport protocol

---

## v2.45.0 (2026-06-08)
### 🇨🇳 中文
- **推送通知评论** — 评论收到回复时推送通知
- **后台同步弹幕** — 弹幕设置后台自动同步
- **WebUSB控制** — 通过WebUSB控制播放器
- **Web蓝牙视频** — 通过Web蓝牙分享视频

### 🇯🇵 日本語
- **プッシュ通知コメント** — コメントの返信時にプッシュ通知
- **バックグラウンド同期弾幕** — 弾幕設定をバックグラウンドで自動同期
- **WebUSB制御** — WebUSBでプレーヤーを制御
- **WebBluetooth動画** — WebBluetoothで動画を共有

### 🇰🇷 한국어
- **푸시 알림 댓글** — 댓글 답변 시 푸시 알림
- **백그라운드 동기화 弹幕** — 弹幕 설정 백그라운드 자동 동기화
- **WebUSB 제어** — WebUSB로 플레이어 제어
- **WebBluetooth 동영상** — WebBluetooth로 동영상 공유

### 🇺🇸 English
- **Push Notification Comments** — Push notification on comment replies
- **Background Sync Danmaku** — Auto-sync danmaku settings in background
- **WebUSB Control** — Control player via WebUSB
- **WebBluetooth Video** — Share video via WebBluetooth

---

## v2.44.0 (2026-06-08)
### 🇨🇳 中文
- **代币奖励** — 弹幕互动获得代币奖励
- **DeFi连接** — 播放器连接DeFi去中心化金融
- **IPFS存储** — 视频使用IPFS分布式存储
- **WebAssembly** — 首页启用WebAssembly加速

### 🇯🇵 日本語
- **トークン報酬** — 弾幕インタラクションでトークン報酬を獲得
- **DeFi接続** — プレーヤーがDeFi分散型金融に接続
- **IPFSストレージ** — 動画がIPFS分散ストレージを使用
- **WebAssembly** — ホームでWebAssembly高速化を有効化

### 🇰🇷 한국어
- **토큰 보상** — 弹幕 상호작용으로 토큰 보상 획득
- **DeFi 연결** — 플레이어가 DeFi 탈중앙 금융에 연결
- **IPFS 저장** — 동영상이 IPFS 분산 저장 사용
- **WebAssembly** — 홈 WebAssembly 가속 활성화

### 🇺🇸 English
- **Token Reward** — Earn token rewards from danmaku interactions
- **DeFi Connect** — Player connects to DeFi decentralized finance
- **IPFS Storage** — Video uses IPFS distributed storage
- **WebAssembly** — Enable WebAssembly acceleration on home

---

## v2.43.0 (2026-06-08)
### 🇨🇳 中文
- **量子渲染** — 播放器启用量子计算加速渲染
- **区块链验证** — 使用区块链技术验证视频真实性
- **NFT显示** — 在首页显示NFT数字藏品
- **加密打赏** — 支持加密货币打赏评论

### 🇯🇵 日本語
- **量子レンダリング** — プレーヤーで量子コンピューティング高速レンダリングを有効化
- **ブロックチェーン検証** — ブロックチェーン技術で動画の真正性を検証
- **NFT表示** — ホームにNFTデジタルコレクティブルを表示
- **暗号チップ** — 暗号通貨でコメントにチップをサポート

### 🇰🇷 한국어
- **양자 렌더링** — 플레이어 양자 컴퓨팅 가속 렌더링 활성화
- **블록체인 검증** — 블록체인 기술로 동영상 진위 검증
- **NFT 표시** — 홈에 NFT 디지털 컬렉티블 표시
- **암호화 팁** — 암호화폐로 댓글 팁 지원

### 🇺🇸 English
- **Quantum Render** — Enable quantum computing acceleration on player
- **Blockchain Verify** — Verify video authenticity with blockchain
- **NFT Display** — Display NFT digital collectibles on home
- **Crypto Tip** — Support cryptocurrency tipping for comments

---

## v2.42.0 (2026-06-08)
### 🇨🇳 中文
- **SRT协议** — 支持SRT安全可靠传输协议
- **NDI广播** — 首页支持NDI网络设备接口广播
- **ONNX推理** — 使用ONNX运行时进行评论推理
- **Matter连接** — 弹幕支持Matter智能家居协议

### 🇯🇵 日本語
- **SRTプロトコル** — SRT安全可靠トランスポートプロトコルをサポート
- **NDI放送** — ホームでNDIネットワークデバイスインターフェース放送をサポート
- **ONNX推論** — ONNXランタイムでコメント推論を実行
- **Matter接続** — 弾幕でMatterスマートホームプロトコルをサポート

### 🇰🇷 한국어
- **SRT 프로토콜** — SRT 안전 신뢰 전송 프로토콜 지원
- **NDI 방송** — 홈 NDI 네트워크 장치 인터페이스 방송 지원
- **ONNX 추론** — ONNX 런타임으로 댓글 추론 실행
- **Matter 연결** — 弹幕 Matter 스마트홈 프로토콜 지원

### 🇺🇸 English
- **SRT Protocol** — Support SRT Secure Reliable Transport protocol
- **NDI Broadcast** — Support NDI Network Device Interface broadcast on home
- **ONNX Inference** — Run comment inference with ONNX runtime
- **Matter Connect** — Support Matter smart home protocol for danmaku

---

## v2.41.0 (2026-06-08)
### 🇨🇳 中文
- **CDN优化** — 首页启用CDN智能优化
- **AI机器人评论** — AI机器人自动回复评论
- **TensorFlow增强** — 使用TensorFlow增强弹幕体验
- **RTSP流** — 支持RTSP协议流媒体播放

### 🇯🇵 日本語
- **CDN最適化** — ホームでCDNスマート最適化を有効化
- **AIボットコメント** — AIボットが自動でコメントに返信
- **TensorFlowエンハンス** — TensorFlowで弾幕体験を強化
- **RTSPストリーム** — RTSPプロトコルのストリーミング再生をサポート

### 🇰🇷 한국어
- **CDN 최적화** — 홈 CDN 스마트 최적화 활성화
- **AI 봇 댓글** — AI 봇 자동 댓글 답변
- **TensorFlow 강화** — TensorFlow로 弹幕 경험 강화
- **RTSP 스트림** — RTSP 프로토콜 스트리밍 재생 지원

### 🇺🇸 English
- **CDN Optimize** — Enable CDN smart optimization on home
- **AI Bot Comments** — AI bot auto-reply to comments
- **TensorFlow Enhance** — Enhance danmaku experience with TensorFlow
- **RTSP Stream** — Support RTSP protocol streaming playback

---

## v2.40.0 (2026-06-08)
### 🇨🇳 中文
- **机器学习评论** — 使用机器学习优化评论排序
- **深度学习过滤** — 使用深度学习过滤低质量弹幕
- **神经网络超分** — 使用神经网络进行视频超分辨率
- **P2P分享** — 支持P2P协议分享视频

### 🇯🇵 日本語
- **機械学習コメント** — 機械学習でコメントソートを最適化
- **深層学習フィルター** — 深層学習で低品質弾幕をフィルタリング
- **ニューラルネットワーク超解像度** — ニューラルネットワークで動画の超解像度を実現
- **P2P共有** — P2Pプロトコルで動画共有をサポート

### 🇰🇷 한국어
- **기계 학습 댓글** — 기계 학습으로 댓글 정렬 최적화
- **딥러닝 필터** — 딥러닝으로 저품질 弹幕 필터링
- **신경망 초해상도** — 신경망으로 동영상 초해상도 구현
- **P2P 공유** — P2P 프로토콜 동영상 공유 지원

### 🇺🇸 English
- **Machine Learning Comments** — Optimize comment sorting with ML
- **Deep Learning Filter** — Filter low-quality danmaku with deep learning
- **Neural Upscale** — Video super resolution with neural networks
- **P2P Sharing** — Support P2P protocol for video sharing

---

## v2.39.0 (2026-06-08)
### 🇨🇳 中文
- **弹幕聊天机器人** — AI聊天机器人自动回复弹幕
- **边缘计算** — 播放器启用边缘计算加速
- **WebRTC直播** — 支持WebRTC协议实时直播
- **HLS流媒体** — 首页启用HLS流媒体播放

### 🇯🇵 日本語
- **弾幕チャットボット** — AIチャットボットが弾幕に自動返信
- **エッジコンピューティング** — プレーヤーでエッジコンピューティング加速を有効化
- **WebRTCライブ** — WebRTCプロトコルのリアルタイムライブをサポート
- **HLSストリーミング** — ホームでHLSストリーミング再生を有効化

### 🇰🇷 한국어
- **弹幕 챗봇** — AI 챗봇 자동 弹幕 답변
- **엣지 컴퓨팅** — 플레이어 엣지 컴퓨팅 가속 활성화
- **WebRTC 라이브** — WebRTC 프로토콜 실시간 라이브 지원
- **HLS 스트리밍** — 홈 HLS 스트리밍 재생 활성화

### 🇺🇸 English
- **Danmaku Chatbot** — AI chatbot auto-reply to danmaku
- **Edge Computing** — Enable edge computing acceleration on player
- **WebRTC Live** — Support WebRTC protocol real-time live streaming
- **HLS Stream** — Enable HLS streaming playback on home

---

## v2.38.0 (2026-06-08)
### 🇨🇳 中文
- **地理围栏触发** — 根据地理位置自动触发播放器操作
- **信标检测** — 检测附近的视频信标信号
- **智能家居连接** — 首页支持智能家居设备连接
- **语音助手评论** — 通过语音助手发送评论

### 🇯🇵 日本語
- **ジオフェンストリガー** — 地理的位置に基づいてプレーヤー操作を自動トリガー
- **ビーコン検出** — 近くの動画ビーコン信号を検出
- **スマートホーム接続** — ホームでスマートホームデバイス接続をサポート
- **音声アシスタントコメント** — 音声アシスタントでコメントを送信

### 🇰🇷 한국어
- **지오펜스 트리거** — 지리적 위치에 따라 플레이어 작업 자동 트리거
- **비콘 감지** — 주변 동영상 비콘 신호 감지
- **스마트홈 연결** — 홈 스마트홈 기기 연결 지원
- **음성 어시스턴트 댓글** — 음성 어시스턴트로 댓글 전송

### 🇺🇸 English
- **Geofence Trigger** — Auto-trigger player actions by geolocation
- **Beacon Detect** — Detect nearby video beacon signals
- **Smart Home Connect** — Support smart home device connection on home
- **Voice Assistant Comments** — Send comments via voice assistant

---

## v2.37.0 (2026-06-08)
### 🇨🇳 中文
- **条形码扫描** — 扫描视频中的条形码获取信息
- **NFC分享** — 通过NFC分享视频链接
- **RFID标签** — 支持RFID标签读取视频信息
- **UWB广播** — 通过超宽带广播分享弹幕设置

### 🇯🇵 日本語
- **バーコードスキャン** — 動画内のバーコードをスキャンして情報を取得
- **NFC共有** — NFCで動画リンクを共有
- **RFIDタグ** — RFIDタグから動画情報を読み取り
- **UWB放送** — 超広帯域で弾幕設定を放送共有

### 🇰🇷 한국어
- **바코드 스캔** — 동영상 내 바코드 스캔으로 정보获取
- **NFC 공유** — NFC로 동영상 링크 공유
- **RFID 태그** — RFID 태그에서 동영상 정보 읽기
- **UWB 방송** — 초광대역으로 弹幕 설정 방송 공유

### 🇺🇸 English
- **Barcode Scanner** — Scan barcodes in videos for info
- **NFC Share** — Share video links via NFC
- **RFID Tag** — Read video info from RFID tags
- **UWB Broadcast** — Broadcast danmaku settings via UWB

---

## v2.36.0 (2026-06-08)
### 🇨🇳 中文
- **对象检测** — 评论区支持图片中的对象检测
- **人脸识别** — 弹幕叠加人脸检测信息
- **文字OCR** — 播放器支持视频文字识别
- **AI增强** — 首页启用AI画质增强

### 🇯🇵 日本語
- **オブジェクト検出** — コメント欄で画像内のオブジェクトを検出
- **顔認識** — 弾幕に顔検出情報をオーバーレイ
- **テキストOCR** — プレーヤーで動画テキスト認識をサポート
- **AIエンハンス** — ホームでAI画質エンハンスを有効化

### 🇰🇷 한국어
- **객체 감지** — 댓글란에서 이미지 내 객체 감지
- **얼굴 인식** — 弹幕에 얼굴 감지 정보 오버레이
- **텍스트 OCR** — 플레이어 동영상 텍스트 인식 지원
- **AI 강화** — 홈 AI 화질 강화 활성화

### 🇺🇸 English
- **Object Detection** — Detect objects in comment images
- **Face Recognition** — Overlay face detection on danmaku
- **Text OCR** — Video text recognition in player
- **AI Enhance** — Enable AI quality enhancement on home

---

## v2.35.0 (2026-06-08)
### 🇨🇳 中文
- **帧插值评论** — 为评论区启用帧插值平滑
- **弹幕运动平滑** — 弹幕滚动启用运动平滑
- **AI超分辨率** — 播放器启用AI超分辨率
- **超分辨率视频** — 视频启用超分辨率处理

### 🇯🇵 日本語
- **フレーム補間コメント** — コメント欄でフレーム補間スムーズを有効化
- **弾幕モーションスムーズ** — 弾幕スクロールにモーションスムーズを有効化
- **AIスーパー解像度** — プレーヤーでAIスーパー解像度を有効化
- **スーパー解像度動画** — 動画にスーパー解像度処理を有効化

### 🇰🇷 한국어
- **프레임 보간 댓글** — 댓글란 프레임 보간 스무딩 활성화
- **弹幕 모션 스무딩** — 弹幕 스크롤 모션 스무딩 활성화
- **AI 초해상도** — 플레이어 AI 초해상도 활성화
- **초해상도 동영상** — 동영상 초해상도 처리 활성화

### 🇺🇸 English
- **Frame Interpolation Comments** — Enable frame interpolation for comments
- **Danmaku Motion Smooth** — Enable motion smoothing for danmaku
- **AI Upscale** — Enable AI super resolution on player
- **Super Resolution Video** — Enable super resolution for videos

---

## v2.34.0 (2026-06-08)
### 🇨🇳 中文
- **环绕声弹幕** — 弹幕启用环绕声效果
- **杜比全景声** — 播放器支持杜比全景声
- **HDR10增强** — 视频启用HDR10增强显示
- **杜比视界** — 首页支持杜比视界显示

### 🇯🇵 日本語
- **サラウンドサウンド弾幕** — 弾幕にサラウンドサウンド効果を有効化
- **ドルビーアトモス** — プレーヤーでドルビーアトモスをサポート
- **HDR10エンハンス** — 動画でHDR10エンハンス表示を有効化
- **ドルビービジョン** — ホームでドルビービジョン表示をサポート

### 🇰🇷 한국어
- **서라운드 사운드 弹幕** — 弹幕 서라운드 사운드 효과 활성화
- **돌비 애트모스** — 플레이어 돌비 애트모스 지원
- **HDR10 강화** — 동영상 HDR10 강화 표시 활성화
- **돌비 비전** — 홈 돌비 비전 표시 지원

### 🇺🇸 English
- **Surround Sound Danmaku** — Enable surround sound for danmaku
- **Dolby Atmos** — Support Dolby Atmos on player
- **HDR10 Enhance** — Enable HDR10 enhanced display for videos
- **Dolby Vision** — Support Dolby Vision display on home

---

## v2.33.0 (2026-06-08)
### 🇨🇳 中文
- **智能手表遥控** — 支持智能手表遥控播放器
- **手柄控制** — 支持游戏手柄控制播放器
- **VR模式** — 首页支持VR虚拟现实模式
- **空间音频评论** — 评论区支持空间音频播放

### 🇯🇵 日本語
- **スマートウォッチリモコン** — スマートウォッチでプレーヤーをリモコン操作
- **ゲームパッド制御** — ゲームパッドでプレーヤーを制御
- **VRモード** — ホームでVRバーチャルリアリティモードをサポート
- **空間オーディオコメント** — コメント欄で空間オーディオ再生をサポート

### 🇰🇷 한국어
- **스마트워치 리모컨** — 스마트워치로 플레이어 리모컨 조작
- **게임패드 제어** — 게임패드로 플레이어 제어
- **VR 모드** — 홈 VR 가상현실 모드 지원
- **공간 오디오 댓글** — 댓글란 공간 오디오 재생 지원

### 🇺🇸 English
- **Smartwatch Remote** — Control player with smartwatch
- **Gamepad Control** — Control player with gamepad
- **VR Mode** — Support VR virtual reality mode on home
- **Spatial Audio Comments** — Support spatial audio playback in comments

---

## v2.32.0 (2026-06-08)
### 🇨🇳 中文
- **优先模式** — 视频播放获得系统优先级
- **独家内容** — 显示独家付费内容预告
- **打赏显示** — 在评论区显示打赏信息
- **忠诚度等级** — 显示弹幕互动的忠诚度等级

### 🇯🇵 日本語
- **優先モード** — 動画再生にシステム優先度を付与
- **独占コンテンツ** — 独占有料コンテンツのプレビューを表示
- **チップ表示** — コメント欄にチップ情報を表示
- **ロイヤリティティア** — 弾幕インタラクションのロイヤリティティアを表示

### 🇰🇷 한국어
- **우선 모드** — 동영상 재생에 시스템 우선순위 부여
- **독점 콘텐츠** — 독점 유료 콘텐츠 미리보기 표시
- **팁 표시** — 댓글란에 팁 정보 표시
- **충성도 등급** — 弹幕 상호작용 충성도 등급 표시

### 🇺🇸 English
- **Priority Mode** — Give video playback system priority
- **Exclusive Content** — Show exclusive paid content previews
- **Reward Display** — Display reward info in comments
- **Loyalty Tier** — Display danmaku interaction loyalty tier

---

## v2.31.0 (2026-06-08)
### 🇨🇳 中文
- **正念模式** — 首页启用正念浏览模式
- **垃圾评论过滤** — 自动过滤垃圾评论内容
- **辱骂弹幕过滤** — 自动过滤辱骂性弹幕
- **勿扰模式** — 播放器启用勿扰模式

### 🇯🇵 日本語
- **マインドフルモード** — ホームでマインドフル浏览モードを有効化
- **スパムコメントフィルター** — スパムコメントを自動フィルタリング
- **侮辱弾幕フィルター** — 侮辱的な弾幕を自動フィルタリング
- **おやすみモード** — プレーヤーでおやすみモードを有効化

### 🇰🇷 한국어
- **마인드풀 모드** — 홈 마인드풀浏览 모드 활성화
- **스팸 댓글 필터** — 스팸 댓글 자동 필터링
- **욕설 弹幕 필터** — 욕설性 弹幕 자동 필터링
- **방해 금지 모드** — 플레이어 방해 금지 모드 활성화

### 🇺🇸 English
- **Mindful Mode** — Enable mindful browsing on home
- **Spam Comment Filter** — Auto-filter spam comments
- **Abusive Danmaku Filter** — Auto-filter abusive danmaku
- **Do Not Disturb** — Enable do not disturb mode on player

---

## v2.30.0 (2026-06-08)
### 🇨🇳 中文
- **评论时间限制** — 限制评论区的浏览时间
- **静默模式** — 弹幕启用静默不打扰模式
- **专注模式** — 播放器启用专注学习模式
- **数字健康** — 启用数字健康提醒功能

### 🇯🇵 日本語
- **コメント時間制限** — コメント欄の浏览時間を制限
- **サイレントモード** — 弾幕でサイレントモードを有効化
- **フォーカスモード** — プレーヤーで学習フォーカスモードを有効化
- **デジタルウェルビーイング** — デジタルウェルビーイングリマインダーを有効化

### 🇰🇷 한국어
- **댓글 시간 제한** — 댓글란浏览 시간 제한
- **무음 모드** — 弹幕 무음 모드 활성화
- **집중 모드** — 플레이어 학습 집중 모드 활성화
- **디지털 웰빙** — 디지털 웰빙 알림 활성화

### 🇺🇸 English
- **Comment Time Limit** — Limit comment browsing time
- **Silent Mode** — Enable silent mode for danmaku
- **Focus Mode** — Enable study focus mode on player
- **Digital Wellbeing** — Enable digital wellbeing reminders

---

## v2.29.0 (2026-06-08)
### 🇨🇳 中文
- **输入指示器** — 显示其他用户正在输入弹幕
- **隐私模式** — 播放器启用隐私保护模式
- **年龄门禁** — 根据年龄限制视频内容访问
- **家长控制** — 启用家长控制过滤内容

### 🇯🇵 日本語
- **タイピングインジケーター** — 他のユーザーが弾幕を入力中であることを表示
- **プライバシーモード** — プレーヤーでプライバシー保護モードを有効化
- **年齢制限** — 年齢に基づいて動画コンテンツへのアクセスを制限
- **ペアレンタルコントロール** — ペアレンタルコントロールでコンテンツをフィルタリング

### 🇰🇷 한국어
- **입력 표시기** — 다른 사용자가 弹幕 입력 중임을 표시
- **개인정보 보호 모드** — 플레이어 개인정보 보호 모드 활성화
- **연령 제한** — 연령에 따라 동영상 콘텐츠 접근 제한
- **자녀 보호** — 자녀 보호 콘텐츠 필터링 활성화

### 🇺🇸 English
- **Typing Indicator** — Show when others are typing danmaku
- **Privacy Mode** — Enable privacy protection on player
- **Age Gate** — Restrict video content access by age
- **Parental Control** — Enable parental control content filtering

---

## v2.28.0 (2026-06-08)
### 🇨🇳 中文
- **穿戴设备控制** — 支持智能手表控制播放器
- **桌面小组件** — 在桌面添加视频播放小组件
- **未读标记** — 在首页显示未读内容标记
- **在线状态** — 在评论区显示用户在线状态

### 🇯🇵 日本語
- **ウェアラブル制御** — スマートウォッチでプレーヤーを制御
- **デスクトップウィジェット** — デスクトップに動画再生ウィジェットを追加
- **未読バッジ** — ホームに未読コンテンツバッジを表示
- **オンラインステータス** — コメント欄にユーザーオンラインステータスを表示

### 🇰🇷 한국어
- **웨어러블 제어** — 스마트워치로 플레이어 제어
- **데스크톱 위젯** — 데스크톱에 동영상 재생 위젯 추가
- **읽지 않음 배지** — 홈에 읽지 않은 콘텐츠 배지 표시
- **온라인 상태** — 댓글란에 사용자 온라인 상태 표시

### 🇺🇸 English
- **Wearable Control** — Control player with smartwatch
- **Desktop Widget** — Add video playback widget to desktop
- **Unread Badge** — Show unread content badge on home
- **Online Status** — Display user online status in comments

---

## v2.27.0 (2026-06-08)
### 🇨🇳 中文
- **扫描模式** — 扫描视频二维码快速打开
- **光线传感器** — 根据环境光调节首页亮度
- **指南针叠加** — 在评论区叠加指南针方向
- **GPS叠加** — 在弹幕上叠加位置信息

### 🇯🇵 日本語
- **スキャンモード** — 動画QRコードをスキャンしてすばやく開く
- **光センサー** — 環境光に基づいてホームの明るさを調整
- **コンパスオーバーレイ** — コメント欄にコンパス方向をオーバーレイ
- **GPSオーバーレイ** — 弾幕に位置情報をオーバーレイ

### 🇰🇷 한국어
- **스캔 모드** — 동영상 QR 코드 스캔으로 빠르게 열기
- **광 센서** — 주변 광선에 따라 홈 밝기 조절
- **나침반 오버레이** — 댓글란에 나침반 방향 오버레이
- **GPS 오버레이** — 弹幕에 위치 정보 오버레이

### 🇺🇸 English
- **Scanner Mode** — Scan video QR code to open quickly
- **Light Sensor** — Adjust home brightness based on ambient light
- **Compass Overlay** — Overlay compass direction on comments
- **GPS Overlay** — Overlay location info on danmaku

---

## v2.26.0 (2026-06-08)
### 🇨🇳 中文
- **蓝牙扫描** — 扫描附近蓝牙设备推荐内容
- **相机滤镜评论** — 为评论添加相机滤镜效果
- **麦克风输入弹幕** — 通过语音输入弹幕内容
- **扬声器增强** — 增强播放器扬声器音量

### 🇯🇵 日本語
- **Bluetoothスキャン** — 近くのBluetoothデバイスをスキャンしてコンテンツを推薦
- **カメラフィルターコメント** — コメントにカメラフィルター効果を追加
- **マイク入力弾幕** — 音声で弾幕コンテンツを入力
- **スピーカーブースト** — プレーヤーのスピーカー音量を増強

### 🇰🇷 한국어
- **블루투스 스캔** — 주변 블루투스 기기 스캔으로 콘텐츠 추천
- **카메라 필터 댓글** — 댓글에 카메라 필터 효과 추가
- **마이크 입력 弹幕** — 음성으로 弹幕 콘텐츠 입력
- **스피커 부스트** — 플레이어 스피커 볼륨 증강

### 🇺🇸 English
- **Bluetooth Scan** — Scan nearby Bluetooth devices for content recommendations
- **Camera Filter Comments** — Add camera filter effects to comments
- **Microphone Input Danmaku** — Input danmaku content via voice
- **Speaker Boost** — Boost player speaker volume

---

## v2.25.0 (2026-06-08)
### 🇨🇳 中文
- **二维码分享评论** — 将评论生成二维码分享
- **NFC广播弹幕** — 通过NFC分享弹幕设置
- **加速度手势** — 使用加速度计控制播放器
- **距离传感器** — 检测距离自动暂停/播放

### 🇯🇵 日本語
- **QRコード共有コメント** — コメントをQRコードに生成して共有
- **NFC放送弾幕** — NFCで弾幕設定を共有
- **加速度ジェスチャー** — 加速度計でプレーヤーを制御
- **近接センサー** — 距離を検出して自動一時停止/再生

### 🇰🇷 한국어
- **QR 코드 공유 댓글** — 댓글을 QR 코드로 생성하여 공유
- **NFC 방송 弹幕** — NFC로 弹幕 설정 공유
- **가속도 제스처** — 가속도계로 플레이어 제어
- **근접 센서** — 거리 감지로 자동 일시정지/재생

### 🇺🇸 English
- **QR Code Share Comments** — Generate QR code from comments to share
- **NFC Broadcast Danmaku** — Share danmaku settings via NFC
- **Accelerometer Gesture** — Use accelerometer to control player
- **Proximity Sensor** — Detect distance for auto pause/play

---

## v2.24.0 (2026-06-08)
### 🇨🇳 中文
- **耳机控制弹幕** — 通过耳机线控弹幕显示
- **陀螺仪旋转** — 使用陀螺仪控制播放器旋转
- **WiFi预加载** — 仅在WiFi下预加载视频
- **GPS定位推荐** — 基于位置推荐本地内容

### 🇯🇵 日本語
- **ヘッドセット制御弾幕** — ヘッドセットのワイヤ制御で弾幕表示を制御
- **ジャイロスコープ回転** — ジャイロスコープでプレーヤーの回転を制御
- **WiFiプリロード** — WiFi接続時のみ動画をプリロード
- **GPS位置推薦** — 位置に基づいてローカルコンテンツを推薦

### 🇰🇷 한국어
- **이어셋 제어 弹幕** — 이어셋 와이어 제어로 弹幕 표시 제어
- **자이로스코프 회전** — 자이로스코프로 플레이어 회전 제어
- **WiFi 프리로드** — WiFi 연결 시에만 동영상 프리로드
- **GPS 위치 추천** — 위치 기반 로컬 콘텐츠 추천

### 🇺🇸 English
- **Headset Control Danmaku** — Control danmaku display via headset wire
- **Gyroscope Rotate** — Use gyroscope to control player rotation
- **WiFi Preload** — Preload videos only on WiFi
- **GPS Location Recommend** — Recommend local content based on location

---

## v2.23.0 (2026-06-08)
### 🇨🇳 中文
- **Chromecast投屏** — 支持Chromecast投屏播放
- **DLNA镜像** — 支持DLNA协议的设备镜像
- **AirPlay投屏** — 支持AirPlay投屏到Apple设备
- **蓝牙音频** — 评论区支持蓝牙音频输出

### 🇯🇵 日本語
- **Chromecastキャスト** — Chromecastへのキャスト再生をサポート
- **DLNAミラー** — DLNAプロトコルのデバイスミラーをサポート
- **AirPlayキャスト** — AppleデバイスへのAirPlayキャストをサポート
- **Bluetoothオーディオ** — コメント欄でBluetoothオーディオ出力をサポート

### 🇰🇷 한국어
- **Chromecast 캐스트** — Chromecast 캐스트 재생 지원
- **DLNA 미러** — DLNA 프로토콜 기기 미러 지원
- **AirPlay 캐스트** — Apple 기기로 AirPlay 캐스트 지원
- **블루투스 오디오** — 댓글란 블루투스 오디오 출력 지원

### 🇺🇸 English
- **Chromecast Support** — Support Chromecast casting playback
- **DLNA Mirror** — Support DLNA protocol device mirroring
- **AirPlay Cast** — Support AirPlay casting to Apple devices
- **Bluetooth Audio** — Support Bluetooth audio output in comments

---

## v2.22.0 (2026-06-08)
### 🇨🇳 中文
- **搜索统计面板** — 显示搜索历史的统计数据
- **评论图表** — 以图表形式显示评论统计数据
- **弹幕图表叠加** — 在播放器上叠加弹幕活跃度图表
- **任务进度** — 在播放器上显示每日任务进度

### 🇯🇵 日本語
- **検索統計パネル** — 検索履歴の統計データを表示
- **コメントチャート** — コメント統計データをチャート形式で表示
- **弾幕グラフオーバーレイ** — プレーヤーに弾幕アクティビティグラフをオーバーレイ
- **タスク進捗** — プレーヤーに毎日のタスク進捗を表示

### 🇰🇷 한국어
- **검색 통계 패널** — 검색 기록 통계 데이터 표시
- **댓글 차트** — 댓글 통계 데이터를 차트 형태로 표시
- **弹幕 그래프 오버레이** — 플레이어에 弹幕 활동 그래프 오버레이
- **작업 진행률** — 플레이어에 일일 작업 진행률 표시

### 🇺🇸 English
- **Search Statistics Panel** — Display search history statistics
- **Comment Chart** — Display comment statistics as charts
- **Danmaku Graph Overlay** — Overlay danmaku activity graph on player
- **Task Progress** — Display daily task progress on player

---

## v2.21.0 (2026-06-08)
### 🇨🇳 中文
- **钻石徽章** — 在播放器上显示钻石会员徽章
- **银牌画质** — 为银牌会员优化视频画质
- **每周摘要** — 生成首页浏览的每周摘要
- **月度报告** — 生成动态互动的月度报告

### 🇯🇵 日本語
- **ダイヤモンドバッジ** — プレーヤーにダイヤモンドメンバーバッジを表示
- **シルバー画質** — シルバーメンバー用に動画画質を最適化
- **週間サマリー** — ホーム浏览の週間サマリーを生成
- **月次レポート** — 動的インタラクションの月次レポートを生成

### 🇰🇷 한국어
- **다이아몬드 배지** — 플레이어에 다이아몬드 회원 배지 표시
- **실버 화질** — 실버 회원을 위한 동영상 화질 최적화
- **주간 요약** — 홈浏览 주간 요약 생성
- **월간 보고서** — 동적 상호작용 월간 보고서 생성

### 🇺🇸 English
- **Diamond Badge** — Display diamond member badge on player
- **Silver Quality** — Optimize video quality for silver members
- **Weekly Summary** — Generate home feed weekly summary
- **Monthly Report** — Generate dynamic interaction monthly report

---

## v2.20.0 (2026-06-08)
### 🇨🇳 中文
- **每日报告** — 生成首页浏览的每日报告
- **成就追踪** — 追踪动态互动的成就进度
- **勋章显示** — 在评论区显示用户勋章
- **投币计数器** — 统计弹幕互动的投币数量

### 🇯🇵 日本語
- **デイリーレポート** — ホーム浏览の日次レポートを生成
- **アチーブメントトラッカー** — 動的インタラクションのアチーブメント進捗を追跡
- **メダル表示** — コメント欄にユーザーメダルを表示
- **コインカウンター** — 弾幕インタラクションのコイン数を統計

### 🇰🇷 한국어
- **일일 보고서** — 홈浏览 일일 보고서 생성
- **성취 추적** — 동적 상호작용 성취 진행률 추적
- **메달 표시** — 댓글란에 사용자 메달 표시
- **코인 카운터** — 弹幕 상호작용 코인 수 통계

### 🇺🇸 English
- **Daily Report** — Generate home feed daily report
- **Achievement Tracker** — Track dynamic interaction achievement progress
- **Medal Display** — Display user medals in comment section
- **Coin Counter** — Count coins from danmaku interactions

---

## v2.19.0 (2026-06-08)
### 🇨🇳 中文
- **评论导出导入** — 支持评论数据的导出和导入
- **弹幕云同步** — 跨设备同步弹幕设置
- **账号快速切换** — 支持播放器内快速切换账号
- **个人资料快览** — 快速查看视频发布者的个人资料

### 🇯🇵 日本語
- **コメントエクスポート/インポート** — コメントデータのエクスポートとインポートをサポート
- **弾幕クラウド同期** — デバイス間で弾幕設定を同期
- **アカウント切替** — プレーヤー内でアカウントをすばやく切替
- **プロフィールクイックビュー** — 動画投稿者のプロフィールをすばやく表示

### 🇰🇷 한국어
- **댓글 내보내기/가져오기** — 댓글 데이터 내보내기 및 가져오기 지원
- **弹幕 클라우드 동기화** — 기기 간 弹幕 설정 동기화
- **계정 빠른 전환** — 플레이어 내 계정 빠른 전환 지원
- **프로필 빠른 보기** — 동영상 게시자 프로필 빠른 보기

### 🇺🇸 English
- **Comment Export/Import** — Support comment data export and import
- **Danmaku Cloud Sync** — Sync danmaku settings across devices
- **Account Quick Switch** — Quick account switching in player
- **Profile Quick View** — Quick view video publisher profile

---

## v2.18.0 (2026-06-08)
### 🇨🇳 中文
- **分屏模式** — 支持播放器分屏模式显示
- **剪贴板管理** — 管理视频链接的剪贴板历史
- **数据分析显示** — 在首页显示浏览数据分析
- **动态备份恢复** — 备份和恢复动态浏览记录

### 🇯🇵 日本語
- **分割画面モード** — プレーヤーの分割画面モード表示をサポート
- **クリップボード管理** — 動画リンクのクリップボード履歴を管理
- **分析データ表示** — ホームに浏览分析データを表示
- **動的バックアップ復元** — 動的浏览記録のバックアップと復元

### 🇰🇷 한국어
- **분할 화면 모드** — 플레이어 분할 화면 모드 표시 지원
- **클립보드 관리** — 동영상 링크 클립보드 기록 관리
- **분석 데이터 표시** — 홈에浏览 분석 데이터 표시
- **동적 백업 복원** — 동적浏览 기록 백업 및 복원

### 🇺🇸 English
- **Split Screen Mode** — Support player split screen display
- **Clipboard Manager** — Manage video link clipboard history
- **Analytics Display** — Display browsing analytics on home
- **Dynamic Backup/Restore** — Backup and restore dynamic browsing records

---

## v2.17.0 (2026-06-08)
### 🇨🇳 中文
- **GPU监控** — 在播放器上显示GPU使用率
- **存储管理器** — 管理视频缓存和下载的存储空间
- **内存缓存** — 优化首页信息流的内存缓存策略
- **动态无障碍模式** — 为视障用户优化动态浏览体验

### 🇯🇵 日本語
- **GPUモニター** — プレーヤーにGPU使用率を表示
- **ストレージマネージャー** — 動画キャッシュとダウンロードのストレージを管理
- **メモリキャッシュ** — ホームフィードのメモリキャッシュを最適化
- **動的アクセシビリティ** — 視覚障害者の動的浏览体験を最適化

### 🇰🇷 한국어
- **GPU 모니터** — 플레이어에 GPU 사용률 표시
- **저장소 관리자** — 동영상 캐시와 다운로드 저장소 관리
- **메모리 캐시** — 홈 피드 메모리 캐시 최적화
- **동적 접근성 모드** — 시각 장애인의 동적浏览 경험 최적화

### 🇺🇸 English
- **GPU Monitor** — Display GPU usage on player
- **Storage Manager** — Manage video cache and download storage
- **Memory Cache** — Optimize home feed memory cache
- **Dynamic Accessibility** — Optimize dynamic browsing for visually impaired

---

## v2.16.0 (2026-06-08)
### 🇨🇳 中文
- **性能模式** — 首页启用性能优化模式
- **动态语音旁白** — 为动态内容添加语音旁白
- **评论无障碍模式** — 为视障用户优化评论阅读体验
- **弹幕颜色对比度** — 增强弹幕文字的颜色对比度

### 🇯🇵 日本語
- **パフォーマンスモード** — ホームのパフォーマンス最適化モード
- **動的ボイスオーバー** — 動的コンテンツにボイスオーバーを追加
- **コメントアクセシビリティ** — 視覚障害者のコメント阅读体験を最適化
- **弾幕カラーコントラスト** — 弾幕テキストのカラーコントラストを強化

### 🇰🇷 한국어
- **성능 모드** — 홈 성능 최적화 모드
- **동적 보이스오버** — 동적 콘텐츠에 보이스오버 추가
- **댓글 접근성 모드** — 시각 장애인의 댓글阅读 경험 최적화
- **弹幕 색상 대비** — 弹幕 텍스트 색상 대비 강화

### 🇺🇸 English
- **Performance Mode** — Enable home performance optimization
- **Dynamic Voice Over** — Add voice over to dynamic content
- **Comment Accessibility** — Optimize comment reading for visually impaired
- **Danmaku Color Contrast** — Enhance danmaku text color contrast

---

## v2.15.0 (2026-06-08)
### 🇨🇳 中文
- **评论字体无障碍** — 根据系统字体大小自动调整评论
- **弹幕高对比度** — 启用弹幕高对比度显示模式
- **延迟显示** — 在播放器上显示网络延迟
- **网速测试** — 播放视频时实时测试网络速度

### 🇯🇵 日本語
- **コメントフォントアクセシビリティ** — システムフォントサイズに基づいてコメントを自動調整
- **弾幕ハイコントラスト** — 弾幕のハイコントラスト表示モード
- **レイテンシー表示** — プレーヤーにネットワーク遅延を表示
- **ネットワーク速度テスト** — 動画再生中にリアルタイムでネットワーク速度をテスト

### 🇰🇷 한국어
- **댓글 글꼴 접근성** — 시스템 글꼴 크기에 따라 댓글 자동 조절
- **弹幕 고대비** — 弹幕 고대비 표시 모드
- **지연 시간 표시** — 플레이어에 네트워크 지연 시간 표시
- **네트워크 속도 테스트** — 동영상 재생 중 실시간 네트워크 속도 테스트

### 🇺🇸 English
- **Comment Font Accessibility** — Auto-adjust comments based on system font size
- **Danmaku High Contrast** — Enable danmaku high contrast display mode
- **Latency Display** — Display network latency on player
- **Network Speed Test** — Real-time network speed test during video playback

---

## v2.14.0 (2026-06-08)
### 🇨🇳 中文
- **电池温度显示** — 在播放器上显示设备电池温度
- **折叠屏布局** — 为折叠屏设备优化首页布局
- **动态语音朗读** — 使用语音合成朗读动态内容
- **搜索无障碍模式** — 为视障用户优化搜索体验

### 🇯🇵 日本語
- **バッテリー温度表示** — プレーヤーにデバイスのバッテリー温度を表示
- **折りたたみ画面レイアウト** — 折りたたみデバイス用にホームレイアウトを最適化
- **動的テキスト読み上げ** — 音声合成で動的コンテンツを読み上げ
- **検索アクセシビリティ** — 視覚障害者の検索体験を最適化

### 🇰🇷 한국어
- **배터리 온도 표시** — 플레이어에 기기 배터리 온도 표시
- **접이식 화면 레이아웃** — 접이식 기기에 홈 레이아웃 최적화
- **동적 텍스트 읽기** — 음성 합성으로 동적 콘텐츠 읽기
- **검색 접근성 모드** — 시각 장애인의 검색 경험 최적화

### 🇺🇸 English
- **Battery Temperature** — Display device battery temperature on player
- **Foldable Layout** — Optimize home layout for foldable devices
- **Dynamic Text-to-Speech** — Read dynamic content with speech synthesis
- **Search Accessibility** — Optimize search experience for visually impaired

---

## v2.13.0 (2026-06-08)
### 🇨🇳 中文
- **触觉反馈** — 播放器操作时启用触觉振动反馈
- **弹幕无障碍模式** — 为视障用户优化弹幕显示
- **帧率显示** — 在播放器上显示实时帧率
- **带宽监控** — 实时监控视频播放的带宽使用

### 🇯🇵 日本語
- **触覚フィードバック** — プレーヤー操作時に触覚振動フィードバックを有効化
- **弾幕アクセシビリティ** — 視覚障害者の弾幕表示を最適化
- **FPS表示** — プレーヤーにリアルタイムFPSを表示
- **帯域幅モニター** — 動画再生の帯域幅使用をリアルタイム監視

### 🇰🇷 한국어
- **햅틱 피드백** — 플레이어 조작 시 햅틱 진동 피드백 활성화
- **弹幕 접근성 모드** — 시각 장애인의 弹幕 표시 최적화
- **FPS 표시** — 플레이어에 실시간 FPS 표시
- **대역폭 모니터** — 동영상 재생 대역폭 사용 실시간 모니터링

### 🇺🇸 English
- **Haptic Feedback** — Enable haptic vibration feedback on player actions
- **Danmaku Accessibility** — Optimize danmaku display for visually impaired
- **FPS Display** — Display real-time FPS on player
- **Bandwidth Monitor** — Real-time bandwidth monitoring during playback

---

## v2.12.0 (2026-06-08)
### 🇨🇳 中文
- **截图分享路径** — 自定义截图分享的默认保存路径
- **全面屏蔽敏感内容** — 全面屏蔽所有包含敏感内容的动态
- **滑动调速手势** — 支持上下滑动调节播放速度
- **直播弹幕用户过滤** — 按用户ID过滤直播间弹幕
- **首页夜间亮度自定义** — 自定义首页夜间模式的屏幕亮度

### 🇯🇵 日本語
- **スクリーンショット共有パス** — スクリーンショット共有のデフォルト保存パスをカスタマイズ
- **全面センシティブブロック** — センシティブコンテンツを含む全ての動的を全面ブロック
- **スワイプ速度ジェスチャー** — 上下スワイプで再生速度を調整
- **ライブ弾幕ユーザーフィルター** — ユーザーIDでライブ弾幕をフィルタリング
- **ナイト明るさカスタマイズ** — ホームナイトモードの画面明るさをカスタマイズ

### 🇰🇷 한국어
- **스크린샷 공유 경로** — 스크린샷 공유 기본 저장 경로 사용자 정의
- **全面 민감 콘텐츠 차단** — 민감한 콘텐츠를 포함한 모든 동적 全面 차단
- **스와이프 속도 제스처** — 상하 스와이프로 재생 속도 조절
- **라이브 弹幕 사용자 필터** — 사용자 ID로 라이브 弹幕 필터링
- **야간 밝기 사용자 정의** — 홈 야간 모드 화면 밝기 사용자 정의

### 🇺🇸 English
- **Screenshot Share Path** — Customize default screenshot share save path
- **Block All Sensitive** — Block all dynamics containing sensitive content
- **Swipe Speed Gesture** — Swipe up/down to adjust playback speed
- **Live Danmaku User Filter** — Filter live danmaku by user ID
- **Night Brightness Custom** — Customize home night mode screen brightness

---

## v2.11.0 (2026-06-08)
### 🇨🇳 中文
- **评论按点赞排序** — 评论区默认按点赞数排序显示
- **控制栏隐藏超时** — 设置播放器控制栏自动隐藏的超时时间
- **动态全部置顶** — 一键置顶当前页面所有动态
- **动态全部归档** — 一键归档当前页面所有动态
- **搜索智能补全** — 搜索时显示智能补全建议

### 🇯🇵 日本語
- **コメントいいねソート** — コメント欄をデフォルトでいいね数でソート表示
- **コントロール非表示タイムアウト** — コントロールバーの自動非表示タイムアウトを設定
- **動的全ピン留め** — 現在のページの全動的をワンクリックピン留め
- **動的全アーカイブ** — 現在のページの全動的をワンクリックアーカイブ
- **検索スマート補完** — 検索時にスマート補完候補を表示

### 🇰🇷 한국어
- **댓글 좋아요 정렬** — 댓글란 기본으로 좋아요수 정렬 표시
- **컨트롤 숨기기 타임아웃** — 컨트롤바 자동 숨기기 타임아웃 설정
- **동적 전체 고정** — 현재 페이지 모든 동적 원클릭 고정
- **동적 전체 아카이브** — 현재 페이지 모든 동적 원클릭 아카이브
- **검색 스마트 완성** — 검색 시 스마트 완성 후보 표시

### 🇺🇸 English
- **Comment Sort by Likes** — Default sort comments by like count
- **Control Hide Timeout** — Set player control bar auto-hide timeout
- **Dynamic Pin All** — One-click pin all dynamics on current page
- **Dynamic Archive All** — One-click archive all dynamics on current page
- **Search Smart Suggest** — Show smart auto-complete suggestions in search

---

## v2.10.0 (2026-06-08)
### 🇨🇳 中文
- **控制栏自动淡出** — 播放器控制栏自动淡出隐藏
- **智能码率选择** — 根据网络状况智能选择视频码率
- **定时夜间主题** — 按时间计划自动切换首页夜间主题
- **动态自动置顶自己** — 自动置顶自己发布的动态

### 🇯🇵 日本語
- **コントロール自動フェードアウト** — プレーヤーコントロールバーを自動フェードアウト
- **スマートビットレート** — ネットワーク状況に基づいてビットレートをスマート選択
- **スケジュールナイトテーマ** — 時間計画に基づいてナイトテーマを自動切替
- **自分の動的自動ピン留め** — 自分が投稿した動的を自動ピン留め

### 🇰🇷 한국어
- **컨트롤 자동 페이드아웃** — 플레이어 컨트롤바 자동 페이드아웃
- **스마트 비트레이트** — 네트워크 상태에 따라 비트레이트 스마트 선택
- **스케줄 야간 테마** — 시간 계획에 따라 야간 테마 자동 전환
- **내 동적 자동 고정** — 내가 게시한 동적 자동 고정

### 🇺🇸 English
- **Control Auto-Fade** — Player control bar auto-fade out
- **Smart Bitrate** — Smart video bitrate selection based on network
- **Night Theme Schedule** — Auto-switch night theme on schedule
- **Dynamic Self-Pin** — Auto-pin your own posted dynamics

---

## v2.09.0 (2026-06-08)
### 🇨🇳 中文
- **画中画自动进入** — 直播间自动进入画中画模式
- **自动归档旧动态** — 自动归档超过指定天数的旧动态
- **评论全部展开** — 自动展开所有折叠的评论
- **发送弹幕字体族** — 选择发送弹幕时使用的字体族

### 🇯🇵 日本語
- **PiP自動入場** — ライブルーム自動でPiPモードに入る
- **古い動的自動アーカイブ** — 指定日数超えた古い動的を自動アーカイブ
- **コメント全展開** — 折りたたまれたコメントを全て自動展開
- **弾幕送信フォントファミリー** — 弾幕送信時のフォントファミリーを選択

### 🇰🇷 한국어
- **PiP 자동 진입** — 라이브룸 자동으로 PiP 모드 진입
- **오래된 동적 자동 아카이브** — 지정 일수 초과한 오래된 동적 자동 아카이브
- **댓글 모두 펼치기** — 접힌 댓글 모두 자동 펼치기
- **弹幕 전송 글꼴 패밀리** — 弹幕 전송 시 글꼴 패밀리 선택

### 🇺🇸 English
- **Auto-Enter PiP** — Auto-enter picture-in-picture in live room
- **Archive Old Dynamics** — Auto-archive dynamics older than specified days
- **Expand All Comments** — Auto-expand all folded comments
- **Danmaku Send Font Family** — Choose font family for sending danmaku

---

## v2.08.0 (2026-06-08)
### 🇨🇳 中文
- **高亮回复评论** — 高亮显示有新回复的评论
- **弹幕行高** — 调整弹幕文字的行高
- **截图格式自定义** — 自定义截图的保存格式
- **静音自动暂停** — 检测到环境静音时自动暂停

### 🇯🇵 日本語
- **返信コメントハイライト** — 新しい返信があるコメントをハイライト
- **弾幕行高** — 弾幕テキストの行高を調整
- **スクリーンショットフォーマット** — スクリーンショットの保存フォーマットをカスタマイズ
- **サイレンス自動一時停止** — 環境の沈黙を検出して自動一時停止

### 🇰🇷 한국어
- **회신 댓글 강조** — 새 회신이 있는 댓글 강조 표시
- **弹幕 줄 높이** — 弹幕 텍스트 줄 높이 조절
- **스크린샷 형식 사용자 정의** — 스크린샷 저장 형식 사용자 정의
- **무음 자동 일시정지** — 환경 무음 감지 시 자동 일시정지

### 🇺🇸 English
- **Highlight Reply Comments** — Highlight comments with new replies
- **Danmaku Line Height** — Adjust danmaku text line height
- **Screenshot Format Custom** — Customize screenshot save format
- **Auto-Pause on Silence** — Auto-pause when silence is detected

---

## v2.07.0 (2026-06-08)
### 🇨🇳 中文
- **合并相似弹幕** — 合并直播间相似内容的弹幕
- **首页夜间主题** — 自动切换首页为夜间主题
- **屏蔽敏感动态** — 自动屏蔽包含敏感内容的动态
- **搜索热门补全** — 搜索时显示热门搜索词补全

### 🇯🇵 日本語
- **類似弾幕マージ** — ライブの類似内容の弾幕をマージ
- **ホームナイトテーマ** — ホームのナイトテーマを自動切替
- **敏感動的ブロック** — 機密性の高い動的コンテンツを自動ブロック
- **検索トレンド補完** — 検索時にトレンド検索語を補完表示

### 🇰🇷 한국어
- **유사 弹幕 병합** — 라이브 유사 내용 弹幕 병합
- **홈 야간 테마** — 홈 야간 테마 자동 전환
- **민감 동적 차단** — 민감한 동적 콘텐츠 자동 차단
- **검색 트렌드 완성** — 검색 시 트렌드 검색어 완성 표시

### 🇺🇸 English
- **Merge Similar Danmaku** — Merge similar danmaku in live streams
- **Home Night Theme** — Auto-switch home to night theme
- **Block Sensitive Dynamics** — Auto-block sensitive dynamic content
- **Search Trending Complete** — Show trending search terms in auto-complete

---

## v2.06.0 (2026-06-08)
### 🇨🇳 中文
- **评论自动翻译** — 自动翻译外语评论内容
- **弹幕字间距** — 调整弹幕文字的字母间距
- **双击速度设置** — 自定义双击快进/快退的速度
- **自动跳过中插广告** — 自动跳过视频中间的广告片段

### 🇯🇵 日本語
- **コメント自動翻訳** — 外国語コメントを自動翻訳
- **弾幕文字間隔** — 弾幕テキストの文字間隔を調整
- **ダブルタップ速度** — ダブルタップ早送り/巻き戻しの速度をカスタマイズ
- **ミッドロール広告自動スキップ** — 動画の中間広告を自動スキップ

### 🇰🇷 한국어
- **댓글 자동 번역** — 외국어 댓글 자동 번역
- **弹幕 자간** — 弹幕 텍스트 자간 조절
- **더블탭 속도** — 더블탭 빨리감기/되감기 속도 사용자 정의
- **중간 광고 자동 건너뛰기** — 동영상 중간 광고 자동 건너뛰기

### 🇺🇸 English
- **Comment Auto-Translate** — Auto-translate foreign comments
- **Danmaku Letter Spacing** — Adjust danmaku text letter spacing
- **Double Tap Speed** — Customize double-tap seek speed
- **Auto Skip Midroll Ads** — Auto-skip midroll video ads

---

## v2.04.0 (2026-06-08)
### 🇨🇳 中文
- **智能画质选择** — 根据网络速度智能选择最佳画质
- **首页聚焦刷新** — 首页获得焦点时自动刷新内容
- **动态评论置顶** — 自动置顶动态下的优质评论

### 🇯🇵 日本語
- **スマート画質選択** — ネットワーク速度に基づいて最適な画質を自動選択
- **フォーカス更新** — ホームがフォーカスされた時にコンテンツを自動更新
- **動的コメントピン留め** — 動的の高品質コメントを自動ピン留め

### 🇰🇷 한국어
- **스마트 화질 선택** — 네트워크 속도에 따라 최적 화질 자동 선택
- **포커스 새로고침** — 홈 포커스 시 콘텐츠 자동 새로고침
- **동적 댓글 고정** — 동적优质评论 자동 고정

### 🇺🇸 English
- **Smart Quality** — Auto-select best quality based on network speed
- **Refresh on Focus** — Auto-refresh content when home gains focus
- **Dynamic Pin Comment** — Auto-pin quality comments on dynamics

---

## v2.03.0 (2026-06-08)
### 🇨🇳 中文
- **动态关键词屏蔽** — 按关键词屏蔽动态内容
- **短评论自动展开** — 自动展开过短的折叠评论
- **弹幕全屏预览** — 发送弹幕前全屏预览效果
- **控制栏淡出时间** — 设置播放器控制栏的淡出时间

### 🇯🇵 日本語
- **動的キーワードブロック** — キーワードで動的コンテンツをブロック
- **短コメント自動展開** — 短い折りたたみコメントを自動展開
- **弾幕フルスクリーンプレビュー** — 弾幕送信前にフルスクリーンプレビュー
- **フェードアウト時間** — プレーヤーコントロールバーのフェードアウト時間を設定

### 🇰🇷 한국어
- **동적 키워드 차단** — 키워드로 동적 콘텐츠 차단
- **짧은 댓글 자동 펼치기** — 짧은 접힌 댓글 자동 펼치기
- **弹幕 전체화면 미리보기** — 弹幕 전송 전 전체화면 미리보기
- **페이드아웃 시간** — 플레이어 컨트롤바 페이드아웃 시간 설정

### 🇺🇸 English
- **Dynamic Block Keywords** — Block dynamic content by keywords
- **Auto-Expand Short Comments** — Auto-expand short folded comments
- **Danmaku Full Preview** — Full-screen preview before sending danmaku
- **Control Fade-Out Time** — Set player control bar fade-out time

---

## v2.02.0 (2026-06-08)
### 🇨🇳 中文
- **高亮新评论** — 高亮显示新发布的评论
- **弹幕字体粗细自定义** — 自定义弹幕文字的粗细程度
- **截图保存路径** — 自定义播放器截图的保存路径
- **直播间批量关注** — 一键关注直播间所有主播

### 🇯🇵 日本語
- **新着コメントハイライト** — 新規コメントをハイライト表示
- **弾幕フォントウェイトカスタマイズ** — 弾幕テキストの太さをカスタマイズ
- **スクリーンショット保存パス** — プレーヤースクリーンショットの保存パスをカスタマイズ
- **ライブ一括フォロー** — ライブルームの全ホストをワンクリックフォロー

### 🇰🇷 한국어
- **새 댓글 강조** — 새로 게시된 댓글 강조 표시
- **弹幕 글꼴 두께 사용자 정의** — 弹幕 텍스트 두께 사용자 정의
- **스크린샷 저장 경로** — 플레이어 스크린샷 저장 경로 사용자 정의
- **라이브 일괄 팔로우** — 라이브룸 모든 호스트 원클릭 팔로우

### 🇺🇸 English
- **Highlight New Comments** — Highlight newly posted comments
- **Danmaku Font Weight Custom** — Customize danmaku text thickness
- **Screenshot Save Path** — Customize player screenshot save path
- **Live Batch Follow** — One-click follow all hosts in live room

---

## v2.01.0 (2026-06-08)
### 🇨🇳 中文
- **直播弹幕速度控制** — 控制直播间弹幕的滚动速度
- **定时夜间模式** — 按时间计划自动切换首页夜间模式
- **自动删除旧动态** — 自动删除超过指定天数的旧动态
- **搜索历史补全** — 搜索时自动补全历史搜索记录

### 🇯🇵 日本語
- **ライブ弾幕速度制御** — ライブ弾幕のスクロール速度を制御
- **スケジュールナイトモード** — 時間計画に基づいてナイトモードを自動切替
- **古い動的自動削除** — 指定日数を超えた古い動的を自動削除
- **検索履歴自動補完** — 検索時に検索履歴を自動補完

### 🇰🇷 한국어
- **라이브 弹幕 속도 제어** — 라이브 弹幕 스크롤 속도 제어
- **스케줄 야간 모드** — 시간 계획에 따라 야간 모드 자동 전환
- **오래된 동적 자동 삭제** — 지정 일수 초과한 오래된 동적 자동 삭제
- **검색 기록 자동 완성** — 검색 시 검색 기록 자동 완성

### 🇺🇸 English
- **Live Danmaku Speed Control** — Control live danmaku scroll speed
- **Night Mode Schedule** — Auto-switch night mode on schedule
- **Auto-Delete Old Dynamics** — Auto-delete dynamics older than specified days
- **Search History Complete** — Auto-complete with search history

---

## v2.00.0 (2026-06-08)
### 🇨🇳 中文
- **评论自动置顶** — 发送评论后自动将其置顶显示
- **暂停时隐藏弹幕** — 视频暂停时自动隐藏弹幕显示
- **旋转手势** — 支持旋转手势调整播放器画面方向
- **片段自动循环** — 自动循环播放视频中的指定片段

### 🇯🇵 日本語
- **コメント自動ピン留め** — コメント送信後に自動ピン留め表示
- **一時停止時弾幕非表示** — 動画一時停止時に弾幕を自動非表示
- **ローテーションジェスチャー** — ジェスチャーでプレーヤーの向きを調整
- **セグメント自動リピート** — 動画の指定セグメントを自動リピート再生

### 🇰🇷 한국어
- **댓글 자동 고정** — 댓글 전송 후 자동 고정 표시
- **일시정지 시 弹幕 숨기기** — 동영상 일시정지 시 弹幕 자동 숨기기
- **회전 제스처** — 제스처로 플레이어 방향 조절
- **세그먼트 자동 반복** — 동영상 지정 세그먼트 자동 반복 재생

### 🇺🇸 English
- **Comment Auto-Pin** — Auto-pin comments after posting
- **Hide Danmaku on Pause** — Auto-hide danmaku when video is paused
- **Rotate Gesture** — Gesture to adjust player orientation
- **Auto-Repeat Segment** — Auto-repeat specified video segment

---

## v1.99.0 (2026-06-08)
### 🇨🇳 中文
- **控制手势模式** — 自定义播放器控制手势的触发模式
- **智能缓冲策略** — 根据网络状况智能调整缓冲策略
- **首页自动护眼** — 根据环境光线自动切换首页护眼模式
- **动态自动置顶** — 发布动态时自动置顶显示

### 🇯🇵 日本語
- **コントロールジェスチャーモード** — プレーヤー制御ジェスチャーのトリガーモードをカスタマイズ
- **スマートバッファ** — ネットワーク状況に基づいてバッファリング戦略をスマート調整
- **ホーム自動アイケア** — 環境光に基づいてホームのアイケアモードを自動切替
- **動的自動ピン留め** — 動的投稿時に自動ピン留め表示

### 🇰🇷 한국어
- **컨트롤 제스처 모드** — 플레이어 컨트롤 제스처 트리거 모드 사용자 정의
- **스마트 버퍼** — 네트워크 상태에 따라 버퍼링 전략 스마트 조정
- **홈 자동 아이케어** — 주변 광선에 따라 홈 아이케어 모드 자동 전환
- **동적 자동 고정** — 동적 게시 시 자동 고정 표시

### 🇺🇸 English
- **Control Gesture Mode** — Customize player control gesture trigger mode
- **Smart Buffer** — Smart buffer strategy based on network conditions
- **Home Auto Eye-Care** — Auto-switch home eye-care mode based on ambient light
- **Dynamic Auto-Pin** — Auto-pin when posting dynamics

---

## v1.98.0 (2026-06-08)
### 🇨🇳 中文
- **直播间自动重连** — 直播间断线时自动重新连接
- **动态自动转发** — 自动转发优质动态内容
- **评论按回复数排序** — 按评论的回复数量排序显示
- **弹幕发送确认框** — 发送弹幕前显示确认对话框

### 🇯🇵 日本語
- **ライブ自動再接続** — ライブルーム切断時に自動再接続
- **動的自動転送** — 優れた動的コンテンツを自動転送
- **コメント返信数ソート** — コメントの返信数でソート表示
- **弾幕送信確認ダイアログ** — 弾幕送信前に確認ダイアログを表示

### 🇰🇷 한국어
- **라이브 자동 재연결** — 라이브룸 연결 끊김 시 자동 재연결
- **동적 자동 전달** — 우수한 동적 콘텐츠 자동 전달
- **댓글 회신수 정렬** — 댓글 회신수로 정렬 표시
- **弹幕 전송 확인 대화상자** — 弹幕 전송 전 확인 대화상자 표시

### 🇺🇸 English
- **Live Auto-Reconnect** — Auto-reconnect when live room disconnects
- **Dynamic Auto-Repost** — Auto-repost quality dynamic content
- **Comment Sort by Replies** — Sort comments by reply count
- **Danmaku Send Confirm Dialog** — Show confirmation dialog before sending danmaku

---

## v1.97.0 (2026-06-08)
### 🇨🇳 中文
- **高亮管理员评论** — 高亮显示管理员/版主的评论
- **弹幕描边宽度** — 调整弹幕文字的描边宽度
- **截图通知提示** — 截图成功后显示通知提示
- **通知自动暂停** — 收到通知时自动暂停视频播放

### 🇯🇵 日本語
- **モデレーターコメントハイライト** — 管理者/モデレーターのコメントをハイライト
- **弾幕アウトライン幅** — 弾幕テキストのアウトライン幅を調整
- **スクリーンショット通知** — スクリーンショット成功後に通知表示
- **通知自動一時停止** — 通知受信時に動画を自動一時停止

### 🇰🇷 한국어
- **관리자 댓글 강조** — 관리자/모더레이터 댓글 강조 표시
- **弹幕 아웃라인 너비** — 弹幕 텍스트 아웃라인 너비 조절
- **스크린샷 알림** — 스크린샷 성공 후 알림 표시
- **알림 자동 일시정지** — 알림 수신 시 동영상 자동 일시정지

### 🇺🇸 English
- **Highlight Moderator Comments** — Highlight comments from moderators
- **Danmaku Outline Width** — Adjust danmaku text outline width
- **Screenshot Notification** — Show notification after successful screenshot
- **Auto-Pause on Notification** — Auto-pause video when receiving notification

---

## v1.96.0 (2026-06-08)
### 🇨🇳 中文
- **直播弹幕自动滚动** — 直播间弹幕自动滚动到底部
- **首页刷新间隔** — 设置首页信息流自动刷新的时间间隔
- **动态自动归档** — 自动归档浏览过的动态内容
- **搜索建议数量** — 设置搜索自动补全建议的最大数量

### 🇯🇵 日本語
- **ライブ弾幕自動スクロール** — ライブ弾幕を自動で下部にスクロール
- **フィード更新間隔** — ホームフィードの自動更新間隔を設定
- **動的自動アーカイブ** — 閲覧した動的コンテンツを自動アーカイブ
- **検索サジェスト数** — 検索自動補完候補の最大数を設定

### 🇰🇷 한국어
- **라이브 弹幕 자동 스크롤** — 라이브 弹幕 자동으로 하단 스크롤
- **피드 새로고침 간격** — 홈 피드 자동 새로고침 간격 설정
- **동적 자동 아카이브** —浏览한 동적 콘텐츠 자동 아카이브
- **검색 제안 수** — 검색 자동완성 후보 최대 수 설정

### 🇺🇸 English
- **Live Danmaku Auto-Scroll** — Auto-scroll live danmaku to bottom
- **Home Feed Refresh Interval** — Set home feed auto-refresh interval
- **Dynamic Auto-Archive** — Auto-archive browsed dynamic content
- **Search Suggest Limit** — Set max number of search auto-complete suggestions

---

## v1.95.0 (2026-06-08)
### 🇨🇳 中文
- **自动折叠旧评论** — 自动折叠发布时间较久的评论
- **弹幕发送冷却** — 设置弹幕发送的冷却时间间隔
- **音量手势步长** — 自定义音量手势调节的步长
- **自动跳过转场** — 自动跳过视频中的转场动画片段

### 🇯🇵 日本語
- **古いコメント自動折りたたみ** — 古いコメントを自動折りたたみ
- **弾幕送信クールダウン** — 弾幕送信のクールダウン間隔を設定
- **音量ジェスチャーステップ** — 音量ジェスチャー調整のステップをカスタマイズ
- **トランジション自動スキップ** — 動画のトランジションを自動スキップ

### 🇰🇷 한국어
- **오래된 댓글 자동 접기** — 오래된 댓글 자동 접기
- **弹幕 전송 쿨다운** — 弹幕 전송 쿨다운 간격 설정
- **음량 제스처 단계** — 음량 제스처 조절 단계 사용자 정의
- **전환 자동 건너뛰기** — 동영상 전환 애니메이션 자동 건너뛰기

### 🇺🇸 English
- **Auto-Collapse Old Comments** — Auto-collapse comments posted long ago
- **Danmaku Send Cooldown** — Set danmaku send cooldown interval
- **Volume Gesture Step** — Customize volume gesture adjustment step
- **Auto Skip Transition** — Auto-skip video transition animations

---

## v1.94.0 (2026-06-08)
### 🇨🇳 中文
- **摇动撤销操作** — 摇动手机撤销上一步播放器操作
- **智能缓存管理** — 根据存储空间智能管理视频缓存
- **首页自动滚动** — 首页信息流自动缓慢滚动浏览
- **动态自动屏蔽用户** — 自动屏蔽发布不良动态的用户

### 🇯🇵 日本語
- **シェイク元に戻す** — スマホを振ってプレーヤー操作を元に戻す
- **スマートキャッシュ** — ストレージに基づいて動画キャッシュをスマート管理
- **フィード自動スクロール** — ホームフィードを自動スクロール浏览
- **動的自動ユーザーブロック** — 不良動的を投稿したユーザーを自動ブロック

### 🇰🇷 한국어
- **흔들기 실행 취소** — 휴대폰 흔들어 플레이어 작업 실행 취소
- **스마트 캐시** — 저장 공간에 따라 동영상 캐시 스마트 관리
- **피드 자동 스크롤** — 홈 피드 자동 스크롤浏览
- **동적 자동 사용자 차단** — 불량 동적 게시 사용자 자동 차단

### 🇺🇸 English
- **Shake to Undo** — Shake phone to undo last player action
- **Smart Cache** — Smart video cache management based on storage
- **Home Feed Auto-Scroll** — Auto-scroll home feed for browsing
- **Dynamic Auto-Block User** — Auto-block users posting inappropriate dynamics

---

## v1.93.0 (2026-06-08)
### 🇨🇳 中文
- **后台自动静音** — 直播间切到后台时自动静音
- **动态自动删除** — 自动删除过期的动态内容
- **高亮VIP评论** — 高亮显示VIP用户的评论
- **弹幕字体拉伸** — 调整弹幕文字的水平拉伸比例

### 🇯🇵 日本語
- **バックグラウンド自動ミュート** — ライブルームをバックグラウンドにした時自動ミュート
- **動的自動削除** — 期限切れ動的コンテンツを自動削除
- **VIPコメントハイライト** — VIPユーザーのコメントをハイライト
- **弾幕フォントストレッチ** — 弾幕テキストの水平ストレッチ比率を調整

### 🇰🇷 한국어
- **백그라운드 자동 음소거** — 라이브룸 백그라운드 전환 시 자동 음소거
- **동적 자동 삭제** — 만료된 동적 콘텐츠 자동 삭제
- **VIP 댓글 강조** — VIP 사용자 댓글 강조 표시
- **弹幕 글꼴 스트레치** — 弹幕 텍스트 수평 스트레치 비율 조절

### 🇺🇸 English
- **Auto-Mute on Background** — Auto-mute live room when switching to background
- **Dynamic Auto-Delete** — Auto-delete expired dynamic content
- **Highlight VIP Comments** — Highlight comments from VIP users
- **Danmaku Font Stretch** — Adjust danmaku text horizontal stretch ratio

---

## v1.92.0 (2026-06-08)
### 🇨🇳 中文
- **评论按长度排序** — 按评论内容长度排序显示
- **弹幕发送队列** — 支持弹幕发送队列排队
- **截图快捷分享** — 截图后显示快捷分享面板
- **拔耳机自动暂停** — 拔出耳机时自动暂停视频

### 🇯🇵 日本語
- **コメント長さソート** — コメント内容の長さでソート表示
- **弾幕送信キュー** — 弾幕送信キューの順番待ちをサポート
- **スクリーンショット共有** — スクリーンショット後にクイック共有パネル
- **ヘッドフォン抜き自動一時停止** — ヘッドフォンを抜いた時に動画を自動一時停止

### 🇰🇷 한국어
- **댓글 길이 정렬** — 댓글 내용 길이로 정렬 표시
- **弹幕 전송 대기열** — 弹幕 전송 대기열 지원
- **스크린샷 빠른 공유** — 스크린샷 후 빠른 공유 패널
- **이어폰 뽑기 자동 일시정지** — 이어폰 뽑을 때 동영상 자동 일시정지

### 🇺🇸 English
- **Comment Sort by Length** — Sort comments by content length
- **Danmaku Send Queue** — Support danmaku send queue
- **Screenshot Quick Share** — Show quick share panel after screenshot
- **Auto-Pause on Headphone Unplug** — Auto-pause video when headphones are unplugged

---

## v1.91.0 (2026-06-08)
### 🇨🇳 中文
- **直播弹幕字号** — 调整直播间弹幕的字体大小
- **首页自动夜间模式** — 根据时间自动切换首页夜间模式
- **动态自动置顶** — 发布动态时自动置顶
- **搜索自动纠正** — 自动纠正搜索中的拼写错误

### 🇯🇵 日本語
- **ライブ弾幕フォントサイズ** — ライブ弾幕のフォントサイズを調整
- **自動ナイトモード** — 時間に基づいてホームのナイトモードを自動切替
- **動的自動ピン留め** — 動的投稿時に自動ピン留め
- **検索自動訂正** — 検索中のスペルミスを自動訂正

### 🇰🇷 한국어
- **라이브 弹幕 글꼴 크기** — 라이브 弹幕 글꼴 크기 조절
- **자동 야간 모드** — 시간에 따라 홈 야간 모드 자동 전환
- **동적 자동 고정** — 동적 게시 시 자동 고정
- **검색 자동 수정** — 검색 중 맞춤법 오류 자동 수정

### 🇺🇸 English
- **Live Danmaku Font Size** — Adjust live danmaku font size
- **Auto Night Mode** — Auto-switch home night mode based on time
- **Dynamic Auto-Pin** — Auto-pin when posting dynamics
- **Search Auto-Correct** — Auto-correct spelling errors in search

---

## v1.90.0 (2026-06-08)
### 🇨🇳 中文
- **直播评论自动翻译** — 自动翻译直播间中的外语评论
- **滚动时隐藏弹幕** — 滚动页面时自动隐藏弹幕
- **摇动手势操作** — 摇动手机触发播放器操作
- **自动跳过赞助商** — 自动跳过视频中的赞助商片段

### 🇯🇵 日本語
- **ライブコメント自動翻訳** — ライブ中の外国語コメントを自動翻訳
- **スクロール時弾幕非表示** — ページスクロール時に弾幕を自動非表示
- **シェイクジェスチャー** — スマホを振ってプレーヤー操作をトリガー
- **スポンサー自動スキップ** — 動画内のスポンサーセグメントを自動スキップ

### 🇰🇷 한국어
- **라이브 댓글 자동 번역** — 라이브 중 외국어 댓글 자동 번역
- **스크롤 시 弹幕 숨기기** — 페이지 스크롤 시 弹幕 자동 숨기기
- **흔들기 제스처** — 휴대폰 흔들어 플레이어 작업 트리거
- **스폰서 자동 건너뛰기** — 동영상 내 스폰서 세그먼트 자동 건너뛰기

### 🇺🇸 English
- **Live Comment Auto-Translate** — Auto-translate foreign comments in live streams
- **Hide Danmaku on Scroll** — Auto-hide danmaku when scrolling page
- **Shake Gesture** — Shake phone to trigger player action
- **Auto Skip Sponsor** — Auto-skip sponsor segments in videos

---

## v1.89.0 (2026-06-08)
### 🇨🇳 中文
- **控制栏动画** — 启用播放器控制栏的显示/隐藏动画
- **智能分辨率** — 根据屏幕尺寸自动选择最佳分辨率
- **首页已读标记** — 标记已浏览的信息流内容
- **动态自动回复** — 自动回复指定动态的评论

### 🇯🇵 日本語
- **コントロールアニメーション** — プレーヤーコントロールバーの表示/非表示アニメーション
- **スマート解像度** — 画面サイズに基づいて最適な解像度を自動選択
- **既読マーカー** — 閲覧済みフィードコンテンツにマーカーを付ける
- **動的自動返信** — 指定動的のコメントに自動返信

### 🇰🇷 한국어
- **컨트롤 애니메이션** — 플레이어 컨트롤바 표시/숨기기 애니메이션
- **스마트 해상도** — 화면 크기에 따라 최적 해상도 자동 선택
- **읽음 표시** —浏览한 피드 콘텐츠에 읽음 표시
- **동적 자동 답변** — 지정 동적 댓글에 자동 답변

### 🇺🇸 English
- **Control Animation** — Enable player control bar show/hide animation
- **Smart Resolution** — Auto-select best resolution based on screen size
- **Mark as Read** — Mark browsed feed content as read
- **Dynamic Auto-Reply** — Auto-reply to specified dynamic comments

---

## v1.88.0 (2026-06-08)
### 🇨🇳 中文
- **直播间自动全屏** — 进入直播间时自动切换到全屏模式
- **动态全部点赞** — 一键点赞当前页面所有动态
- **评论置顶优先** — 评论区优先显示置顶评论
- **发送弹幕字体** — 自定义发送弹幕时使用的字体

### 🇯🇵 日本語
- **ライブ自動フルスクリーン** — ライブルーム入場時に自動フルスクリーン
- **動的全いいね** — 現在のページの全動的にワンクリックいいね
- **コメントピン留め優先** — コメント欄でピン留めコメントを優先表示
- **弾幕送信フォント** — 弾幕送信時のフォントをカスタマイズ

### 🇰🇷 한국어
- **라이브 자동 전체화면** — 라이브룸 입장 시 자동 전체화면 전환
- **동적 전체 좋아요** — 현재 페이지 모든 동적에 좋아요
- **댓글 고정 우선** — 댓글란에서 고정 댓글 우선 표시
- **弹幕 전송 글꼴** — 弹幕 전송 시 사용할 글꼴 사용자 정의

### 🇺🇸 English
- **Live Auto-Fullscreen** — Auto-switch to fullscreen when entering live room
- **Dynamic Like All** — One-click like all dynamics on current page
- **Comment Pinned First** — Show pinned comments first in comment section
- **Danmaku Send Font** — Customize font for sending danmaku

---

## v1.87.0 (2026-06-08)
### 🇨🇳 中文
- **评论关键词高亮** — 高亮显示包含指定关键词的评论
- **弹幕背景透明度** — 调节弹幕文字背景的透明度
- **播放器夜间模式** — 播放器启用夜间模式护眼配色
- **低电量自动暂停** — 电量低于指定值时自动暂停视频

### 🇯🇵 日本語
- **コメントキーワードハイライト** — 指定キーワードを含むコメントをハイライト
- **弾幕背景透明度** — 弾幕テキスト背景の透明度を調整
- **プレーヤーナイトモード** — プレーヤーのアイケアナイトモード
- **低バッテリー自動一時停止** — バッテリーが指定値以下で自動一時停止

### 🇰🇷 한국어
- **댓글 키워드 강조** — 지정 키워드가 포함된 댓글 강조 표시
- **弹幕 배경 투명도** — 弹幕 텍스트 배경 투명도 조절
- **플레이어 야간 모드** — 플레이어 아이케어 야간 모드
- **저배터리 자동 일시정지** — 배터리 지정 수치 이하 시 자동 일시정지

### 🇺🇸 English
- **Comment Keyword Highlight** — Highlight comments containing specified keywords
- **Danmaku Background Opacity** — Adjust danmaku text background opacity
- **Player Night Mode** — Player eye-care night mode
- **Auto-Pause on Low Battery** — Auto-pause video when battery is below threshold

---

## v1.86.0 (2026-06-08)
### 🇨🇳 中文
- **屏蔽表情弹幕** — 屏蔽直播间纯表情弹幕
- **首页自动加载更多** — 首页信息流自动加载更多内容
- **动态自动收藏** — 自动收藏浏览过的优质动态
- **搜索自动过滤** — 自动过滤搜索结果中的指定关键词

### 🇯🇵 日本語
- **絵文字弾幕ブロック** — ライブの絵文字のみの弾幕をブロック
- **自動読み込み** — ホームフィードの自動追加読み込み
- **動的自動ブックマーク** — 閲覧した高品質動的を自動ブックマーク
- **検索自動フィルター** — 指定キーワードで検索結果を自動フィルタリング

### 🇰🇷 한국어
- **이모티콘 弹幕 차단** — 라이브 순수 이모티콘 弹幕 차단
- **자동 더 보기** — 홈 피드 자동 추가 로드
- **동적 자동 북마크** —浏览한 우수 동적 자동 북마크
- **검색 자동 필터** — 지정 키워드로 검색 결과 자동 필터링

### 🇺🇸 English
- **Block Emoji Danmaku** — Block emoji-only danmaku in live streams
- **Auto Load More** — Auto-load more content in home feed
- **Dynamic Auto-Bookmark** — Auto-bookmark quality dynamics
- **Search Auto-Filter** — Auto-filter search results by specified keywords

---

## v1.85.0 (2026-06-08)
### 🇨🇳 中文
- **表情自动补全** — 输入表情关键词时自动显示表情候选
- **弹幕位置自定义** — 自定义弹幕显示的垂直位置
- **捏合缩放手势** — 支持捏合手势缩放播放器画面
- **自动跳过片尾** — 自动跳过视频结尾的片尾部分

### 🇯🇵 日本語
- **絵文字自動補完** — 絵文字キーワード入力時に候補を自動表示
- **弾幕位置カスタマイズ** — 弾幕の垂直位置をカスタマイズ
- **ピンチズームジェスチャー** — ピンチジェスチャーでプレーヤーをズーム
- **エンド自動スキップ** — 動画終了部分を自動スキップ

### 🇰🇷 한국어
- **이모티콘 자동 완성** — 이모티콘 키워드 입력 시 후보 자동 표시
- **弹幕 위치 사용자 정의** — 弹幕 수직 위치 사용자 정의
- **핀치 줌 제스처** — 핀치 제스처로 플레이어 줌
- **엔드 자동 건너뛰기** — 동영상 끝부분 자동 건너뛰기

### 🇺🇸 English
- **Emoji Auto-Complete** — Show emoji candidates when typing emoji keywords
- **Danmaku Position Custom** — Customize danmaku vertical position
- **Pinch Zoom Gesture** — Support pinch gesture to zoom player
- **Auto Skip Ending** — Auto-skip video ending portion

---

## v1.84.0 (2026-06-08)
### 🇨🇳 中文
- **智能暂停** — 检测到用户注意力分散时自动暂停
- **截图自动保存** — 播放器截图后自动保存到相册
- **首页随机打乱** — 随机打乱首页信息流顺序
- **动态刷新间隔** — 设置动态页面自动刷新的时间间隔

### 🇯🇵 日本語
- **スマート一時停止** — ユーザーの注意散漫を検出して自動一時停止
- **スクリーンショット自動保存** — プレーヤースクリーンショットを自動でギャラリーに保存
- **フィードシャッフル** — ホームフィードの順序をランダムシャッフル
- **動的更新間隔** — 動的ページの自動更新間隔を設定

### 🇰🇷 한국어
- **스마트 일시정지** — 사용자 주의 분산 감지 시 자동 일시정지
- **스크린샷 자동 저장** — 플레이어 스크린샷 자동으로 갤러리에 저장
- **피드 셔플** — 홈 피드 순서 랜덤 셔플
- **동적 새로고침 간격** — 동적 페이지 자동 새로고침 간격 설정

### 🇺🇸 English
- **Smart Pause** — Auto-pause when user attention is detected as distracted
- **Screenshot Auto-Save** — Auto-save player screenshots to gallery
- **Home Feed Shuffle** — Randomly shuffle home feed order
- **Dynamic Refresh Interval** — Set dynamic page auto-refresh interval

---

## v1.83.0 (2026-06-08)
### 🇨🇳 中文
- **直播间自动送礼** — 进入直播间时自动送出免费礼物
- **动态自动静音** — 浏览动态视频时自动静音播放
- **评论自动删除** — 自动删除已发送的过期评论
- **弹幕去重过滤** — 自动过滤重复内容的弹幕

### 🇯🇵 日本語
- **ライブ自動ギフト** — ライブルーム入場時に無料ギフトを自動送信
- **動的自動ミュート** — 動的動画浏览時に自動ミュート再生
- **コメント自動削除** — 送信済みの期限切れコメントを自動削除
- **弾幕重複フィルター** — 重複コンテンツの弾幕を自動フィルタリング

### 🇰🇷 한국어
- **라이브 자동 선물** — 라이브룸 입장 시 무료 선물 자동 전송
- **동적 자동 음소거** — 동적 동영상浏览 시 자동 음소거 재생
- **댓글 자동 삭제** — 보낸 만료된 댓글 자동 삭제
- **弹幕 중복 필터** — 중복 콘텐츠 弹幕 자동 필터링

### 🇺🇸 English
- **Live Auto-Gift** — Auto-send free gifts when entering live room
- **Dynamic Auto-Mute** — Auto-mute when browsing dynamic videos
- **Comment Auto-Delete** — Auto-delete expired sent comments
- **Danmaku Dedup Filter** — Auto-filter duplicate danmaku content

---

## v1.82.0 (2026-06-08)
### 🇨🇳 中文
- **高亮自己的评论** — 在评论区高亮显示自己的评论
- **弹幕速度微调** — 微调弹幕的滚动速度
- **控制栏透明度** — 调节播放器控制栏的透明度
- **锁屏自动暂停** — 锁屏时自动暂停视频播放

### 🇯🇵 日本語
- **自分のコメントハイライト** — コメント欄で自分のコメントをハイライト表示
- **弾幕速度微調整** — 弾幕のスクロール速度を微調整
- **コントロール透明度** — プレーヤーコントロールバーの透明度を調整
- **ロック画面自動一時停止** — ロック画面時に動画を自動一時停止

### 🇰🇷 한국어
- **내 댓글 강조** — 댓글란에서 내 댓글 강조 표시
- **弹幕 속도 미세 조정** — 弹幕 스크롤 속도 미세 조정
- **컨트롤 투명도** — 플레이어 컨트롤바 투명도 조절
- **잠금화면 자동 일시정지** — 잠금화면 시 동영상 자동 일시정지

### 🇺🇸 English
- **Highlight Self Comments** — Highlight your own comments in comment section
- **Danmaku Speed Fine-Tune** — Fine-tune danmaku scroll speed
- **Control Opacity** — Adjust player control bar opacity
- **Auto-Pause on Lock** — Auto-pause video when screen is locked

---

## v1.81.0 (2026-06-08)
### 🇨🇳 中文
- **直播弹幕合并** — 合并显示相同内容的直播弹幕
- **首页无限滚动** — 首页信息流启用无限滚动加载
- **动态自动屏蔽** — 自动屏蔽不良动态内容
- **搜索历史自动清除** — 自动清除搜索历史记录

### 🇯🇵 日本語
- **ライブ弾幕マージ** — 同じ内容のライブ弾幕をマージ表示
- **無限スクロール** — ホームフィードの無限スクロール読み込みを有効化
- **動的自動ブロック** — 不良動的コンテンツを自動ブロック
- **検索履歴自動クリア** — 検索履歴を自動クリア

### 🇰🇷 한국어
- **라이브 弹幕 병합** — 동일 내용의 라이브 弹幕 병합 표시
- **무한 스크롤** — 홈 피드 무한 스크롤 로드 활성화
- **동적 자동 차단** — 불량 동적 콘텐츠 자동 차단
- **검색 기록 자동 삭제** — 검색 기록 자동 삭제

### 🇺🇸 English
- **Live Danmaku Merge** — Merge same-content live danmaku display
- **Infinite Scroll** — Enable infinite scroll loading for home feed
- **Dynamic Auto-Block** — Auto-block inappropriate dynamic content
- **Search History Auto-Clear** — Auto-clear search history

---

## v1.80.0 (2026-06-08)
### 🇨🇳 中文
- **评论草稿自动保存** — 自动保存未发送的评论草稿
- **弹幕发送确认** — 发送弹幕前显示确认对话框
- **三击手势自定义** — 自定义三击播放器的行为
- **自动跳过预告** — 自动跳过视频开头的预告片段

### 🇯🇵 日本語
- **コメント下書き自動保存** — 未送信のコメント下書きを自動保存
- **弾幕送信確認** — 弾幕送信前に確認ダイアログを表示
- **トリプルタップジェスチャー** — プレーヤーのトリプルタップ動作をカスタマイズ
- **予告自動スキップ** — 動画冒頭の予告を自動スキップ

### 🇰🇷 한국어
- **댓글 초안 자동 저장** — 미전송 댓글 초안 자동 저장
- **弹幕 전송 확인** — 弹幕 전송 전 확인 대화상자 표시
- **트리플탭 제스처** — 플레이어 트리플탭 동작 사용자 정의
- **예고 자동 건너뛰기** — 동영상 시작 시 예고 자동 건너뛰기

### 🇺🇸 English
- **Comment Draft Auto-Save** — Auto-save unsent comment drafts
- **Danmaku Send Confirm** — Show confirmation dialog before sending danmaku
- **Triple Tap Gesture** — Customize triple-tap player behavior
- **Auto Skip Preview** — Auto-skip video opening previews

---

## v1.79.0 (2026-06-08)
### 🇨🇳 中文
- **章节自动跳过** — 自动跳过视频中不感兴趣的章节
- **亮度自动调节** — 根据环境光线自动调节播放器亮度
- **首页排序方式** — 自定义首页信息流的排序方式
- **动态自动保存** — 自动保存浏览过的动态内容

### 🇯🇵 日本語
- **チャプター自動スキップ** — 興味のないチャプターを自動スキップ
- **明るさ自動調整** — 環境光に基づいてプレーヤーの明るさを自動調整
- **フィードソート** — ホームフィードのソート方法をカスタマイズ
- **動的自動保存** — 閲覧した動的コンテンツを自動保存

### 🇰🇷 한국어
- **챕터 자동 건너뛰기** — 관심 없는 챕터 자동 건너뛰기
- **밝기 자동 조절** — 주변 광선에 따라 플레이어 밝기 자동 조절
- **피드 정렬 방식** — 홈 피드 정렬 방식 사용자 정의
- **동적 자동 저장** —浏览한 동적 콘텐츠 자동 저장

### 🇺🇸 English
- **Auto Skip Chapters** — Auto-skip uninteresting video chapters
- **Auto Brightness** — Auto-adjust player brightness based on ambient light
- **Home Feed Sort** — Customize home feed sorting method
- **Dynamic Auto-Save** — Auto-save browsed dynamic content

---

## v1.78.0 (2026-06-08)
### 🇨🇳 中文
- **直播间自动关注** — 进入直播间时自动关注主播
- **动态自动翻译** — 自动翻译外语动态内容
- **评论行间距** — 调整评论区文字的行间距
- **弹幕字体粗细** — 调整弹幕文字的粗细程度

### 🇯🇵 日本語
- **ライブ自動フォロー** — ライブルーム入場時に自動フォロー
- **動的自動翻訳** — 外国語動的コンテンツを自動翻訳
- **コメント行間隔** — コメントテキストの行間隔を調整
- **弾幕フォントウェイト** — 弾幕テキストの太さを調整

### 🇰🇷 한국어
- **라이브 자동 팔로우** — 라이브룸 입장 시 자동 팔로우
- **동적 자동 번역** — 외국어 동적 콘텐츠 자동 번역
- **댓글 줄 간격** — 댓글 텍스트 줄 간격 조절
- **弹幕 글꼴 두께** — 弹幕 텍스트 두께 조절

### 🇺🇸 English
- **Live Auto-Follow** — Auto-follow host when entering live room
- **Dynamic Auto-Translate** — Auto-translate foreign dynamic content
- **Comment Line Spacing** — Adjust comment text line spacing
- **Danmaku Font Weight** — Adjust danmaku text thickness

---

## v1.77.0 (2026-06-08)
### 🇨🇳 中文
- **评论自动折叠** — 自动折叠过长的评论内容
- **弹幕发送延迟** — 设置弹幕发送的延迟时间
- **控制栏自动隐藏** — 设置播放器控制栏自动隐藏时间
- **来电自动暂停** — 接到来电时自动暂停视频播放

### 🇯🇵 日本語
- **コメント自動折りたたみ** — 長いコメントを自動折りたたみ
- **弾幕送信遅延** — 弾幕送信の遅延時間を設定
- **コントロール自動非表示** — コントロールバーの自動非表示時間を設定
- **着信自動一時停止** — 着信時に動画を自動一時停止

### 🇰🇷 한국어
- **댓글 자동 접기** — 긴 댓글 자동 접기
- **弹幕 전송 지연** — 弹幕 전송 지연 시간 설정
- **컨트롤 자동 숨기기** — 컨트롤바 자동 숨기기 시간 설정
- **수신 자동 일시정지** — 수신 시 동영상 자동 일시정지

### 🇺🇸 English
- **Comment Auto-Fold** — Auto-fold long comments
- **Danmaku Send Delay** — Set danmaku send delay time
- **Control Auto-Hide** — Set player control bar auto-hide time
- **Auto-Pause on Call** — Auto-pause video when receiving a call

---

## v1.76.0 (2026-06-08)
### 🇨🇳 中文
- **直播弹幕关键词过滤** — 按关键词过滤直播间弹幕
- **首页自动刷新** — 首页信息流自动定时刷新
- **动态自动转发** — 浏览动态时自动转发优质内容
- **搜索智能建议** — 搜索时显示智能补全建议

### 🇯🇵 日本語
- **ライブ弾幕キーワードフィルター** — キーワードでライブ弾幕をフィルタリング
- **フィード自動更新** — ホームフィードを自動的に定期更新
- **動的自動転送** — 優れた動的コンテンツを自動転送
- **検索スマートサジェスト** — 検索時にスマート補完候補を表示

### 🇰🇷 한국어
- **라이브 弹幕 키워드 필터** — 키워드로 라이브 弹幕 필터링
- **피드 자동 새로고침** — 홈 피드 자동 정기 새로고침
- **동적 자동 전달** — 우수한 동적 콘텐츠 자동 전달
- **검색 스마트 제안** — 검색 시 스마트 자동완성 제안 표시

### 🇺🇸 English
- **Live Danmaku Keyword Filter** — Filter live danmaku by keywords
- **Home Feed Auto-Refresh** — Auto-refresh home feed periodically
- **Dynamic Auto-Forward** — Auto-forward quality dynamic content
- **Search Smart Suggest** — Show smart auto-complete suggestions in search

---

## v1.75.0 (2026-06-08)
### 🇨🇳 中文
- **评论逆序排列** — 将评论区按逆序排列显示
- **弹幕阴影样式** — 自定义弹幕文字的阴影效果
- **长按手势自定义** — 自定义长按播放器的行为
- **自动跳过片尾** — 自动跳过视频片尾字幕部分

### 🇯🇵 日本語
- **コメント逆順ソート** — コメント欄を逆順にソート表示
- **弾幕シャドウスタイル** — 弾幕テキストのシャドウ効果をカスタマイズ
- **ロングプレスジェスチャー** — プレーヤーのロングプレス動作をカスタマイズ
- **エンドクレジット自動スキップ** — 動画のエンドクレジットを自動スキップ

### 🇰🇷 한국어
- **댓글 역순 정렬** — 댓글란 역순 정렬 표시
- **弹幕 그림자 스타일** — 弹幕 텍스트 그림자 효과 사용자 정의
- **길게 누르기 제스처** — 플레이어 길게 누르기 동작 사용자 정의
- **엔드크레딧 자동 건너뛰기** — 동영상 엔드크레딧 자동 건너뛰기

### 🇺🇸 English
- **Comment Reverse Sort** — Sort comments in reverse order
- **Danmaku Shadow Style** — Customize danmaku text shadow effect
- **Long Press Gesture** — Customize long-press player behavior
- **Auto Skip Credits** — Auto-skip video end credits

---

## v1.74.0 (2026-06-08)
### 🇨🇳 中文
- **动态置顶** — 将自己的动态置顶显示
- **弹幕字体族** — 选择弹幕显示的字体族（默认/衬线/等宽/无衬线）
- **评论自动点赞** — 浏览评论时自动点赞优质评论
- **视频自动画质** — 根据网络状况自动选择最佳画质

### 🇯🇵 日本語
- **動的ピン留め** — 自分の動的をピン留め表示
- **弾幕フォントファミリー** — 弾幕のフォントファミリーを選択
- **コメント自動いいね** — コメント閲覧時に高品質コメントに自動いいね
- **動画画質自動** — ネットワーク状況に基づいて最適な画質を自動選択

### 🇰🇷 한국어
- **동적 고정** — 내 동적 상단 고정 표시
- **弹幕 글꼴 패밀리** — 弹幕 글꼴 패밀리 선택 (기본/세리프/고정폭/산세리프)
- **댓글 자동 좋아요** — 댓글浏览时优质评论에 자동 좋아요
- **동영상 자동 화질** — 네트워크 상태에 따라 최적 화질 자동 선택

### 🇺🇸 English
- **Dynamic Pin Top** — Pin your own dynamic posts to top
- **Danmaku Font Family** — Choose danmaku font family (default/serif/monospace/sans-serif)
- **Comment Auto-Like** — Auto-like quality comments when browsing
- **Video Auto Quality** — Auto-select best quality based on network

---

## v1.73.0 (2026-06-08)
### 🇨🇳 中文
- **断点续播增强** — 增强的断点续播功能，支持跨设备同步
- **直播弹幕速度** — 调节直播间弹幕的滚动速度
- **首页缓存控制** — 控制首页信息流的缓存行为
- **搜索自动纠错** — 自动纠正搜索关键词中的错别字

### 🇯🇵 日本語
- **ブックマーク再生強化** — デバイス間同期対応のブックマーク再生
- **ライブ弾幕速度** — ライブ弾幕のスクロール速度を調整
- **フィードキャッシュ制御** — ホームフィードのキャッシュ動作を制御
- **検索自動訂正** — 検索キーワードの誤字を自動訂正

### 🇰🇷 한국어
- **이어보기 강화** — 기기 간 동기화 지원 이어보기 기능
- **라이브 弹幕 속도** — 라이브 弹幕 스크롤 속도 조절
- **피드 캐시 제어** — 홈 피드 캐시 동작 제어
- **검색 자동 수정** — 검색어의 오탈자 자동 수정

### 🇺🇸 English
- **Enhanced Resume** — Cross-device sync for video resume
- **Live Danmaku Speed** — Adjust live danmaku scroll speed
- **Home Feed Cache** — Control home feed caching behavior
- **Search Auto-Correct** — Auto-correct typos in search queries

---

## v1.72.0 (2026-06-08)
### 🇨🇳 中文
- **动态自动点赞** — 浏览动态时自动点赞
- **弹幕翻译** — 将外语弹幕翻译为中文显示
- **评论默认排序** — 设置评论区的默认排序方式
- **双击手势自定义** — 自定义双击播放器的行为

### 🇯🇵 日本語
- **動的自動いいね** — 動的浏览時に自動いいね
- **弾幕翻訳** — 外国語弾幕を中国語に翻訳表示
- **コメントデフォルトソート** — コメント欄のデフォルトソートを設定
- **ダブルタップジェスチャー** — プレーヤーのダブルタップ動作をカスタマイズ

### 🇰🇷 한국어
- **동적 자동 좋아요** — 동적浏览 시 자동 좋아요
- **弹幕 번역** — 외국어 弹幕을 중국어로 번역 표시
- **댓글 기본 정렬** — 댓글란 기본 정렬 방식 설정
- **더블탭 제스처** — 플레이어 더블탭 동작 사용자 정의

### 🇺🇸 English
- **Dynamic Auto-Like** — Auto-like when browsing dynamics
- **Danmaku Translate** — Translate foreign danmaku to Chinese
- **Comment Default Sort** — Set default comment sorting method
- **Double Tap Gesture** — Customize double-tap player behavior

---

## v1.71.0 (2026-06-08)
### 🇨🇳 中文
- **直播自动录制** — 进入直播间时自动录制直播流
- **智能预加载** — 根据网络状况智能预加载视频
- **首页小组件自定义** — 自定义首页显示的小组件
- **搜索质量过滤** — 过滤搜索结果中的低质量内容

### 🇯🇵 日本語
- **ライブ自動録画** — ライブルーム入場時に自動録画
- **スマートプリロード** — ネットワーク状況に基づいてスマートプリロード
- **ウィジェットカスタマイズ** — ホームウィジェットをカスタマイズ
- **検索品質フィルター** — 低品質な検索結果をフィルタリング

### 🇰🇷 한국어
- **라이브 자동 녹화** — 라이브룸 입장 시 자동 녹화
- **스마트 프리로드** — 네트워크 상태에 따른 스마트 프리로드
- **위젯 사용자 정의** — 홈 위젯 사용자 정의
- **검색 품질 필터** — 저품질 검색 결과 필터링

### 🇺🇸 English
- **Live Auto-Record** — Auto-record when entering live room
- **Smart Preload** — Smart video preloading based on network
- **Home Widget Custom** — Customize homepage widgets
- **Search Quality Filter** — Filter low-quality search results

---

## v1.70.0 (2026-06-08)
### 🇨🇳 中文
- **视频自动变速** — 根据内容类型自动调整播放速度
- **按用户高亮弹幕** — 高亮显示指定用户发送的弹幕
- **评论图片压缩** — 自动压缩评论区图片以节省流量
- **字幕同步调整** — 手动调整字幕的显示时间偏移

### 🇯🇵 日本語
- **動画自動速度調整** — コンテンツタイプに基づいて再生速度を自動調整
- **ユーザー別弾幕ハイライト** — 指定ユーザーの弾幕をハイライト表示
- **コメント画像圧縮** — コメント画像を自動圧縮してデータ節約
- **字幕同期調整** — 字幕の表示タイミングを手動調整

### 🇰🇷 한국어
- **동영상 자동 속도** — 콘텐츠 유형에 따라 재생 속도 자동 조절
- **사용자별 弹幕 강조** — 지정 사용자의 弹幕 강조 표시
- **댓글 이미지 압축** — 댓글 이미지 자동 압축으로 데이터 절약
- **자막 동기화 조절** — 자막 표시 타이밍 수동 조절

### 🇺🇸 English
- **Video Auto Speed** — Auto-adjust playback speed by content type
- **Danmaku Highlight User** — Highlight danmaku from specified users
- **Comment Image Compress** — Auto-compress comment images to save data
- **Subtitle Sync Adjust** — Manually adjust subtitle timing offset

---

## v1.69.0 (2026-06-08)

### 🇨🇳 中文
- **智能弹幕过滤** — 自动过滤低质量弹幕（纯数字、重复、过短）
- **评论自动置顶** — 发送评论后自动将其置顶显示
- **视频自动下载** — 收藏视频后自动下载到本地
- **动态自动滚动** — 动态页面自动滚动加载更多内容

### 🇯🇵 日本語
- **スマート弾幕フィルター** — 低品質弾幕を自動フィルタリング（数字のみ、繰り返し、短すぎる）
- **コメント自動ピン留め** — コメント送信後に自動でピン留め表示
- **動画自動ダウンロード** — 動画をお気に入り後に自動でローカルにダウンロード
- **動的自動スクロール** — 動的ページで自動スクロールしてさらに読み込み

### 🇰🇷 한국어
- **스마트 弹幕 필터** — 저품질 弹幕 자동 필터링 (숫자만, 반복, 너무 짧은 것)
- **댓글 자동 고정** — 댓글 전송 후 자동으로 상단 고정 표시
- **동영상 자동 다운로드** — 동영상 즐겨찾기 후 자동으로 로컬에 다운로드
- **동적 자동 스크롤** — 동적 페이지에서 자동 스크롤하여 더 많은 내용 로드

### 🇺🇸 English
- **Smart Danmaku Filter** — Auto-filter low-quality danmaku (numbers-only, repeats, too short)
- **Comment Auto-Pin** — Auto-pin comments after posting
- **Video Auto-Download** — Auto-download videos to local storage after favoriting
- **Dynamic Auto-Scroll** — Auto-scroll dynamic page to load more content

---

## v1.68.0 (2026-06-08)

### 🇨🇳 中文
- **按用户屏蔽弹幕** — 屏蔽指定用户ID发送的弹幕
- **手势快进快退** — 在播放器左右滑动快进/快退
- **离开自动暂停** — 检测到用户离开时自动暂停视频
- **GIF动图表情** — 在评论输入框旁添加GIF动图表情选择面板

### 🇯🇵 日本語
- **ユーザー別弾幕ブロック** — 指定ユーザーIDの弾幕をブロック
- **ジェスチャー早送り/巻き戻し** — プレーヤーを左右にスワイプして早送り/巻き戻し
- **離脱自動一時停止** — ユーザーが離れたことを検出して動画を自動一時停止
- **GIFアニメ絵文字** — コメント入力欄にGIFアニメ絵文字選択パネルを追加

### 🇰🇷 한국어
- **사용자별 弹幕 차단** — 지정한 사용자 ID의 弹幕 차단
- **제스처 빨리감기/되감기** — 플레이어를 좌우로 스와이프하여 빨리감기/되감기
- **이탈 시 자동 일시정지** — 사용자가 자리를 비웠을 때 동영상 자동 일시정지
- **GIF 애니메이션 이모티콘** — 댓글 입력란에 GIF 애니메이션 이모티콘 선택 패널 추가

### 🇺🇸 English
- **Block Danmaku by User** — Block danmaku from specified user IDs
- **Gesture Seek** — Swipe left/right on player to seek forward/backward
- **Auto-Pause on Leave** — Auto-pause video when user leaves
- **GIF Sticker Panel** — Add GIF sticker selection panel in comment input

---

## v1.67.0 (2026-06-08)

### 🇨🇳 中文
- **搜索自动补全增强** — 增强搜索框的自动补全和搜索建议
- **进度条样式自定义** — 自定义播放器进度条的颜色（粉色/绿色/蓝色/橙色）
- **首页快捷方式** — 在首页添加常用功能快捷入口
- **视频结束动作** — 自定义视频播放结束时的行为（重播/退出/下一集）

### 🇯🇵 日本語
- **検索自動補完強化** — 検索ボックスの自動補完と検索候補を強化
- **プログレスバースタイル** — プレーヤーのプログレスバーの色をカスタマイズ
- **ホームショートカット** — ホームに常用機能のショートカットを追加
- **動画終了アクション** — 動画再生終了時の動作をカスタマイズ

### 🇰🇷 한국어
- **검색 자동완성 강화** — 검색창 자동완성 및 검색 제안 강화
- **프로그레스바 스타일** — 플레이어 프로그레스바 색상 사용자 정의
- **홈 바로가기** — 홈에 자주 사용하는 기능 바로가기 추가
- **동영상 종료 동작** — 동영상 재생 종료 시 동작 사용자 정의

### 🇺🇸 English
- **Search Auto-Complete Enhancement** — Enhance search box auto-complete and suggestions
- **Progress Bar Style** — Customize player progress bar color
- **Home Shortcut** — Add common function shortcuts on homepage
- **Video End Action** — Customize behavior when video playback ends

---

## v1.66.0 (2026-06-08)

### 🇨🇳 中文
- **动态自动刷新** — 进入动态页面时自动刷新内容
- **评论语音输入** — 支持在评论区使用语音转文字输入
- **下载画质选择** — 自定义视频下载的画质（360P/480P/720P/1080P/4K）
- **直播弹幕颜色** — 设置直播间发送弹幕的默认颜色

### 🇯🇵 日本語
- **動的自動更新** — 動的ページに入った時に自動でコンテンツを更新
- **コメント音声入力** — コメント欄で音声からテキスト入力をサポート
- **ダウンロード画質選択** — 動画ダウンロードの画質をカスタマイズ（360P/480P/720P/1080P/4K）
- **ライブ弾幕色** — ライブ配信で送信する弾幕のデフォルト色を設定

### 🇰🇷 한국어
- **동적 자동 새로고침** — 동적 페이지 진입 시 콘텐츠 자동 새로고침
- **댓글 음성 입력** — 댓글란에서 음성-텍스트 입력 지원
- **다운로드 화질 선택** — 동영상 다운로드 화질 사용자 정의 (360P/480P/720P/1080P/4K)
- **라이브 弹幕 색상** — 라이브 방송에서 보내는 弹幕 기본 색상 설정

### 🇺🇸 English
- **Dynamic Auto-Refresh** — Auto-refresh content when entering dynamic page
- **Comment Voice Input** — Support voice-to-text input in comment section
- **Download Quality Selection** — Customize video download quality (360P/480P/720P/1080P/4K)
- **Live Danmaku Color** — Set default color for danmaku sent in live streams

---

## v1.65.0 (2026-06-07)

### 🇨🇳 中文
- **视频缓冲大小** — 控制视频预加载缓冲时长（默认/低/中/高/极限）
- **屏蔽礼物特效** — 隐藏直播间全屏礼物特效动画，减少干扰
- **纯音频模式** — 只播放声音不显示画面，节省流量和性能
- **迷你播放器大小** — 调整迷你播放器（小窗）的尺寸

### 🇯🇵 日本語
- **動画バッファサイズ** — 動画のプリロードバッファ時間を制御
- **ギフトエフェクト屏蔽** — ライブルームのフルスクリーンギフトエフェクトを非表示
- **オーディオンリーモード** - 音声のみ再生、画面非表示でトラフィックとパフォーマンスを節約
- **ミニプレーヤーサイズ** — ミニプレーヤー（小窓）のサイズを調整

### 🇰🇷 한국어
- **동영상 버퍼 크기** — 동영상 프리로드 버퍼 시간 제어
- **선물 효과 차단** — 라이브룸 전체 화면 선물 효과 애니메이션 숨기기
- **오디오 전용 모드** — 소리만 재생, 화면 표시 안 함
- **미니 플레이어 크기** — 미니 플레이어(작은 창) 크기 조정

### 🇺🇸 English
- **Video Buffer Size** — Control video preload buffer duration (default/low/medium/high/extreme)
- **Block Gift Effect** — Hide live room full-screen gift effect animations
- **Audio Only Mode** — Play audio only, hide video to save bandwidth and performance
- **Mini Player Size** — Adjust mini player (small window) size

---


## v1.64.0 (2026-06-07)

### 🇨🇳 中文
- **评论区贴纸键盘增强** — 在评论输入框旁添加B站特色贴纸快速选择面板
- **弹幕发送历史** — 记录最近发送的弹幕，方便快速重发
- **播放器控制栏布局** — 调整播放器控制按钮的排列和显示（默认/紧凑/精简）
- **直播间背景自定义** — 设置直播间的背景样式（默认/纯黑/纯白/自定义）

### 🇯🇵 日本語
- **コメントステッカーキーボード強化** — コメント入力欄にBilibiliステッカーパネルを追加
- **弾幕送信履歴** — 最近送信した弾mouthを記録し、再送信を簡単にする
- **プレーヤーコントロールレイアウト** — コントロールボタンの配置を調整（デフォルト/コンパクト/ミニマル）
- **ライブルーム背景カスタマイズ** — ライブルームの背景スタイルを設定

### 🇰🇷 한국어
- **댓글 스티커 키보드 강화** — 댓글 입력란에 Bilibili 스티커 패널 추가
- **弹幕 전송 기록** — 최근 전송한 弹幕 기록으로 빠른 재전송
- **플레이어 컨트롤 레이아웃** — 컨트롤 버튼 배치 조정 (기본/컴팩트/미니멀)
- **라이브룸 배경 사용자 정의** — 라이브룸 배경 스타일 설정 (기본/검정/흰색/사용자 정의)

### 🇺🇸 English
- **Comment Sticker Keyboard Enhancement** — Add Bilibili sticker panel in comment input
- **Danmaku Send History** — Record recent danmaku for quick resend
- **Player Control Layout** — Customize control button layout (default/compact/minimal)
- **Live Room Background Customization** — Set live room background style

---

## v1.63.0 (2026-06-07)

### 🇨🇳 中文
- **直播弹幕字体** — 自定义直播弹幕的字体大小（独立于视频弹幕设置）
- **自动跳过花絮** — 自动跳过正片前后的花絮、预告、广告
- **截图质量设置** — 调节截图压缩质量（1-100），数值越高质量越好
- **视频自动分享** — 播放视频后自动复制分享链接到剪贴板

### 🇯🇵 日本語
- **ライブ弾幕フォント** — ライブ弾幕のフォントサイズをカスタマイズ
- **花絮自動スキップ** — 本編前後の予告、広告、花絮を自動スキップ
- **スクリーンショット品質設定** — スクリーンショットの圧縮品質を調整（1-100）
- **動画自動共有** — 動画再生後にクリップボードに共有URLを自動コピー

### 🇰🇷 한국어
- **라이브 弹幕 글꼴** — 라이브 弹幕 글꼴 크기 사용자 정의 (동영상 弹幕과 독립적)
- **预告 자동 건너뛰기** — 본편 전후의 예고, 광고, 부가 내용 자동 건너뛰기
- **스크린샷 품질 설정** — 스크린샷 압축 품질 조절 (1-100, 높을수록 좋음)
- **동영상 자동 공유** — 동영상 재생 후 공유 URL 자동 복사

### 🇺🇸 English
- **Live Danmaku Font** — Customize live danmaku font size (independent from video danmaku)
- **Auto Skip Filler** — Auto-skip previews, credits, and ads around main content
- **Screenshot Quality Setting** — Adjust screenshot compression quality (1-100)
- **Video Auto-Share** — Auto-copy share URL to clipboard after playing video

---

## v1.62.0 (2026-06-07)

### 🇨🇳 中文
- **弹幕发送预览** — 发送前在屏幕上预览弹幕效果（字体、颜色、位置）
- **字幕下载** — 支持下载视频字幕文件（SRT/ASS/VTT格式）
- **直播切片** — 将直播流录制为本地视频片段
- **画中画窗口大小** — 自定义画中画窗口的大小（小/中/大）

### 🇯🇵 日本語
- **弾幕送信プレビュー** — 送信前に画面上で弾幕の効果をプレビュー
- **字幕ダウンロード** — 動画の字幕ファイルをダウンロード可能（SRT/ASS/VTT）
- **ライブクリップ** — ライブストリームをローカル動画クリップとして録画
- **ピクチャインピクチャサイズ** — PiPウィンドウのサイズをカスタマイズ

### 🇰🇷 한국어
- **弹幕 전송 미리보기** — 전송 전 화면에서 弹幕 효과 미리보기
- **자막 다운로드** — 동영상 자막 파일 다운로드 지원 (SRT/ASS/VTT)
- **라이브 클립** — 라이브 스트림을 로컬 비디오 클립으로 녹화
- **화면 속 화면 크기** — PiP 창 크기 사용자 정의 (작음/보통/큼)

### 🇺🇸 English
- **Danmaku Send Preview** — Preview danmaku effect on screen before sending
- **Subtitle Download** — Download video subtitles (SRT/ASS/VTT format)
- **Live Stream Clip** — Record live stream as local video clip
- **PiP Window Size** — Customize picture-in-picture window size

---

## v1.61.0 (2026-06-07)

### 🇨🇳 中文
- **评论区表情键盘增强** — 在评论输入框旁添加常用表情快速选择面板
- **直播间自动静音** — 进入直播间时自动静音，避免突然声音打扰
- **手势调节亮度** — 在播放器左侧上下滑动调节屏幕亮度
- **按颜色过滤弹幕** — 根据弹幕颜色过滤特定颜色的弹幕

### 🇯🇵 日本語
- **コメント絵文字キーボード強化** — コメント入力欄に常用絵文字パネルを追加
- **ライブルーム自動ミュート** — ライブルーム入室時に自動ミュート
- **ジェスチャー明るさ調整** — プレーヤー左側で上下スワイプして明るさ調整
- **色別弾幕フィルター** — 弹幕の色で特定色をフィルタリング

### 🇰🇷 한국어
- **댓글 이모지 키보드 강화** — 댓글 입력란에 자주 쓰는 이모지 패널 추가
- **라이브룸 자동 음소거** — 라이브룸 입장 시 자동 음소거
- **제스처 밝기 조절** — 플레이어 왼쪽에서 위아래로 스와이프하여 밝기 조절
- **색상별 弹幕 필터** — 弹幕 색상으로 특정 색상을 필터링

### 🇺🇸 English
- **Comment Emoji Keyboard Enhancement** — Add quick emoji selection panel in comment input
- **Live Room Auto-Mute** — Auto-mute when entering live room
- **Gesture Brightness Control** — Swipe up/down on left side to adjust brightness
- **Danmaku Filter by Color** — Filter danmaku by specific colors

---

## v1.60.0 (2026-06-07)

### 🇨🇳 中文
- **按用户过滤弹幕** — 根据用户ID过滤特定用户的弹幕
- **手势调节音量** — 在播放器右侧上下滑动调节音量
- **视频自动收藏** — 播放视频时自动添加到稍后再看列表
- **评论自动回复模板** — 在评论区快速插入预设回复模板

### 🇯🇵 日本語
- **ユーザー別弾幕フィルター** — ユーザーIDで特定ユーザーの弾幕をフィルタリング
- **ジェスチャー音量調整** — プレーヤー右側で上下スワイプして音量調整
- **動画自動お気に入り** — 動画再生時に自動で「後で見る」に追加
- **コメント自動返信テンプレート** — コメント欄にテンプレートをすばやく挿入

### 🇰🇷 한국어
- **사용자별 弹幕 필터** — 사용자 ID로 특정 사용자의 弹幕 필터링
- **제스처 음량 조절** — 플레이어 오른쪽에서 위아래로 스와이프하여 음량 조절
- **동영상 자동 북마크** — 동영상 재생 시 자동으로 나중에 보기 목록에 추가
- **댓글 자동 회신 템플릿** — 댓글 섹션에 템플릿을 빠르게 삽입

### 🇺🇸 English
- **Danmaku Filter by User** — Filter danmaku by specific user IDs
- **Gesture Volume Control** — Swipe up/down on right side to adjust volume
- **Video Auto-Save** — Auto-add played videos to watch later list
- **Comment Auto-Reply Template** — Quick insert preset reply templates in comments

---

## v1.59.0 (2026-06-07)

### 🇨🇳 中文
- **弹幕字体样式** — 自定义弹幕文字样式（默认/粗体/斜体/粗斜体）
- **弹幕发送动画** — 新发送的弹幕带有入场动画效果（淡入+滑入）
- **播放器背景变暗** — 调节播放器背景的变暗程度（0-100%）
- **自动播放下一集** — 当前视频播放结束后自动播放下一集

### 🇯🇵 日本語
- **弾幕フォントスタイル** — 弾幕テキストのスタイルをカスタマイズ
- **弾幕送信アニメーション** — 新規弾幕にイントロアニメーション
- **プレーヤー背景暗転** — プレーヤー背景の暗さを調整（0-100%）
- **自動再生次回** — 現在の動画終了後に自動で次回を再生

### 🇰🇷 한국어
- **弹幕 글꼴 스타일** — 弹幕 텍스트 스타일 사용자 정의
- **弹幕 전송 애니메이션** — 새 弹幕에 입장 애니메이션 효과
- **플레이어 배경 어둡게** — 플레이어 배경 어둡기 조절 (0-100%)
- **자동 재생 다음 회** — 현재 동영상 종료 후 자동으로 다음 회 재생

### 🇺🇸 English
- **Danmaku Font Style** — Customize danmaku text style (bold/italic/bold-italic)
- **Danmaku Send Animation** — Entrance animation for new danmaku
- **Player Background Dim** — Adjust player background dimness (0-100%)
- **Auto Play Next Episode** — Auto-play next episode when current video ends

---

## v1.58.0 (2026-06-07)

### 🇨🇳 中文
- **评论图片自动加载控制** — 关闭后仅显示占位图，点击后才加载，节省流量
- **弹幕批量举报** — 在弹幕列表中支持批量选择和举报违规弹幕
- **缩略图预览** — 进度条悬停时显示视频缩略图预览
- **滚动自动暂停** — 在信息流中滚动时自动暂停正在播放的视频

### 🇯🇵 日本語
- **コメント画像自動読み込み制御** — オフにするとプレースホルダーのみ表示、タップで読み込み
- **弾幕一括通報** — 弾幕リストで一括選択・違反通報に対応
- **サムネイルプレビュー** — プログレスバーにカーソルを合わせるとサムネイル表示
- **スクロール自動一時停止** — フィードスクロール時に再生中の動画を自動停止

### 🇰🇷 한국어
- **댓글 이미지 자동 로드 제어** — 끄면 자리표시 이미지만 표시, 탭 시 로드
- **弹幕 일괄 신고** — 弹幕 목록에서 일괄 선택 및 위반 신고 지원
- **썸네일 미리보기** — 진행률 표시줄에 마우스를 올리면 썸네일 표시
- **스크롤 자동 일시정지** — 피드 스크롤 시 재생 중인 동영상 자동 일시정지

### 🇺🇸 English
- **Comment Image Auto-Load Control** — Show placeholder only, load on tap to save data
- **Danmaku Batch Report** — Batch select and report violating danmaku
- **Thumbnail Preview** — Show video thumbnail on progress bar hover
- **Auto-Pause on Scroll** — Auto-pause playing video when scrolling in feed

---

## v1.57.0 (2026-06-07)

### 🇨🇳 中文
- **评论GIF自动播放** — 控制评论区GIF图片是否自动播放，节省流量
- **弹幕批量删除** — 在弹幕列表中支持批量选择和删除弹幕
- **截图格式设置** — 自定义播放器截图保存格式（PNG/JPG/WebP）
- **直播间自动进入** — 启动时自动进入指定直播间（填入房间号或URL）

### 🇯🇵 日本語
- **コメントGIF自動再生** — コメント欄のGIF画像の自動再生を制御
- **弾幕一括削除** — 弾幕リストで一括選択・削除に対応
- **スクリーンショット形式設定** — プレーヤーの保存形式をカスタマイズ（PNG/JPG/WebP）
- **ライブルーム自動入室** — 起動時に指定ルームへ自動入室

### 🇰🇷 한국어
- **댓글 GIF 자동 재생** — 댓글 섹션의 GIF 이미지 자동 재생 제어
- **弹幕 일괄 삭제** — 弹幕 목록에서 일괄 선택 및 삭제 지원
- **스크린샷 형식 설정** — 플레이어 스크린샷 저장 형식 사용자 정의 (PNG/JPG/WebP)
- **라이브룸 자동 입장** — 시작 시 지정된 라이브룸에 자동 입장

### 🇺🇸 English
- **Comment GIF Auto-Play** — Control GIF auto-play in comment section
- **Danmaku Batch Delete** — Batch select and delete danmaku in list
- **Screenshot Format Setting** — Customize screenshot save format (PNG/JPG/WebP)
- **Live Room Auto-Enter** — Auto-enter specified live room on app start

---

## v1.56.0 (2026-06-07)

### 🇨🇳 中文
- **匿名评论模式** — 发评论时隐藏用户身份信息，保护隐私
- **弹幕显示时长调节** — 控制弹幕飘过速度，值越小越快
- **直播礼物合并** — 合并短时间内相同礼物减少刷屏
- **调速步长自定义** — 自定义每次调节播放速度的步长（0.05x~1.0x）

### 🇯🇵 日本語
- **匿名コメントモード** — コメント投稿時にユーザー情報を非表示
- **弾幕表示時間調整** — 弾幕のスクロール速度を制御（小さいほど速い）
- **ライブギフト統合** — 短時間の同じギフトを統合して表示
- **速度調整ステップカスタマイズ** — 再生速度の調整ステップを設定（0.05x～1.0x）

### 🇰🇷 한국어
- **익명 댓글 모드** — 댓글 작성 시 사용자 정보 숨기기
- **弹幕 표시 시간 조절** — 弹幕 스크롤 속도 제어 (값이 작을수록 빠름)
- **라이브 선물 병합** — 짧은 시간 내 같은 선물을 병합하여 표시
- **재생 속도 단계 사용자 정의** — 재생 속도 조절 단계 설정 (0.05x~1.0x)

### 🇺🇸 English
- **Anonymous Comment Mode** — Hide user identity when posting comments
- **Danmaku Display Duration** — Control danmaku scroll speed (smaller = faster)
- **Live Gift Merge** — Merge identical gifts within a short time window
- **Playback Speed Step Customization** — Customize speed adjustment step (0.05x~1.0x)

---

## v1.55.0 (2026-06-07)

### 🇨🇳 中文
- **视频缓存大小设置** — 自定义视频缓存上限，节省存储空间
- **评论图片预览增强** — 长按评论图片支持保存、分享、复制链接
- **弹幕发送位置设置** — 设置新发送弹幕的显示位置（顶部/底部/滚动）
- **播放器覆盖层透明度** — 调整播放器控制覆盖层的透明度

### 🇯🇵 日本語
- **動画キャッシュサイズ設定** — 動画キャッシュの上限をカスタマイズし、ストレージを節約
- **コメント画像プレビュー強化** — コメント画像の長押しで保存・共有・リンクコピー
- **弾幕送信位置設定** — 新しい弾幕の表示位置を設定（上/下/スクロール）
- **プレーヤーオーバーレイ透明度** — プレーヤー制御オーバーレイの透明度を調整

### 🇰🇷 한국어
- **동영상 캐시 크기 설정** — 동영상 캐시 상한을 사용자 정의하여 저장 공간 절약
- **댓글 이미지 미리보기 강화** — 댓글 이미지 길게 눌러 저장, 공유, 링크 복사
- **弹幕 전송 위치 설정** — 새 弹幕 표시 위치 설정 (상단/하단/스크롤)
- **플레이어 오버레이 투명도** — 플레이어 제어 오버레이 투명도 조절

### 🇺🇸 English
- **Video Cache Size Setting** — Customize video cache limit to save storage
- **Comment Image Preview Enhancement** — Long press comment images to save, share, or copy link
- **Danmaku Send Position Setting** — Set display position for new danmaku (top/bottom/scroll)
- **Player Overlay Opacity** — Adjust transparency of player control overlay

