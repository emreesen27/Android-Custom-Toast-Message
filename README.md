# Android Custom Toast Message (SnToast)
Customizable Toast Message Library For Android


 Success Toast             | Error Toast 
:-------------------------:|:-------------------------:
<img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/success.gif?raw=true" width="250" height="100" /> | <img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/error.gif?raw=true" width="250" height="100" />

 Warning Toast             |  Information Toast
:-------------------------:|:-------------------------:
<img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/warning.gif?raw=true" width="250" height="100" /> | <img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/info.gif?raw=true" width="250" height="100" />


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
  implementation 'com.github.emreesen27:Android-Custom-Toast-Message:1.0.1'
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
                // .animation(false or true) optional
                // .duration(int ms) optional
                .build();
```
#### Custom
```java
       new SnToast.Custom()
                .context(YourActivity.this)
                .backgroundColor(R.color.your_bg_color)
                .textColor(R.color.your_text_color)
                .icon(R.drawable.your_icon)
                // .animation(false or true) optional
                // .duration(int ms) optional
                .message("Custom !!!")
                .build();
```

