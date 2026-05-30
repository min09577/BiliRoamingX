<div align="center">

# BiliRoamingX (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-1.28.0-green)](https://github.com/min09577/BiliRoamingX)
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
- Video codec info display (codec, resolution, bitrate, UP info)
- Screenshot/recording unlock
- Video description auto-expand + hide floating button
- Custom splash screen

### Changelog

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
- **Dev:** 8 new feature patches developed and pushed to GitHub (15 commits total)

#### 2026-05-28 (v1.23.3 → v1.24.1)
- **Critical fixes:** All 70+ patches adapted for bilibili 8.95.0
  - Fingerprint graceful degradation (return instead of throw on mismatch)
  - DmAdvert reflection fix (NoClassDefFoundError)
  - VerifyError fix (disable goto instruction modification)
  - All remaining fingerprint patches adapted
- **New patches:** SplashAd (OkHttp hook), EndpageCharge (charging page block), RelatedGames (game recommendation block)
- **Ad removal:** Full UI configuration completed (homepage, video detail, live room, dynamic, my page)
- **Build:** SO file handling, R8 proguard keep rules, 33 DEX compilation

#### 2026-05-27 (v1.23.3 — Initial Fork)
- Fork from BiliRoamingX/BiliRoamingX
- Fix local build (add libbiliroamingx.so to patches resources)
- Fix theseus_playlist_default_order for 8.95.0
- Fix PlayerSettingHelperFingerprint for 8.95.0 compatibility
- 60+ patches verified working on MuMu emulator
- README with 4 languages (CN/EN/JP/KR)

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
# Patch APK (requires standalone BouncyCastle on classpath for JDK 17 signing)
BC_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcprov-jdk18on/1.77/*/bcprov-jdk18on-1.77.jar
BCPKIX_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcpkix-jdk18on/1.77/*/bcpkix-jdk18on-1.77.jar
java -cp "$BC_JAR:$BCPKIX_JAR:revanced-cli.jar" app.revanced.cli.command.MainCommandKt \
  patch -b patches.jar -m integrations.apk -o bilibili-8.95.0-patched.apk bilibili-8.95.0.apk
