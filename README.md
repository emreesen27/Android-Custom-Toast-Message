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
  implementation 'com.github.emreesen27:Android-Custom-Toast-Message:1.0.5'
}
```
## Usage
It very simple!

#### Builder
```java
new SnToast.Builder()
        .context(MainActivity.this)
        .type(Type.SUCCESS)
        .message("Success !")
        //.cancelable(false or true) Optional Default: False
        // .iconSize(int size) Optional Default: 34dp
        // .textSize(int size) Optional Default 18sp
        // .animation(false or true) Optional Default: True
        // .duration(int ms) Optional Default: 3000ms
        // .backgroundColor(R.color.example) Default: It is filled according to the toast type. If an assignment is made, the assigned value is used
        // .icon(R.drawable.example) Default: It is filled according to the toast type. If an assignment is made, the assigned value is used
        .build();
```

#### Change Typeface
```java
  Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.example);
  .typeface(type) // Optional Default: sans-serif-condensed
```

##### Toast Types
* SUCCESS
* ERROR
* WARNING
* INFORMATION

### Showcase
 Success Toast             | Error Toast 
:-------------------------:|:-------------------------:
<img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/success.gif?raw=true"/> | <img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/error.gif?raw=true"/>

 Warning Toast             |  Information Toast
:-------------------------:|:-------------------------:
<img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/warning.gif?raw=true"/> | <img src="https://github.com/emreesen27/Android-Custom-Toast-Message/blob/assets/information.gif?raw=true"/>



