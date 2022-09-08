<h1 align="center">RandomFox</h1>
<p align="center">
  <a href="https://github.com/taasonei/RandomFox/blob/master/README.md">
    <img src="https://img.shields.io/badge/lang-en-yellow" />
  </a>
  <a href="https://github.com/taasonei/RandomFox/blob/master/README.ru.md">
    <img src="https://img.shields.io/badge/%D1%8F%D0%B7%D1%8B%D0%BA-%D1%80%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B9-orange" />
  </a>
</p>
<p align="center">
Получи порцию пушистых лис по клику 🦊 Самых няшных можно добавить в избранное. Приложение поддерживат темную тему
</p>
<p align="center">
<img src="https://github.com/taasonei/RandomFox/blob/master/demo/demo_screens.png">
</p>

## Описание
Приложение использует [RandomFox API](https://github.com/xinitrc-dev/randomfox.ca)  
При запуске отображается последняя просмотренная фотография. Если это первый запуск и отсутствует подключение к Интернету, либо изображение из кэша было удалено, то будет показана картинка-плейсхолдер  
  
Добавлять в избранное и удалять фотографии можно по клику на иконку лайка или двойным тапом:  
  
<img src=https://github.com/taasonei/RandomFox/blob/master/demo/demo_like.gif width=50% height=50%>  
  
При отмене удаления фотография из списка избранного возвращается на прежнюю позицию:  

<img src=https://github.com/taasonei/RandomFox/blob/master/demo/demo_delete_from_favourites.gif width=50% height=50%>  

## TechStack
- Clean architecture, MVVM  
- Kotlin coroutines, Kotlin Flow  
- Retrofit, Moshi  
- Coil  
- Room  
- Androix: ViewPager2, RecyclerView, ViewModel, LiveData  

## Roadmap
Планы по улучшению и рефакторингу  
- [ ] Сохранение фотографий
- [ ] Рефакторинг LiveData на Flow  
- [x] Clean Architecture  
- [ ] Dependency Injection  

## Copyright
<a href="https://www.flaticon.com/free-icons/fox" title="fox icons">Иконка приложения с лисой создана Freepik - Flaticon</a>
