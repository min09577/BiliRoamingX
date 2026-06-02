<div align="center">

# BiliRoamingX (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-1.32.0-green)](https://github.com/min09577/BiliRoamingX)
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

### Changelog

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
- **New:** Live room watermark removal — recursive view tree traversal with resource ID matching
- **New:** Live room mosaic removal — removes overlay/mask views from live streams
- **New:** CID display in video codec info toast
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

#### 2026-05-28 (v1.23.3 → v1.24.1)
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

基于 BiliRoamingX v1.23.3，由 AI 辅助开发增强。

**目标版本：** 哔哩哔哩 8.95.0（Android 64位）

**状态：** 70+ 补丁全部运行正常，核心功能已验证

### 功能特性
- 解除番剧区域限制
- 移除页面组件（广告、大会员横幅、关注按钮）
- 自定义播放速度（默认 + 长按倍速）
- 自定义直播/视频默认画质
- 强制 HDR 画质
- 字幕样式调整 + 导入/保存
- 捏合缩放视频填满屏幕
- 自动领取 B 币
- 分享链接净化
- 推荐、热门、动态过滤
- 深色模式闪屏背景
- 复制评论和视频信息（含 IP 属地、楼层号、回复数）
- 调用外部下载器
- 开屏广告屏蔽（OkHttp API hook）
- 充电鸣谢页面 & 游戏推荐屏蔽
- 无限试看画质（移除 deadline 参数）
- 弹幕关键词过滤（正则 + 纯文本）
- 弹幕显示控制（透明度、密度、字号、时间偏移、弹幕池过滤）
- 视频编码信息显示（编码器、分辨率、码率、CID、UP主信息）
- 视频统计面板（播放量、弹幕数、点赞、投币、收藏、评论数）
- 截图/录屏解锁
- 视频简介自动展开 + 隐藏浮动按钮
- 自定义启动画面
- 屏蔽评论搜索关键词
- 禁止首页自动刷新
- 循环播放（播完自动重播）
- **直播间去水印**
- **直播间去马赛克遮罩**
- **自定义弹幕速度**
- **弹幕显示区域控制**
- **弹幕关键词高亮**

### 更新日志

#### 2026-06-02 (v1.36.0)
- **新增：** 默认视频画面比例 — 设置视频默认画面比例（默认/适应/填充）
- **设置：** 播放器设置页面新增选择对话框

#### 2026-06-02 (v1.35.0)
- **新增：** 评论区默认按时间排序 — 开启后评论区默认按时间排序（最新评论在前）
- **设置：** 评论过滤设置页面新增开关

#### 2026-06-02 (v1.34.0)
- **新增：** 弹幕关键词高亮 — 匹配关键词的弹幕会变成醒目颜色（支持7种颜色选择）
- **新增：** 自定义弹幕速度 — 控制弹幕滚动速度（0-10级）
- **新增：** 弹幕显示区域 — 自定义弹幕显示区域百分比（0-100%）
- **设置：** 弹幕显示设置页面新增速度/区域滑块和关键词高亮输入框

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0:** 自定义弹幕速度 & 显示区域 — hook DmViewReply 修改 playerDanmakuSpeed 和 playerDanmakuDomain
- **v1.32.0:** 直播间去水印 — 递归视图树遍历 + 资源 ID 匹配移除左上角水印
- **v1.32.0:** 直播间去马赛克 — 移除直播流中的遮罩/马赛克覆盖层
- **v1.32.0:** 视频编码信息 Toast 显示 CID
- **构建：** 70+ 补丁全部成功，BKS keystore 签名

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0：** 视频简介自动展开 — 通过反射 hook ExpandableLayout，加载时自动展开视频描述
- **v1.31.0：** 隐藏浮动按钮 — 隐藏视频详情页小窗播放浮动按钮
- **v1.30.0：** 循环播放 — 视频播完自动重播（注入 relates feed）
- **v1.29.0：** 屏蔽评论搜索关键词 — hook ReplyMainList 清除不想要的搜索话题
- **v1.29.0：** 禁止首页自动刷新 — 阻止首页内容自动刷新

