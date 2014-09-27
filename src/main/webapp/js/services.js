'use strict';

function BlogService() {
  var BlogService = {};

  var latest = [
    {
      id: 1,
      title: 'С чего начиналась эргономика',
      previewImg: 'blog/blog1/img0.png',
      author: 'Admin',
      createDate: '23 сентября, 2014',
      cut: 'Очень часто, когда задаешь вопрос: «С чем ассоциируешь эргономику?», в ответ получаешь: «Компьютер, стол, кресло…». Для многих эргономика – в первую очередь правила, организации рабочего места за компьютером.',
      tags: ['Эргономика', 'Оператор', 'История эргономики', 'Рабочее место']
    },
    {
      id: 2,
      title: 'Безопасность детей на городских улицах',
      previewImg: 'blog/blog2/img0.png',
      author: 'Admin',
      createDate: '24 сентября, 2014',
      cut: 'Что может быть важнее, чем безопасность наших детей? Каждый год этот вопрос набирает актуальность с наступлением осени, когда день становится короче, а темнеет раньше. В темное время маленьким пешеходам угрожает еще больше опасностей.',
      tags: []
    }
  ];

  BlogService.getLatest = function () {
    return latest;
  };

  return BlogService;
}

function WorksService() {
  var WorksService = {};

  var works = [
    {
      title: 'Image title',
      categories: [],
      previewImg: 'portfolio/preview/img1.png',
      img: 'portfolio/img1.png'
    },
    {
      title: 'Image title',
      categories: ['office'],
      previewImg: 'portfolio/preview/img2.png',
      img: 'portfolio/img2.png'
    },
    {
      title: 'Image title',
      categories: [],
      previewImg: 'portfolio/preview/img3.png',
      img: 'portfolio/img3.png'
    },
    {
      title: 'Image title',
      categories: [],
      previewImg: 'portfolio/preview/img4.png',
      img: 'portfolio/img4.png'
    },
    {
      title: 'Image title',
      categories: ['workspace'],
      previewImg: 'portfolio/preview/img5.png',
      img: 'portfolio/img5.png'
    },
    {
      title: 'Image title',
      categories: [],
      previewImg: 'portfolio/preview/img6.png',
      img: 'portfolio/img6.png'
    },
    {
      title: 'Image title',
      categories: ['interface'],
      previewImg: 'portfolio/preview/img7.png',
      img: 'portfolio/img7.png'
    },
    {
      title: 'Image title',
      categories: [],
      previewImg: 'portfolio/preview/img8.png',
      img: 'portfolio/img8.png'
    },
    {
      title: 'Image title',
      categories: ['office'],
      previewImg: 'portfolio/preview/img9.png',
      img: 'portfolio/img9.png'
    },
    {
      title: 'Image title',
      categories: ['office'],
      previewImg: 'portfolio/preview/img10.png',
      img: 'portfolio/img10.png'
    },
    {
      title: 'Image title',
      categories: ['interface'],
      previewImg: 'portfolio/preview/img11.png',
      img: 'portfolio/img11.png'
    },
    {
      title: 'Image title',
      categories: ['workspace'],
      previewImg: 'portfolio/preview/img12.png',
      img: 'portfolio/img12.png'
    }
  ];

  WorksService.getWorks = function () {
    return works;
  };

  return WorksService;
}

angular.module('ErgoBureauApp.services', [])
  .factory('BlogService', BlogService)
  .factory('WorksService', WorksService)
;