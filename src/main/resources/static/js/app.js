'use strict';

var app = angular.module('ErgoBureauApp', [
  'angulartics', 'angulartics.google.analytics', 'angulartics.scroll',
  'ErgoBureauApp.services', 'ErgoBureauApp.controllers', 'ErgoBureauApp.directives']);

app.config(function ($analyticsProvider) {
});