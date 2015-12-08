'use strict';

angular.module('dbeb')
  .factory('Auth',['$cookieStore','hello',
    'User','$rootScope', function Auth($cookieStore,hello,
      User,$rootScope) {
  
   

    return {

      
      login: function(socialNet){
        hello(socialNet).login();

      },

      logout: function(socialNet){
        hello(socialNet).logout();
      },


      getCurrentAuth: function(socialNet) {
        return hello.getAuthResponse(socialNet);

      },

      /**
       * Check if a user is logged in
       *
       * @return {Boolean}
       */
      isLoggedIn: function(socialNet) {
        var session = hello.getAuthResponse(socialNet);

        var currentTime = (new Date()).getTime() / 1000;
        return session && session.access_token && session.expires > currentTime;
      },


      registerLogin : function(socialNet) {

        hello(socialNet).api('/me').then(function(r) {
            
           var query = User.query({email: r.email})
           query.$promise.then(function(data) {
              if(data.length == 0)  {
                  $rootScope.currentUser = User.save({facebookId: r.email});
              } else {
                $rootScope.currentUser = data[0];
              }
           });
           
            
          });
      },

      registerLogout : function() {
        $rootScope.currentUser = {};

      },

      getUserFromSocial : function(socialNet) {
        hello(socialNet).api('/me').then(function(r) {
            
           var query = User.query({email: r.email})
           query.$promise.then(function(data) {
               return data;
           });
           
          });
      }


    };
  }]);
