package zerobase.weather.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.DateWeather;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class WeatherDateTest {

    @Autowired
    private DiaryService diaryService;

    @MockBean
    private DateWeatherRepository dateWeatherRepository;

    @Test
    @DisplayName("스케줄러 저장 성공 테스트")
    void saveWeatherDateTest(){

        DateWeather mockWeather = new DateWeather();
        mockWeather.setDate(LocalDate.now());
        mockWeather.setWeather("Clear");
        mockWeather.setIcon("01d");
        mockWeather.setTemperature(25.0);


        Mockito.when(dateWeatherRepository.save(Mockito.any(DateWeather.class))).thenReturn(mockWeather);

        diaryService.saveWeatherDate();


        Mockito.verify(dateWeatherRepository, Mockito.times(1)).save(Mockito.any(DateWeather.class));
    }
}
