package com.aluracursos.currencyconverter.api;

import com.aluracursos.currencyconverter.models.Conversion;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ExchangeApi {

    private HttpClient client;

    private Gson gson;

    private final String API_URL = "https://v6.exchangerate-api.com/v6/";

    private final String API_KEY = "eceddc8bbbb641aef278444f";

    public ExchangeApi() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public Conversion convert(String from, String to, double amount) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.API_URL + this.API_KEY + "/pair/" + from + "/" + to + "/" + amount))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), Conversion.class);

    }
}