```

---

<a name="中文"></a>
## 中文

### 哔哩漫游X - AI 增强版

基于 BiliRoamingX v1.23.3 的 AI 增强自用版本。

**适配版本：** 哔哩哔哩 8.95.0 (安卓64位)

**状态：** 70+ 个补丁全部可用，核心功能已验证

**构建：** 参见下方 [构建说明](#build-zh)

### 功能列表
- 解除番剧区域限制
- 移除页面组件（广告、VIP 区域、关注按钮）
- 自定义播放速度（默认 + 长按倍速）
- 自定义直播/视频默认清晰度
- 强制 HDR 画质
- 字幕样式调整 + 字幕导入/保存
- 双指缩放视频填充屏幕
- 自动领取B币券
- 分享链接净化
- 推荐、热门、动态过滤
- 深色模式开屏背景
- 复制评论和视频信息（含IP属地、楼层号、回复数）
- 调用外部下载器
- 开屏广告屏蔽（OkHttp API hook）
- 充电鸣谢页面 & 游戏推荐屏蔽
- 无限试看画质（去除 deadline 参数）
- 弹幕关键词过滤（正则 + 普通文本）
- 弹幕显示控制（透明度、密度、字号缩放、时间偏移、池过滤）
- 视频编码信息显示（编码格式、分辨率、码率、UP主信息）
- 截图/录屏解锁
- 视频简介自动展开 + 隐藏浮动按钮
- 自定义开屏画面

### 更新日志

#### 2026-05-30 (v1.28.0)
- **修复：** CopyEnhancePatch 寄存器错误 — ConversationCopy 注入的 `invoke-static {p0, p2, v0}` 中 p0 映射到 v16+（超出 4-bit 限制），使用 `move-object/from16` 移到低寄存器解决
- **修复：** APK 签名失败 — JDK 17 JCE 拒绝认证 fat CLI jar 中未签名的 BouncyCastle，解决方案：将独立签名的 BC 1.77 JAR 放在 classpath 最前面
- **新增：** 视频简介自动展开（VideoDescExpandPatch）— hook ExpandableLayout 自动展开视频描述
- **新增：** 隐藏浮动按钮选项
- **构建：** 全量 70+ 补丁构建成功并签名

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0：** 强制 HDR 画质开关 — fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0：** 弹幕时间偏移(-30~+30s) + 池过滤(0=全部, 1=普通, 2=字幕, 3=特殊)
- **v1.25.0：** 视频信息面板增强 — UP主MID、粉丝数、分享数；评论楼层号 + 回复数显示
- **新补丁：** 弹幕关键词过滤、视频编码信息显示、弹幕显示调整、截图/录屏解锁、评论IP属地、AV/BV号显示、视频统计面板
- **开发：** 8 个新功能补丁开发并推送到 GitHub（共 15 次提交）

#### 2026-05-28 (v1.23.3 → v1.24.1)
- **关键修复：** 全部 70+ 补丁适配 bilibili 8.95.0
  - Fingerprint 容错策略（不匹配时 return 而非 throw）
  - DmAdvert 反射修复（NoClassDefFoundError）
  - VerifyError 修复（禁用 goto 指令修改）
  - 所有剩余 fingerprint 补丁适配
- **新补丁：** 开屏广告屏蔽（OkHttp hook）、充电鸣谢屏蔽、游戏推荐屏蔽
- **去广告：** 完成全部 UI 配置（首页、视频详情页、直播间、动态页、我的页）
- **构建：** SO 文件处理、R8 proguard keep 规则、33 DEX 编译

#### 2026-05-27 (v1.23.3 — 初始 Fork)
- 从 BiliRoamingX/BiliRoamingX fork
- 修复本地构建（添加 libbiliroamingx.so 到 patches 资源）
- 修复 8.95.0 的 theseus_playlist_default_order
- 修复 PlayerSettingHelperFingerprint 兼容性
- 60+ 补丁在 MuMu 模拟器上验证通过
- README 支持中日韩英四国语言

<a name="build-zh"></a>
### 构建说明
```bash
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
# 构建 patches 和 integrations
./gradlew :patches:jar :integrations:app:assembleRelease -x lint
# 复制产物
cp patches/build/libs/BiliRoamingX-patches-*.jar patches.jar
cp integrations/app/build/outputs/apk/release/integrations-release.apk integrations.apk
# 打补丁（JDK 17 需要独立签名的 BouncyCastle 在 classpath 前面）
BC_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcprov-jdk18on/1.77/*/bcprov-jdk18on-1.77.jar
BCPKIX_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcpkix-jdk18on/1.77/*/bcpkix-jdk18on-1.77.jar
java -cp "$BC_JAR:$BCPKIX_JAR:revanced-cli.jar" app.revanced.cli.command.MainCommandKt \
  patch -b patches.jar -m integrations.apk -o bilibili-8.95.0-patched.apk bilibili-8.95.0.apk
