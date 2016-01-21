'use strict';

angular.module('dbeb')
  .factory('Reddit',['$http',function Reddit($http) {
  var Reddit = function() {
    this.items = [];
    this.busy = false;
    this.after = '';
  };

  Reddit.prototype.nextPage = function() {
    if (this.busy) return;
    this.busy = true;

    var url = "dbeb/recommendations";//?from="+this.after;
    //var url = "http://api.reddit.com/hot?after=" + this.after + "&jsonp=JSON_CALLBACK";
    $http.get(url).then(function (response) {
      var items = response.data;
      for (var i = 0; i < items.length; i++) {
        this.items.push(items[i]);
      }
      this.after = this.items.length;
      this.busy = false;
    }.bind(this));
  };

  return Reddit;
}]);