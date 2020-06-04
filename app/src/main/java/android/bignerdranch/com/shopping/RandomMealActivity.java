package android.bignerdranch.com.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomMealActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_meal);

//        backButton = v.findViewById(R.id.back_button);

        textViewResult = findViewById(R.id.random_meal);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/random.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

//        Call<List<Meal>> call = jsonPlaceHolderAPI.getRandomMeal();
//        Call<android.bignerdranch.com.shopping.Response> call = jsonPlaceHolderAPI.getRandomMeal();
        Call<Response> call = (Call<Response>) jsonPlaceHolderAPI.getRandomMeal();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Response getResponse = response.body();

                Meal meal = getResponse.meals.get(0);

                // todo: use recycleView

                String content = "";

                content += "Name: " + meal.getStrMeal() + "\n";
                content += "Category: " + meal.getStrCategory() + "\n";
                content += "Ingredient 1: " + meal.getStrIngredient1() + "\n";
                content += "Ingredient 2: " + meal.getStrIngredient2() + "\n";
                content += "Ingredient 3: " + meal.getStrIngredient3() + "\n";
                content += "Ingredient 4: " + meal.getStrIngredient4() + "\n";
                content += "Ingredient 5: " + meal.getStrIngredient5() + "\n";
                content += "Ingredient 6: " + meal.getStrIngredient6() + "\n";
                content += "Ingredient 7: " + meal.getStrIngredient7() + "\n";
                content += "Ingredient 8: " + meal.getStrIngredient8() + "\n";
                content += "Ingredient 9: " + meal.getStrIngredient9() + "\n";
                content += "Ingredient 10: " + meal.getStrIngredient10() + "\n";
                content += "Instructions: " + meal.getStrInstructions() + "\n";

                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                textViewResult.setText(t.getMessage());


            }
        });


//        call.enqueue(new Callback<List<Meal>>() {
//            @Override
//            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
//                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
//                    return;
//                }
//
//                List<Meal> randomMeals = response.body();
//
//                Meal meal = randomMeals.get(0);
//
//                String content = null;
//
//                content += "Name: " + meal.getStrMeal() + "\n";
//                content += "Category: " + meal.getStrCategory() + "\n";
//
//                textViewResult.append(content);
//            }
//
//            @Override
//            public void onFailure(Call<List<Meal>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//
//            }
//        });
    }
}
