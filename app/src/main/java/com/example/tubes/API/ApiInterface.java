package com.example.tubes.API;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("pelanggan")
    Call<UserResponse> getAllPelanggan(@Query("data") String data);

    @GET("pelanggan/{id}")
    Call<UserResponse> getPelangganById(@Path("id")String id,
                                   @Query("data")String data);

    @POST("pelanggan")
    @FormUrlEncoded
    Call<UserResponse> createPelanggan(@Field("nama_pelanggan")String nama_pelanggan,
                                  @Field("jenis_room")String room,
                                  @Field("tanggal")String date,
                                  @Field("jml_orang_dewasa")String jml_dewasa,
                                  @Field("jml_anak_kecil")String jml_kecil);


    @POST("pelanggan/delete/{id}")
    @FormUrlEncoded
    Call<UserResponse> deletePelanggan(@Path("id") String id,@Field("nama_pelanggan") String nama_pelanggan, @Field("jenis_room") String room,
                                  @Field("tanggal") String date, @Field("jml_orang_dewasa") String jml_dewasa,
                                  @Field("jml_anak_kecil") String jml_kecil);

    @POST("pelanggan/{id}")
    @FormUrlEncoded
    Call<UserResponse> updatePelanggan(@Path("id") String id,@Field("nama_pelanggan") String nama_pelanggan, @Field("jenis_room") String room,
                                  @Field("tanggal") String date, @Field("jml_orang_dewasa") String jml_dewasa,
                                  @Field("jml_anak_kecil") String jml_kecil);
}
