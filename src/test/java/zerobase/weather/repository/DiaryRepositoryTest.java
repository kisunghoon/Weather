package zerobase.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Diary;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class DiaryRepositoryTest {

    @Autowired
    DiaryRepository diaryRepository;

    @Test
    void insertDiaryTest(){

        Diary diary = new Diary();

        diary.setId(1);
        diary.setWeather("Clear");
        diary.setIcon("01n");
        diary.setTemperature(282.81);
        diary.setText("testText");
        diary.setDate(LocalDate.now());


        diaryRepository.save(diary);

        List<Diary> diaries = diaryRepository.findAll();

        assertTrue(diaries.size() > 0);
    }

    @Test
    void updateDiaryTest(){

        Diary diary = new Diary();

        diary.setId(1);
        diary.setWeather("Clear");
        diary.setIcon("01n");
        diary.setTemperature(282.81);
        diary.setText("testText");
        diary.setDate(LocalDate.now());


        diaryRepository.save(diary);

        LocalDate targetDate = LocalDate.now();
        String updateText = "Updated Text";

        Diary existingDiary = diaryRepository.getFirstByDate(targetDate);
        existingDiary.setText(updateText);

        diaryRepository.save(existingDiary);

        Diary updatedDiary = diaryRepository.getFirstByDate(targetDate);
        assertTrue(updatedDiary.getText().equals(updateText), "Updated Text");
    }

    @Test
    void deleteDiaryTest(){

        Diary diary = new Diary();

        diary.setId(1);
        diary.setWeather("Clear");
        diary.setIcon("01n");
        diary.setTemperature(282.81);
        diary.setText("testText");
        diary.setDate(LocalDate.now());


        diaryRepository.save(diary);

        LocalDate targetDate = LocalDate.now();

        diaryRepository.deleteAllByDate(targetDate);

        List<Diary> diaries = diaryRepository.findAllByDate(targetDate);

        assertTrue(diaries.size() == 0);

    }
}
