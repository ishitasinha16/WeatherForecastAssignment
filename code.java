import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.*;

public class Weather {

    private static final String API = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";

    private static String fetchWeatherData() throws IOException {
        URL url = new URL(API);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();

            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }

            scanner.close();
            return response.toString();
        }

        return null;
    }

    private static double getTemp(String date) throws IOException {
        String response = fetchWeatherData();

        if (response != null) {
            JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(response);
            JsonArray forecasts = rootElement.getAsJsonObject().getAsJsonArray("list");

            for (JsonElement forecastElement : forecasts) {
                JsonObject forecast = forecastElement.getAsJsonObject();
                String forecastDate = forecast.get("dt_txt").getAsString();

                if (forecastDate.contains(date)) {
                    JsonObject main = forecast.getAsJsonObject("main");
                    return main.get("temp").getAsDouble();
                }
            }
        }

        return Double.NaN;
    }

    private static double getWind(String date) throws IOException {
        String response = fetchWeatherData();

        if (response != null) {
            JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(response);
            JsonArray forecasts = rootElement.getAsJsonObject().getAsJsonArray("list");

            for (JsonElement forecastElement : forecasts) {
                JsonObject forecast = forecastElement.getAsJsonObject();
                String forecastDate = forecast.get("dt_txt").getAsString();

                if (forecastDate.contains(date)) {
                    JsonObject wind = forecast.getAsJsonObject("wind");
                    return wind.get("speed").getAsDouble();
                }
            }
        }

        return Double.NaN;
    }

    private static double getPressure(String date) throws IOException {
        String response = fetchWeatherData();

        if (response != null) {
            JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(response);
            JsonArray forecasts = rootElement.getAsJsonObject().getAsJsonArray("list");

            for (JsonElement forecastElement : forecasts) {
                JsonObject forecast = forecastElement.getAsJsonObject();
                String forecastDate = forecast.get("dt_txt").getAsString();

                if (forecastDate.contains(date)) {
                    JsonObject main = forecast.getAsJsonObject("main");
                    return main.get("pressure").getAsDouble();
                }
            }
        }

        return Double.NaN;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Weather Temperature");
            System.out.println("2. Wind Speed");
            System.out.println("3. Pressure");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Program terminated.");
                    scanner.close();
                    return;
                case 1:
                    System.out.print("Enter the date (YYYY-MM-DD HH:mm:ss format): ");
                    String date = scanner.nextLine();
                    double temp = getTemp(date);
                    if (!Double.isNaN(temp)) {
                        System.out.println("Temperature on " + date + ": " + temp + "°C");
                    } else {
                        System.out.println("No weather data available for the given date.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the date (YYYY-MM-DD HH:mm:ss format): ");
                    date = scanner.nextLine();
                    double wind = getWind(date);
                    if (!Double.isNaN(wind)) {
                        System.out.println("Wind Speed on " + date + ": " + wind + " m/s");
                    } else {
                        System.out.println("No weather data available for the given date.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the date (YYYY-MM-DD HH:mm:ss format): ");
                    date = scanner.nextLine();
                    double pressure = getPressure(date);
                    if (!Double.isNaN(pressure)) {
                        System.out.println("Pressure on " + date + ": " + pressure + " hPa");
                    } else {
                        System.out.println("No weather data available for the given date.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
