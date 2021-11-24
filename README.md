# Mlbd_assignment
Mlbd_assignment sample application shows a list of pictures by calling https://picsum.photos/v2/list? api. The application uses paging 3 library to pagination purpose, has response caching system so the user can use the app offline. User can download the images into the internal storage through the app and also can share the image link with other applications available in the device. 
 
## DefaultConfig
- minSdk 24
- targetSdk 31
- versionCode 1
- versionName "1.0"

## Clean architecture
Presentation (for UI logic, with MVVM).

## Library 
- Okhttp interceptor to cache api resposne 
- Retrofit 
- Glide
- Paging 3
- PhotoView

## Jetpack library
- Androidx 
- Navigation ui
- view binding 
- lifecycle

## Implemented Api call
Link: https://picsum.photos/v2/list?
This api will provide list of images 


## Project run and configuration
- Checkout the project from github into your local device 
- Open android studio and open the project form the the directory 
- Replace sdk.dir(sdk path) in local.properties file with your sdk file path. 
- Sync gradle, Rebuild the project and run the project 

## Build Variant
The project has 2 build variant. select the variant you want to run in the device from android studio build variant section
1. production
2. development
