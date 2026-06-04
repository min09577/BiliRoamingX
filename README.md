<div align="center">

# BiliRoamingX (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-1.39.0-green)](https://github.com/min09577/BiliRoamingX)
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

**Status:** 70+ patches working, all core features verified

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
- **Danmaku keyword highlight**
- **Custom danmaku speed**
- **Danmaku display area control**

### Changelog

#### 2026-06-04 (v1.39.0)
- **New:** Video bookmark — add timestamped bookmarks and notes while watching videos for quick navigation
- **Settings:** Added toggle in player settings

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
- **弹幕关键词高亮**
- **自定义弹幕速度**
- **弹幕显示区域控制**

### 更新日志

#### 2026-06-04 (v1.39.0)
- **新功能：** 视频书签 — 在视频中添加带时间戳的书签和笔记，方便快速跳转到特定时刻
- **设置：** 播放器设置中添加开关

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
- **弾幕キーワードハイライト**
- **カスタム弾幕速度**
- **弾幕表示エリア制御**

### 変更履歴

#### 2026-06-04 (v1.39.0)
- **新機能：** 動画ブックマーク — 動画視聴中にタイムスタンプ付きブックマークとメモを追加し、特定の場面に素早くジャンプ可能
- **設定：** プレイヤー設定にトグル追加

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
- README 4言語サポート（CN