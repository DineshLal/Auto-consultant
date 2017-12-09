var app=angular.module('myApp', ['ngMaterial','ngRoute']);
app.controller('myCtrl', function($scope,$rootScope,$http,$mdSidenav) 
		{
	$rootScope.adding={};
	$rootScope.sideNavView=false;
	
	//changing the color on rating
		$scope.ratting = 0;
	    $scope.click3 = function (param) 
	    {
	    	$rootScope.adding.rating=param;
	        console.log('Click');
	    };
	    $scope.mouseHover3 = function (param) {
	        console.log('mouseHover(' + param + ')');
	        $scope.hoverRating3 = param;
	    };

	    $scope.mouseLeave3 = function (param) {
	        console.log('mouseLeave(' + param + ')');
	        $scope.hoverRating3 = param + '*';
	    };
	    // adding comment
	    $scope.toggleLeft=function()
	    {
	    	var config = 
			{headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				}
			};
			$http.post('/SpringMVC/comment/rating',
					JSON.stringify($scope.update), config).then(
					function(response) {
						if (response.data)
							$scope.update=response.data;
						$scope.updates=$scope.update;
					}, function(response) {
						$scope.msg = "error";
					});
	    }
	$scope.clicked=false;
	$scope.dinesh=false;
	$scope.viewdatadiv=false;
	$scope.signedup=true;

	$scope.update={};
		$scope.updateRecord=function()
		{
			var config = 
			{headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				}
			};
			$http.post('/SpringMVC/update/record',
					JSON.stringify($scope.update), config).then(
					function(response) {
						if (response.data)
							$scope.update=response.data;
						$scope.updates=$scope.update;
					}, function(response) {
						$scope.msg = "error";
					});
		}
		
	// toggle use pani pakuraen
		
		 $scope.toggleLeft = buildToggler('left');
		    $scope.toggleRight = buildToggler('right');

		    function buildToggler(componentId) {
		      return function() {
		        $mdSidenav(componentId).toggle();
		      };
		    }		    
	$scope.signupApp = {};
	$scope.signupapp={};
	$scope.redirectMainPage = function() 
	{
			$scope.usernameSubmitted=$scope.signupApp.userName;
			var config = 
			{headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				}
			};
			$http.post('/SpringMVC/signup/getdata',
					JSON.stringify($scope.signupApp), config).then(
					function(response) {
						if (response.data)
							$scope.signedup=false;
							$scope.signupApp=response.data;
						$scope.signupapp=$scope.signupApp;
							$scope.msg = "inserted";
						Username = $scope.userName;
						Firstname = $scope.firstName;
					}, function(response) {
						$scope.msg = "error";
						$scope.mailError = "Email id already exists !....";
						$scope.headers = response.headers();
					});
			
		}
	$scope.register={};
	$scope.Register={};
	$scope.registerVehicleBtn=function()
	{
		var config = 
		{headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		};
		$http.post('/SpringMVC/registering/yourVehicle',
				JSON.stringify($scope.register), config).then(
				function(response) {
					if (response.data)
						$scope.register=response.data;
					$scope.Register=$scope.register;
				}, function(response) {
					$scope.msg = "error";$
					scope.headers = response.headers();
				});
	}
	$scope.viewData = {};
	$scope.viewdata={};
	$scope.viewDataBtn = function() {
		var config = {
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		};
		$http.post('/SpringMVC/view/details', JSON.stringify($scope.viewdata),
				config).then(function(response) {
					if(response.data)
						$scope.viewdatadiv=true;
					$rootScope.sideNavView=true;
						$scope.viewdata = response.data;
						$scope.viewData=$scope.viewdata;
					
				},function(response)
				{
					$scope.msg="error";
				

		});
		
	}
	$scope.viewDataAsAdmin={};
	$scope.adminEmail={};
	$scope.lal={};
	$scope.viewdataAsAdmin={};
	$scope.viewAsAdmin=function()
	{
		var config={headers:{'Content-Type':'application/json','Accept':'application/json'}};
		$http.post('/SpringMVC/viewas/admin',JSON.stringify($scope.viewdataAsAdmin),config).then(function(response)
				{
						$scope.lal=response.data;
						$scope.adminEmail=$scope.lal;
						$scope.dinesh=true;
					
				});
	
	}
	$scope.selected = [];
	$scope.toggle = function (x, list) {
      var idx = list.indexOf(x);
      if (idx > -1) 
      {
        list.splice(idx, 1);
      }
      else {
        list.push(x);        
      }
      console.log("list="+list);
      $scope.resultDelete=list;
    };

    $scope.exists = function (item, list) {
      return list.indexOf(item) > -1;
    };
    
    // Deleting as admin goes here

    $scope.deleteAsAdmin=function()
	{
		var config = 
		{headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		};
		$http.post('/SpringMVC/deleting/recordAsAdmin',
				JSON.stringify($scope.resultDelete),config).then(
				function(response) 
				{
					$scope.dinesh=true;
					$scope.viewAsAdmin();
					
				},
				function(response) 
				{
				});
	}
	$scope.rentVehicle={};
	$scope.rentvehicle={};
	$scope.rentVehicleBtn=function()
	{
		var config={headers:{'Content-Type':'application/json','Accept':'application/json'}};
		$http.post('/SpringMVC/rent/vehicle',
				JSON.stringify($scope.rentVehicle), config).then(
				function(response) {
					if (response.data)
						$scope.rentVehicle.duration=response.data.duration;
						$scope.rentVehicle=response.data;
					$scope.rentvehicle=$scope.rentVehicle;
						$scope.msg = "inserted";
				}, function(response) {
					$scope.headers = response.headers();
				});
		//window.location="ajfs.html";
	}
	$scope.addComment=function(adding)
	{
		var config=
		{
			headers:
				{
					'Content-Type':'application/json',
					'Accept':'application/json'
				}
		}
		$http.post('/SpringMVC/add/comment',JSON.stringify($scope.adding),config).then(
				function(response)
				{
					if(response.data)
						{
							$scope.adding.feedBack=response.data.feedBack;
							if($scope.adding.feedBack=="success")
								{
									$scope.resultingFeedBack="FeedBack submitted";
									$rootScope.adding.rating = 0;
									$scope.adding.feedBack=$scope.adding.feedBack.$untouched;
								}
							else
								{
									$scope.resultingFeedBack="Already inserted";
									$rootScope.adding.rating = 0;
									$scope.adding.feedBack=$scope.adding.feedBack.$untouched;
								}
						}
					
					
				},function(response)
				{
					
				});
	}
	
	//deleting previous and adding new comment
	
	$scope.deletePreviousComment=function()
	{

		var config=
		{
			headers:
				{
					'Content-Type':'application/json',
					'Accept':'application/json'
				}
		}
		$http.post('/SpringMVC/add/newcomment',JSON.stringify($scope.adding),config).then(
				function(response)
				{
					
				},function(response)
				{
					
				});
	}
	
	$scope.loginapp={};
	$scope.loginApp={};
	$scope.loggedin=true;
	$scope.validateLogin=function()
	{
		var config=
			{
				headers:
					{
						'Content-Type':'application/json',
						'Accept':'application/json'
					}
			}
		$http.post('/SpringMVC/login/success',JSON.stringify($scope.loginApp),config).then(
				function(response)
				{
					
					console.log(JSON.stringify(response))
					if(response.data)
						{
						$scope.loginapp=response.data;
						$scope.loggedUser=response.data.userName;
						if($scope.loginapp.emailId==$scope.loginApp.emailId)
							{
							$rootScope.sideNavView=true;
							$rootScope.adding.mailId=$scope.loginApp.emailId;
							
								$scope.loggedin=false;
								$scope.mgs="Email validation success...";
							}
						else
							{
								$scope.loginApp.passWord=$scope.loginForm.$untouched;
								$scope.loginApp.emailId=$scope.loginForm.$untouched;
								$scope.mgs="Validation failed...";
							}

						}
				},function(response)
				{
				});
		
	}
	$scope.deleteRecord=function()
	{
		var config = 
		{headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		};
		$http.post('/SpringMVC/delete/record',
				JSON.stringify($scope.loginApp),config).then(
				function(response) 
				{
					$scope.loggedEmail=$scope.loginApp.emailId;
					window.location="#/"
				},
				function(response) 
				{
				});
	}
