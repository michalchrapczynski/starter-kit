angular.module('app.books').service('AuthorListService', function () {
	
	var authorList = [];

	  var addAuthor = function(newObj) {
		  authorList.push(newObj);
	  };

	  var getAuthor = function(){
	      return authorList;
	  };

	  return {
	    addAuthor: addAuthor,
	    getAuthor: getAuthor
	  };

	  
	
});