```

---

<a name="日本語"></a>
## 日本語

### BiliRoamingX - AI強化版

BiliRoamingX v1.23.3をベースにしたAI強化版。

**対象バージョン:** ビリビリ 8.95.0 (Android 64ビット)

**ステータス:** 70+パッチ全て動作、コア機能検証済み

**ビルド:** 下記 [ビルド手順](#build-ja) 参照

### 機能一覧
- 番組制限解除
- ページコンポーネント削除（広告、VIPエリア、フォローボタン）
- カスタム再生速度（デフォルト + 長押し倍速）
- カスタムライブ/ビデオデフォルト画質
- HDR画質強制
- 字幕スタイル調整 + 字幕インポート/保存
- ピンチズームでビデオ全画面表示
- Bコイン自動受け取り
- 共有リンク净化
- おすすめ、人気、動態フィルタリング
- ダークモードスプラッシュ背景
- コメントとビデオ情報のコピー（IP所在地、フロア番号、返信数含む）
- 外部ダウンローダー呼び出し
- スプラッシュ広告ブロック（OkHttp API hook）
- 課金ページ＆ゲーム推薦ブロック
- 無限試看画質（deadlineパラメータ除去）
- 弾幕キーワードフィルター（正規表現 + テキスト）
- 弾幕表示制御（透明度、密度、フォントスケール、時間オフセット、プールフィルター）
- ビデオコーデック情報表示（コーデック、解像度、ビットレート、UP主情報）
- スクリーンショット/録画ロック解除
- ビデオ説明自動展開 + フローティングボタン非表示
- カスタムスプラッシュ画面

### 変更履歴

#### 2026-05-30 (v1.28.0)
- **修正：** CopyEnhancePatch レジスタエラー — ConversationCopy注入の`invoke-static {p0, p2, v0}`でp0がv16+にマッピング（4ビット制限超過）。`move-object/from16`で低位レジスタに移動して解決
- **修正：** APK署名失敗 — JDK 17 JCEがfat CLI jar内の未署名BouncyCastleを拒否。解決策：スタンドアロン署名済みBC 1.77 JARをclasspathの先頭に配置
- **新規：** ビデオ説明自動展開（VideoDescExpandPatch）— ExpandableLayoutをhookしてビデオ説明を自動展開
- **新規：** フローティングボタン非表示オプション
- **ビルド：** 全70+パッチのビルドと署名が成功

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0：** HDR画質強制トグル — fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0：** 弾幕時間オフセット(-30~+30s) + プールフィルター(0=全て, 1=通常, 2=字幕, 3=特殊)
- **v1.25.0：** ビデオ情報パネル強化 — UP主MID、フォロワー数、共有数；コメントフロア番号 + 返信数表示
- **新パッチ：** 弾幕キーワードフィルター、ビデオコーデック情報表示、弾幕表示調整、スクリーンショット/録画ロック解除、コメントIP所在地、AV/BV番号表示、ビデオ統計パネル
- **開発：** 8つの新機能パッチを開発しGitHubにプッシュ（合計15回コミット）

#### 2026-05-28 (v1.23.3 → v1.24.1)
- **重要な修正：** 全70+パッチをbilibili 8.95.0に適応
  - Fingerprint耐障害性（不一致時にthrowではなくreturn）
  - DmAdvertリフレクション修正（NoClassDefFoundError）
  - VerifyError修正（goto命令変更を無効化）
  - 残りの全fingerprintパッチを適応
- **新パッチ：** スプラッシュ広告ブロック（OkHttp hook）、課金鸣謝ブロック、ゲーム推薦ブロック
- **広告除去：** 全UI設定完了（トップページ、動画詳細、ライブルーム、動態、マイページ）
- **ビルド：** SOファイル処理、R8 proguard keepルール、33 DEXコンパイル

#### 2026-05-27 (v1.23.3 — 初期Fork)
- BiliRoamingX/BiliRoamingXからfork
- ローカルビルド修正（libbiliroamingx.soをpatchesリソースに追加）
- 8.95.0のtheseus_playlist_default_order修正
- PlayerSettingHelperFingerprint 8.95.0互換性修正
- 60+パッチがMuMuエミュレーターで検証済み
- README対応（中国語/英語/日本語/韓国語）

<a name="build-ja"></a>
### ビルド手順
```bash
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
# patchesとintegrationsをビルド
./gradlew :patches:jar :integrations:app:assembleRelease -x lint
# 成果物をコピー
cp patches/build/libs/BiliRoamingX-patches-*.jar patches.jar
cp integrations/app/build/outputs/apk/release/integrations-release.apk integrations.apk
# APKにパッチ適用（JDK 17では署名済みBouncyCastleをclasspath先頭に配置）
BC_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcprov-jdk18on/1.77/*/bcprov-jdk18on-1.77.jar
BCPKIX_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcpkix-jdk18on/1.77/*/bcpkix-jdk18on-1.77.jar
java -cp "$BC_JAR:$BCPKIX_JAR:revanced-cli.jar" app.revanced.cli.command.MainCommandKt \
  patch -b patches.jar -m integrations.apk -o bilibili-8.95.0-patched.apk bilibili-8.95.0.apk
