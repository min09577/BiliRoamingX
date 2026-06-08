<div align="center">

# BiliRoamingX (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-1.40.0-green)](https://github.com/min09577/BiliRoamingX)
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

**Status:** 70+ patches working, new PiP and gesture features added, all core features verified

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

