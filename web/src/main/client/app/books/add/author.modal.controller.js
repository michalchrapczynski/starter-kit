angular.module('app.books').controller('AuthorModalController', function ($scope, AuthorListService) {
    'use strict';

    $scope.title = 'title';
    $scope.author= '';
   
    

    $scope.addAuthorToList = function(){
    	AuthorListService.addAuthor($scope.author);
    	return true;
    };
   
});
