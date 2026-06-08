import urllib.request, json, ssl, time, sys

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

token = open("/tmp/gh_token.txt").read().strip()
ver = sys.argv[1]
msg = sys.argv[2]

# Create release
data = json.dumps({
    "tag_name": "v" + ver, "name": "v" + ver,
    "body": "v" + ver + " - " + msg,
    "draft": False, "prerelease": False
}).encode()

for attempt in range(5):
    try:
        req = urllib.request.Request(
            "https://api.github.com/repos/min09577/BiliRoamingX/releases",
            data=data, method="POST"
        )
        req.add_header("Authorization", "token " + token)
        req.add_header("Content-Type", "application/json")
        resp = urllib.request.urlopen(req, context=ctx, timeout=30)
        result = json.loads(resp.read())
        rid = result["id"]
        upload_url = result["upload_url"].replace("{?name,label}", "")
        print("Release: ID=" + str(rid))
        break
    except Exception as e:
        if "already_exists" in str(e):
            print("Release exists, getting ID...")
            req2 = urllib.request.Request("https://api.github.com/repos/min09577/BiliRoamingX/releases/tags/v" + ver)
            req2.add_header("Authorization", "token " + token)
            resp2 = urllib.request.urlopen(req2, context=ctx, timeout=30)
            r = json.loads(resp2.read())
            rid = r["id"]
            upload_url = r["upload_url"].replace("{?name,label}", "")
            print("Release: ID=" + str(rid))
            break
        print(f"Attempt {attempt+1} failed: {e}")
        if attempt < 4:
            time.sleep(3)
        else:
            print("All attempts failed")
            sys.exit(1)

# Upload APK
apk_path = "integrations/app/build/outputs/apk/release/BiliRoamingX-integrations-" + ver + ".apk"
with open(apk_path, "rb") as f:
    apk_data = f.read()

for attempt in range(5):
    try:
        upload_req = urllib.request.Request(
            upload_url + "?name=BiliRoamingX-integrations-" + ver + ".apk",
            data=apk_data, method="POST"
        )
        upload_req.add_header("Authorization", "token " + token)
        upload_req.add_header("Content-Type", "application/vnd.android.package-archive")
        resp2 = urllib.request.urlopen(upload_req, context=ctx, timeout=120)
        r2 = json.loads(resp2.read())
        print("APK: " + r2["name"])
        break
    except Exception as e:
        print(f"Upload attempt {attempt+1} failed: {e}")
        if attempt < 4:
            time.sleep(3)
        else:
            print("All upload attempts failed")
