#!/usr/bin/env bash
echo no | android create avd --force -n test -t android-21 --abi armeabi-v7a --skin 480x800
emulator -avd test -no-skin -no-audio -no-window &
android-wait-for-emulator
adb shell input keyevent 82