#### 2026-05-30 (v1.28.0)
- **修复：** CopyEnhancePatch 寄存器错误 — ConversationCopy 注入 `invoke-static {p0, p2, v0}` 中 p0 映射到 v16+ 超出 4-bit 限制，用 `move-object/from16` 移至低寄存器解决
- **修复：** APK 签名失败 — JDK 17 JCE 拒绝认证 CLI 内嵌的未签名 BouncyCastle，将独立签名的 BC 1.77 JAR 放在 classpath 最前面解决
- **新增：** 视频简介自动展开（VideoDescExpandPatch）
- **新增：** 隐藏浮动按钮选项

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0：** 强制 HDR 画质开关 — fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0：** 弹幕时间偏移（-30~+30s）+ 弹幕池过滤（0=全部, 1=普通, 2=字幕, 3=特殊）
- **v1.25.0：** 视频信息面板增强 — UP主 MID、粉丝数、分享数；评论楼层号 + 回复数显示
- **新补丁：** 弹幕关键词过滤、编码信息显示、弹幕显示调整、截图/录屏解锁、评论 IP 属地、AV/BV 号显示、视频统计面板

#### 2026-05-28 (v1.23.3 → v1.24.1)
- **关键修复：** 70+ 补丁全部适配哔哩哔哩 8.95.0
  - Fingerprint 容错（不匹配时 return 替代 throw）
  - DmAdvert 反射修复（NoClassDefFoundError）
  - VerifyError 修复（禁用 goto 指令修改）
- **新补丁：** 开屏广告屏蔽（OkHttp hook）、充电鸣谢屏蔽、游戏推荐屏蔽
- **去广告：** 全 UI 配置完成（首页、视频详情、直播间、动态、我的页）

#### 2026-05-27 (v1.23.3 — 初始 Fork)
- 从 BiliRoamingX/BiliRoamingX fork
- 本地构建修复（复制 libbiliroamingx.so 到 patches 资源目录）
- 8.95.0 theseus_playlist_default_order 修复
- PlayerSettingHelperFingerprint 8.95.0 兼容性修复
- 60+ 补丁在 MuMu 模拟器上验证通过
- README 四语言支持

---

<a name="日本語"></a>
## 日本語

### BiliRoamingX - AI 増強版

BiliRoamingX v1.23.3 ベース、AI支援開発で増強。

**対象バージョン：** ビリビリ 8.95.0（Android 64ビット）

**ステータス：** 70+ パッチ全て動作、コア機能検証済み

### 機能
- アニメ地域制限解除
- ページコンポーネント削除（広告、VIPバナー、フォローボタン）
- カスタム再生速度（デフォルト + 長押し倍速）
- カスタム配信/ビデオデフォルト画質
- HDR画質強制
- 字幕スタイル調整 + インポート/保存
- ピンチズームで画面いっぱいにビデオ拡大
- Bコイン自動受け取り
- 共有リンク净化
- おすすめ、人気、動態フィルタリング
- ダークモードスプラッシュ背景
- コメント・ビデオ情報コピー（IP所在地、フロア番号、返信数含む）
- 外部ダウンローダー呼び出し
- スプラッシュ広告ブロック（OkHttp API hook）
- チャージング感謝ページ＆ゲームおすすめブロック
- 無限試看画質（deadlineパラメータ除去）
- タン幕キーワードフィルタ（正規表現 + テキスト）
- タン幕表示制御（透明度、密度、フォントサイズ、タイムオフセット、プールフィルタ）
- ビデオコーデック情報表示（コーデック、解像度、ビットレート、CID、UP主情報）
- ビデオ統計パネル（再生数、タン幕数、いいね、コイン、お気に入り、コメント数）
- スクリーンショット/録画ロック解除
- ビデオ説明自動展開 + フローティングボタン非表示
- カスタムスプラッシュ画面
- コメント検索キーワードブロック
- ホームページ自動更新無効
- ループ再生（終了後自動リプレイ）
- **配信ルームウォーターマーク除去**
- **配信ルームモザイク除去**
- **カスタムタン幕速度**
- **タン幕表示エリア制御**
- **タン幕キーワードハイライト**

### 変更履歴

#### 2026-06-02 (v1.36.0)
- **新規：** デフォルトビデオアスペクト比 — デフォルトのビデオアスペクト比を設定（デフォルト/フィット/フィル）
- **設定：** プレイヤー設定ページに選択ダイアログを追加

#### 2026-06-02 (v1.35.0)
- **新規：** コメント時間順ソート — 有効にするとコメントが時間順にソート（最新が最初）
- **設定：** コメントフィルタ設定ページにスイッチを追加

