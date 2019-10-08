package com.commuters.clicker.data.gameApi;

import com.commuters.clicker.data.entities.Profile;
import com.commuters.clicker.data.entities.Profiles;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GameApi {
    @GET("profiles?order=score,desc&transform=1")
    Call<Profiles> readProfiles();

    @GET("profiles/{username}")
    Call<Profile> readProfile(@Path("username") String username);

    @Headers("Content-Type: application/json")
    @POST("profiles")
    Call<Void> createProfile(@Body Profile profile);

    @Headers("Content-Type: application/json")
    @PUT("profiles/{username}")
    Call<Void> updateProfile(@Path("username") String username, @Body Profile profile);

    @DELETE("profiles/{username}")
    Call<Void> deleteProfile(@Path("username") String username);
}
