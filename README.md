# XCUpHideScrollView
A UpHideScrollView which is some listviews inner scrollview 
仿微博发现页面ScrollView 内嵌ListView 到顶吸顶 滚动ListView效果

## 效果演示图：

![iamge](https://raw.githubusercontent.com/jczmdeveloper/XCUpHideScrollView/master/screenshots/01.gif)


如何使用项目：
首先打开 Project root 的 build.gradle，在 repositories 节点添加上

  maven { url "https://jitpack.io" }


，之后打开想依赖这个 library 的模块，比如这里我们是 app 这个 module，在 dependencies 节点添加上 



compile 'com.github.jczmdeveloper:XCUpHideScrollView:v1.0'，


Sync 一下 Gradle，这样就可以了


