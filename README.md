# &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;MyTweetApp Vol.2

### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&emsp;&emsp;&emsp;&emsp;Main Features

    • Enables User Signup / Registration / Login
    • Enables user to post 140 character tweets
    • Tweets are persisted and will be reloaded when a User logs in
    • Supports viewing all tweets Users have posted
    • Allows a user to edit account settings (email, password, and other details)
***
#### &emsp;&emsp;&emsp;Welcome Screen&emsp;&emsp;&emsp;&emsp;&nbsp;Sign Screen up&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Login Screen&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Tweet Screen

      • Single Activity      • Single Activity         • Single Activity      • Single Activity
      • Welcome message      • Four text views         • One button           • Three buttons
      • Two buttons:                1. First name      • Two text views              1. Tweet
            1. Sign up              2. Last name              1. Email               2. Contact
            2. Login:               3. Email                  2. Password            3. Email Tweet
                                    4. Password                                • Two text views:
                                                                                     1. "140 chars"
                                                                                     2. Date
                                                                               • Edit text view

  ***

#### Login Features

                       USERS ACCOUNT LOGIN DETAILS
                  =====================================
          login: homer@simpson.com   |     login: bart@simpson.com
          password: secret           |     password: secret
          ---------------------------------------------------------
          login: marge@simpson.com   |     login: lisa@simpson.com
          password: secret           |     password: secret



                       ADMINS ACCOUNT LOGIN DETAILS
                ========================================
                login: tim@tom.com   |  password: secret

### Deployed Links
***

##### _1. Heroku_
   _<a href ="https://twitter-web.herokuapp.com/"> My Twitter Web App</a>_
***

##### _2. Glitch_
   _<a href="https://twitter-tweet.glitch.me/">My Twitter Web App</a>_
 ***

##### _3. Amazon - (with m-lab mongo database)_
   _<a href="http://ec2-18-216-44-207.us-east-2.compute.amazonaws.com:4000/">My Twitter Web App</a>_
***
##### _4. Zeit - My Twitter Web App - Releases_

   _<a href="https://twitter-juhluhnibg.now.sh/">V1.0 - Introduction of Tweeter Icons</a>_

   _<a href="https://twitter-mmkillbgcm.now.sh/">V2.0 - Ability to add Images when creating a tweet</a>_

   _<a href="https://twitter-wsrjvrogqf.now.sh/">V3.0 - Ability to follow / be followed & unfollow Users</a>_

   _<a href="https://twitter-mflrtjymcy.now.sh/">V4.0 - Ability to view Users Posts</a>_

   _<a href="https://twitter-web-odzqlhnras.now.sh/">V5.0 - Fully connected through API's to Mobile App</a>_

***
##### _5. Aurelia - SPA_
_<a href="https://twitter-web-aurelia-experiment-yteqvhomxr.now.sh/#/login"/>My Twitter Web Aurelia App</a>_
#### MyTweet
_Entering text causes the number of characters to add up to 140_

_Pressing “Tweet” generates ‘Message Sent’ toast_

_Date is current date / time_

_Navigate back from MyTweet via “back”button or action bar back-option" to Timeline_
***
#### Timeline
_Tweets appear in list in timeline_

_Selecting one brings up MyTweet activity with the text of the Tweet_

_Timeline has an action bar to navigate to MyTweet page_

_It also has a drop down menu which has:_

                            • Settings
                            • Users
                            • Logout
***
#### MyTweet + Timeline (sample error handling)
_Ensures that empty tweets do not appear in timeline_

_Ensures tweet substring in timeline does not exceed single line_

* __NOTE:__ Currently unable to delete tweets from timeline

***
#### Timeline Retrofit
_Timeline is saved to mlabs, so when app is launched, the tweets are displayed (if there are any)_
***
#### Contact List Access
_Pressing “Contact” allows the user to select a contact from their contact list_

_Their email is then displayed on the “Contact” button (Contact: homer@simpson.com)_
***
#### Email Access
_Pressing “Send Tweet via Email” displays email application, which will contain email and tweet text_
***
#### Settings Support
_“Settings” menu option brings up the settings screen_

_The values entered will be saved and restored when the application is relaunched_

_If a password is left blank then the original password will be kept so not to cause err with hashing & salting_
***

#### New Features
* _Users and Tweets retrieved from Web App database through API_
* _Users & Tweets are stored in the database of the Web App where they can be viewed when created_
* _Passwords are hashed & salted through Web App API which allows login for same user on the Web App_
* _Passwords are checked through the Web App API when logging into the Android App_

* __NOTE:__ _Bug when an invalid password is entered on login_

***
# &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;MyTweetApp Screenshots


<img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1509763866/splash_jraolg.jpg"/> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1507476493/welcome_tyc5dj.png" />

<img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1515326036/signup_ssxgev.png" /> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1515326035/login_wod4pv.png"/>

<img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1515326035/signup-validate_vryadm.png" /> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1515326035/login-validate_lagmxq.png"/>

<img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1515326035/timeline_vtbhqb.png"/> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1515326036/tweet_a71b0s.png"/>

<img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1515326035/user-page_rvcaol.png"/> <img src="http://res.cloudinary.com/cloud101/image/upload/c_scale,h_500,w_250/v1515326036/update-settings_adymq1.png"/>

