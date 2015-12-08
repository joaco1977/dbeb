  // Declare app level module which depends on filters, and services
angular.module('dbeb', ['ngResource','ngRoute', 'ui.bootstrap', 
                        'ui.date','ngCookies','ngHello'])
 	.config(['$routeProvider','$locationProvider','$httpProvider','helloProvider',
 	         function ($routeProvider,$locationProvider,$httpProvider,helloProvider) {
  	
 	helloProvider.init({
 	          facebook: '849164405198575'
 	});
 		
	  $routeProvider
      .when('/', {
        templateUrl: 'views/home/home.html', 
        controller: 'HomeController'})
      .otherwise({redirectTo: '/'});
	  
	  
	  
}])
.run(function ($rootScope,$location,$cookieStore,Auth,hello,User) {
      
      
      var isLoggedIn = $rootScope.currentUser;

      hello.on("auth.login", function (auth) {
          
          Auth.registerLogin(auth.network);
          $cookieStore.put('token',auth.authResponse.access_token);
          $location.path('/#/recommendations');
      });
      
      hello.on("auth.logout", function (auth) {
           Auth.registerLogout(auth.network);
           $cookieStore.remove('token');
           $location.path('/#/recommendations');
      });
      

      $rootScope.$on('$routeChangeStart', function (event, next) {
    	  event.preventDefault();

    	  if (next.authenticate && isLoggedIn) {
              event.preventDefault();
              $location.path('/');
              console.log('require auth '+next);
    	  }
    	  
      });
});      
  