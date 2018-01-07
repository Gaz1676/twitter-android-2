package app.mytweetapp.main;

import java.util.List;

import app.mytweetapp.models.Tweet;
import app.mytweetapp.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface TweetService {

    //>>>>>>>>>>>>>>> Users API
    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @POST("/api/users/authenticate")
    Call<User> authenticate(@Body User user);

    @GET("/api/users/{id}")
    Call<User> getUser(@Path("id") String id);

    @GET("/api/users")
    Call<List<User>> getAllUsers();

    @PUT("/api/users")
    Call<User> updateUser(@Body User user);


    //>>>>>>>>>>>>>>>> Tweets API
    @POST("/api/tweets")
    Call<Tweet> createTweet(@Body Tweet tweet);

    @GET("/api/tweets")
    Call<List<Tweet>> getAllTweets();

    @DELETE("/api/tweets/{id}")
    Call<String> deleteTweet(@Path("id") String id);


    //>>>>>>>>>>>>>>>>> Friends
    @POST("/api/users/{id}/follow")
    Call<User> follow(@Path("id") String _id, @Body String user);

    @POST("/api/users/{id}/unfollow")
    Call<User> unfollow(@Path("id") String _id, @Body String user);
}
