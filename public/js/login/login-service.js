'use strict';

angular.module('dbeb')
  .factory('Auth',['$cookieStore','hello',
    'User','$rootScope', function Auth($cookieStore,hello,
      User,$rootScope) {
  
   

    return {

      
      login: function(socialNet){
        hello(socialNet).login({scope: 'email',
        force: true});


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
        //if($cookieStore.get('token')) {
         // return true;
        //}

        var session = hello.getAuthResponse(socialNet);

        var currentTime = (new Date()).getTime() / 1000;
        return session && session.access_token && session.expires > currentTime;
      },


      registerLogin : function(socialNet) {

        hello(socialNet).api('/me').then(function(r) {
            
           var query = User.query({facebookId: r.email})
           query.$promise.then(function(data) {
              if(data.length == 0)  {
                  var user = new User ({facebookId: r.email,
                    firstName: r.first_name, lastName: r.last_name, facebookIdentifier: r.id});
                  $rootScope.currentUser = user.$save();
              } else {
                $rootScope.currentUser = data[0];
              }
           });
           
            
          });
      },

      registerLogout : function(socialNet) {
        $rootScope.currentUser = null;
      },

      getUserFromSocial : function(socialNet) {
        hello(socialNet).api('/me').then(function(r) {
            
           var query = User.query({facebookId: r.email})
           query.$promise.then(function(data) {
               return data;
           });
           
          });
      }


    };
  }]);
