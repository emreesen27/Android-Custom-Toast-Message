# Android Custom Toast Message (SnToast)
Customizable Toast Message Library For Android

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

#### Custom
```java
SnToast.custom(MainActivity.this,"Custom", R.color.backgroundColor,R.color.textColor, R.drawable.customIcon);
```