#### 2026-06-02 (v1.34.0)
- **新規：** タン幕キーワードハイライト — マッチするタン幕が目立つ色に変更（7色選択可能）
- **新規：** カスタムタン幕速度 — タン幕スクロール速度制御（0-10レベル）
- **新規：** タン幕表示エリア — タン幕表示エリアのパーセンテージをカスタマイズ（0-100%）
- **設定：** タン幕表示設定ページに速度/エリアスライダーとキーワードハイライト入力追加

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0：** カスタムタン幕速度 & 表示エリア — DmViewReplyをhookしてplayerDanmakuSpeedとplayerDanmakuDomainを変更
- **v1.32.0：** 配信ルームウォーターマーク除去 — 再帰ビューツリートラバーサル + リソースIDマッチング
- **v1.32.0：** 配信ルームモザイク除去 — 配信ストリームのオーバーレイ/マスクビューを除去
- **v1.32.0：** ビデオコーデック情報ToastにCID表示

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0：** ビデオ説明自動展開 — リフレクション経由でExpandableLayoutをhook
- **v1.31.0：** フローティングボタン非表示 — ビデオ詳細ページのミニプレーヤーボタン非表示
- **v1.30.0：** ループ再生 — 再生終了時自動リプレイ（relates feed注入）
- **v1.29.0：** コメント検索キーワードブロック — ReplyMainListをhookして検索トピックをクリア
- **v1.29.0：** ホームページ自動更新無効 — コンテンツ自動更新を防止

#### 2026-05-30 (v1.28.0)
- **修正：** CopyEnhancePatchレジスタエラー — `invoke-static {p0, p2, v0}`のp0がv16+にマッピング（4ビット制限超過）。`move-object/from16`で低位レジスタに移動して解決
- **修正：** APK署名失敗 — JDK 17 JCEがCLI内蔵の未署名BouncyCastleを拒否。独立署名済みBC 1.77 JARをclasspath最前方に配置して解決

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0：** HDR画質強制トグル
- **v1.26.0：** タン幕タイムオフセット（-30~+30s）+ プールフィルタ
- **v1.25.0：** ビデオ情報パネル強化 — コメントフロア番号 + 返信数表示

#### 2026-05-28 (v1.23.3 → v1.24.1)
- **重要修正：** 70+パッチをビリビリ 8.95.0に完全適応
- **新パッチ：** スプラッシュ広告ブロック、チャージング感謝ブロック、ゲームおすすめブロック

#### 2026-05-27 (v1.23.3 — 初期Fork)
- BiliRoamingX/BiliRoamingXからフォーク
- 60+パッチがMuMuエミュレーターで検証済み

---

<a name="한국어"></a>
## 한국어

### BiliRoamingX - AI 강화판

BiliRoamingX v1.23.3 기반, AI 보조 개발로 강화.

**대상 버전:** 빌리빌리 8.95.0 (Android 64비트)

**상태:** 70+ 패치 모두 정상 작동, 핵심 기능 검증 완료

### 기능
- 애니메이션 지역 제한 해제
- 페이지 컴포넌트 제거 (광고, VIP 배너, 팔로우 버튼)
- 사용자 정의 재생 속도 (기본 + 길게 눌러 배속)
- 사용자 정의 방송/비디오 기본 화질
- HDR 화질 강제
- 자막 스타일 조정 + 가져오기/저장
- 핀치 줌으로 화면 가득 비디오 확대
- B코인 자동 수령
- 공유 링크 정화
- 추천, 인기, 동적 필터링
- 다크 모드 스플래시 배경
- 댓글 및 비디오 정보 복사 (IP 위치, 층 번호, 답글 수 포함)
- 외부 다운로더 호출
- 스플래시 광고 차단 (OkHttp API hook)
- 충전 감사 페이지 & 게임 추천 차단
- 무한 체험 화질 (deadline 파라미터 제거)
- 탄막 키워드 필터 (정규식 + 텍스트)
- 탄막 표시 제어 (투명도, 밀도, 폰트 크기, 시간 오프셋, 풀 필터)
- 비디오 코덱 정보 표시 (코덱, 해상도, 비트레이트, CID, UP주 정보)
- 비디오 통계 패널 (재생수, 탄막수, 좋아요, 코인, 즐겨찾기, 댓글수)
- 스크린샷/녹화 잠금 해제
- 비디오 설명 자동 펼치기 + 플로팅 버튼 숨기기
- 사용자 정의 스플래시 화면
- 댓글 검색 키워드 차단
- 홈페이지 자동 새로고침 비활성화
- 루프 재생 (종료 후 자동 리플레이)
- **방송 워터마크 제거**
- **방송 모자이크 제거**
- **사용자 정의 탄막 속도**
- **탄막 표시 영역 제어**
- **탄막 키워드 하이라이트**