/*	$scope.selectingThis=function()
	{
		if($scope.checkingUp)
		{
			$scope.selecting=false;
		}
		else
			{
				$scope.selecting=true;
			}
	}
	$scope.selecting=true;
	$scope.checkingUp=true;
	$scope.selectAll=function()
	{
		if($scope.selectAllCB)
			{
				$scope.checkingUp=false;
				$scope.selecting=true;
			}
		else
			{
				$scope.checkingUp=true;
				$scope.selecting=false;
			}
	}*/
});

app.config(function($routeProvider)
		{
			$routeProvider
			.when("/#",
			{
				templateUrl:"../index.html"
			})
				.when("/signup",
				{
					templateUrl:"pages/signup.html"
				})
					.when("/login",
					{
						templateUrl:"pages/login.html"
					})
							.when("/viewData",
							{
								templateUrl:"pages/viewData.html"
							})
								.when("/viewasadmin",
								{
									templateUrl:"pages/viewAsAdmin.html"
								})
									.when("/buyVehicle",
									{
										templateUrl:"pages/buyVehicle.html"
									})
										.when("/registerVehicle",
										{
											templateUrl:"pages/registerVehicle.html"
										})
											.when("/rentVehicle",
											{
												templateUrl:"pages/rentVehicle.html"
											})
												.when("/tripVehicle",
												{
													templateUrl:"pages/tripVehicle.html"
												})
														.when("/updaterecord",
													{
														templateUrl:"pages/updateRecord.html"
													})	
														;
		});
