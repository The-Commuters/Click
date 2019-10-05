package com.david.clicker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileApi {

    @GET("profiles")
    Call<Profiles> getProfiles();
}
