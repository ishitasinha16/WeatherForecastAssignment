### Weather Forecast Assignment

This repository contains a Java program that interacts with the OpenWeatherMap API to fetch hourly weather forecast data for London. The user can select options to retrieve weather temperature, wind speed, or pressure for a specific date and time.

#### Usage
1. Run the `Weather.java` program using any Java IDE or compile and execute the file in the command line.
2. The program will display the following options:
   - `1. Weather Temperature`
   - `2. Wind Speed`
   - `3. Pressure`
   - `0. Exit`
3. Enter the desired option by typing the corresponding number and pressing Enter.
4. If you choose option 1, 2, or 3, the program will prompt you to enter a date in the format `YYYY-MM-DD HH:mm:ss`.
5. The program will then fetch the relevant data from the API and display the result.

#### Dependencies
- This program uses the Gson library for JSON parsing. Before running the program, make sure to add the Gson library to your classpath.
- You can download Gson from the Maven repository or other sources. Once downloaded, add the Gson jar file to your project's classpath.

#### API Endpoint
The OpenWeatherMap API endpoint used in this program:
- Endpoint: `https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22`
- API Documentation: [OpenWeatherMap API](https://openweathermap.org/api)

Feel free to fork this repository and explore the code.
