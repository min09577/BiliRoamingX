# BiliRoamingX-AI Fingerprint → Patch 映射表

> 自动生成于 2026-06-15 | 103 个 Fingerprint → 80+ Patch 完整映射
> 用途: B站版本更新后快速定位需维护的 Fingerprint

## 统计概览

| 指标 | 数量 |
|------|------|
| Fingerprint 文件 | 87（含多对象文件） |
| 活跃引用 | ~82 |
| 孤儿 (未引用) | ~5 |
| 使用指纹的 Patch | ~35 |
| 无指纹 Patch (直接类查找/资源) | ~45 |
| 功能域 | 17 |

## 孤儿 Fingerprint（待接入或清理）

| Fingerprint | 位置 | 状态 |
|------------|------|------|
| PlayerFullStoryWidgetFingerprint | layout/ | 未引用 |
| GeminiPlayerFullStoryWidgetFingerprint | layout/ | 未引用 |
| PlayerContainerFingerprint | video/subtitle/ | 未引用 |

---

## 完整映射表

### 播放器 (video/player) — 31 文件, 20 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| BangumiKeyDownHandlerFingerprint | BangumiKeyDownHandlerPatch | 番剧HD按键映射 |
| IjkMediaPlayerOptionsFingerprint | HwCodecPatch | 强制硬件解码 |
| MenuFuncSegmentFingerprint | RememberPlaybackSpeedPatch | 速度选择回调 |
| MenuServiceCreateSpeedFingerprint | RememberPlaybackSpeedPatch | 速度菜单回调 |
| PlaySpeedManagerImplFingerprint | DefaultPlaybackSpeedPatch | 默认速度 |
| PlaybackSpeedSettingFingerprint | RememberPlaybackSpeedPatch, OverridePlaybackSpeedPatch | 速度设置UI |
| PlayerGestureListenerFingerprint | PlayerGestureDetectorPatch | 长按禁用 |
| PlayerGestureRotateFingerprint | PlayerGestureDetectorPatch | 旋转禁用 |
| PlayerOnPreparedFingerprint | DefaultPlaybackSpeedPatch | 播放器就绪回调 |
| PlayerResizableGestureListenerFingerprint | PlayerGestureDetectorPatch | 缩放/平移手势 |
| PlayerSeekToFingerprint | PlayerPatch | 播放器seek回调 |
| PlayerSettingCreateSpeedFingerprint | RememberPlaybackSpeedPatch | 速度设置创建 |
| PlayerSpeedChooseWidgetFingerprint | RememberPlaybackSpeedPatch | 速度选择UI |
| PlayerSpeedWidgetFingerprint | OverridePlaybackSpeedPatch | 速度选项UI |
| RemoteServiceHandlerOnStartFingerprint | PlayerPatch | OnlineInfoChanged |
| ResetResizeFunctionWidgetFingerprint | PlayerGestureDetectorPatch | 重置可见性 |
| ShowPlayerToastFingerprint | PlayerToastPatch | Toast服务 |
| TripleSpeedServiceFingerprint | LongPressPlaybackSpeedPatch | 长按倍速 |
| TripleSpeedServiceUniteFingerprint | LongPressPlaybackSpeedPatch | 长按倍速(统一版) |
| UnitePlayerSetSpeedMenuFingerprint | OverridePlaybackSpeedPatch | 速度菜单覆盖 |

### 字幕 (video/subtitle) — 9 文件, 6 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| FunctionWidgetServiceFingerprint | SubtitleImportSavePatch | 字幕功能组件 |
| FunctionWidgetTokenFingerprint | SubtitleImportSavePatch | 字幕令牌 |
| PlayerSubtitleFunctionWidgetFingerprint | SubtitleImportSavePatch | 字幕UI组件 |
| RecordSelectedSubtitleFingerprint | SubtitleImportSavePatch | 字幕选择持久化 |
| SetDmViewReplyFingerprint | SubtitleImportSavePatch | 弹幕字幕回复 |
| PlayerContainerFingerprint | [孤儿] | - |

### 画质 (video/quality) — 2 文件, 1 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| PlayerSettingHelperFingerprint | VideoQualityPatch | 默认画质 |

### 主题 (misc/theme) — 9 文件, 8 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| BuiltInThemesFingerprint | CustomThemePatch | 内置主题数据 |
| BuiltInThemesFingerprint2 | CustomThemePatch | 内置主题(变体2) |
| SkinListFingerprint | CustomThemePatch | 皮肤列表 |
| ThemeClickFingerprint | CustomThemePatch | 主题选择 |
| ThemeColorsFingerprint | CustomThemePatch | 主题颜色 |
| ThemeHelperFingerprint | CustomThemePatch | 颜色数组 |
| ThemeNameFingerprint | CustomThemePatch | 主题名称 |
| ThemeProcessorFingerprint | CustomThemePatch | 主题重置 |
| WebActivityBuildUriFingerprint | CustomThemePatch | 颜色ID映射 |

