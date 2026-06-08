import urllib.request, json, ssl

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

token = open("/tmp/gh_token.txt").read().strip()

upload_url = "https://uploads.github.com/repos/min09577/BiliRoamingX/releases/335887911/assets"
apk_path = "integrations/app/build/outputs/apk/release/BiliRoamingX-integrations-1.75.0.apk"
with open(apk_path, "rb") as f:
    apk_data = f.read()

upload_req = urllib.request.Request(
    upload_url + "?name=BiliRoamingX-integrations-1.75.0.apk",
    data=apk_data, method="POST"
)
upload_req.add_header("Authorization", "token " + token)
upload_req.add_header("Content-Type", "application/vnd.android.package-archive")
try:
    resp = urllib.request.urlopen(upload_req, context=ctx)
    r = json.loads(resp.read())
    print("APK uploaded: " + r["name"])
except Exception as e:
    print("Error: " + str(e))
