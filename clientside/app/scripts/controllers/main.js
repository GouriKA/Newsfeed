/*function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
      $('#blah')
        .attr('src', e.target.result)
        .width(100)
        .height(150);
    };
    reader.readAsDataURL(input.files[0]);
  }
}*/

'use strict';

angular.module('newsfeed3App')
  .controller('MainCtrl',function($scope, $http) {

  $scope.loginInfo = "";
  $scope.pageNo = 0;

  $scope.signInShow = false;
  $scope.signUpShow = false;
  $scope.homeShow = true;
  $scope.signInSuccess = false;
  $scope.postOfAUserShow = false;
  $scope.editUserPost = false;
  $scope.deleteConfirmation = false;
  $scope.newPostShow = false;
  $scope.noUserMessagesShow = false;
  $scope.allUsersShow = false;
  $scope.userInfoShow = false;



  $scope.signInClicked = function() {
    $scope.signInShow = true;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;

    $scope.passwordMatchError = "";
    $scope.errorMessage = "";
  };

  $scope.newPostClicked = function() {
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = true;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;
  };

  $scope.signUpClicked = function() {
    $scope.signInShow = false;
    $scope.signUpShow = true;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;

    $scope.errorMessage1 = "";
    $scope.passwordMatchError = "";
  };

  $scope.editPostsClicked = function() {
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = true;
    $scope.editUserPost = false;
    $scope.getPostsOfUser();
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;
  };

  $scope.homePage = function() {
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = true;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.getAllPosts();
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;
  };

  $scope.editAPostClicked = function(message,timeStamp,id) {
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = true;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;

    $scope.textMessage = message;
    $scope.msgTimeStamp = timeStamp;
    $scope.textId = id;
  };

  $scope.deleteClicked = function(message,timeStamp,id) {
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = true;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;

    $scope.textMessage = message;
    $scope.msgTimeStamp = timeStamp;
    $scope.textId = id;
  }

  $scope.cancelLogin = function(){
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = true;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;

    $scope.username = "";
    $scope.password = "";
    $scope.usernameSignUp = "";
    $scope.firstName = "";
    $scope.lastName = "";
    $scope.age = "";
    $scope.gender = "";
    $scope.password0 = "";
    $scope.password1 = "";
  }

  $scope.cancelOperationOnText = function(){
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = true;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;

    $scope.newText = "";
    $scope.updatedText = "";
  }

  $scope.noUserMessagesFound = function(){
    $scope.signInShow = false;
    scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = true;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;
  }

  $scope.getAllUsersClicked = function(){
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = true;
    $scope.userInfoShow = false;

    $scope.getAllUsers();
  }

  $scope.findAFriendClicked = function(findByFirstName){
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = false;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = true;

    $scope.firstNameFind = findByFirstName.charAt(0).toUpperCase() + findByFirstName.slice(1);
    $scope.getUsersByName();
  }

  $scope.logout = function() {
    $scope.loginInfo = "";
    $scope.getAllPosts();
    $scope.signInShow = false;
    $scope.signUpShow = false;
    $scope.homeShow = true;
    $scope.signInSuccess = false;
    $scope.postOfAUserShow = false;
    $scope.editUserPost = false;
    $scope.deleteConfirmation = false;
    $scope.newPostShow = false;
    $scope.noUserMessagesShow = false;
    $scope.allUsersShow = false;
    $scope.userInfoShow = false;
  }

  $scope.getAllPosts = function() {
    $http.get('http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/user_post/')
      .success(function(data, status) {
        $scope.httpStatus = status;
        $scope.errorStatus=false;
        $scope.AllTextMessages=data;
      })
      .error(function(data, status) {
        $scope.httpStatus = status;
        $scope.httpData = data;
        $scope.errorStatus=true;
        $scope.messageText=data.error.code+ " "+ data.error.message;
      });
  };

   $scope.readURL = function(input) {
      if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
          $('#blah')
            .attr('src', e.target.result)
            .width(150)
            .height(200);
        };

        reader.readAsDataURL(input.files[0]);
      }
    }

  $scope.login = function() {
    var req = {
      method: 'PUT',
      url: "http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/login",
      headers: {
         'Content-Type': "application/json"
      },
      data: {
        "username": $scope.username,
        "password": $scope.password
      }
    }
    $http(req)
      .success(function(data, status) {
        $scope.username = "";
        $scope.password = "";
        $scope.httpStatusLogin = status;
        $scope.loginInfo = data;
        $scope.errorStatusLogin=false;
        $scope.logInStatus = true;
        $scope.signInSuccess = true;
        $scope.signInShow = false;
        $scope.getAllPosts();
      })
      .error(function(data, status) {
        $scope.httpStatusLogin = status;
        $scope.errorStatusLogin=true;
        $scope.logInStatus = false;
        $scope.errorMessage = data;
      });
  };

  $scope.getAllUsers = function() {
    var req = {
      method: 'GET',
      url: "http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/user",
      headers: {
        'Content-Type': "application/json",
        'NewsFeed-AuthToken' : $scope.loginInfo.authToken
      }
    }
    $http(req)
      .success(function(data, status) {
         $scope.httpStatusLogin = status;
         $scope.allUsers = data;
      })
      .error(function(data, status) {
         $scope.getAllUserStatus = status;
         $scope.errorGetAllUsers=true;
         $scope.errorMessageGetAllUsers = data;
      });
  };

  $scope.getUsersByName = function() {
    var req = {
      method: 'PUT',
      url: "http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/user/findByName",
      headers: {
        'Content-Type': "application/json",
        'NewsFeed-AuthToken' : $scope.loginInfo.authToken
      },
      data: {
        "firstName":$scope.firstNameFind
      }
    }
    $http(req)
      .success(function(data, status) {
        $scope.httpStatusLogin = status;
        $scope.usersByName = data;
        $scope.findByFirstName = "";
      })
      .error(function(data, status) {
        $scope.getAllUserStatus = status;
        $scope.errorGetAllUsers=true;
        $scope.errorMessageGetAllUsers = data;
      });
  };

  $scope.getPostsOfUser = function() {
    var req = {
      method: 'GET',
      url:"http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/user_post/" + $scope.loginInfo.userId,
      headers: {
        'NewsFeed-AuthToken' : $scope.loginInfo.authToken,
        'Content-Type' : "application/json"
      }
    }
    $http(req)
      .success(function(data, status) {
        $scope.httpStatusPostsOfUser = status;
        $scope.errorStatusPostsOfUser=false;
        $scope.messagesOfUser = data;
        /*$scope.userMsgCount = messagesOfUser.texts.length;
        if(userMsgCount == 0){
          $scope.noUserMessagesFound();
        }*/
      })
      .error(function(data, status) {
        $scope.httpStatusPostsOfUser = status;
        $scope.errorMessage = data;
        $scope.errorStatusPostsOfUser=true;
        $scope.messageTextPostsOfUser="Error";
      });
  };

  $scope.newUser = function() {
    $scope.passwordMatchError = "";
    $scope.loginInfo = "";
    if($scope.password0 === $scope.password1) {
      var req = {
        method: 'POST',
        url: "http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/user",
        headers: {
           'Content-Type': "application/json"
        },
        data: {
          "username": $scope.usernameSignUp,
          "firstName" : $scope.firstName,
          "lastName" : $scope.lastName,
          "dateOfBirth" : $scope.dob,
          "gender" : $scope.gender,
          "tagLine" : $scope.tagLine,
          "password": $scope.password1
        }
      };
      $http(req)
        .success(function(data, status) {
          $scope.usernameSignUp = "";
          $scope.firstName = "";
          $scope.lastName = "";
          $scope.age = "";
          $scope.gender = "";
          $scope.password0 = "";
          $scope.password1 = "";
          $scope.httpStatusUserSignUp = status;
          $scope.errorStatusUserSignUp = false;
          $scope.loginInfo = data;
          $scope.username = $scope.usernameSignUp;
          $scope.signInSuccess = true;
          $scope.getPostsOfUser();
          $scope.signUpShow = false;
        })
        .error(function(data, status) {
          $scope.httpStatusLogin = status;
          $scope.errorStatusUserSignUp = true;
          $scope.logInStatus = false;
          $scope.errorMessage1 = data;
        });
    }
    else {
      $scope.httpStatusUserSignUp = status;
      $scope.errorMessage1 = "";
      $scope.passwordMatchError = "Please enter the right password";
      $scope.errorStatusUserSignUp = false;
    }
  };

  $scope.newPost = function() {
    var req = {
      method: 'POST',
      url: "http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/user_post/"+ $scope.loginInfo.userId,
      headers :{
        'NewsFeed-AuthToken': $scope.loginInfo.authToken,
        'Content-Type': "application/json"
      },
      data: {
        "msg": $scope.newText,
        "firstName" : $scope.loginInfo.firstName,
        "lastName" : $scope.loginInfo.lastName
      }
    }
    $http(req)
      .success(function (data, status) {
        $scope.newText = "";
        $scope.httpStatusUserSignUp = status;
        $scope.UserSignUp = "Posted";
        $scope.errorStatusUserSignUp = false;
        $scope.getPostsOfUser();
        $scope.postOfAUserShow = true;
        $scope.newPostShow = false;
      })
      .error(function (data, status) {
        $scope.httpStatusUserSignUp = status;
        $scope.errorMessage2 = data;
        $scope.UserSignUp = "Not Posted";
        $scope.errorStatusUserSignUp = true;
      });
  };

  $scope.editPost = function(textId1) {
    var req = {
      method: 'POST',
      url: "http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/user_post/update/"+ $scope.loginInfo.userId,
      headers :{
        'NewsFeed-AuthToken': $scope.loginInfo.authToken,
        'Content-Type': "application/json"
      },
      data: { "textId" : textId1 , "msg" : $scope.updatedText }
    }
    $http(req)
      .success(function (data, status) {
        $scope.httpStatusUserSignUp = status;
        $scope.errorStatusUserSignUp = false;
        $scope.editUserPost = false;
        $scope.getPostsOfUser();
        $scope.postOfAUserShow = true;
      })
      .error(function (data, status) {
        $scope.textIdx = textId1;
        $scope.xxx = $scope.updatedText;
        $scope.httpStatusEditPost = status;
        $scope.errorStatusEditPost = true;
        $scope.updatapostdata = data;
      });
  };

  $scope.deletePost = function(textId) {
    var req = {
      method: 'DELETE',
      url: 'http://localhost:8080/newsfeed-0.0.1-SNAPSHOT/newsfeed/user_post/' + $scope.loginInfo.userId + "/" + textId,
      headers :
      {
        'NewsFeed-AuthToken': $scope.loginInfo.authToken,
        'Content-Type': "application/json"
      }
    }
    $http(req)
      .success(function(data, status) {
        $scope.httpStatusDelete = status;
        $scope.errorStatusDelete=false;
        $scope.getPostsOfUser();
        $scope.postOfAUserShow = true;
        $scope.deleteConfirmation = false;
      })
      .error(function(data, status) {
        $scope.httpStatusDelete = status;
        $scope.deleteMessage = data;
        $scope.errorStatusDelete=true;
      });
  };
});
