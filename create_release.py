import urllib.request, json, ssl

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

token = open("/tmp/gh_token.txt").read().strip()

data = json.dumps({
    "tag_name": "v1.73.0",
    "name": "v1.73.0",
    "body": "v1.73.0 - 断点续播增强、直播弹幕速度、首页缓存控制、搜索自动纠错",
    "draft": False,
    "prerelease": False
}).encode()

req = urllib.request.Request(
    "https://api.github.com/repos/min09577/BiliRoamingX/releases",
    data=data, method="POST"
)
req.add_header("Authorization", "token " + token)
req.add_header("Content-Type", "application/json")
try:
    resp = urllib.request.urlopen(req, context=ctx)
    result = json.loads(resp.read())
    release_id = result["id"]
    upload_url = result["upload_url"].replace("{?name,label}", "")
    print("Release created: ID=" + str(release_id))

    apk_path = "integrations/app/build/outputs/apk/release/BiliRoamingX-integrations-1.73.0.apk"
    with open(apk_path, "rb") as f:
        apk_data = f.read()

    upload_req = urllib.request.Request(
        upload_url + "?name=BiliRoamingX-integrations-1.73.0.apk",
        data=apk_data, method="POST"
    )
    upload_req.add_header("Authorization", "token " + token)
    upload_req.add_header("Content-Type", "application/vnd.android.package-archive")
    resp2 = urllib.request.urlopen(upload_req, context=ctx)
    r2 = json.loads(resp2.read())
    print("APK uploaded: " + r2["name"])
except Exception as e:
    print("Error: " + str(e))
    if hasattr(e, "read"):
        print(e.read().decode())
