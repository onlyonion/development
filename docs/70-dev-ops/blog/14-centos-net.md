内容选自《鸟哥的linux私房菜:基础学习篇》第四版

新版的CentOS 7 开始对于网卡的编号有另外一套规则，网卡的代号与网卡的来源有关

eno1：代表有主板bios内置的网卡
ens1: 代表有主板bios内置的PCI-E网卡
enp2s0: PCI-E独立网卡
eth0：如果以上都不使用，则回到默认的网卡名

ens33则属于第二种类型，即说明你的网卡是内置的PCI-E网卡