var services = angular.module('myApp.services', ["ngResource"]);

services.factory('InitializeSvc',['RootUrlSvc',function(RootUrlSvc){

	var initialize = function(){
		console.log("InitializeSvc");
		var grantUrl = RootUrlSvc.initialize();

		        intuit.ipp.anywhere.setup({
                    grantUrl: grantUrl,
                    datasources: {
                        quickbooks: true,
                        payments: true

                    }
                });

		console.log(grantUrl);
	}

	return {
		initialize: initialize
	}
}]);

services.factory('RootUrlSvc',['$resource','$location',function($resource, $location){

	var apiRoot = function(){
		return $location.protocol() + "://" + $location.host() + ":8080";
	}

	var oauthGrantUrl = function(){
		return apiRoot() + "/request_token";
	}

	var initialize = function(){
		var requestUrl = oauthGrantUrl();
		var grantUrl = requestUrl + '?appCompanyId=' + 123145717935157;

		return grantUrl;
		console.log(grantUrl);
		        // intuit.ipp.anywhere.setup({
          //           grantUrl: grantUrl,
          //           datasources: {
          //               quickbooks: true,
          //               payments: true

          //           }
          //       });

			// Just http request
// 			$resource(apiRoot()).get(function (data) {
// 				 // http://localhost:9001 gives you all links available
// 				var links = data._links;
//                 for (var link in  links) {
//                     var href = links[link].href;
// //                    console.log("Discovered the URL for " + link + ": " + href);
//                     rootUrls[link] = href.split(/\{/)[0]; //chop off the template stuff
//                 }
//                 rootUrls['syncRequest'] = apiRoot() + "/syncrequest";  // non-discoverable
//                 rootUrls['orders'] = apiRoot() + "/orders";  // non-discoverable
//                 $rootScope.$broadcast('api.loaded');  //broadcast an event so that the CompanySvc can know to load the companies
        
//             });
	}

	return {
		initialize : initialize
	}

}]);