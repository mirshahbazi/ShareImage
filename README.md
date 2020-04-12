# ShareImage
Android Share Image and View Example (without permissions)

## Usage
```java
  //for share imageView
  ShareUtils.shareImage(context, bitmapToShare, "Your Share Message");
  //for share some view
  ShareUtils.shareImage(context, ShareUtils.getBitmapFromView(someView), "Share View Demo Message");
```

## Requirements
  
  - Add to string.xml
```java
<string name="chooser_title">Share using:</string>
<string name="file_provider">YOUR_APPLICATION_PACKAGE_NAME.fileprovider</string>
```
    
  - Create a .xml file into res/xml/your_file.xml (You might need to create the res/xml folder as well)
```java
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <cache-path name="shared_image" path="shared_image/"/>
</paths>
```

  - Add the previous created .xml file as provider to the AndroidManifest.xml
```java
    <application
    // manifest application normal code
    ...
    ...
    ...

        <provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="@string/file_provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/filepaths" />
        </provider>

    </application>
```

## Known Issues
  - Only approach you can share the text in the Facebook is using Facebook SDK, so the message will be ignored.
  
## Screen Preview
![ScreenShot](https://github.com/mirshahbazi/ShareImage/blob/master/image/device-2019-06-11-162354.png)

