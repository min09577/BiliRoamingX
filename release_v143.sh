#!/bin/bash
cd /c/Users/Min/BiliRoamingX
GIT_CREDS=$(printf "protocol=https\nhost=github.com\n" | git credential fill 2>/dev/null)
TOKEN=$(echo "$GIT_CREDS" | grep password | sed 's/password=//')
echo "Token length: ${#TOKEN}"
RELEASE_ID=$(curl -s -H "Authorization: token $TOKEN" "https://api.github.com/repos/min09577/BiliRoamingX/releases/tags/v1.43.0" | grep -o '"id":[0-9]*' | head -1 | cut -d: -f2)
echo "Release ID: $RELEASE_ID"
if [ -n "$RELEASE_ID" ]; then
  echo "Uploading APK..."
  curl -s -X POST -H "Authorization: token $TOKEN" -H "Content-Type: application/octet-stream" "https://uploads.github.com/repos/min09577/BiliRoamingX/releases/$RELEASE_ID/assets?name=BiliRoamingX-integrations-1.43.0.apk" --data-binary "@integrations/app/build/outputs/apk/release/BiliRoamingX-integrations-1.43.0.apk" | grep -o '"state":"[^"]*"'
  echo "Done!"
fi
