var myApp = angular.module('myApp', []);

myApp.controller('theController', function theController($scope) {
  $scope.artists = [
  {
    "name":"John Davis",
    "shortname":"Johnny",
    "reknown":"Great Welder",
    "bio":"1. Sed ut perspiciatis unde omnis iste natusasd error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."
  },
  {
    "name":"Sal Rizzo",
    "shortname":"Sal",
    "reknown":"Great Builder",
    "bio":"Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."
  },
  {
    "name":"Mary Adams",
    "shortname":"Mary",
    "reknown":"Mayor of NY",
    "bio":"Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt"
  }
]
});

