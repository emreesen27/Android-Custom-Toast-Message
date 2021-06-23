# Android Custom Toast Message (SnToast)
Customizable Toast Message Library For Android

[![](https://jitpack.io/v/emreesen27/Android-Custom-Toast-Message.svg)](https://jitpack.io/#emreesen27/Android-Custom-Toast-Message)

#### Add this in your root build.gradle file
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
#### Add this to your module's build.gradle file
```gradle
dependencies {
  implementation 'com.github.emreesen27:Android-Custom-Toast-Message:1.0.2'
}
```
## Usage
It very simple!

##### Toast Types
* SUCCESS
* ERROR
* WARNING
* INFORMATION

#### Standard
```java
 new SnToast.Standard()
      .context(YourActivity.this)
      .type(Type.SUCCESS)
      .message("Success !")
      // .iconSize(int size) Optional Default: 34dp
      // .textSize(int size) Optional Default 18sp
      // .animation(false or true) Optional Default: True
      // .duration(int ms) Optional Default: 3000ms
      .build();
```
#### Custom
```java
  new SnToast.Custom()
      .context(YourActivity.this)
      .backgroundColor(R.color.your_bg_color)
      .textColor(R.color.your_text_color)
      .icon(R.drawable.your_icon)
      // .iconSize(int size) Optional Default: 34dp
      // .textSize(int size) Optional Default 18sp
      // .animation(false or true) Optional Default: True
      // .duration(int ms) Optional Default: 3000ms
      .message("Custom !!!")
      .build();
```

### Showcase
 Success Toast             | Error Toast 
:-------------------------:|:-------------------------:
<img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/success.gif?raw=true"/> | <img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/error.gif?raw=true"/>

 Warning Toast             |  Information Toast
:-------------------------:|:-------------------------:
<img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/warning.gif?raw=true"/> | <img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/information.gif?raw=true"/>