### 深色模式 (misc/darkswitch) — 2 文件, 2 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| SwitchDarkModeFingerprint | DarkSwitchPatch | 深色模式切换 |
| HdNewSwitchDarkModeFingerprint | DarkSwitchPatch | HD版深色切换 |

### 侧边栏 (misc/drawer) — 4 文件, 3 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| OpenDrawerControlFingerprint | DrawerPatch | 打开抽屉 |
| CloseDrawerControlFingerprint | DrawerPatch | 关闭抽屉 |
| DrawerIsOpenFingerprint | DrawerPatch | 抽屉状态 |
| DrawerLayoutParamsFingerprint | DrawerPatch | 抽屉布局参数 |

### 复制增强 (misc/copy) — 8 文件, 7 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| BaseCommentCopyFingerprint | CopyEnhancePatch | 评论复制基类 |
| CommentCopyOldFingerprint | CopyEnhancePatch | 旧版复制 |
| CommentCopyNewFingerprint | CopyEnhancePatch | 新版复制 |
| Comment3CopyFingerprint | CopyEnhancePatch | 评论3复制 |
| Comment3DialogCopyFingerprint | CopyEnhancePatch | 评论3弹窗复制 |
| ConversationCopyFingerprint | CopyEnhancePatch | 会话复制 |
| DescCopyFingerprint | CopyEnhancePatch | 描述复制 |

### 通知 (misc/notification) — 5 文件, 4 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| LiveNotificationHelperFingerprint | MusicNotificationPatch | 直播通知 |
| NotificationStyleAbFingerprint | MusicNotificationPatch | 通知样式 |
| MediaSessionCallbackApi21Fingerprint | MusicNotificationPatch | 媒体按钮 |
| HeadsetMediaSessionCallbackFingerprint | MusicNotificationPatch | 耳机按钮 |

### 配置 (misc/config) — 4 文件, 3 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| ABSourceFingerprint | ConfigPatch | AB实验源 |
| ConfigSourceFingerprint | ConfigPatch | 配置源 |
| DDContractImplFingerprint | ConfigPatch | DDContract |

### OKHttp 网络 (misc/okhttp) — 10 文件, 9 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| HttpUrlFingerprint | OkHttpPatch | URL解析 |
| MediaTypeGetFingerprint | OkHttpPatch | 内容类型 |
| RequestFingerprint | OkHttpPatch | 请求拦截 |
| ResponseBodyFingerprint | OkHttpPatch | 响应体 |
| ResponseFingerprint | OkHttpPatch | 响应字段 |
| BufferFingerprint | OkHttpPatch | 缓冲流 |
| HeadersFingerprint | OkHttpPatch | 响应头 |
| RealCallFingerprint | OkHttpPatch | execute() |
| BodyWrapperFingerprint | OkHttpPatch | 响应体封装 |

### JSON 处理 (misc/json) — 6 文件, 4 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| JSONFingerprint | JSONPatch | FastJSON parseObject |
| PegasusParserFingerprint | PegasusPatch | 推荐流解析 |
| CardClickProcessorFingerprint | PegasusPatch | 旧版卡片点击 |
| CardClickProcessorNewFingerprint | PegasusPatch | 新版卡片点击 |

### Protobuf (misc/protobuf) — 5 文件, 2 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| MossServiceFingerprint | MossPatch | gRPC Service |
| MossMiddlewareGaiaFingerprint | MossPatch | gRPC 中间件 |

### 设置 (misc/settings) — 6 文件, 6 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| PreferenceManagerFingerprint | FixPreferenceManagerPatch | 混淆修复 |
| HdOnSettingsClickFingerprint | HdPreferenceClickFixPatch | HD设置点击 |
| HdOnWatchLaterClickFingerprint | HdPreferenceClickFixPatch | HD稍后观看 |
| HdOnImClickFingerprint | HdPreferenceClickFixPatch | HD通信 |
| HdOnContactClickFingerprint | HdPreferenceClickFixPatch | HD联系人 |
| HdOnCourseClickFingerprint | HdPreferenceClickFixPatch | HD课程 |

