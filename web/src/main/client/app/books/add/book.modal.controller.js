angular.module('app.books').controller('BookModalController', function ($scope, $modal, bookService, AuthorListService) {
    'use strict';

    $scope.title = 'title';
    $scope.addAuthor = function() {
    	console.log($scope.aut);
				$modal.open({
					templateUrl : 'books/add/add-author-modal.html',
					controller : 'AuthorModalController',
					size : 'md'
				});
			};
    $scope.aut = AuthorListService.getAuthor();
    
    $scope.remove = function(index){
		  $scope.aut.splice(index, 1)
		};
			
	$scope.validateBookAuthors = function() {
		var y = $scope.aut.length;
		 if (y == 0) {
		        return false;
		    } else{
		    	return true;
		    }    
	}
	
	
	$scope.newBook = {};
	$scope.lib = {name: 'Uniwersytet'};
	
	$scope.addNewBook = function(){
	
		
		var newBook= {
				title: $scope.tytul, 
				authors: $scope.aut,
				libraryName: $scope.lib
		};
		console.log(newBook);
		bookService.addNewBook(newBook);
		return true;
	};
	
});
