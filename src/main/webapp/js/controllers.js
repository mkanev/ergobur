'use strict';

function WorksController($scope, WorksService) {
  $scope.works = WorksService.getWorks();
}

function BlogController($scope, BlogService) {
  $scope.latest = BlogService.getLatest();
}

function ContactsController($scope, $http, $log, $timeout) {
  $scope.submit = function (form) {
    $scope.submitted = true;
    if (form.$invalid) {
      return;
    }
    var request = {
      'name': $scope.name,
      'email': $scope.email,
      'message': $scope.message
    };
    $http.post('/contact', request)
      .success(function (data, status, headers, config) {
                 if (status == 200) {
                   $scope.name = null;
                   $scope.email = null;
                   $scope.message = null;
                   $scope.messages = 'Спасибо! Ваше сообщение отправлено.';
                   $scope.submitted = false;
                 } else {
                   $scope.messages = 'Ошибка обработки запроса. Мы свяжемся с вами позже.';
                   $log.error(data);
                 }
               })
      .error(function (data, status, headers, config) {
               $scope.progress = data;
               $scope.messages = 'Невозможно связаться с сервером. Пожалуйста, попробуйте позже.';
               $log.error(data);
             })
      .finally(function () {
                 // Hide status messages after three seconds.
                 $timeout(function () {
                   $scope.messages = null;
                 }, 3000);
               });
  };
}

angular.module('ErgoBureauApp.controllers', [])
  .controller('WorksController', WorksController)
  .controller('BlogController', BlogController)
  .controller('ContactsController', ContactsController)
;
