package com.david.clicker;

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
    Call<Integer> createProfile(@Body Profile profile);

    @Headers("Content-Type: application/json")
    @PUT("profiles/{username}")
    Call<Integer> updateProfile(@Path("username") String username, @Body Profile profile);

    @DELETE("profiles/{username}")
    Call<Integer> deleteProfile(@Path("username") String username);
}
