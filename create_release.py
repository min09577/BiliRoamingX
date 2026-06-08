import urllib.request, json, ssl
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE
token = open("/tmp/gh_token.txt").read().strip()
data = json.dumps({"tag_name": "v1.71.0", "name": "v1.71.0", "body": "v1.71.0 - 直播自动录制、智能预加载、首页组件自定义、搜索质量过滤", "draft": False, "prerelease": False}).encode()
req = urllib.request.Request("https://api.github.com/repos/min09577/BiliRoamingX/releases", data=data, method="POST")
req.add_header("Authorization", "token " + token)
req.add_header("Content-Type", "application/json")
try:
    resp = urllib.request.urlopen(req, context=ctx)
    result = json.loads(resp.read())
    rid = result["id"]
    upload_url = result["upload_url"].replace("{?name,label}", "")
    print("Release: ID=" + str(rid))
    apk = "integrations/app/build/outputs/apk/release/BiliRoamingX-integrations-1.71.0.apk"
    with open(apk, "rb") as f: apk_data = f.read()
    ureq = urllib.request.Request(upload_url + "?name=BiliRoamingX-integrations-1.71.0.apk", data=apk_data, method="POST")
    ureq.add_header("Authorization", "token " + token)
    ureq.add_header("Content-Type", "application/vnd.android.package-archive")
    resp2 = urllib.request.urlopen(ureq, context=ctx)
    r2 = json.loads(resp2.read())
    print("APK: " + r2["name"])
except Exception as e:
    print("Error: " + str(e))
    if hasattr(e, "read"): print(e.read().decode())

