<div align="center">

# BiliRoamingX (AI Enhanced Fork)

[![Source](https://img.shields.io/badge/Source-BiliRoamingX-blue)](https://github.com/BiliRoamingX/BiliRoamingX)
[![Version](https://img.shields.io/badge/Version-1.39.0-green)](https://github.com/min09577/BiliRoamingX)
[![AI](https://img.shields.io/badge/AI-Assisted-purple)](https://github.com/min09577/BiliRoamingX)

</div>

---

## [English](#english) | [����](#����) | [�ձ��Z](#�ձ��Z) | [???](#???)

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

### Changelog

#### 2026-06-04 (v1.39.0)
- **New:** Video bookmark — add timestamped bookmarks and notes while watching videos for quick navigation
- **Settings:** Added toggle in player settings

#### 2026-06-02 (v1.38.0)
- **New:** Live room auto sign-in �� automatically sign in when entering live room to get daily rewards
- **Settings:** Added toggle in live room settings page

#### 2026-06-02 (v1.37.0)
- **New:** Remember video playback position �� automatically save and restore playback position for each video
- **Settings:** Added toggle in player settings


#### 2026-06-02 (v1.38.0)
- **New:** Live room auto sign-in — automatically sign in when entering live room to get daily rewards
- **Settings:** Added toggle in live room settings page

#### 2026-06-02 (v1.37.0)
- **New:** Remember video playback position — automatically save and restore playback position for each video
- **Settings:** Added toggle in player settings
#### 2026-06-02 (v1.36.0)
- **New:** Default video aspect ratio �� set default video aspect ratio (Default/Fit/Fill)
- **Settings:** Added selection dialog in player settings

#### 2026-06-02 (v1.35.0)
- **New:** Comment sort by time �� default comment sorting by time (newest first)
- **Settings:** Added switch in comment filter settings

#### 2026-06-02 (v1.34.0)
- **New:** Danmaku keyword highlight �� matching danmaku change to a prominent color (7 colors available)
- **New:** Custom danmaku speed �� control danmaku scroll speed (0-10 levels)
- **New:** Danmaku display area �� customize danmaku display area percentage (0-100%)
- **Settings:** Danmaku display settings page with speed/area sliders and keyword highlight input

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0:** Custom danmaku speed & display area �� hooks DmViewReply to modify playerDanmakuSpeed and playerDanmakuDomain
- **v1.32.0:** Live room watermark removal �� recursive view tree traversal with resource ID matching
- **v1.32.0:** Live room mosaic removal �� removes overlay/mask views from live streams
- **v1.32.0:** CID display in video codec info toast
- **New:** Live room watermark removal �� recursive view tree traversal with resource ID matching
- **New:** Live room mosaic removal �� removes overlay/mask views from live streams
- **New:** CID display in video codec info toast
- **Build:** Full 70+ patches build successful, signed with BKS keystore

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0:** Video description auto-expand �� hooks ExpandableLayout via reflection to expand video description on load
- **v1.31.0:** Hide floating button �� hides mini-player floating button on video detail page
- **v1.30.0:** Loop play �� auto replay video when playback finishes (relates feed injection)
- **v1.29.0:** Block comment search keywords �� hooks ReplyMainList to clear unwanted search topics
- **v1.29.0:** Disable homepage auto-refresh �� prevents automatic content refresh on homepage

#### 2026-05-30 (v1.28.0)
- **Fix:** Register error in CopyEnhancePatch �� ConversationCopy injection used `invoke-static {p0, p2, v0}` where p0 mapped to v16+ (beyond 4-bit register limit). Fixed with `move-object/from16` to low registers
- **Fix:** APK signing failure �� JDK 17 JCE refused to authenticate unsigned BouncyCastle in fat CLI jar. Solution: put standalone signed BC 1.77 JARs on classpath before CLI
- **New:** Video description auto-expand (VideoDescExpandPatch) �� hooks ExpandableLayout to auto-expand video description
- **New:** Hide floating button option
- **Build:** Full 70+ patches build successful with signing

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0:** Force HDR quality toggle �� fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0:** Danmaku time offset (-30~+30s) + pool filter (0=all, 1=normal, 2=subtitle, 3=special)
- **v1.25.0:** Video info panel enhancement �� UP MID, follower count, share count; Comment floor number + reply count display
- **New patches:** DanmakuKeywordFilter, ShowCodecInfo, DanmakuDisplay adjustments, Screenshot/Recording unlock, Comment IP location, AV/BV number display, Video statistics panel (play/danmaku/like/coin/fav/reply)

#### 2026-05-28 (v1.23.3 �� v1.24.1)
- **Critical fixes:** All 70+ patches adapted for bilibili 8.95.0
  - Fingerprint graceful degradation (return instead of throw on mismatch)
  - DmAdvert reflection fix (NoClassDefFoundError)
  - VerifyError fix (disable goto instruction modification)
- **New patches:** SplashAd (OkHttp hook), EndpageCharge (charging page block), RelatedGames (game recommendation block)
- **Ad removal:** Full UI configuration completed (homepage, video detail, live room, dynamic, my page)

#### 2026-05-27 (v1.23.3 �� Initial Fork)
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

<a name="����"></a>
## ����

### BiliRoamingX - AI ��ǿ��

���� BiliRoamingX v1.23.3���� AI ����������ǿ��

**Ŀ��汾��** �������� 8.95.0��Android 64λ��

**״̬��** 70+ ����ȫ���������������Ĺ�������֤

### ��������
- ���������������
- �Ƴ�ҳ���������桢���Ա�������ע��ť��
- �Զ��岥���ٶȣ�Ĭ�� + �������٣�
- �Զ���ֱ��/��ƵĬ�ϻ���
- ǿ�� HDR ����
- ��Ļ��ʽ���� + ����/����
- ���������Ƶ������Ļ
- �Զ���ȡ B ��
- �������Ӿ���
- �Ƽ������š���̬����
- ��ɫģʽ��������
- �������ۺ���Ƶ��Ϣ���� IP ���ء�¥��š��ظ�����
- �����ⲿ������
- ����������Σ�OkHttp API hook��
- �����лҳ�� & ��Ϸ�Ƽ�����
- �����Կ����ʣ��Ƴ� deadline ������
- ��Ļ�ؼ��ʹ��ˣ����� + ���ı���
- ��Ļ��ʾ���ƣ�͸���ȡ��ܶȡ��ֺš�ʱ��ƫ�ơ���Ļ�ع��ˣ�
- ��Ƶ������Ϣ��ʾ�����������ֱ��ʡ����ʡ�CID��UP����Ϣ��
- ��Ƶͳ����壨����������Ļ�������ޡ�Ͷ�ҡ��ղء���������
- ��ͼ/¼������
- ��Ƶ����Զ�չ�� + ���ظ�����ť
- �Զ�����������
- �������������ؼ���
- ��ֹ��ҳ�Զ�ˢ��
- ѭ�����ţ������Զ��ز���
- **ֱ����ȥˮӡ**
- **ֱ����ȥ����������**
- **�Զ��嵯Ļ�ٶ�**
- **��Ļ��ʾ�������**
- **��Ļ�ؼ��ʸ���**

### ������־


#### 2026-06-02 (v1.38.0)
- **New:** Live room auto sign-in — automatically sign in when entering live room to get daily rewards
- **Settings:** Added toggle in live room settings page

#### 2026-06-02 (v1.37.0)
- **New:** Remember video playback position — automatically save and restore playback position for each video
- **Settings:** Added toggle in player settings
#### 2026-06-02 (v1.36.0)
- **������** Ĭ����Ƶ������� �� ������ƵĬ�ϻ��������Ĭ��/��Ӧ/��䣩
- **���ã�** ����������ҳ������ѡ��Ի���

#### 2026-06-02 (v1.35.0)
- **������** ������Ĭ�ϰ�ʱ������ �� ������������Ĭ�ϰ�ʱ����������������ǰ��
- **���ã�** ���۹�������ҳ����������

#### 2026-06-02 (v1.34.0)
- **������** ��Ļ�ؼ��ʸ��� �� ƥ��ؼ��ʵĵ�Ļ������Ŀ��ɫ��֧��7����ɫѡ��
- **������** �Զ��嵯Ļ�ٶ� �� ���Ƶ�Ļ�����ٶȣ�0-10����
- **������** ��Ļ��ʾ���� �� �Զ��嵯Ļ��ʾ����ٷֱȣ�0-100%��
- **���ã�** ��Ļ��ʾ����ҳ�������ٶ�/���򻬿�͹ؼ��ʸ��������

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0:** �Զ��嵯Ļ�ٶ� & ��ʾ���� �� hook DmViewReply �޸� playerDanmakuSpeed �� playerDanmakuDomain
- **v1.32.0:** ֱ����ȥˮӡ �� �ݹ���ͼ������ + ��Դ ID ƥ���Ƴ����Ͻ�ˮӡ
- **v1.32.0:** ֱ����ȥ������ �� �Ƴ�ֱ�����е�����/�����˸��ǲ�
- **v1.32.0:** ��Ƶ������Ϣ Toast ��ʾ CID
- **������** 70+ ����ȫ���ɹ���BKS keystore ǩ��

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0��** ��Ƶ����Զ�չ�� �� ͨ������ hook ExpandableLayout������ʱ�Զ�չ����Ƶ����
- **v1.31.0��** ���ظ�����ť �� ������Ƶ����ҳС�����Ÿ�����ť
- **v1.30.0��** ѭ������ �� ��Ƶ�����Զ��ز���ע�� relates feed��
- **v1.29.0��** �������������ؼ��� �� hook ReplyMainList �������Ҫ����������
- **v1.29.0��** ��ֹ��ҳ�Զ�ˢ�� �� ��ֹ��ҳ�����Զ�ˢ��

#### 2026-05-30 (v1.28.0)
- **�޸���** CopyEnhancePatch �Ĵ������� �� ConversationCopy ע�� `invoke-static {p0, p2, v0}` �� p0 ӳ�䵽 v16+ ���� 4-bit ���ƣ��� `move-object/from16` �����ͼĴ������
- **�޸���** APK ǩ��ʧ�� �� JDK 17 JCE �ܾ���֤ CLI ��Ƕ��δǩ�� BouncyCastle��������ǩ���� BC 1.77 JAR ���� classpath ��ǰ����
- **������** ��Ƶ����Զ�չ����VideoDescExpandPatch��
- **������** ���ظ�����ťѡ��

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0��** ǿ�� HDR ���ʿ��� �� fnval=MAX_FNVAL, fourk=true, qn=125
- **v1.26.0��** ��Ļʱ��ƫ�ƣ�-30~+30s��+ ��Ļ�ع��ˣ�0=ȫ��, 1=��ͨ, 2=��Ļ, 3=���⣩
- **v1.25.0��** ��Ƶ��Ϣ�����ǿ �� UP�� MID����˿����������������¥��� + �ظ�����ʾ
- **�²�����** ��Ļ�ؼ��ʹ��ˡ�������Ϣ��ʾ����Ļ��ʾ��������ͼ/¼������������ IP ���ء�AV/BV ����ʾ����Ƶͳ�����

#### 2026-05-28 (v1.23.3 �� v1.24.1)
- **�ؼ��޸���** 70+ ����ȫ�������������� 8.95.0
  - Fingerprint �ݴ�����ƥ��ʱ return ��� throw��
  - DmAdvert �����޸���NoClassDefFoundError��
  - VerifyError �޸������� goto ָ���޸ģ�
- **�²�����** ����������Σ�OkHttp hook���������л���Ρ���Ϸ�Ƽ�����
- **ȥ��棺** ȫ UI ������ɣ���ҳ����Ƶ���顢ֱ���䡢��̬���ҵ�ҳ��

#### 2026-05-27 (v1.23.3 �� ��ʼ Fork)
- �� BiliRoamingX/BiliRoamingX fork
- ���ع����޸������� libbiliroamingx.so �� patches ��ԴĿ¼��
- 8.95.0 theseus_playlist_default_order �޸�
- PlayerSettingHelperFingerprint 8.95.0 �������޸�
- 60+ ������ MuMu ģ��������֤ͨ��
- README ������֧��

---

<a name="�ձ��Z"></a>
## �ձ��Z

### BiliRoamingX - AI ������

BiliRoamingX v1.23.3 �٩`����AI֧Ԯ�_�k�ǉ�����

**����Щ`�����** �ӥ�ӥ� 8.95.0��Android 64�ӥåȣ�

**���Ʃ`������** 70+ �ѥå�ȫ�Ƅ����������C�ܗ��^�g��

### �C��
- ���˥�������޽��
- �ک`������ݩ`�ͥ���������ڸ桢VIP�Хʩ`���ե����`�ܥ���
- �������������ٶȣ��ǥե���� + �LѺ�����٣�
- ������������/�ӥǥ��ǥե���Ȼ��|
- HDR���|����
- ��Ļ���������{�� + ����ݩ`��/����
- �ԥ�����`��ǻ��椤�äѤ��˥ӥǥ�����
- B�������Ԅ��ܤ�ȡ��
- ���Х�󥯾���
- �������ᡢ�˚ݡ��ӑB�ե��륿���
- ���`����`�ɥ��ץ�å��屳��
- ������?�ӥǥ���󥳥ԩ`��IP���ڵء��ե������š����������ࣩ
- �ⲿ��������`���`���ӳ���
- ���ץ�å���ڸ�֥��å���OkHttp API hook��
- ����`���󥰸��x�ک`�������`�प������֥��å�
- �o��ԇ�����|��deadline�ѥ��`����ȥ��
- ����Ļ���`��`�ɥե��륿����Ҏ���F + �ƥ����ȣ�
- ����Ļ��ʾ������͸���ȡ��ܶȡ��ե���ȥ������������४�ե��åȡ��ש`��ե��륿��
- �ӥǥ����`�ǥå�����ʾ�����`�ǥå�������ȡ��ӥåȥ�`�ȡ�CID��UP�����
- �ӥǥ��yӋ�ѥͥ루������������Ļ���������͡������󡢤��ݤ���ꡢ����������
- ������`�󥷥�å�/�h�����å����
- �ӥǥ��h���Ԅ�չ�_ + �ե��`�ƥ��󥰥ܥ���Ǳ�ʾ
- �������ॹ�ץ�å��廭��
- �����ȗ������`��`�ɥ֥��å�
- �۩`��ک`���ԄӸ��o��
- ��`���������K�����Ԅӥ�ץ쥤��
- **���ť�`�०���`���`�ީ`����ȥ**
- **���ť�`��⥶������ȥ**
- **�������ॿ��Ļ�ٶ�**
- **����Ļ��ʾ���ꥢ����**
- **����Ļ���`��`�ɥϥ��饤��**

### ����Ěs


#### 2026-06-02 (v1.38.0)
- **New:** Live room auto sign-in — automatically sign in when entering live room to get daily rewards
- **Settings:** Added toggle in live room settings page

#### 2026-06-02 (v1.37.0)
- **New:** Remember video playback position — automatically save and restore playback position for each video
- **Settings:** Added toggle in player settings
#### 2026-06-02 (v1.36.0)
- **��Ҏ��** �ǥե���ȥӥǥ������ڥ��ȱ� �� �ǥե���ȤΥӥǥ������ڥ��ȱȤ��O�����ǥե����/�ե��å�/�ե��룩
- **�O����** �ץ쥤��`�O���ک`�����x�k������������׷��

#### 2026-06-02 (v1.35.0)
- **��Ҏ��** �����ȕr�g혥��`�� �� �Є��ˤ���ȥ����Ȥ��r�g혤˥��`�ȣ����¤������
- **�O����** �����ȥե��륿�O���ک`���˥����å���׷��

#### 2026-06-02 (v1.34.0)
- **��Ҏ��** ����Ļ���`��`�ɥϥ��饤�� �� �ޥå����륿��Ļ��Ŀ����ɫ�ˉ����7ɫ�x�k���ܣ�
- **��Ҏ��** �������ॿ��Ļ�ٶ� �� ����Ļ�������`���ٶ�������0-10��٥룩
- **��Ҏ��** ����Ļ��ʾ���ꥢ �� ����Ļ��ʾ���ꥢ�Υѩ`����Ʃ`���򥫥����ޥ�����0-100%��
- **�O����** ����Ļ��ʾ�O���ک`�����ٶ�/���ꥢ���饤���`�ȥ��`��`�ɥϥ��饤������׷��

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0��** �������ॿ��Ļ�ٶ� & ��ʾ���ꥢ �� DmViewReply��hook����playerDanmakuSpeed��playerDanmakuDomain����
- **v1.32.0��** ���ť�`�०���`���`�ީ`����ȥ �� �َ��ӥ�`�ĥ�`�ȥ�Щ`���� + �꥽�`��ID�ޥå���
- **v1.32.0��** ���ť�`��⥶������ȥ �� ���ť��ȥ�`��Υ��`�Щ`�쥤/�ޥ����ӥ�`���ȥ
- **v1.32.0��** �ӥǥ����`�ǥå����Toast��CID��ʾ

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0��** �ӥǥ��h���Ԅ�չ�_ �� ��ե쥯�����U�ɤ�ExpandableLayout��hook
- **v1.31.0��** �ե��`�ƥ��󥰥ܥ���Ǳ�ʾ �� �ӥǥ�Ԕ���ک`���Υߥ˥ץ�`��`�ܥ���Ǳ�ʾ
- **v1.30.0��** ��`������ �� �����K�˕r�Ԅӥ�ץ쥤��relates feedע�룩
- **v1.29.0��** �����ȗ������`��`�ɥ֥��å� �� ReplyMainList��hook���Ɨ����ȥԥå��򥯥ꥢ
- **v1.29.0��** �۩`��ک`���ԄӸ��o�� �� ����ƥ���ԄӸ��¤��ֹ

#### 2026-05-30 (v1.28.0)
- **������** CopyEnhancePatch�쥸��������` �� `invoke-static {p0, p2, v0}`��p0��v16+�˥ޥåԥ󥰣�4�ӥå����޳��^����`move-object/from16`�ǵ�λ�쥸�������ƄӤ��ƽ�Q
- **������** APK����ʧ�� �� JDK 17 JCE��CLI���i��δ����BouncyCastle��ܷ񡣶��������g��BC 1.77 JAR��classpath��ǰ�������ä��ƽ�Q

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0��** HDR���|���ƥȥ���
- **v1.26.0��** ����Ļ�����४�ե��åȣ�-30~+30s��+ �ש`��ե��륿
- **v1.25.0��** �ӥǥ����ѥͥ돊�� �� �����ȥե������� + ��������ʾ

#### 2026-05-28 (v1.23.3 �� v1.24.1)
- **��Ҫ������** 70+�ѥå���ӥ�ӥ� 8.95.0����ȫ�m��
- **�¥ѥå���** ���ץ�å���ڸ�֥��å�������`���󥰸��x�֥��å������`�प������֥��å�

#### 2026-05-27 (v1.23.3 �� ����Fork)
- BiliRoamingX/BiliRoamingX����ե��`��
- 60+�ѥå���MuMu���ߥ��`���`�Ǘ��^�g��

---

<a name="???"></a>
## ???

### BiliRoamingX - AI ???

BiliRoamingX v1.23.3 ??, AI ?? ??? ??.

**?? ??:** ???? 8.95.0 (Android 64??)

**??:** 70+ ?? ?? ?? ??, ?? ?? ?? ??

### ??
- ????? ?? ?? ??
- ??? ???? ?? (??, VIP ??, ??? ??)
- ??? ?? ?? ?? (?? + ?? ?? ??)
- ??? ?? ??/??? ?? ??
- HDR ?? ??
- ?? ??? ?? + ????/??
- ?? ??? ?? ?? ??? ??
- B?? ?? ??
- ?? ?? ??
- ??, ??, ?? ???
- ?? ?? ???? ??
- ?? ? ??? ?? ?? (IP ??, ? ??, ?? ? ??)
- ?? ???? ??
- ???? ?? ?? (OkHttp API hook)
- ?? ?? ??? & ?? ?? ??
- ?? ?? ?? (deadline ???? ??)
- ?? ??? ?? (??? + ???)
- ?? ?? ?? (???, ??, ?? ??, ?? ???, ? ??)
- ??? ?? ?? ?? (??, ???, ?????, CID, UP? ??)
- ??? ?? ?? (???, ???, ???, ??, ????, ???)
- ????/?? ?? ??
- ??? ?? ?? ??? + ??? ?? ???
- ??? ?? ???? ??
- ?? ?? ??? ??
- ???? ?? ???? ????
- ?? ?? (?? ? ?? ????)
- **?? ???? ??**
- **?? ???? ??**
- **??? ?? ?? ??**
- **?? ?? ?? ??**
- **?? ??? ?????**

### ?? ??


#### 2026-06-02 (v1.38.0)
- **New:** Live room auto sign-in — automatically sign in when entering live room to get daily rewards
- **Settings:** Added toggle in live room settings page

#### 2026-06-02 (v1.37.0)
- **New:** Remember video playback position — automatically save and restore playback position for each video
- **Settings:** Added toggle in player settings
#### 2026-06-02 (v1.36.0)
- **??:** ?? ??? ?? ?? �� ?? ??? ?? ?? ?? (??/??/???)
- **??:** ???? ?? ???? ?? ?? ?? ??

#### 2026-06-02 (v1.35.0)
- **??:** ?? ??? ?? �� ??? ? ??? ????? ?? (??? ??)
- **??:** ?? ?? ?? ???? ??? ??

#### 2026-06-02 (v1.34.0)
- **??:** ?? ??? ????? �� ???? ??? ?? ?? ???? ?? (7?? ?? ?? ??)
- **??:** ??? ?? ?? ?? �� ?? ??? ?? ?? (0-10 ??)
- **??:** ?? ?? ?? �� ?? ?? ?? ?? ??? ?? (0-100%)
- **??:** ?? ?? ?? ???? ??/?? ???? ? ??? ????? ?? ??

#### 2026-06-02 (v1.32.0 ~ v1.33.0)
- **v1.33.0:** ??? ?? ?? ?? & ?? ?? �� DmViewReply? hook?? playerDanmakuSpeed? playerDanmakuDomain ??
- **v1.32.0:** ?? ???? ?? �� ?? ? ?? ?? + ???ID ???? ???? ??
- **v1.32.0:** ?? ???? ?? �� ?? ???? ????/??? ? ??
- **v1.32.0:** ??? ?? ?? Toast? CID ??
- **??:** 70+ ?? ?? ??, BKS keystore ??

#### 2026-06-01 (v1.29.0 ~ v1.31.0)
- **v1.31.0:** ??? ?? ?? ??? �� ?????? ExpandableLayout hook
- **v1.31.0:** ??? ?? ??? �� ??? ?? ??? ?????? ?? ??
- **v1.30.0:** ?? ?? �� ?? ?? ? ?? ???? (relates feed ??)
- **v1.29.0:** ?? ?? ??? ?? �� ReplyMainList? hook?? ?? ?? ???
- **v1.29.0:** ???? ?? ???? ???? �� ??? ?? ???? ??

#### 2026-05-30 (v1.28.0)
- **??:** CopyEnhancePatch ???? ?? �� `invoke-static {p0, p2, v0}`? p0? v16+? ?? (4?? ?? ??). `move-object/from16`?? ??
- **??:** APK ?? ?? �� JDK 17 JCE? ??? BouncyCastle ??. ??? BC 1.77 JAR? classpathǰ��? ???? ??

#### 2026-05-29 (v1.25.0 ~ v1.27.0)
- **v1.27.0:** HDR ?? ?? ??
- **v1.26.0:** ?? ?? ??? (-30~+30s) + ? ??
- **v1.25.0:** ??? ?? ?? ?? �� ?? ? ?? + ?? ? ??

#### 2026-05-28 (v1.23.3 �� v1.24.1)
- **?? ??:** ?? 70+ ??? bilibili 8.95.0? ??
- **? ??:** ???? ?? ??, ?? ?? ??, ?? ?? ??

#### 2026-05-27 (v1.23.3 �� ?? Fork)
- BiliRoamingX/BiliRoamingX?? ??
- 60+ ??? MuMu ??????? ???

---

## Notes / ˵�� / ע����� / ????

- Personal use version, for study and research only / ��������ѧϰ�о�ʹ�� / ���ˤ�ѧ��?�о��Τ� / ?? ?? ? ?? ??
- Original project by Kofua (github.com/zjns)

## Links / ���� / ��� / ??

- Original: https://github.com/BiliRoamingX/BiliRoamingX
- Fork: https://github.com/min09577/BiliRoamingX
- Releases: https://github.com/min09577/BiliRoamingX/releases
- Bilibili: https://www.bilibili.com
- ReVanced: https://revanced.app

