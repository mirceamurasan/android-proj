package android.bignerdranch.com.shopping;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("random")
    Call<Response> getRandomMeal();
//    void getRandomMeal(Callback<Meal> meals);

}
