## 绘图
* Echarts HighCharts Canvas
* SVG  Scalable Vector Graphics 可伸缩矢量图形 XML 描述 2D 图形的语言
* Canvas 通过 JavaScript 来绘制 2D 图形 基于像素的即时模式图形系统
* [html_5_canvas_vs_svg.asp](http://www.w3school.com.cn/html5/html_5_canvas_vs_svg.asp)

## 前后端分离，好与坏
当一个系统是基于前端与后端分离这种模式来进行来发的，通常来说，这种开发模式使得整个开发的环节变长了，
这将导致整个开发团队（包括前端与后端开发人员）的开发失去了灵活性，因为一点点小功能的改动，很有可能都需要好几个人来进行协作开发。

架构的本质是什么？其实也是一种管理。通常我们所说的管理，都是指对于任务和人员的管理，而架构管的是机器和代码。
比如说，机器的部署属于运维的物理架构，SOA属于服务架构，那么，前端的架构指什么呢？

切图，即从设计稿中获取需要的素材，并不是所有前端开发都被要求切图，也不是所有前端开发都会切图，但请享受学习新知识的过程吧。
创建模版（html、jade、haml）、
脚本（javascript、coffeescript）、
样式（css、less、sass、stylus）文件，搭建基础的项目骨架。
文件（jade、coffeescript、less、sass…）编译

执行测试用例
代码检测
移除调试代码
静态资源合并与优化
静态资源通过hash计算指纹化
部署测试环境
灰度发布现网

## 响应式布局
[响应式布局](https://www.cnblogs.com/yanayana/p/7066948.html)

* 静态布局（Static Layout）即传统Web设计，网页上的所有元素的尺寸一律使用px作为单位。
* 流式布局（Liquid Layout）
流式布局（Liquid）的特点（也叫"Fluid") 是页面元素的宽度按照屏幕分辨率进行适配调整，但整体布局不变。代表作栅栏系统（网格系统）。
min-*、max-*、百分比%
* 自适应布局（Adaptive Layout）自适应布局的特点是分别为不同的屏幕分辨率定义布局，即创建多个静态布局，每个静态布局对应一个屏幕分辨率范围。
* 响应式布局（Responsive Layout）媒体查询@media 流式布局
* 弹性布局（rem/em布局）
