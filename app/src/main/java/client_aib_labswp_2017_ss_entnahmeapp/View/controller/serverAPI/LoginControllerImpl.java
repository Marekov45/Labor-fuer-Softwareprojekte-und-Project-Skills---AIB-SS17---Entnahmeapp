package client_aib_labswp_2017_ss_entnahmeapp.View.controller.serverAPI;

import client_aib_labswp_2017_ss_entnahmeapp.View.controller.enumResponseCode.ResponseCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;

/**
 * {@link LoginControllerImpl} implements the REST request regarding the user authentication.
 */
public class LoginControllerImpl {

    private final String BASE_URL = "http://10.0.2.2:8080/";

    public static final Gson GSON = new GsonBuilder().setLenient().create();
    // Initializes Retrofit REST API
    private Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build();

    private LoginAPI loginAPI = mRetrofit.create(LoginAPI.class);
    private CustomObserver cObserver;

    public void requestLogin(String name, String password) {
        Call<Boolean> call = loginAPI.getAuthentification(name, password);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK) {
                    Boolean status = response.body();
                    cObserver.onResponseSuccess(status, ResponseCode.LOGIN);
                } else {
                    try {
                        System.out.println(response.code());
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cObserver.onResponseError(null, ResponseCode.LOGIN);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                cObserver.onResponseFailure(ResponseCode.LOGIN);
            }
        });
    }

    /**
     * @param customObserver is not allowed to be {@code null}.
     */
    public void setCObserver(CustomObserver customObserver) {
        this.cObserver = customObserver;
    }
}
