<h1 align="center">RandomFox</h1>
<p align="center">
  <a href="https://github.com/taasonei/RandomFox/blob/master/README.ru.md">
    <img src="https://img.shields.io/badge/%D1%8F%D0%B7%D1%8B%D0%BA-%D1%80%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B9-orange" />
  </a>
  <a href="https://github.com/taasonei/RandomFox/blob/master/README.md">
    <img src="https://img.shields.io/badge/lang-en-yellow" />
  </a>
</p>
<p align="center">
Get some random pictures of fluffy foxes 🦊 Save ones you loved the most to favourites. The app supports dark theme
</p>
<p align="center">
<img src="https://github.com/taasonei/RandomFox/blob/master/demo/demo_screens.png">
</p>

## Description
The app is using [RandomFox API](https://github.com/xinitrc-dev/randomfox.ca)  
When the app is starting it shows the last viewed photo or if it's the first launch without Internet connection or the cached image was deleted it shows placeholder image  
  
You can add photos to favourites by clicking the like button or double tapping on an image:  
  
<img src=https://github.com/taasonei/RandomFox/blob/master/demo/demo_like.gif width=50% height=50%>  
 
If you undo deleting the image from favourites it will show up on the same position:  

<img src=https://github.com/taasonei/RandomFox/blob/master/demo/demo_delete_from_favourites.gif width=50% height=50%>  

## TechStack
- Clean architecture, MVVM  
- Kotlin coroutines, Kotlin Flow  
- Retrofit, Moshi  
- Coil  
- Room  
- Androix: ViewPager2, RecyclerView, ViewModel, LiveData  

## Roadmap
Future plans for enhancements and development:  
- [ ] Saving pictures
- [ ] Refactoring from LiveData to Flow  
- [x] Clean Architecture  
- [ ] Dependency Injection  

## Copyright
<a href="https://www.flaticon.com/free-icons/fox" title="fox icons">The app icon with a fox created by Freepik - Flaticon</a>
