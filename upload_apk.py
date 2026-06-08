import urllib.request, json, ssl

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

token = open("/tmp/gh_token.txt").read().strip()

# Get release ID
req = urllib.request.Request("https://api.github.com/repos/min09577/BiliRoamingX/releases/tags/v1.78.0")
req.add_header("Authorization", "token " + token)
resp = urllib.request.urlopen(req, context=ctx)
r = json.loads(resp.read())
upload_url = r["upload_url"].replace("{?name,label}", "")
print("Release ID=" + str(r["id"]))

apk_path = "integrations/app/build/outputs/apk/release/BiliRoamingX-integrations-1.78.0.apk"
with open(apk_path, "rb") as f:
    apk_data = f.read()

upload_req = urllib.request.Request(
    upload_url + "?name=BiliRoamingX-integrations-1.78.0.apk",
    data=apk_data, method="POST"
)
upload_req.add_header("Authorization", "token " + token)
upload_req.add_header("Content-Type", "application/vnd.android.package-archive")
resp2 = urllib.request.urlopen(upload_req, context=ctx)
r2 = json.loads(resp2.read())
print("APK uploaded: " + r2["name"])
