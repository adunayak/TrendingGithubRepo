# TrendingGithubRepo

Android App to list trending github repo using clean architecture and MVVM pattern.

# Features
1. List trending github repo
2. Bookmark and Unbookmark the repo
3. List bookmarked repo

# Architecture

Clean architecture principles are applied to build the project. App contains 6 modules

<img width="641" alt="screen shot 2019-02-11 at 2 41 40 pm" src="https://user-images.githubusercontent.com/11552868/52555871-ebaf4f00-2e10-11e9-8feb-7f2f73861cd4.png">

1. Domain : This contains usecase classes to provide projects, bookmark a project and unbookmark a project and get bookmarked projects

<img width="296" alt="screen shot 2019-02-11 at 3 32 22 pm" src="https://user-images.githubusercontent.com/11552868/52556382-6167ea80-2e12-11e9-9400-f02e92bfc2b3.png">

2. Data : This module enables the orchestration of data between remote, cache and the Domain layer. 
Data layer implements the interface to provide the application data to domain layer.

<img width="780" alt="screen shot 2019-02-11 at 3 36 51 pm" src="https://user-images.githubusercontent.com/11552868/52556586-07b3f000-2e13-11e9-85cb-76c6151f4bc9.png">

3. Remote : Remote module handles all the webservice calls

4. Cache : Cache module provided database caching using room database library and provides cached data in case data is cached and not expired

5. Presentation : Contains View Models And architecture components to  presents the data to UI layer.

6. UI layer : Application UI rendered using android framework.

# Language, tools and Libraries 

1. Kotlin - JVM language
2. Android Studio - IDE to develop android app
3. Dagger : Dependency injection library
4. Android arch components - MVVM and live data
5. Retrofit using OkHttp - For Rest api calls
6. Room - ORM library for data caching
7. Junit, Mockito and espresso for unit and UI testing

# Reference

1. Caster.io clean architecture for Android
2. d.android.com - android developmer site
3. Kotlin for Android by Antonio Leiva
