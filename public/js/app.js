  // Declare app level module which depends on filters, and services
angular.module('dbeb', ['ngResource','ngRoute', 'ui.bootstrap','ui.bootstrap.tpls',
                        'ui.date','ngCookies','ngHello','ngAutocomplete','ngTagsInput',
                        'ngAnimate','tagged.directives.infiniteScroll','ngMap'])
 	.config(['$routeProvider','$locationProvider','$httpProvider','helloProvider',
 	         function ($routeProvider,$locationProvider,$httpProvider,helloProvider) {
  	
 	helloProvider.init({
 	          facebook: '373314332783587'
 	});
 		
	  $routeProvider
      .when('/#/', {
        templateUrl: 'views/recommendation/recommendations.html', 
        controller: 'RecommendationController',
        authenticate: false})
      .otherwise({redirectTo: '/recommendations'});
	  
	  
	  
}])
.run(function ($rootScope,$location,$cookieStore,Auth,hello) {
      

      hello.on("auth.login", function (auth) {
          
          Auth.registerLogin(auth.network);
          $cookieStore.put('token',auth.authResponse.access_token);
          $location.path('/#/recommendations');
      });
      
      hello.on("auth.logout", function (auth) {
           Auth.registerLogout(auth.network);
           $cookieStore.remove('token');
           $location.path('/#/');
      });
      

      $rootScope.$on('$routeChangeStart', function (event, next) {
    	  event.preventDefault();

    	  if (next.authenticate && !$cookieStore.get('token')) {
              event.preventDefault();
              $location.path('/#/');
              console.log('require auth '+next);
    	  }
        
    	  
      });
});      
  