app.directive('starRating', function () {
    return {
        scope: {
            rating: '=',
            maxRating: '@',
            readOnly: '@',
            click: "&",
            mouseHover: "&",
            mouseLeave: "&"
        },
        restrict: 'EA',
        template:
            "<div style='display: inline-block; margin: 0px; padding: 0px; cursor:pointer;' ng-repeat='idx in maxRatings track by $index'> \
                    <img ng-src='{{((hoverValue + _rating) <= $index) && \"http://www.codeproject.com/script/ratings/images/star-empty-lg.png\" || \"http://www.codeproject.com/script/ratings/images/star-fill-lg.png\"}}' \
                    ng-Click='isolatedClick($index + 1)' \
                    ng-mouseenter='isolatedMouseHover($index + 1)' \
                    ng-mouseleave='isolatedMouseLeave($index + 1)'></img> \
            </div>",
        compile: function (element, attrs) {
            if (!attrs.maxRating || (Number(attrs.maxRating) <= 0)) {
                attrs.maxRating = '5';
            };
        },
        controller: function ($scope, $element, $attrs) {
            $scope.maxRatings = [];

            for (var i = 1; i <= $scope.maxRating; i++) {
                $scope.maxRatings.push({});
            };

            $scope._rating = $scope.rating;
			
			$scope.isolatedClick = function (param) {
				if ($scope.readOnly == 'true') return;

				$scope.rating = $scope._rating = param;
				$scope.hoverValue = 0;
				$scope.click({
					param: param
				});
			};
			$scope.isolatedMouseHover = function (param) {
				if ($scope.readOnly == 'true') return;

				$scope._rating = 0;
				$scope.hoverValue = param;
				$scope.mouseHover({
					param: param
				});
			};

			$scope.isolatedMouseLeave = function (param) {
				if ($scope.readOnly == 'true') return;

				$scope._rating = $scope.rating;
				$scope.hoverValue = 0;
				$scope.mouseLeave({
					param: param
				});
			};
        }
    };
});