### 集成框架 (misc/integrations) — 16 文件, 6 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| AppCompatActivityFingerprint | DpiPatch | DPI缩放 |
| BaseMainFrameFragmentFingerprint | ConfigPatch | 主框架配置 |
| BLKVFingerprint | BLKVPatch | KV存储 |
| BrotliFingerprint | BrotliPatch | Brotli解压 |
| DanmakuFontSwitchPreferenceFingerprint | ConfigPatch | 弹幕字体 |
| RecyclerViewHolderFingerprint | NormalizeRecyclerViewPatch | RecyclerView |

### 杂项功能 (misc/other) — 64 文件, 22 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| AppendTrackingInfoFingerprint | SharePatch | 分享链接净化 |
| AttachChannelInfoFingerprint | UpgradePatch | 升级检查 |
| BLRouteBuilderFingerprint | BLRoutePatch | 路由构建 |
| CommentConfigFingerprint | ForceCommentNavigablePatch | 评论导航 |
| CommentImageSetLayoutBindViewFingerprint | SaveCommentImagePatch | 保存评论图 |
| ConfigV3PreloadFingerprint | ModulePreDownloadHookPatch | 模块预下载 |
| FavFolderOnDataSuccessFingerprint | FavFolderDialogPatch | 收藏夹(旧) |
| FavFolderOnDataSuccessNewFingerprint | FavFolderDialogPatch | 收藏夹(新) |
| LiveRoomSetFloatWindowFingerprint | ForbidLiveRoomAutoFloatPatch | 禁止悬浮窗 |
| LiveRoomTouchDispatchViewModelFingerprint | ForbidLiveRoomSlideLeftPatch | 禁止左滑 |
| LogcatAdapterFingerprint | BLogPatch | BLog hook |
| MainCommonServiceImplFingerprint | UpgradePatch | 升级检查 |
| MineBindAccountStateFingerprint | NumberFormatPatch | 数字格式化 |
| OgvSearchResultFingerprint (×3) | AppendExtraSearchTypePatch | 搜索类型 |
| OnOgvDownloadFingerprint | CacheRedirectPatch | 缓存重定向 |
| PublishToFollowingConfigFingerprint | PublishToFollowingPatch | 发布设置 |
| QualityViewHolderFingerprint | TrialQualityPatch | 试用画质 |
| RouteRequestFingerprint | BLRoutePatch | 路由拦截 |
| SectionFingerprint | AutoLikePatch | 自动点赞 |
| SetUnitedTabLayoutFingerprint | BiliLayoutAdjustPatch | 布局调整 |
| SpaceBindAccountStateFingerprint | NumberFormatPatch | 数字格式化 |
| TeenagersModeFingerprint | TeenagerModePatch | 青少年模式 |
| UidCopyFingerprint | UidCopyNoPrefixPatch | UID复制 |
| UniteDownloadMenuInvokeFingerprint | CacheRedirectPatch | 下载菜单 |
| WebConfigFingerprint | ModulePreDownloadHookPatch | Web配置 |

### 布局 (layout) — 2 文件, 2 Fingerprint

| Fingerprint | 引用 Patch | 功能 |
|------------|-----------|------|
| PlayerFullStoryWidgetFingerprint | [孤儿] | - |
| GeminiPlayerFullStoryWidgetFingerprint | [孤儿] | - |

---

## 无 Fingerprint 的 Patch（备忘）

这些 Patch 直接通过类名查找、ResourcePatch 或 AndroidManifest 操作，版本更新时反而**不受影响**：

HideFollowButtonPatch, RemoveVipSectionPatch, UpRcmdAdsPatch, IntegrationsPatch, LibBiliPatch, MainActivityPatch, ModifyModifierPatch, ProtoBufPrintPatch, AppendSignatureInfoPatch, BiliLayoutAdjustResourcesPatch, BiliLibraryPatch, BiliSettingPreferenceLayoutPatch, BlockThaiCommentPatch, ChannelModelFixPatch, CrashHandlerPatch, CustomSplashPatch, FakeNotInMultiWindowPatch, ForbidSwitchLiveRoomPatch, InjectDataProviderPatch, InjectMoreSchemePatch, KeepDataWhenUninstallPatch, LiveRoomPatch, MakeTextSelectablePatch, NoManageSpaceActivityPatch, NotificationPatch, PerAppLanguagePatch, PlayManifestCompatibilityPatch, TextFoldPatch, UnlockProtobufPatch, CommentReplyUrlPatch, HdPreferenceFragmentPatch, SettingsResourcePatch, SubtitlePatch, SubtitleImportSaveButtonPatch, AddBackDmSystemFontPatch, PlayUrlPatch
