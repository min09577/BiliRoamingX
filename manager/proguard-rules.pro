# Keep revanced patcher classes
-keep class app.revanced.patcher.** { *; }
-keep class app.revanced.patches.** { *; }
-keep class com.android.tools.smali.** { *; }

# Keep annotations
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses

# Keep our patch classes
-keep class app.revanced.biliroaming.manager.** { *; }
