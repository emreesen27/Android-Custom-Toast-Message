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
  implementation 'com.github.emreesen27:Android-Custom-Toast-Message:1.0.0'
}
```

## Usage
It very simple!

#### Standart
```java
SnToast.standard(YourActivity.this,"Success !", SnToast.ToastType.Success);
```
##### Toast Types
* Success
* Error
* Warning
* Info

#### You can edit the animation state and duration value.
```java
SnToast.standard(YourActivity.this,"Success !", SnToast.ToastType.Success, 4000, false);
```
#### Custom
```java
SnToast.custom(MainActivity.this,"Custom", R.color.backgroundColor,R.color.textColor, R.drawable.customIcon);
```

