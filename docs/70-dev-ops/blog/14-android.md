Android Debug Bridge

### root后system file提示Read-only
对于Android 6.0以上的系统，需要调用disable-verity，因此整个命令序列为：
adb root
adb disable-verity 该命令的作用是去掉System的Verify标志
adb reboot
adb root

adb remount

### cacerts
/system/etc/security/cacerts
/data/misc/user/0/cacerts-added/

### adb
adb tcpip 5555

adb connect 172.16.224.79:5555

// zhenzhu