package com.david.clicker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameApi {
    @GET("profiles?order=score,desc&transform=1")
    Call<Profiles> getProfiles();
}
