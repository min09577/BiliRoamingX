#!/usr/bin/env python3
"""修补 revanced-patcher JAR，绕过 Android 上的 decodeResources NPE.

ResourceContext.decodeResources() 调用 getPackageRenamed() 返回 null，
导致 checkNotNullExpressionValue 抛出 NPE。
本脚本定位并修改 ResourceContext.class 中的该调用。
"""

import sys, zipfile, io, struct

def patch_class(class_bytes):
    """
    搜索并修改 ResourceContext.decodeResources 方法中的
    checkNotNullExpressionValue 调用。
    
    Kotlin 字节码模式：
      dup (0x59)
      ldc_w (0x13 xx xx)      # "getPackageRenamed(...)"
      invokestatic (0xB8 xx xx) # checkNotNullExpressionValue
      invokevirtual (0xB6 xx xx) # setPackageName
    
    替换为：
      dup (0x59)               # 保留
      nop x3 (0x00 00 00)      # 替换 ldc_w
      pop (0x57) nop x2        # 替换 invokestatic,丢弃null check结果
      invokevirtual (0xB6 xx xx) # 保留
    """
    # 查找模式: dup(1B) + ldc_w(3B) + invokestatic(3B) + invokevirtual(3B)
    # 共10字节
    data = bytearray(class_bytes)
    replacements = 0
    i = 0
    while i < len(data) - 9:
        if (data[i] == 0x59 and           # dup
            data[i+1] == 0x13 and          # ldc_w
            data[i+4] == 0xB8 and          # invokestatic  
            data[i+7] == 0xB6):            # invokevirtual
            # 验证 ldc_w 引用的常量池索引（此处只做基本检查）
            # 替换 ldc_w (3B) + invokestatic (3B)
            data[i+1] = 0x00  # nop
            data[i+2] = 0x00  # nop
            data[i+3] = 0x00  # nop
            data[i+4] = 0x57  # pop (discard null check result)
            data[i+5] = 0x00  # nop
            data[i+6] = 0x00  # nop
            replacements += 1
            i += 10
        else:
            i += 1
    
    return bytes(data), replacements

def main():
    if len(sys.argv) != 2:
        print(f"Usage: {sys.argv[0]} <revanced-patcher.jar>")
        sys.exit(1)
    
    jar_path = sys.argv[1]
    target = "app/revanced/patcher/data/ResourceContext.class"
    patched = jar_path + ".patched"
    
    replacements = 0
    with zipfile.ZipFile(jar_path, 'r') as zin:
        with zipfile.ZipFile(patched, 'w', zipfile.ZIP_DEFLATED) as zout:
            for entry in zin.infolist():
                data = zin.read(entry.filename)
                if entry.filename == target:
                    print(f"Patching {entry.filename} ({len(data)} bytes)...")
                    data, count = patch_class(data)
                    replacements = count
                    print(f"  {count} replacement(s) made")
                zout.writestr(entry, data)
    
    import os
    os.replace(patched, jar_path)
    
    if replacements == 0:
        print("WARNING: No patches applied. Bytecode pattern may have changed.")
        sys.exit(2)
    else:
        print(f"Successfully patched {replacements} NPE check(s) in {target}")

if __name__ == "__main__":
    main()