### 변경 이력

#### 2026-06-02 (v1.36.0)
- **신규:** 기본 비디오 화면 비율 — 기본 비디오 화면 비율 설정 (기본/맞춤/채우기)
- **설정:** 플레이어 설정 페이지에 선택 대화 상자 추가

#### 2026-06-02 (v1.35.0)
- **신규:** 댓글 시간순 정렬 — 활성화 시 댓글이 시간순으로 정렬 (최신이 먼저)
- **설정:** 댓글 필터 설정 페이지에 스위치 추가

#### 2026-06-02 (v1.34.0)
- **신규:** 탄막 키워드 하이라이트 — 일치하는 탄막이 눈에 띄는 색상으로 변경 (7가지 색상 선택 가능)
- **신규:** 사용자 정의 탄막 속도 — 탄막 스크롤 속도 제어 (0-10 레벨)
- **신규:** 탄막 표시 영역 — 탄막 표시 영역 비율 사용자 정의 (0-100%)
- **설정:** 탄막 표시 설정 페이지에 속도/영역 슬라이더 및 키워드 하이라이트 입력 추가

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0:** 사용자 정의 탄막 속도 & 표시 영역 — DmViewReply를 hook하여 playerDanmakuSpeed와 playerDanmakuDomain 수정
- **v1.32.0:** 방송 워터마크 제거 — 재귀 뷰 트리 순회 + 리소스ID 매칭으로 워터마크 제거
- **v1.32.0:** 방송 모자이크 제거 — 방송 스트림의 오버레이/마스크 뷰 제거
- **v1.32.0:** 비디오 코덱 정보 Toast에 CID 표시
- **빌드:** 70+ 패치 모두 성공, BKS keystore 서명

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0:** 비디오 설명 자동 펼치기 — 리플렉션으로 ExpandableLayout hook
- **v1.31.0:** 플로팅 버튼 숨기기 — 비디오 상세 페이지 미니플레이어 버튼 숨김
- **v1.30.0:** 루프 재생 — 재생 종료 시 자동 리플레이 (relates feed 주입)
- **v1.29.0:** 댓글 검색 키워드 차단 — ReplyMainList를 hook하여 검색 토픽 클리어
- **v1.29.0:** 홈페이지 자동 새로고침 비활성화 — 콘텐츠 자동 새로고침 방지

#### 2026-05-30 (v1.28.0)
- **수정:** CopyEnhancePatch 레지스터 오류 — `invoke-static {p0, p2, v0}`의 p0가 v16+에 매핑 (4비트 제한 초과). `move-object/from16`으로 해결
- **수정:** APK 서명 실패 — JDK 17 JCE가 미서명 BouncyCastle 거부. 서명된 BC 1.77 JAR를 classpath前方에 배치하여 해결

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0:** HDR 화질 강제 토글
- **v1.26.0:** 탄막 시간 오프셋 (-30~+30s) + 풀 필터
- **v1.25.0:** 비디오 정보 패널 강화 — 댓글 층 번호 + 답글 수 표시

#### 2026-05-28 (v1.23.3 → v1.24.1)
- **핵심 수정:** 전체 70+ 패치를 bilibili 8.95.0에 적응
- **새 패치:** 스플래시 광고 차단, 충전 감사 차단, 게임 추천 차단

#### 2026-05-27 (v1.23.3 — 초기 Fork)
- BiliRoamingX/BiliRoamingX에서 포크
- 60+ 패치가 MuMu 에뮬레이터에서 검증됨

---

## Notes / 说明 / 注意事項 / 참고사항

- Personal use version, for study and research only / 仅供个人学习研究使用 / 個人の学習・研究のみ / 개인 학습 및 연구 전용
- Original project by Kofua (github.com/zjns)

## Links / 链接 / リンク / 링크

- Original: https://github.com/BiliRoamingX/BiliRoamingX
- Fork: https://github.com/min09577/BiliRoamingX
- Releases: https://github.com/min09577/BiliRoamingX/releases
- Bilibili: https://www.bilibili.com
- ReVanced: https://revanced.app

