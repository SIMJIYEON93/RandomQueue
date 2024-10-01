package com.bookstudy.randomqueue.service;

import com.bookstudy.randomqueue.dto.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class QuestionService {
    private final List<QuestionDto> questions = new ArrayList<>();

    public void addQuestion(QuestionDto questionDto) {
        questions.add(questionDto);
    }

    public List<QuestionDto> getAllQuestions() {
        return questions;
    }

    public List<QuestionDto> randomizeQuestions() {
        List<QuestionDto> randomizedQuestions = new ArrayList<>(questions);
        Collections.shuffle(randomizedQuestions); // 질문들을 랜덤으로 섞기

        // 자신이 제출한 질문을 다시 받지 않도록 하는 로직
        for (int i = 0; i < randomizedQuestions.size(); i++) {
            String userNickname = randomizedQuestions.get(i).getNickname();
            String userQuestion = randomizedQuestions.get(i).getQuestion();

            // 현재 사용자에게 자신이 제출한 질문을 매칭되지 않도록 swap 처리
            for (int j = 0; j < randomizedQuestions.size(); j++) {
                if (i != j && randomizedQuestions.get(j).getNickname().equals(userNickname)) {
                    // 자신에게 매칭된 질문 발견 시, 다음 질문으로 swap
                    Collections.swap(randomizedQuestions, i, j);
                }
            }
        }

        return randomizedQuestions;
    }
}