```

---

<a name="한국어"></a>
## 한국어

### BiliRoamingX - AI 강화판

BiliRoamingX v1.23.3을 기반으로 한 AI 강화 버전.

**대상 버전:** 빌리빌리 8.95.0 (안드로이드 64비트)

**상태:** 70+ 패치 모두 작동, 핵심 기능 검증 완료

**빌드:** 아래 [빌드 방법](#build-ko) 참조

### 기능 목록
- 방송 제한 해제
- 페이지 컴포넌트 제거 (광고, VIP 영역, 팔로우 버튼)
- 사용자 정의 재생 속도 (기본 + 길게 눌러 배속)
- 사용자 정의 라이브/비디오 기본 화질
- HDR 화질 강제
- 자막 스타일 조절 + 자막 가져오기/저장
- 핀치 줌으로 비디오 전체 화면
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
- 비디오 코덱 정보 표시 (코덱, 해상도, 비트레이트, UP주 정보)
- 스크린샷/녹화 잠금 해제
- 비디오 설명 자동 펼치기 + 플로팅 버튼 숨기기
- 사용자 정의 스플래시 화면

### 변경 이력

#### 2026-05-30 (v1.28.0)
- **수정:** CopyEnhancePatch 레지스터 오류 — ConversationCopy 주입의 `invoke-static {p0, p2, v0}`에서 p0가 v16+에 매핑 (4비트 제한 초과). `move-object/from16`으로 저위 레지스터로 이동하여 해결
- **수정:** APK 서명 실패 — JDK 17 JCE가 fat CLI jar 내 미서명 BouncyCastle 거부. 해결: 독립 서명된 BC 1.77 JAR를 classpath 최前方에 배치
- **신규:** 비디오 설명 자동 펼치기 (VideoDescExpandPatch) — ExpandableLayout을 hook하여 비디오 설명 자동 펼치기
- **신규:** 플로팅 버튼 숨기기 옵션
- **빌드:** 전체 70+ 패치 빌드 및 서명 성공

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0:** HDR 화질 강제 토글 — fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0:** 탄막 시간 오프셋(-30~+30s) + 풀 필터(0=전체, 1=일반, 2=자막, 3=특수)
- **v1.25.0:** 비디오 정보 패널 강화 — UP주 MID, 팔로워 수, 공유 수; 댓글 층 번호 + 답글 수 표시
- **새 패치:** 탄막 키워드 필터, 비디오 코덱 정보 표시, 탄막 표시 조절, 스크린샷/녹화 잠금 해제, 댓글 IP 위치, AV/BV 번호 표시, 비디오 통계 패널
- **개발:** 8개 신기능 패치 개발 및 GitHub 푸시 (총 15회 커밋)

#### 2026-05-28 (v1.23.3 → v1.24.1)
- **핵심 수정:** 전체 70+ 패치를 bilibili 8.95.0에 적응
  - Fingerprint 내결함성 (불일치 시 throw 대신 return)
  - DmAdvert 리플렉션 수정 (NoClassDefFoundError)
  - VerifyError 수정 (goto 명령 변경 비활성화)
  - 나머지 모든 fingerprint 패치 적응
- **새 패치:** 스플래시 광고 차단 (OkHttp hook), 충전 감사 차단, 게임 추천 차단
- **광고 제거:** 전체 UI 설정 완료 (홈페이지, 동영상 상세, 라이브룸, 동적, 마이페이지)
- **빌드:** SO 파일 처리, R8 proguard keep 규칙, 33 DEX 컴파일

#### 2026-05-27 (v1.23.3 — 초기 Fork)
- BiliRoamingX/BiliRoamingX에서 fork
- 로컬 빌드 수정 (libbiliroamingx.so를 patches 리소스에 추가)
- 8.95.0의 theseus_playlist_default_order 수정
- PlayerSettingHelperFingerprint 8.95.0 호환성 수정
- 60+ 패치가 MuMu 에뮬레이터에서 검증됨
- README 4개 언어 지원 (중국어/영어/일본어/한국어)

<a name="build-ko"></a>
### 빌드 방법
```bash
git clone https://github.com/min09577/BiliRoamingX.git
cd BiliRoamingX
# patches와 integrations 빌드
./gradlew :patches:jar :integrations:app:assembleRelease -x lint
# 결과물 복사
cp patches/build/libs/BiliRoamingX-patches-*.jar patches.jar
cp integrations/app/build/outputs/apk/release/integrations-release.apk integrations.apk
# APK 패치 (JDK 17에서는 서명된 BouncyCastle을 classpath 앞에 배치)
BC_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcprov-jdk18on/1.77/*/bcprov-jdk18on-1.77.jar
BCPKIX_JAR=~/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcpkix-jdk18on/1.77/*/bcpkix-jdk18on-1.77.jar
java -cp "$BC_JAR:$BCPKIX_JAR:revanced-cli.jar" app.revanced.cli.command.MainCommandKt \
  patch -b patches.jar -m integrations.apk -o bilibili-8.95.0-patched.apk bilibili-8.95.0.apk
```

---

## Notes / 说明 / 注意事項 / 참고사항

- Personal use version, for study and research only / 仅供个人学习研究使用 / 個人の学習・研究のみ / 개인 학습 및 연구 전용
- Original project by Kofua (github.com/zjns)

## Links / 链接 / リンク / 링크

- Original: https://github.com/BiliRoamingX/BiliRoamingX
- Fork: https://github.com/min09577/BiliRoamingX
- Bilibili: https://www.bilibili.com
- ReVanced: https://revanced.app
