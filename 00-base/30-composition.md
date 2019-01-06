* cpu
* storage
* io
* bus(net)

### CPU，运算控制
* ALU、CU（排队逻辑、控制单元寄存器和解码器、控制存储器）、寄存器、cpu内部互连
* cpu ALU, register, interrupt, cu, inner bus

#### cpu有哪些架构
* CISC 复杂指令集计算机
  - x86 IA32，Intel Architecture 32；IA-64
  - x86-64 AMD64，EM64T（intel版本，Extended Memory 64 Technology，扩展64bit内存技术）
* RISC 精简指令集计算机
  - POWER/PowerPC架构（Power G4、G5、G6、PowerXCell）IBM
  - MIPS架构（多家厂商，包括AMD也获授权生产，龙芯也是MIPS变种）MIPS
  - UltraSPARC架构（UltraSPARC III、IV、VI、T1、T2） SUN
  - Alpha架构（现今少见，DEC被Compaq收购，Compaq又被HP收购）DEC
  - EPIC架构（Iantium、Iantium2） Intel和HP，这也就是 IA64 架构，Intel Architecture 64（Intel64位架构），有时单独列为一类而不算RISC
  - VLIW（超长指令字）架构 Transmeta（全美达），与IA64在设计思想上有某种程度上的神似之处，有时也单独列为一类而不算RISC
  - ARM架构（类似于 MIPS 也授权多家公司制造，包括Intel） Acorn，后来 Intel 还开发出 ARM的变种 XScale 架构

需要指出的是，现在的 Intel 和 AMD CISC处理器的内核其实都是 RISC内核，是在内核的外围电路中把复杂指令动态翻译成精简指令，然后送到 RISC内核中处理。严格来说，现代CPU本质上都是 RISC处理器。

[cpu有哪些架构](https://blog.csdn.net/yyyljw/article/details/79419190)

> X86架构 X86架构（The X86 architecture）是微处理器执行的计算机语言指令集，指一个intel通用计算机系列的标准编号缩写，也标识一套通用的计算机指令集合

#### 指令集架构 Instruction Set Architecture，ISA

### intel cpu 
i5 cpu-z 指令集
MMX Multi-media Extension Technology 多媒体扩展指令集技术
SSE Streaming SIMD Extensions SIMD(Single Instruction Multiple Data, 单指令多数据流)
SSE2
SSE3
SSSE3 Supplemental Streaming SIMD Extensions 3
SSE4.1
SSE4.2
EM64T
VT-x
AES Advanced Encryption Standard 高级加密标准
AVX Intel Advanced Vector Extensions Sandy Bridge和Larrabee架构下的新指令集
AVX2
FMA3

### 存储
1. 寄存器是中央处理器内的组成部份。寄存器是有限存贮容量的高速存贮部件，它们可用来暂存指令、数据和位址。在中央处理器的控制部件中，包含的寄存器有指令寄存器(IR)和程序计数器(PC)。在中央处理器的算术及逻辑部件中，包含的寄存器有累加器(ACC)。
2. 内存包含的范围非常广，一般分为只读存储器（ROM）、随机存储器（RAM）和高速缓存存储器（cache）。
3. 寄存器是CPU内部的元件，寄存器拥有非常高的读写速度，所以在寄存器之间的数据传送非常快。
4. Cache：即高速缓冲存储器，是位于CPU与主内存间的一种容量较小但速度很高的存储器。由于CPU的速度远高于主内存，CPU直接从内存中存取数据要等待一定时间周期，Cache中保存着CPU刚用过或循环使用的一部分数据，当CPU再次使用该部分数据时可从Cache中直接调用,这样就减少了CPU的等待时间,提高了系统的效率。Cache又分为一级Cache(L1 Cache)和二级Cache(L2 Cache)，L1 Cache集成在CPU内部，L2 Cache早期一般是焊在主板上,现在也都集成在CPU内部，常见的容量有256KB或512KB L2 Cache。

总结：大致来说数据是通过内存-Cache-寄存器，Cache缓存则是为了弥补CPU与内存之间运算速度的差异而设置的的部件。

[内存，寄存器和cache的区别与联系](http://www.cnblogs.com/zzdbullet/p/9484040.html)

### IO


### 总线